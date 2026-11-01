package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.query.internal.FilterHolder;

/* JADX INFO: loaded from: classes2.dex */
@SafeParcelable.Class(creator = "OpenFileIntentSenderRequestCreator")
@SafeParcelable.Reserved({1})
public final class zzgm extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzgm> CREATOR = new zzgn();

    @SafeParcelable.Field(id = 2)
    private final String zzba;

    @SafeParcelable.Field(id = 3)
    private final String[] zzbb;

    @SafeParcelable.Field(id = 4)
    private final DriveId zzbd;

    @SafeParcelable.Field(id = 5)
    private final FilterHolder zzbe;

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzba, false);
        SafeParcelWriter.writeStringArray(parcel, 3, this.zzbb, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzbd, i, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzbe, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @SafeParcelable.Constructor
    @VisibleForTesting
    public zzgm(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) String[] strArr, @SafeParcelable.Param(id = 4) DriveId driveId, @SafeParcelable.Param(id = 5) FilterHolder filterHolder) {
        this.zzba = str;
        this.zzbb = strArr;
        this.zzbd = driveId;
        this.zzbe = filterHolder;
    }
}
