package com.reservation.table.tablereservation.pages;

import android.support.test.espresso.contrib.RecyclerViewActions;

import com.reservation.table.tablereservation.R;
import com.reservation.table.tablereservation.RecyclerViewItemCountAssertion;

import java.util.concurrent.TimeUnit;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created on 17/07/17.
 */

public class CustomerListPage extends TestPage {

    public void assertPresent() {
        onView(isRoot()).perform(waitForViewId(R.id.fragment_customer_list_root, TimeUnit.SECONDS.toMillis(600)));
    }

    public void checkItemsCount(int expected) {
        onView(isRoot()).perform(waitForViewId(R.id.list_item_person_first_name, TimeUnit.SECONDS.toMillis(6000)));
        onView(withId(R.id.fragment_customer_list_recyclerview)).check(new RecyclerViewItemCountAssertion(expected));
    }

    public TableListPage showTableListScreenVerify(int index) {
        TableListPage tableListPage = clickCustomer(index);
        tableListPage.assertPresent();
        return tableListPage;
    }

    ///// private
    private TableListPage clickCustomer(int index) {
        onView(withId(R.id.fragment_customer_list_recyclerview))
                .perform(RecyclerViewActions.actionOnItemAtPosition(index, click()));
        return new TableListPage();
    }
}
