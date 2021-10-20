package br.com.douglas.financaskt.dao

import br.com.douglas.financaskt.model.Transact

class TransactDAO {

    val transacts: List<Transact> = Companion.transacts
    companion object {
        private val transacts: MutableList<Transact> = mutableListOf()
    }

    fun add(transact: Transact) {
        Companion.transacts.add(transact)
    }

    fun change(transact: Transact, position: Int) {
        Companion.transacts[position] = transact
    }

    fun remove(position: Int) {
        Companion.transacts.removeAt(position)
    }
}