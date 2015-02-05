package com.factorypatterndemo.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ShellCommandUtils {
    public static void stopGoogleKeep() {
        String stopCommand = "system/bin/am force-stop com.google.android.keep";
        executeShellCommand(stopCommand);
    }

    public static void clearGoogleKeep() {
        String stopCommand = "system/bin/pm clear com.google.android.keep";
        executeShellCommand(stopCommand);
    }

    public static void launchGoogleKeep() {
        String launchCommand = "/system/bin/am start -a android.intent.action.MAIN -n com.google.android.keep/.activities.BrowseActivity";
        executeShellCommand(launchCommand);
    }

    public static boolean executeShellCommand(String command) {
        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            int read;
            char[] buffer = new char[4096];
            StringBuffer output = new StringBuffer();
            while ((read = reader.read(buffer)) > 0) {
                output.append(buffer, 0, read);
            }
            reader.close();
            process.waitFor();

            return true;

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
