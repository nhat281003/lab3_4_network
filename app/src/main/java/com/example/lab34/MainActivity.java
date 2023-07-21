package com.example.lab34;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab34.api.Apiservide;
import com.example.lab34.api.onClickItemRecycleView;
import com.example.lab34.model.DataMong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements onClickItemRecycleView {
    RecyclerView recyclerView;
    Button btnAdd;
    List<DataMong> dataMongList = new ArrayList<>();
    DataMong DataMong;
    ImageView img;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyView);
        btnAdd = findViewById(R.id.btnAdd);
        img= findViewById(R.id.img);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Adapter adapter = new Adapter(dataMongList,this);
        recyclerView.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, Main2.class);
//                startActivity(intent);
                callApi();
            }
        });

    }

    @Override
    public void onClickItem(int position) {
        Intent intent = new Intent(MainActivity.this, DetailProduct.class);
        startActivity(intent);
    }


    void callApi (){
        Apiservide.apiservice.getdata().enqueue(new Callback<DataMong>() {
            @Override
            public void onResponse(Call<DataMong> call, Response<DataMong> response) {
                Toast.makeText(MainActivity.this, "call thành công", Toast.LENGTH_SHORT).show();
                DataMong dataMong = response.body();
                if(dataMong != null){
                    dataMongList.add(dataMong);
                    Log.d("cccccccccc", String.valueOf(dataMongList.size()));
                }
            }

            @Override
            public void onFailure(Call<DataMong> call, Throwable t) {
                Toast.makeText(MainActivity.this, "call như cc", Toast.LENGTH_SHORT).show();

            }
        });
    }



}