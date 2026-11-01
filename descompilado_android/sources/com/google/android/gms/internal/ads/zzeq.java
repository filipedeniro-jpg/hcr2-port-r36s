package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzeq extends zzfl {
    private long startTime;

    public zzeq(zzdx zzdxVar, String str, String str2, zzbo.zza.zzb zzbVar, long j, int i, int i2) {
        super(zzdxVar, str, str2, zzbVar, i, 25);
        this.startTime = j;
    }

    @Override // com.google.android.gms.internal.ads.zzfl
    protected final void zzct() throws IllegalAccessException, InvocationTargetException {
        long jLongValue = ((Long) this.zzaal.invoke(null, new Object[0])).longValue();
        synchronized (this.zzaaa) {
            this.zzaaa.zzbr(jLongValue);
            if (this.startTime != 0) {
                this.zzaaa.zzat(jLongValue - this.startTime);
                this.zzaaa.zzaw(this.startTime);
            }
        }
    }
}
