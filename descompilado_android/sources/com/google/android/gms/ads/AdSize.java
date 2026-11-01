package com.google.android.gms.ads;

import android.content.Context;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzawe;
import com.google.android.gms.internal.ads.zztw;
import com.google.android.gms.internal.ads.zzuo;
import com.tonyodev.fetch.FetchService;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@VisibleForTesting
public final class AdSize {
    public static final int AUTO_HEIGHT = -2;
    public static final int FULL_WIDTH = -1;
    private final int height;
    private final int width;
    private final String zzabd;
    private boolean zzabe;
    public static final AdSize BANNER = new AdSize(FetchService.ACTION_LOGGING, 50, "320x50_mb");
    public static final AdSize FULL_BANNER = new AdSize(468, 60, "468x60_as");
    public static final AdSize LARGE_BANNER = new AdSize(FetchService.ACTION_LOGGING, 100, "320x100_as");
    public static final AdSize LEADERBOARD = new AdSize(728, 90, "728x90_as");
    public static final AdSize MEDIUM_RECTANGLE = new AdSize(300, 250, "300x250_as");
    public static final AdSize WIDE_SKYSCRAPER = new AdSize(160, 600, "160x600_as");
    public static final AdSize SMART_BANNER = new AdSize(-1, -2, "smart_banner");
    public static final AdSize FLUID = new AdSize(-3, -4, "fluid");
    public static final AdSize INVALID = new AdSize(0, 0, "invalid");
    public static final AdSize zzabc = new AdSize(50, 50, "50x50_mb");
    public static final AdSize SEARCH = new AdSize(-3, 0, "search_v2");

    public AdSize(int i, int i2) {
        String strValueOf = i == -1 ? "FULL" : String.valueOf(i);
        String strValueOf2 = i2 == -2 ? "AUTO" : String.valueOf(i2);
        StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 4 + String.valueOf(strValueOf2).length());
        sb.append(strValueOf);
        sb.append("x");
        sb.append(strValueOf2);
        sb.append("_as");
        this(i, i2, sb.toString());
    }

    AdSize(int i, int i2, String str) {
        if (i < 0 && i != -1 && i != -3) {
            StringBuilder sb = new StringBuilder(37);
            sb.append("Invalid width for AdSize: ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        }
        if (i2 < 0 && i2 != -2 && i2 != -4) {
            StringBuilder sb2 = new StringBuilder(38);
            sb2.append("Invalid height for AdSize: ");
            sb2.append(i2);
            throw new IllegalArgumentException(sb2.toString());
        }
        this.width = i;
        this.height = i2;
        this.zzabd = str;
    }

    public static AdSize getPortraitBannerAdSizeWithWidth(Context context, int i) {
        AdSize adSizeZza = zzawe.zza(context, i, 50, 1);
        adSizeZza.zzabe = true;
        return adSizeZza;
    }

    public static AdSize getLandscapeBannerAdSizeWithWidth(Context context, int i) {
        AdSize adSizeZza = zzawe.zza(context, i, 50, 2);
        adSizeZza.zzabe = true;
        return adSizeZza;
    }

    public static AdSize getCurrentOrientationBannerAdSizeWithWidth(Context context, int i) {
        AdSize adSizeZza = zzawe.zza(context, i, 50, 0);
        adSizeZza.zzabe = true;
        return adSizeZza;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AdSize)) {
            return false;
        }
        AdSize adSize = (AdSize) obj;
        return this.width == adSize.width && this.height == adSize.height && this.zzabd.equals(adSize.zzabd);
    }

    public final int getHeight() {
        return this.height;
    }

    public final int getHeightInPixels(Context context) {
        int i = this.height;
        if (i == -4 || i == -3) {
            return -1;
        }
        if (i == -2) {
            return zztw.zzc(context.getResources().getDisplayMetrics());
        }
        zzuo.zzof();
        return zzawe.zza(context, this.height);
    }

    public final int getWidth() {
        return this.width;
    }

    public final int getWidthInPixels(Context context) {
        int i = this.width;
        if (i == -4 || i == -3) {
            return -1;
        }
        if (i == -1) {
            return zztw.zzb(context.getResources().getDisplayMetrics());
        }
        zzuo.zzof();
        return zzawe.zza(context, this.width);
    }

    public final int hashCode() {
        return this.zzabd.hashCode();
    }

    public final boolean isAutoHeight() {
        return this.height == -2;
    }

    public final boolean isFullWidth() {
        return this.width == -1;
    }

    public final boolean isFluid() {
        return this.width == -3 && this.height == -4;
    }

    public final boolean zzdb() {
        return this.zzabe;
    }

    public final String toString() {
        return this.zzabd;
    }
}
