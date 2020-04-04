package com.bstu.fit.yarmolik.cinema.Model;

public class UserData {
    public String Name;
    public String Surname;
    public String Login;
    public String Email;
    public String Password;
    public int RoleId;
    public UserData(){

    }
    public UserData(String name,String surname,String login, String email, String password, int roleId){
        Name=name;
        Surname=surname;
        Login=login;
        Email=email;
        Password=password;
        RoleId=roleId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
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

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getRoleId() {
        return RoleId;
    }

    public void setRoleId(int roleId) {
        RoleId = roleId;
    }
}
