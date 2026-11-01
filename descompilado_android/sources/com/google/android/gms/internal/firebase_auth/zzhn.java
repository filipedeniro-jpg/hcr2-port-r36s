package com.google.android.gms.internal.firebase_auth;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzhn {
    private static final zzhm<?> zza = new zzho();
    private static final zzhm<?> zzb = zzc();

    private static zzhm<?> zzc() {
        try {
            return (zzhm) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    static zzhm<?> zza() {
        return zza;
    }

    static zzhm<?> zzb() {
        zzhm<?> zzhmVar = zzb;
        if (zzhmVar != null) {
            return zzhmVar;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }
}
