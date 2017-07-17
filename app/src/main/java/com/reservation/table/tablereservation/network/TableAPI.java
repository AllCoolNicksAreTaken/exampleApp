package com.reservation.table.tablereservation.network;

import com.reservation.table.tablereservation.model.Table;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created on 14/07/17.
 */


public interface TableAPI {
    String TABLES_FILE = "table-map.json";

    @GET(TABLES_FILE)
    Call<List<Boolean>> getAllTables();

}
