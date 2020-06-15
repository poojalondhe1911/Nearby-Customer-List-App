package com.example.customerapp.presentation.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.customerapp.domain.contract.ICustomerRepo
import com.example.customerapp.domain.usecase.GetCustomerListUseCase
import com.example.customerapp.presentation.view.model.CustomerUIModel
import com.example.customerapp.presentation.view.util.GeoUtil
import javax.inject.Inject


class CustomerListViewModel @Inject constructor(
    private val getCustomerListUseCase: GetCustomerListUseCase)
    : ViewModel() {

    var liveData: MutableLiveData<List<CustomerUIModel>> = MutableLiveData()
    private var lat: Double = 53.339428
    private var long: Double = -6.257664


    @Inject
    lateinit var customerRepo: ICustomerRepo

    private val customerOriginalList: MutableList<CustomerUIModel> = mutableListOf()

    fun getCustomerData(distance: Int): MutableLiveData<List<CustomerUIModel>> {

        val customerChangedList :  MutableList<CustomerUIModel> = mutableListOf()
        liveData =  Transformations.map(getCustomerListUseCase.getCustomerData()) { customer ->
            customerOriginalList.clear()
            customer.forEach {
                if(GeoUtil().distance(
                    lat,
                    it.latitude.toDouble(),
                    long,
                    it.longitude.toDouble()
                ) <= distance) {
                    val customerUIInfo = CustomerUIModel(it.id,it.name,
                        it.latitude.toDouble(),it.longitude.toDouble())
                    customerChangedList.add(customerUIInfo)
                }
                val customerUIInfoAllRecords = CustomerUIModel(it.id,it.name,
                    it.latitude.toDouble(),it.longitude.toDouble())
                customerOriginalList.add(customerUIInfoAllRecords)
            }
            customerChangedList.sortedBy { it.id }
        } as MutableLiveData

        return liveData
    }
    fun getDistanceValues(): MutableList<Int> {
        return mutableListOf(100,150,200,250,300)
    }

    fun setCustomerUpdatedFilterInfo(distance: Int, lat: String, long: String) {

        if(lat.isNotBlank() && long.isNotBlank()) {
            liveData.value = customerOriginalList.filter {
                GeoUtil().distance(
                    lat.toDouble(),
                    it.latitude,
                    long.toDouble(),
                    it.longitude
                ) <= distance
            }.sortedBy { it.id }
        }
    }

    fun getDefaultLat(): Double {
        return lat
    }

    fun getDefaultLong(): Double {
        return long
    }
}
