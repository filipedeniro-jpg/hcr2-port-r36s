package com.google.android.gms.ads.mediation.customevent;

import com.google.ads.mediation.NetworkExtras;
import java.util.HashMap;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@Deprecated
public final class CustomEventExtras implements NetworkExtras {
    private final HashMap<String, Object> zzeje = new HashMap<>();

    public final Object getExtra(String str) {
        return this.zzeje.get(str);
    }

    public final void setExtra(String str, Object obj) {
        this.zzeje.put(str, obj);
    }
}
