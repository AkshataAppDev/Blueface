package com.example.bluefacecodingchallenge.ui.base

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.bluefacecodingchallenge.ui.activity.MainViewModel
import com.example.bluefacecodingchallenge.di.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseCityFragment: DaggerFragment()
{
    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    lateinit var mainViewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel = activity?.run {
            ViewModelProvider(requireActivity(), providerFactory).get(MainViewModel::class.java)
        }?: throw Exception("Invalid activity")

    }
}