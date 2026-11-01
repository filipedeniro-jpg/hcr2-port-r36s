package com.google.android.gms.internal.firebase_auth;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzix implements zzjz {
    private static final zzjh zzb = new zziw();
    private final zzjh zza;

    public zzix() {
        this(new zziz(zzhy.zza(), zza()));
    }

    private zzix(zzjh zzjhVar) {
        this.zza = (zzjh) zzib.zza(zzjhVar, "messageInfoFactory");
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjz
    public final <T> zzjw<T> zza(Class<T> cls) {
        zzjy.zza((Class<?>) cls);
        zzje zzjeVarZzb = this.zza.zzb(cls);
        if (zzjeVarZzb.zzb()) {
            if (zzhx.class.isAssignableFrom(cls)) {
                return zzjn.zza(zzjy.zzc(), zzhn.zza(), zzjeVarZzb.zzc());
            }
            return zzjn.zza(zzjy.zza(), zzhn.zzb(), zzjeVarZzb.zzc());
        }
        if (zzhx.class.isAssignableFrom(cls)) {
            if (zza(zzjeVarZzb)) {
                return zzjk.zza(cls, zzjeVarZzb, zzjr.zzb(), zziq.zzb(), zzjy.zzc(), zzhn.zza(), zzjf.zzb());
            }
            return zzjk.zza(cls, zzjeVarZzb, zzjr.zzb(), zziq.zzb(), zzjy.zzc(), null, zzjf.zzb());
        }
        if (zza(zzjeVarZzb)) {
            return zzjk.zza(cls, zzjeVarZzb, zzjr.zza(), zziq.zza(), zzjy.zza(), zzhn.zzb(), zzjf.zza());
        }
        return zzjk.zza(cls, zzjeVarZzb, zzjr.zza(), zziq.zza(), zzjy.zzb(), null, zzjf.zza());
    }

    private static boolean zza(zzje zzjeVar) {
        return zzjeVar.zza() == zzhx.zze.zzh;
    }

    private static zzjh zza() {
        try {
            return (zzjh) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            return zzb;
        }
    }
}
