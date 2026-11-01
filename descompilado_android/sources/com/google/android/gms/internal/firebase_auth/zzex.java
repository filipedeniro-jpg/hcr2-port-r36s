package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzex implements Parcelable.Creator<zzey> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzey[] newArray(int i) {
        return new zzey[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzey createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String strCreateString = null;
        String strCreateString2 = null;
        Long longObject = null;
        String strCreateString3 = null;
        Long longObject2 = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 2) {
                strCreateString = SafeParcelReader.createString(parcel, header);
            } else if (fieldId == 3) {
                strCreateString2 = SafeParcelReader.createString(parcel, header);
            } else if (fieldId == 4) {
                longObject = SafeParcelReader.readLongObject(parcel, header);
            } else if (fieldId == 5) {
                strCreateString3 = SafeParcelReader.createString(parcel, header);
            } else if (fieldId == 6) {
                longObject2 = SafeParcelReader.readLongObject(parcel, header);
            } else {
                SafeParcelReader.skipUnknownField(parcel, header);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzey(strCreateString, strCreateString2, longObject, strCreateString3, longObject2);
    }
}
