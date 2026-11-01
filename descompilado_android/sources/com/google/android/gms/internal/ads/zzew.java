package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzew extends zzfl {
    private static volatile String zzaac;
    private static final Object zzzz = new Object();

    public zzew(zzdx zzdxVar, String str, String str2, zzbo.zza.zzb zzbVar, int i, int i2) {
        super(zzdxVar, str, str2, zzbVar, i, 1);
    }

    @Override // com.google.android.gms.internal.ads.zzfl
    protected final void zzct() throws IllegalAccessException, InvocationTargetException {
        this.zzaaa.zzz("E");
        if (zzaac == null) {
            synchronized (zzzz) {
                if (zzaac == null) {
                    zzaac = (String) this.zzaal.invoke(null, new Object[0]);
                }
            }
        }
        synchronized (this.zzaaa) {
            this.zzaaa.zzz(zzaac);
        }
    }
}
