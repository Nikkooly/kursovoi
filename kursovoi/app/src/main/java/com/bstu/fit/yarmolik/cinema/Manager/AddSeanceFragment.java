package com.bstu.fit.yarmolik.cinema.Manager;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.bstu.fit.yarmolik.cinema.R;

import java.util.Calendar;


public class AddSeanceFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_add_seance, container, false);
        return view;
    }
}
