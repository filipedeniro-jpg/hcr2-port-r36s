package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.drive.DriveId;

/* JADX INFO: loaded from: classes2.dex */
@SafeParcelable.Class(creator = "TrashResourceRequestCreator")
@SafeParcelable.Reserved({1})
public final class zzhb extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzhb> CREATOR = new zzhc();

    @SafeParcelable.Field(id = 2)
    private final DriveId zzdd;

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzdd, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @SafeParcelable.Constructor
    @VisibleForTesting
    public zzhb(@SafeParcelable.Param(id = 2) DriveId driveId) {
        this.zzdd = driveId;
    }
}
