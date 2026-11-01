package com.google.android.gms.internal.firebase_remote_config;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
final class zzim implements zzij {
    zzim() {
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzij
    public final Map<?, ?> zzp(Object obj) {
        return (zzik) obj;
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzij
    public final zzih<?, ?> zzu(Object obj) {
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzij
    public final Map<?, ?> zzq(Object obj) {
        return (zzik) obj;
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzij
    public final boolean zzr(Object obj) {
        return !((zzik) obj).isMutable();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzij
    public final Object zzs(Object obj) {
        ((zzik) obj).zzes();
        return obj;
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzij
    public final Object zzt(Object obj) {
        return zzik.zzhv().zzhw();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzij
    public final Object zzd(Object obj, Object obj2) {
        zzik zzikVarZzhw = (zzik) obj;
        zzik zzikVar = (zzik) obj2;
        if (!zzikVar.isEmpty()) {
            if (!zzikVarZzhw.isMutable()) {
                zzikVarZzhw = zzikVarZzhw.zzhw();
            }
            zzikVarZzhw.zza(zzikVar);
        }
        return zzikVarZzhw;
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzij
    public final int zzc(int i, Object obj, Object obj2) {
        zzik zzikVar = (zzik) obj;
        if (zzikVar.isEmpty()) {
            return 0;
        }
        Iterator it = zzikVar.entrySet().iterator();
        if (!it.hasNext()) {
            return 0;
        }
        Map.Entry entry = (Map.Entry) it.next();
        entry.getKey();
        entry.getValue();
        throw new NoSuchMethodError();
    }
}
