package com.factorypatterndemo.utils.helpers;

import java.io.IOException;
import java.util.Calendar;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.factorypatterndemo.utils.ShellCommandUtils;
import com.factorypatterndemo.utils.interfaces.SystemUiDateTimeViewHelperInterface;

public class AsusNexus72012sdk21SystemDateTimeViewHelper implements SystemUiDateTimeViewHelperInterface {

    @Override
    public void setSystemTime(Calendar calendar) throws UiObjectNotFoundException, IOException {
    	ShellCommandUtils
        		.executeShellCommand("/system/bin/am start -a android.settings.DATE_SETTINGS");

        UiObject dateAndTimeToggle = new UiObject(new UiSelector().text("Automatic date & time"));
        UiObject setDate = new UiObject(new UiSelector().text("Set date"));

        if (!setDate.isEnabled()) {
            dateAndTimeToggle.click();
        }

        new UiObject(
                new UiSelector().text("Set time")).click();

        AsusNexus72012sdk21TimePickerHelper.setTime(calendar);
        AsusNexus72012sdk21TimePickerHelper.clickOK();
    }

    @Override
    public void resetAutomaticDateAndTime() throws UiObjectNotFoundException, IOException {
    	ShellCommandUtils
        		.executeShellCommand("/system/bin/am start -a android.settings.DATE_SETTINGS");

        UiObject dateAndTimeToggle = new UiObject(new UiSelector().text("Automatic date & time"));
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

        AsusNexus72012sdk21DatePickerHelper.setDate(calendar);
        AsusNexus72012sdk21DatePickerHelper.clickOk();
    }
}
