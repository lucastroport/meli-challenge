package com.lucas.yourmarket.presentation.util

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*


fun Double.toCurrency(): String {
    return try {
        val parsedNumber = this.toBigDecimal()
        getCurrencyValue(parsedNumber)
    } catch (e: NumberFormatException) {
        return this.toString()
    }
}

private fun getCurrencyValue(number: BigDecimal): String {
    val currencyInstance = NumberFormat.getCurrencyInstance(Locale.ENGLISH)
    val symbol = currencyInstance.currency?.symbol ?: " "
    return removeCurrencyTrailingZeroes(
        currencyInstance
            .format(number)
            .replace(symbol, "")
    )
}

private fun removeCurrencyTrailingZeroes(currency: String): String {
    currency.let {
        if (currency.length > 3) {
            val dotAndZeros = currency.takeLast(3)
            if (dotAndZeros == ".00") {
                return currency.dropLast(3)
            }
        }
    }
    return currency
}
