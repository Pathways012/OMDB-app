package com.example.mobilefinalproject;


import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;

public class MovieRepository {

    private String DB_NAME = "db_task";

    private AppDatabase watchlistDatabase;
    public MovieRepository(Context context) {
        watchlistDatabase = Room.databaseBuilder(context, AppDatabase.class, DB_NAME).build();
    }

    public void insertMovie(String title,
                           String posterUrl) {

        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setPosterUrl(posterUrl);
        watchlistDatabase.daoAccess().insertMovie(movie);
    }

    public void deleteMovie(final Movie movie) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                watchlistDatabase.daoAccess().deleteMovie(movie);
                return null;
            }
        }.execute();
    }
}
