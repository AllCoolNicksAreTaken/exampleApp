package com.reservation.table.tablereservation.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.reservation.table.tablereservation.db.BookingDatabase;

/**
 * Created on 16/07/17.
 */

public class ResetAllReservationsService extends IntentService {

    public ResetAllReservationsService() {
        super(ResetAllReservationsService.class.getName());
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public ResetAllReservationsService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(">>>>> ", "RESET RESERVATION");
        BookingDatabase.getDBInstance(getApplicationContext()).tableDAO().removeAllReservations();
        //TODO: update UI (LIVEDATA)
    }
}
