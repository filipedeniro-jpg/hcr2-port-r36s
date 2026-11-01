package com.google.android.gms.internal.firebase_remote_config;

import java.util.Collection;
import java.util.HashSet;

/* JADX INFO: loaded from: classes2.dex */
public final class zzbb {
    final zzaw zzda;
    Collection<String> zzdf = new HashSet();

    public zzbb(zzaw zzawVar) {
        this.zzda = (zzaw) zzdt.checkNotNull(zzawVar);
    }

    public final zzay zzbm() {
        return new zzay(this);
    }

    public final zzbb zza(Collection<String> collection) {
        this.zzdf = collection;
        return this;
    }
}
