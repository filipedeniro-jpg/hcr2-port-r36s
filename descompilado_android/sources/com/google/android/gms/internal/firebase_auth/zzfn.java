package com.google.android.gms.internal.firebase_auth;

import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzfn implements com.google.firebase.auth.api.internal.zzfk<zzp.zzo> {
    private String zza;
    private String zzb;

    @Nullable
    private String zzc;

    @Nullable
    private String zzd;

    public zzfn(@Nullable String str) {
        this.zzd = str;
    }

    public zzfn(String str, String str2, @Nullable String str3, @Nullable String str4) {
        this.zza = Preconditions.checkNotEmpty(str);
        this.zzb = Preconditions.checkNotEmpty(str2);
        this.zzc = null;
        this.zzd = str4;
    }

    @Override // com.google.firebase.auth.api.internal.zzfk
    public final /* synthetic */ zzjg zza() {
        zzp.zzo.zza zzaVarZza = zzp.zzo.zza();
        String str = this.zza;
        if (str != null) {
            zzaVarZza.zza(str);
        }
        String str2 = this.zzb;
        if (str2 != null) {
            zzaVarZza.zzb(str2);
        }
        String str3 = this.zzd;
        if (str3 != null) {
            zzaVarZza.zzc(str3);
        }
        return (zzp.zzo) ((zzhx) zzaVarZza.zzf());
    }
}
