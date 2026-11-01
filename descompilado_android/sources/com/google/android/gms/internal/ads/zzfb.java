package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzfb extends zzfl {
    private List<Long> zzaaf;

    public zzfb(zzdx zzdxVar, String str, String str2, zzbo.zza.zzb zzbVar, int i, int i2) {
        super(zzdxVar, str, str2, zzbVar, i, 31);
        this.zzaaf = null;
    }

    @Override // com.google.android.gms.internal.ads.zzfl
    protected final void zzct() throws IllegalAccessException, InvocationTargetException {
        this.zzaaa.zzax(-1L);
        this.zzaaa.zzay(-1L);
        if (this.zzaaf == null) {
            this.zzaaf = (List) this.zzaal.invoke(null, this.zzvo.getContext());
        }
        List<Long> list = this.zzaaf;
        if (list == null || list.size() != 2) {
            return;
        }
        synchronized (this.zzaaa) {
            this.zzaaa.zzax(this.zzaaf.get(0).longValue());
            this.zzaaa.zzay(this.zzaaf.get(1).longValue());
        }
    }
}
