package com.google.android.gms.internal.firebase_remote_config;

/* JADX INFO: loaded from: classes2.dex */
final class zzhj implements zzin {
    private static final zzhj zztf = new zzhj();

    private zzhj() {
    }

    public static zzhj zzgx() {
        return zztf;
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzin
    public final boolean zzh(Class<?> cls) {
        return zzhi.class.isAssignableFrom(cls);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzin
    public final zzio zzi(Class<?> cls) {
        if (!zzhi.class.isAssignableFrom(cls)) {
            String strValueOf = String.valueOf(cls.getName());
            throw new IllegalArgumentException(strValueOf.length() != 0 ? "Unsupported message type: ".concat(strValueOf) : new String("Unsupported message type: "));
        }
        try {
            return (zzio) zzhi.zzg(cls.asSubclass(zzhi.class)).zzb(zzhi.zze.zzto, null, null);
        } catch (Exception e) {
            String strValueOf2 = String.valueOf(cls.getName());
            throw new RuntimeException(strValueOf2.length() != 0 ? "Unable to get message info for ".concat(strValueOf2) : new String("Unable to get message info for "), e);
        }
    }
}
