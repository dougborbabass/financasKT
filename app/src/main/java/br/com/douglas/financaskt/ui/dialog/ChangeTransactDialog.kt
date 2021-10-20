package br.com.douglas.financaskt.ui.dialog

import android.content.Context
import android.view.ViewGroup
import br.com.douglas.financaskt.R
import br.com.douglas.financaskt.extension.formatToBr
import br.com.douglas.financaskt.model.Transact
import br.com.douglas.financaskt.model.Type

class ChangeTransactDialog(
    viewGroup: ViewGroup,
    private val context: Context
) : FormTransactDialog(context, viewGroup) {

    override val titleButtonPositive: String
        get() = "Alterar"

    fun configureDialog(transact: Transact, delegate: (transact: Transact) -> Unit) {
        val type = transact.type
        super.configureDialog(type, delegate)
        initFields(transact)
    }

    private fun initFields(transact: Transact) {
        initFieldValue(transact)
        initFieldDate(transact)
        initFieldCategory(transact.type, transact)
    }

    private fun initFieldCategory(type: Type, transact: Transact) {
        val categoriesArray = context.resources.getStringArray(categoriesBy(type))
        val posCategory = categoriesArray.indexOf(transact.category)
        fieldCategory.setSelection(posCategory, true)
    }

    private fun initFieldDate(transact: Transact) {
        fieldDate.setText(transact.date.formatToBr())
    }

    private fun initFieldValue(transact: Transact) {
        fieldValue.setText(transact.value.toString())
    }

    override fun titleBy(type: Type): Int {
        if (type == Type.REVENUE) {
            return R.string.altera_receita
        }
        return R.string.altera_despesa
    }
}
