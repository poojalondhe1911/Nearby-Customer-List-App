package com.example.customerapp.device.di

import com.example.customerapp.domain.contract.ICustomerRepo
import com.example.customerapp.domain.usecase.GetCustomerListUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Singleton
    @Provides
    fun provideUseCase(repositoryContract: ICustomerRepo): GetCustomerListUseCase {
        return GetCustomerListUseCase(repositoryContract)
    }
}