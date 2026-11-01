package com.google.android.gms.internal.firebase_auth;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzbf<E> extends zzaz<E> {
    static final zzaz<Object> zza = new zzbf(new Object[0], 0);
    private final transient Object[] zzb;
    private final transient int zzc;

    zzbf(Object[] objArr, int i) {
        this.zzb = objArr;
        this.zzc = i;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzba
    final int zze() {
        return 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzba
    final Object[] zzd() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzba
    final int zzf() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzaz, com.google.android.gms.internal.firebase_auth.zzba
    final int zza(Object[] objArr, int i) {
        System.arraycopy(this.zzb, 0, objArr, i, this.zzc);
        return i + this.zzc;
    }

    @Override // java.util.List
    public final E get(int i) {
        zzao.zza(i, this.zzc);
        return (E) this.zzb[i];
    }
}
