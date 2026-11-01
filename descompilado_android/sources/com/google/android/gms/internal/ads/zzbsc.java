package com.google.android.gms.internal.ads;

import com.my.target.aa;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbsc implements zzdvi<String> {
    private static final zzbsc zzfie = new zzbsc();

    public static zzbsc zzagt() {
        return zzfie;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (String) zzdvo.zza(aa.e.bo, "Cannot return null from a non-@Nullable @Provides method");
    }
}
