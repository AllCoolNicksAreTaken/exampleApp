package com.reservation.table.tablereservation.db;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.reservation.table.tablereservation.model.Customer;
import com.reservation.table.tablereservation.model.Table;

/**
 * Created on 16/07/17.
 */
@android.arch.persistence.room.Database(entities = {Customer.class, Table.class}, version = 1)
public abstract class BookingDatabase extends RoomDatabase{

    public static final String BOOKING_DATABASE_NAME = "BOOKING_DATABASE";
    private static BookingDatabase INSTANCE;

    public static BookingDatabase getDBInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    BookingDatabase.class,
                    BOOKING_DATABASE_NAME).build();
        }

        return INSTANCE;
    }

    public static void destroyDBInstance() {
        INSTANCE = null;
    }

    public abstract CustomerDAO customerDAO();
    public abstract TableDAO tableDAO();
}