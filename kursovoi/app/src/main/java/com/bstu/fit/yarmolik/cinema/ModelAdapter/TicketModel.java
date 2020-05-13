package com.bstu.fit.yarmolik.cinema.ModelAdapter;

public class TicketModel {
    public String filmName;
    public String seanceId;
    public String date;
    public String userId;
    public String time;

    public TicketModel(String filmName, String seanceId, String date,String time, String userId) {
        this.filmName = filmName;
        this.seanceId = seanceId;
        this.date = date;
        this.userId = userId;
        this.time=time;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getSeanceId() {
        return seanceId;
    }

    public void setSeanceId(String seanceId) {
        this.seanceId = seanceId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
