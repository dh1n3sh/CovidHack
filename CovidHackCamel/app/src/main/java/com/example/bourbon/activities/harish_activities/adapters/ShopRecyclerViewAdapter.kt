package com.example.bourbon.activities.harish_activities.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.bourbon.R
import com.example.bourbon.activities.clement_activities.Order_Food
import com.example.bourbon.activities.harish_activities.model.Shop
import com.example.bourbon.databinding.CardShopBinding
import print.Print

class ShopRecyclerViewAdapter(var shopList:ArrayList<Shop>?): RecyclerView.Adapter<ShopRecyclerViewAdapter.MyViewHolder>() {
    var p:Print?=null


    class MyViewHolder(itemView:CardShopBinding,context:Context):RecyclerView.ViewHolder(itemView.root) {
        var binding:CardShopBinding?=null

        init {
            binding=itemView

            binding?.shopHere?.setOnClickListener{
                val intent = Intent(context,Order_Food::class.java)
                intent.putExtra("Name",binding?.shopName?.text.toString())
                intent.putExtra("ShopId",binding?.shopId?.text.toString())
                context.startActivity(intent)
                //btn event
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val binding:CardShopBinding= DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.card_shop,
                parent, false
        )
        p=Print(parent.context)


        return MyViewHolder(binding,parent.context)
    }

    override fun getItemCount(): Int {
        return shopList!!.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding?.pd= shopList!!.get(position)


    }


}