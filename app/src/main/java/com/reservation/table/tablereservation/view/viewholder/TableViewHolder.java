package com.reservation.table.tablereservation.view.viewholder;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.reservation.table.tablereservation.R;
import com.reservation.table.tablereservation.model.Table;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created on 14/07/17.
 */

public class TableViewHolder extends RecyclerView.ViewHolder {
    private Table table;
    private ClickListener listener;

    @BindView(R.id.list_item_table_number)
    TextView tvTableNumber;

    @BindView(R.id.list_item_table)
    ViewGroup vgParent;

    public TableViewHolder(ViewGroup parent, ClickListener listener) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_table, parent, false));
        this.listener = listener;
        ButterKnife.bind(this, this.itemView);
    }

    public void bind(Table table){
        this.table = table;
        tvTableNumber.setText(String.valueOf(table.getNumber()));

        if (table.isBooked()){
            vgParent.setBackgroundColor(Color.CYAN);
        }
    }

    /// click listener

    @OnClick(R.id.list_item_table)
    void onTableClick() {
        listener.onItemClicked(table);
    }


    public static class ClickListener{
        public void onItemClicked(Table table){}
    }
}
