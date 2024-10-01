package net.azarquiel.openweather.adapter

import net.azarquiel.openweather.R


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import net.azarquiel.openweather.model.Dia

class CustomAdapter(val context: Context,
                    val layout: Int
                    ) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private var dataList: List<Dia> = emptyList()

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

    internal fun setResultados(resultados: List<Dia>) {
        this.dataList = resultados
        notifyDataSetChanged()
    }


    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Dia){
            // itemview es el item de diseño
            // al que hay que poner los datos del objeto dataItem
            val ivDia = itemView.findViewById(R.id.ivDia) as ImageView
            val tvDt_txt = itemView.findViewById(R.id.tvDt_txt) as TextView
            val tvTemp_min = itemView.findViewById(R.id.tvTemp_min) as TextView
            val tvTemp_max = itemView.findViewById(R.id.tvTemp_max) as TextView
            val tvDescription = itemView.findViewById(R.id.tvDescription) as TextView

            //....

            tvDt_txt.text = dataItem.dt_txt
            tvTemp_min.text = dataItem.temperatura.temp_min.toString()
            tvTemp_max.text = dataItem.temperatura.temp_max.toString()
            tvDescription.text = dataItem.pronostico[0].description

            //....

            //val id = context.resources.getIdentifier(dataItem.pronostico[0].icon,"drawable",context.packageName)
            //ivDia.setImageResource(id)
            // foto de internet a traves de Picasso
            Picasso.get().load("http://openweathermap.org/img/wn/${dataItem.pronostico[0].icon}@2x.png").into(ivDia)

            itemView.tag = dataItem

        }

    }
}