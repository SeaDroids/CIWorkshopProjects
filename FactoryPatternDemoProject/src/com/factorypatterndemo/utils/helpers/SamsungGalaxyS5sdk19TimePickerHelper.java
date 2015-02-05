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
import java.util.Locale;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;

/**
 * Based mostly on the AOSP version of TimePickerHelper.class with changes called out in comments.
 */
public class SamsungGalaxyS5sdk19TimePickerHelper {
    private static final String HOUR_ID = "android:id/hour";
    private static final String MINUTE_ID = "android:id/minute";
    private static final String MERIDIEM_ID = "android:id/amPm";

    // Modified from AOSP because Samsung appends: ". Double tap to edit"
    public static String getCurrentHour() throws UiObjectNotFoundException {
        String hourText = getNumberPickerField(HOUR_ID).getText();
        hourText = hourText.substring(0, hourText.indexOf('.'));
        return hourText;
    }

    // Modified from AOSP because Samsung appends: ". Double tap to edit"
    public static String getCurrentMinute() throws UiObjectNotFoundException {
        String minuteText = getNumberPickerField(MINUTE_ID).getText();
        minuteText = minuteText.substring(0, minuteText.indexOf('.'));
        return minuteText;
    }

    // Modified from AOSP because Samsung doesn't use a NumberPicker for the meridiem
    public static String getCurrentMeridiem() throws UiObjectNotFoundException {
        UiObject meridiem = new UiObject(new UiSelector().className(
                android.widget.Button.class.getName()).resourceId(MERIDIEM_ID));

        return meridiem.getText();
    }

    public static void incrementHour() throws UiObjectNotFoundException {
        incrementHour(1);
    }

    public static void incrementHour(int count) throws UiObjectNotFoundException {
        for (int x = 0; x < count; x++) {
            getNumberPickerIncrementButton(HOUR_ID).click();
        }
    }

    public static void decrementHour() throws UiObjectNotFoundException {
        decrementHour(1);
    }

    public static void decrementHour(int count) throws UiObjectNotFoundException {
        for (int x = 0; x < count; x++) {
            getNumberPickerDecrementButton(HOUR_ID).click();
        }
    }

    public static void incrementMinute() throws UiObjectNotFoundException {
        incrementMinute(1);
    }

    public static void incrementMinute(int count) throws UiObjectNotFoundException {
        for (int x = 0; x < count; x++) {
            getNumberPickerIncrementButton(MINUTE_ID).click();
        }
    }

    public static void decrementMinute() throws UiObjectNotFoundException {
        decrementMinute(1);
    }

    public static void decrementMinute(int count) throws UiObjectNotFoundException {
        for (int x = 0; x < count; x++) {
            getNumberPickerDecrementButton(MINUTE_ID).click();
        }
    }

    // Modified from AOSP because Samsung doesn't use a number picker for this field
    public static void selectPM() throws UiObjectNotFoundException {
        UiObject meridiem = new UiObject(new UiSelector().className(
                android.widget.Button.class.getName()).resourceId(MERIDIEM_ID));
        if (meridiem.getText().equals("AM")) {
            meridiem.click();
        }
    }

    // Modified from AOSP because Samsung doesn't use a number picker for this field
    public static void selectAM() throws UiObjectNotFoundException {
        UiObject meridiem = new UiObject(new UiSelector().className(
                android.widget.Button.class.getName()).resourceId(MERIDIEM_ID));
        if (meridiem.getText().equals("PM")) {
            meridiem.click();
        }
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

    public static void setTime(Calendar cal) throws UiObjectNotFoundException {
        // Adjust minutes - increment or decrement using the shortest path
        int tpMinute = Integer.parseInt(getCurrentMinute());
        int calMinute = cal.get(Calendar.MINUTE);
        if (calMinute > tpMinute) {
            if (calMinute - tpMinute < 30) {
                incrementMinute(calMinute - tpMinute);
            } else {
                decrementMinute(tpMinute - calMinute + 60);
            }
        } else if (tpMinute > calMinute) {
            if (tpMinute - calMinute < 30) {
                decrementMinute(tpMinute - calMinute);
            } else {
                incrementMinute(calMinute - tpMinute + 60);
            }
        }
        // Adjust hour - increment or decrement using the shortest path
        int tpHour = Integer.parseInt(getCurrentHour());
        int calHour = cal.get(Calendar.HOUR);
        if (calHour > tpHour) {
            if (calHour - tpHour < 6) {
                incrementHour(calHour - tpHour);
            } else {
                decrementHour(tpHour - calHour + 12);
            }
        } else if (tpHour > calHour) {
            if (tpHour - calHour < 6) {
                decrementHour(tpHour - calHour);
            } else {
                incrementHour(calHour - tpHour + 12);
            }
        }
        // Adjust meridiem
        String calMer = cal.getDisplayName(Calendar.AM_PM, Calendar.SHORT, Locale.US);
        String tpMer = getCurrentMeridiem();
        if (tpMer.equalsIgnoreCase(calMer)) {
            return;
        }
        if (!calMer.equalsIgnoreCase("AM")) {
            selectPM();
        } else {
            selectAM();
        }
    }
}
