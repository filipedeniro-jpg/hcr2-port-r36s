package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
@SafeParcelable.Class(creator = "IconAdOptionsParcelCreator")
@SafeParcelable.Reserved({1})
public final class zzwq extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzwq> CREATOR = new zzwp();

    @SafeParcelable.Field(id = 2)
    private final int zzcds;

    @SafeParcelable.Constructor
    public zzwq(@SafeParcelable.Param(id = 2) int i) {
        this.zzcds = i;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.zzcds);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
