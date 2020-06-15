package com.example.customerapp.domain.usecase

import androidx.lifecycle.MutableLiveData
import com.example.customerapp.domain.contract.ICustomerRepo
import com.example.customerapp.domain.model.CustomerDomainModel
import javax.inject.Inject

class GetCustomerListUseCase @Inject constructor(private val repositoryContract: ICustomerRepo) {

    fun getCustomerData(): MutableLiveData<List<CustomerDomainModel>> {
        return repositoryContract.getCustomerList()
    }

}