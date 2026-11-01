package com.google.android.gms.internal.ads;

import com.my.target.ai;
import java.util.Collections;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbst implements zzdvi<Set<String>> {
    private final zzdvv<zzbui> zzfdk;

    public zzbst(zzdvv<zzbui> zzdvvVar) {
        this.zzfdk = zzdvvVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        Set setEmptySet;
        if (this.zzfdk.get().zzaid() != null) {
            setEmptySet = Collections.singleton(ai.a.cW);
        } else {
            setEmptySet = Collections.emptySet();
        }
        return (Set) zzdvo.zza(setEmptySet, "Cannot return null from a non-@Nullable @Provides method");
    }
}
