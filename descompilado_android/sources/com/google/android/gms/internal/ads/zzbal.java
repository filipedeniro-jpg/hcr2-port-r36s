package com.google.android.gms.internal.ads;

import com.my.target.bf;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzbal implements Runnable {
    private final /* synthetic */ String zzdtz;
    private final /* synthetic */ String zzect;
    private final /* synthetic */ int zzecv;
    private final /* synthetic */ zzbah zzecx;

    zzbal(zzbah zzbahVar, String str, String str2, int i) {
        this.zzecx = zzbahVar;
        this.zzdtz = str;
        this.zzect = str2;
        this.zzecv = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        HashMap map = new HashMap();
        map.put("event", "precacheComplete");
        map.put(bf.a.fE, this.zzdtz);
        map.put("cachedSrc", this.zzect);
        map.put("totalBytes", Integer.toString(this.zzecv));
        this.zzecx.zza("onPrecacheEvent", (Map<String, String>) map);
    }
}
