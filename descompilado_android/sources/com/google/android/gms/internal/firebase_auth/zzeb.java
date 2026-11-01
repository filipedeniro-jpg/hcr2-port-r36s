package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
@SafeParcelable.Class(creator = "OnFailedIdpSignInAidlResponseCreator")
public final class zzeb extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzeb> CREATOR = new zzee();

    @SafeParcelable.Field(getter = "getStatus", id = 1)
    private final Status zza;

    @SafeParcelable.Field(getter = "getDefaultOAuthCredential", id = 2)
    private final com.google.firebase.auth.zzg zzb;

    @SafeParcelable.Field(getter = "getEmail", id = 3)
    private final String zzc;

    @SafeParcelable.Field(getter = "getTenantId", id = 4)
    private final String zzd;

    @SafeParcelable.Constructor
    public zzeb(@SafeParcelable.Param(id = 1) Status status, @SafeParcelable.Param(id = 2) com.google.firebase.auth.zzg zzgVar, @SafeParcelable.Param(id = 3) String str, @Nullable @SafeParcelable.Param(id = 4) String str2) {
        this.zza = status;
        this.zzb = zzgVar;
        this.zzc = str;
        this.zzd = str2;
    }

    public final Status zza() {
        return this.zza;
    }

    public final com.google.firebase.auth.zzg zzb() {
        return this.zzb;
    }

    public final String zzc() {
        return this.zzc;
    }

    public final String zzd() {
        return this.zzd;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zza, i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzb, i, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzd, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
