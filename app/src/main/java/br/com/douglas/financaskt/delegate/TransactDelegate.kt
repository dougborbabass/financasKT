package br.com.douglas.financaskt.delegate

import br.com.douglas.financaskt.model.Transact

interface TransactDelegate {
    fun delegate(transact: Transact)
}