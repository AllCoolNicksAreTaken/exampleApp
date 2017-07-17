package com.reservation.table.tablereservation;

import android.graphics.drawable.ColorDrawable;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created by kitsune on 17/07/17.
 */

public class ColorMatcher extends TypeSafeMatcher<View>{

    private int expectedColor;

    public ColorMatcher(int expectedColor) {
        this.expectedColor = expectedColor;
    }

    @Override
    protected boolean matchesSafely(View item) {
        return expectedColor == ((ColorDrawable)item.getBackground()).getColor();
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("with background color: ");
    }

}
