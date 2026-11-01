package com.google.android.gms.internal.ads;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzdaa<T> extends zzczy<T> {
    private final T zzcyr;

    zzdaa(T t) {
        this.zzcyr = t;
    }

    @Override // com.google.android.gms.internal.ads.zzczy
    public final T zzaoa() {
        return this.zzcyr;
    }

    public final boolean equals(@NullableDecl Object obj) {
        if (obj instanceof zzdaa) {
            return this.zzcyr.equals(((zzdaa) obj).zzcyr);
        }
        return false;
    }

    public final int hashCode() {
        return this.zzcyr.hashCode() + 1502476572;
    }

    public final String toString() {
        String strValueOf = String.valueOf(this.zzcyr);
        StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 13);
        sb.append("Optional.of(");
        sb.append(strValueOf);
        sb.append(")");
        return sb.toString();
    }
}
