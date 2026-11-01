package com.google.android.gms.internal.firebase_auth;

import androidx.annotation.Nullable;
import com.google.android.gms.common.util.Strings;
import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzfo implements com.google.firebase.auth.api.internal.zzea<zzfo, zzp.zzn> {
    private String zza;
    private String zzb;
    private Boolean zzc;
    private String zzd;
    private String zze;
    private zzfe zzf;
    private String zzg;
    private String zzh;
    private long zzi;

    @Nullable
    public final String zzb() {
        return this.zzg;
    }

    @Nullable
    public final String zzc() {
        return this.zzh;
    }

    public final long zzd() {
        return this.zzi;
    }

    @Nullable
    public final String zze() {
        return this.zza;
    }

    public final List<zzfc> zzf() {
        zzfe zzfeVar = this.zzf;
        if (zzfeVar != null) {
            return zzfeVar.zza();
        }
        return null;
    }

    @Override // com.google.firebase.auth.api.internal.zzea
    public final zzjq<zzp.zzn> zza() {
        return zzp.zzn.zzj();
    }

    @Override // com.google.firebase.auth.api.internal.zzea
    public final /* synthetic */ com.google.firebase.auth.api.internal.zzea zza(zzjg zzjgVar) {
        if (!(zzjgVar instanceof zzp.zzn)) {
            throw new IllegalArgumentException("The passed proto must be an instance of SetAccountInfoResponse.");
        }
        zzp.zzn zznVar = (zzp.zzn) zzjgVar;
        this.zza = Strings.emptyToNull(zznVar.zza());
        this.zzb = Strings.emptyToNull(zznVar.zzh());
        this.zzc = Boolean.valueOf(zznVar.zzi());
        this.zzd = Strings.emptyToNull(zznVar.zzb());
        this.zze = Strings.emptyToNull(zznVar.zze());
        this.zzf = zzfe.zza(zznVar.zzd());
        this.zzg = Strings.emptyToNull(zznVar.zzc());
        this.zzh = Strings.emptyToNull(zznVar.zzf());
        this.zzi = zznVar.zzg();
        return this;
    }
}
