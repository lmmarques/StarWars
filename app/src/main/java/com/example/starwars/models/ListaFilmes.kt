package com.example.starwars.models

import com.google.gson.annotations.SerializedName

class ListaFilmes {
    @SerializedName("films")
    var edited: String? = null

    @SerializedName("films")
    var director: String? = null

    @SerializedName("films")
    var created: String? = null

    @SerializedName("films")
    var vehicles: Array<String>? = null

    @SerializedName("films")
    var opening_crawl: String? = null

    @SerializedName("films")
    var title: String? = null

    @SerializedName("films")
    var url: String? = null

    @SerializedName("films")
    var characters: Array<String>? = null

    @SerializedName("films")
    var episode_id: String? = null

    @SerializedName("films")
    var planets: Array<String>? = null

    @SerializedName("films")
    var release_date: String? = null

    @SerializedName("films")
    var starships: Array<String>? = null

    @SerializedName("films")
    var species: Array<String>? = null

    @SerializedName("films")
    var producer: String? = null

    override fun toString(): String {
        return "ClassPojo [edited = $edited, director = $director, created = $created, vehicles = $vehicles, opening_crawl = $opening_crawl, title = $title, url = $url, characters = $characters, episode_id = $episode_id, planets = $planets, release_date = $release_date, starships = $starships, species = $species, producer = $producer]"
    }

}


