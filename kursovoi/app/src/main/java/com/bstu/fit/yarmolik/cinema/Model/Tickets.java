package com.bstu.fit.yarmolik.cinema.Model;

public class Tickets {
    private Integer Place;
    private Double Price;
    private String SeanceId;
    private boolean Status;
    public Tickets(Integer place, Double price, String seanceId, boolean status){
        Place=place;
        Price=price;
        SeanceId=seanceId;
        Status=status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setPlace(Integer place) {
        Place = place;
    }

    public Integer getPlace() {
        return Place;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public Double getPrice() {
        return Price;
    }

    public String getSeanceId() {
        return SeanceId;
    }

    public void setSeanceId(String seanceId) {
        SeanceId = seanceId;
    }
}
