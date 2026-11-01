package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.Contents;

/* JADX INFO: loaded from: classes2.dex */
@SafeParcelable.Class(creator = "OnContentsResponseCreator")
@SafeParcelable.Reserved({1})
public final class zzfh extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzfh> CREATOR = new zzfi();

    @SafeParcelable.Field(id = 2)
    final Contents zzes;

    @SafeParcelable.Field(id = 3)
    final boolean zzhv;

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzes, i, false);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzhv);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @SafeParcelable.Constructor
    public zzfh(@SafeParcelable.Param(id = 2) Contents contents, @SafeParcelable.Param(id = 3) boolean z) {
        this.zzes = contents;
        this.zzhv = z;
    }

    public final Contents zzar() {
        return this.zzes;
    }
}
