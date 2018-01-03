package com.example.android.dataanalysis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Houses extends AppCompatActivity {


    private Button mShowGraph;
    private Button mKit;
    private TextView mName;
    private TextView mAvg;
    private GraphActivity temps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_houses);

        mKit = findViewById(R.id.Kit);
        mName = findViewById(R.id.nameDetail);
        mAvg = findViewById(R.id.avg);

        mShowGraph = findViewById(R.id.showGraph);


        Intent intent = getIntent();
        String name = intent.getStringExtra(getString(R.string.key_name));

        if (name.equals("Kit")) {
            kitsHouse();
        } else if (name.equals("Andreas")){
            andreasHouse();
        } else {
            patricksHouse();
        }


        mShowGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), GraphActivity.class));
            }
        });


    }

    private void patricksHouse() {
        mName.setText("Patrick");

    }

    private void andreasHouse() {
        mName.setText("Andreas");
    }

    public void kitsHouse(){
        mName.setText("Kit");

        mAvg.setText("" + getAverageTemperatures());



    }

    public float getAverageTemperatures(){
        temps = new GraphActivity();

        ArrayList<Float> allTemps = temps.getList();
        ArrayList<Float> transferedTemp = new ArrayList<>();

        for (float i = 0; i < 10; i++){
            transferedTemp.add(i);
        }
        return  allTemps.size();

    }



}
