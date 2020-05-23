package com.example.bourbon.activities.harish_activities.recycler_view_acts

import android.os.Bundle
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bourbon.R
import com.example.bourbon.activities.harish_activities.adapters.CustomerOrderRvAdapter
import com.example.bourbon.activities.harish_activities.model.CustomerOrder
import com.example.bourbon.databinding.RvCustomerOrderInfoBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator
import print.Print

class CustomerOrderInfo : AppCompatActivity() {

    private var binding: RvCustomerOrderInfoBinding?=null
    private val p=Print(this)
    private var products= arrayListOf<CustomerOrder>()

    private val adapter=CustomerOrderRvAdapter(products)
    private var mDatabase: DatabaseReference?=null
    private var auth: FirebaseAuth?=null
    private var user: FirebaseUser?=null

    fun init(){
        binding= DataBindingUtil.setContentView(this, R.layout.rv_customer_order_info)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        configRecyclerView()
        mDatabase = FirebaseDatabase.getInstance().reference
        auth  =FirebaseAuth.getInstance()
        user = auth?.currentUser


        fetchProductsFromFirebase()

    }

    fun configRecyclerView() {
        binding?.recyclerView?.setHasFixedSize(true)
        binding?.recyclerView?.setLayoutManager(LinearLayoutManager(this))

        binding?.recyclerView?.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        binding?.recyclerView?.adapter = ScaleInAnimationAdapter(adapter)
        binding?.recyclerView?.itemAnimator= SlideInUpAnimator(OvershootInterpolator(1f))
    }

    fun fetchProductsFromFirebase() {
        //fill this
        mDatabase?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                // ...
                var i=0
                for (postSnapshot in dataSnapshot.child("Carts").children) {
                    var purchasedItems:ArrayList<String>?=null
                    // TODO: handle the post
                    if(postSnapshot.child("customerId").value == user?.uid){


                        var custId:String = postSnapshot.child("customerId").value.toString()
                        var dop:String = postSnapshot.key.toString()
                        var shopId:String = postSnapshot.child("shopId").value.toString()
                        var manuel:String = postSnapshot.child("manuel").value.toString()
                        var custName:String = dataSnapshot.child("users").child(custId).child("Name").value.toString()
                        var shopName:String = dataSnapshot.child("Stores").child(shopId).child("Name").value.toString()
                        if(manuel == "False"){
                            for(items in postSnapshot.child(dop).child("Items").children){
                                purchasedItems?.add(items.value.toString())
                            }
                            products.add(CustomerOrder(shopName,custId,dop,dop,purchasedItems,manuel))
                            adapter.notifyItemInserted(i)
                            i++
                        }else{
                            purchasedItems?.add("NULL")
                            products.add(CustomerOrder(custName,custId,"o007",
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
                val toast = Toast.makeText(applicationContext, databaseError.message, Toast.LENGTH_LONG)
                toast.show()

                // ...
            }
        })
    }
}