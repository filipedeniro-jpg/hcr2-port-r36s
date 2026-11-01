package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.drive.DriveId;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
@SafeParcelable.Class(creator = "SetResourceParentsRequestCreator")
@SafeParcelable.Reserved({1})
public final class zzgw extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzgw> CREATOR = new zzgx();

    @SafeParcelable.Field(id = 2)
    private final DriveId zzis;

    @SafeParcelable.Field(id = 3)
    private final List<DriveId> zzit;

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzis, i, false);
        SafeParcelWriter.writeTypedList(parcel, 3, this.zzit, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @SafeParcelable.Constructor
    @VisibleForTesting
    public zzgw(@SafeParcelable.Param(id = 2) DriveId driveId, @SafeParcelable.Param(id = 3) List<DriveId> list) {
        this.zzis = driveId;
        this.zzit = list;
    }
}
