package com.example.bourbon.activities.harish_activities.recycler_view_acts

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bourbon.R
import com.example.bourbon.activities.harish_activities.adapters.ShopRecyclerViewAdapter
import com.example.bourbon.activities.harish_activities.model.Shop
import com.example.bourbon.databinding.RvActivityShopInfoBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import print.Print
import java.util.*

class ShopInfo : AppCompatActivity() {

    private var binding:RvActivityShopInfoBinding?=null
    private var p: Print?=null
    private var products: ArrayList<Shop>?=null
    private var adapter:ShopRecyclerViewAdapter?=null
    private var mDatabase:DatabaseReference?=null
    private var auth:FirebaseAuth?=null
    private var user:FirebaseUser?=null
    private var pincode:String?=null
    private var uid:String?=null

     fun init(){
        binding =DataBindingUtil.setContentView(this,R.layout.rv_activity_shop_info)
        p=Print(this)
        products=ArrayList()
    }

    fun configRecyclerView() {
        binding?.recyclerView?.setHasFixedSize(true)
        binding?.recyclerView?.setLayoutManager(LinearLayoutManager(this))
        adapter =ShopRecyclerViewAdapter(products)
        binding?.recyclerView?.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        binding?.recyclerView?.setAdapter(adapter)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        configRecyclerView()
        auth = FirebaseAuth.getInstance()
        user = auth?.currentUser
        uid = auth?.uid
        mDatabase = FirebaseDatabase.getInstance().reference
        fetchProductsFromFirebase()
    }

    fun fetchProductsFromFirebase(){







        mDatabase?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                pincode = dataSnapshot.child("users").child(uid.toString()).child("Pincode").value.toString()
                // ...
                var i:Int=0
                for (postSnapshot in dataSnapshot.child("Stores").children) {
                    // TODO: handle the post
                    if(postSnapshot.child("Pincode").value.toString() == pincode && (postSnapshot.key != uid)){
                        var name:String = postSnapshot.child("Name").value.toString()
                        var type:String = postSnapshot.child("Type").value.toString()
                        var address:String = postSnapshot.child("Address").value.toString()
//                        val toast = Toast.makeText(applicationContext, postSnapshot.key.toString(), Toast.LENGTH_LONG)
//                        toast.show()
                        var shopId:String = postSnapshot.key.toString()
                        products?.add(Shop(name,address,type,shopId))
                        adapter?.notifyItemInserted(i)
                        i++
                    }
                }
                if(i==0){
                    val toast = Toast.makeText(applicationContext, "No Shops Nearby", Toast.LENGTH_SHORT)
                    toast.show()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                val toast = Toast.makeText(applicationContext, databaseError.message, Toast.LENGTH_LONG)
                        toast.show()

                // ...
            }
        })


//        for(i in 1..5){
//            products?.add(Shop("chennai $i","addr$i","stationary $i"))
//            adapter?.notifyItemInserted(i)
//        }


    }
}
