package com.bstu.fit.yarmolik.cinema;

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
import com.bstu.fit.yarmolik.cinema.Manager.ManagerActivity;
import com.bstu.fit.yarmolik.cinema.Model.LoginUser;
import com.bstu.fit.yarmolik.cinema.Remote.IMyApi;
import com.bstu.fit.yarmolik.cinema.Remote.RetrofitClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import dmax.dialog.SpotsDialog;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity  {
SliderFragment sliderFragment;
    private Toolbar toolbar;
    Fragment currentFragment = null;
    FragmentTransaction ft;
    IMyApi iMyApi;
    CompositeDisposable compositeDisposable;
    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ft = getSupportFragmentManager().beginTransaction();
        iMyApi= RetrofitClient.getInstance().create(IMyApi.class);
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
    public void getFilms(){
        AlertDialog alertDialog = new SpotsDialog.Builder()
                .setContext(MainActivity.this)
                .build();
        alertDialog.show();
        Toast.makeText(MainActivity.this,  iMyApi.getFilms().toString(), Toast.LENGTH_LONG).show();

       /* compositeDisposable.add(iMyApi.getFilms()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    alertDialog.dismiss();
                    Toast.makeText(Login.this, s, Toast.LENGTH_LONG).show();
                    Integer equal=1;
                    Integer secondequal=2;
                    if(s.toLowerCase().contains(equal.toString()))
                    {
                        Toast.makeText(Login.this, "Уже заходим...", Toast.LENGTH_LONG).show();
                        intent=new Intent(Login.this,MainActivity.class);
                        startActivity(intent);
                    }
                    else if(s.toLowerCase().contains(secondequal.toString())){
                        intent=new Intent(Login.this, ManagerActivity.class);
                        startActivity(intent);
                    }
                }, throwable -> {
                    alertDialog.dismiss();
                    Toast.makeText(Login.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                })
        );*/
    }
}