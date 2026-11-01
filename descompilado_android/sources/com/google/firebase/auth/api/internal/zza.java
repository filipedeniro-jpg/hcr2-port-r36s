package com.google.firebase.auth.api.internal;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.internal.firebase_auth.zzen;
import com.google.android.gms.internal.firebase_auth.zzeo;
import com.google.android.gms.internal.firebase_auth.zzfl;
import com.google.android.gms.internal.firebase_auth.zzfn;
import com.google.android.gms.internal.firebase_auth.zzfo;
import com.google.android.gms.internal.firebase_auth.zzfr;
import com.google.android.gms.internal.firebase_auth.zzft;
import com.google.android.gms.internal.firebase_auth.zzfw;
import com.google.android.gms.internal.firebase_auth.zzfx;
import com.google.android.gms.internal.firebase_auth.zzfz;
import com.google.android.gms.internal.firebase_auth.zzgd;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.UserProfileChangeRequest;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zza {
    private static final Logger zza = new Logger("FBAuthApiDispatcher", new String[0]);
    private final zzfc zzb;
    private final zzar zzc;

    public zza(zzfc zzfcVar, zzar zzarVar) {
        this.zzb = (zzfc) Preconditions.checkNotNull(zzfcVar);
        this.zzc = (zzar) Preconditions.checkNotNull(zzarVar);
    }

    public final void zza(String str, zzdr zzdrVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzdrVar);
        this.zzb.zza(new zzeo(str), new zzc(this, zzdrVar));
    }

    public final void zza(zzfw zzfwVar, zzdr zzdrVar) {
        Preconditions.checkNotNull(zzfwVar);
        Preconditions.checkNotNull(zzdrVar);
        this.zzb.zza(zzfwVar, new zzl(this, zzdrVar));
    }

    public final void zza(Context context, zzfr zzfrVar, zzdr zzdrVar) {
        Preconditions.checkNotNull(zzfrVar);
        Preconditions.checkNotNull(zzdrVar);
        if (this.zzc.zza()) {
            zzfrVar.zzc(true);
        }
        this.zzb.zza((Context) null, zzfrVar, new zzx(this, zzdrVar));
    }

    public final void zzb(@Nullable String str, zzdr zzdrVar) {
        Preconditions.checkNotNull(zzdrVar);
        this.zzb.zza(new zzfn(str), new zzag(this, zzdrVar));
    }

    public final void zza(String str, UserProfileChangeRequest userProfileChangeRequest, zzdr zzdrVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(userProfileChangeRequest);
        Preconditions.checkNotNull(zzdrVar);
        zza(str, new zzaf(this, userProfileChangeRequest, zzdrVar));
    }

    public final void zza(String str, String str2, zzdr zzdrVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzdrVar);
        zza(str, new zzai(this, str2, zzdrVar));
    }

    public final void zzb(String str, String str2, zzdr zzdrVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzdrVar);
        zza(str, new zzah(this, str2, zzdrVar));
    }

    public final void zzc(String str, @Nullable String str2, zzdr zzdrVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzdrVar);
        zzfl zzflVar = new zzfl();
        zzflVar.zzh(str);
        zzflVar.zzi(str2);
        this.zzb.zza(zzflVar, new zzak(this, zzdrVar));
    }

    private final void zza(String str, zzfe<com.google.android.gms.internal.firebase_auth.zzey> zzfeVar) {
        Preconditions.checkNotNull(zzfeVar);
        Preconditions.checkNotEmpty(str);
        com.google.android.gms.internal.firebase_auth.zzey zzeyVarZzb = com.google.android.gms.internal.firebase_auth.zzey.zzb(str);
        if (zzeyVarZzb.zzb()) {
            zzfeVar.zza(zzeyVarZzb);
        } else {
            this.zzb.zza(new zzeo(zzeyVarZzb.zzc()), new zzaj(this, zzfeVar));
        }
    }

    public final void zza(String str, String str2, @Nullable String str3, zzdr zzdrVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzdrVar);
        this.zzb.zza(new zzfn(str, str2, null, str3), new zzb(this, zzdrVar));
    }

    public final void zza(Context context, String str, String str2, @Nullable String str3, zzdr zzdrVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzdrVar);
        this.zzb.zza((Context) null, new zzfx(str, str2, str3), new zze(this, zzdrVar));
    }

    public final void zza(EmailAuthCredential emailAuthCredential, zzdr zzdrVar) {
        Preconditions.checkNotNull(emailAuthCredential);
        Preconditions.checkNotNull(zzdrVar);
        if (emailAuthCredential.zze()) {
            zza(emailAuthCredential.zzd(), new zzd(this, emailAuthCredential, zzdrVar));
        } else {
            zza(new com.google.android.gms.internal.firebase_auth.zzej(emailAuthCredential, null), zzdrVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(com.google.android.gms.internal.firebase_auth.zzej zzejVar, zzdr zzdrVar) {
        Preconditions.checkNotNull(zzejVar);
        Preconditions.checkNotNull(zzdrVar);
        this.zzb.zza(zzejVar, new zzg(this, zzdrVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzdr zzdrVar, com.google.android.gms.internal.firebase_auth.zzey zzeyVar, zzfl zzflVar, zzfb zzfbVar) {
        Preconditions.checkNotNull(zzdrVar);
        Preconditions.checkNotNull(zzeyVar);
        Preconditions.checkNotNull(zzflVar);
        Preconditions.checkNotNull(zzfbVar);
        this.zzb.zza(new zzen(zzeyVar.zzd()), new zzf(this, zzfbVar, zzdrVar, zzeyVar, zzflVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzdr zzdrVar, com.google.android.gms.internal.firebase_auth.zzey zzeyVar, com.google.android.gms.internal.firebase_auth.zzes zzesVar, zzfl zzflVar, zzfb zzfbVar) {
        Preconditions.checkNotNull(zzdrVar);
        Preconditions.checkNotNull(zzeyVar);
        Preconditions.checkNotNull(zzesVar);
        Preconditions.checkNotNull(zzflVar);
        Preconditions.checkNotNull(zzfbVar);
        this.zzb.zza(zzflVar, new zzi(this, zzflVar, zzesVar, zzdrVar, zzeyVar, zzfbVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static com.google.android.gms.internal.firebase_auth.zzey zza(com.google.android.gms.internal.firebase_auth.zzey zzeyVar, zzfo zzfoVar) {
        Preconditions.checkNotNull(zzeyVar);
        Preconditions.checkNotNull(zzfoVar);
        String strZzb = zzfoVar.zzb();
        String strZzc = zzfoVar.zzc();
        return (TextUtils.isEmpty(strZzb) || TextUtils.isEmpty(strZzc)) ? zzeyVar : new com.google.android.gms.internal.firebase_auth.zzey(strZzc, strZzb, Long.valueOf(zzfoVar.zzd()), zzeyVar.zzf());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(com.google.android.gms.internal.firebase_auth.zzey zzeyVar, @Nullable String str, @Nullable String str2, @Nullable Boolean bool, @Nullable com.google.firebase.auth.zzg zzgVar, zzdr zzdrVar, zzfb zzfbVar) {
        Preconditions.checkNotNull(zzeyVar);
        Preconditions.checkNotNull(zzfbVar);
        Preconditions.checkNotNull(zzdrVar);
        this.zzb.zza(new zzen(zzeyVar.zzd()), new zzh(this, zzfbVar, str2, str, bool, zzgVar, zzdrVar, zzeyVar));
    }

    public final void zzd(String str, @Nullable String str2, zzdr zzdrVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzdrVar);
        this.zzb.zza(new com.google.android.gms.internal.firebase_auth.zzeg(str, str2), new zzk(this, zzdrVar));
    }

    public final void zza(String str, ActionCodeSettings actionCodeSettings, @Nullable String str2, zzdr zzdrVar) {
        com.google.android.gms.internal.firebase_auth.zzew zzewVar;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzdrVar);
        zzgd zzgdVarZza = zzgd.zza(actionCodeSettings.zzd());
        if (zzgdVarZza != null) {
            zzewVar = new com.google.android.gms.internal.firebase_auth.zzew(zzgdVarZza);
        } else {
            zzewVar = new com.google.android.gms.internal.firebase_auth.zzew(zzgd.OOB_REQ_TYPE_UNSPECIFIED);
        }
        zzewVar.zza(str);
        zzewVar.zza(actionCodeSettings);
        zzewVar.zzc(str2);
        this.zzb.zza(zzewVar, new zzj(this, zzdrVar));
    }

    public final void zza(String str, @Nullable ActionCodeSettings actionCodeSettings, zzdr zzdrVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzdrVar);
        com.google.android.gms.internal.firebase_auth.zzew zzewVar = new com.google.android.gms.internal.firebase_auth.zzew(zzgd.VERIFY_EMAIL);
        zzewVar.zzb(str);
        if (actionCodeSettings != null) {
            zzewVar.zza(actionCodeSettings);
        }
        zzb(zzewVar, zzdrVar);
    }

    public final void zze(String str, @Nullable String str2, zzdr zzdrVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzdrVar);
        this.zzb.zza(new com.google.android.gms.internal.firebase_auth.zzfg(str, null, str2), new zzm(this, zzdrVar));
    }

    public final void zzb(String str, String str2, @Nullable String str3, zzdr zzdrVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzdrVar);
        this.zzb.zza(new com.google.android.gms.internal.firebase_auth.zzfg(str, str2, str3), new zzo(this, zzdrVar));
    }

    public final void zza(com.google.android.gms.internal.firebase_auth.zzfk zzfkVar, zzdr zzdrVar) {
        Preconditions.checkNotEmpty(zzfkVar.zzb());
        Preconditions.checkNotNull(zzdrVar);
        this.zzb.zza(zzfkVar, new zzn(this, zzdrVar));
    }

    public final void zza(Context context, zzfz zzfzVar, zzdr zzdrVar) {
        Preconditions.checkNotNull(zzfzVar);
        Preconditions.checkNotNull(zzdrVar);
        this.zzb.zza((Context) null, zzfzVar, new zzq(this, zzdrVar));
    }

    public final void zzc(String str, String str2, String str3, zzdr zzdrVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotEmpty(str3);
        Preconditions.checkNotNull(zzdrVar);
        zza(str3, new zzp(this, str, str2, zzdrVar));
    }

    public final void zza(Context context, String str, zzfz zzfzVar, zzdr zzdrVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzfzVar);
        Preconditions.checkNotNull(zzdrVar);
        zza(str, new zzs(this, zzfzVar, null, zzdrVar));
    }

    public final void zza(String str, zzfr zzfrVar, zzdr zzdrVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzfrVar);
        Preconditions.checkNotNull(zzdrVar);
        zza(str, new zzu(this, zzfrVar, zzdrVar));
    }

    public final void zzc(String str, zzdr zzdrVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzdrVar);
        zza(str, new zzw(this, zzdrVar));
    }

    public final void zzf(String str, String str2, zzdr zzdrVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(zzdrVar);
        zza(str2, new zzv(this, str, zzdrVar));
    }

    public final void zza(com.google.android.gms.internal.firebase_auth.zzew zzewVar, zzdr zzdrVar) {
        zzb(zzewVar, zzdrVar);
    }

    public final void zzd(String str, zzdr zzdrVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzdrVar);
        zza(str, new zzaa(this, zzdrVar));
    }

    public final void zze(String str, zzdr zzdrVar) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzdrVar);
        zza(str, new zzac(this, zzdrVar));
    }

    public final void zzf(@Nullable String str, zzdr zzdrVar) {
        Preconditions.checkNotNull(zzdrVar);
        this.zzb.zza(str, new zzae(this, zzdrVar));
    }

    private final void zzb(com.google.android.gms.internal.firebase_auth.zzew zzewVar, zzdr zzdrVar) {
        Preconditions.checkNotNull(zzewVar);
        Preconditions.checkNotNull(zzdrVar);
        this.zzb.zza(zzewVar, new zzad(this, zzdrVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzft zzftVar, zzdr zzdrVar, zzfb zzfbVar) {
        Status statusZza;
        if (zzftVar.zzk()) {
            com.google.firebase.auth.zzg zzgVarZzp = zzftVar.zzp();
            String strZzd = zzftVar.zzd();
            String strZzl = zzftVar.zzl();
            if (zzftVar.zzb()) {
                statusZza = new Status(FirebaseError.ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL);
            } else {
                statusZza = com.google.firebase.auth.internal.zzy.zza(zzftVar.zzj());
            }
            if (this.zzc.zza()) {
                zzdrVar.zza(new com.google.android.gms.internal.firebase_auth.zzeb(statusZza, zzgVarZzp, strZzd, strZzl));
                return;
            } else {
                zzdrVar.zza(statusZza);
                return;
            }
        }
        zza(new com.google.android.gms.internal.firebase_auth.zzey(zzftVar.zzg(), zzftVar.zzc(), Long.valueOf(zzftVar.zzh()), "Bearer"), zzftVar.zzf(), zzftVar.zze(), Boolean.valueOf(zzftVar.zzi()), zzftVar.zzp(), zzdrVar, zzfbVar);
    }
}
