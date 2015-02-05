package com.factorypatterndemo.utils.helpers;

import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.factorypatterndemo.utils.UiTestTextUtils;

public class AsusNexus72012sdk21TimePickerHelper {

    /**
     * Sets system time in settings TimePicker. Currently only supports setting time using the 12H
     * format.
     * 
     * @param calendar
     * @throws UiObjectNotFoundException
     * @throws IOException
     */
    public static void setTime(Calendar calendar) throws UiObjectNotFoundException, IOException {
        int hour = calendar.get(Calendar.HOUR);
        int minutes = calendar.get(Calendar.MINUTE);

        // Adjust hour and minutes
        sendKeyEventsForTimeHHMM(hour, minutes);

        // Adjust meridiem
        String calMer = calendar.getDisplayName(Calendar.AM_PM, Calendar.SHORT, Locale.getDefault());
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

    public static String getCurrentMeridiem() throws UiObjectNotFoundException {
        return new UiObject(new UiSelector().resourceId("android:id/ampm_label")).getText();
    }

    public static void selectPM() throws UiObjectNotFoundException {
        UiTestTextUtils.setStringByKeyEvents("p");
    }

    public static void selectAM() throws UiObjectNotFoundException {
        UiTestTextUtils.setStringByKeyEvents("a");
    }

    public static void clickOK() throws UiObjectNotFoundException {
        new UiObject(new UiSelector().resourceId("android:id/button1").text("OK")).click();
    }

    public static void clickCancel() throws UiObjectNotFoundException {
        new UiObject(new UiSelector().resourceId("android:id/button2").text("CANCEL")).click();
    }

    public static void sendKeyEventsForTimeHHMM(int hours, int minutes) throws IOException {
        String hh = String.format("%d", hours);
        if (hh.length() < 2) {
            hh = "0" + hh;
        }
        String mm = String.format("%d", minutes);
        if (mm.length() < 2) {
            mm = "0" + mm;
        }
        UiTestTextUtils.setStringByKeyEvents(hh + mm);
    }
}
