package com.example.mobilefinalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class WatchlistActivity extends AppCompatActivity {
    Button searchButton;
    MovieRepository movieRepository;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watchlist);

        searchButton = findViewById(R.id.searchButton);

        movieRepository = new MovieRepository(getApplicationContext());
        //AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                //AppDatabase.class, "watchlistDatabase").build();

        //Movie testMovie1 ("Despicable Me", "https://m.media-amazon.com/images/M/MV5BMTY3NjY0MTQ0Nl5BMl5BanBnXkFtZTcwMzQ2MTc0Mw@@._V1_SX300.jpg");
        //testMovie1.title = "Deadpool";
        //testMovie1.posterUrl = "https://m.media-amazon.com/images/M/MV5BMTY3NjY0MTQ0Nl5BMl5BanBnXkFtZTcwMzQ2MTc0Mw@@._V1_SX300.jpg";

        ListView list = findViewById(R.id.watchlist);
        ArrayList<SubjectData> arrayList = new ArrayList<SubjectData>();
        /*arrayList.add(new SubjectData("JAVA",              "https://www.tutorialspoint.com/java/images/java-mini-logo.jpg"));
        arrayList.add(new SubjectData("Python",  "https://www.tutorialspoint.com/python/images/python-mini.jpg"));
        arrayList.add(new SubjectData("Javascript",  "https://www.tutorialspoint.com/javascript/images/javascript-mini-logo.jpg"));
        arrayList.add(new SubjectData("Cprogramming",  "https://www.tutorialspoint.com/cprogramming/images/c-mini-logo.jpg"));
        arrayList.add(new SubjectData("Cplusplus",  "https://www.tutorialspoint.com/cplusplus/images/cpp-mini-logo.jpg"));
        arrayList.add(new SubjectData("Android", "https://www.tutorialspoint.com/android/images/android-mini-logo.jpg"));
        arrayList.add(new SubjectData("Test Object", "https://m.media-amazon.com/images/M/MV5BMjEwMzMxODIzOV5BMl5BanBnXkFtZTgwNzg3OTAzMDI@._V1_SX300.jpg"));
        */
        customAdapter = new CustomAdapter(this, arrayList);
        list.setAdapter(customAdapter);

        searchButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                switch (v.getId()) {
                    case R.id.searchButton:
                        Intent intent = new Intent(WatchlistActivity.this, SearchActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }
}
