package br.com.douglas.financaskt.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import br.com.douglas.financaskt.R
import br.com.douglas.financaskt.extension.formateToBr
import br.com.douglas.financaskt.model.Transact
import kotlinx.android.synthetic.main.transact_item.view.*

class ListTrasactsAdapter(
    private val transacts: List<Transact>,
    private val context: Context
) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val viewCreate = LayoutInflater.from(context).inflate(R.layout.transact_item, parent, false)

        val transact = transacts[position]

        viewCreate.transact_value.text = transact.value.toString()
        viewCreate.transact_category.text = transact.category
        viewCreate.transact_date.text = transact.date.formateToBr()

        return viewCreate
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