package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
@SafeParcelable.Class(creator = "OnChangesResponseCreator")
@SafeParcelable.Reserved({1})
public final class zzff extends com.google.android.gms.drive.zzu {
    public static final Parcelable.Creator<zzff> CREATOR = new zzfg();

    @SafeParcelable.Field(id = 2)
    private final DataHolder zzhr;

    @SafeParcelable.Field(id = 3)
    private final List<DriveId> zzhs;

    @SafeParcelable.Field(id = 4)
    private final com.google.android.gms.drive.zza zzht;

    @SafeParcelable.Field(id = 5)
    private final boolean zzhu;

    @Override // com.google.android.gms.drive.zzu
    protected final void zza(Parcel parcel, int i) {
        int i2 = i | 1;
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzhr, i2, false);
        SafeParcelWriter.writeTypedList(parcel, 3, this.zzhs, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzht, i2, false);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzhu);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @SafeParcelable.Constructor
    public zzff(@SafeParcelable.Param(id = 2) DataHolder dataHolder, @SafeParcelable.Param(id = 3) List<DriveId> list, @SafeParcelable.Param(id = 4) com.google.android.gms.drive.zza zzaVar, @SafeParcelable.Param(id = 5) boolean z) {
        this.zzhr = dataHolder;
        this.zzhs = list;
        this.zzht = zzaVar;
        this.zzhu = z;
    }
}
