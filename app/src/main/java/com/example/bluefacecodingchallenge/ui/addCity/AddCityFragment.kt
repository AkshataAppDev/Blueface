package com.example.bluefacecodingchallenge.ui.addCity

import android.os.Bundle
import android.view.*
import androidx.navigation.fragment.NavHostFragment
import com.example.bluefacecodingchallenge.R
import com.example.bluefacecodingchallenge.database.CityEntity
import com.example.bluefacecodingchallenge.ui.base.BaseCityFragment
import kotlinx.android.synthetic.main.fragment_add_city.*


class AddCityFragment : BaseCityFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_city, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        backButton.setOnClickListener{
            NavHostFragment.findNavController(this).popBackStack()
        }

        addCity.setOnClickListener{
            val city = city.text?.toString()
            val rank = cityRank.text?.toString()

            if(city.isNullOrEmpty() || rank.isNullOrEmpty())
                return@setOnClickListener

            val cityEntity = CityEntity()
            cityEntity.cityname = city
            cityEntity.cityrank = Integer.parseInt(rank)
            mainViewModel.insertNewCity(cityEntity)
            NavHostFragment.findNavController(this).popBackStack()
        }

    }

    companion object{
        const val TAG = "CityFragment"
    }
}