package net.azarquiel.chistesapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import net.azarquiel.chistesapp.R
import net.azarquiel.chistesapp.model.Categoria

/**
 * Created by pacopulido on 9/10/18.
 */
class CategoriaAdapter(val context: Context,
                    val layout: Int
                    ) : RecyclerView.Adapter<CategoriaAdapter.ViewHolder>() {

    private var dataList: List<Categoria> = emptyList()

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

    internal fun setCategorias(categorias: List<Categoria>) {
        this.dataList = categorias
        notifyDataSetChanged()
    }


    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Categoria){
            // itemview es el item de diseño
            // al que hay que poner los datos del objeto dataItem
            val ivcategoriarow = itemView.findViewById(R.id.ivcategoriarow) as ImageView
            val tvcategoriarow = itemView.findViewById(R.id.tvcategoriarow) as TextView

            tvcategoriarow.text = dataItem.nombre


            // foto de internet a traves de Picasso
            Picasso.get().load("http://www.ies-azarquiel.es/paco/apichistes/img/${dataItem.id}.png").into(ivcategoriarow)

            itemView.tag = dataItem

        }

    }
}