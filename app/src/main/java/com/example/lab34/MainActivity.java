package com.example.lab34;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.UriMatcher;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements onClickItemRecycleView{
    RecyclerView recyclerView;
    Button btnAdd;
    TextView txtName, txtprice, txtdes;
    List<data> dataList = new ArrayList<>();
    data data;
    ImageView img;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtName= findViewById(R.id.txtName);
        txtprice= findViewById(R.id.txtPrice);
        txtdes= findViewById(R.id.txtdescription);
        recyclerView = findViewById(R.id.recyView);
        btnAdd = findViewById(R.id.btnAdd);
        img= findViewById(R.id.img);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
     callApi(new ResponseAPICallBack() {
         @Override
         public void onResponse() {
             dataList.add(data);
         }
     });

        Adapter adapter = new Adapter(dataList,this);
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
    public void onClickItem(int position) {
        Intent intent = new Intent(MainActivity.this, DetailProduct.class);
        startActivity(intent);
    }

    interface ResponseAPICallBack {
        void onResponse();
    }

    private  void  callApi(ResponseAPICallBack callBack)  {
        try {
            URL url = new URL("http://192.168.1.6:3000/danhsach/danhsach");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onResponse();
                        txtName.setText(data.name);
                        txtdes.setText(data.dess);
                        txtprice.setText(data.price);
                    }
                });

            } else {
                System.out.println("Request failed with response code: " + responseCode);
            }
            connection.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}