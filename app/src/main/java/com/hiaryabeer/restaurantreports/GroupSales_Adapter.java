package com.hiaryabeer.restaurantreports;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GroupSales_Adapter extends RecyclerView.Adapter<GroupSales_Adapter.ViewHolder> {

    private final List<GroupSales_Model> groupSales_List;
    private final Context context;

    public GroupSales_Adapter(List<GroupSales_Model> groupSales_List, Context context) {
        this.groupSales_List = groupSales_List;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.group_sales_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.kind.setText(groupSales_List.get(position).getKind());
        holder.model.setText(groupSales_List.get(position).getModel());
        holder.group.setText(groupSales_List.get(position).getGroup());
        holder.qty.setText(groupSales_List.get(position).getQty());
        holder.gross.setText(groupSales_List.get(position).getGross());
        holder.grossPer.setText(groupSales_List.get(position).getGross_percent());
        holder.discount.setText(groupSales_List.get(position).getDiscount());
        holder.totAfterDisc.setText(groupSales_List.get(position).getTotal_After_discount());
        holder.tax.setText(groupSales_List.get(position).getTax());
        holder.net.setText(groupSales_List.get(position).getNet());
        holder.netPercent.setText(groupSales_List.get(position).getNet_percent());

    }

    @Override
    public int getItemCount() {
        return groupSales_List.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView kind, model, group, qty, gross, grossPer, discount,
                totAfterDisc, tax, net, netPercent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            kind = itemView.findViewById(R.id.kind);
            model = itemView.findViewById(R.id.model);
            group = itemView.findViewById(R.id.group);
            qty = itemView.findViewById(R.id.qty);
            gross = itemView.findViewById(R.id.gross);
            grossPer = itemView.findViewById(R.id.grossPer);
            discount = itemView.findViewById(R.id.discount);
            totAfterDisc = itemView.findViewById(R.id.totAfterDisc);
            tax = itemView.findViewById(R.id.tax);
            net = itemView.findViewById(R.id.net);
            netPercent = itemView.findViewById(R.id.netPercent);

        }

    }

}
