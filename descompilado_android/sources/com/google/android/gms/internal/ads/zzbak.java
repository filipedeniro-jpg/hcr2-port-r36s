package com.google.android.gms.internal.ads;

import com.my.target.bf;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzbak implements Runnable {
    private final /* synthetic */ String zzdtz;
    private final /* synthetic */ String zzect;
    private final /* synthetic */ zzbah zzecx;
    private final /* synthetic */ long zzeda;

    zzbak(zzbah zzbahVar, String str, String str2, long j) {
        this.zzecx = zzbahVar;
        this.zzdtz = str;
        this.zzect = str2;
        this.zzeda = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        HashMap map = new HashMap();
        map.put("event", "precacheComplete");
        map.put(bf.a.fE, this.zzdtz);
        map.put("cachedSrc", this.zzect);
        map.put("totalDuration", Long.toString(this.zzeda));
        this.zzecx.zza("onPrecacheEvent", (Map<String, String>) map);
    }
}
