package com.google.android.gms.internal.ads;

import android.content.Context;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@ParametersAreNonnullByDefault
public final class zzahy {
    private final Object zzdai = new Object();
    private final Object zzdaj = new Object();

    @GuardedBy("lockClient")
    private zzaid zzdak;

    @GuardedBy("lockService")
    private zzaid zzdal;

    public final zzaid zza(Context context, zzawv zzawvVar) {
        zzaid zzaidVar;
        synchronized (this.zzdaj) {
            if (this.zzdal == null) {
                this.zzdal = new zzaid(zzk(context), zzawvVar, (String) zzuo.zzoj().zzd(zzyt.zzcfy));
            }
            zzaidVar = this.zzdal;
        }
        return zzaidVar;
    }

    public final zzaid zzb(Context context, zzawv zzawvVar) {
        zzaid zzaidVar;
        synchronized (this.zzdai) {
            if (this.zzdak == null) {
                this.zzdak = new zzaid(zzk(context), zzawvVar, (String) zzuo.zzoj().zzd(zzyt.zzcfz));
            }
            zzaidVar = this.zzdak;
        }
        return zzaidVar;
    }

    private static Context zzk(Context context) {
        Context applicationContext = context.getApplicationContext();
        return applicationContext == null ? context : applicationContext;
    }
}
