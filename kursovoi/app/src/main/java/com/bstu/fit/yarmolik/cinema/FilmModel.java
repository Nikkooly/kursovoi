package com.bstu.fit.yarmolik.cinema;

public class FilmModel {
    private int Position;
    private String Title;
    private float Vote;
    private Double Popularity;
    private String Date;


    public FilmModel(int position, String title, float vote, Double popularity, String date) {
        this.Title = title;
        this.Position = position;
        this.Vote = vote;
        this.Popularity = popularity;
        this.Date = date;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public float getVote() {
        return Vote;
    }

    public void setVote(float vote) {
        Vote = vote;
    }

    public Double getPopularity() {
        return Popularity;
    }

    public void setPopularity(Double popularity) {
        Popularity = popularity;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setPosition(int position) {
        Position = position;
    }

    public int getPosition() {
        return Position;
    }


}
