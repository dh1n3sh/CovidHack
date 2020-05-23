package com.example.bourbon.activities.harish_activities.recycler_view_acts

import android.os.Bundle
import android.view.animation.OvershootInterpolator
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.bourbon.R
import com.example.bourbon.activities.harish_activities.adapters.InfectedPeopleAdapter
import com.example.bourbon.activities.harish_activities.model.PersonLocModel
import com.example.bourbon.databinding.RvInfectedPeopleInfoBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator
import print.Print

class InfectedPeopleInfo : AppCompatActivity() {

    private var binding: RvInfectedPeopleInfoBinding?=null
    private val p= Print(this)
    private var products= arrayListOf<PersonLocModel>()

    private val adapter=InfectedPeopleAdapter(products)
    private val database= FirebaseDatabase.getInstance()
    private var auth: FirebaseAuth?=null


    fun configRecyclerView() {
        binding?.recyclerView?.setHasFixedSize(true)
        binding?.recyclerView?.setLayoutManager(StaggeredGridLayoutManager(2,LinearLayout.VERTICAL))

        //binding?.recyclerView?.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        binding?.recyclerView?.adapter = ScaleInAnimationAdapter(adapter).apply{
            setFirstOnly(false)
            setDuration(1000)
            setHasStableIds(false)
            setInterpolator(OvershootInterpolator(.100f))
        }
        binding?.recyclerView?.itemAnimator= SlideInUpAnimator(OvershootInterpolator(1f))
    }
    fun init(){
        binding= DataBindingUtil.setContentView(this, R.layout.rv_infected_people_info)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()

        configRecyclerView()
        fetchProductsFromFirebase()

        binding!!.searchBar.setOnQueryTextListener(object :androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchForThisString(query!!)
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                searchForThisString(query!!)
                return false
            }

        })
    }

    fun fetchProductsFromFirebase(){
        database.getReference("Infected")
                .addValueEventListener(object:ValueEventListener{
                    override fun onCancelled(p0: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                    override fun onDataChange(usersList: DataSnapshot) {

                        products.clear()
                        adapter.notifyDataSetChanged()
                        if(usersList.exists()){
                            var i=0
                            for(obj in usersList.children){

                                for(innerObj in obj.children)
                                {
                                    val personLoc=innerObj?.getValue(PersonLocModel::class.java) ?: PersonLocModel("err","err")
                                    //p.sprintf("dateAndPERSON : ${personLoc.dateAndTime}")
                                    products.add(personLoc)
                                    println("Person Addr: ${personLoc.addr}")
                                    adapter.notifyItemInserted(i)
                                    i+=1
                                }

                            }
                        }

                    }

                })
    }

    fun searchForThisString(str:String){
        database.getReference("Infected")
                .addValueEventListener(object :ValueEventListener{
                    override fun onCancelled(p0: DatabaseError) {
                        p.fprintf("Error in fetching Users:\n ${p0.message}")
                    }

                    override fun onDataChange(usersList: DataSnapshot) {
                        products.clear()
                        adapter.notifyDataSetChanged()
                        if(usersList.exists()) {
                            var i = 0
                            for (obj in usersList.children) {

                                for (innerObj in obj.children) {
                                    println("""
                                        innerObj: $innerObj
                                    """.trimIndent())
                                    val personLoc = innerObj?.getValue(PersonLocModel::class.java)
                                            ?: PersonLocModel("err", "err")
                                    //p.sprintf("dateAndPERSON : ${personLoc.dateAndTime}")
                                    if(personLoc.addr.toLowerCase().contains(str) ){

                                        products.add(personLoc)

                                        println("This is search and found Addr: ${personLoc.addr}")
                                        adapter.notifyItemInserted(i)
                                        i += 1
                                    }
                                    else if(personLoc.dateAndTime.contains(str)) {
                                        products.add(personLoc)

                                        println("This is search and found Addr: ${personLoc.addr}")
                                        adapter.notifyItemInserted(i)
                                        i += 1
                                    }
                                }

                            }
                        }
                    }

                })
    }
}
