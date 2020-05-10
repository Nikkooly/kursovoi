package com.bstu.fit.yarmolik.cinema.Fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import com.bstu.fit.yarmolik.cinema.R;
import com.bstu.fit.yarmolik.cinema.Remote.IMyApi;
import com.bstu.fit.yarmolik.cinema.Remote.RetrofitClient;
import com.bstu.fit.yarmolik.cinema.Responces.TicketResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectTickets extends AppCompatActivity {
private String seanceId;
private IMyApi iMyApi;
private ArrayList<Integer> placesList;
private ArrayList<Double> priceList;
private ArrayList<String> idTicketsList;
private ArrayList<Boolean> statusList;
private GridView gridview;

private List<TicketResponse> ticketResponseArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_tickets);
        init();
        loadTicketsInfo(seanceId);
    }
    private void init(){
        iMyApi= RetrofitClient.getInstance().create(IMyApi.class);
        Bundle arguments = getIntent().getExtras();
        seanceId=arguments.get("SeanceId").toString();
        statusList=new ArrayList<>();
        priceList=new ArrayList<>();
        placesList=new ArrayList<>();
        idTicketsList=new ArrayList<>();
        gridview=findViewById(R.id.gridPlaces);
    }
    private void loadTicketsInfo(String id){
        Call<List<TicketResponse>> call= iMyApi.getTickets(id);
        call.enqueue(new Callback<List<TicketResponse>>() {
            @Override
            public void onResponse(Call<List<TicketResponse>> call, Response<List<TicketResponse>> response) {
                ticketResponseArrayList=response.body();
                for(TicketResponse ticket : ticketResponseArrayList){
                    placesList.add(ticket.getPlace());
                    priceList.add(ticket.getPrice());
                    idTicketsList.add(ticket.getId());
                    statusList.add(ticket.getStatus());
                    Toast.makeText(SelectTickets.this, statusList.get(0).toString(), Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<List<TicketResponse>> call, Throwable t) {
                Toast.makeText(SelectTickets.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
}
