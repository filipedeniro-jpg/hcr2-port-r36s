package com.google.android.gms.internal.firebase_auth;

import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.auth.ActionCodeSettings;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzew implements com.google.firebase.auth.api.internal.zzfk<zzp.zzh> {
    private String zza;
    private String zzb;
    private String zzc;
    private String zzd;
    private ActionCodeSettings zze;

    @Nullable
    private String zzf;

    public zzew(zzgd zzgdVar) {
        this.zza = zza(zzgdVar);
    }

    private zzew(zzgd zzgdVar, ActionCodeSettings actionCodeSettings, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4) {
        this.zza = zza((zzgd) Preconditions.checkNotNull(zzgdVar));
        this.zze = (ActionCodeSettings) Preconditions.checkNotNull(actionCodeSettings);
        this.zzb = null;
        this.zzc = str2;
        this.zzd = str3;
        this.zzf = null;
    }

    public static zzew zza(ActionCodeSettings actionCodeSettings, String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(actionCodeSettings);
        return new zzew(zzgd.VERIFY_AND_CHANGE_EMAIL, actionCodeSettings, null, str2, str, null);
    }

    public final zzew zza(String str) {
        this.zzb = Preconditions.checkNotEmpty(str);
        return this;
    }

    public final zzew zzb(String str) {
        this.zzd = Preconditions.checkNotEmpty(str);
        return this;
    }

    public final zzew zza(ActionCodeSettings actionCodeSettings) {
        this.zze = (ActionCodeSettings) Preconditions.checkNotNull(actionCodeSettings);
        return this;
    }

    public final zzew zzc(@Nullable String str) {
        this.zzf = str;
        return this;
    }

    public final ActionCodeSettings zzb() {
        return this.zze;
    }

    private static String zza(zzgd zzgdVar) {
        int i = zzev.zza[zzgdVar.ordinal()];
        if (i == 1) {
            return "PASSWORD_RESET";
        }
        if (i == 2) {
            return "VERIFY_EMAIL";
        }
        if (i != 3) {
            return i != 4 ? "REQUEST_TYPE_UNSET_ENUM_VALUE" : "VERIFY_BEFORE_UPDATE_EMAIL";
        }
        return "EMAIL_SIGNIN";
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:17:0x0039  */
    @Override // com.google.firebase.auth.api.internal.zzfk
    public final /* synthetic */ zzjg zza() {
        byte b;
        zzgd zzgdVar;
        zzp.zzh.zza zzaVarZza = zzp.zzh.zza();
        switch (this.zza) {
            case "PASSWORD_RESET":
                b = 0;
                break;
            case "VERIFY_EMAIL":
                b = 1;
                break;
            case "VERIFY_BEFORE_UPDATE_EMAIL":
                b = 3;
                break;
            case "EMAIL_SIGNIN":
                b = 2;
                break;
            default:
                b = -1;
                break;
        }
        if (b == 0) {
            zzgdVar = zzgd.PASSWORD_RESET;
        } else if (b == 1) {
            zzgdVar = zzgd.VERIFY_EMAIL;
        } else if (b == 2) {
            zzgdVar = zzgd.EMAIL_SIGNIN;
        } else if (b == 3) {
            zzgdVar = zzgd.VERIFY_AND_CHANGE_EMAIL;
        } else {
            zzgdVar = zzgd.OOB_REQ_TYPE_UNSPECIFIED;
        }
        zzp.zzh.zza zzaVarZza2 = zzaVarZza.zza(zzgdVar);
        String str = this.zzb;
        if (str != null) {
            zzaVarZza2.zza(str);
        }
        String str2 = this.zzc;
        if (str2 != null) {
            zzaVarZza2.zzb(str2);
        }
        String str3 = this.zzd;
        if (str3 != null) {
            zzaVarZza2.zzc(str3);
        }
        ActionCodeSettings actionCodeSettings = this.zze;
        if (actionCodeSettings != null) {
            zzaVarZza2.zza(actionCodeSettings.getAndroidInstallApp()).zzb(this.zze.canHandleCodeInApp());
            if (this.zze.getUrl() != null) {
                zzaVarZza2.zzd(this.zze.getUrl());
            }
            if (this.zze.getIOSBundle() != null) {
                zzaVarZza2.zze(this.zze.getIOSBundle());
            }
            if (this.zze.zzb() != null) {
                zzaVarZza2.zzf(this.zze.zzb());
            }
            if (this.zze.getAndroidPackageName() != null) {
                zzaVarZza2.zzg(this.zze.getAndroidPackageName());
            }
            if (this.zze.getAndroidMinimumVersion() != null) {
                zzaVarZza2.zzh(this.zze.getAndroidMinimumVersion());
            }
            if (this.zze.zze() != null) {
                zzaVarZza2.zzj(this.zze.zze());
            }
        }
        String str4 = this.zzf;
        if (str4 != null) {
            zzaVarZza2.zzi(str4);
        }
        return (zzp.zzh) ((zzhx) zzaVarZza2.zzf());
    }
}
