package com.example.android.dataanalysis;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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



    private Button mAndreas;
    private Button mKit;
    private Button mPatrick;
    private Button mComparePersons;
    private TempData mTempData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mAndreas = findViewById(R.id.Andreas);
        mKit = findViewById(R.id.Kit);
        mPatrick =findViewById(R.id.Patrick);
        mComparePersons = findViewById(R.id.comparePersons);

        mAndreas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startStory("Andreas");
            }
        });

        mKit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startStory("Kit");
            }
        });

        mPatrick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startStory("Patrick");
            }
        });



        mComparePersons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Activity2.class));
            }
        });


    }

    private void startStory(String name) {
        //Intent =
        Intent intent = new Intent(getApplicationContext(), Houses.class);
        Resources resources = getResources();
        String key = resources.getString(R.string.key_name);
        intent.putExtra(key, name);
        startActivity(intent);
        // Its most significant use is in the launching of activities, where it can be thought of as
        // the glue between activities. It is basically a passive data structure holding
        // an abstract description of an action to be performed.
    }


}










