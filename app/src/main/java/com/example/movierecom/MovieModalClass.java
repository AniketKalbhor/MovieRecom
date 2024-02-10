package com.example.movierecom;

public class MovieModalClass {
    String overview;
    String title;
    String img;

    public MovieModalClass(String id, String title, String img) {
        this.overview = id;
        this.title = title;
        this.img = img;
    }

    public MovieModalClass() {
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

    public void setImg(String img) {
        this.img = img;
    }
}
