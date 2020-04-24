package com.bstu.fit.yarmolik.cinema.Responces;

import java.io.Serializable;

public class HallResponse implements Serializable {
    private Integer Id;
    private String Name;
    private Integer Places;

    public void setPlaces(Integer places) {
        Places = places;
    }

    public Integer getPlaces() {
        return Places;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getId() {
        return Id;
    }
}
