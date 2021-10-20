package br.com.douglas.financaskt.ui.dialog

import android.content.Context
import android.view.ViewGroup
import br.com.douglas.financaskt.R
import br.com.douglas.financaskt.model.Type

class AddTransactDialog(
    viewGroup: ViewGroup,
    context: Context) : FormTransactDialog(context, viewGroup) {

    override val titleButtonPositive: String
        get() = "Adicionar"

    override fun titleBy(type: Type): Int {
        if (type == Type.REVENUE) {
            return R.string.adiciona_receita
        }
        return R.string.adiciona_despesa
    }
}