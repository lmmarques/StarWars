package com.example.starwars.models

import com.google.gson.annotations.SerializedName

class Personagem {

    @SerializedName("films")
    var films: Array<String>? = null

    @SerializedName("homeworld")
    var homeworld: String? = null

    @SerializedName("gender")
    var gender: String? = null

    @SerializedName("skin_color")
    var skin_color: String? = null

    @SerializedName("edited")
    var edited: String? = null

    @SerializedName("created")
    var created: String? = null

    @SerializedName("mass")
    var mass: String? = null

    @SerializedName("vehicles")
    var vehicles: Array<String>? = null


    var vehiclesSize: Int? = null

    @SerializedName("url")
    var url: String? = null

    @SerializedName("hair_color")
    var hair_color: String? = null

    @SerializedName("birth_year")
    var birth_year: String? = null

    @SerializedName("eye_color")
    var eye_color: String? = null

    @SerializedName("species")
    var species: Array<String>? = null

    var speciesURL: String? = null

    @SerializedName("starships")
    var starships: Array<String>? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("height")
    var height: String? = null

    companion object {
        var dadosPersonagem: MutableMap<String, Any> = LinkedHashMap<String, Any>()


    }

    constructor(name: String?, species: String?, vehicles: Int?) {
        this.vehiclesSize = vehicles
        this.speciesURL = species
        this.name = name
        dadosPersonagem[name!!] = arrayOf(name, species, vehiclesSize)
    }

    constructor()

    override fun toString(): String {
        return "ClassPojo [films = $films, homeworld = $homeworld, gender = $gender, skin_color = $skin_color, edited = $edited, created = $created, mass = $mass, vehicles = $vehicles, url = $url, hair_color = $hair_color, birth_year = $birth_year, eye_color = $eye_color, species = $species, starships = $starships, name = $name, height = $height]"
    }

}



