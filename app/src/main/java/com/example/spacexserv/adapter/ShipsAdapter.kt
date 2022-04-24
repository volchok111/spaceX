package com .example.spacexserv.adapter

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.spacexserv.R
import com.example.spacexserv.model.ships.Ships
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.ArrayList

class ShipsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var shipsList = arrayListOf<Ships>()



    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: ArrayList<Ships>) {
        shipsList = list
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addShip(ships: Ships) {
        shipsList.add(ships)
        notifyDataSetChanged()
        Log.d("Artem", "Ship arrived in adapter. ${shipsList.size} size")
    }

    fun doSortByName(){
        Collections.sort(shipsList,Ships.sortByYear)
        notifyDataSetChanged()
    }

    class ShipViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val image = view.findViewById<ImageView>(R.id.itemIv)
        private val rocketName = view.findViewById<TextView>(R.id.rocketNameTv)
        private val description = view.findViewById<TextView>(R.id.descriptionTv)


        fun bind(ships: Ships) {
            Picasso.get().load(ships.image).into(image)
            rocketName.text = ships.id
            description.text = "${itemView.context.getString(R.string.type)}: ${ships.name}"
            itemView.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("id", ships.id)
                itemView.findNavController().navigate(
                    R.id.action_launchesFragment_to_shipFragment, bundle
                )
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ShipViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ShipViewHolder)
            holder.bind(shipsList[position])
    }

    override fun getItemCount(): Int {
        return shipsList.count()
    }

        }



