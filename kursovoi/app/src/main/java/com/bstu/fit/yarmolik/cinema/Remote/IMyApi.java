package com.bstu.fit.yarmolik.cinema.Remote;

import com.bstu.fit.yarmolik.cinema.Model.UserData;

import java.util.Observable;

import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IMyApi {
    //http://localhost:5000/api/register
    @POST("api/register")
    Observable registerUser(@Body UserData user);
    @POST("api/login")
    Observable loginUser(@Body UserData user);

}
