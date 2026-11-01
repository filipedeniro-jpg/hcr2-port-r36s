package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.AdSize;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcvo {
    public static zzcva zza(List<zzcva> list, zzcva zzcvaVar) {
        return list.get(0);
    }

    public static zztw zza(Context context, List<zzcva> list) {
        ArrayList arrayList = new ArrayList();
        for (zzcva zzcvaVar : list) {
            if (zzcvaVar.zzgip) {
                arrayList.add(AdSize.FLUID);
            } else {
                arrayList.add(new AdSize(zzcvaVar.width, zzcvaVar.height));
            }
        }
        return new zztw(context, (AdSize[]) arrayList.toArray(new AdSize[arrayList.size()]));
    }

    public static zzcva zze(zztw zztwVar) {
        if (zztwVar.zzccg) {
            return new zzcva(-3, 0, true);
        }
        return new zzcva(zztwVar.width, zztwVar.height, false);
    }
}
