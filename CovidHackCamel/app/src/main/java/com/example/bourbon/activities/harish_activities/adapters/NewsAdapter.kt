package com.example.bourbon.activities.harish_activities.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.bourbon.R
import com.example.bourbon.activities.harish_activities.model.NewsClassModel
import com.example.bourbon.databinding.CardNewsBinding
import print.Print

class NewsAdapter(val products:ArrayList<NewsClassModel>): RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {
    var p: Print? = null
    class MyViewHolder(val binding: CardNewsBinding, context: Context) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: CardNewsBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.card_news,
                parent, false
        )
        p = Print(parent.context)
        p?.sprintf("Worked News")

        return MyViewHolder(binding, parent.context)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.pd = products[position]
    }
}