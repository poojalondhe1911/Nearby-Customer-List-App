package com.example.customerapp.device.di

import android.content.Context
import com.example.customerapp.data.datasource.DataRepository
import com.example.customerapp.data.datasource.RemoteDataRepository
import com.example.customerapp.data.util.ReadFileClass
import com.example.customerapp.domain.contract.ICustomerRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRepo(repo: DataRepository): ICustomerRepo {
        return repo
    }

    @Provides
    @Singleton
    fun provideClientApiCliente(context: Context): ReadFileClass {
        return ReadFileClass(context)
    }

    @Singleton
    @Provides
    fun provideLocalRepository(readFile:ReadFileClass): RemoteDataRepository {
        return RemoteDataRepository(readFile)
    }


}