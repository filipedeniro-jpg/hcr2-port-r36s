package com.google.android.gms.internal.ads;

import com.my.target.ai;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbja implements zzdvi<String> {
    private static final zzbja zzfdz = new zzbja();

    public static zzbja zzafc() {
        return zzfdz;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (String) zzdvo.zza(ai.a.cW, "Cannot return null from a non-@Nullable @Provides method");
    }
}
