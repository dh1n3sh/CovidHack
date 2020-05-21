//package com.example.bourbon.activities.clement_activities;
//
//import android.os.Bundle;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
//public class Graph_Stats extends AppCompatActivity {
//
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        //setContentView(R.layout.activity_graph__stats);
//        ButterKnife.bind(this);
//
//        String city = getIntent().getExtras().getString("City Name");
//
//        plotGraph(city);
//    }
//
//    private void plotGraph(String cityname) {
//
//        ArrayList<Integer> confirmed = new ArrayList<Integer>();
//        ArrayList<Integer> recovered = new ArrayList<>();
//        ArrayList<Integer> death = new ArrayList<>();
//        ArrayList<Date> dates = new ArrayList<>();
//
//        RequestQueue queue = Volley.newRequestQueue(this);
//        String url ="https://api.covid19india.org/districts_daily.json";
//
//// Request a string response from the provided URL.
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        // Display the first 500 characters of the response string.
//                        try {
//                            JSONObject jsonObject = new JSONObject(response);
//                            JSONObject districts = jsonObject.getJSONObject("districtsDaily");
//                            JSONObject state = districts.getJSONObject("Kerala");
//                            JSONArray city = state.getJSONArray(cityname);
//
////                            Toast.makeText(Graph_Stats.this, city.length() + "", Toast.LENGTH_SHORT).show();
//
//                            for(int i=0;i<city.length();i++){
//                                JSONObject day = city.getJSONObject(i);
////                                Toast.makeText(Graph_Stats.this, day.getString("date"), Toast.LENGTH_SHORT).show();
//                                confirmed.add(day.getInt("confirmed"));
//                                recovered.add(day.getInt("recovered"));
//                                death.add(day.getInt("deceased"));
//                                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
//                                SimpleDateFormat format2 = new SimpleDateFormat("dd-MM-yyyy");
//                                SimpleDateFormat format3 = new SimpleDateFormat("dd-MM-yyyy");
//                                Date date = format1.parse(day.getString("date"));
//                                dates.add(format3.parse((format2.format(date))));
//
////                                Toast.makeText(Graph_Stats.this, format3.parse((format2.format(date))).toString(), Toast.LENGTH_SHORT).show()
//                            }
//                            DataPoint[] dataPoints =  new DataPoint[confirmed.size()];
//                            for(int j=0;j<confirmed.size();j++){
////                                    Toast.makeText(Graph_Stats.this,dates.get(j).toString() + j, Toast.LENGTH_SHORT).show();
//                                dataPoints[j] = new DataPoint(dates.get(j),confirmed.get(j));
//                            }
//
////                            Toast.makeText(Graph_Stats.this,confirmed.size() + " ", Toast.LENGTH_SHORT).show();
//
//                                LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPoints);
////
//                                graph.addSeries(series);
//
//// set date label formatter
//                                graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getApplicationContext()));
//                                graph.getGridLabelRenderer().setNumHorizontalLabels(5);
//
//                            // set manual x bounds to have nice steps
//                            graph.getViewport().setMinX(dates.get(0).getTime());
//                            graph.getViewport().setMaxX(dates.get(confirmed.size()-1).getTime());
//                            graph.getViewport().setXAxisBoundsManual(true);
//
//// as we use dates as labels, the human rounding to nice readable numbers
//// is not necessary
//                            graph.getGridLabelRenderer().setHumanRounding(false);
//                        } catch (JSONException | ParseException e) {
//                            Toast.makeText(Graph_Stats.this,e.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//
//        // Add the request to the RequestQueue.
//        queue.add(stringRequest);
//
//
//
//
//
//    }
//}
