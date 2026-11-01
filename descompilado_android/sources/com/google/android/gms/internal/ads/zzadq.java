package com.google.android.gms.internal.ads;

import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzadq implements zzadx<zzbbc> {
    zzadq() {
    }

    @Override // com.google.android.gms.internal.ads.zzadx
    public final /* synthetic */ void zza(zzbbc zzbbcVar, Map map) {
        zzbbc zzbbcVar2 = zzbbcVar;
        if (zzbbcVar2.zzzm() != null) {
            zzbbcVar2.zzzm().zzmd();
        }
        com.google.android.gms.ads.internal.overlay.zze zzeVarZzyu = zzbbcVar2.zzyu();
        if (zzeVarZzyu != null) {
            zzeVarZzyu.close();
            return;
        }
        com.google.android.gms.ads.internal.overlay.zze zzeVarZzyv = zzbbcVar2.zzyv();
        if (zzeVarZzyv != null) {
            zzeVarZzyv.close();
        } else {
            zzatm.zzeu("A GMSG tried to close something that wasn't an overlay.");
        }
    }
}
