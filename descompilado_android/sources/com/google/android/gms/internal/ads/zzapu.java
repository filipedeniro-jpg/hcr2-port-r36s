package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
@SafeParcelable.Class(creator = "RewardedVideoAdRequestParcelCreator")
@SafeParcelable.Reserved({1})
public final class zzapu extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzapu> CREATOR = new zzapx();

    @SafeParcelable.Field(id = 3)
    public final String zzccm;

    @SafeParcelable.Field(id = 2)
    public final zztp zzdii;

    @SafeParcelable.Constructor
    public zzapu(@SafeParcelable.Param(id = 2) zztp zztpVar, @SafeParcelable.Param(id = 3) String str) {
        this.zzdii = zztpVar;
        this.zzccm = str;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzdii, i, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzccm, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
