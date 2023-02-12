package com.mma.orbankmamtest.data.model

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class JsonTransactionResponse(
    @Json(name = "Data")
    val data: TransactionList
)

@Keep
@JsonClass(generateAdapter = true)
data class TransactionList(
    @Json(name = "Transaction")
    val transaction: List<JsonTransactionInformation>
)

@Keep
@JsonClass(generateAdapter = true)
data class JsonTransactionInformation(
    @Json(name = "TransactionId")
    val transactionId: String,
    @Json(name = "TransactionReference")
    val transactionReference: String,
    @Json(name = "Amount")
    val amount: JsonAmount,
    @Json(name = "CreditDebitIndicator")
    val creditDebitIndicator: String,
    @Json(name = "Status")
    val status: String,
    @Json(name = "ValueDateTime")
    val valueDateTime: String,
    @Json(name = "TransactionInformation")
    val transactionInformation: String,
    @Json(name = "BankTransactionCode")
    val bankTransactionCode: JsonBankTransactionCode,
    @Json(name = "ProprietaryBankTransactionCode")
    val proprietaryBankTransactionCode: JsonProprietaryBankTransactionCode,
)

@Keep
@JsonClass(generateAdapter = true)
data class JsonAmount(
    @Json(name = "Amount")
    val amount: String,
    @Json(name = "Currency")
    val currency: String
)

@Keep
@JsonClass(generateAdapter = true)
data class JsonBankTransactionCode(
    @Json(name = "Code")
    val code: String,
    @Json(name = "SubCode")
    val subCode: String
)

@Keep
@JsonClass(generateAdapter = true)
data class JsonProprietaryBankTransactionCode(
    @Json(name = "Code")
    val code: String,
    @Json(name = "Issuer")
    val issuer: String
)
