package com.google.android.gms.internal.firebase_auth;

import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzeg implements com.google.firebase.auth.api.internal.zzfk<zzp.zza> {
    private String zza;
    private String zzb = "http://localhost";

    @Nullable
    private final String zzc;

    public zzeg(String str, @Nullable String str2) {
        this.zza = Preconditions.checkNotEmpty(str);
        this.zzc = str2;
    }

    @Override // com.google.firebase.auth.api.internal.zzfk
    public final /* synthetic */ zzjg zza() {
        zzp.zza.C0061zza c0061zzaZzb = zzp.zza.zza().zza(this.zza).zzb(this.zzb);
        String str = this.zzc;
        if (str != null) {
            c0061zzaZzb.zzc(str);
        }
        return (zzp.zza) ((zzhx) c0061zzaZzb.zzf());
    }
}
