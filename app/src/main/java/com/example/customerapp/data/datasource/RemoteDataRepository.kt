package com.example.customerapp.data.datasource

import com.example.customerapp.data.model.Customer
import com.example.customerapp.data.util.ReadFileClass
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject


class RemoteDataRepository @Inject constructor(private val readFileUtil : ReadFileClass){


    suspend fun getCustomerData(): MutableList<Customer> {
            val customer:MutableList<Customer> = mutableListOf()
            withContext(IO){
                readFileUtil.readFile().forEach {
                    if(it.isNotBlank()) customer.add(GsonBuilder().create().fromJson(it, Customer::class.java))
                }
            }
        return customer
    }
}