package com.example.smartandsafekitchen;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.recyclerview.widget.RecyclerView;

public class IngredientViewHolder extends RecyclerView.ViewHolder {

    TextView ingrItemTV;
    private IngredientViewHolder.ClickListener mClickListener;
    public IngredientViewHolder(@NonNull View itemView) {
        super(itemView);
        ingrItemTV = itemView.findViewById(R.id.ingrItemTV);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(v, getBindingAdapterPosition());
            }
        });
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mClickListener.onItemLongClick(v, getBindingAdapterPosition());
                return true;
            }
        });
    }

    //Interface to send callbacks...
    public interface ClickListener{
        public void onItemClick(View view, int position);
        public void onItemLongClick(View view, int position);
    }

    public void setOnClickListener(IngredientViewHolder.ClickListener clickListener){
        mClickListener = clickListener;
    }
}
