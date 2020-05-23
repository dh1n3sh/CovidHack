package com.example.bourbon.activities.harish_activities.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.bourbon.R
import com.example.bourbon.activities.clement_activities.Order_Details
import com.example.bourbon.activities.harish_activities.model.Order
import com.example.bourbon.activities.harish_activities.model.PersonLocModel
import com.example.bourbon.databinding.CardInfectedPeopleBinding
import com.example.bourbon.databinding.CardOrderBinding
import print.Print

class InfectedPeopleAdapter(var products:ArrayList<PersonLocModel>): RecyclerView.Adapter<InfectedPeopleAdapter.MyViewHolder>() {
    var p: Print? = null
    class MyViewHolder(itemView: CardInfectedPeopleBinding, context: Context) : RecyclerView.ViewHolder(itemView.root) {
        var binding: CardInfectedPeopleBinding? = null

        init {
            binding = itemView

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: CardInfectedPeopleBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.card_infected_people,
                parent, false
        )
        p = Print(parent.context)

        return MyViewHolder(binding, parent.context)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding?.pd = products[position]

    }
}