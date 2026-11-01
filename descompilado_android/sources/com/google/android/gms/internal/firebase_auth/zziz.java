package com.google.android.gms.internal.firebase_auth;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zziz implements zzjh {
    private zzjh[] zza;

    zziz(zzjh... zzjhVarArr) {
        this.zza = zzjhVarArr;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjh
    public final boolean zza(Class<?> cls) {
        for (zzjh zzjhVar : this.zza) {
            if (zzjhVar.zza(cls)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjh
    public final zzje zzb(Class<?> cls) {
        for (zzjh zzjhVar : this.zza) {
            if (zzjhVar.zza(cls)) {
                return zzjhVar.zzb(cls);
            }
        }
        String strValueOf = String.valueOf(cls.getName());
        throw new UnsupportedOperationException(strValueOf.length() != 0 ? "No factory is available for message type: ".concat(strValueOf) : new String("No factory is available for message type: "));
    }
}
