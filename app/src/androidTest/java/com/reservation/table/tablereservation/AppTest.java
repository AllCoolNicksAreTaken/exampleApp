package com.reservation.table.tablereservation;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.reservation.table.tablereservation.pages.CustomerListPage;
import com.reservation.table.tablereservation.pages.TableListPage;
import com.reservation.table.tablereservation.view.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class AppTest {

    private CustomerListPage customerListPage;
    private final int maxCustomerCount = 21;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Before
    public void initPages() {
        customerListPage = new CustomerListPage();
    }

    @Test
    public void canLoadAllCustomers() {
        customerListPage.assertPresent();
        customerListPage.checkItemsCount(maxCustomerCount);
    }

    @Test
    public void canOpenTableList() {
        customerListPage.assertPresent();
        customerListPage.checkItemsCount(maxCustomerCount);
        customerListPage.clickCustomer(2);
//        customerListPage.showTableListScreenVerify(2);
    }

    @Test
    public void canOpenTableListAndLoadAll() {
        customerListPage.assertPresent();
        customerListPage.checkItemsCount(maxCustomerCount);
        TableListPage tableListPage = customerListPage.showTableListScreenVerify(2);
        tableListPage.checkItemsCount(67);
    }

    @Test
    public void canOpenTableListAndLoadAllAndBookATable() {
        customerListPage.assertPresent();
        customerListPage.checkItemsCount(maxCustomerCount);
        TableListPage tableListPage = customerListPage.showTableListScreenVerify(2);
        tableListPage.clickOnATableAt(2);
        tableListPage.checkIfItemHighlightedAt(2);
    }
}
