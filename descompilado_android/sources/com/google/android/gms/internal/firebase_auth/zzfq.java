package com.google.android.gms.internal.firebase_auth;

import androidx.annotation.NonNull;
import com.google.android.gms.common.util.Strings;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzfq implements com.google.firebase.auth.api.internal.zzea<zzfq, zzp.C0062zzp> {
    private String zza;
    private String zzb;
    private String zzc;
    private String zzd;
    private long zze;

    public final String zzb() {
        return this.zza;
    }

    @NonNull
    public final String zzc() {
        return this.zzd;
    }

    public final long zzd() {
        return this.zze;
    }

    @Override // com.google.firebase.auth.api.internal.zzea
    public final zzjq<zzp.C0062zzp> zza() {
        return zzp.C0062zzp.zzf();
    }

    @Override // com.google.firebase.auth.api.internal.zzea
    public final /* synthetic */ com.google.firebase.auth.api.internal.zzea zza(zzjg zzjgVar) {
        if (!(zzjgVar instanceof zzp.C0062zzp)) {
            throw new IllegalArgumentException("The passed proto must be an instance of SignUpNewUserResponse.");
        }
        zzp.C0062zzp c0062zzp = (zzp.C0062zzp) zzjgVar;
        this.zza = Strings.emptyToNull(c0062zzp.zza());
        this.zzb = Strings.emptyToNull(c0062zzp.zzb());
        this.zzc = Strings.emptyToNull(c0062zzp.zzc());
        this.zzd = Strings.emptyToNull(c0062zzp.zzd());
        this.zze = c0062zzp.zze();
        return this;
    }
}
