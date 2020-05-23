package com.example.bourbon.activities.clement_activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bourbon.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class YoutubeList extends AppCompatActivity {

    ListView listView;
    ArrayList<videodetails> videodetailsArrayList ;
    MyCustomAdapter myCustomAdapter;
    String url = "https://www.googleapis.com/youtube/v3/search?part=snippet&channelId=UClaQJq84XMtMkL44zDmL-Tg&maxResults=15&key=AIzaSyCgMfU9aS6h8qjAF8NxzBHhTS84palI7UY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_list);

        listView = findViewById(R.id.listview);

        videodetailsArrayList = new ArrayList<>();

        myCustomAdapter = new MyCustomAdapter(YoutubeList.this,videodetailsArrayList);

//        Toast.makeText(this, "Inside Youtube Play", Toast.LENGTH_SHORT).show();

        displayVideos();

    }

    private void displayVideos() {

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

//                Toast.makeText(YoutubeList.this, "GOt Response", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("items");

                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        JSONObject jsonvideoId = jsonObject1.getJSONObject("id");
                        JSONObject jsonobjectSnippet = jsonObject1.getJSONObject("snippet");

                        JSONObject jsonObjectDefault = jsonobjectSnippet.getJSONObject("thumbnails").getJSONObject("medium");

                        String video_id = jsonvideoId.getString("videoId");

                       videodetails vd = new videodetails();
                       vd.setVideoId(video_id);
                       vd.setTitle(jsonobjectSnippet.getString("title"));
                       vd.setDescription(jsonobjectSnippet.getString("description"));
                       vd.setUrl(jsonObjectDefault.getString("url"));
//                        Toast.makeText(YoutubeList.this,vd.getTitle(), Toast.LENGTH_SHORT).show();

                       videodetailsArrayList.add(vd);
                    }

                    listView.setAdapter((myCustomAdapter));
                    myCustomAdapter.notifyDataSetChanged();
                }catch (JSONException e){
//                    Toast.makeText(YoutubeList.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                    e.printStackTrace();
                    Log.d("CLEMENT",e.toString());
                }

                }
        },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(YoutubeList.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);
    }
}
