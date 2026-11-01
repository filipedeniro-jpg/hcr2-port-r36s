package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdau<E> extends zzdak<E> {
    static final zzdak<Object> zzgpb = new zzdau(new Object[0], 0);
    private final transient int size;
    private final transient Object[] zzgpc;

    zzdau(Object[] objArr, int i) {
        this.zzgpc = objArr;
        this.size = i;
    }

    @Override // com.google.android.gms.internal.ads.zzdaf
    final int zzaog() {
        return 0;
    }

    @Override // com.google.android.gms.internal.ads.zzdaf
    final boolean zzaoj() {
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.size;
    }

    @Override // com.google.android.gms.internal.ads.zzdaf
    final Object[] zzaof() {
        return this.zzgpc;
    }

    @Override // com.google.android.gms.internal.ads.zzdaf
    final int zzaoh() {
        return this.size;
    }

    @Override // com.google.android.gms.internal.ads.zzdak, com.google.android.gms.internal.ads.zzdaf
    final int zza(Object[] objArr, int i) {
        System.arraycopy(this.zzgpc, 0, objArr, i, this.size);
        return i + this.size;
    }

    @Override // java.util.List
    public final E get(int i) {
        zzczx.zzr(i, this.size);
        return (E) this.zzgpc[i];
    }
}
