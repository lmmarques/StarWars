package com.example.starwars.services

import android.os.AsyncTask
import android.util.Log
import com.example.starwars.models.Especie
import com.example.starwars.models.ListaPersonagens
import com.example.starwars.models.Personagem
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



    private fun loadJSON(url: URL?) {
        val connection: HttpURLConnection
        try {
          connection = url?.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.doOutput = true
           connection.connectTimeout = 30000
            connection.readTimeout = 30000
            connection.connect()
            var input :BufferedReader?=null
            val responseCode = connection.responseCode

            //verifica a conexão
            if (responseCode == 200) {
                input  = BufferedReader(
                    InputStreamReader(connection.inputStream)
                )

            }
            gson = Gson()


            responseListaPersonagens = gson!!.fromJson<ListaPersonagens>(input, ListaPersonagens::class.java)

            //otem todas as personagens
            for (i in 0 until responseListaPersonagens!!.results!!.size){
                responsePersonagem = responseListaPersonagens!!.results!![i]
                val nome = responsePersonagem!!.name

                //obtem as especies
                var species :String?=null
                for (e in 0 until responsePersonagem!!.species!!.size) {
                     species = responsePersonagem!!.species!![e]
                }
                //obtem o numero de veiculos
                val numeroVeiculos = responsePersonagem!!.vehicles


                Log.d("REST", "nomePersonagem: " + nome)
                Log.d("REST", "species: " + species)
                Log.d("REST", "numeroVeiculos: " + numeroVeiculos!!.size)

                Personagem(nome,species, numeroVeiculos.size)
            }

            responseEspecie = gson!!.fromJson<Especie>(input, Especie::class.java::class.java)
            val especie= responseEspecie!!.name
            //Personagem(nome,species, numeroVeiculos.size)


        } catch (e: Exception) {
            e.printStackTrace()
        }

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