package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import com.google.android.gms.internal.ads.zzbls;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzctk<RequestComponentT extends zzbls<AdT>, AdT> implements zzctn<RequestComponentT, AdT> {

    @Nullable
    @GuardedBy("this")
    private RequestComponentT zzghg;

    @Override // com.google.android.gms.internal.ads.zzctn
    public final zzdcp<AdT> zza(zzblr<RequestComponentT> zzblrVar, Executor executor) {
        this.zzghg = zzblrVar.zzabw();
        return this.zzghg.zzaci().zzafo();
    }

    @Override // com.google.android.gms.internal.ads.zzctn
    public final /* synthetic */ Object zzamp() {
        return this.zzghg;
    }
}
