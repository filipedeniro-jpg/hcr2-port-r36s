package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: classes2.dex */
public final class zzfk implements Parcelable.Creator<zzfj> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzfj[] newArray(int i) {
        return new zzfj[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzfj createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        zzei zzeiVar = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            if (SafeParcelReader.getFieldId(header) == 2) {
                zzeiVar = (zzei) SafeParcelReader.createParcelable(parcel, header, zzei.CREATOR);
            } else {
                SafeParcelReader.skipUnknownField(parcel, header);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzfj(zzeiVar);
    }
}
