package br.com.douglas.financaskt.model

import java.math.BigDecimal

class Resume(private val transacts: List<Transact>) {

    fun revenue(): BigDecimal {
        return sumBy(Type.REVENUE)
    }

    fun expense(): BigDecimal {
        return sumBy(Type.EXPENSE)
    }

    // Single expression function
    fun total() = revenue().subtract(expense())

    private fun sumBy(type: Type): BigDecimal {
        val sumOfTrasactsByType = transacts
                .filter { it.type == type }
                .sumByDouble { it.value.toDouble() } // transact -> transact. = it
        return BigDecimal(sumOfTrasactsByType)
    }
}