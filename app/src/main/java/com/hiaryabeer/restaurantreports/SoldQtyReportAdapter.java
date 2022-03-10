package com.hiaryabeer.restaurantreports;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SoldQtyReportAdapter extends RecyclerView.Adapter<SoldQtyReportAdapter.ViewHolder> {

        ArrayList<SoldQtyReport> soldQtyReportArrayList;
        Context context;

        @NonNull
        @Override
        public SoldQtyReportAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return null;
        }

        @Override
        public void onBindViewHolder(@NonNull SoldQtyReportAdapter.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
                return 0;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
                TextView tax, discount, price, qty, item_name, itemNCode,area,reomveItem;

                public ViewHolder(@NonNull View itemView) {
                        super(itemView);
                }
        }
}
