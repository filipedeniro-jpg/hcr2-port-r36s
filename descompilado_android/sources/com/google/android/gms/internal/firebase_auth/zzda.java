package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzda implements Parcelable.Creator<zzcx> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzcx[] newArray(int i) {
        return new zzcx[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzcx createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        zzfk zzfkVar = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            if (SafeParcelReader.getFieldId(header) == 1) {
                zzfkVar = (zzfk) SafeParcelReader.createParcelable(parcel, header, zzfk.CREATOR);
            } else {
                SafeParcelReader.skipUnknownField(parcel, header);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzcx(zzfkVar);
    }
}
