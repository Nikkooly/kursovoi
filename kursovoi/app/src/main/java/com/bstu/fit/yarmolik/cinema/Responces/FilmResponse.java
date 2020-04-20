package com.bstu.fit.yarmolik.cinema.Responces;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FilmResponse implements Serializable {
    public Integer Id;
    public String Name;
    public Integer Year;
    public String Country;
    public Integer Duration;
    public String Genre;
    public String Description;
    public String Poster;


    @SerializedName("body")
    private String text;

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getId() {
        return Id;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public void setYear(Integer year) {
        Year = year;
    }

    public Integer getYear() {
        return Year;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getCountry() {
        return Country;
    }

    public void setDuration(Integer duration) {
        Duration = duration;
    }

    public Integer getDuration() {
        return Duration;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public String getGenre() {
        return Genre;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDescription() {
        return Description;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }

    public String getPoster() {
        return Poster;
    }
}
