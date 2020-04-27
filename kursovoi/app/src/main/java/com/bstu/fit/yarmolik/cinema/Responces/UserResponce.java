package com.bstu.fit.yarmolik.cinema.Responces;

import java.io.Serializable;

public class UserResponce implements Serializable {
public String Id;
public String Login;
public String Email;

    public void setId(String id) {
        Id = id;
    }

    public String getId() {
        return Id;
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
