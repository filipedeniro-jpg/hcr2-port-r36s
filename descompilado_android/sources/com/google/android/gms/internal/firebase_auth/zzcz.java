package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
@SafeParcelable.Class(creator = "SetFirebaseUiVersionAidlRequestCreator")
public final class zzcz extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzcz> CREATOR = new zzdc();

    @SafeParcelable.Field(getter = "getFirebaseUiVersion", id = 1)
    private final String zza;

    @SafeParcelable.Constructor
    public zzcz(@SafeParcelable.Param(id = 1) String str) {
        this.zza = str;
    }

    public final String zza() {
        return this.zza;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zza, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
