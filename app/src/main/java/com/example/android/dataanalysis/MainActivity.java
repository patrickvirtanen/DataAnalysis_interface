package com.example.android.dataanalysis;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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

public class MainActivity extends AppCompatActivity {


    LineChart mLineChart;
    BarChart mBarChart;
    InputStream inputStream;
    String [] sensorData;
    ArrayList<Entry> entries = new ArrayList<>();
    ArrayList<String> xAxis = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLineChart = findViewById(R.id.linegraph);

        // METOD FÖR ATT LÄSA IN CSV-FILEN OCH GÖRA OM VÄRDERNA TILL ENTRIES
        importData();

        // LÄGGER ALLA ENTRIES I ETT DATASET OCH RITAR UPP LINJEDIAGRAMMET
        LineDataSet dataSet = new LineDataSet(entries, "Temperatures");
        LineData lineData = new LineData(dataSet);
        mLineChart.setData(lineData);
        dataSet.setColor(Color.RED);
        dataSet.setDrawCircles(false);
        dataSet.setDrawValues(false);
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        mLineChart.getDescription().setText("");
        mLineChart.getLegend().setEnabled(false);
        mLineChart.invalidate();


        // INSTÄLLNINGAR FÖR Y- OCH X AXLAR LINECHART
        YAxis leftAxis = mLineChart.getAxisLeft();
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setAxisMaximum(30f);


        YAxis rightAxis = mLineChart.getAxisRight();
        rightAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        rightAxis.setAxisMinimum(0f);
        rightAxis.setAxisMaximum(30f);

        XAxis xAxis = mLineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new DateAxisValueFormatter(null));

    }

    public void importData() {

        inputStream = getResources().openRawResource(R.raw.sensor_vardagsrum);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine;

                while ((csvLine = reader.readLine()) != null) {

                sensorData = csvLine.split(",");

                sensorData[0] = sensorData[0].substring(0, sensorData[0].length() - 1).replaceAll("\\s+", "");
                sensorData[1] = sensorData[1].replaceAll("\\D", "");

                        float temp = Float.parseFloat(sensorData[0]);
                        float date = Float.parseFloat(sensorData[1]);

                        entries.add(new Entry(date, temp));

                    }

            } catch (IOException ex) {
             throw new RuntimeException("Error in reading CSV file" + ex);
        }
    }
}


class DateAxisValueFormatter implements IAxisValueFormatter {
    private String[] mValues;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");


    public DateAxisValueFormatter(String[] values) {
        this.mValues = values; }

        @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return sdf.format(new Date((long)value));
        }
    }