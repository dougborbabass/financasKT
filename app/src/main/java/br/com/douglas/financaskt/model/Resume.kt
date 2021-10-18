package br.com.douglas.financaskt.model

import java.math.BigDecimal

class Resume(private val transacts: List<Transact>) {

    fun revenue(): BigDecimal {

        val sumRevenue = transacts
                .filter{ transact -> transact.type == Type.REVENUE }
                .sumByDouble { transact -> transact.value.toDouble() }

        return BigDecimal(sumRevenue)
    }

    fun expense(): BigDecimal {

        val sumExpanse = transacts
                .filter{ transact -> transact.type == Type.EXPENSE}
                .sumByDouble { transact -> transact.value.toDouble() }

        return BigDecimal(sumExpanse)
    }

    fun total(): BigDecimal {
        return revenue().subtract(expense())
    }
}