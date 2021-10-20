package br.com.douglas.financaskt.ui.activity

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import br.com.douglas.financaskt.R
import br.com.douglas.financaskt.extension.formatToBr
import br.com.douglas.financaskt.model.Resume
import br.com.douglas.financaskt.model.Transact
import kotlinx.android.synthetic.main.resume_card.view.*
import java.math.BigDecimal

class ResumeView(
    context: Context,
    private val view: View,
    transacts: List<Transact>
) {

    private val resume: Resume = Resume(transacts)
    private val colorExpanse = ContextCompat.getColor(context, R.color.despesa)
    private val colorRevenue = ContextCompat.getColor(context, R.color.receita)

    fun refresh() {
        addRevenueResume()
        addExpenseResume()
        addTotalResume()
    }

    private fun addRevenueResume() {
        val totalRevenue = resume.revenue()
        with(view.resume_card_revenue) {
            setTextColor(colorRevenue)
            text = totalRevenue.formatToBr()

        }
    }

    private fun addExpenseResume() {
        val totalExpense = resume.expense()
        with(view.resume_card_expense) {
            setTextColor(colorExpanse)
            text = totalExpense.formatToBr()
        }

    }

    private fun addTotalResume() {
        val total = resume.total()
        val color = colorBy(total)
        with(view.resume_card_total) {
            setTextColor(color)
            text = total.formatToBr()
        }
    }

    private fun colorBy(total: BigDecimal) = if (total >= BigDecimal.ZERO) {
        colorRevenue
    } else {
        colorExpanse
    }
}