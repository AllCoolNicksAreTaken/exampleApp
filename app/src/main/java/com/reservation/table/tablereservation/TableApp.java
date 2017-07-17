package com.reservation.table.tablereservation;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.reservation.table.tablereservation.service.ResetAllReservationsReceiver;

/**
 * Created on 16/07/17.
 */

public class TableApp extends Application {

    private final int intervalLengthInMS = 30000; //10 Minutes

    @Override
    public void onCreate() {
        super.onCreate();
        scheduleAlarm();
    }

    private void scheduleAlarm() {
        Intent intent = new Intent(getApplicationContext(), ResetAllReservationsReceiver.class);
        intent.setAction(ResetAllReservationsReceiver.ACTION);
        final PendingIntent pIntent = PendingIntent.getBroadcast(this, ResetAllReservationsReceiver.REQUEST_CODE,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        long firstExecutionTime = System.currentTimeMillis()+ intervalLengthInMS;
        AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstExecutionTime,
                intervalLengthInMS, pIntent);
    }

}
