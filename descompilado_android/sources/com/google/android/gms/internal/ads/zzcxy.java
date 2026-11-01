package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.common.util.Clock;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcxy implements zzdvi<zzcxw> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<zzaws> zzekc;
    private final zzdvv<zzcvi> zzeyn;
    private final zzdvv<Executor> zzfbt;
    private final zzdvv<Clock> zzfbv;
    private final zzdvv<zzawv> zzfcg;
    private final zzdvv<String> zzgea;
    private final zzdvv<String> zzgeb;
    private final zzdvv<zzcil> zzgml;

    private zzcxy(zzdvv<Executor> zzdvvVar, zzdvv<zzaws> zzdvvVar2, zzdvv<zzcil> zzdvvVar3, zzdvv<zzawv> zzdvvVar4, zzdvv<String> zzdvvVar5, zzdvv<String> zzdvvVar6, zzdvv<Context> zzdvvVar7, zzdvv<zzcvi> zzdvvVar8, zzdvv<Clock> zzdvvVar9) {
        this.zzfbt = zzdvvVar;
        this.zzekc = zzdvvVar2;
        this.zzgml = zzdvvVar3;
        this.zzfcg = zzdvvVar4;
        this.zzgea = zzdvvVar5;
        this.zzgeb = zzdvvVar6;
        this.zzejn = zzdvvVar7;
        this.zzeyn = zzdvvVar8;
        this.zzfbv = zzdvvVar9;
    }

    public static zzcxy zzc(zzdvv<Executor> zzdvvVar, zzdvv<zzaws> zzdvvVar2, zzdvv<zzcil> zzdvvVar3, zzdvv<zzawv> zzdvvVar4, zzdvv<String> zzdvvVar5, zzdvv<String> zzdvvVar6, zzdvv<Context> zzdvvVar7, zzdvv<zzcvi> zzdvvVar8, zzdvv<Clock> zzdvvVar9) {
        return new zzcxy(zzdvvVar, zzdvvVar2, zzdvvVar3, zzdvvVar4, zzdvvVar5, zzdvvVar6, zzdvvVar7, zzdvvVar8, zzdvvVar9);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcxw(this.zzfbt.get(), this.zzekc.get(), this.zzgml.get(), this.zzfcg.get(), this.zzgea.get(), this.zzgeb.get(), this.zzejn.get(), this.zzeyn.get(), this.zzfbv.get());
    }
}
