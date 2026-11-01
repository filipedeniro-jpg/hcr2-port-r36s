package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
@SafeParcelable.Class(creator = "SendVerificationCodeAidlRequestCreator")
public final class zzcx extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzcx> CREATOR = new zzda();

    @SafeParcelable.Field(getter = "getSendVerificationCodeRequest", id = 1)
    private final zzfk zza;

    @SafeParcelable.Constructor
    public zzcx(@SafeParcelable.Param(id = 1) zzfk zzfkVar) {
        this.zza = zzfkVar;
    }

    public final zzfk zza() {
        return this.zza;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zza, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
