package com.bstu.fit.yarmolik.cinema.Remote;

import com.bstu.fit.yarmolik.cinema.Model.LoginUser;
import com.bstu.fit.yarmolik.cinema.Model.UserData;
import  io.reactivex.observers.DefaultObserver;
import io.reactivex.Observable;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IMyApi {
    //http://localhost:5000/api/register
    @POST("api/register")
    Observable<String> registerUser(@Body UserData user);
    @POST("api/login")
    Observable<String> loginUser(@Body LoginUser user);

}
