package com.reservation.table.tablereservation.presenter;

import com.reservation.table.tablereservation.model.Table;

/**
 * Created  on 16/07/17.
 */

public interface TableReservationPresenter {
    void fetchAllTables();
    void bookTable(Table table);
}
