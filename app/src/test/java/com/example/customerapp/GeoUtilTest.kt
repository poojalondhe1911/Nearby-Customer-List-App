package com.example.customerapp

import com.example.customerapp.presentation.view.util.GeoUtil
import junit.framework.Assert.assertTrue
import org.junit.Test

class GeoUtilTest {

    @Test
    fun testDistance(){
        val distance = GeoUtil()
            .distance(53.339428,52.986375,-6.257664,-6.043701)
        assertTrue("Distance is less than 100", distance.compareTo(100) < 0)

        val distanceGreater = GeoUtil()
            .distance(53.339428,52.833502,-6.257664,-8.522366)
        assertTrue("Distance is greater than 100", distanceGreater.compareTo(100) > 0)

    }
}