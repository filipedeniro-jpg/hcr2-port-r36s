package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
@SafeParcelable.Class(creator = "SendVerificationCodeRequestCreator")
public final class zzfk extends AbstractSafeParcelable implements com.google.firebase.auth.api.internal.zzfk<zzp.zzk> {
    public static final Parcelable.Creator<zzfk> CREATOR = new zzfj();

    @SafeParcelable.Field(getter = "getPhoneNumber", id = 1)
    private final String zza;

    @SafeParcelable.Field(getter = "getTimeoutInSeconds", id = 2)
    private final long zzb;

    @SafeParcelable.Field(getter = "getForceNewSmsVerificationSession", id = 3)
    private final boolean zzc;

    @SafeParcelable.Field(getter = "getLanguageHeader", id = 4)
    private final String zzd;

    @Nullable
    @SafeParcelable.Field(getter = "getTenantId", id = 5)
    private final String zze;

    @Nullable
    @SafeParcelable.Field(getter = "getRecaptchaToken", id = 6)
    private final String zzf;

    @SafeParcelable.Constructor
    public zzfk(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) long j, @SafeParcelable.Param(id = 3) boolean z, @SafeParcelable.Param(id = 4) String str2, @Nullable @SafeParcelable.Param(id = 5) String str3, @Nullable @SafeParcelable.Param(id = 6) String str4) {
        this.zza = Preconditions.checkNotEmpty(str);
        this.zzb = j;
        this.zzc = z;
        this.zzd = str2;
        this.zze = str3;
        this.zzf = str4;
    }

    public final String zzb() {
        return this.zza;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zza, false);
        SafeParcelWriter.writeLong(parcel, 2, this.zzb);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzc);
        SafeParcelWriter.writeString(parcel, 4, this.zzd, false);
        SafeParcelWriter.writeString(parcel, 5, this.zze, false);
        SafeParcelWriter.writeString(parcel, 6, this.zzf, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @Override // com.google.firebase.auth.api.internal.zzfk
    public final /* synthetic */ zzjg zza() {
        zzp.zzk.zza zzaVarZza = zzp.zzk.zza().zza(this.zza);
        String str = this.zze;
        if (str != null) {
            zzaVarZza.zzc(str);
        }
        String str2 = this.zzf;
        if (str2 != null) {
            zzaVarZza.zzb(str2);
        }
        return (zzp.zzk) ((zzhx) zzaVarZza.zzf());
    }
}
