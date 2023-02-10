package com.mma.orbankmamtest.data.source

import com.mma.orbankmamtest.data.model.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(MockitoExtension::class)
class AccountServiceTest {

    @Mock
    lateinit var accountEndPoint: AccountEndPoint

    @InjectMocks
    lateinit var accountService: AccountService


    private val accounts = JsonAccountResponse(
        data = AccountList(
            account = listOf(
                JsonAccountInformation(
                    accountId = "accountId",
                    status = "status",
                    statusUpdateDateTime = "statusUpdateDateTime",
                    currency = "currency",
                    accountType = "accountType",
                    accountSubType = "accountId",
                    nickname = "nickname",
                    openingDate = "openingDate",
                    transactionsUrl = "transactionsUrl",
                    accountInfo = listOf(
                        JsonAccountInfo(
                            schemeName = "schemeName",
                            identification = "identification",
                            name = "name",
                            secondaryIdentification = "secondaryIdentification",
                        )
                    )
                )
            )
        )
    )

    @Test
    fun `getAccountsData - when call the endPoint - then return accounts`() {
        runTest {
            //given
            given(accountEndPoint.getAccounts()).willReturn(accounts)

            //when
            val result = accountService.getAccountsData()

            //then
            assertThat(result).isEqualTo(accounts)
        }
    }
}