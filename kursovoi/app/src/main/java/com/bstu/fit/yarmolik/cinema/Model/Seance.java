package com.bstu.fit.yarmolik.cinema.Model;

public class Seance {
    private String Date;
    private String Time;
    private String HallId;
    private String FilmId;
    public Seance(String date, String time, String hallId, String filmId){
        Date=date;
        Time=time;
        HallId=hallId;
        FilmId=filmId;
    }

    public void setHallId(String hallId) {
        HallId = hallId;
    }

    public void setFilmId(String filmId) {
        FilmId = filmId;
    }

    public String getFilmId() {
        return FilmId;
    }

    public String getHallId() {
        return HallId;
    }

    public String getDate() {
        return Date;
    }

    public String getTime() {
        return Time;
    }



    public void setDate(String date) {
        Date = date;
    }


    public void setTime(String time) {
        Time = time;
    }
}
