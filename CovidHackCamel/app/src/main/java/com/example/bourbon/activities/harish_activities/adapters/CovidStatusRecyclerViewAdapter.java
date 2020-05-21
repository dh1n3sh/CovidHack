package com.example.bourbon.activities.harish_activities.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bourbon.R;
import com.example.bourbon.activities.harish_activities.GraphDaily;
import com.example.bourbon.activities.harish_activities.model.CovidStatus;
import com.example.bourbon.databinding.CovidStatusCardBinding;

import java.util.ArrayList;

import print.Print;

public class CovidStatusRecyclerViewAdapter extends RecyclerView.Adapter<CovidStatusRecyclerViewAdapter.MyViewHolder> {


        private ArrayList<CovidStatus> productDetails;
        private Print p;
        Context context ;

        public CovidStatusRecyclerViewAdapter(ArrayList<CovidStatus> productDetails){
            this.productDetails = productDetails;
        }


        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            CovidStatusCardBinding binding= DataBindingUtil.inflate(
                    LayoutInflater.from(parent.getContext()), R.layout.covid_status_card,
                    parent,false
            );

            p=new Print(parent.getContext());
            context = parent.getContext();

            return new MyViewHolder(binding,parent.getContext());
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

            CovidStatusCardBinding binding;

            @SuppressLint("MissingPermission")
            private MyViewHolder(@NonNull CovidStatusCardBinding itemView,Context context) {
                super(itemView.getRoot());

                binding=itemView;
                binding.statscard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, GraphDaily.class);
                        intent.putExtra("City Name", binding.getPd().getCityName());
                        context.startActivity(intent);
                    }
                });


            }


        }



    }
