package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdvs<T> implements zzdvv<T> {
    private static final Object zzhxa = new Object();
    private volatile Object zzdup = zzhxa;
    private volatile zzdvv<T> zzhxb;

    private zzdvs(zzdvv<T> zzdvvVar) {
        this.zzhxb = zzdvvVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final T get() {
        T t = (T) this.zzdup;
        if (t != zzhxa) {
            return t;
        }
        zzdvv<T> zzdvvVar = this.zzhxb;
        if (zzdvvVar == null) {
            return (T) this.zzdup;
        }
        T t2 = zzdvvVar.get();
        this.zzdup = t2;
        this.zzhxb = null;
        return t2;
    }

    public static <P extends zzdvv<T>, T> zzdvv<T> zzan(P p) {
        return ((p instanceof zzdvs) || (p instanceof zzdvj)) ? p : new zzdvs((zzdvv) zzdvo.checkNotNull(p));
    }
}
