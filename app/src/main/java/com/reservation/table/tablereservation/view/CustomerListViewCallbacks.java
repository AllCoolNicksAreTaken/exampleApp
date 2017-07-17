package com.reservation.table.tablereservation.view;

import android.content.Context;

import com.reservation.table.tablereservation.model.Customer;

import java.util.List;

/**
 * Created on 14/07/17.
 */

public interface CustomerListViewCallbacks {
    void requestIsOngoing();
    void requestIsDone();
    void onFetchDataSuccess(List<Customer> customers);
    void onFetchDataFailure(String message);
    @Deprecated
    Context getContextREMOVETHisMethod();
}
