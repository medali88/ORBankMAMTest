package com.mma.orbankmamtest.data

import com.mma.orbankmamtest.domain.models.Account

sealed class AccountDataState {

    data class AccountSuccess(val accounts: List<Account>) : AccountDataState()
    object AccountFailure : AccountDataState()
}
