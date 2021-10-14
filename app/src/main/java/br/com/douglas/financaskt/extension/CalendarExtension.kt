package br.com.douglas.financaskt.extension

import java.text.SimpleDateFormat
import java.util.*

// extension function de Calendar
fun Calendar.formateToBr(): String {
    val formatBrDate = "dd/MM/yyyy"
    val simpleDateFormat = SimpleDateFormat(formatBrDate)
    return simpleDateFormat.format(time)
}