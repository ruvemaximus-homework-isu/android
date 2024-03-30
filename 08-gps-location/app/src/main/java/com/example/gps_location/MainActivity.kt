package com.example.gps_location

import android.content.Context
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.gps_location.databinding.ActivityMainBinding

data class LocationInfo (
    val location: Location,
    val provider: String
)

class MainActivity : AppCompatActivity(), LocationListener {
    companion object {
        private const val LOCATION_PERM_CODE = 2
        private const val TAG = "LocationTag"
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.refreshPermissionButton.setOnClickListener {
            checkLocationPermission()
        }

        val locationInfo = getLocationInfo() ?: return

        onProviderEnabled(locationInfo.provider)
    }

    private fun getLocationInfo(): LocationInfo? {
        checkLocationPermission()

        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5f, this)
        val provider = locationManager.getBestProvider(Criteria(), true) ?: return null

        Log.d(TAG, "Location Providers: ${locationManager.allProviders}")

        val location = locationManager.getLastKnownLocation(provider) ?: return null
        return LocationInfo(location, provider)
    }

    private fun checkLocationPermission() {
        if ((ContextCompat.checkSelfPermission(
                this, android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED)
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERM_CODE
            )
        }
    }

    override fun onLocationChanged(loc: Location) {
        Log.d(TAG, "Location has changed: lat ${loc.latitude} long ${loc.longitude}")
        displayLocation(loc)
    }

    private fun displayLocation(loc: Location?) {
        Log.d(TAG, "Actual location: ${loc?.latitude}, ${loc?.longitude}")
        if (loc === null) return

        binding.apply {
            lat.text = String.format("%.5f", loc.latitude)
            lng.text = String.format("%.5f", loc.longitude)
        }
    }

    override fun onProviderEnabled(provider: String) {
        val locationInfo = getLocationInfo()

        binding.status.text = "Online. Info from $provider"
        displayLocation(locationInfo?.location)

        Toast.makeText(this, "GPS enabled", Toast.LENGTH_SHORT).show()
        Log.d(TAG, "GPS Enabled by $provider")

        super.onProviderEnabled(provider)
    }

    override fun onProviderDisabled(provider: String) {
        binding.status.text = "Offline"
        Toast.makeText(this, "GPS disabled", Toast.LENGTH_SHORT).show()
        Log.d(TAG, "GPS Disabled by $provider")

        super.onProviderDisabled(provider)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        Log.d(TAG, "Permission Result: $requestCode, ${grantResults.asList()}")
        Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show()


        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}
