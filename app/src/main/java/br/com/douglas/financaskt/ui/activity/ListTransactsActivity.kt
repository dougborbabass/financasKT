package br.com.douglas.financaskt.ui.activity

import android.os.Bundle
import android.view.View
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
    private lateinit var viewOfActivity: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_transacts)

        viewOfActivity = window.decorView

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
        AddTransactDialog(viewOfActivity as ViewGroup, this)
            .configureDialog(type, object : TransactDelegate {
                override fun delegate(transact: Transact) {
                    addTransact(transact)
                    list_transacts_add_menu.close(true)
                }
            })
    }

    private fun addTransact(transact: Transact) {
        transacts.add(transact)
        refreshTransacts()
    }

    private fun refreshTransacts() {
        configureList()
        configureResume()
    }

    private fun configureResume() {
        val resumeView = ResumeView(this, viewOfActivity, transacts)
        resumeView.refresh()
    }

    private fun configureList() {
        with(list_transacts_listview) {
            adapter = ListTrasactsAdapter(transacts, this@ListTransactsActivity)
            setOnItemClickListener { _, _, position, _ ->
                val transact = transacts[position]
                callDialogChange(transact, position)
            }
        }
    }

    private fun callDialogChange(transact: Transact, position: Int) {
        ChangeTransactDialog(viewOfActivity as ViewGroup, this)
            .configureDialog(transact, object : TransactDelegate {
                override fun delegate(transact: Transact) {
                    changeTransact(transact, position)
                }
            })
    }

    private fun changeTransact(transact: Transact, position: Int) {
        transacts[position] = transact
        refreshTransacts()
    }
}