package com.factorypatterndemo.tests;

import java.io.IOException;
import java.util.Calendar;

import android.os.Build;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.factorypatterndemo.utils.ShellCommandUtils;
import com.factorypatterndemo.utils.UiTestTextUtils;
import com.factorypatterndemo.utils.factories.SystemUiDateTimeViewHelperFactory;
import com.factorypatterndemo.utils.interfaces.SystemUiDateTimeViewHelperInterface;

public class GoogleKeepTest extends UiAutomatorTestCase {

    SystemUiDateTimeViewHelperInterface systemUi = null;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        // press home to close any open notification shades
        UiDevice.getInstance().pressHome();

        // clear the test environment in the app to simulate fresh install
        ShellCommandUtils.stopGoogleKeep();
        ShellCommandUtils.clearGoogleKeep();

        // instantiate factory
        systemUi = SystemUiDateTimeViewHelperFactory.create();
    }

    @Override
    public void tearDown() throws Exception {
        // press home to close any open notification shades
        UiDevice.getInstance().pressHome();

        // clean up after ourselves in the app
        ShellCommandUtils.stopGoogleKeep();
        ShellCommandUtils.clearGoogleKeep();

        // clean up after ourselves in the system time settings
        systemUi.resetAutomaticDateAndTime();

        super.tearDown();
    }

    // For the purposes of this demo, make sure you disable sync on Google Keep for your device's
    // Google Account if you're going to try running the test on multiple devices simultaneously.
    // Google Keep syncs notes aggressively.
    public void testGoogleKeepNoteReminderShouldTriggerNotification() throws UiObjectNotFoundException, IOException {
        UiDevice device = getUiDevice();
        Calendar cal = Calendar.getInstance();
        String testMessage = Build.DEVICE;

        // set system time to 1:00 pm for convenience sake
        cal.set(Calendar.HOUR_OF_DAY, 13);
        cal.set(Calendar.MINUTE, 0);
        systemUi.setSystemTime(cal);

        // launch app
        ShellCommandUtils.launchGoogleKeep();

        // click create new note
        new UiObject(new UiSelector()
                .resourceId("com.google.android.keep:id/new_note_button")
                .description("New note"))
                .clickAndWaitForNewWindow();

        // set note text
        new UiObject(new UiSelector()
                .className(android.widget.EditText.class.getName())
                .resourceId("com.google.android.keep:id/add_item_text_view"))
                .setText(testMessage);

        // click "Remind me"
        new UiObject(new UiSelector()
                .resourceId("com.google.android.keep:id/reminder_header"))
                .click();
        device.pressBack();

        // set reminder day to today
        new UiObject(new UiSelector()
                .resourceId("com.google.android.keep:id/date_spinner"))
                .clickAndWaitForNewWindow();
        new UiObject(new UiSelector()
                .resourceId("com.google.android.keep:id/reminder_date_today"))
                .click();

        // set reminder for custom time
        new UiObject(new UiSelector()
                .resourceId("com.google.android.keep:id/time_spinner"))
                .clickAndWaitForNewWindow();
        new UiObject(new UiSelector()
                .resourceId("com.google.android.keep:id/reminder_time_custom"))
                .clickAndWaitForNewWindow();

        // set reminder time for 1:15pm
        // here's the tricksy bit with their compatible time picker: you can send key events
        // despite the fact that it has no apparent accessibility affordance. Hooray for
        // undocumented features! So sending "0115p" is like 1:15pm in HHmmaa.
        UiTestTextUtils.setStringByKeyEvents("0115p");

        // click done
        new UiObject(new UiSelector()
                .resourceId("com.google.android.keep:id/done_button"))
                .clickAndWaitForNewWindow();

        // even though the app is supposted to automatically save the event when edits are made,
        // press Navigate Up to set the event
        new UiObject(new UiSelector()
                .className(android.widget.ImageButton.class.getName())
                .description("Navigate up"))
                .clickAndWaitForNewWindow();

        // set system time to 15 minutes ahead today
        cal.set(Calendar.MINUTE, 15);
        systemUi.setSystemTime(cal);

        // check notification shade for reminder
        device.openNotification();
        device.waitForIdle(25);

        boolean notificationExists = false;
        UiObject notificationTitle = null;
        for (int i = 0; i < 100; i++) {
            notificationTitle = new UiObject(new UiSelector().resourceId("android:id/title").text(testMessage));
            if (notificationTitle.waitForExists(5)) {
                notificationExists = true;
                break;
            }
        }

        assertTrue("Expected notification with title: " + testMessage, notificationExists);
    }
}
