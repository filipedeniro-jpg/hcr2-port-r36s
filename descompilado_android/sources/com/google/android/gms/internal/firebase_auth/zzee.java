package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzee implements Parcelable.Creator<zzeb> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzeb[] newArray(int i) {
        return new zzeb[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzeb createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        Status status = null;
        com.google.firebase.auth.zzg zzgVar = null;
        String strCreateString = null;
        String strCreateString2 = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 1) {
                status = (Status) SafeParcelReader.createParcelable(parcel, header, Status.CREATOR);
            } else if (fieldId == 2) {
                zzgVar = (com.google.firebase.auth.zzg) SafeParcelReader.createParcelable(parcel, header, com.google.firebase.auth.zzg.CREATOR);
            } else if (fieldId == 3) {
                strCreateString = SafeParcelReader.createString(parcel, header);
            } else if (fieldId == 4) {
                strCreateString2 = SafeParcelReader.createString(parcel, header);
            } else {
                SafeParcelReader.skipUnknownField(parcel, header);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzeb(status, zzgVar, strCreateString, strCreateString2);
    }
}
