package com.mma.orbankmamtest.presentation.accounts

import com.mma.orbankmamtest.domain.models.Account

object AccountsModelTransformer {

     fun transformToAccountDataDisplayModel(model: List<Account>) : List<AccountsDisplayModel> =
         model.map { account ->
             with(account) {
                 AccountsDisplayModel(
                     nickname = nickname,
                     transactionsUrl = transactionsUrl,
                 )
             }
         }
}