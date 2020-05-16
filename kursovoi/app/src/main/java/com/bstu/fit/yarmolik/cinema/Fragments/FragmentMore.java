package com.bstu.fit.yarmolik.cinema.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bstu.fit.yarmolik.cinema.Login;
import com.bstu.fit.yarmolik.cinema.R;



public class FragmentMore extends Fragment {
private MainActivity mainActivity;
private Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_more, container, false);
        button=view.findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishActivity();
            }
        });
        return view;
    }
    private void finishActivity() {

            MainActivity mainActivity = new MainActivity();
            mainActivity.finish();
            Intent intent=new Intent(getContext(), Login.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
    }
}
