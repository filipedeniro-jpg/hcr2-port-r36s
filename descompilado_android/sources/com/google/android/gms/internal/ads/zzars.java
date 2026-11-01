package com.google.android.gms.internal.ads;

import android.content.Context;
import android.text.TextUtils;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzars implements zzpl {
    private final Object lock;
    private String zzccm;
    private final Context zzcft;
    private boolean zzdos;

    public zzars(Context context, String str) {
        this.zzcft = context.getApplicationContext() != null ? context.getApplicationContext() : context;
        this.zzccm = str;
        this.zzdos = false;
        this.lock = new Object();
    }

    public final void zzaf(boolean z) {
        if (com.google.android.gms.ads.internal.zzp.zzkw().zzaa(this.zzcft)) {
            synchronized (this.lock) {
                if (this.zzdos == z) {
                    return;
                }
                this.zzdos = z;
                if (TextUtils.isEmpty(this.zzccm)) {
                    return;
                }
                if (this.zzdos) {
                    com.google.android.gms.ads.internal.zzp.zzkw().zze(this.zzcft, this.zzccm);
                } else {
                    com.google.android.gms.ads.internal.zzp.zzkw().zzf(this.zzcft, this.zzccm);
                }
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzpl
    public final void zza(zzpi zzpiVar) {
        zzaf(zzpiVar.zzbnr);
    }

    public final String getAdUnitId() {
        return this.zzccm;
    }
}
