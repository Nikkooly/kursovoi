package com.bstu.fit.yarmolik.cinema.Model;

public class Seance {
    private String StartTime;
    private String HallId;
    private String FilmId;
    private String EndTime;
    public Seance(String start_time, String hallId, String filmId,String end_time){
        StartTime=start_time;
        HallId=hallId;
        FilmId=filmId;
        EndTime=end_time;
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

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }
}
