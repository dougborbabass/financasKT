package br.com.douglas.financaskt.ui.activity

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.com.douglas.financaskt.R
import br.com.douglas.financaskt.extension.formatToBr
import br.com.douglas.financaskt.model.Transact
import br.com.douglas.financaskt.model.Type
import br.com.douglas.financaskt.ui.adapter.ListTrasactsAdapter
import kotlinx.android.synthetic.main.activity_list_transacts.*
import kotlinx.android.synthetic.main.form_transact.view.*
import java.math.BigDecimal
import java.util.*

class ListTransactsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_transacts)

        val transacts: List<Transact> = transactsExample()
        configureResume(transacts)
        configureList(transacts)

        // object expression similar a representação de classe anonima que é utilizada no android com java
        list_transacts_add_revenue
                .setOnClickListener {
                    val view = window.decorView
                    val viewCreated = LayoutInflater.from(this)
                            .inflate(R.layout.form_transact, view as ViewGroup, false)

                    val year = 2017
                    val month = 9
                    val day = 18

                    val today = Calendar.getInstance()
                    viewCreated.form_transact_date.setText(today.formatToBr())

                    // chamando datepicker ao clicar na data
                    viewCreated.form_transact_date.setOnClickListener{
                        DatePickerDialog(this,
                                { view, year, month, dayOfMonth ->
                                    val dateSelected = Calendar.getInstance()
                                    dateSelected.set(year, month, dayOfMonth)
                                    viewCreated.form_transact_date
                                            .setText(dateSelected.formatToBr())
                                }, year, month, day)
                                .show()
                    }

                    // chamando spinner de categoria de receitas
                    val adapter = ArrayAdapter
                            .createFromResource(this,
                                    R.array.categorias_de_receita, android.R.layout.simple_spinner_dropdown_item)

                    viewCreated.form_transact_category.adapter = adapter

                    AlertDialog.Builder(this)
                            .setTitle(R.string.adiciona_receita)
                            .setView(viewCreated)
                            .setPositiveButton("Add",null)
                            .setNegativeButton("Cancel", null)
                            .show()
                }
    }

    private fun configureResume(transacts: List<Transact>) {
        val view = window.decorView
        val resumeView = ResumeView(this, view, transacts)
        resumeView.refresh()
    }

    private fun configureList(transacts: List<Transact>) {
        list_transacts_listview.adapter = ListTrasactsAdapter(transacts, this)
    }

    private fun transactsExample(): List<Transact> {
        return listOf(Transact(BigDecimal(20.5),
                "Comida", Type.EXPENSE),
                Transact(BigDecimal(10000.0),
                        "Economia", Type.REVENUE),
                Transact(BigDecimal(800.0),
                        "Gasto muito alto",Type.EXPENSE))
    }
}