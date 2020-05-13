package com.bstu.fit.yarmolik.cinema.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bstu.fit.yarmolik.cinema.ModelAdapter.TicketModel;
import com.bstu.fit.yarmolik.cinema.R;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.TicketViewHolder> {
    private ArrayList<TicketModel> list;
    public TicketAdapter(ArrayList<TicketModel> ticketModels) { list=ticketModels;
    }
    public class TicketViewHolder extends RecyclerView.ViewHolder {
         TextView nameFilm;
         TextView dateSeance;
         TextView timeSeance;
         Button button;
         CardView cardView;
        public TicketViewHolder(View view) {
            super(view);
            nameFilm=view.findViewById(R.id.film_title_text);
            dateSeance=view.findViewById(R.id.textView31);
            timeSeance=view.findViewById(R.id.starting_time_text);
            cardView=view.findViewById(R.id.cardTickets);
            button=view.findViewById(R.id.detail_button);

        }
    }
    @NonNull
    @Override
    public TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_tickets,parent,false);
        return new TicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketViewHolder holder, int position) {
        YoYo.with(Techniques.FadeInRight).playOn(holder.cardView);
        holder.nameFilm.setText(list.get(position).getFilmName());
        holder.dateSeance.setText(list.get(position).getDate());
        holder.timeSeance.setText(list.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
