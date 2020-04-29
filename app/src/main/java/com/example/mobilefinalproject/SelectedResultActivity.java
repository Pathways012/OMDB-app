package com.example.mobilefinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SelectedResultActivity extends AppCompatActivity {
    TextView results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_result);

        //get the intent that started this activity and the title string
        Intent intent = getIntent();
        //String title = intent.getExtras().getString("extraMessage");

        results = findViewById(R.id.results);
        //results.setText(title);
    }
}
