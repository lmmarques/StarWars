package com.example.starwars.models

import com.google.gson.annotations.SerializedName
import java.util.*

class ListaPersonagens {
    @SerializedName("next")
    private val next: String? = null

    @SerializedName("count")
     val count: String? = null

    @SerializedName("results")
     val results: Array<Personagem>? = null



    override fun toString(): String {
        return "ListaPersonagens(next=$next, count=$count, results=${Arrays.toString(results)})"
    }


}
