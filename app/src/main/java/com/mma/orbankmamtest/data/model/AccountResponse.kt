package com.mma.orbankmamtest.data.model

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class JsonAccountResponse(
    @Json(name = "Data")
    val data: AccountList
)

@Keep
@JsonClass(generateAdapter = true)
data class AccountList(
    @Json(name = "Account")
    val account: List<JsonAccountInformation>
)

@Keep
@JsonClass(generateAdapter = true)
data class JsonAccountInformation(
    @Json(name = "AccountId")
    val accountId: String,
    @Json(name = "Status")
    val status: String,
    @Json(name = "StatusUpdateDateTime")
    val statusUpdateDateTime: String,
    @Json(name = "Currency")
    val currency: String,
    @Json(name = "AccountType")
    val accountType: String,
    @Json(name = "AccountSubType")
    val accountSubType: String,
    @Json(name = "Nickname")
    val nickname: String,
    @Json(name = "OpeningDate")
    val openingDate: String?,
    @Json(name = "transactionsUrl")
    val transactionsUrl: String,
    @Json(name = "Account")
    val accountInfo: List<JsonAccountInfo>
)

@Keep
@JsonClass(generateAdapter = true)
data class JsonAccountInfo(
    @Json(name = "SchemeName")
    val schemeName: String,
    @Json(name = "Identification")
    val identification: String,
    @Json(name = "Name")
    val name: String,
    @Json(name = "SecondaryIdentification")
    val secondaryIdentification: String?,
)

