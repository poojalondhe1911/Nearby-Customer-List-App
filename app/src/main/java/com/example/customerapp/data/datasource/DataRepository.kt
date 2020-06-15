package com.example.customerapp.data.datasource

import androidx.lifecycle.MutableLiveData
import com.example.customerapp.domain.contract.ICustomerRepo
import com.example.customerapp.domain.model.CustomerDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DataRepository @Inject constructor(private val remoteDataRepository: RemoteDataRepository) : ICustomerRepo{


    override fun  getCustomerList(): MutableLiveData<List<CustomerDomainModel>> {
        val customerData : MutableLiveData<List<CustomerDomainModel>> = MutableLiveData()
        val customerModelDataList : MutableList<CustomerDomainModel> = mutableListOf()
        GlobalScope.launch {
            val customerListInfo = remoteDataRepository.getCustomerData()
            customerListInfo.forEach {
                val customerDomainModel = CustomerDomainModel(it.getId(),
                it.getName()!!,it.getLatitude()!!,it.getLongitude()!!)
                customerModelDataList.add(customerDomainModel)
            }

            withContext(Dispatchers.Main) {  customerData.value = customerModelDataList }
        }

        return customerData
    }
}