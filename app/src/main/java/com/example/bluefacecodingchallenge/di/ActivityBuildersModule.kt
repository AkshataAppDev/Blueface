package com.example.bluefacecodingchallenge.di

import com.example.bluefacecodingchallenge.di.main.MainFragmentBuildersModule
import com.example.bluefacecodingchallenge.di.main.MainModule
import com.example.bluefacecodingchallenge.di.main.MainScope
import com.example.bluefacecodingchallenge.di.main.MainViewModelModule
import com.example.bluefacecodingchallenge.ui.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @MainScope
    @ContributesAndroidInjector(
        modules = [MainModule::class, MainFragmentBuildersModule::class, MainViewModelModule::class]
    )
    abstract fun contributeMainActivity(): MainActivity
}