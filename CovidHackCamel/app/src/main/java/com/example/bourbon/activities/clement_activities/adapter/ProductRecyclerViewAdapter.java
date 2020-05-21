package com.example.bourbon.activities.clement_activities.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bourbon.R;
import com.example.bourbon.activities.clement_activities.model.ProductDetails;
import com.example.bourbon.databinding.CardHarishEmergencyContactNumBinding;

import java.util.ArrayList;

import print.Print;

public class ProductRecyclerViewAdapter extends RecyclerView.Adapter<ProductRecyclerViewAdapter.MyViewHolder> {


        private ArrayList<ProductDetails> productDetails;
        private Print p;
        Context context ;

        public ProductRecyclerViewAdapter(ArrayList<ProductDetails> productDetails){
            this.productDetails = productDetails;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            CardHarishEmergencyContactNumBinding binding= DataBindingUtil.inflate(
                    LayoutInflater.from(parent.getContext()), R.layout.card_harish_emergency_contact_num,
                    parent,false
            );

            p=new Print(parent.getContext());
            context = parent.getContext();

            return new MyViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.binding.setPd(productDetails.get(position));
//            p.sprintf("DistrictName: "+productDetails.get(position).getDistrictName());
        }

        @Override
        public int getItemCount() {
            return productDetails.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder{

            CardHarishEmergencyContactNumBinding binding;

            @SuppressLint("MissingPermission")
            private MyViewHolder(@NonNull CardHarishEmergencyContactNumBinding itemView) {
                super(itemView.getRoot());
                binding=itemView;

                binding.emergencyCard.setOnClickListener((v)->{
//                    p.sprintf("Card Clicked");
                    //on card click
                    //clement fill this fn
                });

                binding.emergencyNo.setOnClickListener((v)->{
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + binding.emergencyNo.getText()));
                    context.startActivity(intent);
                });

                binding.emerLandLineNo.setOnClickListener((v)->{
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + binding.emerLandLineNo.getText()));
                    context.startActivity(intent);
                });
            }


        }



    }
