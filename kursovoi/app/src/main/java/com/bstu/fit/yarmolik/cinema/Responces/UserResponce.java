package com.bstu.fit.yarmolik.cinema.Responces;

import java.io.Serializable;

public class UserResponce implements Serializable {
public int Id;
public String Login;
public String Email;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
