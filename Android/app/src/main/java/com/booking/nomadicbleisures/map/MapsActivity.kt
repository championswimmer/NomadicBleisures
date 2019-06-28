package com.booking.nomadicbleisures.map

import android.graphics.Color
import android.graphics.Point
import android.location.Location
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.booking.nomadicbleisures.R
import com.booking.nomadicbleisures.models.MapSearchResponse
import com.booking.nomadicbleisures.network.ApiClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    val amsterdam = LatLng(52.3668101, 4.8986768)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val drawOverlay = findViewById<DrawOverlay>(R.id.draw_overlay)
        drawOverlay.setMapDrawComplete(object : MapDrawComplete {
            override fun onDrawComplete(coordinates: ArrayList<Point>, closedPolygon: Boolean, targetPoint: Point) {
                if (closedPolygon) {
                    coordinates.map {
                        mMap.projection.fromScreenLocation(it)
                    }.let { latLngs ->
                        // make api call here
                        val latLng = mMap.projection.fromScreenLocation(targetPoint)
                        val results = FloatArray(10)
                        Location.distanceBetween(
                            latLng.latitude,
                            latLng.longitude,
                            latLngs[0].latitude,
                            latLngs[0].longitude,
                            results
                        )
                        ApiClient.mapSearchApi.getMapSearchResults(
                            latLng.latitude,
                            latLng.longitude,
                            results[0]
                        ).enqueue(object : Callback<MapSearchResponse> {
                            override fun onFailure(call: Call<MapSearchResponse>, t: Throwable) {

                            }

                            override fun onResponse(
                                call: Call<MapSearchResponse>,
                                response: Response<MapSearchResponse>
                            ) {
                                response.body()?.let {
                                    mMap.addPolyline(
                                        PolylineOptions().addAll(latLngs).clickable(false).color(Color.RED).width(12f)
                                    )
                                    drawOverlay.visibility = View.GONE
                                    it.hotels?.let {
                                        it.forEach { hotel ->
                                            hotel.location?.let {
                                                mMap.addMarker(
                                                    MarkerOptions().position(
                                                        LatLng(
                                                            it.latitude,
                                                            it.longitude
                                                        )
                                                    ).title(hotel.name)
                                                )
                                            }
                                        }
                                    }
                                    it.coworkingPlaces?.let {
                                        it.forEach { coworking ->
                                            coworking.location?.let {
                                                mMap.addMarker(
                                                    MarkerOptions().position(
                                                        LatLng(
                                                            it.latitude,
                                                            it.longitude
                                                        )
                                                    ).title(coworking.name).icon(
                                                        BitmapDescriptorFactory.defaultMarker(
                                                            BitmapDescriptorFactory.HUE_BLUE
                                                        )
                                                    )
                                                )
                                            }
                                        }
                                    }
                                }
                            }

                        })
                    }
                }
            }
        })

        val searchButton = findViewById<View>(R.id.search_btn)
        searchButton.setOnClickListener {
            mMap.clear()
            drawOverlay.visibility = View.VISIBLE
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(amsterdam, 16f))
    }
}
