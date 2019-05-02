package com.example.starwars.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.starwars.R
import com.example.starwars.adapters.PersonagensAdapter
import com.example.starwars.models.Personagem
import com.example.starwars.services.RestService
import com.example.starwars.utils.dynamicHeigthListView
import com.example.starwars.utils.loading
import kotlinx.android.synthetic.main.activity_star_wars_people.*
import java.net.MalformedURLException
import java.net.URL
import java.util.*
import java.util.concurrent.ExecutionException

class StarWarsPeopleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_star_wars_people)

         getPersonagensService()
        //loading().showLoading(this,::getPersonagensService)
        buttonBackPersonagens.setOnClickListener {
            super.onBackPressed()
        }


    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    //obtem os dados de todas as personagens
    private fun getPersonagensService() {

        val baseURL = "https://swapi.co/api/"
        val endpoint = "people"
        val urlService = baseURL + endpoint


        val task = RestService.AsyncCallWS()
        try {
            task.execute(URL(urlService)).get()
            val searchResults = getPersonagens()
            listaPersonagens.adapter = PersonagensAdapter(this, searchResults)
            dynamicHeigthListView().setListViewHeightBasedOnChildren(listaPersonagens, this)
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
                personagens.speciesURL ="Espécie: "+ value[1] as String

                personagens.vehiclesSize = "Nr. Veículos: " + value[2] as String

                results.add(personagens)
            }


        } catch (e: Exception) {
            e.printStackTrace()
        }

        return results
    }
}
