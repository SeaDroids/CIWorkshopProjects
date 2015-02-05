/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.factorypatterndemo.utils.helpers;

import java.util.Calendar;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;

/**
 * Based mostly on the AOSP version of DatePickerHelper.class with changes called out in comments.
 */
public class SamsungGalaxyS5sdk19DatePickerHelper {
    public static final String MONTH_ID = "android:id/month";
    public static final String DAY_ID = "android:id/day";
    public static final String YEAR_ID = "android:id/year";

    // Modified from AOSP because Samsung appends: ". Double tap to edit"
    public static String getCurrentMonth() throws UiObjectNotFoundException {
        String month = getNumberPickerField(MONTH_ID).getText();
        month = month.substring(0, month.indexOf("."));
        return month;
    }

    // Modified from AOSP because Samsung appends: ". Double tap to edit"
    public static String getCurrentDay() throws UiObjectNotFoundException {
        String day = getNumberPickerField(DAY_ID).getText();
        day = day.substring(0, day.indexOf("."));
        return day;
    }

    // Modified from AOSP because Samsung appends: ". Double tap to edit"
    public static String getCurrentYear() throws UiObjectNotFoundException {
        String year = getNumberPickerField(YEAR_ID).getText();
        year = year.substring(0, year.indexOf("."));
        return year;
    }

    public static void incrementMonth() throws UiObjectNotFoundException {
        incrementMonth(1);
    }

    public static void incrementMonth(int count) throws UiObjectNotFoundException {
        for (int x = 0; x < count; x++)
            getNumberPickerIncrementButton(MONTH_ID).click();
    }

    public static void decrementMonth() throws UiObjectNotFoundException {
        decrementMonth(1);
    }

    public static void decrementMonth(int count) throws UiObjectNotFoundException {
        for (int x = 0; x < count; x++)
            getNumberPickerDecrementButton(MONTH_ID).click();
    }

    public static void incrementDay() throws UiObjectNotFoundException {
        incrementDay(1);
    }

    public static void incrementDay(int count) throws UiObjectNotFoundException {
        for (int x = 0; x < count; x++)
            getNumberPickerIncrementButton(DAY_ID).click();
    }

    public static void decrementDay() throws UiObjectNotFoundException {
        decrementDay(1);
    }

    public static void decrementDay(int count) throws UiObjectNotFoundException {
        for (int x = 0; x < count; x++)
            getNumberPickerDecrementButton(DAY_ID).click();
    }

    public static void incrementYear() throws UiObjectNotFoundException {
        incrementYear(1);
    }

    public static void incrementYear(int count) throws UiObjectNotFoundException {
        for (int x = 0; x < count; x++)
            getNumberPickerIncrementButton(YEAR_ID).click();
    }

    public static void decrementYear() throws UiObjectNotFoundException {
        decrementYear(1);
    }

    public static void decrementYear(int count) throws UiObjectNotFoundException {
        for (int x = 0; x < count; x++)
            getNumberPickerDecrementButton(YEAR_ID).click();
    }

    public static UiObject getNumberPicker(String layoutId) {
        return new UiObject(new UiSelector().className(
                android.widget.LinearLayout.class.getName()).resourceId(layoutId));
    }

    public static UiObject getNumberPickerField(String layoutId)
            throws UiObjectNotFoundException {
        return getNumberPicker(layoutId).getChild(
                new UiSelector().className(android.widget.EditText.class.getName()));
    }

    // Modified from AOSP because Samsung's dialog uses ImageButtons instead of Buttons.
    public static UiObject getNumberPickerDecrementButton(String layoutId)
            throws UiObjectNotFoundException {
        return getNumberPicker(layoutId).getChild(
                new UiSelector()
                        .className(android.widget.ImageButton.class.getName())
                        .resourceId("android:id/decrement"));
    }

    // Modified from AOSP because Samsung's dialog uses ImageButtons instead of Buttons.
    public static UiObject getNumberPickerIncrementButton(String layoutId)
            throws UiObjectNotFoundException {
        return getNumberPicker(layoutId).getChild(
                new UiSelector()
                        .className(android.widget.ImageButton.class.getName())
                        .resourceId("android:id/increment"));
    }

    // Modified from AOSP because Samsung's dialog instead uses Cancel and Set Buttons
    public static void clickSet() throws UiObjectNotFoundException {
        new UiObject(new UiSelector().text("Set")).click();
    }

    // Modified from AOSP because the original order in which these fields were set resulted in bugs
    // when run in TouchWiz
    public static void setDate(Calendar cal) throws UiObjectNotFoundException {
        int calYear = cal.get(Calendar.YEAR);
        int calMonth = cal.get(Calendar.MONTH);
        int calDay = cal.get(Calendar.DAY_OF_MONTH);
        // Adjust year
        int dpYear = Integer.parseInt(getCurrentYear());
        if (calYear > dpYear) {
            incrementYear(calYear - dpYear);
        } else if (dpYear > calYear) {
            decrementYear(dpYear - calYear);
        }
        // Adjust month - increment or decrement using the shortest path
        int dpMonth = toMonthNumber(getCurrentMonth());
        if (calMonth > dpMonth) {
            if (calMonth - dpMonth < 6)
                incrementMonth(calMonth - dpMonth);
            else
                decrementMonth(dpMonth - calMonth + 12);
        } else if (dpMonth > calMonth) {
            if (dpMonth - calMonth < 6)
                decrementMonth(dpMonth - calMonth);
            else
                incrementMonth(calMonth - dpMonth + 12);
        }
        // Adjust day - increment or decrement using the shortest path
        // while accounting for number of days in month and considering
        // special case for Feb and leap years.
        int dpDay = Integer.parseInt(getCurrentDay());
        if (calDay > dpDay) {
            if (calDay - dpDay < getDaysInMonth(calYear, calMonth) / 2)
                incrementDay(calDay - dpDay);
            else
                decrementDay(dpDay - calDay + getDaysInMonth(calYear, calMonth));
        } else if (dpDay > calDay) {
            if (dpDay - calDay < getDaysInMonth(calYear, calMonth) / 2)
                decrementDay(dpDay - calDay);
            else
                incrementDay(calDay - dpDay + getDaysInMonth(calYear, calMonth));
        }
    }

    private static int toMonthNumber(String monthName) {
        String months[] = new String[] {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December" };
        for (int x = 0; x < months.length; x++) {
            if (months[x].contains(monthName))
                return x;
        }
        return 0;
    }

    /**
     * Get the number of days in the month
     * 
     * @param year
     * @param month
     * @return
     */
    private static int getDaysInMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
}