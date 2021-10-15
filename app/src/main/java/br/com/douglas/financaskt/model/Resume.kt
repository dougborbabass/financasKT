package br.com.douglas.financaskt.model

import java.math.BigDecimal

class Resume(private val transacts: List<Transact>) {

    fun revenue(): BigDecimal {
        var totalRevenue = BigDecimal.ZERO
        for (transact in transacts) {
            if (transact.type == Type.REVENUE) {
                totalRevenue = totalRevenue.plus(transact.value)
            }
        }
        return totalRevenue
    }

    fun expense(): BigDecimal {
        var totalExpense = BigDecimal.ZERO
        for (transact in transacts) {
            if (transact.type == Type.EXPENSE) {
                totalExpense = totalExpense.plus(transact.value)
            }
        }
        return totalExpense
    }

    fun total(): BigDecimal {
        return revenue().subtract(expense())
    }
}