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

    fun configureDialog(type: Type, transactDelegate: TransactDelegate) {
        configureFieldDate()
        configureFielCategory(type)
        configureForm(type, transactDelegate)
    }

    private fun configureForm(type: Type, transactDelegate: TransactDelegate) {

        val title = if(type == Type.REVENUE) {
            R.string.adiciona_receita
        } else {
            R.string.adiciona_despesa
        }

        AlertDialog.Builder(context)
                .setTitle(title)
                .setView(viewCreated)
                .setPositiveButton("Add", DialogInterface.OnClickListener
                { _, _ ->
                    val valueInText = viewCreated.form_transact_value.text.toString()
                    val dateInText = viewCreated.form_transact_date.text.toString()
                    val categoryInText = viewCreated.form_transact_category.selectedItem.toString()

                    val value = convertFieldValue(valueInText)

                    val date = dateInText.convertToCalendar()

                    val transactCreated = Transact(
                            type = type,
                            value = value,
                            date = date,
                            category = categoryInText)

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

    private fun configureFielCategory(type: Type) {

        val categories = if(type == Type.REVENUE) {
            R.array.categorias_de_receita
        } else {
            R.array.categorias_de_despesa
        }

        val adapter = ArrayAdapter
                .createFromResource(context,
                        categories, android.R.layout.simple_spinner_dropdown_item)

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