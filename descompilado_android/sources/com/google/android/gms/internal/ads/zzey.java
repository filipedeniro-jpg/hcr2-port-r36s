package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzey extends zzfl {
    public zzey(zzdx zzdxVar, String str, String str2, zzbo.zza.zzb zzbVar, int i, int i2) {
        super(zzdxVar, str, str2, zzbVar, i, 73);
    }

    @Override // com.google.android.gms.internal.ads.zzfl
    protected final void zzct() throws IllegalAccessException, InvocationTargetException {
        try {
            this.zzaaa.zzh(((Boolean) this.zzaal.invoke(null, this.zzvo.getContext())).booleanValue() ? zzbz.ENUM_TRUE : zzbz.ENUM_FALSE);
        } catch (InvocationTargetException unused) {
            this.zzaaa.zzh(zzbz.ENUM_FAILURE);
        }
    }
}
