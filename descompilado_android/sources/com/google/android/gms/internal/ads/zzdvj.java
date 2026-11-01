package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdvj<T> implements zzdvc<T>, zzdvv<T> {
    private static final Object zzhxa = new Object();
    private volatile Object zzdup = zzhxa;
    private volatile zzdvv<T> zzhxb;

    private zzdvj(zzdvv<T> zzdvvVar) {
        this.zzhxb = zzdvvVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdvc, com.google.android.gms.internal.ads.zzdvv
    public final T get() {
        T t = (T) this.zzdup;
        if (t == zzhxa) {
            synchronized (this) {
                t = (T) this.zzdup;
                if (t == zzhxa) {
                    t = this.zzhxb.get();
                    Object obj = this.zzdup;
                    if (((obj == zzhxa || (obj instanceof zzdvp)) ? false : true) && obj != t) {
                        String strValueOf = String.valueOf(obj);
                        String strValueOf2 = String.valueOf(t);
                        StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 118 + String.valueOf(strValueOf2).length());
                        sb.append("Scoped provider was invoked recursively returning different results: ");
                        sb.append(strValueOf);
                        sb.append(" & ");
                        sb.append(strValueOf2);
                        sb.append(". This is likely due to a circular dependency.");
                        throw new IllegalStateException(sb.toString());
                    }
                    this.zzdup = t;
                    this.zzhxb = null;
                }
            }
        }
        return t;
    }

    public static <P extends zzdvv<T>, T> zzdvv<T> zzan(P p) {
        zzdvo.checkNotNull(p);
        return p instanceof zzdvj ? p : new zzdvj(p);
    }

    public static <P extends zzdvv<T>, T> zzdvc<T> zzao(P p) {
        if (p instanceof zzdvc) {
            return (zzdvc) p;
        }
        return new zzdvj((zzdvv) zzdvo.checkNotNull(p));
    }
}
