package com.reservation.table.tablereservation.presenter;

import com.reservation.table.tablereservation.BookingDataRepository;
import com.reservation.table.tablereservation.model.Customer;
import com.reservation.table.tablereservation.network.NetworkServiceCallbacks;
import com.reservation.table.tablereservation.view.CustomerListViewCallbacks;

import java.util.List;

/**
 * Created on 14/07/17.
 */

public class CustomerListPresenter implements CustomerPresenter {
    private CustomerListViewCallbacks viewCallbacks;
    private BookingDataRepository dataRepository;

    public CustomerListPresenter(CustomerListViewCallbacks viewCallbacks) {
        this.viewCallbacks = viewCallbacks;
        dataRepository = new BookingDataRepository(viewCallbacks.getContextREMOVETHisMethod());
    }

    @Override
    public void fetchAllCustomers() {
        viewCallbacks.requestIsOngoing();
        dataRepository.fetchCustomerData(new NetworkServiceCallbacks<List<Customer>>() {

            @Override
            public void onSuccess(List<Customer> customers) {
                viewCallbacks.requestIsDone();
                viewCallbacks.onFetchDataSuccess(customers);
            }

            @Override
            public void onFailure(String message) {
                viewCallbacks.requestIsDone();
                viewCallbacks.onFetchDataFailure(message);
            }
        });
    }
}
