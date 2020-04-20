package com.bstu.fit.yarmolik.cinema.Manager;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bstu.fit.yarmolik.cinema.Login;
import com.bstu.fit.yarmolik.cinema.MainActivity;
import com.bstu.fit.yarmolik.cinema.Model.FilmInfo;
import com.bstu.fit.yarmolik.cinema.R;
import com.bstu.fit.yarmolik.cinema.Remote.IMyApi;
import com.bstu.fit.yarmolik.cinema.Remote.RetrofitClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import dmax.dialog.SpotsDialog;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import static android.app.Activity.RESULT_OK;


public class ManagerFilmFragment extends Fragment {
    EditText nameOfTheFilm,yearOfTheFilm,durationOfTheFilm,countryOfTheFilm,genreOfTheFilm,descriptionOfTheFilm;
    Button posterOfTheFilm,addFilm;
    ImageView mImageview;
    IMyApi iMyApi;
    Bitmap bitmapImage = null;
    Intent intent;
    String vitalikLox="vitalik lox";
    byte[] s=vitalikLox.getBytes();
    CompositeDisposable compositeDisposable;
    final int REQUEST_CODE_GALLERY = 999;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View view= inflater.inflate(R.layout.fragment_manager_film, container, false);
     init(view);
        iMyApi= RetrofitClient.getInstance().create(IMyApi.class);
      posterOfTheFilm.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              if(ActivityCompat.checkSelfPermission(getActivity(),
                      Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
              {
                  requestPermissions(
                          new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                          2000);
              }
              else {
                  startGallery();
              }
          }
      });
      addFilm.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              try {
                  AlertDialog alertDialog = new SpotsDialog.Builder()
                          .setContext(getContext())
                          .build();
                  alertDialog.show();
                  FilmInfo film = new FilmInfo(nameOfTheFilm.getText().toString(),
                          Integer.parseInt(yearOfTheFilm.getText().toString()),
                          countryOfTheFilm.getText().toString(),
                          Integer.parseInt(durationOfTheFilm.getText().toString()),
                          genreOfTheFilm.getText().toString(),
                          descriptionOfTheFilm.getText().toString(),
                          convertToBase64(bitmapImage)
                  );
                  compositeDisposable.add(iMyApi.filmInfo(film)
                          .subscribeOn(Schedulers.io())
                          .observeOn(AndroidSchedulers.mainThread())
                          .subscribe(s -> {
                              alertDialog.dismiss();
                              Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
                          }, throwable -> {
                              alertDialog.dismiss();
                              Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                          })
                  );
              }
              catch (Exception ex){
                  Toast.makeText(getContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
              }
          }
      });
      return view;
    }
    private void startGallery() {
        Intent cameraIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        cameraIntent.setType("image/*");
        if (cameraIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(cameraIntent, 1000);
        }
    }
    public void init(View view){
        nameOfTheFilm=view.findViewById(R.id.addNameOfFilm);
        yearOfTheFilm=view.findViewById(R.id.addYearOfFilm);
        durationOfTheFilm=view.findViewById(R.id.addDurationOfFilm);
        countryOfTheFilm=view.findViewById(R.id.addCountryOfFilm);
        genreOfTheFilm=view.findViewById(R.id.addGenreOfFilm);
        posterOfTheFilm=view.findViewById(R.id.addPosterOfFilm);
        descriptionOfTheFilm=view.findViewById(R.id.addDescriptionOfFilm);
        addFilm=view.findViewById(R.id.addFilm);
        mImageview=view.findViewById(R.id.imageView);
        compositeDisposable=new CompositeDisposable();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super method removed
        if (resultCode == RESULT_OK) {
            if (requestCode == 1000) {
                Uri returnUri = data.getData();

                try {
                    bitmapImage = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), returnUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mImageview.setImageBitmap(bitmapImage);
            }
        }
    }

    /*private byte[] imageViewToByte(ImageView image){
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }*/
    public byte[] convertToBase64(Bitmap bitmap) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,os);
        byte[] byteArray = os.toByteArray();
        return byteArray;
    }
    }
