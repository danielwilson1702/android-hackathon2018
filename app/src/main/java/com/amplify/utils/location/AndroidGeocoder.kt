/*
 * Copyright (c) 2017 Smart Points Ltd. - All rights reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Created by danie on 21/12/17 11:09
 */

package com.amplify.utils.location

import android.content.Context
import android.location.Geocoder
import android.util.Log
import java.io.IOException
import java.util.*

object AndroidGeocoder {

    fun getCountryCode(context: Context, location: CommonLocation): String {
        val gcd = Geocoder(context, Locale.getDefault())

        try {
            val addresses = gcd.getFromLocation(location.latitude, location.longitude, 1)
            if (addresses.size > 0) {
                return addresses[0].countryCode
            }
        }
        catch (e: IOException){
            Log.d("geocode", "Geocoder IOException")
        }

        Log.d("geocode", "No country code retrieved from this lat-long")
        return "0"
    }
}