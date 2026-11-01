package com.applovin.impl.sdk.ad;

import android.util.Log;
import com.applovin.impl.sdk.AppLovinAdBase;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class h extends AppLovinAdBase {
    private AppLovinAd a;
    private final d b;

    public h(d dVar, com.applovin.impl.sdk.h hVar) {
        super(new JSONObject(), new JSONObject(), b.UNKNOWN, hVar);
        this.b = dVar;
    }

    private AppLovinAd c() {
        return (AppLovinAd) this.sdk.O().c(this.b);
    }

    private String d() {
        d adZone = getAdZone();
        if (adZone == null || adZone.j()) {
            return null;
        }
        return adZone.a();
    }

    public AppLovinAd a() {
        return this.a;
    }

    public void a(AppLovinAd appLovinAd) {
        this.a = appLovinAd;
    }

    public AppLovinAd b() {
        AppLovinAd appLovinAd = this.a;
        return appLovinAd != null ? appLovinAd : c();
    }

    @Override // com.applovin.impl.sdk.AppLovinAdBase
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AppLovinAd appLovinAdB = b();
        return appLovinAdB != null ? appLovinAdB.equals(obj) : super.equals(obj);
    }

    @Override // com.applovin.impl.sdk.AppLovinAdBase, com.applovin.sdk.AppLovinAd
    public long getAdIdNumber() {
        try {
            AppLovinAd appLovinAdB = b();
            if (appLovinAdB != null) {
                return appLovinAdB.getAdIdNumber();
            }
            return 0L;
        } catch (Throwable th) {
            Log.e("AppLovinAd", "Failed to retrieve ad id number", th);
            return 0L;
        }
    }

    @Override // com.applovin.impl.sdk.AppLovinAdBase
    public d getAdZone() {
        AppLovinAdBase appLovinAdBase = (AppLovinAdBase) b();
        return appLovinAdBase != null ? appLovinAdBase.getAdZone() : this.b;
    }

    @Override // com.applovin.impl.sdk.AppLovinAdBase, com.applovin.sdk.AppLovinAd
    public AppLovinAdSize getSize() {
        AppLovinAdSize appLovinAdSize = AppLovinAdSize.INTERSTITIAL;
        try {
            return getAdZone().b();
        } catch (Throwable th) {
            Log.e("AppLovinAd", "Failed to retrieve ad size", th);
            return appLovinAdSize;
        }
    }

    @Override // com.applovin.impl.sdk.AppLovinAdBase
    public b getSource() {
        AppLovinAdBase appLovinAdBase = (AppLovinAdBase) b();
        return appLovinAdBase != null ? appLovinAdBase.getSource() : b.UNKNOWN;
    }

    @Override // com.applovin.impl.sdk.AppLovinAdBase, com.applovin.sdk.AppLovinAd
    public AppLovinAdType getType() {
        AppLovinAdType appLovinAdType = AppLovinAdType.REGULAR;
        try {
            return getAdZone().c();
        } catch (Throwable th) {
            Log.e("AppLovinAd", "Failed to retrieve ad type", th);
            return appLovinAdType;
        }
    }

    @Override // com.applovin.impl.sdk.AppLovinAdBase, com.applovin.sdk.AppLovinAd
    public String getZoneId() {
        try {
            if (this.b.j()) {
                return null;
            }
            return this.b.a();
        } catch (Throwable th) {
            Log.e("AppLovinAd", "Failed to return zone id", th);
            return null;
        }
    }

    @Override // com.applovin.impl.sdk.AppLovinAdBase
    public int hashCode() {
        AppLovinAd appLovinAdB = b();
        return appLovinAdB != null ? appLovinAdB.hashCode() : super.hashCode();
    }

    @Override // com.applovin.impl.sdk.AppLovinAdBase, com.applovin.sdk.AppLovinAd
    public boolean isVideoAd() {
        try {
            AppLovinAd appLovinAdB = b();
            if (appLovinAdB != null) {
                return appLovinAdB.isVideoAd();
            }
            return false;
        } catch (Throwable th) {
            Log.e("AppLovinAd", "Failed to return whether ad is video ad", th);
            return false;
        }
    }

    @Override // com.applovin.impl.sdk.AppLovinAdBase
    public String toString() {
        return "AppLovinAd{ #" + getAdIdNumber() + ", adType=" + getType() + ", adSize=" + getSize() + ", zoneId='" + d() + "'}";
    }
}
