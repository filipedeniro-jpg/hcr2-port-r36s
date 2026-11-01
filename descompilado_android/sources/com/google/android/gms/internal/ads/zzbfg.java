package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public class zzbfg {
    private zza zzezs;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static abstract class zza {
        public abstract zzbap zzabr();

        public abstract zzayd zzabs();

        public abstract zzse zzabt();

        public abstract zzarq zzabu();
    }

    public zzbfg(zza zzaVar) {
        this.zzezs = zzaVar;
    }

    /* JADX WARN: Type inference failed for: r4v0, types: [com.google.android.gms.internal.ads.zzarl, com.google.android.gms.internal.ads.zzarr] */
    public final com.google.android.gms.ads.internal.zzb zzadp() {
        zza zzaVar = this.zzezs;
        return new com.google.android.gms.ads.internal.zzb(zzaVar.zzabr(), zzaVar.zzabs(), new zzarl(zzaVar.zzabu()), zzaVar.zzabt());
    }

    public final zzarq zzabu() {
        return this.zzezs.zzabu();
    }
}
