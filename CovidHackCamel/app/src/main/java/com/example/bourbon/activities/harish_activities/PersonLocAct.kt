package com.example.bourbon.activities.harish_activities

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.bourbon.R

import com.example.bourbon.activities.harish_activities.model.PersonLocModel
import com.example.bourbon.databinding.ActivityPersonLocHarishBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import frame_transition.Transition
import print.Print
import java.util.*

class PersonLocAct : AppCompatActivity() {

    private val p= Print(this)
    private var binding:ActivityPersonLocHarishBinding?=null
    private val mAuth= FirebaseAuth.getInstance()
    private val transition= Transition(this)
    private val database= FirebaseDatabase.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_person_loc_harish)

        binding!!.share.setOnClickListener{
            if(binding!!.infectedBox.isChecked){
                if(binding!!.pushBox.isChecked){
                    readFromSharedPref()
                    p.sprintf("Your details will be shared now!")
                }
            }

        }
    }

    private fun shareToFirebase(obj:PersonLocModel){

        database.getReference("Infected")
                .child(mAuth.currentUser?.uid.toString())
                .child(UUID.randomUUID().toString())
                .setValue(obj)
                .addOnCompleteListener{
                    //p.sprintf("Published Successfully")
                    println("Published successfully")
                }
                .addOnFailureListener{e->
                    p.fprintf("Couldn't publish\nError: ${e.message}")
                    println("Error ${e.message}")
                }
    }

    private fun readFromSharedPref(){
        //clement fill this fn

        val sharedPreferences = getSharedPreferences("default",Context.MODE_PRIVATE)
        val keys: Map<String?, *> = sharedPreferences.all

        for ((key, value) in keys) {
//            Log.d("map values",entry.getKey() + ": " + entry.getValue().toString());
            val obj=PersonLocModel(key.toString(),value.toString())
            shareToFirebase(obj)
        }
        //shareToFirebase(PersonLocModel("1/4/2000","Error"))
    }
}
