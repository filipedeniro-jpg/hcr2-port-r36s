package com.google.android.gms.internal.firebase_auth;

import java.util.Comparator;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzgo implements Comparator<zzgm> {
    zzgo() {
    }

    @Override // java.util.Comparator
    public final /* synthetic */ int compare(zzgm zzgmVar, zzgm zzgmVar2) {
        zzgm zzgmVar3 = zzgmVar;
        zzgm zzgmVar4 = zzgmVar2;
        zzgv zzgvVar = (zzgv) zzgmVar3.iterator();
        zzgv zzgvVar2 = (zzgv) zzgmVar4.iterator();
        while (zzgvVar.hasNext() && zzgvVar2.hasNext()) {
            int iCompare = Integer.compare(zzgm.zzb(zzgvVar.zza()), zzgm.zzb(zzgvVar2.zza()));
            if (iCompare != 0) {
                return iCompare;
            }
        }
        return Integer.compare(zzgmVar3.zza(), zzgmVar4.zza());
    }
}
