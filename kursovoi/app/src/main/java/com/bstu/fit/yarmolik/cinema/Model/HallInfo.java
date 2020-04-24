package com.bstu.fit.yarmolik.cinema.Model;

public class HallInfo {
    private Integer CinemaId;
    private String Name;
    private Integer Places;
    public  HallInfo(Integer cinemaId,String name, Integer places){
        CinemaId=cinemaId;
        Name=name;
        Places=places;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public Integer getCinemaId() {
        return CinemaId;
    }

    public Integer getPlaces() {
        return Places;
    }

    public void setPlaces(Integer places) {
        Places = places;
    }

    public void setCinemaId(Integer cinemaId) {
        CinemaId = cinemaId;
    }

}
