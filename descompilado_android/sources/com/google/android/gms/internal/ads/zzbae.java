package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbae implements Iterable<zzbac> {
    private final List<zzbac> zzecr = new ArrayList();

    public static boolean zzc(zzayt zzaytVar) {
        zzbac zzbacVarZzd = zzd(zzaytVar);
        if (zzbacVarZzd == null) {
            return false;
        }
        zzbacVarZzd.zzecq.abort();
        return true;
    }

    static zzbac zzd(zzayt zzaytVar) {
        for (zzbac zzbacVar : com.google.android.gms.ads.internal.zzp.zzku()) {
            if (zzbacVar.zzdxm == zzaytVar) {
                return zzbacVar;
            }
        }
        return null;
    }

    public final void zza(zzbac zzbacVar) {
        this.zzecr.add(zzbacVar);
    }

    public final void zzb(zzbac zzbacVar) {
        this.zzecr.remove(zzbacVar);
    }

    @Override // java.lang.Iterable
    public final Iterator<zzbac> iterator() {
        return this.zzecr.iterator();
    }
}
