package com.google.android.gms.internal.firebase_remote_config;

/* JADX INFO: loaded from: classes2.dex */
final class zzif implements zzin {
    private zzin[] zzvh;

    zzif(zzin... zzinVarArr) {
        this.zzvh = zzinVarArr;
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzin
    public final boolean zzh(Class<?> cls) {
        for (zzin zzinVar : this.zzvh) {
            if (zzinVar.zzh(cls)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzin
    public final zzio zzi(Class<?> cls) {
        for (zzin zzinVar : this.zzvh) {
            if (zzinVar.zzh(cls)) {
                return zzinVar.zzi(cls);
            }
        }
        String strValueOf = String.valueOf(cls.getName());
        throw new UnsupportedOperationException(strValueOf.length() != 0 ? "No factory is available for message type: ".concat(strValueOf) : new String("No factory is available for message type: "));
    }
}
