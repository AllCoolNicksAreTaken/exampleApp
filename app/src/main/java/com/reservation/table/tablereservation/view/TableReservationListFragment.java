package com.reservation.table.tablereservation.view;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.reservation.table.tablereservation.R;
import com.reservation.table.tablereservation.model.Table;
import com.reservation.table.tablereservation.presenter.TableReservationListPresenter;
import com.reservation.table.tablereservation.view.viewholder.TableViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TableReservationListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TableReservationListFragment extends Fragment implements TableReservationListViewCallbacks {
    private static final String ARG_CUSTOMER_ID = "ARG_CUSTOMER_ID";
    public static final int SPAN_COUNT = 4;

    private int customerId;


    @BindView(R.id.fragment_table_reservation_message)
    TextView tvStatusMessage;

    @BindView(R.id.fragment_table_reservation_progress_bar)
    ContentLoadingProgressBar progressBar;

    @BindView(R.id.fragment_table_reservation_list_recyclerview)
    RecyclerView recyclerView;


    private Unbinder unbinder;
    private List<Table> tables;
    private TableReservationListPresenter presenter;
    private TableAdapter adapter;


    public TableReservationListFragment() {
        // Required empty public constructor
    }

    public static TableReservationListFragment newInstance(int customerId) {
        TableReservationListFragment fragment = new TableReservationListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_CUSTOMER_ID, customerId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            customerId = getArguments().getInt(ARG_CUSTOMER_ID);
        }
        presenter = new TableReservationListPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_table_reservation, container, false);
        unbinder = ButterKnife.bind(this, view);
        initRecyclerView();
        presenter.fetchAllTables();
        return view;
    }

    private void initRecyclerView() {
        adapter = new TableAdapter(new ItemActionListener());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), SPAN_COUNT);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                gridLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
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
    public void onFetchDataSuccess(List<Table> tables) {
        this.tables = tables;
        adapter.notifyDataSetChanged();

        if (tables.isEmpty()) {
            tvStatusMessage.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.INVISIBLE);
            tvStatusMessage.setText(R.string.no_data);
        } else {
            tvStatusMessage.setVisibility(View.INVISIBLE);
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


    private class TableAdapter extends RecyclerView.Adapter<TableViewHolder> {

        private ItemActionListener listener;

        TableAdapter (ItemActionListener listener) {

            this.listener = listener;
        }

        @Override
        public TableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new TableViewHolder(parent, listener);
        }

        @Override
        public void onBindViewHolder(TableViewHolder holder, int position) {
            holder.bind(tables.get(position));
        }

        @Override
        public int getItemCount() {
            return tables.size();
        }
    }

    class ItemActionListener extends TableViewHolder.ClickListener {
        @Override
        public void onItemClicked(Table table) {
            table.setCustomerId(customerId);
            table.setBooked(true);
            AsyncTask<Table, Void, Void> task = new AsyncTask<Table, Void, Void>() {
                @Override
                protected Void doInBackground(Table... params) {
                    presenter.bookTable(params[0]);
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    adapter.notifyDataSetChanged();
                }
            };
            task.execute(table);

        }
    }
}
