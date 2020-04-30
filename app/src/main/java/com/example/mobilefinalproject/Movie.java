package com.example.mobilefinalproject;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "watchlistTable")
public class Movie
{
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "title")
    private String mTitle;

    @ColumnInfo (name = "posterUrl")
    private String mPosterUrl;

    public Movie (String title, String posterUrl)
    {
        this.mTitle = title;
        this.mPosterUrl = posterUrl;
    }

    public String getTitle()
    {
        return this.mTitle;
    }

    public String getPosterUrl()
    {
        return this.mPosterUrl;
    }
}