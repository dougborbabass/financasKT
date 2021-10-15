package br.com.douglas.financaskt.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.content.ContextCompat
import br.com.douglas.financaskt.R
import br.com.douglas.financaskt.extension.formatToBr
import br.com.douglas.financaskt.extension.formatToBr
import br.com.douglas.financaskt.model.Transact
import br.com.douglas.financaskt.model.Type
import kotlinx.android.synthetic.main.transact_item.view.*

class ListTrasactsAdapter(
    private val transacts: List<Transact>,
    private val context: Context
) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val viewCreate = LayoutInflater.from(context).inflate(R.layout.transact_item, parent, false)

        val transact = transacts[position]

        addValue(transact, viewCreate)
        addCategory(transact, viewCreate)
        addDate(viewCreate, transact)

        return viewCreate
    }

    private fun addValue(transact: Transact, viewCreate: View) {
        val color: Int = colorBy(transact.type)
        viewCreate.transact_value.setTextColor(color)
        viewCreate.transact_value.text = transact.value.formatToBr()
    }

    private fun addCategory(transact: Transact, viewCreate: View) {
        val icon: Int = iconBy(transact.type)
        viewCreate.transact_icon.setBackgroundResource(icon)
        viewCreate.transact_category.text = transact.category
    }

    private fun addDate(viewCreate: View, transact: Transact) {
        viewCreate.transact_date.text = transact.date.formatToBr()
    }

    private fun colorBy(type: Type): Int {
        if (type == Type.REVENUE) {
            return ContextCompat.getColor(context, R.color.receita)
        }
        return ContextCompat.getColor(context, R.color.despesa)
    }

    private fun iconBy(type: Type): Int {
        if (type == Type.REVENUE) {
            return R.drawable.icone_transacao_item_receita
        }
        return R.drawable.icone_transacao_item_despesa
    }

    override fun getCount(): Int {
        return transacts.size
    }

    override fun getItem(position: Int): Transact {
        return transacts[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }
}