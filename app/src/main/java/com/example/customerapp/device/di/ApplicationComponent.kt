package com.example.customerapp.device.di

import android.app.Application
import com.example.customerapp.presentation.view.ui.MainApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ViewModelModule::class,
    RepositoryModule::class,
    ActivityModule::class,
    ContextModule::class
])

interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(appController: MainApplication)
}

