package com.example.android.dataanalysis;


import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.EntryXIndexComparator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {


    LineChart mLineChart;
    InputStream inputStream;
    String [] sensorData;
    ArrayList<Entry> entries = new ArrayList<>();
    ArrayList<String> xAxis = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLineChart = findViewById(R.id.linegraph);
        mLineChart.setDescription("");


        // METOD FÖR ATT LÄSA IN CSV-FILEN OCH GÖRA OM VÄRDERNA TILL ENTRIES
        importData();

        // LÄGGER ALLA ENTRIES I ETT DATASET OCH RITAR UPP LINJEDIAGRAMMET
        LineDataSet dataSet = new LineDataSet(entries, "Temperatures");
        LineData lineData = new LineData();
        lineData.addDataSet(dataSet);
        mLineChart.setData(lineData);
        dataSet.setColor(Color.RED);
        mLineChart.invalidate();
        System.out.println(entries.toString());

        // INSTÄLLNINGAR FÖR Y- OCH X AXLAR
        YAxis leftAxis = mLineChart.getAxisLeft();
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);

        XAxis xAxis = mLineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

    }

    public void importData() {

        inputStream = getResources().openRawResource(R.raw.sensor_vardagsrum);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine;

            while ((csvLine = reader.readLine()) != null) {

                sensorData = csvLine.split(",");

                sensorData[0] = sensorData[0].substring(0, sensorData[0].length() - 1).replaceAll("\\s+", "");

                    for (int i = 0; i < sensorData.length; i++) {

                        float temp = Float.parseFloat(sensorData[0]);

                        entries.add(new Entry(temp, i));

                        xAxis.add(sensorData[1]);
                    }

                    System.out.println(entries);
                }

            } catch (IOException ex) {
             throw new RuntimeException("Error in reading CSV file" + ex);
        }
    }
}