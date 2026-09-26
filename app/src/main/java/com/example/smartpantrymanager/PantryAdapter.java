package com.example.smartpantrymanager;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class PantryAdapter extends RecyclerView.Adapter<PantryAdapter.PantryViewHolder> {

    private ArrayList<PantryItem> pantryList;
    private Context context;

    public PantryAdapter(Context context, ArrayList<PantryItem> pantryList) {
        this.context = context;
        this.pantryList = pantryList;
    }

    @NonNull
    @Override
    public PantryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_2, parent, false);
        return new PantryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PantryViewHolder holder, int position) {
        PantryItem item = pantryList.get(position);
        holder.text1.setText(item.getName().substring(0, 1).toUpperCase() + item.getName().substring(1));

        String details = "Qty: " + item.getQuantity() + " " + item.getUnit();
        if (item.getExpiryDate() != null && !item.getExpiryDate().trim().isEmpty()) {
            details += " | Expires: " + item.getExpiryDate();
        }
        holder.text2.setText(details);

        // Click to Edit item
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, AddEditPantryActivity.class);
            intent.putExtra("pantryID", item.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return pantryList.size();
    }

    public static class PantryViewHolder extends RecyclerView.ViewHolder {
        TextView text1, text2;

        public PantryViewHolder(@NonNull View itemView) {
            super(itemView);
            text1 = itemView.findViewById(android.R.id.text1);
            text2 = itemView.findViewById(android.R.id.text2);
        }
    }
}