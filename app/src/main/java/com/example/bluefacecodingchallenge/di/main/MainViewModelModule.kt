package com.example.bluefacecodingchallenge.di.main;

import androidx.lifecycle.ViewModel
import com.example.bluefacecodingchallenge.ui.activity.MainViewModel
import com.example.bluefacecodingchallenge.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel
}