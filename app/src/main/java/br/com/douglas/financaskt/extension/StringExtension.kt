package br.com.douglas.financaskt.extension

import java.text.SimpleDateFormat
import java.util.*

fun String.limitOf(characters: Int) : String {
    if(this.length > characters) {
        return "${this.substring(0, characters)} ..."
    }
    return this
}

fun String.convertToCalendar(): Calendar {
    val formatBr = SimpleDateFormat("dd/MM/yyyy")
    val dateConverted = formatBr.parse(this)
    val date = Calendar.getInstance()
    date.time = dateConverted
    return date
}