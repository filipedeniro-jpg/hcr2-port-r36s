package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
@SafeParcelable.Class(creator = "ExceptionParcelCreator")
public final class zzauw extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzauw> CREATOR = new zzauy();

    @SafeParcelable.Field(id = 2)
    public final int errorCode;

    @SafeParcelable.Field(id = 1)
    public final String zzdtk;

    @Nullable
    public static zzauw zza(Throwable th, int i) {
        return new zzauw(th.getMessage(), i);
    }

    @SafeParcelable.Constructor
    zzauw(@Nullable @SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) int i) {
        this.zzdtk = str == null ? "" : str;
        this.errorCode = i;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzdtk, false);
        SafeParcelWriter.writeInt(parcel, 2, this.errorCode);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
