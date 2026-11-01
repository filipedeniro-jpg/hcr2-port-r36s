package com.google.android.gms.internal.firebase_auth;

import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzeo implements com.google.firebase.auth.api.internal.zzfk<zzlo.zza> {
    private String zza;
    private String zzb;

    @Nullable
    private final String zzc;

    public zzeo(String str) {
        this(str, null);
    }

    private zzeo(String str, @Nullable String str2) {
        this.zza = zzel.REFRESH_TOKEN.toString();
        this.zzb = Preconditions.checkNotEmpty(str);
        this.zzc = null;
    }

    @Override // com.google.firebase.auth.api.internal.zzfk
    public final /* synthetic */ zzjg zza() {
        return (zzlo.zza) ((zzhx) zzlo.zza.zza().zza(this.zza).zzb(this.zzb).zzf());
    }
}
