package com.google.android.gms.internal.ads;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import java.util.List;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzccn implements zzdvi<zzdcp<zzaok>> {
    private final zzdvv<zzato> zzeoo;
    private final zzdvv<zzawv> zzfcg;
    private final zzdvv<zzcxn> zzfdw;
    private final zzdvv<String> zzfqm;
    private final zzdvv<zzdcp<Bundle>> zzftj;
    private final zzdvv<ApplicationInfo> zzftk;
    private final zzdvv<List<String>> zzftl;
    private final zzdvv<PackageInfo> zzftm;
    private final zzdvv<zzdcp<String>> zzftn;
    private final zzdvv<String> zzfto;

    private zzccn(zzdvv<zzcxn> zzdvvVar, zzdvv<zzdcp<Bundle>> zzdvvVar2, zzdvv<zzawv> zzdvvVar3, zzdvv<ApplicationInfo> zzdvvVar4, zzdvv<String> zzdvvVar5, zzdvv<List<String>> zzdvvVar6, zzdvv<PackageInfo> zzdvvVar7, zzdvv<zzdcp<String>> zzdvvVar8, zzdvv<zzato> zzdvvVar9, zzdvv<String> zzdvvVar10) {
        this.zzfdw = zzdvvVar;
        this.zzftj = zzdvvVar2;
        this.zzfcg = zzdvvVar3;
        this.zzftk = zzdvvVar4;
        this.zzfqm = zzdvvVar5;
        this.zzftl = zzdvvVar6;
        this.zzftm = zzdvvVar7;
        this.zzftn = zzdvvVar8;
        this.zzeoo = zzdvvVar9;
        this.zzfto = zzdvvVar10;
    }

    public static zzccn zza(zzdvv<zzcxn> zzdvvVar, zzdvv<zzdcp<Bundle>> zzdvvVar2, zzdvv<zzawv> zzdvvVar3, zzdvv<ApplicationInfo> zzdvvVar4, zzdvv<String> zzdvvVar5, zzdvv<List<String>> zzdvvVar6, zzdvv<PackageInfo> zzdvvVar7, zzdvv<zzdcp<String>> zzdvvVar8, zzdvv<zzato> zzdvvVar9, zzdvv<String> zzdvvVar10) {
        return new zzccn(zzdvvVar, zzdvvVar2, zzdvvVar3, zzdvvVar4, zzdvvVar5, zzdvvVar6, zzdvvVar7, zzdvvVar8, zzdvvVar9, zzdvvVar10);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        zzcxn zzcxnVar = this.zzfdw.get();
        final zzdcp<Bundle> zzdcpVar = this.zzftj.get();
        final zzawv zzawvVar = this.zzfcg.get();
        final ApplicationInfo applicationInfo = this.zzftk.get();
        final String str = this.zzfqm.get();
        final List<String> list = this.zzftl.get();
        final PackageInfo packageInfo = this.zzftm.get();
        final zzdcp<String> zzdcpVar2 = this.zzftn.get();
        final zzato zzatoVar = this.zzeoo.get();
        final String str2 = this.zzfto.get();
        return (zzdcp) zzdvo.zza(zzcxnVar.zza(zzcxk.REQUEST_PARCEL, zzdcpVar, zzdcpVar2).zzb(new Callable(zzdcpVar, zzawvVar, applicationInfo, str, list, packageInfo, zzdcpVar2, zzatoVar, str2) { // from class: com.google.android.gms.internal.ads.zzcco
            private final zzawv zzfdn;
            private final zzdcp zzfoj;
            private final String zzfqp;
            private final ApplicationInfo zzftp;
            private final List zzftq;
            private final PackageInfo zzftr;
            private final zzdcp zzfts;
            private final zzato zzftt;
            private final String zzftu;

            {
                this.zzfoj = zzdcpVar;
                this.zzfdn = zzawvVar;
                this.zzftp = applicationInfo;
                this.zzfqp = str;
                this.zzftq = list;
                this.zzftr = packageInfo;
                this.zzfts = zzdcpVar2;
                this.zzftt = zzatoVar;
                this.zzftu = str2;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.concurrent.Callable
            public final Object call() {
                zzdcp zzdcpVar3 = this.zzfoj;
                zzawv zzawvVar2 = this.zzfdn;
                ApplicationInfo applicationInfo2 = this.zzftp;
                String str3 = this.zzfqp;
                List list2 = this.zzftq;
                PackageInfo packageInfo2 = this.zzftr;
                zzdcp zzdcpVar4 = this.zzfts;
                zzato zzatoVar2 = this.zzftt;
                return new zzaok((Bundle) zzdcpVar3.get(), zzawvVar2, applicationInfo2, str3, list2, packageInfo2, (String) zzdcpVar4.get(), zzatoVar2.zzuw(), this.zzftu);
            }
        }).zzano(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
