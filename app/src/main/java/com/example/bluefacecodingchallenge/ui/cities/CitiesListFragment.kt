package com.example.bluefacecodingchallenge.ui.cities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bluefacecodingchallenge.R
import com.example.bluefacecodingchallenge.database.CityEntity
import com.example.bluefacecodingchallenge.model.City
import com.example.bluefacecodingchallenge.ui.base.BaseCityFragment
import kotlinx.android.synthetic.main.fragment_cities_list.*
import org.w3c.dom.Text

class CitiesListFragment : BaseCityFragment() {

    lateinit var adapter: CitiesListAdapter
    lateinit var filteredList: MutableList<CityEntity>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cities_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        addCityButton.setOnClickListener {
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_citiesListFragment_to_newCityFragment)
        }

        addCity.setOnClickListener {
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_citiesListFragment_to_newCityFragment)
        }
        NavHostFragment.findNavController(this).graph.findNode(R.id.action_citiesListFragment_to_newCityFragment)
        setupRecyclerView()
        initSearch()
    }

    private fun setupRecyclerView() {
        adapter = CitiesListAdapter()
        citiesList.layoutManager = LinearLayoutManager(context)
        citiesList.adapter = adapter
        attachListeners()
    }

    private fun attachListeners() {
        mainViewModel.cities.observe(viewLifecycleOwner, Observer { handleCitiesObserver(it) })
    }

    private fun handleCitiesObserver(cities: List<CityEntity>?) {
        progressBar.visibility = View.GONE

        if (cities.isNullOrEmpty()) {
            emptyState.visibility = View.VISIBLE
            citiesList.visibility = View.GONE
            searchCity.visibility = View.GONE
            addCity.visibility = View.GONE
            return
        }

        emptyState.visibility = View.GONE
        citiesList.visibility = View.VISIBLE
        searchCity.visibility = View.VISIBLE
        addCity.visibility = View.VISIBLE
        adapter.list = cities as MutableList<CityEntity>

    }

    private fun initSearch() {

        searchCity.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(
                charSequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                setupRecyclerView()
                filteredList = ArrayList()
                if (charSequence.toString() != "") {
                    for (item in adapter.list) {
                        if (item.cityname?.contains(charSequence.toString(), true)!!) {
                            (filteredList as ArrayList<CityEntity>).add(item)
                        }
                    }   
                    adapter.list = filteredList
                } else {
                    setupRecyclerView()
                }
            }
        })

    }
}