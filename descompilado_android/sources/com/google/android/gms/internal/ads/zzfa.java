package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzfa extends zzfl {
    private final boolean zzaae;

    public zzfa(zzdx zzdxVar, String str, String str2, zzbo.zza.zzb zzbVar, int i, int i2) {
        super(zzdxVar, str, str2, zzbVar, i, 61);
        this.zzaae = zzdxVar.zzck();
    }

    @Override // com.google.android.gms.internal.ads.zzfl
    protected final void zzct() throws IllegalAccessException, InvocationTargetException {
        long jLongValue = ((Long) this.zzaal.invoke(null, this.zzvo.getContext(), Boolean.valueOf(this.zzaae))).longValue();
        synchronized (this.zzaaa) {
            this.zzaaa.zzbo(jLongValue);
        }
    }
}
