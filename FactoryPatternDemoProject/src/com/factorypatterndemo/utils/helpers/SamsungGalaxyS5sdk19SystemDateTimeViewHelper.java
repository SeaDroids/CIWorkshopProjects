package com.factorypatterndemo.utils.helpers;

import java.io.IOException;
import java.util.Calendar;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.factorypatterndemo.utils.ShellCommandUtils;
import com.factorypatterndemo.utils.interfaces.SystemUiDateTimeViewHelperInterface;

public class SamsungGalaxyS5sdk19SystemDateTimeViewHelper implements SystemUiDateTimeViewHelperInterface {

    @Override
    public void setSystemTime(Calendar calendar) throws UiObjectNotFoundException, IOException {
    	ShellCommandUtils
    			.executeShellCommand("/system/bin/am start -a android.settings.DATE_SETTINGS");

        UiObject dateAndTimeToggle = new UiObject(new UiSelector().text("Automatic date and time"));
        UiObject setDate = new UiObject(new UiSelector().text("Set date"));

        if (!setDate.isEnabled()) {
            dateAndTimeToggle.click();
        }

        new UiObject(
                new UiSelector().text("Set time")).click();

        SamsungGalaxyS5sdk19TimePickerHelper.setTime(calendar);
        SamsungGalaxyS5sdk19TimePickerHelper.clickSet();
    }

    @Override
    public void resetAutomaticDateAndTime() throws UiObjectNotFoundException, IOException {
    	ShellCommandUtils
    			.executeShellCommand("/system/bin/am start -a android.settings.DATE_SETTINGS");

        UiObject dateAndTimeToggle = new UiObject(new UiSelector().text("Automatic date and time"));
        UiObject setDate = new UiObject(new UiSelector().text("Set date"));

        if (setDate.isEnabled()) {
            dateAndTimeToggle.click();
        }
    }

    @Override
    public void setSystemDate(Calendar calendar) throws UiObjectNotFoundException, IOException {
    	ShellCommandUtils
    			.executeShellCommand("/system/bin/am start -a android.settings.DATE_SETTINGS");

        UiObject dateAndTimeToggle = new UiObject(new UiSelector().text("Automatic date and time"));
        UiObject setDate = new UiObject(new UiSelector().text("Set date"));

        if (!setDate.isEnabled()) {
            dateAndTimeToggle.click();
        }

        new UiObject(
                new UiSelector().text("Set date")).click();

        SamsungGalaxyS5sdk19DatePickerHelper.setDate(calendar);
        SamsungGalaxyS5sdk19DatePickerHelper.clickSet();
    }
}
