package com.mma.orbankmamtest.presentation.accounts

import com.mma.orbankmamtest.domain.AccountsState.*
import com.mma.orbankmamtest.domain.GetAccountsUseCase
import com.mma.orbankmamtest.domain.models.Account
import com.mma.orbankmamtest.domain.models.AccountInfo
import com.mma.orbankmamtest.presentation.accounts.AccountsFetchUiState.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(MockitoExtension::class)
class AccountsViewModelTest {

    private val scheduler = TestCoroutineScheduler()
    private val mainDispatcher = StandardTestDispatcher(scheduler)

    @Mock
    lateinit var getAccountsUseCase: GetAccountsUseCase

    private lateinit var accountsViewModel: AccountsViewModel

    @BeforeEach
    internal fun setUp() {
        Dispatchers.setMain(mainDispatcher)
        accountsViewModel = AccountsViewModel(
            dispatcher = mainDispatcher,
            getAccountsUseCase = getAccountsUseCase,
        )
    }

    @AfterEach
    internal fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getAccounts when getAccounts from getAccountsUseCase is ValidAccounts then return SuccessAccountsUiState with accountsDisplayModel`() {
        runTest {
            //given
            given(getAccountsUseCase.getAccounts()).willReturn(
                ValidAccounts(
                    listOf(
                        Account(
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
                                AccountInfo(
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

            //when
            accountsViewModel.getAccounts()

            //then
            assertThat(accountsViewModel.accountsData.value).isEqualTo(LoadingAccountsUiState)
            scheduler.advanceUntilIdle()
            assertThat(accountsViewModel.accountsData.value).isEqualTo(
                SuccessAccountsUiState(
                    listOf(
                        AccountsDisplayModel(
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
                                AccountInfoDisplayModel(
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
        }
    }

    @Test
    fun `getAccounts when getAccounts from getAccountsUseCase is InvalidAccounts then return FailureAccountsUiState`() {
        runTest {
            //given
            given(getAccountsUseCase.getAccounts()).willReturn(InvalidAccounts)

            //when
            accountsViewModel.getAccounts()

            //then
            assertThat(accountsViewModel.accountsData.value).isEqualTo(LoadingAccountsUiState)
            scheduler.advanceUntilIdle()
            assertThat(accountsViewModel.accountsData.value).isEqualTo(
                FailureAccountsUiState
            )
        }
    }

    @Test
    fun `refreshAccounts when getAccounts from getAccountsUseCase then isRefreshingAccounts is false`() {
        runTest {
            //given
            given(getAccountsUseCase.getAccounts()).willReturn(InvalidAccounts)

            //when
            accountsViewModel.refreshAccounts()

            //then
            scheduler.advanceUntilIdle()
            assertThat(accountsViewModel.isRefreshingAccounts.value).isEqualTo(
                false
            )
        }
    }
}