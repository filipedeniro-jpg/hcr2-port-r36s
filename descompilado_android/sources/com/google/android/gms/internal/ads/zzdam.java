package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdam<E> extends zzdab<E> {
    private final zzdak<E> zzgop;

    zzdam(zzdak<E> zzdakVar, int i) {
        super(zzdakVar.size(), i);
        this.zzgop = zzdakVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdab
    protected final E get(int i) {
        return this.zzgop.get(i);
    }
}
