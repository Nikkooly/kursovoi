package com.bstu.fit.yarmolik.cinema.Fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.bstu.fit.yarmolik.cinema.R;

public class AddInfoTicket extends AppCompatActivity {
private TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_info_ticket);
        init();
        Bundle arguments = getIntent().getExtras();
        name.setText(arguments.get("idFilmFragment").toString());
    }
    private void init(){
        name=findViewById(R.id.textView5);
    }
}
