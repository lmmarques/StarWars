package com.example.starwars.services

import android.os.AsyncTask
import android.util.Log
import com.example.starwars.models.*
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class RestService {
    private var gson: Gson? = null
    private var responseListaPersonagens: ListaPersonagens? = null
    private var responsePersonagem: Personagem? = null
    private var responseEspecie: Especie? = null
    private var responsePlaneta: Planeta? = null
    private var responseVeiculo: Veiculo? = null



    private fun loadJSON(url: URL?) =
        try {

            gson = Gson()

            responseListaPersonagens = gson!!.fromJson<ListaPersonagens>(connection(url), ListaPersonagens::class.java)

            //otem todas as personagens
            for (i in 0 until responseListaPersonagens!!.results!!.size) {
                responsePersonagem = responseListaPersonagens!!.results!![i]
                val nome = responsePersonagem!!.name
                val genero = responsePersonagem!!.gender
                val corPele = responsePersonagem!!.skin_color
                val planetaNatal = responsePersonagem!!.homeworld
                //remove o ultimo caracter do URL ("/")
                val urlFinalPlaneta = URL(planetaNatal!!.substring(0, planetaNatal.length - 1))

                //chama o serviço de especie
                responsePlaneta = gson!!.fromJson<Planeta>(connection(urlFinalPlaneta), Planeta::class.java)
                val planeta = responsePlaneta!!.name
                Log.d("REST", "planeta: $planeta")

                //obtem o numero de veiculos

                val numeroVeiculos = responsePersonagem!!.vehicles!!.size
                var veiculos: String?

                val listaVeiculos = ArrayList<String>()
                for (j in 0 until numeroVeiculos) {
                    veiculos = responsePersonagem!!.vehicles!![j]
                    val urlFinalVeiculo = URL(veiculos.substring(0, veiculos.length - 1))

                    //chama o serviço de especie
                    responseVeiculo = gson!!.fromJson<Veiculo>(connection(urlFinalVeiculo), Veiculo::class.java)
                    val nomeVeiculo = responseVeiculo!!.name

                    listaVeiculos.add(nomeVeiculo!!)

                    Log.d("REST", "nomeVeiculo: $nomeVeiculo")
                    Log.d("REST", "listaVeiculos: $veiculos")
                }

                var speciesURL: String? = null
                var especie: String? = null

                //obtem as especies
                for (e in 0 until responsePersonagem!!.species!!.size) {
                    speciesURL = responsePersonagem!!.species!![e]

                    //remove o ultimo caracter do URL ("/")
                    val urlFinalSpecies = URL(speciesURL.substring(0, speciesURL.length - 1))

                    //chama o serviço de especie
                    responseEspecie = gson!!.fromJson<Especie>(connection(urlFinalSpecies), Especie::class.java)
                    especie = responseEspecie!!.name
                    Log.d("REST", "especie: $especie")
                }


                Log.d("REST", "nomePersonagem: $nome")
                Log.d("REST", "species: $speciesURL")
                Log.d("REST", "numeroVeiculos: $numeroVeiculos")

                Log.d("REST", "corPele: $corPele")
                Log.d("REST", "planetaNatal: $planetaNatal")
                Log.d("REST", "genero: $genero")

                //Adiciona ao objeto os dados
                Personagem(nome, especie, listaVeiculos,genero,planeta,corPele)
            }


        } catch (e: Exception) {
            e.printStackTrace()
        }


    //protocolo de ligação
   private fun connection(url: URL?): BufferedReader? {
        val connection: HttpURLConnection = url?.openConnection() as HttpURLConnection

        connection.requestMethod = "GET"
        connection.doOutput = true
        /* connection.connectTimeout = 30000
         connection.readTimeout = 30000*/
        connection.connect()
        var input: BufferedReader? = null
        val responseCode = connection.responseCode

        //verifica a conexão
        if (responseCode == 200) {
            input = BufferedReader(
                InputStreamReader(connection.inputStream)
            )

        }

        return input
    }


    class AsyncCallWS : AsyncTask<URL, Void, Void>() {


        override fun onPreExecute() {


            Log.i("AAA", "onPreExecute")

        }

        override fun doInBackground(vararg params: URL): Void? {
            Log.i("AAA", "doInBackground")

            RestService().loadJSON(params[0])


            return null
        }

        override fun onPostExecute(result: Void?) {

            Log.i("AAA", "onPostExecute")
        }
    }


}