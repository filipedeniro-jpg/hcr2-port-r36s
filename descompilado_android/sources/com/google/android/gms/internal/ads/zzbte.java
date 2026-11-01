package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.common.util.Clock;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbte implements zzdvi<zzbta> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<JSONObject> zzelk;
    private final zzdvv<zzbgu> zzelr;
    private final zzdvv<zzbmf> zzemc;
    private final zzdvv<zzbmo> zzemj;
    private final zzdvv<zzbqb> zzend;
    private final zzdvv<zzcxw> zzerp;
    private final zzdvv<zzawv> zzfaa;
    private final zzdvv<Clock> zzfbv;
    private final zzdvv<zzbui> zzfdk;
    private final zzdvv<zzcvk> zzfds;
    private final zzdvv<zzcvb> zzfem;
    private final zzdvv<zzbxn> zzfjt;
    private final zzdvv<zzbtx> zzfju;
    private final zzdvv<zzdf> zzfjv;
    private final zzdvv<zzbuw> zzfjw;

    public zzbte(zzdvv<Context> zzdvvVar, zzdvv<zzbui> zzdvvVar2, zzdvv<JSONObject> zzdvvVar3, zzdvv<zzbxn> zzdvvVar4, zzdvv<zzbtx> zzdvvVar5, zzdvv<zzdf> zzdvvVar6, zzdvv<zzbmo> zzdvvVar7, zzdvv<zzbmf> zzdvvVar8, zzdvv<zzcvb> zzdvvVar9, zzdvv<zzawv> zzdvvVar10, zzdvv<zzcvk> zzdvvVar11, zzdvv<zzbgu> zzdvvVar12, zzdvv<zzbuw> zzdvvVar13, zzdvv<Clock> zzdvvVar14, zzdvv<zzbqb> zzdvvVar15, zzdvv<zzcxw> zzdvvVar16) {
        this.zzejn = zzdvvVar;
        this.zzfdk = zzdvvVar2;
        this.zzelk = zzdvvVar3;
        this.zzfjt = zzdvvVar4;
        this.zzfju = zzdvvVar5;
        this.zzfjv = zzdvvVar6;
        this.zzemj = zzdvvVar7;
        this.zzemc = zzdvvVar8;
        this.zzfem = zzdvvVar9;
        this.zzfaa = zzdvvVar10;
        this.zzfds = zzdvvVar11;
        this.zzelr = zzdvvVar12;
        this.zzfjw = zzdvvVar13;
        this.zzfbv = zzdvvVar14;
        this.zzend = zzdvvVar15;
        this.zzerp = zzdvvVar16;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbta(this.zzejn.get(), this.zzfdk.get(), this.zzelk.get(), this.zzfjt.get(), this.zzfju.get(), this.zzfjv.get(), this.zzemj.get(), this.zzemc.get(), this.zzfem.get(), this.zzfaa.get(), this.zzfds.get(), this.zzelr.get(), this.zzfjw.get(), this.zzfbv.get(), this.zzend.get(), this.zzerp.get());
    }
}
