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
                Intent intent = new Intent(MainActivity.this, Main2.class);
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        callApi();
    }

    @Override
    public void onClickItem(int position) {
        Intent intent = new Intent(MainActivity.this, DetailProduct.class);
        startActivity(intent);
    }

    void callApi (){
        Apiservide.apiservice.getdata().enqueue(new Callback<List<com.example.lab34.model.DataMong>>() {
            @Override
            public void onResponse(Call<List<com.example.lab34.model.DataMong>> call, Response<List<com.example.lab34.model.DataMong>> response) {
                Toast.makeText(MainActivity.this, "call thành công", Toast.LENGTH_SHORT).show();

                dataMongList = response.body();
                Adapter adapter = new Adapter(dataMongList, MainActivity.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<com.example.lab34.model.DataMong>> call, Throwable t) {

            }
        });
    }
}