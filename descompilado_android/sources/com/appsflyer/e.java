package com.appsflyer;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.source.chunk.ChunkedTrackBlacklistUtil;

/* JADX INFO: loaded from: classes.dex */
final class e {

    static final class d {

        /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
        static final e f123 = new e();
    }

    e() {
    }

    @Nullable
    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    static Location m119(@NonNull Context context) {
        try {
            LocationManager locationManager = (LocationManager) context.getSystemService("location");
            Location lastKnownLocation = m118(context, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"}) ? locationManager.getLastKnownLocation("network") : null;
            Location lastKnownLocation2 = m118(context, new String[]{"android.permission.ACCESS_FINE_LOCATION"}) ? locationManager.getLastKnownLocation("gps") : null;
            if (lastKnownLocation2 == null && lastKnownLocation == null) {
                lastKnownLocation2 = null;
            } else if ((lastKnownLocation2 == null && lastKnownLocation != null) || ((lastKnownLocation != null || lastKnownLocation2 == null) && ChunkedTrackBlacklistUtil.DEFAULT_TRACK_BLACKLIST_MS < lastKnownLocation.getTime() - lastKnownLocation2.getTime())) {
                lastKnownLocation2 = lastKnownLocation;
            }
            if (lastKnownLocation2 != null) {
                return lastKnownLocation2;
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    private static boolean m118(@NonNull Context context, @NonNull String[] strArr) {
        for (String str : strArr) {
            if (f.AnonymousClass1.m125(context, str)) {
                return true;
            }
        }
        return false;
    }
}
