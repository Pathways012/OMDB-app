package com.example.mobilefinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class WatchlistActivity extends AppCompatActivity {
    //ListView watchlist;
    //List arrayList;
    //ArrayAdapter adapter;
    Button searchButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watchlist);

        searchButton = findViewById(R.id.searchButton);

        //watchlist = findViewById(R.id.watchlist);

        ListView list = findViewById(R.id.watchlist);
        ArrayList<SubjectData> arrayList = new ArrayList<SubjectData>();
        arrayList.add(new SubjectData("JAVA", "https://www.tutorialspoint.com/java/",             "https://www.tutorialspoint.com/java/images/java-mini-logo.jpg"));
        arrayList.add(new SubjectData("Python", "https://www.tutorialspoint.com/python/", "https://www.tutorialspoint.com/python/images/python-mini.jpg"));
        arrayList.add(new SubjectData("Javascript", "https://www.tutorialspoint.com/javascript/", "https://www.tutorialspoint.com/javascript/images/javascript-mini-logo.jpg"));
        arrayList.add(new SubjectData("Cprogramming", "https://www.tutorialspoint.com/cprogramming/", "https://www.tutorialspoint.com/cprogramming/images/c-mini-logo.jpg"));
        arrayList.add(new SubjectData("Cplusplus", "https://www.tutorialspoint.com/cplusplus/", "https://www.tutorialspoint.com/cplusplus/images/cpp-mini-logo.jpg"));
        arrayList.add(new SubjectData("Android", "https://www.tutorialspoint.com/android/", "https://www.tutorialspoint.com/android/images/android-mini-logo.jpg"));
        CustomAdapter customAdapter = new CustomAdapter(this, arrayList);
        list.setAdapter(customAdapter);
        /*arrayList = new ArrayList();

        arrayList.add("Test Item 1");
        arrayList.add("Test Item 2");
        arrayList.add("Test Item 3");
        arrayList.add("Test Item 4");
        arrayList.add("Test Item 5");
        arrayList.add("Test Item 6");
        arrayList.add("Test Item 7");
        arrayList.add("Test Item 8");
        arrayList.add("Test Item 9 ");
        arrayList.add("Test Item 10");
        arrayList.add("Test Item 11");
        arrayList.add("Test Item 12");
        arrayList.add("Test Item 13");
        arrayList.add("Test Item 14");
        arrayList.add("Test Item 15");
        arrayList.add("Test Item 16");
        arrayList.add("Test Item 17");
        arrayList.add("Test Item 18");
        arrayList.add("Test Item 19");
        arrayList.add("Test Item 20");

        adapter = new ArrayAdapter(WatchlistActivity.this, android.R.layout.simple_list_item_1, arrayList);
        watchlist.setAdapter(adapter);*/

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
