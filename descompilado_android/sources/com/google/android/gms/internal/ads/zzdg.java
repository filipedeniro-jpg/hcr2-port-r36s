package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdg extends zzdd {
    public static zzdg zza(String str, Context context, boolean z) {
        zza(context, z);
        return new zzdg(context, str, z);
    }

    private zzdg(Context context, String str, boolean z) {
        super(context, str, z);
    }

    @Override // com.google.android.gms.internal.ads.zzdd
    protected final List<Callable<Void>> zza(zzdx zzdxVar, Context context, zzbo.zza.zzb zzbVar, zzbk.zza zzaVar) {
        if (zzdxVar.zzcd() == null || !this.zzwo) {
            return super.zza(zzdxVar, context, zzbVar, zzaVar);
        }
        int iZzbz = zzdxVar.zzbz();
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(super.zza(zzdxVar, context, zzbVar, zzaVar));
        arrayList.add(new zzet(zzdxVar, "XH9P571BKuLP93qYQxkRpy1PUNifzwu73ZoDGVFo2YZ/YFYubO2Lb3GWBfhhanl4", "NeE5pLFr5wX1ANovtLOsJqP768Ctmq/FMTICBM52x6A=", zzbVar, iZzbz, 24));
        return arrayList;
    }
}
