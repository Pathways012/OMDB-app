package com.example.mobilefinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SelectedResultActivity extends AppCompatActivity {
    TextView results;
    JSONObject search;
    String url;
    String title = null;
    String posterUrl = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_result);

        //get the intent that started this activity and the title string
        Intent intent = getIntent();
        title = intent.getExtras().getString("extraMessage");

        results = findViewById(R.id.movieInfo1);
        results.setText(title);

        url = "https://www.omdbapi.com/?apikey=ef8f6b02&t="+title;

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
    }
}
