package com.example.bourbon.activities.harish_activities.helper_classes;

import android.content.Context;
import android.graphics.Color;

import com.example.bourbon.databinding.ActivityGraphDailyBinding;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.Date;

import print.Print;

public class PlotGraphHelper {

    private ActivityGraphDailyBinding binding;
    private Context context;
    private Print p;
    public PlotGraphHelper(ActivityGraphDailyBinding binding,Context ctx) {
        this.binding=binding;
        context=ctx;
        p=new Print(ctx);
    }

    public void plotGraph(ArrayList<Date> dateList, ArrayList<Integer> activeList
    ,ArrayList<Integer> recoveredList, ArrayList<Integer> deceasedList){

        ArrayList<Entry> activeCases=new ArrayList<>();
        ArrayList<Entry> recoveredCases=new ArrayList<>();
        ArrayList<Entry> deceasedCases=new ArrayList<>();

        for(int i=0;i<dateList.size();i++){

            activeCases.add(new Entry(new Long(dateList.get(i).getTime()).floatValue(),activeList.get(i)));
            recoveredCases.add(new Entry(new Long(dateList.get(i).getTime()).floatValue(),recoveredList.get(i)));
            deceasedCases.add(new Entry(new Long(dateList.get(i).getTime()).floatValue(),deceasedList.get(i)));
        }
        binding.lineChart.setNoDataText("No Chart Available\nClick to refresh!");
        configGraph(activeCases,recoveredCases,deceasedCases);
    }

    private void configGraph(ArrayList<Entry> activeCases,ArrayList<Entry> recoveredCases
    ,ArrayList<Entry> deceasedCases) {

        binding.lineChart.setDragEnabled(true);
        binding.lineChart.setScaleEnabled(true);

        LineDataSet set1=new LineDataSet(activeCases,"ActiveCases");
        LineDataSet set2=new LineDataSet(recoveredCases,"RecoveredCases");
        LineDataSet set3=new LineDataSet(deceasedCases,"DeceasedCases");
        set1.setColor(Color.RED);
        set2.setColor(Color.GREEN);
        set3.setColor(Color.BLUE);

//        ArrayList<ILineDataSet> dataSets=new ArrayList<>();
//        dataSets.add(set1);
//        dataSets.add(set2);
//        dataSets.add(set3);

        LineData data1=new LineData();
        data1.addDataSet(set1);
        data1.addDataSet(set2);
        data1.addDataSet(set3);


        binding.lineChart.getXAxis().setGranularityEnabled(true);
        binding.lineChart.getXAxis().setGranularity(1.0f);

        XAxis xAxis = binding.lineChart.getXAxis();
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                Date date=new Date(new Float(value).longValue());
                //p.sprintf("MyMpDate = "+date.toString().substring(4,11));
                return date.toString().substring(4,11);
            }
        });
        binding.lineChart.setData(data1);

        binding.lineChart.invalidate();





    }




}
