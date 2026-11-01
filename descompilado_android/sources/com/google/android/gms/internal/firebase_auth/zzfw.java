package com.google.android.gms.internal.firebase_auth;

import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzfw implements com.google.firebase.auth.api.internal.zzfk<zzp.zzs> {
    private String zza;

    @Nullable
    private String zzb;

    public zzfw(String str, @Nullable String str2) {
        this.zza = Preconditions.checkNotEmpty(str);
        this.zzb = str2;
    }

    @Override // com.google.firebase.auth.api.internal.zzfk
    public final /* synthetic */ zzjg zza() {
        zzp.zzs.zza zzaVarZza = zzp.zzs.zza().zza(this.zza).zza(true);
        String str = this.zzb;
        if (str != null) {
            zzaVarZza.zzb(str);
        }
        return (zzp.zzs) ((zzhx) zzaVarZza.zzf());
    }
}
