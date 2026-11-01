package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.text.TextUtils;
import com.ironsource.sdk.constants.Constants;
import com.my.target.aa;
import com.my.target.ai;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcar {
    private ConcurrentHashMap<String, String> zzfrk;

    public zzcar(zzcay zzcayVar) {
        this.zzfrk = zzcayVar.zzajs();
    }

    public final void zzb(zzcvj zzcvjVar) {
        if (zzcvjVar.zzgjp.zzgjl.size() > 0) {
            int i = zzcvjVar.zzgjp.zzgjl.get(0).zzfif;
            if (i == 1) {
                this.zzfrk.put("ad_format", ai.a.cW);
            } else if (i == 2) {
                this.zzfrk.put("ad_format", aa.e.bo);
            } else if (i == 3) {
                this.zzfrk.put("ad_format", "native_express");
            } else if (i == 4) {
                this.zzfrk.put("ad_format", "native_advanced");
            } else if (i == 5) {
                this.zzfrk.put("ad_format", Constants.CONVERT_REWARDED);
            } else {
                this.zzfrk.put("ad_format", "unknown");
            }
            if (TextUtils.isEmpty(zzcvjVar.zzgjp.zzgjm.zzbzx)) {
                return;
            }
            this.zzfrk.put("gqi", zzcvjVar.zzgjp.zzgjm.zzbzx);
        }
    }

    public final void zzi(Bundle bundle) {
        if (bundle.containsKey("cnt")) {
            this.zzfrk.put("network_coarse", Integer.toString(bundle.getInt("cnt")));
        }
        if (bundle.containsKey("gnt")) {
            this.zzfrk.put("network_fine", Integer.toString(bundle.getInt("gnt")));
        }
    }

    public final Map<String, String> zzpx() {
        return this.zzfrk;
    }
}
