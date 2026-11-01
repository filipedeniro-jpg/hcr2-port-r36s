package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.Executor;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbhb implements zzdvi<Set<zzbqc<zzpl>>> {
    private final zzdvv<Executor> zzfbt;
    private final zzdvv<zzbgu> zzfca;
    private final zzdvv<JSONObject> zzfcb;

    private zzbhb(zzdvv<zzbgu> zzdvvVar, zzdvv<Executor> zzdvvVar2, zzdvv<JSONObject> zzdvvVar3) {
        this.zzfca = zzdvvVar;
        this.zzfbt = zzdvvVar2;
        this.zzfcb = zzdvvVar3;
    }

    public static zzbhb zzd(zzdvv<zzbgu> zzdvvVar, zzdvv<Executor> zzdvvVar2, zzdvv<JSONObject> zzdvvVar3) {
        return new zzbhb(zzdvvVar, zzdvvVar2, zzdvvVar3);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        Set setSingleton;
        zzbgu zzbguVar = this.zzfca.get();
        Executor executor = this.zzfbt.get();
        if (this.zzfcb.get() == null) {
            setSingleton = Collections.emptySet();
        } else {
            setSingleton = Collections.singleton(new zzbqc(zzbguVar, executor));
        }
        return (Set) zzdvo.zza(setSingleton, "Cannot return null from a non-@Nullable @Provides method");
    }
}
