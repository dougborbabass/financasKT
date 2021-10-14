package br.com.douglas.financaskt.model

import java.math.BigDecimal
import java.util.Calendar

class Transact(val value: BigDecimal,
               val category: String,
               val date: Calendar)