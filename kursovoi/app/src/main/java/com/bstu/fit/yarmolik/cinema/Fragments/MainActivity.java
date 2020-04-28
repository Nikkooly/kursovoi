package com.bstu.fit.yarmolik.cinema.Fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bstu.fit.yarmolik.cinema.Fragments.CinemaFragment;
import com.bstu.fit.yarmolik.cinema.Fragments.FilmFragment;
import com.bstu.fit.yarmolik.cinema.Fragments.FragmentMore;
import com.bstu.fit.yarmolik.cinema.Fragments.SliderFragment;
import com.bstu.fit.yarmolik.cinema.Fragments.TicketFragment;
import com.bstu.fit.yarmolik.cinema.Manager.AddPlacesSeanceFragment;
import com.bstu.fit.yarmolik.cinema.Manager.ManagerActivity;
import com.bstu.fit.yarmolik.cinema.Model.LoginUser;
import com.bstu.fit.yarmolik.cinema.R;
import com.bstu.fit.yarmolik.cinema.Remote.IMyApi;
import com.bstu.fit.yarmolik.cinema.Remote.RetrofitClient;
import com.bstu.fit.yarmolik.cinema.Responces.CinemaResponce;
import com.bstu.fit.yarmolik.cinema.Responces.FilmResponse;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity   {
SliderFragment sliderFragment;
    private Toolbar toolbar;
    Fragment currentFragment = null;
    FragmentTransaction ft;
    public IMyApi iMyApi;
    public List<FilmResponse> film;
    public ArrayList<String> nameList,countryList,descriptionList,posterList,genreList,idList;
    public ArrayList<Integer> durationList,yearList;
    CompositeDisposable compositeDisposable;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        iMyApi= RetrofitClient.getInstance().create(IMyApi.class);
        //getFilms();
        //Toast.makeText(this,nameList.get(0),Toast.LENGTH_SHORT).show();
        ft = getSupportFragmentManager().beginTransaction();
        currentFragment = new SliderFragment();
        ft.replace(R.id.container, currentFragment);
        ft.commit();
        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.bottombaritem_popular:
                                currentFragment = new SliderFragment();
                                ft = getSupportFragmentManager().beginTransaction();
                                ft.replace(R.id.container, currentFragment);
                                ft.addToBackStack(null);
                                ft.commit();
                                return true;
                            case R.id.bottombaritem_films:
                                currentFragment = new FilmFragment();
                                ft = getSupportFragmentManager().beginTransaction();
                                ft.replace(R.id.container, currentFragment);
                                ft.addToBackStack(null);
                                ft.commit();
                                return true;
                            case R.id.bottombaritem_cinemas:
                                currentFragment = new CinemaFragment();
                                ft = getSupportFragmentManager().beginTransaction();
                                ft.replace(R.id.container, currentFragment);
                                ft.addToBackStack(null);
                                ft.commit();
                                return true;
                            case R.id.bottombaritem_tickets:
                                currentFragment = new TicketFragment();
                                ft = getSupportFragmentManager().beginTransaction();
                                ft.replace(R.id.container, currentFragment);
                                ft.addToBackStack(null);
                                ft.commit();
                                return true;
                            case R.id.bottombaritem_more:
                                currentFragment = new FragmentMore();
                                ft = getSupportFragmentManager().beginTransaction();
                                ft.replace(R.id.container, currentFragment);
                                ft.commit();
                                return true;
                        }
                        return false;
                    }
                });
    }

        public void init(){
            nameList= new ArrayList<String>();
            idList=new ArrayList<String>();
            genreList=new ArrayList<String>();
            descriptionList=new ArrayList<String>();
            yearList=new ArrayList<Integer>();
            durationList=new ArrayList<Integer>();
            posterList=new ArrayList<String>();
            countryList= new ArrayList<String>();
        }
}