package com.compfest.mediscanapp.ui.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.compfest.mediscanapp.R
import com.compfest.mediscanapp.databinding.FragmentDashboardBinding
import com.compfest.mediscanapp.ui.maps.MapsActivity
import com.compfest.mediscanapp.ui.resultdrug.ResultDrugActivity
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.auth.FirebaseAuth
import java.util.*


class DashboardFragment : Fragment() {
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var mAuth : FirebaseAuth
    private lateinit var mapsActivity: MapsActivity
    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private val permissionId = 2
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val view: View = binding.root
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
        binding.buttonMaps.setOnClickListener {
            val intent = Intent(activity, MapsActivity::class.java)
            startActivity(intent)
        }
//        binding.button.setOnClickListener {
//            val intent = Intent(activity, ResultDrugActivity::class.java)
//            startActivity(intent)
//        }
        getLocation()
        mAuth = FirebaseAuth.getInstance()
        imageSlide()
        displayUser()
        return view
    }

    private fun displayUser() {
        val currentUser = mAuth.currentUser

        binding.tvName.text = currentUser?.displayName

    }
    @SuppressLint("MissingPermission", "SetTextI18n")
    private fun getLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                mFusedLocationClient.lastLocation.addOnCompleteListener(requireActivity()) { task ->
                    val location: Location? = task.result
                    if (location != null) {
                        val geocoder = Geocoder(requireContext(), Locale.getDefault())
                        val list: List<Address> =
                            geocoder.getFromLocation(location.latitude, location.longitude, 1) as List<Address>
                        binding.apply {
                            myLocation.text = list[0].locality
                        }
                    }
                }
            } else {
                Toast.makeText(activity, "Please turn on location", Toast.LENGTH_LONG).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            requestPermissions()
        }
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager = requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }
    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }
    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            permissionId
        )
    }
    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == permissionId) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                getLocation()
            }
        }
    }
    private fun imageSlide() {
        val view: View = binding.root
        val pictSlider = view.findViewById<ImageSlider>(R.id.pict_carousel)
        val pictList = ArrayList<SlideModel>()
        pictList.add(SlideModel("https://firebasestorage.googleapis.com/v0/b/mediscan-app.appspot.com/o/carousel1.png?alt=media&token=37790ab2-50e8-4db9-800e-350a62df2881"))
        pictList.add(SlideModel("https://firebasestorage.googleapis.com/v0/b/mediscan-app.appspot.com/o/carousel2.png?alt=media&token=04984ff8-65ae-41ce-a8ff-31cdb666c72e"))
        pictList.add(SlideModel("https://firebasestorage.googleapis.com/v0/b/mediscan-app.appspot.com/o/carousel3.png?alt=media&token=81546a2a-6f5b-44d6-b68b-16ec26379d3e"))

        pictSlider.setImageList(pictList, ScaleTypes.FIT)
    }
}