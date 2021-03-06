package br.com.douglas.financaskt.ui.dialog

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import br.com.douglas.financaskt.R
import br.com.douglas.financaskt.extension.convertToCalendar
import br.com.douglas.financaskt.extension.formatToBr
import br.com.douglas.financaskt.model.Transact
import br.com.douglas.financaskt.model.Type
import kotlinx.android.synthetic.main.form_transact.view.*
import java.math.BigDecimal
import java.util.*

abstract class FormTransactDialog(private val context: Context, private val viewGroup: ViewGroup?) {

    private val viewCreated = createLayout()
    protected val fieldValue = viewCreated.form_transact_value
    protected val fieldDate = viewCreated.form_transact_date
    protected val fieldCategory = viewCreated.form_transact_category
    abstract val titleButtonPositive: String

    fun configureDialog(type: Type, delegate: (transact: Transact) -> Unit) {
        configureFieldDate()
        configureFielCategory(type)
        configureForm(type, delegate)
    }

    private fun configureForm(type: Type, delegate: (transact: Transact) -> Unit) {

        val title = titleBy(type)

        AlertDialog.Builder(context)
            .setTitle(title)
            .setView(viewCreated)
            .setPositiveButton(
                titleButtonPositive, DialogInterface.OnClickListener
                { _, _ ->
                    val valueInText = fieldValue.text.toString()
                    val dateInText = fieldDate.text.toString()
                    val categoryInText = fieldCategory.selectedItem.toString()

                    val value = convertFieldValue(valueInText)
                    val date = dateInText.convertToCalendar()

                    val transactCreated = Transact(
                        type = type,
                        value = value,
                        date = date,
                        category = categoryInText
                    )

                    delegate(transactCreated)

                })
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun configureFieldDate() {
        val today = Calendar.getInstance()
        val year = today.get(Calendar.YEAR)
        val month = today.get(Calendar.MONTH)
        val day = today.get(Calendar.DAY_OF_MONTH)

        fieldDate.setText(today.formatToBr())

        // chamando datepicker ao clicar na data
        fieldDate.setOnClickListener {
            DatePickerDialog(
                context,
                { _, year, month, dayOfMonth ->
                    val dateSelected = Calendar.getInstance()
                    dateSelected.set(year, month, dayOfMonth)
                    fieldDate
                        .setText(dateSelected.formatToBr())
                }, year, month, day
            )
                .show()
        }
    }

    private fun configureFielCategory(type: Type) {
        val categories = categoriesBy(type)

        val adapter = ArrayAdapter
            .createFromResource(
                context,
                categories, android.R.layout.simple_spinner_dropdown_item
            )

        fieldCategory.adapter = adapter
    }

    private fun createLayout(): View {
        return LayoutInflater.from(context)
            .inflate(R.layout.form_transact, viewGroup, false)
    }

    private fun convertFieldValue(valueInText: String): BigDecimal {
        return try {
            BigDecimal(valueInText)
        } catch (exception: NumberFormatException) {
            Toast.makeText(context, "Falha na convers??o de valor", Toast.LENGTH_LONG).show()
            BigDecimal.ZERO
        }
    }

    abstract protected fun titleBy(type: Type): Int

    protected fun categoriesBy(type: Type): Int {
        if (type == Type.REVENUE) {
            return R.array.categorias_de_receita
        }
        return R.array.categorias_de_despesa
    }
}