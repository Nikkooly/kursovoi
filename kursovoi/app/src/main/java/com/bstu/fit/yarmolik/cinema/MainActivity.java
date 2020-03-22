package com.bstu.fit.yarmolik.cinema;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
SliderFragment sliderFragment;
    Fragment currentFragment = null;
    FragmentTransaction ft;
    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                                return true;
                            case R.id.bottombaritem_cinemas:
                                // TODO
                                return true;
                            case R.id.bottombaritem_tickets:
                                // TODO
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
}