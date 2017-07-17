package com.reservation.table.tablereservation.view;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.reservation.table.tablereservation.R;
import com.reservation.table.tablereservation.model.Customer;
import com.reservation.table.tablereservation.presenter.CustomerListPresenter;
import com.reservation.table.tablereservation.presenter.CustomerPresenter;
import com.reservation.table.tablereservation.view.viewholder.CustomerViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CustomerListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CustomerListFragment extends Fragment implements CustomerListViewCallbacks{

    @BindView(R.id.fragment_customer_list_recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.fragment_customer_list_message)
    TextView tvStatusMessage;

    @BindView(R.id.fragment_customer_list_progress_bar)
    ContentLoadingProgressBar progressBar;

    private Unbinder unbinder;
    private CustomerPresenter presenter;
    private CustomerAdapter adapter;
    private List<Customer> customers;


    public CustomerListFragment() {
        // Required empty public constructor
    }

    public static CustomerListFragment newInstance() {
        return new CustomerListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_list, container, false);
        // Inflate the layout for this fragment

        unbinder = ButterKnife.bind(this, view);
        initRecyclerView();
        presenter = new CustomerListPresenter(this);
        presenter.fetchAllCustomers();

        return view;
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

    private void initRecyclerView() {
        adapter = new CustomerAdapter(new ItemActionListener());
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recyclerView.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void requestIsOngoing() {
        tvStatusMessage.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void requestIsDone() {
        progressBar.hide();
    }

    @Override
    public void onFetchDataSuccess(List<Customer> customers) {
        this.customers = customers;
        adapter.notifyDataSetChanged();

        if (customers.isEmpty()) {
            tvStatusMessage.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            tvStatusMessage.setText(R.string.no_data);
        } else {
            tvStatusMessage.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onFetchDataFailure(String message) {
        tvStatusMessage.setVisibility(View.VISIBLE);
        tvStatusMessage.setText(message);
    }

    @Override
    public Context getContextREMOVETHisMethod() {
        return getContext();
    }


    ///////////
    // adapter


    private class CustomerAdapter extends RecyclerView.Adapter<CustomerViewHolder> {

        private final CustomerViewHolder.ClickListener mListener;


        CustomerAdapter(CustomerViewHolder.ClickListener listener) {
            mListener = listener;
        }

        @Override
        public CustomerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new CustomerViewHolder(parent, mListener);
        }

        @Override
        public void onBindViewHolder(CustomerViewHolder holder, int position) {
            holder.bind(customers.get(position));
        }

        @Override
        public int getItemCount() {
            return customers.size();
        }
    }

    class ItemActionListener extends CustomerViewHolder.ClickListener {

        @Override
        public void onListItemClick(Customer customer) {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_activity_fragment_container, TableReservationListFragment.newInstance(customer.getId()))
                    .addToBackStack(null)
                    .commit();

        }

    }

}
