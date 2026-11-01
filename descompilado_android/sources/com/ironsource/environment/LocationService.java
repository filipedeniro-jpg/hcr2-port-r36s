package com.ironsource.environment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class LocationService {
    private static String TAG = "LocationService";

    public interface ISLocationListener {
        void onLocationChanged(Location location);
    }

    public static Location getLastLocation(Context context) {
        Location location = null;
        if (!ApplicationContext.isPermissionGranted(context, "android.permission.ACCESS_FINE_LOCATION") && !ApplicationContext.isPermissionGranted(context, "android.permission.ACCESS_COARSE_LOCATION")) {
            Log.d(TAG, "Location Permission Not Granted (ACCESS_FINE_LOCATION or ACCESS_COARSE_LOCATION)");
            return null;
        }
        try {
            LocationManager locationManager = (LocationManager) context.getSystemService("location");
            List<String> allProviders = locationManager != null ? locationManager.getAllProviders() : null;
            if (allProviders != null) {
                Iterator<String> it = allProviders.iterator();
                while (it.hasNext()) {
                    try {
                        Location lastKnownLocation = locationManager.getLastKnownLocation(it.next());
                        if (lastKnownLocation != null && lastKnownLocation.getTime() > Long.MIN_VALUE) {
                            location = lastKnownLocation;
                        }
                    } catch (Exception unused) {
                    }
                }
            }
        } catch (Exception unused2) {
        }
        return location;
    }

    @SuppressLint({"MissingPermission"})
    public static void getPreciseLocation(Context context, final ISLocationListener iSLocationListener) {
        if (!ApplicationContext.isPermissionGranted(context, "android.permission.ACCESS_FINE_LOCATION")) {
            Log.d(TAG, "Location Permission Not Granted (ACCESS_FINE_LOCATION)");
            if (iSLocationListener != null) {
                iSLocationListener.onLocationChanged(null);
                return;
            }
        }
        try {
            LocationManager locationManager = (LocationManager) context.getSystemService("location");
            if (iSLocationListener != null && !locationManager.isProviderEnabled("gps")) {
                Log.d(TAG, "GPS Provider is turned off");
                iSLocationListener.onLocationChanged(null);
            } else {
                locationManager.requestSingleUpdate(new Criteria(), new LocationListener() { // from class: com.ironsource.environment.LocationService.1
                    @Override // android.location.LocationListener
                    public void onLocationChanged(Location location) {
                        Log.d("LocationService", "onLocationChanged " + location.getProvider());
                        ISLocationListener iSLocationListener2 = iSLocationListener;
                        if (iSLocationListener2 != null) {
                            iSLocationListener2.onLocationChanged(location);
                        }
                    }

                    @Override // android.location.LocationListener
                    public void onStatusChanged(String str, int i, Bundle bundle) {
                        Log.d("LocationService", "onStatusChanged " + str);
                    }

                    @Override // android.location.LocationListener
                    public void onProviderEnabled(String str) {
                        Log.d("LocationService", "onProviderEnabled " + str);
                    }

                    @Override // android.location.LocationListener
                    public void onProviderDisabled(String str) {
                        Log.d("LocationService", "onProviderDisabled " + str);
                    }
                }, Looper.myLooper());
            }
        } catch (Exception unused) {
            if (iSLocationListener != null) {
                iSLocationListener.onLocationChanged(null);
            }
        }
    }

    public static boolean locationServicesEnabled(Context context) {
        boolean zIsProviderEnabled;
        boolean zIsProviderEnabled2;
        try {
            if (!ApplicationContext.isPermissionGranted(context, "android.permission.ACCESS_FINE_LOCATION") && !ApplicationContext.isPermissionGranted(context, "android.permission.ACCESS_COARSE_LOCATION")) {
                Log.d(TAG, "Location Permission Not Granted (ACCESS_FINE_LOCATION or ACCESS_COARSE_LOCATION)");
                return false;
            }
            LocationManager locationManager = (LocationManager) context.getSystemService("location");
            try {
                zIsProviderEnabled = locationManager.isProviderEnabled("gps");
            } catch (Exception unused) {
                zIsProviderEnabled = false;
            }
            try {
                zIsProviderEnabled2 = locationManager.isProviderEnabled("network");
            } catch (Exception unused2) {
                zIsProviderEnabled2 = false;
            }
            return zIsProviderEnabled || zIsProviderEnabled2;
        } catch (Exception unused3) {
            return false;
        }
    }
}
