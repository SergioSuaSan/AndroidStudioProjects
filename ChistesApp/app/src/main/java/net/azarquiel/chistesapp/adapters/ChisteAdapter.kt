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
import net.azarquiel.chistesapp.model.Chiste

/**
 * Created by pacopulido on 9/10/18.
 */
class ChisteAdapter(val context: Context,
                    val layout: Int
                    ) : RecyclerView.Adapter<ChisteAdapter.ViewHolder>() {

    private var dataList: List<Chiste> = emptyList()

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

    internal fun setChistes(chistes: List<Chiste>) {
        this.dataList = chistes
        notifyDataSetChanged()
    }


    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Chiste){
            // itemview es el item de diseño
            // al que hay que poner los datos del objeto dataItem
            val ivchisterow = itemView.findViewById(R.id.ivchisterow) as ImageView
            val tvchisterow = itemView.findViewById(R.id.tvchisterow) as TextView
            tvchisterow.text = dataItem.contenido
            if (dataItem.contenido.length > 20)
                tvchisterow.text = "${dataItem.contenido.substring(0,20)}..."

            // foto de internet a traves de Picasso
            Picasso.get().load("http://www.ies-azarquiel.es/paco/apichistes/img/${dataItem.idcategoria}.png").into(ivchisterow)

            itemView.tag = dataItem
        }

    }
}