package com.google.android.gms.internal.firebase_auth;

import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.firebase.auth.EmailAuthCredential;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzej implements com.google.firebase.auth.api.internal.zzfk<zzp.zzd> {
    private static final Logger zza = new Logger("EmailLinkSignInRequest", new String[0]);
    private final String zzb;
    private final String zzc;

    @Nullable
    private final String zzd;

    public zzej(EmailAuthCredential emailAuthCredential, @Nullable String str) {
        this.zzb = Preconditions.checkNotEmpty(emailAuthCredential.zza());
        this.zzc = Preconditions.checkNotEmpty(emailAuthCredential.zzc());
        this.zzd = str;
    }

    @Override // com.google.firebase.auth.api.internal.zzfk
    public final /* synthetic */ zzjg zza() {
        zzp.zzd.zza zzaVarZzb = zzp.zzd.zza().zzb(this.zzb);
        com.google.firebase.auth.zzf zzfVarZza = com.google.firebase.auth.zzf.zza(this.zzc);
        String strZzb = zzfVarZza != null ? zzfVarZza.zzb() : null;
        String strZzc = zzfVarZza != null ? zzfVarZza.zzc() : null;
        if (strZzb != null) {
            zzaVarZzb.zza(strZzb);
        }
        if (strZzc != null) {
            zzaVarZzb.zzd(strZzc);
        }
        String str = this.zzd;
        if (str != null) {
            zzaVarZzb.zzc(str);
        }
        return (zzp.zzd) ((zzhx) zzaVarZzb.zzf());
    }
}
