package com.factorypatterndemo.utils.interfaces;

import java.io.IOException;
import java.util.Calendar;

import com.android.uiautomator.core.UiObjectNotFoundException;

public interface SystemUiDateTimeViewHelperInterface {

	    void setSystemTime(Calendar calendar) throws UiObjectNotFoundException, IOException;

	    void resetAutomaticDateAndTime() throws UiObjectNotFoundException, IOException;

	    void setSystemDate(Calendar calendar) throws UiObjectNotFoundException, IOException;
}
