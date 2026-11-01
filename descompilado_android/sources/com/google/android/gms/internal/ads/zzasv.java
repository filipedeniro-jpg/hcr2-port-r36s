package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.pm.PackageInfo;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzasv implements zzasx {
    @Override // com.google.android.gms.internal.ads.zzasx
    public final zzdcp<String> zza(String str, PackageInfo packageInfo) {
        return zzdcf.zzah(str);
    }

    @Override // com.google.android.gms.internal.ads.zzasx
    public final zzdcp<AdvertisingIdClient.Info> zzaj(Context context) {
        zzaxf zzaxfVar = new zzaxf();
        zzuo.zzof();
        if (zzawe.zzbj(context)) {
            zzawx.zzdvx.execute(new zzasu(this, context, zzaxfVar));
        }
        return zzaxfVar;
    }
}
