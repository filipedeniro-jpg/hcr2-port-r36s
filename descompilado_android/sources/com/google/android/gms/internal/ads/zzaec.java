package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.CollectionUtils;
import com.my.target.ab;
import com.my.target.i;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzaec implements zzadx<zzbbc> {
    private static final Map<String, Integer> zzcxq = CollectionUtils.mapOfKeyValueArrays(new String[]{ab.bA, ab.bE, ab.bF, ab.bG, ab.bD, "closeResizedAd", "unload"}, new Integer[]{1, 2, 3, 4, 5, 6, 7});
    private final com.google.android.gms.ads.internal.zza zzcxn;
    private final zzamf zzcxo;
    private final zzams zzcxp;

    public zzaec(com.google.android.gms.ads.internal.zza zzaVar, zzamf zzamfVar, zzams zzamsVar) {
        this.zzcxn = zzaVar;
        this.zzcxo = zzamfVar;
        this.zzcxp = zzamsVar;
    }

    @Override // com.google.android.gms.internal.ads.zzadx
    public final /* synthetic */ void zza(zzbbc zzbbcVar, Map map) {
        com.google.android.gms.ads.internal.zza zzaVar;
        zzbbc zzbbcVar2 = zzbbcVar;
        int iIntValue = zzcxq.get((String) map.get(i.I)).intValue();
        if (iIntValue != 5 && iIntValue != 7 && (zzaVar = this.zzcxn) != null && !zzaVar.zzjh()) {
            this.zzcxn.zzbl(null);
            return;
        }
        if (iIntValue == 1) {
            this.zzcxo.zzg(map);
            return;
        }
        if (iIntValue == 3) {
            new zzamk(zzbbcVar2, map).execute();
            return;
        }
        if (iIntValue == 4) {
            new zzame(zzbbcVar2, map).execute();
            return;
        }
        if (iIntValue == 5) {
            new zzamh(zzbbcVar2, map).execute();
            return;
        }
        if (iIntValue == 6) {
            this.zzcxo.zzv(true);
        } else if (iIntValue == 7) {
            this.zzcxp.zzsi();
        } else {
            zzatm.zzet("Unknown MRAID command called.");
        }
    }
}
