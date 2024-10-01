package net.azarquiel.nbateams.adapter

import net.azarquiel.nbateams.model.Team


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import net.azarquiel.nbateams.R

class CustomAdapter(val context: Context,
                    val layout: Int
                    ) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private var dataList: List<Team> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewlayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewlayout, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    internal fun setTeams(teams: List<Team>) {
        this.dataList = teams
        notifyDataSetChanged()
    }


    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Team){
            // itemview es el item de diseño
            // al que hay que poner los datos del objeto dataItem
            val ivEscudo = itemView.findViewById(R.id.ivEscudo) as ImageView
            val tvTeams = itemView.findViewById(R.id.tvTeams) as TextView
            //....

            tvTeams.text = dataItem.name
            //....


            // foto de internet a traves de Picasso
            Picasso.get().load(dataItem.teamLogoUrl).into(ivEscudo)

            itemView.tag = dataItem

        }

    }
}