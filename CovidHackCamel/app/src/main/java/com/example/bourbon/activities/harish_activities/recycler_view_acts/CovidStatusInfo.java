package com.example.bourbon.activities.harish_activities.recycler_view_acts;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.OvershootInterpolator;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bourbon.R;
import com.example.bourbon.activities.harish_activities.adapters.CovidStatusRecyclerViewAdapter;
import com.example.bourbon.activities.harish_activities.model.CovidStatus;
import com.example.bourbon.databinding.RvActivityCovidStatusBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;
import print.Print;

public class CovidStatusInfo extends Activity {

    private RvActivityCovidStatusBinding binding;
    private ArrayList<CovidStatus> products =new ArrayList<>();
    private CovidStatusRecyclerViewAdapter adapter;
    private DatabaseReference mDatabase;
    private Print p;

    private static final String TAG = "CovidStatusInfo";

    void configRecyclerView() {
        binding.recyclerView.setHasFixedSize(true);
        //binding.recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, LinearLayout.VERTICAL));
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new CovidStatusRecyclerViewAdapter(products);
        //binding.recyclerView.addItemDecoration(new DividerItemDecoration(CovidStatusInfo.this, DividerItemDecoration.VERTICAL));
        binding.recyclerView.setAdapter(adapter);
        ScaleInAnimationAdapter scaleInAnimationAdapter=new ScaleInAnimationAdapter(adapter);
        binding.recyclerView.setAdapter(scaleInAnimationAdapter);
        scaleInAnimationAdapter.setFirstOnly(false);
        scaleInAnimationAdapter.setDuration(1000);
        //scaleInAnimationAdapter.setHasStableIds(false);
        scaleInAnimationAdapter.setInterpolator(new OvershootInterpolator(.100f));

        //binding.recyclerView.setItemAnimator(new SlideInUpAnimator(new OvershootInterpolator(1f)));
    }

    void init(){
        p=new Print(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        binding= DataBindingUtil.setContentView(CovidStatusInfo.this, R.layout.rv_activity_covid_status);
        configRecyclerView();
        p.sprintf("Click Card to View Overall Stats");
        fetchProdFromFirebase();

    }

    void fetchProdFromFirebase(){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://api.covid19india.org/state_district_wise.json", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject jsonObject1 = jsonObject.getJSONObject("Rajasthan");
                    JSONObject district = jsonObject1.getJSONObject("districtData");
                    int i = 0 ;
                    for(Iterator<String> iter = district.keys(); iter.hasNext();) {
                        String mycity = iter.next();
                        JSONObject thiscity = district.getJSONObject(mycity);
                        int active = thiscity.getInt("active");
                        int recovered = thiscity.getInt("recovered");
                        int deceased = thiscity.getInt("deceased");
                        CovidStatus cs = new CovidStatus(mycity, active, recovered, deceased);
                        products.add(cs);
                        adapter.notifyItemInserted(i);
                        i++;
                    }

                }catch (JSONException e){
                    Toast.makeText(CovidStatusInfo.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d(TAG,e.toString());
                }

            }
        }, error -> p.fprintf(error.getMessage()));

        requestQueue.add(stringRequest);

    }

}
