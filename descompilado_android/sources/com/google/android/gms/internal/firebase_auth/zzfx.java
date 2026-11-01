package com.google.android.gms.internal.firebase_auth;

import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzfx implements com.google.firebase.auth.api.internal.zzfk<zzp.zzu> {
    private String zza;
    private String zzb;

    @Nullable
    private final String zzc;
    private boolean zzd = true;

    public zzfx(String str, String str2, @Nullable String str3) {
        this.zza = Preconditions.checkNotEmpty(str);
        this.zzb = Preconditions.checkNotEmpty(str2);
        this.zzc = str3;
    }

    @Override // com.google.firebase.auth.api.internal.zzfk
    public final /* synthetic */ zzjg zza() {
        zzp.zzu.zza zzaVarZza = zzp.zzu.zza().zza(this.zza).zzb(this.zzb).zza(this.zzd);
        String str = this.zzc;
        if (str != null) {
            zzaVarZza.zzc(str);
        }
        return (zzp.zzu) ((zzhx) zzaVarZza.zzf());
    }
}
