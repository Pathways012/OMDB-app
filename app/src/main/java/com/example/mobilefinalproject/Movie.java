package com.example.mobilefinalproject;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

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

    public Movie ()
    {
        this.mTitle = null;
        this.mPosterUrl = null;
    }

    public String getTitle()
    {
        return this.mTitle;
    }
    public void setTitle(String title) {this.mTitle = title;}

    public String getPosterUrl()
    {
        return this.mPosterUrl;
    }
    public void setPosterUrl(String posterUrl){this.mPosterUrl = posterUrl;}
}