package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbzn implements zzdvi<zzrz> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<Integer> zzfch;
    private final zzdvv<zzawv> zzfgk;
    private final zzdvv<String> zzfqm;
    private final zzdvv<String> zzfqn;

    private zzbzn(zzdvv<Context> zzdvvVar, zzdvv<String> zzdvvVar2, zzdvv<zzawv> zzdvvVar3, zzdvv<Integer> zzdvvVar4, zzdvv<String> zzdvvVar5) {
        this.zzejn = zzdvvVar;
        this.zzfqm = zzdvvVar2;
        this.zzfgk = zzdvvVar3;
        this.zzfch = zzdvvVar4;
        this.zzfqn = zzdvvVar5;
    }

    public static zzbzn zzf(zzdvv<Context> zzdvvVar, zzdvv<String> zzdvvVar2, zzdvv<zzawv> zzdvvVar3, zzdvv<Integer> zzdvvVar4, zzdvv<String> zzdvvVar5) {
        return new zzbzn(zzdvvVar, zzdvvVar2, zzdvvVar3, zzdvvVar4, zzdvvVar5);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        Context context = this.zzejn.get();
        final String str = this.zzfqm.get();
        zzawv zzawvVar = this.zzfgk.get();
        final int iIntValue = this.zzfch.get().intValue();
        final String str2 = this.zzfqn.get();
        zzrz zzrzVar = new zzrz(new zzse(context));
        final zztg zztgVar = new zztg();
        zztgVar.zzcbg = Integer.valueOf(zzawvVar.zzdvs);
        zztgVar.zzcbh = Integer.valueOf(zzawvVar.zzdvt);
        zztgVar.zzcbi = Integer.valueOf(zzawvVar.zzdvu ? 0 : 2);
        zzrzVar.zza(new zzry(iIntValue, str, zztgVar, str2) { // from class: com.google.android.gms.internal.ads.zzbzo
            private final String zzcyl;
            private final int zzdvo;
            private final zztg zzfqo;
            private final String zzfqp;

            {
                this.zzdvo = iIntValue;
                this.zzcyl = str;
                this.zzfqo = zztgVar;
                this.zzfqp = str2;
            }

            @Override // com.google.android.gms.internal.ads.zzry
            public final void zza(zztd zztdVar) {
                int i = this.zzdvo;
                String str3 = this.zzcyl;
                zztg zztgVar2 = this.zzfqo;
                String str4 = this.zzfqp;
                zztdVar.zzcal.zzbze = Integer.valueOf(i);
                zztdVar.zzcai.zzbzp = str3;
                zztdVar.zzcai.zzbzs = zztgVar2;
                zztdVar.zzcad = str4;
            }
        });
        return (zzrz) zzdvo.zza(zzrzVar, "Cannot return null from a non-@Nullable @Provides method");
    }
}
