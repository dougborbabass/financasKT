package br.com.douglas.financaskt.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.douglas.financaskt.R
import br.com.douglas.financaskt.model.Transact
import br.com.douglas.financaskt.model.Type
import br.com.douglas.financaskt.ui.adapter.ListTrasactsAdapter
import kotlinx.android.synthetic.main.activity_list_transacts.*

import java.math.BigDecimal


class ListTransactsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_transacts)

        val transacts: List<Transact> = transactsExample()

        configureResume(transacts)

        configureList(transacts)

    }

    private fun configureResume(transacts: List<Transact>) {
        val view = window.decorView
        ResumeView(view, transacts).addRevenueResume()
        ResumeView(view, transacts).addExpenseResume()
        ResumeView(view, transacts).addTotalResume()
    }

    private fun configureList(transacts: List<Transact>) {
        list_transacts_listview.adapter = ListTrasactsAdapter(transacts, this)
    }



    private fun transactsExample(): List<Transact> {
        return listOf(Transact(BigDecimal(20.5),
                "Comida", Type.EXPENSE),
                Transact(BigDecimal(100.0),
                        "Economia", Type.REVENUE))
    }
}