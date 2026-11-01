package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzep implements Parcelable.Creator<zzeq> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzeq[] newArray(int i) {
        return new zzeq[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzeq createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        zzeu zzeuVar = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            if (SafeParcelReader.getFieldId(header) == 2) {
                zzeuVar = (zzeu) SafeParcelReader.createParcelable(parcel, header, zzeu.CREATOR);
            } else {
                SafeParcelReader.skipUnknownField(parcel, header);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzeq(zzeuVar);
    }
}
