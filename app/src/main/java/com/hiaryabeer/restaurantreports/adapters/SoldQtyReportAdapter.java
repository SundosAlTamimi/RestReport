package com.hiaryabeer.restaurantreports.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hiaryabeer.restaurantreports.R;
import com.hiaryabeer.restaurantreports.model.SoldQtyReportModel;


import java.util.ArrayList;

public class SoldQtyReportAdapter extends RecyclerView.Adapter<SoldQtyReportAdapter.ViewHolder> {

        ArrayList<SoldQtyReportModel> List;
        Context context;

        public SoldQtyReportAdapter(ArrayList<SoldQtyReportModel> list, Context context) {
                List = list;
                this.context = context;
                Log.e("SoldQtyReportAdapter66==","SoldQtyReportAdapter");
        }

        @NonNull
        @Override
        public SoldQtyReportAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.soldqtyrep_row, parent, false);
                return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull SoldQtyReportAdapter.ViewHolder holder, int position) {
                Log.e("SoldQtyReportAdapter99",List.get(position).getNetsales());
                holder.MODEL.setText(List.get(position).getModel());
                holder. Kind.setText(List.get(position).getKind());
                holder.  item_Code.setText(List.get(position).getItemCode());
                holder.  item_Name_.setText(List.get(position).getItemname());
                holder.  QTY.setText(List.get(position).getQty());
                holder.  Hint.setText(List.get(position).getHINTS());
                holder. Tax.setText(List.get(position).getTax());
                holder. Service.setText(List.get(position).getSERVICE());
                holder.ServiceTax.setText(List.get(position).getSERVICETAX());
                holder. discount_.setText(List.get(position).getDiscount());
                holder. UnitPrice.setText(List.get(position).getUnitPrice());
                holder.  Gross.setText(List.get(position).getGross());
                holder. Grossperc.setText(List.get(position).getGrossPerc());
                holder. Net.setText(List.get(position).getNetsales());

        }

        @Override
        public int getItemCount() {
                return List.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
                TextView MODEL,Kind,item_Code,item_Name_,QTY,Hint,Tax,Service, ServiceTax,discount_
                        ,UnitPrice,Gross,Grossperc,Net;

                public ViewHolder(@NonNull View itemView) {
                        super(itemView);
                        MODEL=itemView.findViewById(R.id.MODEL);
                                Kind=itemView.findViewById(R.id.Kind);
                                item_Code=itemView.findViewById(R.id.item_Code);
                                item_Name_=itemView.findViewById(R.id.item_Name_);
                                QTY=itemView.findViewById(R.id.QTY);
                                Hint=itemView.findViewById(R.id.Hint);
                                Tax=itemView.findViewById(R.id.Tax);
                                Service=itemView.findViewById(R.id.Service);
                                ServiceTax =itemView.findViewById(R.id.Servicetax);
                                discount_=itemView.findViewById(R.id.discount_);
                                UnitPrice=itemView.findViewById(R.id.UnitPrice);
                                Gross=itemView.findViewById(R.id.Gross);
                                Grossperc=itemView.findViewById(R.id.Grossperc);
                                Net=itemView.findViewById(R.id.Net);


                }
        }
}
