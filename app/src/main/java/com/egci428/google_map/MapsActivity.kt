package com.egci428.google_map

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import android.graphics.BitmapFactory



class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val bkk = LatLng(13.7934, 100.3225)
        var prevLatLng: LatLng = bkk
        var move = 0

        mMap.addMarker(MarkerOptions().position(bkk).title("Marker in Mahidol Salaya").icon(BitmapDescriptorFactory.fromResource(R.drawable.mu_icon)))

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(bkk,8f))

//        mMap.addMarker(MarkerOptions().position(LatLng(15.5231,100.4))
//                .title("Marker in A")
//                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)))
//
//        mMap.addMarker(MarkerOptions().position(LatLng(14.5,100.6))
//                .title("Marker in B")
//                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)))
//
//        mMap.addMarker(MarkerOptions().position(LatLng(13.1,100.3))
//                .title("Marker in C")
//                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)))

//        val line = mMap.addPolyline(PolylineOptions()
//                .add(LatLng(14.5,100.6)
//                        , LatLng(15.5231,100.4)
//                        ,LatLng(13.1,100.3))
//
//                .width(5f)
//                .color(Color.BLUE))

        mMap.setOnMapClickListener {
            latLng -> mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng))

        }
        
        mMap.setOnMapLongClickListener {
            latLng ->
            var icon = BitmapDescriptorFactory.fromResource(R.drawable.csgo_icon)
            if(move%2 == 0){
                icon = BitmapDescriptorFactory.fromResource(R.drawable.keyicon)
            }
            mMap.addMarker(MarkerOptions().position(latLng).title(latLng.toString()).icon(icon))
            move++
            mMap.addPolyline(PolylineOptions().add(prevLatLng,latLng))
            prevLatLng = latLng
        }

    }
}
