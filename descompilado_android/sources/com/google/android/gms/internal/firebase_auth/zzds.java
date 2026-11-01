package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzds implements Parcelable.Creator<zzdp> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzdp[] newArray(int i) {
        return new zzdp[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzdp createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        com.google.firebase.auth.zzae zzaeVar = null;
        String strCreateString = null;
        String strCreateString2 = null;
        long j = 0;
        boolean z = false;
        boolean z2 = false;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 1:
                    zzaeVar = (com.google.firebase.auth.zzae) SafeParcelReader.createParcelable(parcel, header, com.google.firebase.auth.zzae.CREATOR);
                    break;
                case 2:
                    strCreateString = SafeParcelReader.createString(parcel, header);
                    break;
                case 3:
                    strCreateString2 = SafeParcelReader.createString(parcel, header);
                    break;
                case 4:
                    j = SafeParcelReader.readLong(parcel, header);
                    break;
                case 5:
                    z = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 6:
                    z2 = SafeParcelReader.readBoolean(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzdp(zzaeVar, strCreateString, strCreateString2, j, z, z2);
    }
}
