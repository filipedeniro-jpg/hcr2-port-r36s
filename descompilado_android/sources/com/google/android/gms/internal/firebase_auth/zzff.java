package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.Strings;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
@SafeParcelable.Class(creator = "ResetPasswordResponseCreator")
@SafeParcelable.Reserved({1})
public final class zzff extends AbstractSafeParcelable implements com.google.firebase.auth.api.internal.zzea<zzff, zzp.zzj> {
    public static final Parcelable.Creator<zzff> CREATOR = new zzfh();

    @SafeParcelable.Field(getter = "getEmail", id = 2)
    private String zza;

    @SafeParcelable.Field(getter = "getNewEmail", id = 3)
    private String zzb;

    @SafeParcelable.Field(getter = "getRequestType", id = 4)
    private String zzc;

    @SafeParcelable.Field(getter = "getMfaInfo", id = 5)
    private zzfa zzd;

    public zzff() {
    }

    @SafeParcelable.Constructor
    zzff(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) String str2, @SafeParcelable.Param(id = 4) String str3, @SafeParcelable.Param(id = 5) zzfa zzfaVar) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
        this.zzd = zzfaVar;
    }

    public final String zzb() {
        return this.zza;
    }

    public final String zzc() {
        return this.zzb;
    }

    public final String zzd() {
        return this.zzc;
    }

    @Nullable
    public final zzfa zze() {
        return this.zzd;
    }

    public final boolean zzf() {
        return this.zza != null;
    }

    public final boolean zzg() {
        return this.zzb != null;
    }

    public final boolean zzh() {
        return this.zzc != null;
    }

    public final boolean zzi() {
        return this.zzd != null;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zza, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzb, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzc, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzd, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @Override // com.google.firebase.auth.api.internal.zzea
    public final zzjq<zzp.zzj> zza() {
        return zzp.zzj.zzf();
    }

    @Override // com.google.firebase.auth.api.internal.zzea
    public final /* synthetic */ com.google.firebase.auth.api.internal.zzea zza(zzjg zzjgVar) {
        String str;
        if (!(zzjgVar instanceof zzp.zzj)) {
            throw new IllegalArgumentException("The passed proto must be an instance of ResetPasswordResponse.");
        }
        zzp.zzj zzjVar = (zzp.zzj) zzjgVar;
        this.zza = Strings.emptyToNull(zzjVar.zza());
        this.zzb = Strings.emptyToNull(zzjVar.zzb());
        switch (zzjVar.zzc()) {
            case VERIFY_EMAIL:
                str = "VERIFY_EMAIL";
                break;
            case PASSWORD_RESET:
                str = "PASSWORD_RESET";
                break;
            case EMAIL_SIGNIN:
                str = "EMAIL_SIGNIN";
                break;
            case VERIFY_AND_CHANGE_EMAIL:
                str = "VERIFY_BEFORE_UPDATE_EMAIL";
                break;
            case RECOVER_EMAIL:
                str = "RECOVER_EMAIL";
                break;
            case REVERT_SECOND_FACTOR_ADDITION:
                str = "REVERT_SECOND_FACTOR_ADDITION";
                break;
            default:
                str = null;
                break;
        }
        this.zzc = str;
        if (zzjVar.zzd()) {
            this.zzd = zzfa.zza(zzjVar.zze());
        }
        return this;
    }
}
