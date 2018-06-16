/*
 * Copyright (c) 2018 Smart Points Ltd. - All rights reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Created by danie on 09/01/18 15:13
 */

package com.amplify.utils.location

import android.Manifest
import android.arch.lifecycle.LiveData
import android.content.Context
import android.content.pm.PackageManager
import android.os.Looper
import android.support.annotation.NonNull
import android.support.v4.app.ActivityCompat
import com.google.android.gms.location.*


class LocationLiveData(private val context: Context) : LiveData<CommonLocation>() {

    private var fusedLocationProviderClient: FusedLocationProviderClient? = null

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            val newLocation = locationResult.lastLocation
            val latitude = newLocation.latitude
            val longitude = newLocation.longitude
            val accuracy = newLocation.accuracy
            val location = CommonLocation(latitude, longitude, accuracy)
            location.country = AndroidGeocoder.getCountryCode(context, location)

            value = location
        }
    }

    override fun onActive() {
        super.onActive()
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return
        }
        val locationProviderClient = getFusedLocationProviderClient()
        val locationRequest = LocationRequest.create()
        val looper = Looper.myLooper()
        locationProviderClient?.requestLocationUpdates(locationRequest, locationCallback, looper)
    }

    @NonNull
    private fun getFusedLocationProviderClient(): FusedLocationProviderClient? {
        if (fusedLocationProviderClient == null) {
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
        }
        return fusedLocationProviderClient
    }

    override fun onInactive() {
        fusedLocationProviderClient?.removeLocationUpdates(locationCallback)
    }
}