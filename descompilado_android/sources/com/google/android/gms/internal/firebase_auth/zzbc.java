package com.google.android.gms.internal.firebase_auth;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzbc<E> extends zzav<E> {
    private final zzaz<E> zza;

    zzbc(zzaz<E> zzazVar, int i) {
        super(zzazVar.size(), i);
        this.zza = zzazVar;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzav
    protected final E zza(int i) {
        return this.zza.get(i);
    }
}
