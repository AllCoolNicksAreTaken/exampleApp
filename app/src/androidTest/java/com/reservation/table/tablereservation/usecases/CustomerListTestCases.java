package com.reservation.table.tablereservation.usecases;

import com.reservation.table.tablereservation.pages.CustomerListPage;
import com.reservation.table.tablereservation.pages.TableListPage;

/**
 * Created by kitsune on 17/07/17.
 */

public class CustomerListTestCases extends TestCases {

    private static CustomerListPage customerListPage = new CustomerListPage();
    private TableListPage tableListPage = new TableListPage();

    public static void doOpenCustomerPageAndLoadAllCustomers(){
        customerListPage.assertPresent();
        customerListPage.checkItemsCount(20);
    }

    public static void doOpenTablePage() {
        customerListPage.showTableListScreenVerify(1);
    }

    public static void doBookATableAndVerify() {
        customerListPage.assertPresent();
        customerListPage.checkItemsCount(20);
        TableListPage tableListPage = customerListPage.showTableListScreenVerify(1);
        tableListPage.checkIfItemHighlightedAt(1);
    }
}
