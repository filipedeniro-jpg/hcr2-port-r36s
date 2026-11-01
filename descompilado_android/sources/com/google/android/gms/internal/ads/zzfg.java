package com.google.android.gms.internal.ads;

import android.view.View;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzfg extends zzfl {
    private final View zzzs;

    public zzfg(zzdx zzdxVar, String str, String str2, zzbo.zza.zzb zzbVar, int i, int i2, View view) {
        super(zzdxVar, str, str2, zzbVar, i, 57);
        this.zzzs = view;
    }

    @Override // com.google.android.gms.internal.ads.zzfl
    protected final void zzct() throws IllegalAccessException, InvocationTargetException {
        if (this.zzzs != null) {
            Boolean bool = (Boolean) zzuo.zzoj().zzd(zzyt.zzcng);
            zzeh zzehVar = new zzeh((String) this.zzaal.invoke(null, this.zzzs, this.zzvo.getContext().getResources().getDisplayMetrics(), bool));
            zzbo.zza.zzf.C0044zza c0044zzaZzar = zzbo.zza.zzf.zzar();
            c0044zzaZzar.zzdc(zzehVar.zzzm.longValue()).zzdd(zzehVar.zzzn.longValue()).zzde(zzehVar.zzzo.longValue());
            if (bool.booleanValue()) {
                c0044zzaZzar.zzdf(zzehVar.zzzp.longValue());
            }
            this.zzaaa.zzb((zzbo.zza.zzf) ((zzdqd) c0044zzaZzar.zzazm()));
        }
    }
}
