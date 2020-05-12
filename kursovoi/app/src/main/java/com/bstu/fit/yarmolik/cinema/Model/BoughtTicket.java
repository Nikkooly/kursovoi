package com.bstu.fit.yarmolik.cinema.Model;

import java.util.List;

public class BoughtTicket {
    public String IdUser;
    public List<String> IdPlace;
    public String EmailUser;
    public String Date;
    public String StartTime;
    public String EndTime;
    public String CinemaInfo;
    public String HallName;
    public String Count;
    public String FinalPrice;

    public BoughtTicket(String idUser, List<String> idPlace, String emailUser, String date, String startTime, String endTime, String cinemaInfo, String hallName, String count, String finalPrice) {
        IdUser = idUser;
        IdPlace = idPlace;
        EmailUser = emailUser;
        Date = date;
        StartTime = startTime;
        EndTime = endTime;
        CinemaInfo = cinemaInfo;
        HallName = hallName;
        Count = count;
        FinalPrice = finalPrice;
    }

    public String getIdUser() {
        return IdUser;
    }

    public void setIdUser(String idUser) {
        IdUser = idUser;
    }

    public List<String> getIdPlace() {
        return IdPlace;
    }

    public void setIdPlace(List<String> idPlace) {
        IdPlace = idPlace;
    }

    public String getEmailUser() {
        return EmailUser;
    }

    public void setEmailUser(String emailUser) {
        EmailUser = emailUser;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }


    public String getCinemaInfo() {
        return CinemaInfo;
    }

    public void setCinemaInfo(String cinemaInfo) {
        CinemaInfo = cinemaInfo;
    }

    public String getHallName() {
        return HallName;
    }

    public void setHallName(String hallName) {
        HallName = hallName;
    }

    public String getCount() {
        return Count;
    }

    public void setCount(String count) {
        Count = count;
    }

    public String getFinalPrice() {
        return FinalPrice;
    }

    public void setFinalPrice(String finalPrice) {
        FinalPrice = finalPrice;
    }
}
