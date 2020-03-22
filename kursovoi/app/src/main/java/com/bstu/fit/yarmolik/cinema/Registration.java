package com.bstu.fit.yarmolik.cinema;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class Registration extends AppCompatActivity  {
    TextInputLayout mUsernameLayout;
    EditText name,surname,password,email,login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }
public void Registartion(View view){
        Intent intent=new Intent(this,Login.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
    startActivity(intent);
    finish();
}

}
