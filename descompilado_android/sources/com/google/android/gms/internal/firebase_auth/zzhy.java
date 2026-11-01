package com.google.android.gms.internal.firebase_auth;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzhy implements zzjh {
    private static final zzhy zza = new zzhy();

    private zzhy() {
    }

    public static zzhy zza() {
        return zza;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjh
    public final boolean zza(Class<?> cls) {
        return zzhx.class.isAssignableFrom(cls);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjh
    public final zzje zzb(Class<?> cls) {
        if (!zzhx.class.isAssignableFrom(cls)) {
            String strValueOf = String.valueOf(cls.getName());
            throw new IllegalArgumentException(strValueOf.length() != 0 ? "Unsupported message type: ".concat(strValueOf) : new String("Unsupported message type: "));
        }
        try {
            return (zzje) zzhx.zza(cls.asSubclass(zzhx.class)).zza(zzhx.zze.zzc, (Object) null, (Object) null);
        } catch (Exception e) {
            String strValueOf2 = String.valueOf(cls.getName());
            throw new RuntimeException(strValueOf2.length() != 0 ? "Unable to get message info for ".concat(strValueOf2) : new String("Unable to get message info for "), e);
        }
    }
}
