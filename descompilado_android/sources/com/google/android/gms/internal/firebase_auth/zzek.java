package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzek implements com.google.firebase.auth.api.internal.zzfk<zzp.zzc> {
    private String zza;

    public zzek(String str) {
        this.zza = Preconditions.checkNotEmpty(str);
    }

    @Override // com.google.firebase.auth.api.internal.zzfk
    public final /* synthetic */ zzjg zza() {
        return (zzp.zzc) ((zzhx) zzp.zzc.zza().zza(this.zza).zzf());
    }
}
