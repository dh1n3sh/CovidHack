package com.example.bourbon.activities.harish_activities.adapters

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.bourbon.R
import com.example.bourbon.activities.harish_activities.model.ActivityNames
import com.example.bourbon.activities.harish_activities.model.DashboardCards
import com.example.bourbon.databinding.CardDashboardBinding
import frame_transition.Transition
import print.Print

class DashboardAdapter(var products:ArrayList<DashboardCards>): RecyclerView.Adapter<DashboardAdapter.MyViewHolder>() {
    var p: Print? = null
    var ctx:Context?=null
    var custom_font: Typeface?=null
    class MyViewHolder(val binding: CardDashboardBinding, context: Context) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: CardDashboardBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.card_dashboard,
                parent, false
        )
        p = Print(parent.context)
        ctx=parent.context
        custom_font = Typeface.createFromAsset(parent.context.assets, "fonts/exotc350bdbtbold.ttf")
        return MyViewHolder(binding, parent.context)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val card=products[position]
        holder.binding.pd = card
        holder.binding.cardImage.setImageResource(card.pic)

        holder.binding.dashboardCard.setOnClickListener{
            ActivityNames(Transition(ctx!!),Print(ctx!!), ctx!!).
            call(card.click,card.cname)
        }
        holder.binding.cardTitle.text=card.name
        holder.binding.cardTitle.setTypeface(custom_font)



    }
}