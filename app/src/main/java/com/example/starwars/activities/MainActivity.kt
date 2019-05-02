package com.example.starwars.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.starwars.R
import com.example.starwars.services.RestService
import com.example.starwars.utils.loading
import kotlinx.android.synthetic.main.activity_main.*
import java.net.MalformedURLException
import java.net.URL
import java.util.concurrent.ExecutionException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonAbout.setOnClickListener {
            val intent = Intent(this, FilmesActivity::class.java )
            startActivity(intent)
        }

        buttonStarWarsPeople.setOnClickListener {
            //loading().showLoading(this,::getPersonagensService)
            val intent = Intent(this, StarWarsPeopleActivity::class.java )
            startActivity(intent)
        }
    }
    //obtem os dados de todas as personagens
    private fun getPersonagensService() {

        val baseURL = "https://swapi.co/api/"
        val endpoint = "people"
        val urlService = baseURL + endpoint


        val task = RestService.AsyncCallWS()
        try {
            task.execute(URL(urlService)).get()
            val intent = Intent(this, StarWarsPeopleActivity::class.java )
            startActivity(intent)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }
    }
}
