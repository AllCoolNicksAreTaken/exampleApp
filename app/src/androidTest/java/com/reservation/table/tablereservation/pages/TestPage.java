package com.reservation.table.tablereservation.pages;

import android.app.Activity;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.PerformException;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.util.HumanReadables;
import android.support.test.espresso.util.TreeIterables;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.Stage;
import android.util.Log;
import android.view.View;

import org.hamcrest.Matcher;

import java.util.Collection;
import java.util.concurrent.TimeoutException;

import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


public class TestPage {

    public Activity getCurrentActivity() {
        final Activity[] activity = new Activity[1];

        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
            public void run() {
                Collection<Activity> resumedActivities = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED);
                for (Activity act : resumedActivities) {
                    Log.d("Your current activity: ", act.getClass().getName());
                    activity[0] = act;
                    break;
                }
            }
        });

        return activity[0];
    }


    /**
     *  Perform action of waiting for a specific view id.
     */
    protected static ViewAction waitForViewId(final int viewId, final long millis) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override
            public String getDescription() {
                return "wait for a specific view with id <" + viewId + "> during " + millis + " millis.";
            }

            @Override
            public void perform(final UiController uiController, final View view) {
                uiController.loopMainThreadUntilIdle();
                final long startTime = System.currentTimeMillis();
                final long endTime = startTime + millis;
                final Matcher<View> viewMatcher = withId(viewId);

                do {
                    for (View child : TreeIterables.breadthFirstViewTraversal(view)) {
                        // found view with required ID
                        if (viewMatcher.matches(child)) {
                            uiController.loopMainThreadForAtLeast(100);
                            return;
                        }
                    }

                    uiController.loopMainThreadForAtLeast(100);
                }
                while (System.currentTimeMillis() < endTime);

                // timeout happens
                throw new PerformException.Builder()
                        .withActionDescription(this.getDescription())
                        .withViewDescription(HumanReadables.describe(view))
                        .withCause(new TimeoutException())
                        .build();
            }
        };
    }
}
