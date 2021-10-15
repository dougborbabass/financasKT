package br.com.douglas.financaskt.ui.activity

import android.view.View
import br.com.douglas.financaskt.extension.formatToBr
import br.com.douglas.financaskt.model.Resume
import br.com.douglas.financaskt.model.Transact
import kotlinx.android.synthetic.main.resume_card.view.*

class ResumeView(private val view: View,
                 private val transacts: List<Transact>) {

    private val resume: Resume = Resume(transacts)

    fun addRevenueResume() {
        val totalRevenue = resume.revenue()
        view.resume_card_revenue.text = totalRevenue.formatToBr()
    }

    fun addExpenseResume() {
        val totalExpense = resume.expense()
        view.resume_card_expense.text = totalExpense.formatToBr()
    }

    fun addTotalResume() {
        val total = resume.total()
        view.resume_card_total.text = total.formatToBr()
    }
}