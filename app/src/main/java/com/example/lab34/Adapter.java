package com.example.lab34;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab34.api.Apiservide;
import com.example.lab34.api.onClickItemRecycleView;
import com.example.lab34.model.DataMong;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private List<DataMong> mData;
    private onClickItemRecycleView mListener;

    public Adapter(List<DataMong> DataMong, onClickItemRecycleView listener) {
        this.mData = DataMong;
        this.mListener =  listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyview, parent, false);
        return  new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataMong dataMong = mData.get(position);
        holder.binData(dataMong);
        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("XÓA cc");
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onClickItem(position);
            }
        });

    }



    @Override
    public int getItemCount() {
        return mData.size();
    }


    public  static  class ViewHolder extends  RecyclerView.ViewHolder{
        TextView txtName, txtPrice, txtDes;
        ImageButton imageButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName= itemView.findViewById(R.id.txtName);
            txtPrice= itemView.findViewById(R.id.txtPrice);
            txtDes= itemView.findViewById(R.id.txtdescription);
            imageButton= itemView.findViewById(R.id.btnDelete);
        }
        public void  binData(DataMong dataMong){
            txtName.setText(dataMong.getName());
            txtPrice.setText(dataMong.getPrice());
            txtDes.setText(dataMong.getDes());

        }
    }

}
