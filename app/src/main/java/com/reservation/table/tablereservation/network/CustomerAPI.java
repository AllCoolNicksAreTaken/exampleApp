package com.reservation.table.tablereservation.network;

import com.reservation.table.tablereservation.model.Customer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created on 14/07/17.
 */

public interface CustomerAPI {
    String CUSTOMERS_FILE = "customer-list.json";

    @GET(CUSTOMERS_FILE)
    Call<List<Customer>> getAllCustomers();
}
