package com.moat.analytics.mobile.iro;

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
final class k implements LocationListener {

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    private static k f603;

    /* JADX INFO: renamed from: ʼ, reason: contains not printable characters */
    private boolean f604;

    /* JADX INFO: renamed from: ʽ, reason: contains not printable characters */
    private Location f605;

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    private ScheduledFuture<?> f606;

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private ScheduledFuture<?> f607;

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    private LocationManager f608;

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    private ScheduledExecutorService f609;

    /* JADX INFO: renamed from: ᐝ, reason: contains not printable characters */
    private boolean f610;

    @Override // android.location.LocationListener
    public final void onProviderDisabled(String str) {
    }

    @Override // android.location.LocationListener
    public final void onProviderEnabled(String str) {
    }

    @Override // android.location.LocationListener
    public final void onStatusChanged(String str, int i, Bundle bundle) {
    }

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    static k m554() {
        if (f603 == null) {
            f603 = new k();
        }
        return f603;
    }

    private k() {
        try {
            this.f610 = ((j) MoatAnalytics.getInstance()).f600;
            if (this.f610) {
                b.m461(3, "LocationManager", this, "Moat location services disabled");
                return;
            }
            this.f609 = Executors.newScheduledThreadPool(1);
            this.f608 = (LocationManager) a.m452().getSystemService("location");
            if (this.f608.getAllProviders().size() == 0) {
                b.m461(3, "LocationManager", this, "Device has no location providers");
            } else {
                m556();
            }
        } catch (Exception e) {
            o.m569(e);
        }
    }

    @Nullable
    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    final Location m563() {
        if (this.f610 || this.f608 == null) {
            return null;
        }
        return this.f605;
    }

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    final void m562() {
        m556();
    }

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    final void m561() {
        m551(false);
    }

    @Override // android.location.LocationListener
    public final void onLocationChanged(Location location) {
        try {
            b.m461(3, "LocationManager", this, "Received an updated location = " + location.toString());
            float fCurrentTimeMillis = (float) ((System.currentTimeMillis() - location.getTime()) / 1000);
            if (!location.hasAccuracy() || location.getAccuracy() > 100.0f || fCurrentTimeMillis >= 600.0f) {
                return;
            }
            this.f605 = m548(this.f605, location);
            b.m461(3, "LocationManager", this, "fetchCompleted");
            m551(true);
        } catch (Exception e) {
            o.m569(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    public void m556() {
        try {
            if (!this.f610 && this.f608 != null) {
                if (this.f604) {
                    b.m461(3, "LocationManager", this, "already updating location");
                }
                b.m461(3, "LocationManager", this, "starting location fetch");
                this.f605 = m548(this.f605, m545());
                if (this.f605 != null) {
                    b.m461(3, "LocationManager", this, "Have a valid location, won't fetch = " + this.f605.toString());
                    m557();
                    return;
                }
                m560();
            }
        } catch (Exception e) {
            o.m569(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    public void m551(boolean z) {
        try {
            b.m461(3, "LocationManager", this, "stopping location fetch");
            m547();
            m550();
            if (z) {
                m557();
            } else {
                m546();
            }
        } catch (Exception e) {
            o.m569(e);
        }
    }

    /* JADX INFO: renamed from: ʻ, reason: contains not printable characters */
    private Location m545() {
        Location lastKnownLocation = null;
        try {
            boolean zM553 = m553();
            boolean zM559 = m559();
            if (zM553 && zM559) {
                lastKnownLocation = m548(this.f608.getLastKnownLocation("gps"), this.f608.getLastKnownLocation("network"));
            } else if (zM553) {
                lastKnownLocation = this.f608.getLastKnownLocation("gps");
            } else if (zM559) {
                lastKnownLocation = this.f608.getLastKnownLocation("network");
            }
        } catch (SecurityException e) {
            o.m569(e);
        }
        return lastKnownLocation;
    }

    /* JADX INFO: renamed from: ᐝ, reason: contains not printable characters */
    private void m560() {
        try {
            if (this.f604) {
                return;
            }
            b.m461(3, "LocationManager", this, "Attempting to start update");
            if (m553()) {
                b.m461(3, "LocationManager", this, "start updating gps location");
                this.f608.requestLocationUpdates("gps", 0L, 0.0f, this, Looper.getMainLooper());
                this.f604 = true;
            }
            if (m559()) {
                b.m461(3, "LocationManager", this, "start updating network location");
                this.f608.requestLocationUpdates("network", 0L, 0.0f, this, Looper.getMainLooper());
                this.f604 = true;
            }
            if (this.f604) {
                m550();
                this.f607 = this.f609.schedule(new Runnable() { // from class: com.moat.analytics.mobile.iro.k.5
                    @Override // java.lang.Runnable
                    public final void run() {
                        try {
                            b.m461(3, "LocationManager", this, "fetchTimedOut");
                            k.this.m551(true);
                        } catch (Exception e) {
                            o.m569(e);
                        }
                    }
                }, 60L, TimeUnit.SECONDS);
            }
        } catch (SecurityException e) {
            o.m569(e);
        }
    }

    /* JADX INFO: renamed from: ʽ, reason: contains not printable characters */
    private void m547() {
        try {
            b.m461(3, "LocationManager", this, "Stopping to update location");
            boolean z = true;
            if (!(ContextCompat.checkSelfPermission(a.m452().getApplicationContext(), "android.permission.ACCESS_FINE_LOCATION") == 0)) {
                if (!(ContextCompat.checkSelfPermission(a.m452().getApplicationContext(), "android.permission.ACCESS_COARSE_LOCATION") == 0)) {
                    z = false;
                }
            }
            if (!z || this.f608 == null) {
                return;
            }
            this.f608.removeUpdates(this);
            this.f604 = false;
        } catch (SecurityException e) {
            o.m569(e);
        }
    }

    /* JADX INFO: renamed from: ˊॱ, reason: contains not printable characters */
    private void m550() {
        ScheduledFuture<?> scheduledFuture = this.f607;
        if (scheduledFuture == null || scheduledFuture.isCancelled()) {
            return;
        }
        this.f607.cancel(true);
        this.f607 = null;
    }

    /* JADX INFO: renamed from: ʼ, reason: contains not printable characters */
    private void m546() {
        ScheduledFuture<?> scheduledFuture = this.f606;
        if (scheduledFuture == null || scheduledFuture.isCancelled()) {
            return;
        }
        this.f606.cancel(true);
        this.f606 = null;
    }

    /* JADX INFO: renamed from: ˏॱ, reason: contains not printable characters */
    private void m557() {
        b.m461(3, "LocationManager", this, "Resetting fetch timer");
        m546();
        Location location = this.f605;
        this.f606 = this.f609.schedule(new Runnable() { // from class: com.moat.analytics.mobile.iro.k.1
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    b.m461(3, "LocationManager", this, "fetchTimerCompleted");
                    k.this.m556();
                } catch (Exception e) {
                    o.m569(e);
                }
            }
        }, (long) (location != null ? Math.max(600.0f - ((System.currentTimeMillis() - location.getTime()) / 1000), 0.0f) : 600.0f), TimeUnit.SECONDS);
    }

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    private static Location m548(Location location, Location location2) {
        boolean zM552 = m552(location);
        boolean zM553 = m552(location2);
        if (zM552) {
            return (zM553 && location.getAccuracy() >= location.getAccuracy()) ? location2 : location;
        }
        if (zM553) {
            return location2;
        }
        return null;
    }

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private static boolean m552(Location location) {
        if (location == null) {
            return false;
        }
        return !(location.getLatitude() == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE && location.getLongitude() == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) && location.getAccuracy() >= 0.0f && ((float) ((System.currentTimeMillis() - location.getTime()) / 1000)) < 600.0f;
    }

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    static boolean m555(Location location, Location location2) {
        if (location == location2) {
            return true;
        }
        return (location == null || location2 == null || location.getTime() != location2.getTime()) ? false : true;
    }

    /* JADX INFO: renamed from: ˋॱ, reason: contains not printable characters */
    private boolean m553() {
        return (ContextCompat.checkSelfPermission(a.m452().getApplicationContext(), "android.permission.ACCESS_FINE_LOCATION") == 0) && this.f608.getProvider("gps") != null && this.f608.isProviderEnabled("gps");
    }

    /* JADX WARN: Code duplicated, block: B:14:0x002f  */
    /* JADX INFO: renamed from: ॱˋ, reason: contains not printable characters */
    private boolean m559() {
        boolean z;
        if (ContextCompat.checkSelfPermission(a.m452().getApplicationContext(), "android.permission.ACCESS_FINE_LOCATION") == 0) {
            z = true;
        } else {
            if (ContextCompat.checkSelfPermission(a.m452().getApplicationContext(), "android.permission.ACCESS_COARSE_LOCATION") == 0) {
                z = true;
            } else {
                z = false;
            }
        }
        return z && this.f608.getProvider("network") != null && this.f608.isProviderEnabled("network");
    }
}
