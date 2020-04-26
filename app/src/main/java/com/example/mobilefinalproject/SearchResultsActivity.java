package com.example.mobilefinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SearchResultsActivity extends AppCompatActivity {
    TextView textView;
    ImageView imageView;
    String url;
    String apikey = "ef8f6b02";
    String title = null;
    String posterUrl = null;
    String testUrl = "https://m.media-amazon.com/images/M/MV5BNzVlY2MwMjktM2E4OS00Y2Y3LWE3ZjctYzhkZGM3YzA1ZWM2XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SX300.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);

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

                            for (int i = 0; i < values.length(); i++) {
                                JSONObject search = values.getJSONObject(i);
                                title = search.optString("Title", "No title found");
                                posterUrl = search.optString("Poster", "No poster found");
                                Log.i("title", title);
                                Log.i("posterUrl", posterUrl);
                                textView.setText(title);
                                Glide.with(SearchResultsActivity.this).load(posterUrl).into(imageView);
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
