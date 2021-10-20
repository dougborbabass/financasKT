package br.com.douglas.financaskt.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.douglas.financaskt.R
import br.com.douglas.financaskt.model.Transact
import br.com.douglas.financaskt.model.Type
import br.com.douglas.financaskt.ui.adapter.ListTrasactsAdapter
import br.com.douglas.financaskt.ui.dialog.AddTransactDialog
import br.com.douglas.financaskt.ui.dialog.ChangeTransactDialog
import kotlinx.android.synthetic.main.activity_list_transacts.*

class ListTransactsActivity : AppCompatActivity() {

    private val transacts: MutableList<Transact> = mutableListOf()

    // utilizando o by lazy ele vai inicializar somente quando precisar
    private val viewOfActivity by lazy {
        window.decorView
    }

    private val viewGroupOfActivity by lazy {
        viewOfActivity as ViewGroup
    }

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
        AddTransactDialog(viewGroupOfActivity, this)
            .configureDialog(type, delegate = { transactCreated ->
                addTransact(transactCreated)
                list_transacts_add_menu.close(true)
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

            setOnCreateContextMenuListener { menu, _, _ ->
                menu.add(Menu.NONE, 1, Menu.NONE, "Remover")
            }
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val idOfMenu = item.itemId
        if (idOfMenu == 1) {
            val adapterContextMenuInfo = item.menuInfo as AdapterView.AdapterContextMenuInfo
            val positionTransact = adapterContextMenuInfo.position
            remove(positionTransact)
        }
        return super.onContextItemSelected(item)
    }

    private fun remove(position: Int) {
        transacts.removeAt(position)
        refreshTransacts()
    }

    private fun callDialogChange(transact: Transact, position: Int) {
        ChangeTransactDialog(viewGroupOfActivity, this)
            .configureDialog(transact, delegate = { transactChanged ->
                changeTransact(transactChanged, position)
            })
    }

    private fun changeTransact(transact: Transact, position: Int) {
        transacts[position] = transact
        refreshTransacts()
    }
}