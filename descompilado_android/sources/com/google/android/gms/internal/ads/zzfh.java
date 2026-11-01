package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzfh extends zzfl {
    public zzfh(zzdx zzdxVar, String str, String str2, zzbo.zza.zzb zzbVar, int i, int i2) {
        super(zzdxVar, str, str2, zzbVar, i, 48);
    }

    @Override // com.google.android.gms.internal.ads.zzfl
    protected final void zzct() throws IllegalAccessException, InvocationTargetException {
        this.zzaaa.zze(zzbz.ENUM_FAILURE);
        boolean zBooleanValue = ((Boolean) this.zzaal.invoke(null, this.zzvo.getContext())).booleanValue();
        synchronized (this.zzaaa) {
            try {
                if (zBooleanValue) {
                    this.zzaaa.zze(zzbz.ENUM_TRUE);
                } else {
                    this.zzaaa.zze(zzbz.ENUM_FALSE);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
