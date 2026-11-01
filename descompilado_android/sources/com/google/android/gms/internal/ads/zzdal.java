package com.google.android.gms.internal.ads;

import java.util.List;

/* JADX INFO: Add missing generic type declarations: [E] */
/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdal<E> extends zzdak<E> {
    private final transient int length;
    private final transient int offset;
    private final /* synthetic */ zzdak zzgoo;

    zzdal(zzdak zzdakVar, int i, int i2) {
        this.zzgoo = zzdakVar;
        this.offset = i;
        this.length = i2;
    }

    @Override // com.google.android.gms.internal.ads.zzdaf
    final boolean zzaoj() {
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.length;
    }

    @Override // com.google.android.gms.internal.ads.zzdaf
    final Object[] zzaof() {
        return this.zzgoo.zzaof();
    }

    @Override // com.google.android.gms.internal.ads.zzdaf
    final int zzaog() {
        return this.zzgoo.zzaog() + this.offset;
    }

    @Override // com.google.android.gms.internal.ads.zzdaf
    final int zzaoh() {
        return this.zzgoo.zzaog() + this.offset + this.length;
    }

    @Override // java.util.List
    public final E get(int i) {
        zzczx.zzr(i, this.length);
        return this.zzgoo.get(i + this.offset);
    }

    @Override // com.google.android.gms.internal.ads.zzdak
    /* JADX INFO: renamed from: zzt */
    public final zzdak<E> subList(int i, int i2) {
        zzczx.zzf(i, i2, this.length);
        zzdak zzdakVar = this.zzgoo;
        int i3 = this.offset;
        return (zzdak) zzdakVar.subList(i + i3, i2 + i3);
    }

    @Override // com.google.android.gms.internal.ads.zzdak, java.util.List
    public final /* synthetic */ List subList(int i, int i2) {
        return subList(i, i2);
    }
}
