package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbdu implements zzdvi<Context> {
    private final zzbdr zzeji;

    public zzbdu(zzbdr zzbdrVar) {
        this.zzeji = zzbdrVar;
    }

    public static Context zza(zzbdr zzbdrVar) {
        return (Context) zzdvo.zza(zzbdrVar.zzaax(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return zza(this.zzeji);
    }
}
