package com.example.lab34;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

    public void setmData(List<DataMong> mData){
        this.mData = mData;
        notifyDataSetChanged();
    }

    public Adapter(List<DataMong> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    public Adapter(List<DataMong> DataMong, onClickItemRecycleView listener) {
        this.mData = DataMong;
        this.mListener =  listener;
        notifyDataSetChanged();
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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onClickItem(position);
            }
        });

        holder.txtUpdate.setOnClickListener(v -> {
            Dialog dialog = new Dialog(v.getContext());
            dialog.setContentView(R.layout.dialog_up_data);
            EditText edname = dialog.findViewById(R.id.dialog_edtName);
            EditText edprice = dialog.findViewById(R.id.dialog_edtPrice);
            EditText edimage = dialog.findViewById(R.id.dialog_edtImage);
            EditText edDes = dialog.findViewById(R.id.dialog_edtDesscrip);
            Button btnUp = dialog.findViewById(R.id.dialog_btnUp);

            edname.setText(dataMong.getName());
            edprice.setText(dataMong.getPrice());
            edimage.setText(dataMong.getImage());
            edDes.setText(dataMong.getDes());

            btnUp.setOnClickListener(v1 -> {
                DataMong dataMong1 = new DataMong();
                dataMong1.setName(edname.getText().toString().trim());
                dataMong1.setPrice(edprice.getText().toString().trim());
                dataMong1.setImage(edimage.getText().toString().trim());
                dataMong1.setDes(edDes.getText().toString().trim());
                Apiservide.apiservice.editData(dataMong.getId(), dataMong1).enqueue(new Callback<DataMong>() {
                    @Override
                    public void onResponse(Call<DataMong> call, Response<DataMong> response) {
                        MainActivity mainActivity = new MainActivity();
                        mainActivity.callApi();
                        Toast.makeText(v.getContext(), "Update thanh cong!", Toast.LENGTH_SHORT).show();
                        notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<DataMong> call, Throwable t) {

                    }
                });

            });

            dialog.show();
        });

        holder.txtDel.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle("delete");
            builder.setMessage("Xoa ?");
            builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Apiservide.apiservice.deleteData(dataMong.getId()).enqueue(new Callback<DataMong>() {
                        @Override
                        public void onResponse(Call<DataMong> call, Response<DataMong> response) {
                            MainActivity mainActivity = new MainActivity();
                            mainActivity.callApi();
                            Toast.makeText(v.getContext(), "delete thanh cong!", Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                        }

                        @Override
                        public void onFailure(Call<DataMong> call, Throwable t) {

                        }
                    });
                }
            });
            builder.show();
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public  static  class ViewHolder extends  RecyclerView.ViewHolder{
        TextView txtName, txtPrice, txtDes, txtDel, txtUpdate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName= itemView.findViewById(R.id.txtName);
            txtPrice= itemView.findViewById(R.id.txtPrice);
            txtDes= itemView.findViewById(R.id.txtdescription);
            txtDel= itemView.findViewById(R.id.txtDel);
            txtUpdate = itemView.findViewById(R.id.txtUpdate);

        }
        public void  binData(DataMong data){
            txtName.setText(data.getName());
            txtPrice.setText(data.getPrice());
            txtDes.setText(data.getDes());
        }
    }
}
