package com.example.customerapp.data.model

import com.google.gson.annotations.SerializedName

class Customer {

    @SerializedName("user_id")
    private var id = 0

    @SerializedName("name")
    private val name: String = ""

    @SerializedName("latitude")
    private val latitude: String = ""

    @SerializedName("longitude")
    private val longitude: String = ""


    fun getId(): Int {
        return id
    }

    fun getName(): String? {
        return name
    }

    fun getLatitude(): String?{
        return latitude
    }
    fun getLongitude(): String?{
        return longitude
    }


}