package com.factorypatterndemo.utils.helpers;

import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.factorypatterndemo.utils.ShellCommandUtils;

public class AsusNexus72012sdk21DatePickerHelper {

    public static void setDate(Calendar calendar) throws UiObjectNotFoundException, IOException {
        String dateContentDesc = String.format("%d %s %d",
                calendar.get(Calendar.DAY_OF_MONTH),
                calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()),
                calendar.get(Calendar.YEAR));

        ShellCommandUtils
                .executeShellCommand("/system/bin/am start -a android.settings.DATE_SETTINGS");

        UiObject dateAndTimeToggle = new UiObject(new UiSelector().text("Automatic date and time"));
        UiObject setDate = new UiObject(new UiSelector().text("Set date"));

        // Enable Set time
        if (!setDate.isEnabled()) {
            dateAndTimeToggle.click();
        }

        // Launch time-picker dialog
        new UiObject(new UiSelector().text("Set date")).click();

        // Set year 1 year ahead since the scrollDescriptionIntoView() method only scrolls up
        setYear(calendar.get(Calendar.YEAR) + 1);

        // Scroll through the months of the preceding year(s) to the date matching the content
        // description
        UiScrollable datesList = new UiScrollable(new
                UiSelector().className(android.widget.ListView.class.getName()).scrollable(true));
        datesList.setAsVerticalList();

        // set maxSwipes such that scrollDescriptionIntoView stops on the correct month
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
        int maxSwipes = (currentMonth - calendar.get(Calendar.MONTH)) + 12;

        datesList.setMaxSearchSwipes(maxSwipes);
        datesList.scrollDescriptionIntoView(dateContentDesc);

        new UiObject(new UiSelector().description(dateContentDesc)).click();
    }

    public static void setYear(int year) throws UiObjectNotFoundException {
        Calendar cal = Calendar.getInstance();
        int thisYear = cal.get(Calendar.YEAR);
        int incrementYearIndex = 1;
        int yearDifferential = Math.abs(thisYear - year);
        String yearText = String.format("%d", year);
        UiObject yearTextView = new UiObject(new UiSelector().resourceId("android:id/date_picker_year"));

        if (yearTextView.getText().equals(yearText)) {
            return;
        }

        if (year > thisYear) {
            incrementYearIndex = 3;
        }

        for (int i = 0; i < yearDifferential; i++) {
            yearTextView.click();
            new UiObject(new UiSelector().resourceId("android:id/month_text_view").index(incrementYearIndex))
                    .click();
        }
    }

    public static void clickOk() throws UiObjectNotFoundException {
        new UiObject(new UiSelector()
                .className(android.widget.Button.class.getName())
                .resourceId("android:id/button1")
                .textContains("OK"))
                .click();
    }

    public static void clickCancel() throws UiObjectNotFoundException {
        new UiObject(new UiSelector()
                .className(android.widget.Button.class.getName())
                .resourceId("android:id/button2")
                .textContains("CANCEL"))
                .click();
    }
}
