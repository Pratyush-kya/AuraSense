package com.example.aurasense.utils

import android.Manifest
import android.app.Activity
import androidx.core.app.ActivityCompat

const val LOCATION_PERMISSION_REQUEST_CODE = 100

fun requestLocationPermissions(activity: Activity) {
    val permissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_BACKGROUND_LOCATION
    )
    ActivityCompat.requestPermissions(activity, permissions, LOCATION_PERMISSION_REQUEST_CODE)
}