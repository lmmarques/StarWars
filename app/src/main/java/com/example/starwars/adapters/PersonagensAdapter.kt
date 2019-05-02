package com.example.starwars.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.starwars.R
import com.example.starwars.models.ListaPersonagens
import com.example.starwars.models.Personagem
import java.util.*

class PersonagensAdapter(context: Context, results: ArrayList<Personagem>) : BaseAdapter() {
    private var searchArrayList: List<Personagem>? = results

    private var mInflater: LayoutInflater? = null


    init {
        mInflater = LayoutInflater.from(context)
    }


    override fun getCount(): Int {
        return searchArrayList!!.size
    }

    override fun getItem(position: Int): Any {
        return searchArrayList?.get(position)!!
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val holder: ViewHolder

        if (view == null) {
            view = mInflater?.inflate(R.layout.row_layout_personagens, null)
            holder = ViewHolder()
            holder.nomePersonagem = view!!.findViewById(R.id.nomePersonagem)
            holder.especie = view.findViewById(R.id.especiePersonagem)
            holder.numeroVeiculos = view.findViewById(R.id.numeroVeiculos)

            view.tag = holder
        } else {
            holder = view.tag as ViewHolder
        }

        holder.nomePersonagem?.text = searchArrayList?.get(position)?.name
        holder.especie?.text = searchArrayList?.get(position)?.speciesURL
        holder.numeroVeiculos?.text = searchArrayList?.get(position)?.vehiclesSize.toString()

        return view
    }

    internal class ViewHolder {
        var nomePersonagem: TextView? = null
        var especie: TextView? = null
        var numeroVeiculos: TextView? = null

    }
}