package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzen implements com.google.firebase.auth.api.internal.zzfk<zzp.zzf> {
    private String zza;

    public zzen(String str) {
        this.zza = Preconditions.checkNotEmpty(str);
    }

    @Override // com.google.firebase.auth.api.internal.zzfk
    public final /* synthetic */ zzjg zza() {
        return (zzp.zzf) ((zzhx) zzp.zzf.zza().zza(this.zza).zzf());
    }
}
