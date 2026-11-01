package com.google.firebase.auth.api.internal;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzh implements zzfe<com.google.android.gms.internal.firebase_auth.zzeq> {
    private final /* synthetic */ zzfb zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ String zzc;
    private final /* synthetic */ Boolean zzd;
    private final /* synthetic */ com.google.firebase.auth.zzg zze;
    private final /* synthetic */ zzdr zzf;
    private final /* synthetic */ com.google.android.gms.internal.firebase_auth.zzey zzg;

    zzh(zza zzaVar, zzfb zzfbVar, String str, String str2, Boolean bool, com.google.firebase.auth.zzg zzgVar, zzdr zzdrVar, com.google.android.gms.internal.firebase_auth.zzey zzeyVar) {
        this.zza = zzfbVar;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = bool;
        this.zze = zzgVar;
        this.zzf = zzdrVar;
        this.zzg = zzeyVar;
    }

    @Override // com.google.firebase.auth.api.internal.zzfb
    public final void zza(@Nullable String str) {
        this.zza.zza(str);
    }

    @Override // com.google.firebase.auth.api.internal.zzfe
    public final /* synthetic */ void zza(com.google.android.gms.internal.firebase_auth.zzeq zzeqVar) {
        List<com.google.android.gms.internal.firebase_auth.zzes> listZzb = zzeqVar.zzb();
        if (listZzb == null || listZzb.isEmpty()) {
            this.zza.zza("No users.");
            return;
        }
        com.google.android.gms.internal.firebase_auth.zzes zzesVar = listZzb.get(0);
        com.google.android.gms.internal.firebase_auth.zzfe zzfeVarZzk = zzesVar.zzk();
        List<com.google.android.gms.internal.firebase_auth.zzfc> listZza = zzfeVarZzk != null ? zzfeVarZzk.zza() : null;
        if (listZza != null && !listZza.isEmpty()) {
            if (!TextUtils.isEmpty(this.zzb)) {
                for (int i = 0; i < listZza.size(); i++) {
                    if (listZza.get(i).zzd().equals(this.zzb)) {
                        listZza.get(i).zza(this.zzc);
                        break;
                    }
                }
            } else {
                listZza.get(0).zza(this.zzc);
            }
        }
        Boolean bool = this.zzd;
        if (bool != null) {
            zzesVar.zza(bool.booleanValue());
        } else {
            zzesVar.zza(zzesVar.zzh() - zzesVar.zzg() < 1000);
        }
        zzesVar.zza(this.zze);
        this.zzf.zza(this.zzg, zzesVar);
    }
}
