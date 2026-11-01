package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbhp implements zzdvi<String> {
    private static final zzbhp zzfct = new zzbhp();

    public static zzbhp zzaeo() {
        return zzfct;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (String) zzdvo.zza("app_open_ad", "Cannot return null from a non-@Nullable @Provides method");
    }
}
