package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;

/* JADX INFO: loaded from: classes2.dex */
@SafeParcelable.Class(creator = "AddEventListenerRequestCreator")
@SafeParcelable.Reserved({1})
public final class zzj extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzj> CREATOR = new zzk();

    @Nullable
    @SafeParcelable.Field(id = 4)
    private final com.google.android.gms.drive.events.zze zzbv;

    @SafeParcelable.Field(id = 3)
    final int zzda;

    @Nullable
    @SafeParcelable.Field(id = 5)
    private final com.google.android.gms.drive.events.zzx zzdb;

    @Nullable
    @SafeParcelable.Field(id = 6)
    private final com.google.android.gms.drive.events.zzt zzdc;

    @Nullable
    @SafeParcelable.Field(id = 2)
    final DriveId zzk;

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzk, i, false);
        SafeParcelWriter.writeInt(parcel, 3, this.zzda);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzbv, i, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzdb, i, false);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zzdc, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @SafeParcelable.Constructor
    zzj(@SafeParcelable.Param(id = 2) DriveId driveId, @SafeParcelable.Param(id = 3) int i, @SafeParcelable.Param(id = 4) com.google.android.gms.drive.events.zze zzeVar, @SafeParcelable.Param(id = 5) com.google.android.gms.drive.events.zzx zzxVar, @SafeParcelable.Param(id = 6) com.google.android.gms.drive.events.zzt zztVar) {
        this.zzk = driveId;
        this.zzda = i;
        this.zzbv = zzeVar;
        this.zzdb = zzxVar;
        this.zzdc = zztVar;
    }

    public zzj(int i, DriveId driveId) {
        this((DriveId) Preconditions.checkNotNull(driveId), 1, null, null, null);
    }
}
