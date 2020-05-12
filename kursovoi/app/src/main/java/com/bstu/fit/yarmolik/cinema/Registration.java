package com.bstu.fit.yarmolik.cinema;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bstu.fit.yarmolik.cinema.Model.LoginUser;
import com.bstu.fit.yarmolik.cinema.Model.UserData;
import com.bstu.fit.yarmolik.cinema.Remote.IMyApi;
import com.bstu.fit.yarmolik.cinema.Remote.RetrofitClient;
import com.google.android.material.textfield.TextInputLayout;

import dmax.dialog.SpotsDialog;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class Registration extends AppCompatActivity  {
    TextInputLayout mUsernameLayout;
    EditText password,email,login;
    private static int counter=0;
    IMyApi iMyApi;
    Button register;
    CompositeDisposable compositeDisposable=new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        init();
        iMyApi= RetrofitClient.getInstance().create(IMyApi.class);
        Intent intent=new Intent(Registration.this,Login.class);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginValue = login.getText().toString();
                String emailValue = email.getText().toString();
                String passwordValue = password.getText().toString();
                boolean checkLogin = loginValue.matches("^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$");
                boolean checkEmail = emailValue.matches("^([A-Za-z0-9_-]+\\.)*[A-Za-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$");
                boolean checkPassword = passwordValue.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,}$");
                if (checkPassword == true && checkEmail==true && checkLogin==true) {

                    AlertDialog alertDialog = new SpotsDialog.Builder()
                            .setContext(Registration.this)
                            .build();
                    alertDialog.show();
                    UserData user = new UserData(login.getText().toString(), email.getText().toString(), password.getText().toString(), 1);
                    compositeDisposable.add(iMyApi.registerUser(user)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(s -> {
                                if (s.equals("Register succesfully")) {

                                }
                                alertDialog.dismiss();
                                Toast.makeText(Registration.this, s, Toast.LENGTH_SHORT).show();
                                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                                startActivity(intent);
                                //finish();
                            }, throwable -> {
                                alertDialog.dismiss();
                                Toast.makeText(Registration.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                            })
                    );
                }
                else{
                    Toast.makeText(Registration.this, "Некорректные данные", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
public void init(){
        login=findViewById(R.id.loginUserEditText);
        email=findViewById(R.id.mailUserEditText);
        password=findViewById(R.id.passwordUserEditText);
        register=findViewById(R.id.registerButton);
}
    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

}
