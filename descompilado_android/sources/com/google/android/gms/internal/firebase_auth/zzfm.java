package com.google.android.gms.internal.firebase_auth;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzfm implements com.google.firebase.auth.api.internal.zzea<zzfm, zzp.zzl> {
    private String zza;

    public final String zzb() {
        return this.zza;
    }

    @Override // com.google.firebase.auth.api.internal.zzea
    public final zzjq<zzp.zzl> zza() {
        return zzp.zzl.zzb();
    }

    @Override // com.google.firebase.auth.api.internal.zzea
    public final /* synthetic */ com.google.firebase.auth.api.internal.zzea zza(zzjg zzjgVar) {
        if (!(zzjgVar instanceof zzp.zzl)) {
            throw new IllegalArgumentException("The passed proto must be an instance of SendVerificationCodeResponse.");
        }
        this.zza = ((zzp.zzl) zzjgVar).zza();
        return this;
    }
}
