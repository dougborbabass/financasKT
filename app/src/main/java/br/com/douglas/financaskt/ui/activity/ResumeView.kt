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

class ResumeView(private val context: Context,
                 private val view: View,
                 transacts: List<Transact>) {

    private val resume: Resume = Resume(transacts)

    fun addRevenueResume() {
        val totalRevenue = resume.revenue()
        view.resume_card_revenue.setTextColor(ContextCompat.getColor(context, R.color.receita))
        view.resume_card_revenue.text = totalRevenue.formatToBr()
    }

    fun addExpenseResume() {
        val totalExpense = resume.expense()
        view.resume_card_expense.setTextColor(ContextCompat.getColor(context, R.color.despesa))
        view.resume_card_expense.text = totalExpense.formatToBr()
    }

    fun addTotalResume() {
        val total = resume.total()
        if (total >= BigDecimal.ZERO) {
            view.resume_card_total.setTextColor((ContextCompat.getColor(context, R.color.receita)))
        } else {
            view.resume_card_total.setTextColor((ContextCompat.getColor(context, R.color.despesa)))
        }
        view.resume_card_total.text = total.formatToBr()
    }
}