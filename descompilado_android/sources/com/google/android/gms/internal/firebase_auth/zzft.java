package com.google.android.gms.internal.firebase_auth;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.google.android.gms.common.util.Strings;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzft implements com.google.firebase.auth.api.internal.zzea<zzft, zzp.zzr> {
    private boolean zza;
    private boolean zzb;
    private String zzc;
    private String zzd;
    private long zze;
    private String zzf;
    private String zzg;
    private String zzh;
    private String zzi;
    private String zzj;
    private String zzk;
    private boolean zzl;
    private String zzm;
    private String zzn;
    private String zzo;
    private String zzp;
    private String zzq;
    private String zzr;
    private List<zzfa> zzs;
    private String zzt;

    public final boolean zzb() {
        return this.zza;
    }

    public final String zzc() {
        return this.zzc;
    }

    public final String zzd() {
        return this.zzg;
    }

    public final String zze() {
        return this.zzj;
    }

    public final String zzf() {
        return this.zzk;
    }

    @Nullable
    public final String zzg() {
        return this.zzd;
    }

    public final long zzh() {
        return this.zze;
    }

    public final boolean zzi() {
        return this.zzl;
    }

    public final String zzj() {
        return this.zzp;
    }

    public final boolean zzk() {
        return this.zza || !TextUtils.isEmpty(this.zzp);
    }

    @Nullable
    public final String zzl() {
        return this.zzr;
    }

    public final List<zzfa> zzm() {
        return this.zzs;
    }

    public final String zzn() {
        return this.zzt;
    }

    public final boolean zzo() {
        return !TextUtils.isEmpty(this.zzt);
    }

    @Nullable
    public final com.google.firebase.auth.zzg zzp() {
        if (TextUtils.isEmpty(this.zzm) && TextUtils.isEmpty(this.zzn)) {
            return null;
        }
        return com.google.firebase.auth.zzg.zza(this.zzj, this.zzn, this.zzm, this.zzq, this.zzo);
    }

    @Override // com.google.firebase.auth.api.internal.zzea
    public final zzjq<zzp.zzr> zza() {
        return zzp.zzr.zzu();
    }

    @Override // com.google.firebase.auth.api.internal.zzea
    public final /* synthetic */ com.google.firebase.auth.api.internal.zzea zza(zzjg zzjgVar) {
        if (!(zzjgVar instanceof zzp.zzr)) {
            throw new IllegalArgumentException("The passed proto must be an instance of VerifyAssertionResponse.");
        }
        zzp.zzr zzrVar = (zzp.zzr) zzjgVar;
        this.zza = zzrVar.zzg();
        this.zzb = zzrVar.zzi();
        this.zzc = Strings.emptyToNull(zzrVar.zzf());
        this.zzd = Strings.emptyToNull(zzrVar.zzk());
        this.zze = zzrVar.zzl();
        this.zzf = Strings.emptyToNull(zzrVar.zzd());
        this.zzg = Strings.emptyToNull(zzrVar.zzb());
        this.zzh = Strings.emptyToNull(zzrVar.zze());
        this.zzi = Strings.emptyToNull(zzrVar.zzc());
        this.zzj = Strings.emptyToNull(zzrVar.zza());
        this.zzk = Strings.emptyToNull(zzrVar.zzn());
        this.zzl = zzrVar.zzp();
        this.zzm = zzrVar.zzh();
        this.zzn = zzrVar.zzm();
        this.zzp = Strings.emptyToNull(zzrVar.zzo());
        this.zzq = Strings.emptyToNull(zzrVar.zzq());
        this.zzr = Strings.emptyToNull(zzrVar.zzr());
        this.zzs = new ArrayList();
        Iterator<zzr> it = zzrVar.zzt().iterator();
        while (it.hasNext()) {
            this.zzs.add(zzfa.zza(it.next()));
        }
        this.zzt = Strings.emptyToNull(zzrVar.zzs());
        this.zzo = Strings.emptyToNull(zzrVar.zzj());
        return this;
    }
}
