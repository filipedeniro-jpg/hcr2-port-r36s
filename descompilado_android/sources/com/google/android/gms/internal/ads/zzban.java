package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import com.my.target.bf;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzban implements Runnable {
    private final /* synthetic */ String val$message;
    private final /* synthetic */ String zzdtz;
    private final /* synthetic */ String zzect;
    private final /* synthetic */ zzbah zzecx;
    private final /* synthetic */ String zzedd;

    zzban(zzbah zzbahVar, String str, String str2, String str3, String str4) {
        this.zzecx = zzbahVar;
        this.zzdtz = str;
        this.zzect = str2;
        this.zzedd = str3;
        this.val$message = str4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        HashMap map = new HashMap();
        map.put("event", "precacheCanceled");
        map.put(bf.a.fE, this.zzdtz);
        if (!TextUtils.isEmpty(this.zzect)) {
            map.put("cachedSrc", this.zzect);
        }
        zzbah zzbahVar = this.zzecx;
        map.put("type", zzbah.zzff(this.zzedd));
        map.put(IronSourceConstants.EVENTS_ERROR_REASON, this.zzedd);
        if (!TextUtils.isEmpty(this.val$message)) {
            map.put("message", this.val$message);
        }
        this.zzecx.zza("onPrecacheEvent", (Map<String, String>) map);
    }
}
