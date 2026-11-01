package com.google.android.gms.internal.ads;

import java.io.Serializable;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
public abstract class zzczy<T> implements Serializable {
    @NullableDecl
    public abstract T zzaoa();

    public static <T> zzczy<T> zzz(@NullableDecl T t) {
        return t == null ? zzczq.zzgoc : new zzdaa(t);
    }

    zzczy() {
    }
}
