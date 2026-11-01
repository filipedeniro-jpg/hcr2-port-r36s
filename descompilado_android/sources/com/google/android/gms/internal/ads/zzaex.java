package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
@SafeParcelable.Class(creator = "AdapterStatusParcelCreator")
public final class zzaex extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzaex> CREATOR = new zzafa();

    @SafeParcelable.Field(id = 4)
    public final String description;

    @SafeParcelable.Field(id = 1)
    public final String zzcyb;

    @SafeParcelable.Field(id = 2)
    public final boolean zzcyc;

    @SafeParcelable.Field(id = 3)
    public final int zzcyd;

    @SafeParcelable.Constructor
    public zzaex(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) boolean z, @SafeParcelable.Param(id = 3) int i, @SafeParcelable.Param(id = 4) String str2) {
        this.zzcyb = str;
        this.zzcyc = z;
        this.zzcyd = i;
        this.description = str2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzcyb, false);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzcyc);
        SafeParcelWriter.writeInt(parcel, 3, this.zzcyd);
        SafeParcelWriter.writeString(parcel, 4, this.description, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
