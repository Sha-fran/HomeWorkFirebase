package com.example.homeworkfirebase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.maps.android.PolyUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MapFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.map_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        val mapFragmentViewmodel:MapFragmentViewmodel = ViewModelProvider(this).get(MapFragmentViewmodel::class.java)

        mapFragment.getMapAsync {googleMap ->
            val coordinates = LatLng(49.842957, 24.031111)
            val coordinates_Ternopil = LatLng(49.553516,25.594767)
            googleMap.addMarker(MarkerOptions().position(coordinates))
            googleMap.addMarker(MarkerOptions().position(coordinates_Ternopil))
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 8F))

//            val client = Retrofit.Builder()
//                .baseUrl("https://maps.googleapis.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()

//            CoroutineScope(Dispatchers.IO).launch {
//                val result = client.create(ApiInterface::class.java).getSimpleRoute()
//                if (result.isSuccessful) {
//                    withContext(Dispatchers.Main) {
//                        result.body()?.let {
//                            val polylinePoints = it.routes[0].overviewPolyline.points
//                            val decodedPath = PolyUtil.decode(polylinePoints)
//                            googleMap.addPolyline(PolylineOptions().addAll(decodedPath))
//                        }
//                    }
//                }
//            }

            mapFragmentViewmodel.uiState.observe(viewLifecycleOwner) {uiState ->
                when(uiState) {
                    is UiState.EmptyRoute -> Unit
                    is UiState.FilledRoute -> {
                        val polylinePoints = uiState.route?.routes?.get(0)?.overviewPolyline?.points
                        val decodedPath = PolyUtil.decode(polylinePoints)
                        googleMap.addPolyline(PolylineOptions().addAll(decodedPath))
                    }
                }
            }
        }
    }
}
