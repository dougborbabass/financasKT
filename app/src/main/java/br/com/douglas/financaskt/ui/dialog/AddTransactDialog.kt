package br.com.douglas.financaskt.ui.dialog

import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import br.com.douglas.financaskt.R
import br.com.douglas.financaskt.delegate.TransactDelegate
import br.com.douglas.financaskt.extension.convertToCalendar
import br.com.douglas.financaskt.extension.formatToBr
import br.com.douglas.financaskt.model.Transact
import br.com.douglas.financaskt.model.Type
import kotlinx.android.synthetic.main.form_transact.view.*
import java.math.BigDecimal
import java.util.*

class AddTransactDialog(private val viewGroup: ViewGroup,
                        private val context: Context) {

    private val viewCreated = createLayout()

    fun configureDialog(transactDelegate: TransactDelegate) {
        configureFieldDate()
        configureFielCategory()
        configureForm(transactDelegate)
    }

    private fun configureForm(transactDelegate: TransactDelegate) {
        AlertDialog.Builder(context)
                .setTitle(R.string.adiciona_receita)
                .setView(viewCreated)
                .setPositiveButton("Add", DialogInterface.OnClickListener
                { _, _ ->
                    val valueInText = viewCreated.form_transact_value.text.toString()
                    val dateInText = viewCreated.form_transact_date.text.toString()
                    val categoryInText = viewCreated.form_transact_category.selectedItem.toString()

                    val value = convertFieldValue(valueInText)

                    val date = dateInText.convertToCalendar()

                    val transactCreated = Transact(
                            type = Type.REVENUE,
                            value = value,
                            date = date,
                            category = categoryInText)

//                    refreshTransacts(transactCreated)
//                    list_transacts_add_menu.close(true)

                    transactDelegate.delegate(transactCreated)

                })
                .setNegativeButton("Cancel", null)
                .show()
    }

    private fun convertFieldValue(valueInText: String): BigDecimal {
        return try {
            BigDecimal(valueInText)
        } catch (exception: NumberFormatException) {
            Toast.makeText(context,"Falha na conversão de valor", Toast.LENGTH_LONG).show()
            BigDecimal.ZERO
        }
    }

    private fun configureFielCategory() {
        // chamando spinner de categoria de receitas
        val adapter = ArrayAdapter
                .createFromResource(context,
                        R.array.categorias_de_receita, android.R.layout.simple_spinner_dropdown_item)

        viewCreated.form_transact_category.adapter = adapter
    }

    private fun configureFieldDate() {
        val today = Calendar.getInstance()

        val year = today.get(Calendar.YEAR)
        val month = today.get(Calendar.MONTH)
        val day = today.get(Calendar.DAY_OF_MONTH)

        viewCreated.form_transact_date.setText(today.formatToBr())

        // chamando datepicker ao clicar na data
        viewCreated.form_transact_date.setOnClickListener {
            DatePickerDialog(context,
                    { _, year, month, dayOfMonth ->
                        val dateSelected = Calendar.getInstance()
                        dateSelected.set(year, month, dayOfMonth)
                        viewCreated.form_transact_date
                                .setText(dateSelected.formatToBr())
                    }, year, month, day)
                    .show()
        }
    }

    private fun createLayout(): View {
        return LayoutInflater.from(context)
                .inflate(R.layout.form_transact, viewGroup, false)
    }
}