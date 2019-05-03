package com.example.starwars.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.starwars.R
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        buttonBackAbout.setOnClickListener {
            super.onBackPressed()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}
