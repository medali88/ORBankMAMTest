package com.mma.orbankmamtest.data

import com.mma.orbankmamtest.domain.model.Account

sealed class AccountDataResponse {

    data class Success(val account: List<Account>) :
        AccountDataResponse()

    object Failure : AccountDataResponse()
}
