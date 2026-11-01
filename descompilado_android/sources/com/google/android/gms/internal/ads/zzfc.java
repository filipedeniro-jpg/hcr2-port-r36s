package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzfc extends zzfl {
    public zzfc(zzdx zzdxVar, String str, String str2, zzbo.zza.zzb zzbVar, int i, int i2) {
        super(zzdxVar, str, str2, zzbVar, i, 51);
    }

    @Override // com.google.android.gms.internal.ads.zzfl
    protected final void zzct() throws IllegalAccessException, InvocationTargetException {
        synchronized (this.zzaaa) {
            zzdy zzdyVar = new zzdy((String) this.zzaal.invoke(null, new Object[0]));
            this.zzaaa.zzbj(zzdyVar.zzyh.longValue());
            this.zzaaa.zzbk(zzdyVar.zzyi.longValue());
        }
    }
}
