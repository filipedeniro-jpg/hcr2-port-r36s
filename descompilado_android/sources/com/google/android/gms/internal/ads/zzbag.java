package com.google.android.gms.internal.ads;

import com.facebook.appevents.AppEventsConstants;
import com.my.target.bf;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzbag implements Runnable {
    private final /* synthetic */ String zzdtz;
    private final /* synthetic */ String zzect;
    private final /* synthetic */ int zzecu;
    private final /* synthetic */ int zzecv;
    private final /* synthetic */ boolean zzecw = false;
    private final /* synthetic */ zzbah zzecx;

    zzbag(zzbah zzbahVar, String str, String str2, int i, int i2, boolean z) {
        this.zzecx = zzbahVar;
        this.zzdtz = str;
        this.zzect = str2;
        this.zzecu = i;
        this.zzecv = i2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        HashMap map = new HashMap();
        map.put("event", "precacheProgress");
        map.put(bf.a.fE, this.zzdtz);
        map.put("cachedSrc", this.zzect);
        map.put("bytesLoaded", Integer.toString(this.zzecu));
        map.put("totalBytes", Integer.toString(this.zzecv));
        map.put("cacheReady", this.zzecw ? "1" : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        this.zzecx.zza("onPrecacheEvent", (Map<String, String>) map);
    }
}
