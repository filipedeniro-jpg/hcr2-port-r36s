package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbyr {
    private final zzawv zzblh;
    private final zzbbl zzblw;
    private final zzrz zzeeb;
    private final zzdf zzefm;
    private final com.google.android.gms.ads.internal.zzb zzefo;
    private final zzrb zzefq;
    private final zzcvk zzffn;
    private final zzboa zzfpz;
    private final Context zzlk;

    public zzbyr(zzbbl zzbblVar, Context context, zzcvk zzcvkVar, zzdf zzdfVar, zzawv zzawvVar, com.google.android.gms.ads.internal.zzb zzbVar, zzrz zzrzVar, zzboa zzboaVar, zzbra zzbraVar) {
        this.zzblw = zzbblVar;
        this.zzlk = context;
        this.zzffn = zzcvkVar;
        this.zzefm = zzdfVar;
        this.zzblh = zzawvVar;
        this.zzefo = zzbVar;
        this.zzeeb = zzrzVar;
        this.zzfpz = zzboaVar;
        this.zzefq = zzbraVar;
    }

    public final zzbbc zzc(zztw zztwVar) throws zzbbp {
        return zza(zztwVar, false);
    }

    public final zzbbc zza(zztw zztwVar, boolean z) throws zzbbp {
        return zzbbl.zza(this.zzlk, zzbct.zzb(zztwVar), zztwVar.zzabd, false, false, this.zzefm, this.zzblh, null, new zzbyu(this), this.zzefo, this.zzeeb, this.zzefq, z);
    }
}
