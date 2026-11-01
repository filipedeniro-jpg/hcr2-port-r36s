package com.google.android.gms.internal.firebase_auth;

import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzfz implements com.google.firebase.auth.api.internal.zzfk<zzp.zzw> {
    private boolean zza;
    private String zzb;
    private String zzc;
    private String zzd;
    private String zze;
    private String zzf;
    private boolean zzg;

    private zzfz() {
    }

    public static zzfz zza(String str, String str2, boolean z) {
        zzfz zzfzVar = new zzfz();
        zzfzVar.zza = false;
        zzfzVar.zzc = Preconditions.checkNotEmpty(str);
        zzfzVar.zzd = Preconditions.checkNotEmpty(str2);
        zzfzVar.zzg = z;
        return zzfzVar;
    }

    public static zzfz zzb(String str, String str2, boolean z) {
        zzfz zzfzVar = new zzfz();
        zzfzVar.zza = false;
        zzfzVar.zzb = Preconditions.checkNotEmpty(str);
        zzfzVar.zze = Preconditions.checkNotEmpty(str2);
        zzfzVar.zzg = z;
        return zzfzVar;
    }

    public final void zza(String str) {
        this.zzf = str;
    }

    @Override // com.google.firebase.auth.api.internal.zzfk
    public final /* synthetic */ zzjg zza() {
        zzp.zzw.zza zzaVarZza = zzp.zzw.zza();
        if (!TextUtils.isEmpty(this.zze)) {
            zzaVarZza.zzd(this.zze).zzb(this.zzb);
        } else {
            zzaVarZza.zza(this.zzc).zzc(this.zzd);
        }
        String str = this.zzf;
        if (str != null) {
            zzaVarZza.zze(str);
        }
        if (!this.zzg) {
            zzaVarZza.zza(zzaa.REAUTH);
        }
        return (zzp.zzw) ((zzhx) zzaVarZza.zzf());
    }
}
