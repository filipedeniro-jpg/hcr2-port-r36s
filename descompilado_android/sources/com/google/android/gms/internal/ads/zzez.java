package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzez extends zzfl {
    public zzez(zzdx zzdxVar, String str, String str2, zzbo.zza.zzb zzbVar, int i, int i2) {
        super(zzdxVar, str, str2, zzbVar, i, 3);
    }

    @Override // com.google.android.gms.internal.ads.zzfl
    protected final void zzct() throws IllegalAccessException, InvocationTargetException {
        zzdj zzdjVar = new zzdj((String) this.zzaal.invoke(null, this.zzvo.getContext(), Boolean.valueOf(((Boolean) zzuo.zzoj().zzd(zzyt.zzcmw)).booleanValue())));
        synchronized (this.zzaaa) {
            this.zzaaa.zzal(zzdjVar.zzxe);
            this.zzaaa.zzbn(zzdjVar.zzxf);
        }
    }
}
