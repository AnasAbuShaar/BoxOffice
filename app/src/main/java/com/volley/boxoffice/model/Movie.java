package com.volley.boxoffice.model;

import java.util.ArrayList;

public class Movie {
    private String title, thumbnailUrl , synopsis;
      private int year;

    public Movie() {
    }

    public Movie(String name, String thumbnailUrl, double rating,
                 ArrayList<String> genre) {
        this.title = name;
        this.thumbnailUrl = thumbnailUrl;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }


    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public int getYear(){
        return year;

    }
    public void setYear(int year){

        this.year=year;

    }

}
