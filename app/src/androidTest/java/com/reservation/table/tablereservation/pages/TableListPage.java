package com.reservation.table.tablereservation.pages;

import android.graphics.Color;

import com.reservation.table.tablereservation.ColorMatcher;
import com.reservation.table.tablereservation.R;
import com.reservation.table.tablereservation.RecyclerViewItemCountAssertion;

import java.util.concurrent.TimeUnit;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.anything;

/**
 * This class contains basic operations which can be performed on
 * {@link com.reservation.table.tablereservation.view.TableReservationListFragment}.
 * These operations can be combined to perform tests of complex use cases.
 */
public class TableListPage extends TestPage {

    public void assertPresent() {
        onView(isRoot()).perform(waitForViewId(R.id.fragment_table_list_root, TimeUnit.MILLISECONDS.toMillis(6000)));
    }

    public void checkItemsCount(int expected) {
        onView(withId(R.id.fragment_table_reservation_list_recyclerview)).check(new RecyclerViewItemCountAssertion(expected));
    }

    public void clickOnATableAt(int index) {
        onData(anything())
                .inAdapterView(withId(R.id.fragment_table_reservation_list_recyclerview))
                .atPosition(index).perform(click());
    }

    public void clickOnHomeBtn() {
        onView(withId(android.R.id.home)).perform(click());
    }

    public void checkIfItemHighlightedAt(int index) {
        onData(anything())
                .inAdapterView(withId(R.id.fragment_table_reservation_list_recyclerview))
                .atPosition(index).check(matches(new ColorMatcher(Color.CYAN)));
    }

}
