package com.bstu.fit.yarmolik.cinema.Manager;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bstu.fit.yarmolik.cinema.Model.Seance;
import com.bstu.fit.yarmolik.cinema.Model.Tickets;
import com.bstu.fit.yarmolik.cinema.R;
import com.bstu.fit.yarmolik.cinema.Remote.IMyApi;
import com.bstu.fit.yarmolik.cinema.Remote.RetrofitClient;
import com.bstu.fit.yarmolik.cinema.Responces.HallResponse;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPlacesSeanceFragment extends Fragment {
private TextView  filmName,cinemaName, hallName, countPlaces;
private String film, cinema, hall,time,date,idHallSeance,idHallFilm;
private Integer countOfPlaces;
private Button addPlaces;
private ArrayList<Integer> placesList;
private List<HallResponse> hallResponse;
private String select="All";
private EditText priceText;
private RadioButton buttonAll,buttonHalf;
CompositeDisposable compositeDisposable;
IMyApi iMyApi;
RadioGroup radioGroup;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view =inflater.inflate(R.layout.fragment_add_places_seance, container, false);
       init(view);
       filmName.setText(film);
       cinemaName.setText(cinema);
       hallName.setText(hall);
      countPlaces.setText(countOfPlaces.toString());
      radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(RadioGroup radioGroup, int i) {
              switch (i){
                  case R.id.allPlaces:
                      select="All";
                      break;
                  case R.id.halfPlaces:
                      select="Half";
                      break;
              }
              Toast.makeText(getContext(),select,Toast.LENGTH_SHORT).show();
          }
      });
      addPlaces.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              AlertDialog alertDialog = new SpotsDialog.Builder()
                      .setContext(getContext())
                      .build();
              alertDialog.show();
              Seance seance=new Seance(date,time,idHallSeance,idHallFilm);
              compositeDisposable.add(iMyApi.addSeance(seance)
                      .subscribeOn(Schedulers.io())
                      .observeOn(AndroidSchedulers.mainThread())
                      .subscribe(new Consumer<String>() {
                          @Override
                          public void accept(String s) throws Exception {
                              Toast.makeText(getContext(),s,Toast.LENGTH_SHORT).show();
                              if(!s.equals("Сеанс уже существует")){
                                  if(!priceText.getText().toString().equals("")){
                                      Double price=Double.parseDouble(priceText.getText().toString());
                                      if(select.equals("All")){
                                          for(int i=1;i<=price;i++){
                                              addTicketsInfo(i,price,s);
                                          }
                                      }
                                      else{
                                          for(int i=1;i<=price;i+=2){
                                              addTicketsInfo(i,price,s);
                                          }
                                      }
                                  }
                                  alertDialog.dismiss();
                              }
                          }
                      }, new Consumer<Throwable>() {
                          @Override
                          public void accept(Throwable throwable) throws Exception {
                              alertDialog.dismiss();
                              Toast.makeText(getContext(), throwable.getMessage(),Toast.LENGTH_SHORT).show();
                          }
                      })
              );


          }
      });
       return view;
    }
    private void addTicketsInfo(Integer place, Double price, String s){
            Tickets tickets = new Tickets(place,price,s,false);
            compositeDisposable.add(iMyApi.addTickets(tickets)
            .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<String>() {
                        @Override
                        public void accept(String s) throws Exception {
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                        }
                    })
            );
        /*compositeDisposable.add(iMyApi.addSeance(seance)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Toast.makeText(getContext(),s,Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        alertDialog.dismiss();
                        Toast.makeText(getContext(), throwable.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                })
        );*/
    }
    private void init(View view){
        filmName=view.findViewById(R.id.textView20);
        cinemaName=view.findViewById(R.id.textView8);
        hallName=view.findViewById(R.id.textView15);
        countPlaces=view.findViewById(R.id.textView17);
        film=getArguments().getString("nameFilm");
        cinema=getArguments().getString("nameCinema");
        hall=getArguments().getString("nameHall");
        countOfPlaces=getArguments().getInt("countPlaces");
        date=getArguments().getString("dateSeance");
        time=getArguments().getString("timeSeance");
        idHallFilm=getArguments().getString("idFilmSeance");
        idHallSeance=getArguments().getString("idHallSeance");
        addPlaces=view.findViewById(R.id.addPlaces);
        buttonAll=view.findViewById(R.id.allPlaces);
        buttonHalf=view.findViewById(R.id.halfPlaces);
        radioGroup=view.findViewById(R.id.radios);
        compositeDisposable=new CompositeDisposable();
        iMyApi= RetrofitClient.getInstance().create(IMyApi.class);
        priceText=view.findViewById(R.id.editText2);
        placesList=new ArrayList<Integer>();
    }

}
