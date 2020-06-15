package com.example.customerapp.presentation.view.util

import kotlin.math.*

class GeoUtil {
    fun distance(
        lat1: Double,
        lat2: Double, lon1: Double,
        lon2: Double
    ): Double {

        // Haversine formula
        val differenceLon = Math.toRadians(lon2) - Math.toRadians(lon1)
        val differenceLat =  Math.toRadians(lat2) -  Math.toRadians(lat1)
        val calculatedValue1 = (sin(differenceLat / 2).pow(2.0)
                + (cos(lat1) * cos(lat2)
                * sin(differenceLon / 2).pow(2.0)))
        val calculatedValue2 = 2 * asin(sqrt(calculatedValue1))

        // Radius of earth in kilometers
        val radius = 6371.0

        // calculate the result
        return calculatedValue2 * radius
    }
}