package com.example.android.dataanalysis;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Houses extends AppCompatActivity {


    private Button mShowGraph;
    private Button mKit;
    private TextView mName;
    private TextView mAvg;
    private TextView mHighest;
    private TextView mLowest;


    InputStream inputStream1;
    InputStream inputStream2;
    String[] sensorData1;
    String[] sensorData2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_houses);

        mKit = findViewById(R.id.Kit);
        mName = findViewById(R.id.nameDetail);
        mAvg = findViewById(R.id.avg);
        mHighest = findViewById(R.id.highest);
        mLowest = findViewById(R.id.lowest);


        mShowGraph = findViewById(R.id.showGraph);


        Intent intent = getIntent();
        String name = intent.getStringExtra(getString(R.string.key_name));

        if (name.equals("Kit")) {

            kitsTempCSV();

        } else if (name.equals("Andreas")) {
            andreasTempCSV();
        } else {
            patricksTempCSV();
        }

       


        mShowGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), GraphActivity.class));
            }
        });


    }

    private void kitsTempCSV() {
        ArrayList<Float> kitsHouse = new ArrayList<>();

        inputStream1 = getResources().openRawResource(R.raw.sensor1); //bytt ut sensor_vardagsrum mot sensor1 och 2
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream1));
        inputStream2 = getResources().openRawResource(R.raw.sensor1); //bytt ut sensor_vardagsrum mot sensor1 och 2
        BufferedReader reader2 = new BufferedReader(new InputStreamReader(inputStream1));
        try {
            String csvLine;


            while ((csvLine = reader.readLine()) != null) {

                sensorData1 = csvLine.split(",");
                sensorData2 = csvLine.split(",");

                sensorData1[0] = sensorData1[0].substring(0, sensorData1[0].length() - 1).replaceAll("\\s+", "");
                sensorData1[1] = sensorData1[1].replaceAll("\\D", "");

                sensorData2[0] = sensorData2[0].substring(0, sensorData2[0].length() - 1).replaceAll("\\s+", "");
                sensorData2[1] = sensorData2[1].replaceAll("\\D", "");

                float temp = Float.parseFloat(sensorData1[0]);
                float temp2 = Float.parseFloat(sensorData2[0]);
                kitsHouse.add(temp);
                kitsHouse.add(temp2);



            }
        } catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file" + ex);
        }

        mName.setText("Kit");

        mAvg.setText("" + calculateAverage(kitsHouse));

        mLowest.setText("" + calculateLowest(kitsHouse));

        mHighest.setText("" + calculateHighest(kitsHouse));



    }

    private void andreasTempCSV() {
        ArrayList<Float> andreasHouse = new ArrayList<>();

        inputStream1 = getResources().openRawResource(R.raw.sensor_vardagsrum); //bytt ut sensor_vardagsrum mot sensor1 och 2
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream1));
//        inputStream2 = getResources().openRawResource(R.raw.sensor1); //bytt ut sensor_vardagsrum mot sensor1 och 2
//        BufferedReader reader2 = new BufferedReader(new InputStreamReader(inputStream1));
        try {
            String csvLine;


            while ((csvLine = reader.readLine()) != null) {

                sensorData1 = csvLine.split(",");
                sensorData2 = csvLine.split(",");

                sensorData1[0] = sensorData1[0].substring(0, sensorData1[0].length() - 1).replaceAll("\\s+", "");
                sensorData1[1] = sensorData1[1].replaceAll("\\D", "");

                sensorData2[0] = sensorData2[0].substring(0, sensorData2[0].length() - 1).replaceAll("\\s+", "");
                sensorData2[1] = sensorData2[1].replaceAll("\\D", "");

                float temp = Float.parseFloat(sensorData1[0]);
                float temp2 = Float.parseFloat(sensorData2[0]);
                andreasHouse.add(temp);
                andreasHouse.add(temp2);



            }
        } catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file" + ex);
        }

        mName.setText("Andreas");

        mAvg.setText("" + calculateAverage(andreasHouse));

        mLowest.setText("" + calculateLowest(andreasHouse));

        mHighest.setText("" + calculateHighest(andreasHouse));



    }

    private void patricksTempCSV() {
        ArrayList<Float> patricksHouse = new ArrayList<>();

        inputStream1 = getResources().openRawResource(R.raw.sensor_vardagsrum); //bytt ut sensor_vardagsrum mot sensor1 och 2
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream1));
//        inputStream2 = getResources().openRawResource(R.raw.sensor1); //bytt ut sensor_vardagsrum mot sensor1 och 2
//        BufferedReader reader2 = new BufferedReader(new InputStreamReader(inputStream1));
        try {
            String csvLine;


            while ((csvLine = reader.readLine()) != null) {

                sensorData1 = csvLine.split(",");
                sensorData2 = csvLine.split(",");

                sensorData1[0] = sensorData1[0].substring(0, sensorData1[0].length() - 1).replaceAll("\\s+", "");
                sensorData1[1] = sensorData1[1].replaceAll("\\D", "");

                sensorData2[0] = sensorData2[0].substring(0, sensorData2[0].length() - 1).replaceAll("\\s+", "");
                sensorData2[1] = sensorData2[1].replaceAll("\\D", "");

                float temp = Float.parseFloat(sensorData1[0]);
                float temp2 = Float.parseFloat(sensorData2[0]);
                patricksHouse.add(temp);
                patricksHouse.add(temp2);



            }
        } catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file" + ex);
        }

        mName.setText("Patrick");

        mAvg.setText("" + calculateAverage(patricksHouse));

        mLowest.setText("" + calculateLowest(patricksHouse));

        mHighest.setText("" + calculateHighest(patricksHouse));



    }


    private float calculateAverage(ArrayList<Float> arr){
        float sum = 0;
        for (int i = 0; i < arr.size(); i++){
            sum += arr.get(i);
        }

        float average = sum/arr.size();
        return average;
    }

    private float calculateHighest(ArrayList<Float> arr){
        float highestTemp = Collections.max(arr);
        return highestTemp;
        }

    private float calculateLowest(ArrayList<Float> arr){
        float lowestTemp = Collections.min(arr);
        return lowestTemp;
    }


    }







