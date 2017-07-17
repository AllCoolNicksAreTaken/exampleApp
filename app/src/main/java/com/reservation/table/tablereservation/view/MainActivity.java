package com.reservation.table.tablereservation.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.reservation.table.tablereservation.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_activity_fragment_container, CustomerListFragment.newInstance())
                .commit();

    }
}
