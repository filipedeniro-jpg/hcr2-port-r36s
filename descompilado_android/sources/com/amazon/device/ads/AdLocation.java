package com.amazon.device.ads;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

/* JADX INFO: loaded from: classes.dex */
class AdLocation {
    private static final int ARCMINUTE_PRECISION = 6;
    private static final String LOGTAG = "AdLocation";
    private static final float MAX_DISTANCE_IN_KILOMETERS = 3.0f;
    private final Configuration configuration;
    private final Context context;
    private final MobileAdsLogger logger;

    private enum LocationAwareness {
        LOCATION_AWARENESS_NORMAL,
        LOCATION_AWARENESS_TRUNCATED,
        LOCATION_AWARENESS_DISABLED
    }

    public AdLocation(Context context) {
        this(context, Configuration.getInstance());
    }

    AdLocation(Context context, Configuration configuration) {
        this.logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);
        this.context = context;
        this.configuration = configuration;
    }

    private static double roundToArcminutes(double d) {
        double dRound = Math.round(d * 60.0d);
        Double.isNaN(dRound);
        return dRound / 60.0d;
    }

    private LocationAwareness getLocationAwareness() {
        if (this.configuration.getBoolean(Configuration.ConfigOption.TRUNCATE_LAT_LON)) {
            return LocationAwareness.LOCATION_AWARENESS_TRUNCATED;
        }
        return LocationAwareness.LOCATION_AWARENESS_NORMAL;
    }

    public Location getLocation() {
        Location lastKnownLocation;
        Location lastKnownLocation2;
        LocationAwareness locationAwareness = getLocationAwareness();
        if (LocationAwareness.LOCATION_AWARENESS_DISABLED.equals(locationAwareness)) {
            return null;
        }
        LocationManager locationManager = (LocationManager) this.context.getSystemService("location");
        try {
            lastKnownLocation = locationManager.getLastKnownLocation("gps");
        } catch (IllegalArgumentException unused) {
            this.logger.d("Failed to retrieve GPS location: No GPS found");
            lastKnownLocation = null;
        } catch (SecurityException unused2) {
            this.logger.d("Failed to retrieve GPS location: No permissions to access GPS");
            lastKnownLocation = null;
        }
        try {
            lastKnownLocation2 = locationManager.getLastKnownLocation("network");
        } catch (IllegalArgumentException unused3) {
            this.logger.d("Failed to retrieve network location: No network provider found");
            lastKnownLocation2 = null;
        } catch (SecurityException unused4) {
            this.logger.d("Failed to retrieve network location: No permissions to access network location");
            lastKnownLocation2 = null;
        }
        if (lastKnownLocation == null && lastKnownLocation2 == null) {
            return null;
        }
        if (lastKnownLocation != null && lastKnownLocation2 != null) {
            if (lastKnownLocation.distanceTo(lastKnownLocation2) / 1000.0f <= MAX_DISTANCE_IN_KILOMETERS) {
                if ((lastKnownLocation.hasAccuracy() ? lastKnownLocation.getAccuracy() : Float.MAX_VALUE) < (lastKnownLocation2.hasAccuracy() ? lastKnownLocation2.getAccuracy() : Float.MAX_VALUE)) {
                    this.logger.d("Setting lat/long using GPS determined by distance");
                    lastKnownLocation2 = lastKnownLocation;
                } else {
                    this.logger.d("Setting lat/long using network determined by distance");
                }
            } else if (lastKnownLocation.getTime() > lastKnownLocation2.getTime()) {
                this.logger.d("Setting lat/long using GPS");
            } else {
                this.logger.d("Setting lat/long using network");
            }
            lastKnownLocation = lastKnownLocation2;
        } else if (lastKnownLocation != null) {
            this.logger.d("Setting lat/long using GPS, not network");
        } else {
            this.logger.d("Setting lat/long using network location, not GPS");
            lastKnownLocation = lastKnownLocation2;
        }
        if (LocationAwareness.LOCATION_AWARENESS_TRUNCATED.equals(locationAwareness)) {
            double dRound = Math.round(Math.pow(10.0d, 6.0d) * roundToArcminutes(lastKnownLocation.getLatitude()));
            double dPow = Math.pow(10.0d, 6.0d);
            Double.isNaN(dRound);
            lastKnownLocation.setLatitude(dRound / dPow);
            double dRound2 = Math.round(Math.pow(10.0d, 6.0d) * roundToArcminutes(lastKnownLocation.getLongitude()));
            double dPow2 = Math.pow(10.0d, 6.0d);
            Double.isNaN(dRound2);
            lastKnownLocation.setLongitude(dRound2 / dPow2);
        }
        return lastKnownLocation;
    }
}
