package com.google.android.gms.internal.ads;

import java.util.Iterator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzdba<E> extends zzdan<E> {
    static final zzdba<Object> zzgpg = new zzdba<>(new Object[0], 0, null, 0, 0);
    private final transient int mask;
    private final transient int size;
    private final transient int zzafu;
    private final transient Object[] zzgph;
    private final transient Object[] zzgpi;

    zzdba(Object[] objArr, int i, Object[] objArr2, int i2, int i3) {
        this.zzgph = objArr;
        this.zzgpi = objArr2;
        this.mask = i2;
        this.zzafu = i;
        this.size = i3;
    }

    @Override // com.google.android.gms.internal.ads.zzdaf
    final int zzaog() {
        return 0;
    }

    @Override // com.google.android.gms.internal.ads.zzdaf
    final boolean zzaoj() {
        return false;
    }

    @Override // com.google.android.gms.internal.ads.zzdan
    final boolean zzaol() {
        return true;
    }

    @Override // com.google.android.gms.internal.ads.zzdaf, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(@NullableDecl Object obj) {
        Object[] objArr = this.zzgpi;
        if (obj == null || objArr == null) {
            return false;
        }
        int iZzdq = zzdag.zzdq(obj == null ? 0 : obj.hashCode());
        while (true) {
            int i = iZzdq & this.mask;
            Object obj2 = objArr[i];
            if (obj2 == null) {
                return false;
            }
            if (obj2.equals(obj)) {
                return true;
            }
            iZzdq = i + 1;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.size;
    }

    @Override // com.google.android.gms.internal.ads.zzdaf
    /* JADX INFO: renamed from: zzaoe */
    public final zzdbb<E> iterator() {
        return (zzdbb) zzaoi().iterator();
    }

    @Override // com.google.android.gms.internal.ads.zzdaf
    final Object[] zzaof() {
        return this.zzgph;
    }

    @Override // com.google.android.gms.internal.ads.zzdaf
    final int zzaoh() {
        return this.size;
    }

    @Override // com.google.android.gms.internal.ads.zzdaf
    final int zza(Object[] objArr, int i) {
        System.arraycopy(this.zzgph, 0, objArr, i, this.size);
        return i + this.size;
    }

    @Override // com.google.android.gms.internal.ads.zzdan
    final zzdak<E> zzaom() {
        return zzdak.zzb(this.zzgph, this.size);
    }

    @Override // com.google.android.gms.internal.ads.zzdan, java.util.Collection, java.util.Set
    public final int hashCode() {
        return this.zzafu;
    }

    @Override // com.google.android.gms.internal.ads.zzdan, com.google.android.gms.internal.ads.zzdaf, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return iterator();
    }
}
