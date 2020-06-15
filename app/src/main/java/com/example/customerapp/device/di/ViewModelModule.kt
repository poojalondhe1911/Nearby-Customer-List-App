package com.example.customerapp.device.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.customerapp.presentation.view.viewmodel.CustomerListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CustomerListViewModel::class)
    internal abstract fun postListViewModel(viewModel: CustomerListViewModel): ViewModel
}