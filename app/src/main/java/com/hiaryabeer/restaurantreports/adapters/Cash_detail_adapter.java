package com.hiaryabeer.restaurantreports.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hiaryabeer.restaurantreports.GroupSales_Adapter;
import com.hiaryabeer.restaurantreports.GroupSales_Model;
import com.hiaryabeer.restaurantreports.R;
import com.hiaryabeer.restaurantreports.model.detailCashReport;

import java.util.List;

public class Cash_detail_adapter extends RecyclerView.Adapter<Cash_detail_adapter.ViewHolder> {

    private  List<detailCashReport> groupSales_List;
    private  Context context;

    public Cash_detail_adapter(List<detailCashReport> groupSales_List, Context context) {
        this.groupSales_List = groupSales_List;
        this.context = context;
    }

    @NonNull
    @Override
    public Cash_detail_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.row_detail_cash, parent, false);

        return new Cash_detail_adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Cash_detail_adapter.ViewHolder holder, int position) {

        holder.transName.setText(groupSales_List.get(position).getCNAME());
        holder.transAmount.setText(groupSales_List.get(position).getAMOUNT());


    }

    @Override
    public int getItemCount() {
        return groupSales_List.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView transName, transAmount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            transName = itemView.findViewById(R.id.trans_name);
            transAmount = itemView.findViewById(R.id.trans_amount);

        }

    }
}
