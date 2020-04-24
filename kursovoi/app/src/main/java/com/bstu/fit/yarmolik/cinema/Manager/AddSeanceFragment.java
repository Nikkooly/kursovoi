package com.bstu.fit.yarmolik.cinema.Manager;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.bstu.fit.yarmolik.cinema.R;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.Calendar;


public class AddSeanceFragment extends Fragment {
    TextView currentDateTime;
    private MaterialSpinner cinemaSpinner,hallSpinner,filmSpinner,timeSpinner;
    Button setDate;
    Calendar dateAndTime=Calendar.getInstance();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_add_seance, container, false);
        init(view);
        setDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getContext(), d,
                        dateAndTime.get(Calendar.YEAR),
                        dateAndTime.get(Calendar.MONTH),
                        dateAndTime.get(Calendar.DAY_OF_MONTH))
                        .show();
            }
        });
        return view;
    }
    public void init(View view){
        cinemaSpinner=view.findViewById(R.id.spinnerSelectCinemaOfSeance);
        hallSpinner=view.findViewById(R.id.spinnerSelectHallOfSeance);
        filmSpinner=view.findViewById(R.id.spinnerSelectFilmOfSeance);
        timeSpinner=view.findViewById(R.id.spinnerSelectTimeOfSeance);
        setDate=view.findViewById(R.id.dateButton);
    }
    DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            //Toast.makeText(getContext(),dateAndTime.getD,Toast.LENGTH_SHORT).show();
            //setInitialDateTime();
        }
    };
}
