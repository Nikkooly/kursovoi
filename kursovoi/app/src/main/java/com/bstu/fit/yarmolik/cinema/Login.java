package com.bstu.fit.yarmolik.cinema;

import android.animation.Animator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bstu.fit.yarmolik.cinema.Fragments.MainActivity;
import com.bstu.fit.yarmolik.cinema.Manager.ManagerActivity;
import com.bstu.fit.yarmolik.cinema.Model.LoginUser;
import com.bstu.fit.yarmolik.cinema.Remote.IMyApi;
import com.bstu.fit.yarmolik.cinema.Remote.RetrofitClient;
import com.bstu.fit.yarmolik.cinema.Responces.FilmResponse;
import com.bstu.fit.yarmolik.cinema.Responces.GuestResponse;
import com.bstu.fit.yarmolik.cinema.Responces.UserResponce;

import java.util.List;

import dmax.dialog.SpotsDialog;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class Login extends AppCompatActivity {

    private ImageView bookIconImageView;
    private TextView bookITextView;
    private EditText login,password;
    Button btn_login;
    List<FilmResponse> posts;
    String response="";
    public boolean stateInternet;
    private ProgressBar loadingProgressBar;
    private RelativeLayout rootView, afterAnimationView;
    IMyApi iMyApi;
    public static Integer userRoleId,guestRoleId;
    public static String userId,guestId,userEmail,guestEmail,userLogin,guestLogin;
    Intent intent;
    CompositeDisposable compositeDisposable;
    CheckInternetConnection checkInternetConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        compositeDisposable=new CompositeDisposable();
        iMyApi= RetrofitClient.getInstance().create(IMyApi.class);
        // requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        initViews();

        new CountDownTimer(3000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                bookITextView.setVisibility(VISIBLE);
                rootView.setBackgroundColor(ContextCompat.getColor(Login.this, R.color.colorBackground));
                bookIconImageView.setImageResource(R.drawable.app_icon);
            }

            @Override
            public void onFinish() {
                bookITextView.setVisibility(GONE);
                rootView.setBackgroundColor(ContextCompat.getColor(Login.this, R.color.btnColor));
                startAnimation();
                if(checkInternetConnection.isOnline(Login.this)){
                    stateInternet=true;
                }
                else{
                    stateInternet=false;
                    AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                    builder.setTitle("Важное сообщение!")
                            .setMessage("Отсутствует подключение к интернету, многие функции будут не доступны. Включите интернет и перезайдите в приложение!")
                            .setIcon(R.drawable.app_icon)
                            .setPositiveButton("ОК", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // Закрываем окно
                                    dialog.cancel();
                                }
                            });
                    builder.create();
                    builder.show();
                }
            }
        }.start();
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginValue = login.getText().toString();
                String passwordValue = password.getText().toString();
                boolean checkLogin = loginValue.matches("^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$");
                boolean checkPassword = passwordValue.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,}$");
                if (checkPassword == true && checkLogin == true) {
                    if(stateInternet) {
                        AlertDialog alertDialog = new SpotsDialog.Builder()
                                .setContext(Login.this)
                                .build();
                        alertDialog.show();
                        LoginUser user = new LoginUser(login.getText().toString(), Registration.md5(password.getText().toString()));
                        Call<List<UserResponce>> call = iMyApi.checkLogin(user);
                        call.enqueue(new Callback<List<UserResponce>>() {
                            @Override
                            public void onResponse(Call<List<UserResponce>> call, Response<List<UserResponce>> response) {
                                    for (UserResponce userResponce : response.body()) {
                                        //Toast.makeText(Login.this, response.body().size(), Toast.LENGTH_LONG).show();
                                        //Toast.makeText(Login.this, userResponce.toString(), Toast.LENGTH_LONG).show();
                                        userId = userResponce.getId();
                                        userRoleId = userResponce.getRoleId();
                                        userEmail = userResponce.getEmail();
                                        userLogin = userResponce.getLogin();
                                        //Toast.makeText(Login.this, userRoleId.toString(), Toast.LENGTH_LONG).show();
                                        if (userRoleId == 1) {
                                            alertDialog.dismiss();
                                            //Toast.makeText(Login.this, "Уже заходим...", Toast.LENGTH_LONG).show();
                                            intent = new Intent(Login.this, MainActivity.class);
                                            intent.putExtra("stateInternetConnection", stateInternet);
                                            startActivity(intent);
                                            clear();
                                        } else if (userRoleId == 2) {
                                            alertDialog.dismiss();
                                            intent = new Intent(Login.this, ManagerActivity.class);
                                            startActivity(intent);
                                            clear();
                                        }
                                        else if (userRoleId == 4) {
                                            alertDialog.dismiss();
                                            Toast.makeText(Login.this, "Неверный логин или пароль. Проверьте введенные данные!", Toast.LENGTH_LONG).show();
                                            userRoleId=0;
                                        }
                                    }

                            }

                            @Override
                            public void onFailure(Call<List<UserResponce>> call, Throwable t) {
                                alertDialog.dismiss();
                                Toast.makeText(Login.this, "Некорректные данные", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                    else{
                        Toast.makeText(Login.this, "Нет интернета", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Login.this, "Некорректные данные", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void Skip(View view){
         userRoleId=3;
        getGuestInfo(3);
        intent=new Intent(Login.this,MainActivity.class);
        startActivity(intent);
        clear();
    }
    public void SignUp(View view){
        Intent intent=new Intent(this,Registration.class);
        startActivity(intent);
    }

    private void initViews() {
        bookIconImageView = findViewById(R.id.bookIconImageView);
        bookITextView = findViewById(R.id.bookITextView);
        rootView = findViewById(R.id.rootView);
        afterAnimationView = findViewById(R.id.afterAnimationView);
        login=findViewById(R.id.loginEditText);
        password=findViewById(R.id.passwordEditText);
        btn_login=findViewById(R.id.loginButton);
        checkInternetConnection=new CheckInternetConnection();
    }

    private void startAnimation() {
        ViewPropertyAnimator viewPropertyAnimator = bookIconImageView.animate();
        viewPropertyAnimator.x(50f);
        viewPropertyAnimator.y(100f);
        viewPropertyAnimator.setDuration(1000);
        viewPropertyAnimator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                afterAnimationView.setVisibility(VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }
    public void getGuestInfo(Integer id){
        Call<List<GuestResponse>> call=iMyApi.getGuestInfo(id);
        call.enqueue(new Callback<List<GuestResponse>>() {
            @Override
            public void onResponse(Call<List<GuestResponse>> call, Response<List<GuestResponse>> response) {
                for(GuestResponse guestResponse:response.body()){
                    userId=guestResponse.getId();
                    userLogin=guestResponse.getLogin();
                    userEmail=guestResponse.getEmail();
                }
            }

            @Override
            public void onFailure(Call<List<GuestResponse>> call, Throwable t) {

            }
        });
    }
    private void clear(){
        login.setText(null);
        password.setText(null);
    }
}
