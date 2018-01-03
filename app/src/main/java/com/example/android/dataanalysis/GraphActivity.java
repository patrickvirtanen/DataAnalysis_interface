package com.example.android.dataanalysis;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.LimitLine.LimitLabelPosition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class GraphActivity extends AppCompatActivity {



    LineChart mLineChart;
    BarChart mBarChart;
    InputStream inputStream;
    String[] sensorData;

    ArrayList<Entry> entries = new ArrayList<>();
    ArrayList<String> dates = new ArrayList<>();

    String[] sensorData2;
    ArrayList<Entry> entries2 = new ArrayList<>();
    ArrayList<String> dates2 = new ArrayList<>();

    ArrayList<Float> allTemps = new ArrayList<>();

    ArrayList<Entry> smallerSet = new ArrayList<>();
    ArrayList<String> smallerDateSet = new ArrayList<>();


    ArrayList<Entry> smallerSet2 = new ArrayList<>();
    ArrayList<String> smallerDateSet2 = new ArrayList<>();


    private TempData mTempData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        mLineChart = findViewById(R.id.linegraph);


        drawDiagram();


    }





    private void drawDiagram() {

        importData();
        importData2();
        createSmallerSet();
        createSmallerSet2();
        //just nu är det två av båda dessa eftersom vi inte har en fungerande klass för att spara denna info.

        LineDataSet dataSet = new LineDataSet(smallerSet, "Temperatures"); //bytt ut entries mot smallerset
        LineDataSet dataSet2 = new LineDataSet(smallerSet2, "Temperatures"); //bytt ut entries mot smallerset
        List<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(dataSet);
        dataSets.add(dataSet2);

        LineData lineData = new LineData(dataSets);
        mLineChart.setData(lineData);
        dataSet.setColor(Color.GREEN);
        dataSet.setDrawFilled(true);
        dataSet.setFillColor(Color.GREEN);
        dataSet.setDrawCircles(false);
        dataSet.setDrawValues(false);
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        dataSet.notifyDataSetChanged();
        dataSet2.setColor(Color.RED);
        dataSet2.setDrawCircles(false);
        mLineChart.getDescription().setText("");
        mLineChart.getLegend().setEnabled(false);
        mLineChart.invalidate();


        YAxis leftAxis = mLineChart.getAxisLeft();
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setAxisMinimum(17f);
        leftAxis.setAxisMaximum(25f);


        YAxis rightAxis = mLineChart.getAxisRight();
        rightAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        rightAxis.setAxisMinimum(17f);
        rightAxis.setAxisMaximum(25f);

        XAxis xAxis = mLineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisMinimum(0f);
        xAxis.setLabelCount(5, true);
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);


        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return smallerDateSet.get((int) value / 65); //byter ut dates mot smallerdates
            }
        });
    }

    public void createSmallerSet() {
        int j = 0;
        for (int i = (entries.size() - 73); i < entries.size(); i++) {
            // hämta värdet
            Float tempTemp = entries.get(i).getY();
            System.out.println("tempTemp:" + tempTemp);
            // hämta datumet o lägg till på index 0 i ny arraylist
            String tempDate = dates.get(i);
            smallerDateSet.add(tempDate);
            System.out.println("tempDate:" + tempDate);


            // göra en ny entry m ny int o lägg till på index 0 i smallerset
            smallerSet.add(new Entry(j, tempTemp));
            j++;
        }
    }

    public void createSmallerSet2() {
        int j = 0;
        for (int i = (entries2.size() - 73); i < entries2.size(); i++) {
            // hämta värdet
            Float tempTemp = entries2.get(i).getY();
            System.out.println("tempTemp:" + tempTemp);
            // hämta datumet o lägg till på index 0 i ny arraylist
            String tempDate = dates2.get(i);
            smallerDateSet2.add(tempDate);
            System.out.println("tempDate:" + tempDate);


            // göra en ny entry m ny int o lägg till på index 0 i smallerset
            smallerSet2.add(new Entry(j, tempTemp));
            j++;
        }
    }


    public void importData() {

        inputStream = getResources().openRawResource(R.raw.sensor1); //bytt ut sensor_vardagsrum mot sensor1 och 2
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine;
            int index = 0;

            while ((csvLine = reader.readLine()) != null) {

                sensorData = csvLine.split(",");

                sensorData[0] = sensorData[0].substring(0, sensorData[0].length() - 1).replaceAll("\\s+", "");
                sensorData[1] = sensorData[1].replaceAll("\\D", "");

                float temp = Float.parseFloat(sensorData[0]);
                allTemps.add(temp);


                entries.add(new Entry(index, temp));
                dates.add(sensorData[1]);
                index++;
            }
        } catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file" + ex);
        }
    }

    public void importData2() {

        inputStream = getResources().openRawResource(R.raw.sensor2); //bytt ut sensor_vardagsrum mot sensor1 och 2
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine;
            int index = 0;

            while ((csvLine = reader.readLine()) != null) {

                sensorData2 = csvLine.split(",");

                sensorData2[0] = sensorData2[0].substring(0, sensorData2[0].length() - 1).replaceAll("\\s+", "");
                sensorData2[1] = sensorData2[1].replaceAll("\\D", "");

                float temp = Float.parseFloat(sensorData2[0]);
                allTemps.add(temp);


                entries2.add(new Entry(index, temp));
                dates2.add(sensorData2[1]);
                index++;
            }
        } catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file" + ex);
        }
    }

    public ArrayList<Float> getList() {

        return allTemps;

    }
}







