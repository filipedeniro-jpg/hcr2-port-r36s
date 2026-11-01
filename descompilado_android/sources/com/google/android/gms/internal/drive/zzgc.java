package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: classes2.dex */
public final class zzgc implements Parcelable.Creator<zzfx> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzfx[] newArray(int i) {
        return new zzfx[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzfx createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        boolean z = false;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            if (SafeParcelReader.getFieldId(header) == 2) {
                z = SafeParcelReader.readBoolean(parcel, header);
            } else {
                SafeParcelReader.skipUnknownField(parcel, header);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzfx(z);
    }
}
