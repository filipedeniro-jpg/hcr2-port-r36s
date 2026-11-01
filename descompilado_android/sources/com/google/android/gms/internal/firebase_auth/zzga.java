package com.google.android.gms.internal.firebase_auth;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.google.android.gms.common.util.Strings;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzga implements com.google.firebase.auth.api.internal.zzea<zzga, zzp.zzv> {
    private String zza;
    private String zzb;
    private String zzc;
    private String zzd;
    private String zze;
    private String zzf;
    private long zzg;
    private List<zzfa> zzh;
    private String zzi;

    @NonNull
    public final String zzb() {
        return this.zzd;
    }

    @NonNull
    public final String zzc() {
        return this.zzf;
    }

    public final long zzd() {
        return this.zzg;
    }

    public final List<zzfa> zze() {
        return this.zzh;
    }

    public final String zzf() {
        return this.zzi;
    }

    public final boolean zzg() {
        return !TextUtils.isEmpty(this.zzi);
    }

    @Override // com.google.firebase.auth.api.internal.zzea
    public final zzjq<zzp.zzv> zza() {
        return zzp.zzv.zzj();
    }

    @Override // com.google.firebase.auth.api.internal.zzea
    public final /* synthetic */ com.google.firebase.auth.api.internal.zzea zza(zzjg zzjgVar) {
        if (!(zzjgVar instanceof zzp.zzv)) {
            throw new IllegalArgumentException("The passed proto must be an instance of VerifyPasswordResponse.");
        }
        zzp.zzv zzvVar = (zzp.zzv) zzjgVar;
        this.zza = Strings.emptyToNull(zzvVar.zza());
        this.zzb = Strings.emptyToNull(zzvVar.zzb());
        this.zzc = Strings.emptyToNull(zzvVar.zzc());
        this.zzd = Strings.emptyToNull(zzvVar.zzd());
        this.zze = Strings.emptyToNull(zzvVar.zze());
        this.zzf = Strings.emptyToNull(zzvVar.zzf());
        this.zzg = zzvVar.zzg();
        this.zzh = new ArrayList();
        Iterator<zzr> it = zzvVar.zzi().iterator();
        while (it.hasNext()) {
            this.zzh.add(zzfa.zza(it.next()));
        }
        this.zzi = zzvVar.zzh();
        return this;
    }
}
