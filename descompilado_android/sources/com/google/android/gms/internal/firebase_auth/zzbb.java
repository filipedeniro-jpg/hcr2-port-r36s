package com.google.android.gms.internal.firebase_auth;

import java.util.List;

/* JADX INFO: Add missing generic type declarations: [E] */
/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzbb<E> extends zzaz<E> {
    private final transient int zza;
    private final transient int zzb;
    private final /* synthetic */ zzaz zzc;

    zzbb(zzaz zzazVar, int i, int i2) {
        this.zzc = zzazVar;
        this.zza = i;
        this.zzb = i2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzba
    final Object[] zzd() {
        return this.zzc.zzd();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzba
    final int zze() {
        return this.zzc.zze() + this.zza;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzba
    final int zzf() {
        return this.zzc.zze() + this.zza + this.zzb;
    }

    @Override // java.util.List
    public final E get(int i) {
        zzao.zza(i, this.zzb);
        return this.zzc.get(i + this.zza);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzaz
    /* JADX INFO: renamed from: zza */
    public final zzaz<E> subList(int i, int i2) {
        zzao.zza(i, i2, this.zzb);
        zzaz zzazVar = this.zzc;
        int i3 = this.zza;
        return (zzaz) zzazVar.subList(i + i3, i2 + i3);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzaz, java.util.List
    public final /* synthetic */ List subList(int i, int i2) {
        return subList(i, i2);
    }
}
