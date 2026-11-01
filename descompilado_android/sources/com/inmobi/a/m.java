package com.inmobi.a;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.HandlerThread;
import android.provider.Settings;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: compiled from: LocationInfo.java */
/* JADX INFO: loaded from: classes2.dex */
public class m implements LocationListener {
    private static final String e = "m";
    private static m f;
    LocationManager b;
    HandlerThread c = new HandlerThread("LThread");
    GoogleApiClient d;
    private static final Object g = new Object();
    static boolean a = false;
    private static boolean h = false;

    @Override // android.location.LocationListener
    public void onProviderDisabled(String str) {
    }

    @Override // android.location.LocationListener
    public void onProviderEnabled(String str) {
    }

    @Override // android.location.LocationListener
    public void onStatusChanged(String str, int i, Bundle bundle) {
    }

    public static m a() {
        m mVar = f;
        if (mVar == null) {
            synchronized (g) {
                mVar = f;
                if (mVar == null) {
                    mVar = new m();
                    f = mVar;
                }
            }
        }
        return mVar;
    }

    private m() {
        this.c.start();
        this.b = (LocationManager) com.inmobi.commons.a.a.b().getSystemService("location");
    }

    static boolean b() {
        try {
            GoogleApiClient.class.getName();
            FusedLocationProviderClient.class.getName();
            LocationServices.class.getName();
            return false;
        } catch (NoClassDefFoundError unused) {
            return true;
        }
    }

    static boolean c() {
        try {
            return com.inmobi.commons.core.utilities.e.a(com.inmobi.commons.a.a.b(), "signals", "android.permission.ACCESS_FINE_LOCATION") || com.inmobi.commons.core.utilities.e.a(com.inmobi.commons.a.a.b(), "signals", "android.permission.ACCESS_COARSE_LOCATION");
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // android.location.LocationListener
    public void onLocationChanged(Location location) {
        if (location != null) {
            try {
                StringBuilder sb = new StringBuilder("location changed. ts:");
                sb.append(location.getTime());
                sb.append(" lat:");
                sb.append(location.getLatitude());
                sb.append(":");
                sb.append(location.getLongitude());
                sb.append(" accu:");
                sb.append(location.getAccuracy());
            } catch (Exception e2) {
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
                return;
            }
        }
        if (c()) {
            this.b.removeUpdates(this);
        }
    }

    public final synchronized HashMap<String, Object> d() {
        return a(i(), true);
    }

    public final HashMap<String, String> e() {
        HashMap<String, String> map = new HashMap<>();
        String str = "DENIED";
        if (c() && g()) {
            str = "AUTHORISED";
        }
        map.put("loc-consent-status", str.toLowerCase(Locale.ENGLISH));
        return map;
    }

    public final synchronized HashMap<String, String> f() {
        HashMap<String, String> map;
        HashMap<String, Object> mapA;
        map = new HashMap<>();
        Location locationI = i();
        if (locationI != null) {
            mapA = a(locationI, true);
        } else {
            mapA = a(com.inmobi.commons.core.utilities.b.g.c(), false);
        }
        for (Map.Entry<String, Object> entry : mapA.entrySet()) {
            map.put(entry.getKey(), entry.getValue().toString());
        }
        return map;
    }

    public static void a(boolean z) {
        a = z;
    }

    @SuppressLint({"newApi"})
    @TargetApi(19)
    final boolean g() {
        boolean zIsProviderEnabled;
        boolean zIsProviderEnabled2;
        int i;
        Context contextB = com.inmobi.commons.a.a.b();
        if (contextB == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 28) {
            LocationManager locationManager = this.b;
            if (locationManager != null) {
                return locationManager.isLocationEnabled();
            }
            return false;
        }
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 28) {
            try {
                i = Settings.Secure.getInt(contextB.getContentResolver(), "location_mode");
            } catch (Settings.SettingNotFoundException unused) {
                i = 0;
            }
            return i != 0;
        }
        if (this.b != null) {
            if (com.inmobi.commons.core.utilities.e.a(contextB, "signals", "android.permission.ACCESS_FINE_LOCATION")) {
                zIsProviderEnabled2 = this.b.isProviderEnabled("gps");
                zIsProviderEnabled = false;
            } else {
                zIsProviderEnabled = com.inmobi.commons.core.utilities.e.a(contextB, "signals", "android.permission.ACCESS_COARSE_LOCATION") ? this.b.isProviderEnabled("network") : false;
                zIsProviderEnabled2 = false;
            }
            if (zIsProviderEnabled || zIsProviderEnabled2) {
                return true;
            }
        }
        return false;
    }

    private Location i() {
        Location location;
        Location locationK;
        Location locationJ = null;
        try {
            if (a && g() && c()) {
                locationK = h ? k() : null;
                try {
                    if (this.b != null) {
                        locationJ = j();
                    }
                } catch (Exception e2) {
                    location = locationK;
                    e = e2;
                    new StringBuilder("SDK encountered unexpected error in getting a location fix; ").append(e.getMessage());
                    locationK = location;
                }
            } else {
                locationK = null;
            }
        } catch (Exception e3) {
            e = e3;
            location = null;
        }
        return a(locationK, locationJ);
    }

    private static Location a(Location location, Location location2) {
        if (location == null && location2 == null) {
            try {
                com.inmobi.commons.core.e.b.a().a(new com.inmobi.commons.core.e.f("signals", "LocationFixFailed"));
                return null;
            } catch (Exception e2) {
                StringBuilder sb = new StringBuilder("Error in submitting telemetry event : (");
                sb.append(e2.getMessage());
                sb.append(")");
                return null;
            }
        }
        if (location == null) {
            StringBuilder sb2 = new StringBuilder("Location info provided by Android Api client:");
            sb2.append(location2);
            sb2.append(" ts : ");
            sb2.append(location2.getTime());
            return location2;
        }
        if (location2 == null) {
            StringBuilder sb3 = new StringBuilder("Location info provided by Google Api client:");
            sb3.append(location);
            sb3.append(" ts : ");
            sb3.append(location.getTime());
            return location;
        }
        long time = location.getTime() - location2.getTime();
        boolean z = time > 120000;
        boolean z2 = time < -120000;
        boolean z3 = time > 0;
        if (z) {
            StringBuilder sb4 = new StringBuilder("Location info provided by Google Api client:");
            sb4.append(location);
            sb4.append(" ts : ");
            sb4.append(location.getTime());
            return location;
        }
        if (z2) {
            StringBuilder sb5 = new StringBuilder("Location info provided by Android Api client:");
            sb5.append(location2);
            sb5.append(" ts : ");
            sb5.append(location2.getTime());
            return location2;
        }
        int accuracy = (int) (location.getAccuracy() - location2.getAccuracy());
        boolean z4 = accuracy > 0;
        boolean z5 = accuracy < 0;
        boolean z6 = accuracy > 200;
        if (z5 || (z3 && (!z4 || !z6))) {
            StringBuilder sb6 = new StringBuilder("Location info provided by Google Api client:");
            sb6.append(location);
            sb6.append(" ts : ");
            sb6.append(location.getTime());
            return location;
        }
        StringBuilder sb7 = new StringBuilder("Location info provided by Android Api client:");
        sb7.append(location2);
        sb7.append(" ts : ");
        sb7.append(location2.getTime());
        return location2;
    }

    private Location j() {
        Criteria criteria = new Criteria();
        if (com.inmobi.commons.core.utilities.e.a(com.inmobi.commons.a.a.b(), "signals", "android.permission.ACCESS_FINE_LOCATION")) {
            criteria.setAccuracy(1);
        } else if (com.inmobi.commons.core.utilities.e.a(com.inmobi.commons.a.a.b(), "signals", "android.permission.ACCESS_COARSE_LOCATION")) {
            criteria.setAccuracy(2);
        }
        criteria.setCostAllowed(false);
        String bestProvider = this.b.getBestProvider(criteria, true);
        Location locationL = null;
        if (bestProvider != null) {
            try {
                locationL = this.b.getLastKnownLocation(bestProvider);
            } catch (Exception e2) {
                try {
                    HashMap map = new HashMap();
                    map.put("type", "SecurityException");
                    map.put("message", e2.getMessage());
                    com.inmobi.commons.core.e.b.a();
                    com.inmobi.commons.core.e.b.a("signals", "ExceptionCaught", map);
                } catch (Exception e3) {
                    StringBuilder sb = new StringBuilder("Error in submitting telemetry event : (");
                    sb.append(e3.getMessage());
                    sb.append(")");
                }
            }
            if (locationL == null) {
                locationL = l();
            }
        }
        new StringBuilder("Location info provided by Location manager:").append(locationL != null);
        return locationL;
    }

    private static Location k() {
        try {
            return (Location) LocationServices.getFusedLocationProviderClient(com.inmobi.commons.a.a.b()).getLastLocation().getResult();
        } catch (Exception unused) {
            return null;
        }
    }

    private Location l() {
        LocationManager locationManager = this.b;
        Location lastKnownLocation = null;
        if (locationManager != null) {
            List<String> providers = locationManager.getProviders(true);
            for (int size = providers.size() - 1; size >= 0; size--) {
                String str = providers.get(size);
                if (this.b.isProviderEnabled(str)) {
                    try {
                        lastKnownLocation = this.b.getLastKnownLocation(str);
                    } catch (SecurityException e2) {
                        try {
                            HashMap map = new HashMap();
                            map.put("type", "SecurityException");
                            map.put("message", e2.getMessage());
                            com.inmobi.commons.core.e.b.a();
                            com.inmobi.commons.core.e.b.a("signals", "ExceptionCaught", map);
                        } catch (Exception e3) {
                            StringBuilder sb = new StringBuilder("Error in submitting telemetry event : (");
                            sb.append(e3.getMessage());
                            sb.append(")");
                        }
                    }
                    if (lastKnownLocation != null) {
                        break;
                    }
                }
            }
        }
        return lastKnownLocation;
    }

    private HashMap<String, Object> a(Location location, boolean z) {
        HashMap<String, Object> map = new HashMap<>();
        Context contextB = com.inmobi.commons.a.a.b();
        if (contextB == null) {
            return map;
        }
        if (location != null) {
            if (location.getTime() > 0) {
                map.put("u-ll-ts", Long.valueOf(location.getTime()));
            }
            map.put("u-latlong-accu", location.getLatitude() + "," + location.getLongitude() + "," + ((int) location.getAccuracy()));
            map.put("sdk-collected", Integer.valueOf(z ? 1 : 0));
        }
        if (a) {
            map.put("loc-allowed", Integer.valueOf(g() ? 1 : 0));
        }
        if (g() && c()) {
            if (com.inmobi.commons.core.utilities.e.a(contextB, "signals", "android.permission.ACCESS_COARSE_LOCATION")) {
                map.put("loc-granularity", "coarse");
            }
            if (com.inmobi.commons.core.utilities.e.a(contextB, "signals", "android.permission.ACCESS_FINE_LOCATION")) {
                map.put("loc-granularity", "fine");
            }
        } else {
            map.put("loc-granularity", "none");
        }
        return map;
    }
}
