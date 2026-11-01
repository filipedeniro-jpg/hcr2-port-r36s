package com.google.android.gms.internal.firebase_auth;

import androidx.annotation.Nullable;
import com.google.android.gms.common.util.Strings;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzgb implements com.google.firebase.auth.api.internal.zzea<zzgb, zzp.zzx> {
    private String zza;
    private String zzb;
    private long zzc;
    private String zzd;
    private boolean zze;
    private String zzf;
    private String zzg;
    private long zzh;
    private String zzi;

    @Nullable
    public final String zzb() {
        return this.zza;
    }

    @Nullable
    public final String zzc() {
        return this.zzb;
    }

    public final long zzd() {
        return this.zzc;
    }

    public final boolean zze() {
        return this.zze;
    }

    @Nullable
    public final String zzf() {
        return this.zzf;
    }

    @Nullable
    public final String zzg() {
        return this.zzi;
    }

    @Override // com.google.firebase.auth.api.internal.zzea
    public final zzjq<zzp.zzx> zza() {
        return zzp.zzx.zzj();
    }

    @Override // com.google.firebase.auth.api.internal.zzea
    public final /* synthetic */ com.google.firebase.auth.api.internal.zzea zza(zzjg zzjgVar) {
        if (!(zzjgVar instanceof zzp.zzx)) {
            throw new IllegalArgumentException("The passed proto must be an instance of verifyPhoneNumberResponse.");
        }
        zzp.zzx zzxVar = (zzp.zzx) zzjgVar;
        this.zza = Strings.emptyToNull(zzxVar.zza());
        this.zzb = Strings.emptyToNull(zzxVar.zzb());
        this.zzc = zzxVar.zzc();
        this.zzd = Strings.emptyToNull(zzxVar.zzd());
        this.zze = zzxVar.zze();
        this.zzf = Strings.emptyToNull(zzxVar.zzf());
        this.zzg = Strings.emptyToNull(zzxVar.zzg());
        this.zzh = zzxVar.zzh();
        this.zzi = zzxVar.zzi();
        return this;
    }
}
