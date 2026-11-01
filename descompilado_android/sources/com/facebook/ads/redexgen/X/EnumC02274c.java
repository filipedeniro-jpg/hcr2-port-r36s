package com.facebook.ads.redexgen.X;

import android.os.Build;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.4c, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public enum EnumC02274c {
    ACCELEROMETER_SENSOR(1),
    GYROSCOPE_SENSOR(4),
    MAGNETOMETER_SENSOR(2),
    GEOMAGNETIC_ROTATION_SENSOR(Build.VERSION.SDK_INT >= 19 ? 20 : -1),
    ORIENTATION_SENSOR(0),
    PROXIMITY_SENSOR(8),
    STEP_DETECTOR_SENSOR(Build.VERSION.SDK_INT >= 19 ? 18 : -1),
    LIGHT_SENSOR(5),
    BAROMETER_SENSOR(6),
    AMBIENT_TEMPERATURE_SENSOR(13),
    RELATIVE_HUMIDITY_SENSOR(12);

    private int B;

    EnumC02274c(int value) {
        this.B = value;
    }

    public final int A() {
        return this.B;
    }
}
