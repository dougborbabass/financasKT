package br.com.douglas.financaskt.extension

import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.Locale

fun BigDecimal.formatToBr() : String {
    val instanceBR = DecimalFormat.getCurrencyInstance(Locale("pt", "br"))
    return instanceBR.format(this).replace("-R$", "R$ -")
}