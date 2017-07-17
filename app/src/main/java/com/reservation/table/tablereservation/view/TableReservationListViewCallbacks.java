package com.reservation.table.tablereservation.view;

import android.content.Context;

import com.reservation.table.tablereservation.model.Table;

import java.util.List;

/**
 * Created on 16/07/17.
 */

public interface TableReservationListViewCallbacks {
    void requestIsOngoing();
    void requestIsDone();
    void onFetchDataSuccess(List<Table> tables);
    void onFetchDataFailure(String message);
    @Deprecated
    Context getContextREMOVETHisMethod();
}
