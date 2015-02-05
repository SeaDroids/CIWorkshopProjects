package com.factorypatterndemo.utils.factories;

import android.os.Build;

import com.factorypatterndemo.utils.helpers.AsusNexus72012sdk21SystemDateTimeViewHelper;
import com.factorypatterndemo.utils.helpers.SamsungGalaxyS5sdk19SystemDateTimeViewHelper;
import com.factorypatterndemo.utils.interfaces.SystemUiDateTimeViewHelperInterface;

public class SystemUiDateTimeViewHelperFactory {
    static String galaxyS5_sdk19_fingerprint = "samsung/kltedx/klte:4.4.2/KOT49H/G900FDXU1ANG4:user/release-keys";
    static String asusNexus72012_sdk21_fingerprint = "google/nakasi/grouper:5.0.2/LRX22G/1649326:user/release-keys";

    public static SystemUiDateTimeViewHelperInterface create() {
        String fingerprint = Build.FINGERPRINT;

        if (fingerprint.equals(asusNexus72012_sdk21_fingerprint)) {
            return new AsusNexus72012sdk21SystemDateTimeViewHelper();
        }

        if (fingerprint.equals(galaxyS5_sdk19_fingerprint)) {
            return new SamsungGalaxyS5sdk19SystemDateTimeViewHelper();
        }

        return null;

    }
}
