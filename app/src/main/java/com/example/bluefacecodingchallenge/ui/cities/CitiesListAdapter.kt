package com.example.bluefacecodingchallenge.ui.cities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bluefacecodingchallenge.R
import com.example.bluefacecodingchallenge.database.CityEntity
import kotlinx.android.synthetic.main.city_list_item.view.*

class CitiesListAdapter : RecyclerView.Adapter<CitiesListAdapter.CityViewHolder>() {

    var list = mutableListOf<CityEntity>()
        set(value: MutableList<CityEntity>) {
            list.clear()
            list.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.city_list_item, parent, false)
        return CityViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  list.size
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(data: CityEntity) {
            itemView.city.text = data.cityname
            itemView.rank.text = "${data.cityrank}"
            itemView.latLng.text = "Lat : ${data.citylat} Lon: ${data.citylon}"
        }
    }

}