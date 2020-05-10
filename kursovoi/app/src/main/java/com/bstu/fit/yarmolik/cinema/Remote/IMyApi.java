package com.bstu.fit.yarmolik.cinema.Remote;

import com.bstu.fit.yarmolik.cinema.Model.CheckSeance;
import com.bstu.fit.yarmolik.cinema.Model.CinemaInfo;
import com.bstu.fit.yarmolik.cinema.Model.FilmInfo;
import com.bstu.fit.yarmolik.cinema.Model.HallInfo;
import com.bstu.fit.yarmolik.cinema.Model.LoginUser;
import com.bstu.fit.yarmolik.cinema.Model.Seance;
import com.bstu.fit.yarmolik.cinema.Model.SeanceDate;
import com.bstu.fit.yarmolik.cinema.Model.Tickets;
import com.bstu.fit.yarmolik.cinema.Model.UserData;
import com.bstu.fit.yarmolik.cinema.Responces.CinemaResponce;
import com.bstu.fit.yarmolik.cinema.Responces.FilmResponse;
import com.bstu.fit.yarmolik.cinema.Responces.HallResponse;
import com.bstu.fit.yarmolik.cinema.Responces.SeanceDateResponse;
import com.bstu.fit.yarmolik.cinema.Responces.TicketResponse;

import java.util.List;

import io.reactivex.Observable;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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
    Call<Void> deletePost(@Path("id") String id);
    @GET("api/films/{id}")
    Call<FilmResponse> getFilm(@Path("id") String id);
    @PUT("api/films/{id}")
    Call<FilmInfo> updateFilm(@Path("id") String id, @Body FilmInfo film);
    @POST("api/cinema")
    Observable<String> addCinema(@Body CinemaInfo cinema);
    @DELETE("api/cinema/{id}")
    Call<Void> deleteCinema(@Path("id") String id);
    @GET("api/cinema")
    Call<List<CinemaResponce>> getCinema();
    @GET("api/cinema/{id}")
    Call<CinemaResponce> getCinemaInfo(@Path("id") String id);
    @PUT("api/cinema/{id}")
    Call<CinemaInfo> updateCinema(@Path("id") String id, @Body CinemaInfo cinema);
    @POST("api/hall")
    Observable<String> addHall(@Body HallInfo hall);
    @GET("api/hall/{id}")
    Call<List<HallResponse>> getHallInfo(@Path("id") String id);
    @DELETE("api/hall/{id}")
    Call<Void> deleteHall(@Path("id") String id);
    @POST("api/seance")
    Observable<String> addSeance(@Body Seance seance);
    @POST("api/tickets")
    Observable<String> addTickets(@Body Tickets tickets);
    @POST("api/check")
    Observable<String> checkSeance(@Body CheckSeance check);
    @GET("api/seance/{id}")
    Call<List<CinemaResponce>> getCinemaId(@Path("id") String id);
    @POST("api/data")
    Call<List<SeanceDateResponse>> infoSeanceLoad(@Body SeanceDate seanceDate);
    @GET("api/tickets/{id}")
    Call<List<TicketResponse>> getTickets(@Path("id") String id);
}
