package com.google.android.gms.internal.firebase_auth;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzjc implements zzjd {
    zzjc() {
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjd
    public final Map<?, ?> zza(Object obj) {
        return (zzja) obj;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjd
    public final zzjb<?, ?> zzb(Object obj) {
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjd
    public final Map<?, ?> zzc(Object obj) {
        return (zzja) obj;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjd
    public final boolean zzd(Object obj) {
        return !((zzja) obj).zzd();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjd
    public final Object zze(Object obj) {
        ((zzja) obj).zzc();
        return obj;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjd
    public final Object zzf(Object obj) {
        return zzja.zza().zzb();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjd
    public final Object zza(Object obj, Object obj2) {
        zzja zzjaVarZzb = (zzja) obj;
        zzja zzjaVar = (zzja) obj2;
        if (!zzjaVar.isEmpty()) {
            if (!zzjaVarZzb.zzd()) {
                zzjaVarZzb = zzjaVarZzb.zzb();
            }
            zzjaVarZzb.zza(zzjaVar);
        }
        return zzjaVarZzb;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjd
    public final int zza(int i, Object obj, Object obj2) {
        zzja zzjaVar = (zzja) obj;
        if (zzjaVar.isEmpty()) {
            return 0;
        }
        Iterator it = zzjaVar.entrySet().iterator();
        if (!it.hasNext()) {
            return 0;
        }
        Map.Entry entry = (Map.Entry) it.next();
        entry.getKey();
        entry.getValue();
        throw new NoSuchMethodError();
    }
}
