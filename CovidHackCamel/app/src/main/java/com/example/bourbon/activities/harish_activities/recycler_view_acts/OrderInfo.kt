package com.example.bourbon.activities.harish_activities.recycler_view_acts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bourbon.R
import com.example.bourbon.activities.harish_activities.adapters.OrderRVAdapter
import com.example.bourbon.activities.harish_activities.adapters.ShopRecyclerViewAdapter
import com.example.bourbon.activities.harish_activities.model.Order
import com.example.bourbon.activities.harish_activities.model.Shop
import com.example.bourbon.databinding.RvActivityOrderInfoBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import print.Print

class OrderInfo : AppCompatActivity() {

    private var binding:RvActivityOrderInfoBinding?=null
    private var p: Print=Print(this)
    private var products:ArrayList<Order>?=ArrayList()

    private val adapter = OrderRVAdapter(products)
    private val mDatabase = FirebaseDatabase.getInstance().reference
    private val auth = FirebaseAuth.getInstance()
    private val user = auth.currentUser

    fun init(){
        binding=DataBindingUtil.setContentView(this,R.layout.rv_activity_order_info)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        configRecyclerView()
        fetchProductsFromFirebase()
    }

    fun configRecyclerView() {
        binding?.recyclerView?.setHasFixedSize(true)
        binding?.recyclerView?.setLayoutManager(LinearLayoutManager(this))

        binding?.recyclerView?.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        binding?.recyclerView?.setAdapter(adapter)
    }

    fun fetchProductsFromFirebase(){
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                // ...
                var i=0
                for (postSnapshot in dataSnapshot.child("Carts").children) {
                    val purchasedItems:ArrayList<String>?=null
                    // TODO: handle the post
                    if(postSnapshot.child("shopId").value == user?.uid){


                        val custId:String = postSnapshot.child("customerId").value.toString()
                        val dop:String = postSnapshot.key.toString()

                        val manuel:String = postSnapshot.child("manuel").value.toString()
                        val custName:String = dataSnapshot.child("users").child(custId).child("Name").value.toString()

                        if(manuel == "False"){
                            for(items in postSnapshot.child(dop).child("Items").children){
                                purchasedItems?.add(items.value.toString())
                            }
                            products?.add(Order(custName,custId,"7",dop,purchasedItems,manuel))
                            adapter.notifyItemInserted(i)
                            i++
                        }else{
                            purchasedItems?.add("NULL")
                            products?.add(Order(custName,custId,"o007",
                                dop,purchasedItems,manuel))
                            adapter.notifyItemInserted(i)
                            i++
                        }
                    }
                }
                if(i==0){
                    val toast = Toast.makeText(applicationContext, "No Orders Placed Yet", Toast.LENGTH_SHORT)
                    toast.show()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                p.fprintf("Error: ${databaseError.message}")

                // ...
            }
        })

    }
}
