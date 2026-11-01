package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzcjq implements zzbqp {
    private final /* synthetic */ zzcvj zzfzj;
    private final /* synthetic */ zzcvb zzfzk;
    private final /* synthetic */ zzcfl zzfzl;
    final /* synthetic */ zzcjo zzfzm;

    zzcjq(zzcjo zzcjoVar, zzcvj zzcvjVar, zzcvb zzcvbVar, zzcfl zzcflVar) {
        this.zzfzm = zzcjoVar;
        this.zzfzj = zzcvjVar;
        this.zzfzk = zzcvbVar;
        this.zzfzl = zzcflVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbqp
    public final void onInitializationSucceeded() {
        Executor executor = this.zzfzm.zzfbm;
        final zzcvj zzcvjVar = this.zzfzj;
        final zzcvb zzcvbVar = this.zzfzk;
        final zzcfl zzcflVar = this.zzfzl;
        executor.execute(new Runnable(this, zzcvjVar, zzcvbVar, zzcflVar) { // from class: com.google.android.gms.internal.ads.zzcjp
            private final zzcvb zzfdo;
            private final zzcvj zzfno;
            private final zzcjq zzfzh;
            private final zzcfl zzfzi;

            {
                this.zzfzh = this;
                this.zzfno = zzcvjVar;
                this.zzfdo = zzcvbVar;
                this.zzfzi = zzcflVar;
            }

            @Override // java.lang.Runnable
            public final void run() {
                zzcjq zzcjqVar = this.zzfzh;
                zzcvj zzcvjVar2 = this.zzfno;
                zzcvb zzcvbVar2 = this.zzfdo;
                zzcfl zzcflVar2 = this.zzfzi;
                zzcjo zzcjoVar = zzcjqVar.zzfzm;
                zzcjo.zzc(zzcvjVar2, zzcvbVar2, zzcflVar2);
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzbqp
    public final void zzdf(int i) {
        String strValueOf = String.valueOf(this.zzfzl.zzfev);
        zzatm.zzeu(strValueOf.length() != 0 ? "Fail to initialize adapter ".concat(strValueOf) : new String("Fail to initialize adapter "));
    }
}
