package com.google.android.gms.internal.plus;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.HashSet;

/* JADX INFO: loaded from: classes2.dex */
public final class zzw implements Parcelable.Creator<zzr.zzb.C0068zzb> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzr.zzb.C0068zzb createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        HashSet hashSet = new HashSet();
        String strCreateString = null;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            int i4 = 1;
            if (fieldId != 1) {
                i4 = 2;
                if (fieldId != 2) {
                    i4 = 3;
                    if (fieldId != 3) {
                        i4 = 4;
                        if (fieldId != 4) {
                            SafeParcelReader.skipUnknownField(parcel, header);
                        } else {
                            i3 = SafeParcelReader.readInt(parcel, header);
                        }
                    } else {
                        strCreateString = SafeParcelReader.createString(parcel, header);
                    }
                } else {
                    i2 = SafeParcelReader.readInt(parcel, header);
                }
            } else {
                i = SafeParcelReader.readInt(parcel, header);
            }
            hashSet.add(Integer.valueOf(i4));
        }
        if (parcel.dataPosition() == iValidateObjectHeader) {
            return new zzr.zzb.C0068zzb(hashSet, i, i2, strCreateString, i3);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(iValidateObjectHeader);
        throw new SafeParcelReader.ParseException(sb.toString(), parcel);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzr.zzb.C0068zzb[] newArray(int i) {
        return new zzr.zzb.C0068zzb[i];
    }
}
