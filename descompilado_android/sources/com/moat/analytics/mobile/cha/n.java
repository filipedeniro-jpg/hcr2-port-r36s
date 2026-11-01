package com.moat.analytics.mobile.cha;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
final class n implements LocationListener {

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    private static n f390;

    /* JADX INFO: renamed from: ʻ, reason: contains not printable characters */
    private Location f391;

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    private ScheduledExecutorService f392;

    /* JADX INFO: renamed from: ˊॱ, reason: contains not printable characters */
    private boolean f393;

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private ScheduledFuture<?> f394;

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    private ScheduledFuture<?> f395;

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    private LocationManager f396;

    /* JADX INFO: renamed from: ᐝ, reason: contains not printable characters */
    private boolean f397;

    @Override // android.location.LocationListener
    public final void onProviderDisabled(String str) {
    }

    @Override // android.location.LocationListener
    public final void onProviderEnabled(String str) {
    }

    @Override // android.location.LocationListener
    public final void onStatusChanged(String str, int i, Bundle bundle) {
    }

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    static n m375() {
        if (f390 == null) {
            f390 = new n();
        }
        return f390;
    }

    private n() {
        try {
            this.f393 = ((f) MoatAnalytics.getInstance()).f317;
            if (this.f393) {
                a.m273(3, "LocationManager", this, "Moat location services disabled");
                return;
            }
            this.f392 = Executors.newScheduledThreadPool(1);
            this.f396 = (LocationManager) c.m294().getSystemService("location");
            if (this.f396.getAllProviders().size() == 0) {
                a.m273(3, "LocationManager", this, "Device has no location providers");
            } else {
                m373();
            }
        } catch (Exception e) {
            o.m389(e);
        }
    }

    @Nullable
    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    final Location m384() {
        if (this.f393 || this.f396 == null) {
            return null;
        }
        return this.f391;
    }

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    final void m385() {
        m373();
    }

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    final void m386() {
        m380(false);
    }

    @Override // android.location.LocationListener
    public final void onLocationChanged(Location location) {
        try {
            a.m273(3, "LocationManager", this, "Received an updated location = " + location.toString());
            float fCurrentTimeMillis = (float) ((System.currentTimeMillis() - location.getTime()) / 1000);
            if (!location.hasAccuracy() || location.getAccuracy() > 100.0f || fCurrentTimeMillis >= 600.0f) {
                return;
            }
            this.f391 = m378(this.f391, location);
            a.m273(3, "LocationManager", this, "fetchCompleted");
            m380(true);
        } catch (Exception e) {
            o.m389(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    public void m373() {
        try {
            if (!this.f393 && this.f396 != null) {
                if (this.f397) {
                    a.m273(3, "LocationManager", this, "already updating location");
                }
                a.m273(3, "LocationManager", this, "starting location fetch");
                this.f391 = m378(this.f391, m370());
                if (this.f391 != null) {
                    a.m273(3, "LocationManager", this, "Have a valid location, won't fetch = " + this.f391.toString());
                    m376();
                    return;
                }
                m368();
            }
        } catch (Exception e) {
            o.m389(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    public void m380(boolean z) {
        try {
            a.m273(3, "LocationManager", this, "stopping location fetch");
            m369();
            m372();
            if (z) {
                m376();
            } else {
                m383();
            }
        } catch (Exception e) {
            o.m389(e);
        }
    }

    /* JADX INFO: renamed from: ʽ, reason: contains not printable characters */
    private Location m370() {
        Location lastKnownLocation = null;
        try {
            boolean zM377 = m377();
            boolean zM382 = m382();
            if (zM377 && zM382) {
                lastKnownLocation = m378(this.f396.getLastKnownLocation("gps"), this.f396.getLastKnownLocation("network"));
            } else if (zM377) {
                lastKnownLocation = this.f396.getLastKnownLocation("gps");
            } else if (zM382) {
                lastKnownLocation = this.f396.getLastKnownLocation("network");
            }
        } catch (SecurityException e) {
            o.m389(e);
        }
        return lastKnownLocation;
    }

    /* JADX INFO: renamed from: ʻ, reason: contains not printable characters */
    private void m368() {
        try {
            if (this.f397) {
                return;
            }
            a.m273(3, "LocationManager", this, "Attempting to start update");
            if (m377()) {
                a.m273(3, "LocationManager", this, "start updating gps location");
                this.f396.requestLocationUpdates("gps", 0L, 0.0f, this, Looper.getMainLooper());
                this.f397 = true;
            }
            if (m382()) {
                a.m273(3, "LocationManager", this, "start updating network location");
                this.f396.requestLocationUpdates("network", 0L, 0.0f, this, Looper.getMainLooper());
                this.f397 = true;
            }
            if (this.f397) {
                m372();
                this.f395 = this.f392.schedule(new Runnable() { // from class: com.moat.analytics.mobile.cha.n.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        try {
                            a.m273(3, "LocationManager", this, "fetchTimedOut");
                            n.this.m380(true);
                        } catch (Exception e) {
                            o.m389(e);
                        }
                    }
                }, 60L, TimeUnit.SECONDS);
            }
        } catch (SecurityException e) {
            o.m389(e);
        }
    }

    /* JADX INFO: renamed from: ʼ, reason: contains not printable characters */
    private void m369() {
        try {
            a.m273(3, "LocationManager", this, "Stopping to update location");
            boolean z = true;
            if (!(ContextCompat.checkSelfPermission(c.m294().getApplicationContext(), "android.permission.ACCESS_FINE_LOCATION") == 0)) {
                if (!(ContextCompat.checkSelfPermission(c.m294().getApplicationContext(), "android.permission.ACCESS_COARSE_LOCATION") == 0)) {
                    z = false;
                }
            }
            if (!z || this.f396 == null) {
                return;
            }
            this.f396.removeUpdates(this);
            this.f397 = false;
        } catch (SecurityException e) {
            o.m389(e);
        }
    }

    /* JADX INFO: renamed from: ˊॱ, reason: contains not printable characters */
    private void m372() {
        ScheduledFuture<?> scheduledFuture = this.f395;
        if (scheduledFuture == null || scheduledFuture.isCancelled()) {
            return;
        }
        this.f395.cancel(true);
        this.f395 = null;
    }

    /* JADX INFO: renamed from: ᐝ, reason: contains not printable characters */
    private void m383() {
        ScheduledFuture<?> scheduledFuture = this.f394;
        if (scheduledFuture == null || scheduledFuture.isCancelled()) {
            return;
        }
        this.f394.cancel(true);
        this.f394 = null;
    }

    /* JADX INFO: renamed from: ˏॱ, reason: contains not printable characters */
    private void m376() {
        a.m273(3, "LocationManager", this, "Resetting fetch timer");
        m383();
        Location location = this.f391;
        this.f394 = this.f392.schedule(new Runnable() { // from class: com.moat.analytics.mobile.cha.n.2
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    a.m273(3, "LocationManager", this, "fetchTimerCompleted");
                    n.this.m373();
                } catch (Exception e) {
                    o.m389(e);
                }
            }
        }, (long) (location != null ? Math.max(600.0f - ((System.currentTimeMillis() - location.getTime()) / 1000), 0.0f) : 600.0f), TimeUnit.SECONDS);
    }

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    private static Location m378(Location location, Location location2) {
        boolean zM381 = m381(location);
        boolean zM382 = m381(location2);
        if (zM381) {
            return (zM382 && location.getAccuracy() >= location.getAccuracy()) ? location2 : location;
        }
        if (zM382) {
            return location2;
        }
        return null;
    }

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    private static boolean m381(Location location) {
        if (location == null) {
            return false;
        }
        return !(location.getLatitude() == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE && location.getLongitude() == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) && location.getAccuracy() >= 0.0f && ((float) ((System.currentTimeMillis() - location.getTime()) / 1000)) < 600.0f;
    }

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    static boolean m374(Location location, Location location2) {
        if (location == location2) {
            return true;
        }
        return (location == null || location2 == null || location.getTime() != location2.getTime()) ? false : true;
    }

    /* JADX INFO: renamed from: ͺ, reason: contains not printable characters */
    private boolean m377() {
        return (ContextCompat.checkSelfPermission(c.m294().getApplicationContext(), "android.permission.ACCESS_FINE_LOCATION") == 0) && this.f396.getProvider("gps") != null && this.f396.isProviderEnabled("gps");
    }

    /* JADX WARN: Code duplicated, block: B:14:0x002f  */
    /* JADX INFO: renamed from: ॱˊ, reason: contains not printable characters */
    private boolean m382() {
        boolean z;
        if (ContextCompat.checkSelfPermission(c.m294().getApplicationContext(), "android.permission.ACCESS_FINE_LOCATION") == 0) {
            z = true;
        } else {
            if (ContextCompat.checkSelfPermission(c.m294().getApplicationContext(), "android.permission.ACCESS_COARSE_LOCATION") == 0) {
                z = true;
            } else {
                z = false;
            }
        }
        return z && this.f396.getProvider("network") != null && this.f396.isProviderEnabled("network");
    }
}
