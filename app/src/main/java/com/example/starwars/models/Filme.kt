package com.example.starwars.models

class Filme {
    var edited: String? = null

    var director: String? = null

    var created: String? = null

    var vehicles: Array<String>? = null

    var opening_crawl: String? = null

    var title: String? = null

    var url: String? = null

    var characters: Array<String>? = null

    var episode_id: String? = null

    var planets: Array<String>? = null

    var release_date: String? = null

    var starships: Array<String>? = null

    var species: Array<String>? = null

    var producer: String? = null

    override fun toString(): String {
        return "ClassPojo [edited = $edited, director = $director, created = $created, vehicles = $vehicles, opening_crawl = $opening_crawl, title = $title, url = $url, characters = $characters, episode_id = $episode_id, planets = $planets, release_date = $release_date, starships = $starships, species = $species, producer = $producer]"
    }
}