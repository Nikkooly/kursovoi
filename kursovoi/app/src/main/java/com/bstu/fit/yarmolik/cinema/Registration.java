package com.bstu.fit.yarmolik.cinema;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bstu.fit.yarmolik.cinema.LocalDataBase.DbHelper;
import com.bstu.fit.yarmolik.cinema.Model.LoginUser;
import com.bstu.fit.yarmolik.cinema.Model.UserData;
import com.bstu.fit.yarmolik.cinema.Remote.IMyApi;
import com.bstu.fit.yarmolik.cinema.Remote.RetrofitClient;
import com.google.android.material.textfield.TextInputLayout;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import dmax.dialog.SpotsDialog;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class Registration extends AppCompatActivity {
    TextInputLayout mUsernameLayout;
    EditText password, email, login;
    private static int counter = 0;
    private byte[] key;
    IMyApi iMyApi;
    Button register;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_registration);
        init();
        iMyApi = RetrofitClient.getInstance().create(IMyApi.class);
        Intent intent = new Intent(Registration.this, Login.class);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginValue = login.getText().toString();
                String emailValue = email.getText().toString();
                String passwordValue = password.getText().toString();
                boolean checkLogin = loginValue.matches("^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$");
                boolean checkEmail = emailValue.matches("^([A-Za-z0-9_-]+\\.)*[A-Za-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$");
                boolean checkPassword = passwordValue.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,}$");
                if (checkPassword == true && checkEmail == true && checkLogin == true) {

                    AlertDialog alertDialog = new SpotsDialog.Builder()
                            .setContext(Registration.this)
                            .build();
                    alertDialog.show();
                    try {
                        byte[] keyStart = "Magnifisent".getBytes();
                        KeyGenerator kgen = KeyGenerator.getInstance("AES");
                        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
                        sr.setSeed(keyStart);
                        kgen.init(128, sr); // 192 and 256 bits may not be available
                        SecretKey skey = kgen.generateKey();
                        key = skey.getEncoded();
                    UserData user = new UserData(login.getText().toString(), email.getText().toString(), md5(password.getText().toString()), 1);
                    compositeDisposable.add(iMyApi.registerUser(user)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(s -> {
                                if(s.equals("User exists")){
                                    alertDialog.dismiss();
                                    Toast.makeText(Registration.this, "Пользователь уже существует", Toast.LENGTH_SHORT).show();
                                }
                                else if(s.equals("Email exists")){
                                    alertDialog.dismiss();
                                    Toast.makeText(Registration.this, "Пользователь с таким email уже существует", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    alertDialog.dismiss();
                                    dbHelper.insertUserData(s,login.getText().toString(),email.getText().toString(),md5(password.getText().toString()));
                                    Toast.makeText(Registration.this, s, Toast.LENGTH_SHORT).show();
                                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                                    startActivity(intent);
                                }

                                //finish();
                            }, throwable -> {
                                alertDialog.dismiss();
                                Toast.makeText(Registration.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                            })
                    );
                    }
                    catch (Exception ex){
                        Toast.makeText(Registration.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Registration.this, "Некорректные данные", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void init() {
        login = findViewById(R.id.loginUserEditText);
        email = findViewById(R.id.mailUserEditText);
        password = findViewById(R.id.passwordUserEditText);
        register = findViewById(R.id.registerButton);
        dbHelper = new DbHelper(this, "project.db", null, 1);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
    }

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    public static String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
