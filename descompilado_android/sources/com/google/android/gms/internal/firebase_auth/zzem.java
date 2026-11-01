package com.google.android.gms.internal.firebase_auth;

import androidx.annotation.NonNull;
import com.google.android.gms.common.util.Strings;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzem implements com.google.firebase.auth.api.internal.zzea<zzem, zzp.zze> {
    private String zza;
    private String zzb;
    private String zzc;
    private String zzd;
    private boolean zze;
    private long zzf;

    @NonNull
    public final String zzb() {
        return this.zzc;
    }

    @NonNull
    public final String zzc() {
        return this.zzd;
    }

    public final boolean zzd() {
        return this.zze;
    }

    public final long zze() {
        return this.zzf;
    }

    @Override // com.google.firebase.auth.api.internal.zzea
    public final zzjq<zzp.zze> zza() {
        return zzp.zze.zzg();
    }

    @Override // com.google.firebase.auth.api.internal.zzea
    public final /* synthetic */ com.google.firebase.auth.api.internal.zzea zza(zzjg zzjgVar) {
        if (!(zzjgVar instanceof zzp.zze)) {
            throw new IllegalArgumentException("The passed proto must be an instance of EmailLinkSigninResponse.");
        }
        zzp.zze zzeVar = (zzp.zze) zzjgVar;
        this.zza = Strings.emptyToNull(zzeVar.zze());
        this.zzb = Strings.emptyToNull(zzeVar.zzb());
        this.zzc = Strings.emptyToNull(zzeVar.zza());
        this.zzd = Strings.emptyToNull(zzeVar.zzc());
        this.zze = zzeVar.zzf();
        this.zzf = zzeVar.zzd();
        return this;
    }
}
