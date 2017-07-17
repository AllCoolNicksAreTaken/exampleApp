package com.reservation.table.tablereservation;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import com.reservation.table.tablereservation.db.BookingDatabase;
import com.reservation.table.tablereservation.model.Customer;
import com.reservation.table.tablereservation.model.Table;
import com.reservation.table.tablereservation.network.CustomerAPI;
import com.reservation.table.tablereservation.network.NetworkClientFactory;
import com.reservation.table.tablereservation.network.NetworkServiceCallbacks;
import com.reservation.table.tablereservation.network.TableAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created on 16/07/17.
 */

public class BookingDataRepository {
    private CustomerAPI customerAPI;
    private TableAPI tableAPI;
    private BookingDatabase dbInstance;
    private ConnectivityManager cm;
    private static boolean allreadyFetched; //TODO:replace


    public BookingDataRepository(Context context) {
        initDataSources(context);
        cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    private void initDataSources(Context context) {
        dbInstance = BookingDatabase.getDBInstance(context);
        customerAPI = NetworkClientFactory.createDefaultClient().create(CustomerAPI.class);
        tableAPI = NetworkClientFactory.createDefaultClient().create(TableAPI.class);
    }

    public void fetchCustomerData(final NetworkServiceCallbacks<List<Customer>> networkServiceCallbacks) {

        if (isConnected()) {
            customerAPI.getAllCustomers().enqueue(new Callback<List<Customer>>() {
                @Override
                public void onResponse(Call<List<Customer>> call, final Response<List<Customer>> response) {
                    //update the DB
                    AsyncTask<List<Customer>, Void, Void> asyncTask = new AsyncTask<List<Customer>, Void, Void>() {
                        @Override
                        protected Void doInBackground(List<Customer>... params) {
                            dbInstance.customerDAO().insertAll(params[0]);
                            return null;
                        }
                    };
                    asyncTask.execute(response.body());

                    networkServiceCallbacks.onSuccess(response.body());
                }

                @Override
                public void onFailure(Call<List<Customer>> call, Throwable t) {
                    networkServiceCallbacks.onFailure(t.toString());
                }
            });
        } else {

            AsyncTask<Void, Void, List<Customer>> asyncTask = new AsyncTask<Void, Void, List<Customer>> () {
                @Override
                protected List<Customer> doInBackground(Void... params) {
                    return dbInstance.customerDAO().getAll();
                }

                @Override
                protected void onPostExecute(List<Customer> customers) {
                    networkServiceCallbacks.onSuccess(customers);
                }
            };
            asyncTask.execute();
        }
    }

    public void fetchTableData(final NetworkServiceCallbacks<List<Table>> networkServiceCallbacks) {
        if (isConnected() && !allreadyFetched ) {
            tableAPI.getAllTables().enqueue(new Callback<List<Boolean>>() {
                @Override
                public void onResponse(Call<List<Boolean>> call, Response<List<Boolean>> response) {
                    List<Table> tables = new ArrayList<>();

                    for (int i = 0; i < response.body().size(); i++) {
                        tables.add(new Table(i, response.body().get(i)));
                    }

                    //update the DB
                    AsyncTask<List<Table>, Void, Void> asyncTask = new AsyncTask<List<Table>, Void, Void>() {

                        @Override
                        protected Void doInBackground(List<Table>... params) {
                            dbInstance.tableDAO().insertAll(params[0]);
                            allreadyFetched = true;
                            return null;
                        }
                    };
                    asyncTask.execute(tables);

                    networkServiceCallbacks.onSuccess(tables);
                }

                @Override
                public void onFailure(Call<List<Boolean>> call, Throwable t) {
                    networkServiceCallbacks.onFailure(t.toString());
                }
            });
        } else {

            AsyncTask<Void, Void, List<Table>> asyncTask = new AsyncTask<Void, Void, List<Table>>() {
                @Override
                protected List<Table> doInBackground(Void... params) {
                    return dbInstance.tableDAO().getAll();
                }

                @Override
                protected void onPostExecute(List<Table> tables) {
                    networkServiceCallbacks.onSuccess(tables);
                }
            };
            asyncTask.execute();
        }
    }

    public void bookTable(Table table) {
        dbInstance.tableDAO().update(table);

//        AsyncTask<Table, Void, Void> task = new AsyncTask<Table, Void, Void>() {
//            @Override
//            protected Void doInBackground(Table... params) {
//                dbInstance.tableDAO().update(params[0]);
//                return null;
//            }
//
//            @Override
//            protected void onPostExecute(Void aVoid) {
//                //TODO: update UI
//            }
//        };
//        task.execute(table);
    }


    private boolean isConnected() {
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }



}
