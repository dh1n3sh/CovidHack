package com.example.bourbon.activities.harish_activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.bourbon.R
import com.example.bourbon.activities.harish_activities.helper_classes.PlotGraphHelper
import com.example.bourbon.databinding.ActivityGraphDailyBinding
import org.json.JSONArray
import org.json.JSONObject
import print.Print
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class GraphDaily : AppCompatActivity() {

    private var mQueue:RequestQueue?=null
    private var p: Print?=null
    private var binding:ActivityGraphDailyBinding?=null


    private var dateList:ArrayList<Date>?= ArrayList()
    private var activeList:ArrayList<Int>?= ArrayList()
    private var recoveredList:ArrayList<Int>?= ArrayList()
    private var deceasedList:ArrayList<Int>?= ArrayList()


    fun init(){
        mQueue=Volley.newRequestQueue(this)
        p=Print(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_graph_daily)
        init()


        parseGraphDailyJson(intent.extras?.getString("City Name").toString())

        configGraph()
    }

    private fun configGraph() {


    }

    private fun parseGraphDailyJson(district:String) {
        val url: String? = "https://api.covid19india.org/districts_daily.json"

        val job:JsonObjectRequest?=JsonObjectRequest(Request.Method.GET, url, null,
                Response.Listener { response: JSONObject? ->

                    val districtsDaily: JSONObject? = response?.getJSONObject("districtsDaily")
                    val keralaObj:JSONObject?=districtsDaily?.getJSONObject("Rajasthan")

                    val arr:JSONArray=keralaObj!!.getJSONArray(district)

                    var i=0

                    while(i<arr.length()){
                        val obj:JSONObject?=arr.getJSONObject(i)
                        //p?.sprintf("Active = ${obj?.getString("date")}")

                        //p?.sprintf("Date = ${obj?.getString("date")!!.substring(8,10)}")
                        //dateList!!.add(Integer.parseInt(obj?.getString("date")!!.substring(8,10)))
                        activeList!!.add(Integer.parseInt(obj!!.getString("active")))
                        recoveredList!!.add(Integer.parseInt(obj.getString("recovered")))
                        deceasedList!!.add(Integer.parseInt(obj.getString("deceased")))

                        val format1 = SimpleDateFormat("yyyy-MM-dd")
                        val format2 = SimpleDateFormat("dd-MM-yyyy")
                        val format3 = SimpleDateFormat("dd-MM-yyyy")
                        val date = format1.parse(obj.getString("date"))
                        dateList!!.add(format3.parse(format2.format(date)))
                        i+=1
                    }

                    val plot= PlotGraphHelper(binding, this)
                    plot.plotGraph(dateList,activeList,recoveredList,deceasedList)


                }, Response.ErrorListener { error: VolleyError? ->
                    p!!.fprintf("Reched error ${error!!.message}")
         })

        mQueue?.add(job)
    }


}
