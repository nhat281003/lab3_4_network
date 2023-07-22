package com.example.lab34;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lab34.api.Apiservide;
import com.example.lab34.api.onClickItemRecycleView;
import com.example.lab34.model.DataMong;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements onClickItemRecycleView,InterCallAPI {
    RecyclerView recyclerView;
    Button btnAdd;
    List<DataMong> dataMongList = new ArrayList<>();
    DataMong DataMong;
    ImageView img;
    Adapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyView);
        btnAdd = findViewById(R.id.btnAdd);
        img = findViewById(R.id.img);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new Adapter(this);
        callApi();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Main2.class);
                startActivity(intent);
            }
        });
    }

    public void callApi() {
        Apiservide.apiservice.getdata().enqueue(new Callback<List<com.example.lab34.model.DataMong>>() {
            @Override
            public void onResponse(Call<List<com.example.lab34.model.DataMong>> call, Response<List<com.example.lab34.model.DataMong>> response) {
                dataMongList = response.body();
                adapter.setmData(dataMongList);
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<com.example.lab34.model.DataMong>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClickItem(int position) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter = new Adapter(this);
        callApi();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void load() {
        callApi();
    }
}