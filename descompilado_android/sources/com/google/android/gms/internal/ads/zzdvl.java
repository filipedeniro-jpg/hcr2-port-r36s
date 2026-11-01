package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdvl<T> implements zzdvc<T>, zzdvi<T> {
    private static final zzdvl<Object> zzhxd = new zzdvl<>(null);
    private final T zzdup;

    public static <T> zzdvi<T> zzbb(T t) {
        return new zzdvl(zzdvo.zza(t, "instance cannot be null"));
    }

    public static <T> zzdvi<T> zzbc(T t) {
        if (t == null) {
            return zzhxd;
        }
        return new zzdvl(t);
    }

    private zzdvl(T t) {
        this.zzdup = t;
    }

    @Override // com.google.android.gms.internal.ads.zzdvc, com.google.android.gms.internal.ads.zzdvv
    public final T get() {
        return this.zzdup;
    }
}
