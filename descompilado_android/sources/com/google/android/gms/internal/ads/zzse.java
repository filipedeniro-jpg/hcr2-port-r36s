package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.ObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzse {

    @VisibleForTesting
    zzfy zzbto;

    @VisibleForTesting
    boolean zzbtp;

    public final zzsi zzf(byte[] bArr) {
        return new zzsi(this, bArr);
    }

    public zzse(Context context, String str, String str2) {
        zzyt.initialize(context);
        try {
            this.zzbto = (zzfy) zzawr.zza(context, "com.google.android.gms.ads.clearcut.DynamiteClearcutLogger", zzsd.zzbtn);
            ObjectWrapper.wrap(context);
            this.zzbto.zza(ObjectWrapper.wrap(context), str, null);
            this.zzbtp = true;
        } catch (RemoteException | zzawt | NullPointerException unused) {
            zzawo.zzdv("Cannot dynamite load clearcut");
        }
    }

    public zzse(Context context) {
        zzyt.initialize(context);
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcqd)).booleanValue()) {
            try {
                this.zzbto = (zzfy) zzawr.zza(context, "com.google.android.gms.ads.clearcut.DynamiteClearcutLogger", zzsg.zzbtn);
                ObjectWrapper.wrap(context);
                this.zzbto.zzb(ObjectWrapper.wrap(context), "GMA_SDK");
                this.zzbtp = true;
            } catch (RemoteException | zzawt | NullPointerException unused) {
                zzawo.zzdv("Cannot dynamite load clearcut");
            }
        }
    }

    public zzse() {
    }
}
