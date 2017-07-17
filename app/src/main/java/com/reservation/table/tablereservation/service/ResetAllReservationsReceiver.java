package com.reservation.table.tablereservation.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Will be triggered periodically by the alarm.
 * Created on 16/07/17.
 */
public class ResetAllReservationsReceiver extends BroadcastReceiver {

    public static final int REQUEST_CODE = 0xFACE;
    public static final String ACTION = "com.reservation.table.tablereservation.service.alarm";

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, ResetAllReservationsService.class);
        context.startService(i);
    }
}
