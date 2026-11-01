package com.google.android.gms.internal.firebase_remote_config;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
final class zzjl extends zzjr {
    private final /* synthetic */ zzjk zzwy;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    private zzjl(zzjk zzjkVar) {
        super(zzjkVar, null);
        this.zzwy = zzjkVar;
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjr, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator<Map.Entry<K, V>> iterator() {
        return new zzjm(this.zzwy, null);
    }

    /* synthetic */ zzjl(zzjk zzjkVar, zzjj zzjjVar) {
        this(zzjkVar);
    }
}
