package com.example.bourbon.activities.harish_activities.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.bourbon.R
import com.example.bourbon.activities.clement_activities.Order_Details
import com.example.bourbon.activities.clement_activities.Order_Food
import com.example.bourbon.activities.harish_activities.model.Order
import com.example.bourbon.databinding.CardOrderBinding

import print.Print

class OrderRVAdapter(var products:ArrayList<Order>?):RecyclerView.Adapter<OrderRVAdapter.MyViewHolder>() {
    var p:Print?=null


    class MyViewHolder(itemView:CardOrderBinding,context:Context):RecyclerView.ViewHolder(itemView.root) {
        var binding:CardOrderBinding?=null

        init {
            binding=itemView
            binding?.ordercard?.setOnClickListener{
                val intent = Intent(context, Order_Details::class.java)
                intent.putExtra("MyClass", binding?.pd);
                context.startActivity(intent)

            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding:CardOrderBinding= DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.card_order,
                parent, false
        )
        p = Print(parent.context)

        return MyViewHolder(binding,parent.context)
    }

    override fun getItemCount(): Int {
        return products!!.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding?.pd= products!!.get(position)

    }
}