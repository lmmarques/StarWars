package com.example.starwars.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import com.example.starwars.R
import com.example.starwars.adapters.PersonagensAdapter
import com.example.starwars.models.Personagem
import com.example.starwars.services.RestService
import com.example.starwars.utils.InternetConnection
import com.example.starwars.utils.dynamicHeigthListView
import com.example.starwars.utils.loading
import kotlinx.android.synthetic.main.activity_star_wars_people.*
import kotlinx.android.synthetic.main.row_dados_personagens.*
import kotlinx.android.synthetic.main.row_layout_personagens.view.*
import java.net.MalformedURLException
import java.net.URL
import java.util.concurrent.ExecutionException

class StarWarsPeopleActivity : AppCompatActivity() {
    companion object {
        var dadosPersonagem = ArrayList<String>()
        var nomePersonagem :String?=null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_star_wars_people)


        //verifica a conexão à internet
        if (InternetConnection().isConnected(this))
            loading().showLoading(this, ::getPersonagensService)
        else
            InternetConnection().buildDialog(
                this,
                { loading().showLoading(this, ::getPersonagensService) },
                { super.onBackPressed() })


        listaPersonagens.setOnItemClickListener() { adapterView: AdapterView<*>, view1: View, i: Int, l: Long ->
            try {
                 nomePersonagem = view1.nomePersonagem.text.toString()

                for (entry in Personagem.dadosPersonagem.entries) {
                    val value = entry.value as Array<*>
                    //verifica se tem o mesmo nome
                    if (value[0] == nomePersonagem) {
                        dadosPersonagem.add("Nome: " + value[0] as String)

                        dadosPersonagem.add("Género: " + value[3].toString().capitalize())
                        dadosPersonagem.add("Planeta Natal: " + value[4] as String)
                        dadosPersonagem.add("Cor de pele: " + value[5].toString().capitalize())


                        //verifica se tem veiculos
                        val lista = value[2] as ArrayList<*>
                        if (lista.isNotEmpty()) {
                            for (s in lista) {
                                dadosPersonagem.add("Veiculo: $s")
                            }
                        }
                    }
                }
                val intent = Intent(this, DadosPersonagens::class.java)
                startActivity(intent)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }


        buttonBackPersonagens.setOnClickListener {
            super.onBackPressed()
        }


    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    //Adiciona à lista as personagens e os dados das mesmas
    private fun addPersonagensToList() {
        val searchResults = getPersonagens()
        listaPersonagens.adapter = PersonagensAdapter(this, searchResults)
        dynamicHeigthListView().setListViewHeightBasedOnChildren(listaPersonagens, this)
    }

    //obtem os dados de todas as personagens
    private fun getPersonagensService() {

        val baseURL = "https://swapi.co/api/"
        val endpoint = "people" //obtém todas as personagens
        val urlService = baseURL + endpoint


        val task = RestService.AsyncCallWS()
        try {
            task.execute(URL(urlService)).get()
            addPersonagensToList()

        } catch (e: InterruptedException) {
            e.printStackTrace()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }


    }


    //-----------------------------------------
    //
    // Obtem a lista de personagens
    //-------------------------------------------
    //
    private fun getPersonagens(): ArrayList<Personagem> {
        val results = ArrayList<Personagem>()

        try {
            for (entry in Personagem.dadosPersonagem.entries) {
                val value = entry.value as Array<*>

                val personagens = Personagem()

                personagens.name = value[0] as String
                personagens.speciesURL = "Espécie: " + value[1] as String
                val lista = value[2] as ArrayList<*>
                personagens.vehiclesSize = "Nr. Veículos: " + lista.size

                results.add(personagens)
            }


        } catch (e: Exception) {
            e.printStackTrace()
        }

        return results
    }
}
