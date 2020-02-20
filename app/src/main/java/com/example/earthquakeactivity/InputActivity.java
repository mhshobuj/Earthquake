package com.example.earthquakeactivity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InputActivity extends AppCompatActivity {

    private EditText startDateET, endDateET, minMagnitudeET;
    private Button resultBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        getSupportActionBar().hide();

        startDateET = findViewById(R.id.startDate);
        endDateET = findViewById(R.id.endDate);
        minMagnitudeET = findViewById(R.id.minMagnitude);

        resultBT = findViewById(R.id.result);

        resultBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String startDate = startDateET.getText().toString();
                String endDate = endDateET.getText().toString();
                int minMagnitude = Integer.parseInt(minMagnitudeET.getText().toString());

                Intent intent = new Intent(InputActivity.this, MainActivity.class);
                intent.putExtra("sDate",startDate);
                intent.putExtra("eDate",endDate);
                intent.putExtra("minM",minMagnitude);
                startActivity(intent);
            }
        });
    }
}
