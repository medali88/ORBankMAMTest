package com.mma.orbankmamtest.presentation.accounts

import com.mma.orbankmamtest.domain.models.Account

object AccountsModelTransformer {

     fun transformToAccountDataDisplayModel(model: List<Account>) : List<AccountsDisplayModel> =
         model.map { account ->
             with(account) {
                 AccountsDisplayModel(
                     accountId = accountId,
                     status = status,
                     statusUpdateDateTime = statusUpdateDateTime,
                     currency = currency,
                     accountType = accountType,
                     accountSubType = accountId,
                     nickname = nickname,
                     openingDate = openingDate,
                     transactionsUrl = transactionsUrl,
                     accountInfo = accountInfo.map { info ->
                         with(info) {
                             AccountInfoDisplayModel(
                                 schemeName = schemeName,
                                 identification = identification,
                                 name = name,
                                 secondaryIdentification = secondaryIdentification,
                             )
                         }
                     },
                 )
             }
         }
}