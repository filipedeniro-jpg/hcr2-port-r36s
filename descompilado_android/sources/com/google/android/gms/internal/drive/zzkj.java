package com.google.android.gms.internal.drive;

/* JADX INFO: loaded from: classes2.dex */
final class zzkj implements zzlp {
    private static final zzkj zzrp = new zzkj();

    private zzkj() {
    }

    public static zzkj zzcv() {
        return zzrp;
    }

    @Override // com.google.android.gms.internal.drive.zzlp
    public final boolean zzb(Class<?> cls) {
        return zzkk.class.isAssignableFrom(cls);
    }

    @Override // com.google.android.gms.internal.drive.zzlp
    public final zzlo zzc(Class<?> cls) {
        if (!zzkk.class.isAssignableFrom(cls)) {
            String strValueOf = String.valueOf(cls.getName());
            throw new IllegalArgumentException(strValueOf.length() != 0 ? "Unsupported message type: ".concat(strValueOf) : new String("Unsupported message type: "));
        }
        try {
            return (zzlo) zzkk.zzd(cls.asSubclass(zzkk.class)).zza(zzkk.zze.zzrz, (Object) null, (Object) null);
        } catch (Exception e) {
            String strValueOf2 = String.valueOf(cls.getName());
            throw new RuntimeException(strValueOf2.length() != 0 ? "Unable to get message info for ".concat(strValueOf2) : new String("Unable to get message info for "), e);
        }
    }
}
