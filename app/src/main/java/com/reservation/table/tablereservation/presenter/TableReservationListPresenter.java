package com.reservation.table.tablereservation.presenter;

import com.reservation.table.tablereservation.BookingDataRepository;
import com.reservation.table.tablereservation.model.Table;
import com.reservation.table.tablereservation.network.NetworkServiceCallbacks;
import com.reservation.table.tablereservation.view.TableReservationListViewCallbacks;

import java.util.List;

/**
 * Created on 16/07/17.
 */

public class TableReservationListPresenter implements TableReservationPresenter{

    private TableReservationListViewCallbacks viewCallbacks;
    private BookingDataRepository dataRepository;


    public TableReservationListPresenter(TableReservationListViewCallbacks viewCallbacks) {
        this.viewCallbacks = viewCallbacks;
        dataRepository = new BookingDataRepository(viewCallbacks.getContextREMOVETHisMethod());
    }


    @Override
    public void fetchAllTables() {
        viewCallbacks.requestIsOngoing();

        dataRepository.fetchTableData(new NetworkServiceCallbacks<List<Table>>() {
            @Override
            public void onSuccess(List<Table> tables) {
                viewCallbacks.requestIsDone();
                viewCallbacks.onFetchDataSuccess(tables);
            }

            @Override
            public void onFailure(String message) {
                viewCallbacks.requestIsDone();
                viewCallbacks.onFetchDataFailure(message);
            }
        });
    }

    @Override
    public void bookTable(Table table) {
        dataRepository.bookTable(table);
    }
}
