package com.bstu.fit.yarmolik.cinema.Manager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bstu.fit.yarmolik.cinema.R;
import com.bstu.fit.yarmolik.cinema.Remote.IMyApi;
import com.bstu.fit.yarmolik.cinema.Remote.RetrofitClient;
import com.bstu.fit.yarmolik.cinema.Responces.FilmResponse;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UpdateFilmFragment extends Fragment {
    private ArrayList<String> nameList;
    private ArrayList<Integer> idList;
    private String choose="";
    private IMyApi iMyApi;
    private EditText genre,description,country,duration,year;
    private Button updateButton;
    private ImageView imageView;
    private Bitmap poster;
    private MaterialSpinner spinner;
    private List<FilmResponse> posts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_update_film, container, false);
        init(view);
        loadFilms();
        spinner.setItems(nameList);
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                    choose = item;
                    int chooseIndex = nameList.indexOf(item);
                    loadFilm(idList.get(chooseIndex));
            }
        });
        return view;
    }
    private void loadFilms(){
        Call<List<FilmResponse>> call=iMyApi.getFilms();
        call.enqueue(new Callback<List<FilmResponse>>() {
            @Override
            public void onResponse(Call<List<FilmResponse>> call, Response<List<FilmResponse>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getContext(), response.code(), Toast.LENGTH_LONG).show();
                }
                posts=response.body();
                for(FilmResponse post : posts){
                    nameList.add(post.getName());
                    idList.add(post.getId());
                }
            }
            @Override
            public void onFailure(Call<List<FilmResponse>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    private void loadFilm(int id){
        Call<FilmResponse> call=iMyApi.getFilm(id);
        call.enqueue(new Callback<FilmResponse>() {
            @Override
            public void onResponse(Call<FilmResponse> call, Response<FilmResponse> response) {
                year.setText(response.body().getYear().toString());
                genre.setText(response.body().getGenre());
                description.setText(response.body().getDescription());
                country.setText(response.body().getCountry());
                duration.setText(response.body().getDuration().toString());
                byte[] image = response.body().getPoster().getBytes();
                imageView.setImageBitmap(getImage(image));

            }

            @Override
            public void onFailure(Call<FilmResponse> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    private void init(View view){
        iMyApi= RetrofitClient.getInstance().create(IMyApi.class);
        spinner= view.findViewById(R.id.spinnerUpdate);
        updateButton=view.findViewById(R.id.updateFilm);
        nameList= new ArrayList<String>();
        idList=new ArrayList<Integer>();
        genre=view.findViewById(R.id.updateGenreOfFilm);
        description=view.findViewById(R.id.updateDescriptionOfFilm);
        country=view.findViewById(R.id.updateCountryOfFilm);
        duration=view.findViewById(R.id.updateDurationOfFilm);
        year=view.findViewById(R.id.updateYearOfFilm);
        imageView=view.findViewById(R.id.updateImageView);
    }
    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    // convert from byte array to bitmap
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }
}
