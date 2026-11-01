package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
@SafeParcelable.Class(creator = "OnFailedMfaSignInAidlResponseCreator")
public final class zzed extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzed> CREATOR = new zzef();

    @SafeParcelable.Field(getter = "getMfaPendingCredential", id = 1)
    private String zza;

    @SafeParcelable.Field(getter = "getMfaInfoList", id = 2)
    private List<zzfa> zzb;

    @SafeParcelable.Field(getter = "getDefaultOAuthCredential", id = 3)
    private com.google.firebase.auth.zzg zzc;

    @SafeParcelable.Constructor
    public zzed(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) List<zzfa> list, @Nullable @SafeParcelable.Param(id = 3) com.google.firebase.auth.zzg zzgVar) {
        this.zza = str;
        this.zzb = list;
        this.zzc = zzgVar;
    }

    public final String zza() {
        return this.zza;
    }

    public final com.google.firebase.auth.zzg zzb() {
        return this.zzc;
    }

    public final List<com.google.firebase.auth.zzy> zzc() {
        return com.google.firebase.auth.internal.zzar.zza(this.zzb);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zza, false);
        SafeParcelWriter.writeTypedList(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzc, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
