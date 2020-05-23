package com.example.bourbon.activities.harish_activities

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Typeface
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.animation.OvershootInterpolator
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.bourbon.R
import com.example.bourbon.activities.harish_activities.adapters.DashboardAdapter
import com.example.bourbon.activities.harish_activities.adapters.NewsAdapter
import com.example.bourbon.activities.harish_activities.model.ActivityNames
import com.example.bourbon.activities.harish_activities.model.DashboardCards
import com.example.bourbon.activities.harish_activities.model.NewsClassModel
import com.example.bourbon.databinding.ActivityMainBinding
import com.google.android.gms.location.*
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import frame_transition.Transition
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator
import print.Print
import java.io.IOException
import java.util.*


class Dashboard : Activity() {

    private var binding:ActivityMainBinding?=null
    private val transition = Transition(this)
    private val p = Print(this)
    private val mAuth = FirebaseAuth.getInstance()
    private val database= FirebaseDatabase.getInstance()

    private var products= arrayListOf<DashboardCards>()
    private val adapter= DashboardAdapter(products)

    private val newsProducts=arrayListOf<NewsClassModel>()
    private val newsAdapter= NewsAdapter(newsProducts)

    private val uid=mAuth.currentUser?.uid.toString() ?: "404"
    private var sharedPreferences:SharedPreferences?=null
    private var locationManager: LocationManager?=null
    private var geocoder:Geocoder?=null

    private val locationRequest=LocationRequest().apply {
        interval = 1800000
        fastestInterval = 1750000
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }
    private var fusedLocationProviderClient:FusedLocationProviderClient?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        callPermissions()
        binding=DataBindingUtil.setContentView(this, R.layout.activity_main)

        configFeaturesRecyclerView()
        //configNewsRecyclerView()
        fetchProducts()

        //val newsApiHelper=NewsApiHelper(this,newsProducts,newsAdapter)
        //newsApiHelper.fetchNews()

        fetchNews()


        binding?.pd= ActivityNames(transition,p,this)
        sharedPreferences= getSharedPreferences("default", Context.MODE_PRIVATE)
        locationManager= (getSystemService(Context.LOCATION_SERVICE) as LocationManager)
        geocoder=Geocoder(this)
        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(this)

        fetchLocation()


        database.getReference("users")
                .child(mAuth.uid ?: "987654321qwert")
                .addListenerForSingleValueEvent(object:ValueEventListener{
                    override fun onCancelled(p0: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                    override fun onDataChange(user: DataSnapshot) {
                        println("User = ${user}\nName: ${user.child("Name").value}")
                        binding!!.textView.text="Welcome ${user.child("Name").getValue().toString()}"
                        val custom_font: Typeface = Typeface.createFromAsset(assets, "fonts/exotc350bdbtbold.ttf")
                        binding!!.textView.setTypeface(custom_font)

                    }

                })


    }

    private fun fetchProducts() {
        val act=ActivityNames(Transition(this), Print(this),this)
        for(i in 0..13){

            val c= DashboardCards(act.namesArr[i],act.drawableArr[i],act.arr[i],0)

            products.add(c)
            adapter.notifyItemInserted(i)
        }

        products.add(DashboardCards(act.namesArr[13],act.drawableArr[13],act.arr[13],1))
        adapter.notifyItemInserted(13)
    }

    private fun configFeaturesRecyclerView() {
        binding?.recyclerView?.setHasFixedSize(true)
        binding?.recyclerView?.setLayoutManager(GridLayoutManager(this,2))

        binding?.recyclerView?.adapter = ScaleInAnimationAdapter(adapter).apply{
            setFirstOnly(false)
            setDuration(1000)
            setHasStableIds(false)
            setInterpolator(OvershootInterpolator(.100f))
        }

        binding?.recyclerView?.itemAnimator= SlideInUpAnimator(OvershootInterpolator(1f))
    }
//    private fun configNewsRecyclerView() {
//        binding?.newsRecyclerView?.setHasFixedSize(true)
//        val lm=LinearLayoutManager(this)
//        lm.orientation=RecyclerView.HORIZONTAL
//        binding?.newsRecyclerView?.setLayoutManager(lm)
//        binding?.newsRecyclerView?.adapter = ScaleInAnimationAdapter(newsAdapter).apply{
//            setFirstOnly(false)
//            setDuration(1000)
//            setHasStableIds(false)
//            setInterpolator(OvershootInterpolator(.100f))
//        }
//        binding?.newsRecyclerView?.itemAnimator= SlideInUpAnimator(OvershootInterpolator(1f))
//    }

    fun fetchNews(){
        val requestQueue= Volley.newRequestQueue(this)
        val apiKey="ab1e7cf4b1534ec0a0f4f36589e81f18"

        val url = "http://newsapi.org/v2/top-headlines?country=in&apiKey=$apiKey"


        println("Reached fetchNews")
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
                Response.Listener { response ->
                    println("""
                        Response: $response
                    """.trimIndent())

                    val articles=response.getJSONArray("articles")

                    for(i in 0..11){
                        val article=articles.getJSONObject(i)
                        val item=NewsClassModel(article.getString("title"),
                                article.getString("description"),
                                article.getString("author"))

                        newsProducts.add(item)
                        newsAdapter.notifyItemInserted(i)
                        println("Adding Items: $item")
                    }


                },
                Response.ErrorListener { e ->
                    // TODO: Handle error
                    println("""
                        Error:  ${e.message}
                    """.trimIndent())
                }
        )

        requestQueue.add(jsonObjectRequest)


    }

    private fun fetchLocation() {
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
        ==PackageManager.PERMISSION_GRANTED) {

            //p.sprintf("Line 1")
            fusedLocationProviderClient!!.requestLocationUpdates(locationRequest,
                    object:LocationCallback(){
                        override fun onLocationResult(locationResult: LocationResult?) {
                            //p.sprintf("Line 2")
                            locationResult ?:return

                            locationResult.locations.forEach { location ->

                                try{
                                    val addr2 = geocoder!!.getFromLocation(location.latitude, location.longitude, 5)?.get(0)
                                            ?: Address(Locale("Tamil"))

                                    println("""
                                            Update
                                            Lat: ${location.latitude}
                                            Long: ${location.longitude} 
                                            Addr: ${addr2.getAddressLine(0)}
                                        """.trimIndent())
                                }
                                catch (e:Exception){
                                    //p.fprintf("Error: ${e.message}")
                                    println("Error: ${e.message}")
                                }
                            }
                        }

                    }
                    ,Looper.getMainLooper())
        }
        else{
            callPermissions()
        }
    }

    private fun sharedCode(address: String?) {
        val editor: SharedPreferences.Editor = sharedPreferences!!.edit()
        editor.putString(Calendar.getInstance().time.toString(), address)
        editor.apply()
        //getShared()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1000) {
            if (grantResults.isNotEmpty()) {
                if (grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                        getLastLocation()
                        fetchLocation()
                    }
                }
                else if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                        getLastLocation()
                        fetchLocation()
                    }
                }
            }
        }
    }

    private fun callPermissions(){
        requestPermissions(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION),1000)
    }

    private fun getHomeLocation() {
        try {
            val address = Geocoder(this).getFromLocationName("47/18 Krishnapuram Street" +
                    "Choolaimedu Chennai", 5)[0]
            //            SharedCode(address.toString());
            p.sprintf("Home Lat: " + address.latitude + " Long: " + address.longitude)
            val addr2 = Geocoder(this).getFromLocation(13.060661, 80.228231, 5)[0]
            //            SharedCode(addr2.toString());
            p.sprintf("""
                Your Loc Name: ${addr2.getAddressLine(0)}
                Locality${addr2.locality}
                """.trimIndent())
        } catch (e: java.lang.Exception) {
            p.fprintf("""
                Failed to get Home Lat Long
                Error: ${e.message}
                """.trimIndent())
            e.printStackTrace()
        }
    }
    private fun getLastLocation() {
        fusedLocationProviderClient!!.getLastLocation().addOnSuccessListener(OnSuccessListener { location: Location? ->
            if (location != null) {
                val geocoder = Geocoder(this)
                try {
                    val addr2 = geocoder.getFromLocation(location.latitude, location.longitude, 5)[0]
                    //add this below loc info to shared pref
                    sharedCode(addr2.getAddressLine(0).toString())
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                Log.d("Fused Loc ", """
                     Lat: ${location.latitude}
                     Long: ${location.longitude}
                     """.trimIndent())
            }
        }).addOnFailureListener { e: Exception ->
            p.fprintf("""
                        Failed to retrieve Locaiton
                        Error: ${e.message}
                        """.trimIndent())
        }
    }



}