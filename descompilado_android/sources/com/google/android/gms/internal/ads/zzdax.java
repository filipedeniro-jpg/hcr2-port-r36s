package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdax extends zzdak<Object> {
    private final transient int offset;
    private final transient int size;
    private final transient Object[] zzgpa;

    zzdax(Object[] objArr, int i, int i2) {
        this.zzgpa = objArr;
        this.offset = i;
        this.size = i2;
    }

    @Override // com.google.android.gms.internal.ads.zzdaf
    final boolean zzaoj() {
        return true;
    }

    @Override // java.util.List
    public final Object get(int i) {
        zzczx.zzr(i, this.size);
        return this.zzgpa[(i * 2) + this.offset];
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.size;
    }
}
