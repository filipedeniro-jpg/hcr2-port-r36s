package com.google.android.gms.internal.drive;

/* JADX INFO: loaded from: classes2.dex */
final class zzlh implements zzlp {
    private zzlp[] zztt;

    zzlh(zzlp... zzlpVarArr) {
        this.zztt = zzlpVarArr;
    }

    @Override // com.google.android.gms.internal.drive.zzlp
    public final boolean zzb(Class<?> cls) {
        for (zzlp zzlpVar : this.zztt) {
            if (zzlpVar.zzb(cls)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.gms.internal.drive.zzlp
    public final zzlo zzc(Class<?> cls) {
        for (zzlp zzlpVar : this.zztt) {
            if (zzlpVar.zzb(cls)) {
                return zzlpVar.zzc(cls);
            }
        }
        String strValueOf = String.valueOf(cls.getName());
        throw new UnsupportedOperationException(strValueOf.length() != 0 ? "No factory is available for message type: ".concat(strValueOf) : new String("No factory is available for message type: "));
    }
}
