package com.bstu.fit.yarmolik.cinema.Fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bstu.fit.yarmolik.cinema.R;
import com.bstu.fit.yarmolik.cinema.Remote.IMyApi;
import com.bstu.fit.yarmolik.cinema.Remote.RetrofitClient;
import com.bstu.fit.yarmolik.cinema.Responces.CinemaResponce;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddInfoTicket extends AppCompatActivity {
private TextView name;
private ImageView imageView;
private String poster;
private String idFilm;
private MaterialSpinner spinner;
private ArrayList<String> idCinema,nameCinema,addressCinema,infoCinema;
private List<CinemaResponce> cinema;
private IMyApi iMyApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add_info_ticket);
        init();
        Bundle arguments = getIntent().getExtras();
        idFilm=arguments.get("idFilmFragment").toString();

        loadCinema(idFilm);
        spinner.setItems(infoCinema);
        //name.setText(arguments.get("idFilmFragment").toString());
    }
    private void init(){
        idCinema=new ArrayList<String>();
        nameCinema=new ArrayList<String>();
        addressCinema=new ArrayList<String>();
        spinner=findViewById(R.id.spinnerCinemaBook);
        infoCinema=new ArrayList<String>();
        iMyApi= RetrofitClient.getInstance().create(IMyApi.class);
    }
    private void loadCinema(String id){
        Call<List<CinemaResponce>> call=iMyApi.getCinemaId(id);
        call.enqueue(new Callback<List<CinemaResponce>>() {
            @Override
            public void onResponse(Call<List<CinemaResponce>> call, Response<List<CinemaResponce>> response) {
                cinema=response.body();
                for(CinemaResponce post:cinema){
                    idCinema.add(post.getId());
                    nameCinema.add(post.getName());
                    addressCinema.add(post.getAdress());
                    Toast.makeText(AddInfoTicket.this, nameCinema.get(0), Toast.LENGTH_LONG).show();
                    for(int i=0;i<idCinema.size();i++){
                        infoCinema.add(i,nameCinema.get(i)+"\n"+addressCinema.get(i));
                    }
                }
            }

            @Override
            public void onFailure(Call<List<CinemaResponce>> call, Throwable t) {
                Toast.makeText(AddInfoTicket.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
