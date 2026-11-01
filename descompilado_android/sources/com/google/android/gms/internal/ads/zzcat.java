package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcat implements zzcxt {
    private final Clock zzbmd;
    private final zzcar zzfrn;
    private final Map<zzcxk, Long> zzfrm = new HashMap();
    private final Map<zzcxk, zzcaw> zzfro = new HashMap();

    public zzcat(zzcar zzcarVar, Set<zzcaw> set, Clock clock) {
        this.zzfrn = zzcarVar;
        for (zzcaw zzcawVar : set) {
            this.zzfro.put(zzcawVar.zzfrs, zzcawVar);
        }
        this.zzbmd = clock;
    }

    @Override // com.google.android.gms.internal.ads.zzcxt
    public final void zza(zzcxk zzcxkVar, String str) {
    }

    @Override // com.google.android.gms.internal.ads.zzcxt
    public final void zzb(zzcxk zzcxkVar, String str) {
        this.zzfrm.put(zzcxkVar, Long.valueOf(this.zzbmd.elapsedRealtime()));
    }

    @Override // com.google.android.gms.internal.ads.zzcxt
    public final void zza(zzcxk zzcxkVar, String str, Throwable th) {
        if (this.zzfrm.containsKey(zzcxkVar)) {
            long jElapsedRealtime = this.zzbmd.elapsedRealtime() - this.zzfrm.get(zzcxkVar).longValue();
            Map<String, String> mapZzpx = this.zzfrn.zzpx();
            String strValueOf = String.valueOf(str);
            String strConcat = strValueOf.length() != 0 ? "task.".concat(strValueOf) : new String("task.");
            String strValueOf2 = String.valueOf(Long.toString(jElapsedRealtime));
            mapZzpx.put(strConcat, strValueOf2.length() != 0 ? "f.".concat(strValueOf2) : new String("f."));
        }
        if (this.zzfro.containsKey(zzcxkVar)) {
            zza(zzcxkVar, false);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcxt
    public final void zzc(zzcxk zzcxkVar, String str) {
        if (this.zzfrm.containsKey(zzcxkVar)) {
            long jElapsedRealtime = this.zzbmd.elapsedRealtime() - this.zzfrm.get(zzcxkVar).longValue();
            Map<String, String> mapZzpx = this.zzfrn.zzpx();
            String strValueOf = String.valueOf(str);
            String strConcat = strValueOf.length() != 0 ? "task.".concat(strValueOf) : new String("task.");
            String strValueOf2 = String.valueOf(Long.toString(jElapsedRealtime));
            mapZzpx.put(strConcat, strValueOf2.length() != 0 ? "s.".concat(strValueOf2) : new String("s."));
        }
        if (this.zzfro.containsKey(zzcxkVar)) {
            zza(zzcxkVar, true);
        }
    }

    private final void zza(zzcxk zzcxkVar, boolean z) {
        zzcxk zzcxkVar2 = this.zzfro.get(zzcxkVar).zzfrr;
        String str = z ? "s." : "f.";
        if (this.zzfrm.containsKey(zzcxkVar2)) {
            long jElapsedRealtime = this.zzbmd.elapsedRealtime() - this.zzfrm.get(zzcxkVar2).longValue();
            Map<String, String> mapZzpx = this.zzfrn.zzpx();
            String strValueOf = String.valueOf(this.zzfro.get(zzcxkVar).label);
            String strConcat = strValueOf.length() != 0 ? "label.".concat(strValueOf) : new String("label.");
            String strValueOf2 = String.valueOf(Long.toString(jElapsedRealtime));
            mapZzpx.put(strConcat, strValueOf2.length() != 0 ? str.concat(strValueOf2) : new String(str));
        }
    }
}
