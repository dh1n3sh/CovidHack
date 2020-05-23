package com.example.bourbon.activities.harish_activities.model

import android.content.Context
import android.content.Intent
import com.example.bourbon.R
import com.example.bourbon.activities.arumugam_activities.MapsActivity
import com.example.bourbon.activities.arumugam_activities.MapsActivityGeofencing
import com.example.bourbon.activities.arumugam_activities.MapsActivityShelterMapping
import com.example.bourbon.activities.arumugam_activities.MapsActivityLabs
import com.example.bourbon.activities.clement_activities.*
import com.example.bourbon.activities.harish_activities.PersonLocAct
import com.example.bourbon.activities.harish_activities.recycler_view_acts.CovidStatusInfo
import com.example.bourbon.activities.harish_activities.recycler_view_acts.CustomerOrderInfo
import com.example.bourbon.activities.harish_activities.recycler_view_acts.InfectedPeopleInfo
import com.google.firebase.auth.FirebaseAuth
import frame_transition.Transition

import print.Print

class ActivityNames(private val transition: Transition, val p:Print,val ctx:Context) {

    val arr= arrayOf(MapsActivity::class.java,
    Upi_payments::class.java,
    CovidStatusInfo::class.java,
    MapsActivityGeofencing::class.java,

    YoutubeList::class.java,
    EmergencyContactInfo::class.java,
            Store_Menu::class.java,
            CustomerOrderInfo::class.java,

            Volunteer_Registration::class.java,
            Donation::class.java,
            InfectedPeopleInfo::class.java,
            PersonLocAct::class.java,

            MapsActivityShelterMapping::class.java,
            MapsActivityLabs::class.java,
            E_Pass::class.java,
            Startact::class.java)

    val namesArr=arrayOf(
            "Hospital",
            "PM Fund",
            "Stats",
            "Red Zone",

            "Courses",
            "Toll Free",
            "Stores",
            "My Orders",

            "Volunteer",
            "Donation",
            "Infected Loc",
            "My Loc",

            "Shelter",
            "Labs",
            "E-Pass",
            "Log-Out"
    )

    val drawableArr= intArrayOf(
            R.drawable.hospital,
            R.drawable.loan,
            R.drawable.microscope,
            R.drawable.map,

            R.drawable.course,
            R.drawable.call,
            R.drawable.store,
            R.drawable.checkout,

            R.drawable.support,
            R.drawable.donation,
            R.drawable.infloc,
            R.drawable.myloc,

            R.drawable.store,
            R.drawable.microscope,
            R.drawable.pass,
            R.drawable.logout
    )



    fun nextActivity(cname:Class<*>){
        transition.goTo(cname)
    }

    fun nextActivity2(cname:Class<*>){
        FirebaseAuth.getInstance().signOut()
        val intent = Intent(ctx, cname)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        ctx.startActivity(intent)

    }

    fun call(option:Int=0,cname: Class<*>){
        when(option){
            0->nextActivity(cname)

            1->nextActivity2(cname)
        }
    }

}

data class DashboardCards(
        val name:String="",
        val pic: Int =R.drawable.red_ball,
        val cname: Class<*>,
        val click:Int=0
)