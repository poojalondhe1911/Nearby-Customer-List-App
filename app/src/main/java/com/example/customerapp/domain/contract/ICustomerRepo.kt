package com.example.customerapp.domain.contract

import androidx.lifecycle.MutableLiveData
import com.example.customerapp.domain.model.CustomerDomainModel

interface ICustomerRepo {
    fun getCustomerList(): MutableLiveData<List<CustomerDomainModel>>
}