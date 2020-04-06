package com.bstu.fit.yarmolik.cinema;

import android.animation.Animator;
import android.app.AlertDialog;
import android.content.Intent;
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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bstu.fit.yarmolik.cinema.Model.LoginUser;
import com.bstu.fit.yarmolik.cinema.Model.UserData;
import com.bstu.fit.yarmolik.cinema.Remote.IMyApi;
import com.bstu.fit.yarmolik.cinema.Remote.RetrofitClient;

import dmax.dialog.SpotsDialog;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class Login extends AppCompatActivity {

    private ImageView bookIconImageView;
    private TextView bookITextView;
    private EditText login,password;
    Button btn_login;
    String response="";
    private ProgressBar loadingProgressBar;
    private RelativeLayout rootView, afterAnimationView;
    IMyApi iMyApi;
    Intent intent;
    CompositeDisposable compositeDisposable;

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
                    AlertDialog alertDialog = new SpotsDialog.Builder()
                            .setContext(Login.this)
                            .build();
                    alertDialog.show();
                    LoginUser user = new LoginUser(login.getText().toString(), password.getText().toString());
                    compositeDisposable.add(iMyApi.loginUser(user)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(s -> {
                                alertDialog.dismiss();
                                Toast.makeText(Login.this, s, Toast.LENGTH_SHORT).show();
                                response = s;
                                if(response.toLowerCase().contains("id"))
                                {
                                    intent=new Intent(Login.this,MainActivity.class);
                                    startActivity(intent);
                                }
                            }, throwable -> {
                                alertDialog.dismiss();
                                Toast.makeText(Login.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                            })
                    );
                } else {
                    Toast.makeText(Login.this, "Некорректные данные", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void Skip(View view){
        intent=new Intent(Login.this,MainActivity.class);
        startActivity(intent);
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
}
