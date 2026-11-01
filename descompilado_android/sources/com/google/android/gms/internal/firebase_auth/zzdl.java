package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.firebase.auth.PhoneAuthCredential;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
@SafeParcelable.Class(creator = "SignInWithPhoneNumberAidlRequestCreator")
public final class zzdl extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzdl> CREATOR = new zzdo();

    @SafeParcelable.Field(getter = "getCredential", id = 1)
    private final PhoneAuthCredential zza;

    @Nullable
    @SafeParcelable.Field(getter = "getTenantId", id = 2)
    private final String zzb;

    @SafeParcelable.Constructor
    public zzdl(@SafeParcelable.Param(id = 1) PhoneAuthCredential phoneAuthCredential, @Nullable @SafeParcelable.Param(id = 2) String str) {
        this.zza = phoneAuthCredential;
        this.zzb = str;
    }

    public final PhoneAuthCredential zza() {
        return this.zza;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zza, i, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
