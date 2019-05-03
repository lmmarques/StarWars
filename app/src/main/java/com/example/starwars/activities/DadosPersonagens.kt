package com.example.starwars.activities

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.starwars.R
import com.example.starwars.utils.dynamicHeigthListView
import kotlinx.android.synthetic.main.activity_dados_personagens.*


class DadosPersonagens : AppCompatActivity() {
    private var listPersonagem: List<String> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dados_personagens)


        buttonBackDadosPersonagem.setOnClickListener {
            super.onBackPressed()
            StarWarsPeopleActivity.dadosPersonagem.clear()
        }

        buttonPerquisarGoogle.setOnClickListener {
            try {
                //faz a pesquisa no google
                val intent = Intent(Intent.ACTION_WEB_SEARCH)
                //obtem o nome da personagem
                val term = StarWarsPeopleActivity.nomePersonagem
                intent.putExtra(SearchManager.QUERY, term)
                startActivity(intent)
            } catch (e: Exception) {
                e.printStackTrace()
            }


        }

        //carrega a lista com os dados da personagem
        listPersonagem = StarWarsPeopleActivity.dadosPersonagem.toList()
        val adapter = ArrayAdapter<String>(this, R.layout.row_dados_personagens, listPersonagem)
        listViewDadosPersonagem.adapter = adapter
        dynamicHeigthListView().setListViewHeightBasedOnChildren(listViewDadosPersonagem, this)
       
    }

    override fun onBackPressed() {
        super.onBackPressed()
        StarWarsPeopleActivity.dadosPersonagem.clear()
    }


}
