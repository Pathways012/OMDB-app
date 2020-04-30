package com.example.mobilefinalproject;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MovieDAO {
    @Query("SELECT * FROM watchlistTable")
    LiveData<List<Movie>> getAll();

    @Insert
    public void insertMovie(Movie movie);

    //@Query("DELETE FROM watchlistTable WHERE title = 'title'")
    //public void deleteMovie(Movie movie);

    @Query("DELETE FROM watchlistTable")
    public void deleteAll();
}
