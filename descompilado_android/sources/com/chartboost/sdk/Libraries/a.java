package com.chartboost.sdk.Libraries;

import android.content.Context;
import com.chartboost.sdk.impl.s;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class a {
    public int a;
    public String b;

    public a(Context context) {
        AdvertisingIdClient.Info infoA;
        try {
            infoA = s.a().a(context);
        } catch (GooglePlayServicesNotAvailableException unused) {
            infoA = null;
        } catch (GooglePlayServicesRepairableException unused2) {
            CBLogging.b("ContentValues", "There was a recoverable error connecting to Google Play Services.");
            infoA = null;
        } catch (IOException unused3) {
            CBLogging.b("ContentValues", "The connection to Google Play Services failed.");
            infoA = null;
        } catch (IllegalStateException unused4) {
            CBLogging.b("ContentValues", "This should have been called off the main thread.");
            infoA = null;
        }
        if (infoA == null) {
            this.a = -1;
            this.b = null;
        } else if (infoA.isLimitAdTrackingEnabled()) {
            this.a = 1;
            this.b = null;
        } else {
            this.a = 0;
            this.b = infoA.getId();
        }
    }
}
