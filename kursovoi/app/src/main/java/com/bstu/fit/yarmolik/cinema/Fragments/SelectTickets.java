package com.bstu.fit.yarmolik.cinema.Fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.bstu.fit.yarmolik.cinema.Adapters.GridArrayAdapter;
import com.bstu.fit.yarmolik.cinema.R;
import com.bstu.fit.yarmolik.cinema.Remote.IMyApi;
import com.bstu.fit.yarmolik.cinema.Remote.RetrofitClient;
import com.bstu.fit.yarmolik.cinema.Responces.TicketResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectTickets extends AppCompatActivity {
private String seanceId;
private IMyApi iMyApi;
private ArrayList<Integer> placesList;
public ArrayList<Double> priceList;
private TextView priceTextView;
private ArrayList<String> idTicketsList;
private ArrayList<Boolean> statusList;
private GridView gridView;
private GridArrayAdapter adapter;
private Integer size;
private Double price=0.0;
private Integer counter=0;
private Button button;
private TextView countTextView,finalPriceTextView;

private List<TicketResponse> ticketResponseArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_tickets);
        init();
        loadTicketsInfo(seanceId);
        Timer timer = new Timer();
        long delay = 0;
        long period = 100;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        price=GridArrayAdapter.prices;
                        counter=GridArrayAdapter.counter;
                        countTextView.setText(counter.toString());
                        finalPriceTextView.setText(String.valueOf(counter*price));
                    }
                });
            }
        },delay,period);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (adapter.getSelectedPositions().size() > 0) {
                    for(int i=0;i<adapter.getSelectedPositions().size();i++){
                        Toast.makeText(SelectTickets.this, idTicketsList.get(adapter.getSelectedPositions().get(i)), Toast.LENGTH_LONG).show();
                    }
                } else {

                }
                Intent intent = new Intent(SelectTickets.this,TicketInfo.class);
                startActivity(intent);
            }
        });
    }
    private void init(){
        iMyApi= RetrofitClient.getInstance().create(IMyApi.class);
        Bundle arguments = getIntent().getExtras();
        seanceId=arguments.get("SeanceId").toString();
        statusList=new ArrayList<>();
        priceList=new ArrayList<>();
        placesList=new ArrayList<>();
        idTicketsList=new ArrayList<>();
        gridView=findViewById(R.id.gridPlaces);
        priceTextView=findViewById(R.id.priceTicket);
        countTextView=findViewById(R.id.countTicketsTextView);
        finalPriceTextView=findViewById(R.id.priceFinal);
        button=findViewById(R.id.reservTicket);

    }
    public void loadTicketsInfo(String id){
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
                }
                size=statusList.size();
                priceTextView.setText(priceList.get(0).toString());
                if(size%10==0) {
                    adapter = new GridArrayAdapter(SelectTickets.this);
                    //adapter.clear();
                    adapter.setInfo(statusList, idTicketsList, placesList,priceList,0);
                    gridView.setAdapter(adapter);
                }
                else{
                    gridView.setNumColumns(5);
                    adapter = new GridArrayAdapter(SelectTickets.this);
                   // adapter.clear();
                    adapter.setInfo(statusList, idTicketsList, placesList,priceList,0);
                    gridView.setAdapter(adapter);
                }
            }
            @Override
            public void onFailure(Call<List<TicketResponse>> call, Throwable t) {
                Toast.makeText(SelectTickets.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
}
