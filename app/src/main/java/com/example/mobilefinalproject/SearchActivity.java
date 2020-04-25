package com.example.mobilefinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SearchActivity extends AppCompatActivity {
    EditText userInput;
    Button searchResultsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchResultsButton = findViewById(R.id.searchResultsButton);
        userInput = findViewById(R.id.userInput);

        searchResultsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.searchResultsButton:
                        Intent intent = new Intent(SearchActivity.this, SearchResultsActivity.class);
                        String message = userInput.getText().toString();
                        intent.putExtra("extraMessage", message);
                        startActivity(intent);
                        break;
                }
            }
        });


    }
}
