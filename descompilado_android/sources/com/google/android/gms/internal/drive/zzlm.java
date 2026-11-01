package com.google.android.gms.internal.drive;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
final class zzlm implements zzll {
    zzlm() {
    }

    @Override // com.google.android.gms.internal.drive.zzll
    public final Map<?, ?> zzh(Object obj) {
        return (zzlk) obj;
    }

    @Override // com.google.android.gms.internal.drive.zzll
    public final zzlj<?, ?> zzm(Object obj) {
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.internal.drive.zzll
    public final Map<?, ?> zzi(Object obj) {
        return (zzlk) obj;
    }

    @Override // com.google.android.gms.internal.drive.zzll
    public final boolean zzj(Object obj) {
        return !((zzlk) obj).isMutable();
    }

    @Override // com.google.android.gms.internal.drive.zzll
    public final Object zzk(Object obj) {
        ((zzlk) obj).zzbp();
        return obj;
    }

    @Override // com.google.android.gms.internal.drive.zzll
    public final Object zzl(Object obj) {
        return zzlk.zzdw().zzdx();
    }

    @Override // com.google.android.gms.internal.drive.zzll
    public final Object zzb(Object obj, Object obj2) {
        zzlk zzlkVarZzdx = (zzlk) obj;
        zzlk zzlkVar = (zzlk) obj2;
        if (!zzlkVar.isEmpty()) {
            if (!zzlkVarZzdx.isMutable()) {
                zzlkVarZzdx = zzlkVarZzdx.zzdx();
            }
            zzlkVarZzdx.zza(zzlkVar);
        }
        return zzlkVarZzdx;
    }

    @Override // com.google.android.gms.internal.drive.zzll
    public final int zzb(int i, Object obj, Object obj2) {
        zzlk zzlkVar = (zzlk) obj;
        if (zzlkVar.isEmpty()) {
            return 0;
        }
        Iterator it = zzlkVar.entrySet().iterator();
        if (!it.hasNext()) {
            return 0;
        }
        Map.Entry entry = (Map.Entry) it.next();
        entry.getKey();
        entry.getValue();
        throw new NoSuchMethodError();
    }
}
