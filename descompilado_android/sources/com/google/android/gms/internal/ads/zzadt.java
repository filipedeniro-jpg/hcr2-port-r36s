package com.google.android.gms.internal.ads;

import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzadt implements zzadx<zzbbc> {
    zzadt() {
    }

    @Override // com.google.android.gms.internal.ads.zzadx
    public final /* synthetic */ void zza(zzbbc zzbbcVar, Map map) {
        zzbbc zzbbcVar2 = zzbbcVar;
        String str = (String) map.get("action");
        if ("pause".equals(str)) {
            zzbbcVar2.zzju();
        } else if ("resume".equals(str)) {
            zzbbcVar2.zzjv();
        }
    }
}
