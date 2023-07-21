package com.example.lab34;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab34.api.onClickItemRecycleView;
import com.example.lab34.model.DataMong;

import java.util.List;

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
        DataMong DataMong = mData.get(position);
        holder.binData(String.valueOf(DataMong));
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
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName= itemView.findViewById(R.id.txtName);
            txtPrice= itemView.findViewById(R.id.txtPrice);
            txtDes= itemView.findViewById(R.id.txtdescription);
        }
        public void  binData(String data){
            txtName.setText(data);
            txtPrice.setText(data);
            txtDes.setText(data);

        }
    }
}
