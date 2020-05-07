package com.bstu.fit.yarmolik.cinema.Fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.bstu.fit.yarmolik.cinema.CheckInternetConnection;
import com.bstu.fit.yarmolik.cinema.R;

public class TicketFragment extends Fragment {
    CheckInternetConnection checkInternetConnection;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ticket, container, false);
        return view;
    }
}
