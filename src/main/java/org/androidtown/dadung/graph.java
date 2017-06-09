package org.androidtown.dadung;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

public class graph extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(4f,0));
        entries.add(new PieEntry(8f,1));
        entries.add(new PieEntry(6f,2));

        PieChart piChart = (PieChart)findViewById(R.id.test);


        ArrayList<String> label2 = new ArrayList<>();
        label2.add("eee");
        label2.add("bbb");
        label2.add("rewr");

        PieDataSet iPieDataSet = new PieDataSet(entries,"ddd");

        PieData lineData = new PieData(iPieDataSet);

        iPieDataSet.setColors(new int[]{0xFFFF4081,0xFF64081,0xFF41081});

        piChart.setData(lineData); // set the data and list of lables into chart
    }
}
