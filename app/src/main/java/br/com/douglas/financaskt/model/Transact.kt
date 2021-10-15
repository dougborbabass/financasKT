package br.com.douglas.financaskt.model

import java.math.BigDecimal
import java.util.Calendar

class Transact(val value: BigDecimal,
               val category: String = "Others",
               val type: Type,
               val date: Calendar = Calendar.getInstance()) {

    // pode ser feita a sobrecarga do construtor, assim como usar o named parameter na chamada
    constructor(value: BigDecimal, type: Type) : this(value, "Others", type)
}