package com.google.android.gms.internal.ads;

import android.os.Build;
import java.util.HashMap;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzcti implements Callable {
    static final Callable zzgeu = new zzcti();

    private zzcti() {
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        HashMap map = new HashMap();
        String str = (String) zzuo.zzoj().zzd(zzyt.zzchq);
        if (str != null && !str.isEmpty()) {
            if (Build.VERSION.SDK_INT >= ((Integer) zzuo.zzoj().zzd(zzyt.zzchr)).intValue()) {
                for (String str2 : str.split(",", -1)) {
                    map.put(str2, zzawc.zzem(str2));
                }
            }
        }
        return new zzctg(map);
    }
}
