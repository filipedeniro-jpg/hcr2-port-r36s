package com.google.android.gms.internal.firebase_remote_config;

/* JADX INFO: loaded from: classes2.dex */
final class zzid implements zzji {
    private static final zzin zzve = new zzig();
    private final zzin zzvd;

    public zzid() {
        this(new zzif(zzhj.zzgx(), zzhu()));
    }

    private zzid(zzin zzinVar) {
        this.zzvd = (zzin) zzhm.zza(zzinVar, "messageInfoFactory");
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzji
    public final <T> zzjf<T> zzj(Class<T> cls) {
        zzjh.zzl(cls);
        zzio zzioVarZzi = this.zzvd.zzi(cls);
        if (zzioVarZzi.zzic()) {
            if (zzhi.class.isAssignableFrom(cls)) {
                return zziw.zza(zzjh.zzio(), zzhb.zzgl(), zzioVarZzi.zzid());
            }
            return zziw.zza(zzjh.zzim(), zzhb.zzgm(), zzioVarZzi.zzid());
        }
        if (zzhi.class.isAssignableFrom(cls)) {
            if (zza(zzioVarZzi)) {
                return zziu.zza(cls, zzioVarZzi, zzja.zzig(), zzia.zzht(), zzjh.zzio(), zzhb.zzgl(), zzil.zzhz());
            }
            return zziu.zza(cls, zzioVarZzi, zzja.zzig(), zzia.zzht(), zzjh.zzio(), (zzgz<?>) null, zzil.zzhz());
        }
        if (zza(zzioVarZzi)) {
            return zziu.zza(cls, zzioVarZzi, zzja.zzif(), zzia.zzhs(), zzjh.zzim(), zzhb.zzgm(), zzil.zzhy());
        }
        return zziu.zza(cls, zzioVarZzi, zzja.zzif(), zzia.zzhs(), zzjh.zzin(), (zzgz<?>) null, zzil.zzhy());
    }

    private static boolean zza(zzio zzioVar) {
        return zzioVar.zzib() == zzhi.zze.zztu;
    }

    private static zzin zzhu() {
        try {
            return (zzin) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            return zzve;
        }
    }
}
