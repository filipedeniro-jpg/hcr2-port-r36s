package com.appsflyer;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
final class i implements SensorEventListener {

    /* JADX INFO: renamed from: ʽ, reason: contains not printable characters */
    private double f151;

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    @NonNull
    private final String f153;

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    @NonNull
    private final String f154;

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    private final int f155;

    /* JADX INFO: renamed from: ॱॱ, reason: contains not printable characters */
    private long f157;

    /* JADX INFO: renamed from: ᐝ, reason: contains not printable characters */
    private final int f158;

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    private final float[][] f156 = new float[2][];

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    private final long[] f152 = new long[2];

    @Override // android.hardware.SensorEventListener
    public final void onAccuracyChanged(Sensor sensor, int i) {
    }

    private i(int i, @Nullable String str, @Nullable String str2) {
        this.f155 = i;
        this.f154 = str == null ? "" : str;
        this.f153 = str2 == null ? "" : str2;
        this.f158 = ((((i + 31) * 31) + this.f154.hashCode()) * 31) + this.f153.hashCode();
    }

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    static i m141(Sensor sensor) {
        return new i(sensor.getType(), sensor.getName(), sensor.getVendor());
    }

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    private static double m144(@NonNull float[] fArr, @NonNull float[] fArr2) {
        int iMin = Math.min(fArr.length, fArr2.length);
        double dPow = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        for (int i = 0; i < iMin; i++) {
            dPow += StrictMath.pow(fArr[i] - fArr2[i], 2.0d);
        }
        return Math.sqrt(dPow);
    }

    @NonNull
    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    private static List<Float> m139(@NonNull float[] fArr) {
        ArrayList arrayList = new ArrayList(fArr.length);
        for (float f : fArr) {
            arrayList.add(Float.valueOf(f));
        }
        return arrayList;
    }

    @Override // android.hardware.SensorEventListener
    public final void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent == null || sensorEvent.values == null) {
            return;
        }
        Sensor sensor = sensorEvent.sensor;
        if ((sensor == null || sensor.getName() == null || sensor.getVendor() == null) ? false : true) {
            int type = sensorEvent.sensor.getType();
            String name = sensorEvent.sensor.getName();
            String vendor = sensorEvent.sensor.getVendor();
            long j = sensorEvent.timestamp;
            float[] fArr = sensorEvent.values;
            if (m145(type, name, vendor)) {
                long jCurrentTimeMillis = System.currentTimeMillis();
                float[][] fArr2 = this.f156;
                float[] fArr3 = fArr2[0];
                if (fArr3 == null) {
                    fArr2[0] = Arrays.copyOf(fArr, fArr.length);
                    this.f152[0] = jCurrentTimeMillis;
                    return;
                }
                float[] fArr4 = fArr2[1];
                if (fArr4 == null) {
                    float[] fArrCopyOf = Arrays.copyOf(fArr, fArr.length);
                    this.f156[1] = fArrCopyOf;
                    this.f152[1] = jCurrentTimeMillis;
                    this.f151 = m144(fArr3, fArrCopyOf);
                    return;
                }
                if (50000000 <= j - this.f157) {
                    this.f157 = j;
                    if (Arrays.equals(fArr4, fArr)) {
                        this.f152[1] = jCurrentTimeMillis;
                        return;
                    }
                    double dM144 = m144(fArr3, fArr);
                    if (dM144 > this.f151) {
                        this.f156[1] = Arrays.copyOf(fArr, fArr.length);
                        this.f152[1] = jCurrentTimeMillis;
                        this.f151 = dM144;
                    }
                }
            }
        }
    }

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    final void m147(@NonNull Map<i, Map<String, Object>> map) {
        m143(map, true);
    }

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    public final void m146(Map<i, Map<String, Object>> map) {
        m143(map, false);
    }

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    private boolean m145(int i, @NonNull String str, @NonNull String str2) {
        return this.f155 == i && this.f154.equals(str) && this.f153.equals(str2);
    }

    @NonNull
    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    private Map<String, Object> m142() {
        HashMap map = new HashMap(7);
        map.put("sT", Integer.valueOf(this.f155));
        map.put("sN", this.f154);
        map.put("sV", this.f153);
        float[] fArr = this.f156[0];
        if (fArr != null) {
            map.put("sVS", m139(fArr));
        }
        float[] fArr2 = this.f156[1];
        if (fArr2 != null) {
            map.put("sVE", m139(fArr2));
        }
        return map;
    }

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    private void m140() {
        for (int i = 0; i < 2; i++) {
            this.f156[i] = null;
        }
        for (int i2 = 0; i2 < 2; i2++) {
            this.f152[i2] = 0;
        }
        this.f151 = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        this.f157 = 0L;
    }

    public final int hashCode() {
        return this.f158;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof i)) {
            return false;
        }
        i iVar = (i) obj;
        return m145(iVar.f155, iVar.f154, iVar.f153);
    }

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    private void m143(@NonNull Map<i, Map<String, Object>> map, boolean z) {
        if (this.f156[0] != null) {
            map.put(this, m142());
            if (z) {
                m140();
                return;
            }
            return;
        }
        if (map.containsKey(this)) {
            return;
        }
        map.put(this, m142());
    }
}
