package br.com.douglas.financaskt.ui.activity

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import br.com.douglas.financaskt.R
import br.com.douglas.financaskt.delegate.TransactDelegate
import br.com.douglas.financaskt.model.Transact
import br.com.douglas.financaskt.model.Type
import br.com.douglas.financaskt.ui.adapter.ListTrasactsAdapter
import br.com.douglas.financaskt.ui.dialog.AddTransactDialog
import br.com.douglas.financaskt.ui.dialog.ChangeTransactDialog
import kotlinx.android.synthetic.main.activity_list_transacts.*

class ListTransactsActivity : AppCompatActivity() {

    private val transacts: MutableList<Transact> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_transacts)

        configureResume()
        configureList()
        configureFab()
    }

    private fun configureFab() {
        list_transacts_add_revenue
            .setOnClickListener {
                callDialogOfAdd(Type.REVENUE)
            }

        list_transacts_add_expanse
            .setOnClickListener {
                callDialogOfAdd(Type.EXPENSE)
            }
    }

    private fun callDialogOfAdd(type: Type) {
        AddTransactDialog(window.decorView as ViewGroup, this)
            .configureDialog(type, object : TransactDelegate {
                override fun delegate(transact: Transact) {
                    transacts.add(transact)
                    refreshTransacts()
                    list_transacts_add_menu.close(true)
                }
            })
    }

    private fun refreshTransacts() {
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
        list_transacts_listview.setOnItemClickListener { parent, view, position, id ->
            val transact = transacts[position]
            ChangeTransactDialog(window.decorView as ViewGroup, this)
                .configureDialog(transact, object: TransactDelegate {
                    override fun delegate(transact: Transact) {
                        transacts[position] = transact
                        refreshTransacts()
                    }
                })
        }
    }
}