package com.example.mobilefinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SelectedResultActivity extends AppCompatActivity {
    TextView movieInfo1;
    TextView movieInfo2;
    ImageView moviePoster;
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
        title = intent.getExtras().getString("title");
        posterUrl = intent.getExtras().getString("posterUrl");

        moviePoster = findViewById(R.id.moviePoster);
        Glide.with(SelectedResultActivity.this)
                .load(posterUrl)
                .into(moviePoster);

        movieInfo1 = findViewById(R.id.movieInfo1);
        movieInfo2 = findViewById(R.id.movieInfo2);
        movieInfo1.setText(title);

        url = "https://www.omdbapi.com/?apikey=ef8f6b02&t="+title;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("VOLLEY", response.toString());   //put the response into the log to check it was received

                        String movieInfo1Data;
                        String title = response.optString("Title", "No title found");
                        String rated = response.optString("Rated", "No rating found");
                        String released = response.optString("Released", "No release found");
                        String runtime = response.optString("Runtime", "No runtime found");
                        String genres = response.optString("Genre", "No genres found");

                        String movieInfo2Data;
                        String language = response.optString("Language", "No language found");
                        String awards = response.optString("Awards", "No awards found");
                        String plot = response.optString("Plot", "No plot found");
                        String imdbRating = response.optString("imdbRating", "No rating found");

                        movieInfo1Data = "Title: "+title+"\n"+"Rated: "+rated+"\nReleased: "+released+"\nRuntime: "+runtime+"\nGenres: "+genres;
                        movieInfo2Data = "Language: "+language+"\nAwards: "+awards+"\nPlot: "+plot+"\nIMDB Rating: "+imdbRating;

                        movieInfo1.setText(movieInfo1Data);
                        movieInfo2.setText(movieInfo2Data);
                        //Rito wanted me to delete the "catch" block
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

    public void addToWatchlist(View view)
    {
        Toast.makeText(SelectedResultActivity.this, "Item added to watchlist", Toast.LENGTH_LONG).show();
        Movie movie = new Movie(title, posterUrl);
        //insertMovie(movie);
    }
}
