package br.com.douglas.financaskt.ui.dialog

import android.content.Context
import android.view.ViewGroup
import br.com.douglas.financaskt.delegate.TransactDelegate
import br.com.douglas.financaskt.extension.formatToBr
import br.com.douglas.financaskt.model.Transact

class ChangeTransactDialog(
    viewGroup: ViewGroup,
    private val context: Context
) : FormTransactDialog(context, viewGroup) {


    fun configureDialog(transact: Transact, transactDelegate: TransactDelegate) {
        val type = transact.type

        super.configureDialog(type, transactDelegate)

        fieldValue.setText(transact.value.toString())
        fieldDate.setText(transact.date.formatToBr())

        val categoriesArray = context.resources.getStringArray(categoriesBy(type))
        val posCategory = categoriesArray.indexOf(transact.category)
        fieldCategory.setSelection(posCategory, true)
    }

}
