package br.com.douglas.financaskt.ui.activity

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import br.com.douglas.financaskt.R
import br.com.douglas.financaskt.ui.adapter.ListaTrasacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*


class ListaTransacoesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        val transacoes = listOf("Comida - R$25,00", "Economia - R$100,00")

        lista_transacoes_listview.adapter = ListaTrasacoesAdapter(transacoes, this)

    }
}