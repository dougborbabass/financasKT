package br.com.douglas.financaskt.ui.activity

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import br.com.douglas.financaskt.R
import br.com.douglas.financaskt.delegate.TransactDelegate
import br.com.douglas.financaskt.model.Transact
import br.com.douglas.financaskt.ui.adapter.ListTrasactsAdapter
import br.com.douglas.financaskt.ui.dialog.AddTransactDialog
import kotlinx.android.synthetic.main.activity_list_transacts.*

class ListTransactsActivity : AppCompatActivity() {

    private val transacts: MutableList<Transact> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_transacts)

        configureResume()
        configureList()

        list_transacts_add_revenue
                .setOnClickListener {
                    AddTransactDialog(window.decorView as ViewGroup, this)
                            .configureDialog(object : TransactDelegate {
                                override fun delegate(transact: Transact) {
                                    refreshTransacts(transact)
                                    list_transacts_add_menu.close(true)
                                }
                            })
                }
    }

    private fun refreshTransacts(transact: Transact) {
        transacts.add(transact)
        configureList()
        configureResume()
    }

    private fun configureResume() {
        val view = window.decorView
        val resumeView = ResumeView(this, view, transacts)
        resumeView.refresh()
    }

    private fun configureList() {
        list_transacts_listview.adapter = ListTrasactsAdapter(transacts, this)
    }
}