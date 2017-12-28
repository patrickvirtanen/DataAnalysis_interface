package com.example.android.dataanalysis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    private Button mCompare;
    private TextView mCompareText;
    private CheckBox mCheckAndreas;
    private CheckBox mCheckPatrick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        mCompare = findViewById(R.id.compare);
        mCompareText = findViewById(R.id.compareText);
        mCheckAndreas = findViewById(R.id.checkAndreas);
        mCheckPatrick = findViewById(R.id.checkPatrick);




        mCompare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Person andreas = new Person("Andreas", 20, 17, 24);
                Person patrick = new Person("Patrick", 21, 19, 26);
                if(mCheckPatrick.isChecked() && mCheckAndreas.isChecked()){
                    mCompareText.setText(patrick.toString() + "\n");
                    mCompareText.append(andreas.toString());
                }
            }
        });

    }
}
