package com.google.android.gms.internal.firebase_auth;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzkc extends zzki {
    private final /* synthetic */ zzkb zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    private zzkc(zzkb zzkbVar) {
        super(zzkbVar, null);
        this.zza = zzkbVar;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzki, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator<Map.Entry<K, V>> iterator() {
        return new zzkd(this.zza, null);
    }

    /* synthetic */ zzkc(zzkb zzkbVar, zzka zzkaVar) {
        this(zzkbVar);
    }
}
