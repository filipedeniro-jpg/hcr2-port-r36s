package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzatk {
    public static void zzam(Context context) {
        if (zzawi.zzbn(context) && !zzawi.zzwj()) {
            zzdcp<?> zzdcpVarZzup = new zzatn(context).zzup();
            zzatm.zzet("Updating ad debug logging enablement.");
            zzaxb.zza(zzdcpVarZzup, "AdDebugLogUpdater.updateEnablement");
        }
    }
}
