package com.example.lab34;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab34.api.Apiservide;
import com.example.lab34.model.DataMong;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main2 extends AppCompatActivity {
    private Button btnAdd;
    private EditText edtName, edtPrice, edtImage, edtDes;
    private Adapter adapterMong;

    private List<DataMong> dataMongList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);

        initUi();//ánh xạ
        dataMongList = new ArrayList<>();
        adapterMong = new Adapter(dataMongList);
        btnAdd.setOnClickListener(v -> {
            addProduct();
        });

    }

    private void addProduct() {
        String name, image, price, des;
        name = edtName.getText().toString().trim();
        image = edtImage.getText().toString().trim();
        price = edtPrice.getText().toString().trim();
        des = edtDes.getText().toString().trim();

        DataMong dataMong = new DataMong();
        dataMong.setName(name);
        dataMong.setImage(image);
        dataMong.setPrice(price);
        dataMong.setDes(des);

        Apiservide.apiservice.addData(dataMong).enqueue(new Callback<List<DataMong>>() {
            @Override
            public void onResponse(Call<List<DataMong>> call, Response<List<DataMong>> response) {
                Toast.makeText(Main2.this, "Them thanh cong!", Toast.LENGTH_SHORT).show();
                if (response.isSuccessful()){
                    dataMongList = response.body();
                    if (dataMongList != null){
                        adapterMong.setmData(dataMongList);
                    }
                }else {
                    Toast.makeText(Main2.this, "fail response", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<DataMong>> call, Throwable t) {
                Toast.makeText(Main2.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initUi() {
        edtName = findViewById(R.id.edtName);
        edtImage = findViewById(R.id.edtImage);
        edtPrice = findViewById(R.id.edtPrice);
        edtDes = findViewById(R.id.edtDesscrip);
        btnAdd = findViewById(R.id.btnAdd2);
    }
}
