package com.google.firebase.auth.internal;

import android.text.TextUtils;
import com.google.android.gms.internal.firebase_auth.zzfa;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzar {
    public static com.google.firebase.auth.zzy zza(zzfa zzfaVar) {
        if (zzfaVar == null || TextUtils.isEmpty(zzfaVar.zza())) {
            return null;
        }
        return new com.google.firebase.auth.zzae(zzfaVar.zzb(), zzfaVar.zzc(), zzfaVar.zzd(), zzfaVar.zza());
    }

    public static List<com.google.firebase.auth.zzy> zza(List<zzfa> list) {
        if (list == null || list.isEmpty()) {
            return com.google.android.gms.internal.firebase_auth.zzaz.zza();
        }
        ArrayList arrayList = new ArrayList();
        Iterator<zzfa> it = list.iterator();
        while (it.hasNext()) {
            com.google.firebase.auth.zzy zzyVarZza = zza(it.next());
            if (zzyVarZza != null) {
                arrayList.add(zzyVarZza);
            }
        }
        return arrayList;
    }
}
