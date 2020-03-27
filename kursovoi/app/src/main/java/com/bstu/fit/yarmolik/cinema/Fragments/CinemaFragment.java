package com.bstu.fit.yarmolik.cinema.Fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bstu.fit.yarmolik.cinema.CinemaModel;
import com.bstu.fit.yarmolik.cinema.R;

import java.util.ArrayList;


public class CinemaFragment extends Fragment {
    ArrayList<CinemaModel> listitems = new ArrayList<>();
    RecyclerView MyRecyclerView;
    RecyclerView recyclerView;
    String description[] = {"Минск \nпроспект Александра Лукашенко 15","Минск \nул. Николая Лукашенко 31","Минск \nул. Виктора Лукашенко 21/1","Минск \nул. Дмитрия Лукашенко 5"};
    int  images[] = {R.drawable.cinema5,R.drawable.cinema6,R.drawable.cinema7,R.drawable.cinema8};
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Кинотеатры");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cinema, container, false);
        recyclerView=(RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList<CinemaModel> cinema =new ArrayList<CinemaModel>();
        for(int i=0;i<4;i++) {
            cinema.add(new CinemaModel(images[i], description[i]));
        }
        MyAdapter carAdapter = new MyAdapter(cinema);
        recyclerView.setAdapter(carAdapter);
        return view;
    }
}
