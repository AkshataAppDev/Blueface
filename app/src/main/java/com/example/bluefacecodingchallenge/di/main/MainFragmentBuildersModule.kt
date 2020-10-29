package com.example.bluefacecodingchallenge.di.main;

import com.example.bluefacecodingchallenge.ui.addCity.AddCityFragment
import com.example.bluefacecodingchallenge.ui.cities.CitiesListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector()
    abstract fun contributeAddCityFragment(): AddCityFragment

    @ContributesAndroidInjector()
    abstract fun contributeCitiesListFragment(): CitiesListFragment

}