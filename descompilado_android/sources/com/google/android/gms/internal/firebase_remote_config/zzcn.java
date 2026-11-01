package com.google.android.gms.internal.firebase_remote_config;

import java.util.Iterator;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: loaded from: classes2.dex */
final class zzcn<T> implements Iterable<T> {
    final /* synthetic */ Object zzgh;

    zzcn(Object obj) {
        this.zzgh = obj;
    }

    @Override // java.lang.Iterable
    public final Iterator<T> iterator() {
        return new zzcq(this);
    }
}
