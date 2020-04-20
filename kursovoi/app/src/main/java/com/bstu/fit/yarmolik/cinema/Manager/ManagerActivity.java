package com.bstu.fit.yarmolik.cinema.Manager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bstu.fit.yarmolik.cinema.Login;
import com.bstu.fit.yarmolik.cinema.R;

public class ManagerActivity extends AppCompatActivity {
Toolbar toolbar;
Fragment currentFragment = null;
FragmentTransaction ft;
    final int REQUEST_CODE_GALLERY = 999;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_manager);
        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("Magnifisent");
        setSupportActionBar(toolbar);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.manager_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(!item.isChecked()) item.setChecked(true);
        switch(id){
            case R.id.add_film :
                currentFragment = new ManagerFilmFragment();
                ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.managerContainer, currentFragment);
                ft.addToBackStack(null);
                ft.commit();
                return true;
            case R.id.delete_film :
                currentFragment = new DeleteFilmFragment();
                ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.managerContainer, currentFragment);
                ft.addToBackStack(null);
                ft.commit();
                return true;
            case R.id.change_film :
                currentFragment = new UpdateFilmFragment();
                ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.managerContainer, currentFragment);
                ft.addToBackStack(null);
                ft.commit();
                return true;
            case R.id.add_seance :
                currentFragment = new AddSeanceFragment();
                ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.managerContainer, currentFragment);
                ft.addToBackStack(null);
                ft.commit();
                return true;
            case R.id.action_exit:
                Intent intent= new Intent(this, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                finish();
        }
        return false;
    }


}
