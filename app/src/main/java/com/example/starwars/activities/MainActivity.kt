package com.example.starwars.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.starwars.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonAbout.setOnClickListener {
            val intent = Intent(this, FilmesActivity::class.java )
            startActivity(intent)
        }

        buttonStarWarsPeople.setOnClickListener {
            val intent = Intent(this, StarWarsPeopleActivity::class.java )
            startActivity(intent)
        }
    }
}
