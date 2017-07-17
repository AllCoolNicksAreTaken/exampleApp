package com.reservation.table.tablereservation.view.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.reservation.table.tablereservation.R;
import com.reservation.table.tablereservation.model.Customer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created on 12/07/17.
 */

public class CustomerViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.list_item_person_first_name)
    TextView tvFirstName;

    @BindView(R.id.list_item_person_last_name)
    TextView tvLastName;

    private ClickListener mListener;
    private Customer customer;

    public CustomerViewHolder(ViewGroup parent, ClickListener listener) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_customer, parent, false));
        mListener = listener;
        ButterKnife.bind(this, this.itemView);
    }

    public void bind(Customer customer) {
        this.customer = customer;
        tvFirstName.setText(customer.getFirstName());
        tvLastName.setText(customer.getLastName());
    }

    @OnClick(R.id.list_item_person_first_name)
    void onFirstNameClick() {
        mListener.onListItemClick(customer);
    }

    @OnClick(R.id.list_item_person_last_name)
    void onLastNameClick() {
        mListener.onListItemClick(customer);
    }



    public static class ClickListener {
        public void onListItemClick(Customer customer) {}
    }

}
