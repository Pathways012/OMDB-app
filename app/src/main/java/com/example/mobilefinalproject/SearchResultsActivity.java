package com.example.mobilefinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SearchResultsActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        textView = findViewById(R.id.textView);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getExtras().getString("extraMessage");

        textView.setText(message);
    }
}
