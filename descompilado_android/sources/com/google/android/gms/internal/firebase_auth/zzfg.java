package com.google.android.gms.internal.firebase_auth;

import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzfg implements com.google.firebase.auth.api.internal.zzfk<zzp.zzi> {
    private final String zza;

    @Nullable
    private final String zzb;

    @Nullable
    private final String zzc;

    public zzfg(String str, @Nullable String str2, @Nullable String str3) {
        this.zza = Preconditions.checkNotEmpty(str);
        this.zzb = str2;
        this.zzc = str3;
    }

    @Override // com.google.firebase.auth.api.internal.zzfk
    public final /* synthetic */ zzjg zza() {
        zzp.zzi.zza zzaVarZza = zzp.zzi.zza().zza(this.zza);
        String str = this.zzb;
        if (str != null) {
            zzaVarZza.zzb(str);
        }
        String str2 = this.zzc;
        if (str2 != null) {
            zzaVarZza.zzc(str2);
        }
        return (zzp.zzi) ((zzhx) zzaVarZza.zzf());
    }
}
