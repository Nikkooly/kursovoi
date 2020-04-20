package com.bstu.fit.yarmolik.cinema.Remote;

import com.bstu.fit.yarmolik.cinema.Model.FilmInfo;
import com.bstu.fit.yarmolik.cinema.Model.LoginUser;
import com.bstu.fit.yarmolik.cinema.Model.UserData;
import com.bstu.fit.yarmolik.cinema.Responces.FilmResponse;

import java.util.List;

import  io.reactivex.observers.DefaultObserver;
import io.reactivex.Observable;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IMyApi {
    //http://localhost:5000/api/register
    @POST("api/register")
    Observable<String> registerUser(@Body UserData user);
    @POST("api/login")
    Observable<String> loginUser(@Body LoginUser user);
    @POST("api/films")
    Observable<String> filmInfo(@Body FilmInfo film);
    @GET("api/films")
    Call<List<FilmResponse>> getFilms();
    @DELETE("api/films/{id}")
    Call<Void> deletePost(@Path("id") int id);
    @GET("api/films/{id}")
    Call<FilmResponse> getFilm(@Path("id") int id);

}
