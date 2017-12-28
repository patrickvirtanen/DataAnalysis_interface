package com.example.android.dataanalysis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Houses extends AppCompatActivity {


    private Button mShowGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_houses);

        mShowGraph = findViewById(R.id.showGraph);

        mShowGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), GraphActivity.class));
            }
        });
    }
}
