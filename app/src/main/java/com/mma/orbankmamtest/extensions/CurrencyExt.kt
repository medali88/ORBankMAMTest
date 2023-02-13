package com.mma.orbankmamtest.extensions

import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale
import java.util.Currency as CurrencyJava

private const val NC = "-"

private val numberFormatWithCurrency =
    DecimalFormat.getCurrencyInstance(Locale.FRANCE) as DecimalFormat

fun BigDecimal.toCurrency(): String {
    val symbolsWithCurrency = buildDecimalSymbols()
    symbolsWithCurrency.currency = CurrencyJava.getInstance("EUR")
    initDecimalFormat()
    return formatCurrency(this)
}

private fun formatCurrency(value: BigDecimal?): String = if (value == null) {
    NC
} else {
    numberFormatWithCurrency.format(value)
}

private fun initDecimalFormat() {
    val symbolsWithCurrency = buildDecimalSymbols()
    numberFormatWithCurrency.applyPattern("###,###.## ¤")
    numberFormatWithCurrency.decimalFormatSymbols = symbolsWithCurrency
    numberFormatWithCurrency.maximumFractionDigits = 2
    numberFormatWithCurrency.minimumFractionDigits = 2
}

private fun buildDecimalSymbols() = DecimalFormatSymbols(Locale.FRANCE).apply {
    decimalSeparator = ','
    groupingSeparator = ' '
    minusSign = '-'
}
