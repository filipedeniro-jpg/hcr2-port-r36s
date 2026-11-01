package com.google.android.gms.internal.ads;

import android.content.Context;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbdx implements zzdvi<WeakReference<Context>> {
    private final zzbdr zzeji;

    public zzbdx(zzbdr zzbdrVar) {
        this.zzeji = zzbdrVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (WeakReference) zzdvo.zza(this.zzeji.zzaay(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
