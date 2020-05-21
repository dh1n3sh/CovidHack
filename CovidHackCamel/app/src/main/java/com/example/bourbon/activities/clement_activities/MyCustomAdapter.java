package com.example.bourbon.activities.clement_activities;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bourbon.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MyCustomAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        return this.videodetailsArrayList.size();
    }
    Activity activity ;
    ArrayList<videodetails> videodetailsArrayList;
    LayoutInflater inflater ;
    public MyCustomAdapter(Activity activity, ArrayList<videodetails> videodetails){
    this.activity = activity ;
    this.videodetailsArrayList = videodetails ;
    }
    @Override
    public Object getItem(int position) {
        return this.videodetailsArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return (long)position ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null){
            inflater = this.activity.getLayoutInflater();
        }

        if(convertView == null){
            convertView = inflater.inflate((R.layout.custom_item),null);
        }

        ImageView imageView = convertView.findViewById(R.id.imageView);
        TextView textView =convertView.findViewById(R.id.mytitle);
        LinearLayout linearLayout = convertView.findViewById(R.id.root);


        videodetails videodetails = this.videodetailsArrayList.get(position);
        Picasso.get().load(videodetails.getUrl()).into(imageView);

        textView.setText(videodetails.getTitle());
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity,YoutubePlay.class);
                i.putExtra("videoid",videodetails.getVideoId());
                activity.startActivity(i);
            }
        });
        return convertView;
    }
}
