package com.example.mobilefinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchResultsActivity extends AppCompatActivity {
    String url;
    String title = null;
    String posterUrl = null;
    ArrayList<SubjectData> arrayList;
    ListView list;
    JSONObject search;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        /*
        //Make a ListView with custom items
        ListView list = findViewById(R.id.resultsList);
        arrayList = new ArrayList<SubjectData>();
        CustomAdapter customAdapter = new CustomAdapter(this, arrayList);
        list.setAdapter(customAdapter);*/

        list = findViewById(R.id.resultsList);
        arrayList = new ArrayList<SubjectData>();

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getExtras().getString("extraMessage");

        url = "https://www.omdbapi.com/?apikey=ef8f6b02&s="+message;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("VOLLEY", response.toString());   //put the response into the log to check it was received

                        try {
                            JSONArray values = response.getJSONArray("Search");

                            for (int i = 0; i < 6/*values.length()*/; i++) {
                                search = values.getJSONObject(i);
                                title = search.optString("Title", "No title found");
                                posterUrl = search.optString("Poster", "No poster found");
                                Log.i("title", title);
                                Log.i("posterUrl", posterUrl);

                                //The first runthrough, add an item then set the adapter to avoid crashing the list with zero items.
                                //issue may be list restarting after it goes off the bottom of the screen... something to do with the loop.
                                //goes wrong after 6 items have been added, the API always returns 10 items.
                                if(i == 0){
                                    arrayList.add(new SubjectData(title, posterUrl));
                                    customAdapter = new CustomAdapter(SearchResultsActivity.this, arrayList);
                                    list.setAdapter(customAdapter);
                                }
                                else
                                    arrayList.add(new SubjectData(title, posterUrl));
                            }
                        }
                        catch(JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("VOLLEY", error.toString());  //put the error into the log to be read
                    }
                });
        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);


        //this function is still a work in progress.
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                SubjectData item = (SubjectData) adapter.getItemAtPosition(position);

                Intent intent = new Intent(SearchResultsActivity.this, SelectedResultActivity.class);
                //based on item add info to intent
                title = item.SubjectName;
                intent.putExtra("extraMessage", title);
                startActivity(intent);
            }
        });
    }
}
