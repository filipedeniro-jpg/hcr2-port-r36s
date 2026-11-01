package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbkq<T> {
    private final zzccz zzffk;
    private final zzcdg zzffl;
    private final zzdvv<zzdcp<zzaok>> zzffm;
    private final zzcvk zzffn;
    private final zzcxn zzffo;
    private final zzbfz zzffp;
    private final zzciq<T> zzffq;
    private final zzboq zzffr;

    @Nullable
    private final zzcvj zzffs;

    zzbkq(zzccz zzcczVar, zzcdg zzcdgVar, zzdvv<zzdcp<zzaok>> zzdvvVar, zzcvk zzcvkVar, zzcxn zzcxnVar, zzbfz zzbfzVar, zzciq<T> zzciqVar, zzboq zzboqVar, @Nullable zzcvj zzcvjVar) {
        this.zzffk = zzcczVar;
        this.zzffl = zzcdgVar;
        this.zzffm = zzdvvVar;
        this.zzffn = zzcvkVar;
        this.zzffo = zzcxnVar;
        this.zzffp = zzbfzVar;
        this.zzffq = zzciqVar;
        this.zzffr = zzboqVar;
        this.zzffs = zzcvjVar;
    }

    public final zzdcp<T> zzafo() {
        zzcww zzcwwVarZzano;
        if (this.zzffs != null) {
            zzcwwVarZzano = this.zzffo.zzu(zzcxk.SERVER_TRANSACTION).zzb(zzdcf.zzah(this.zzffs)).zzano();
        } else {
            com.google.android.gms.ads.internal.zzp.zzke().zzmf();
            if (this.zzffn.zzgjs.zzcca != null) {
                zzcwwVarZzano = this.zzffo.zzu(zzcxk.SERVER_TRANSACTION).zzb(this.zzffl.zzake()).zzano();
            } else {
                zzcwwVarZzano = this.zzffo.zza(zzcxk.SERVER_TRANSACTION, this.zzffm.get()).zza(this.zzffk).zzano();
            }
        }
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcrh)).booleanValue()) {
            return this.zzffo.zza(zzcxk.RENDERER, zzcwwVarZzano).zza(this.zzffp).zza(this.zzffq).zzano();
        }
        return this.zzffo.zza(zzcxk.RENDERER, zzcwwVarZzano).zza(this.zzffp).zza(this.zzffq).zza(((Integer) zzuo.zzoj().zzd(zzyt.zzcri)).intValue(), TimeUnit.SECONDS).zzano();
    }
}
