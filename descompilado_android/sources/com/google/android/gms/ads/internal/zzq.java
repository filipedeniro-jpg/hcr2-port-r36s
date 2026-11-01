package com.google.android.gms.ads.internal;

import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.internal.ads.zzawv;
import com.google.android.gms.internal.ads.zztp;
import com.google.android.gms.internal.ads.zzuo;
import com.google.android.gms.internal.ads.zzyt;
import com.ironsource.sdk.constants.Constants;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzq {
    private final String zzbmx;
    private final Map<String, String> zzbmy = new TreeMap();
    private String zzbmz;
    private String zzbna;

    public zzq(String str) {
        this.zzbmx = str;
    }

    public final String zzkx() {
        return this.zzbna;
    }

    public final String getQuery() {
        return this.zzbmz;
    }

    public final String zzky() {
        return this.zzbmx;
    }

    public final Map<String, String> zzkz() {
        return this.zzbmy;
    }

    public final void zza(zztp zztpVar, zzawv zzawvVar) {
        this.zzbmz = zztpVar.zzcbs.zzbmz;
        Bundle bundle = zztpVar.zzcbu != null ? zztpVar.zzcbu.getBundle(AdMobAdapter.class.getName()) : null;
        if (bundle == null) {
            return;
        }
        String str = (String) zzuo.zzoj().zzd(zzyt.zzcow);
        for (String str2 : bundle.keySet()) {
            if (str.equals(str2)) {
                this.zzbna = bundle.getString(str2);
            } else if (str2.startsWith("csa_")) {
                this.zzbmy.put(str2.substring(4), bundle.getString(str2));
            }
        }
        this.zzbmy.put(Constants.RequestParameters.SDK_VERSION, zzawvVar.zzbnh);
    }
}
