package com.google.android.gms.internal.ads;

import android.location.Location;
import androidx.annotation.Nullable;
import com.facebook.internal.ServerProtocol;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.mediation.NativeMediationAdRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzaki implements NativeMediationAdRequest {
    private final String zzabl;
    private final int zzcbo;
    private final boolean zzcbz;
    private final int zzddj;
    private final int zzddk;
    private final zzaai zzddy;
    private final List<String> zzddz = new ArrayList();
    private final Map<String, Boolean> zzdea = new HashMap();
    private final Date zznc;
    private final Set<String> zzne;
    private final boolean zznf;
    private final Location zzng;

    public zzaki(@Nullable Date date, int i, @Nullable Set<String> set, @Nullable Location location, boolean z, int i2, zzaai zzaaiVar, List<String> list, boolean z2, int i3, String str) {
        this.zznc = date;
        this.zzcbo = i;
        this.zzne = set;
        this.zzng = location;
        this.zznf = z;
        this.zzddj = i2;
        this.zzddy = zzaaiVar;
        this.zzcbz = z2;
        this.zzddk = i3;
        this.zzabl = str;
        if (list != null) {
            for (String str2 : list) {
                if (str2.startsWith("custom:")) {
                    String[] strArrSplit = str2.split(":", 3);
                    if (strArrSplit.length == 3) {
                        if (ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(strArrSplit[2])) {
                            this.zzdea.put(strArrSplit[1], true);
                        } else if ("false".equals(strArrSplit[2])) {
                            this.zzdea.put(strArrSplit[1], false);
                        }
                    }
                } else {
                    this.zzddz.add(str2);
                }
            }
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdRequest
    @Deprecated
    public final Date getBirthday() {
        return this.zznc;
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdRequest
    @Deprecated
    public final int getGender() {
        return this.zzcbo;
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdRequest
    public final Set<String> getKeywords() {
        return this.zzne;
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdRequest
    public final Location getLocation() {
        return this.zzng;
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdRequest
    public final boolean isTesting() {
        return this.zznf;
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdRequest
    public final int taggedForChildDirectedTreatment() {
        return this.zzddj;
    }

    @Override // com.google.android.gms.ads.mediation.NativeMediationAdRequest
    public final NativeAdOptions getNativeAdOptions() {
        if (this.zzddy == null) {
            return null;
        }
        NativeAdOptions.Builder requestMultipleImages = new NativeAdOptions.Builder().setReturnUrlsForImageAssets(this.zzddy.zzcvn).setImageOrientation(this.zzddy.zzbjt).setRequestMultipleImages(this.zzddy.zzbjv);
        if (this.zzddy.versionCode >= 2) {
            requestMultipleImages.setAdChoicesPlacement(this.zzddy.zzbjw);
        }
        if (this.zzddy.versionCode >= 3 && this.zzddy.zzcvo != null) {
            requestMultipleImages.setVideoOptions(new VideoOptions(this.zzddy.zzcvo));
        }
        return requestMultipleImages.build();
    }

    @Override // com.google.android.gms.ads.mediation.NativeMediationAdRequest
    public final boolean isAdMuted() {
        return zzwv.zzpd().zzop();
    }

    @Override // com.google.android.gms.ads.mediation.NativeMediationAdRequest
    public final float getAdVolume() {
        return zzwv.zzpd().zzoo();
    }

    @Override // com.google.android.gms.ads.mediation.NativeMediationAdRequest
    public final boolean isAppInstallAdRequested() {
        List<String> list = this.zzddz;
        if (list != null) {
            return list.contains("2") || this.zzddz.contains("6");
        }
        return false;
    }

    @Override // com.google.android.gms.ads.mediation.NativeMediationAdRequest
    public final boolean isUnifiedNativeAdRequested() {
        List<String> list = this.zzddz;
        return list != null && list.contains("6");
    }

    @Override // com.google.android.gms.ads.mediation.NativeMediationAdRequest
    public final boolean isContentAdRequested() {
        List<String> list = this.zzddz;
        if (list != null) {
            return list.contains("1") || this.zzddz.contains("6");
        }
        return false;
    }

    @Override // com.google.android.gms.ads.mediation.NativeMediationAdRequest
    public final boolean zzrz() {
        List<String> list = this.zzddz;
        return list != null && list.contains("3");
    }

    @Override // com.google.android.gms.ads.mediation.NativeMediationAdRequest
    public final Map<String, Boolean> zzsa() {
        return this.zzdea;
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdRequest
    @Deprecated
    public final boolean isDesignedForFamilies() {
        return this.zzcbz;
    }
}
