package com.google.android.gms.internal.drive;

/* JADX INFO: loaded from: classes2.dex */
final class zzlf implements zzmg {
    private static final zzlp zzts = new zzlg();
    private final zzlp zztr;

    public zzlf() {
        this(new zzlh(zzkj.zzcv(), zzdv()));
    }

    private zzlf(zzlp zzlpVar) {
        this.zztr = (zzlp) zzkm.zza(zzlpVar, "messageInfoFactory");
    }

    @Override // com.google.android.gms.internal.drive.zzmg
    public final <T> zzmf<T> zze(Class<T> cls) {
        zzmh.zzg((Class<?>) cls);
        zzlo zzloVarZzc = this.zztr.zzc(cls);
        if (zzloVarZzc.zzed()) {
            if (zzkk.class.isAssignableFrom(cls)) {
                return zzlw.zza(zzmh.zzeo(), zzka.zzcl(), zzloVarZzc.zzee());
            }
            return zzlw.zza(zzmh.zzem(), zzka.zzcm(), zzloVarZzc.zzee());
        }
        if (zzkk.class.isAssignableFrom(cls)) {
            if (zza(zzloVarZzc)) {
                return zzlu.zza(cls, zzloVarZzc, zzma.zzeh(), zzla.zzdu(), zzmh.zzeo(), zzka.zzcl(), zzln.zzea());
            }
            return zzlu.zza(cls, zzloVarZzc, zzma.zzeh(), zzla.zzdu(), zzmh.zzeo(), (zzjy<?>) null, zzln.zzea());
        }
        if (zza(zzloVarZzc)) {
            return zzlu.zza(cls, zzloVarZzc, zzma.zzeg(), zzla.zzdt(), zzmh.zzem(), zzka.zzcm(), zzln.zzdz());
        }
        return zzlu.zza(cls, zzloVarZzc, zzma.zzeg(), zzla.zzdt(), zzmh.zzen(), (zzjy<?>) null, zzln.zzdz());
    }

    private static boolean zza(zzlo zzloVar) {
        return zzloVar.zzec() == zzkk.zze.zzsf;
    }

    private static zzlp zzdv() {
        try {
            return (zzlp) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            return zzts;
        }
    }
}
