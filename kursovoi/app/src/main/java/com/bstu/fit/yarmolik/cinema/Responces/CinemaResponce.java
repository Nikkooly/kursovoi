package com.bstu.fit.yarmolik.cinema.Responces;

import java.io.Serializable;

public class CinemaResponce implements Serializable {
    public Integer Id;
    public String Name;
    public String Adress;

       public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }
}
