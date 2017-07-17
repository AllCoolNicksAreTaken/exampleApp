package com.reservation.table.tablereservation.network;

/**
 * Created on 17/07/17.
 */

public interface NetworkServiceCallbacks<T> {
    void onSuccess(T customers);
    void onFailure(String message);
}
