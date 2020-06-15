package com.example.customerapp.device.di

import com.example.customerapp.presentation.view.ui.customerlist.CustomerListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    internal abstract fun contributeVideoListActivity(): CustomerListActivity
}