package com.bstu.fit.yarmolik.cinema.Fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bestsoft32.tt_fancy_gif_dialog_lib.TTFancyGifDialog;
import com.bestsoft32.tt_fancy_gif_dialog_lib.TTFancyGifDialogListener;
import com.bstu.fit.yarmolik.cinema.Model.CheckSeance;
import com.bstu.fit.yarmolik.cinema.R;
import com.bstu.fit.yarmolik.cinema.Remote.IMyApi;
import com.bstu.fit.yarmolik.cinema.Remote.RetrofitClient;
import com.bstu.fit.yarmolik.cinema.Responces.FilmResponse;
import com.squareup.picasso.Picasso;

import dmax.dialog.SpotsDialog;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class InfoFilmFragment extends Fragment implements OnFragmentBookListener {
    private TextView nameFilm,genreFilm,yearFilm,descriptionFilm,durationFilm,countryFilm;
    private ImageView imageView;
    FragmentTransaction ft;
    private String idFilmInfo="",posterFilm;
    private IMyApi iMyApi;
    Button button;
    private OnFragmentBookListener onFragmentBookListener;
    private CompositeDisposable compositeDisposable;
    @Override
    public void onOpenFragmentS(String idFilm) {
       Fragment currentFragment = new InfoFilmFragment();
        Bundle bundle = new Bundle();
        bundle.putString("idFilmCurrentInfo", idFilm);
        currentFragment.setArguments(bundle);
        ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.recyclerFragmentFilm, currentFragment);
        ft.addToBackStack(null);
        ft.commit();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view =inflater.inflate(R.layout.fragment_info_film, container, false);
        init(view);
        loadFilm(idFilmInfo);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckSeance checkSeances= new CheckSeance(idFilmInfo);
                compositeDisposable.add(iMyApi.checkSeance(checkSeances)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<String>() {
                            @Override
                            public void accept(String s) throws Exception {
                                //Toast.makeText(getContext(),s, Toast.LENGTH_LONG).show();
                                if(s.equals("OK")){
                                    Intent intent=new Intent(getContext(), AddInfoTicket.class);
                                    intent.putExtra("idFilmFragment",idFilmInfo);
                                    intent.putExtra("posterFilmFragment",posterFilm);
                                   startActivity(intent);
                                }
                                else{
                                    new TTFancyGifDialog.Builder(getActivity())
                                            .setTitle("Magnifisent")
                                            .setMessage("Администрация приносит свои извинения в связи с отсутствием сеансов на данный фильм. Если хотите перейти к списку фильмов нажмите ОК")
                                            .setPositiveBtnText("Ok")
                                            .setPositiveBtnBackground("#22b573")
                                            .setNegativeBtnText("Cancel")
                                            .setNegativeBtnBackground("#c1272d")
                                            .setGifResource(R.drawable.cinema2)      //pass your gif, png or jpg
                                            .isCancellable(true)
                                            .OnPositiveClicked(new TTFancyGifDialogListener() {
                                                @Override
                                                public void OnClick() {
                                                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                                                    Fragment myFragment = new FilmFragment();
                                                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.recyclerFragmentFilm, myFragment).addToBackStack(null).detach(myFragment).commit();
                                                }
                                            })
                                            .OnNegativeClicked(new TTFancyGifDialogListener() {
                                                @Override
                                                public void OnClick() {
                                                    //Toast.makeText(MainActivity.this,"Cancel",Toast.LENGTH_SHORT).show();
                                                }
                                            })
                                            .build();
                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                ));
            }
        });
        return view;
    }
    private void init(View view){
        iMyApi= RetrofitClient.getInstance().create(IMyApi.class);
        nameFilm=view.findViewById(R.id.nameInfoTextView);
        genreFilm=view.findViewById(R.id.genreInfoTextView);
        yearFilm=view.findViewById(R.id.yearInfoTextView);
        descriptionFilm=view.findViewById(R.id.descriptionInfoTextView);
        durationFilm=view.findViewById(R.id.durationInfoTextView);
        countryFilm=view.findViewById(R.id.countryInfoTextView);
        imageView=view.findViewById(R.id.imageView);
        idFilmInfo=getArguments().getString("idFilmAdapter");
        compositeDisposable= new CompositeDisposable();
        button=view.findViewById(R.id.bookTicket);
    }
    private void loadFilm(String id){
        AlertDialog alertDialog = new SpotsDialog.Builder()
                .setContext(getContext())
                .build();
        alertDialog.show();
        Call<FilmResponse> call=iMyApi.getFilm(id);
        call.enqueue(new Callback<FilmResponse>() {
            @Override
            public void onResponse(Call<FilmResponse> call, Response<FilmResponse> response) {
                yearFilm.setText(response.body().getYear().toString());
                genreFilm.setText(response.body().getGenre());
                nameFilm.setText(response.body().getName());
                descriptionFilm.setText(response.body().getDescription());
                countryFilm.setText(response.body().getCountry());
                durationFilm.setText(response.body().getDuration().toString()+" минут");
                posterFilm=response.body().getPoster();
                Picasso.get().load(response.body().getPoster()).into(imageView);
                alertDialog.dismiss();
            }

            @Override
            public void onFailure(Call<FilmResponse> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
