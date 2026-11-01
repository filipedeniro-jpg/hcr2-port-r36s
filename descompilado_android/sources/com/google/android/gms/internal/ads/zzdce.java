package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzdce<V> extends zzdcb<V> implements zzdcp<V> {
    protected zzdce() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzdcb
    /* JADX INFO: renamed from: zzape, reason: merged with bridge method [inline-methods] */
    public abstract zzdcp<? extends V> zzaod();

    @Override // com.google.android.gms.internal.ads.zzdcp
    public void addListener(Runnable runnable, Executor executor) {
        zzaod().addListener(runnable, executor);
    }
}
