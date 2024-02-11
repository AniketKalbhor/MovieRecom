package com.example.movierecom;

public class MovieModalClass {
    String overview, rating, title, img;
    String id;

    public MovieModalClass(String overview, String rating, String title, String img, String id) {
        this.overview = overview;
        this.rating = rating;
        this.title = title;
        this.img = img;
        this.id = id;
    }


    public MovieModalClass() {
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
