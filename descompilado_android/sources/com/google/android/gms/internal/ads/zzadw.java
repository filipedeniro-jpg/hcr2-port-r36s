package com.google.android.gms.internal.ads;

import com.my.target.m;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzadw implements zzadx<zzbbc> {
    zzadw() {
    }

    @Override // com.google.android.gms.internal.ads.zzadx
    public final /* synthetic */ void zza(zzbbc zzbbcVar, Map map) {
        zzbbc zzbbcVar2 = zzbbcVar;
        if (map.keySet().contains("start")) {
            zzbbcVar2.zzyy().zzzx();
        } else if (map.keySet().contains(m.at)) {
            zzbbcVar2.zzyy().zzzy();
        } else if (map.keySet().contains("cancel")) {
            zzbbcVar2.zzyy().zzzz();
        }
    }
}
