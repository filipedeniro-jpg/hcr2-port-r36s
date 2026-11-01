package com.google.android.gms.internal.plus;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.HashSet;

/* JADX INFO: loaded from: classes2.dex */
public final class zzy implements Parcelable.Creator<zzr.zzd> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzr.zzd createFromParcel(Parcel parcel) {
        int i;
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        HashSet hashSet = new HashSet();
        int i2 = 0;
        String strCreateString = null;
        String strCreateString2 = null;
        String strCreateString3 = null;
        String strCreateString4 = null;
        String strCreateString5 = null;
        String strCreateString6 = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 1:
                    i2 = SafeParcelReader.readInt(parcel, header);
                    i = 1;
                    break;
                case 2:
                    strCreateString = SafeParcelReader.createString(parcel, header);
                    i = 2;
                    break;
                case 3:
                    strCreateString2 = SafeParcelReader.createString(parcel, header);
                    i = 3;
                    break;
                case 4:
                    strCreateString3 = SafeParcelReader.createString(parcel, header);
                    i = 4;
                    break;
                case 5:
                    strCreateString4 = SafeParcelReader.createString(parcel, header);
                    i = 5;
                    break;
                case 6:
                    strCreateString5 = SafeParcelReader.createString(parcel, header);
                    i = 6;
                    break;
                case 7:
                    strCreateString6 = SafeParcelReader.createString(parcel, header);
                    i = 7;
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    continue;
            }
            hashSet.add(Integer.valueOf(i));
        }
        if (parcel.dataPosition() == iValidateObjectHeader) {
            return new zzr.zzd(hashSet, i2, strCreateString, strCreateString2, strCreateString3, strCreateString4, strCreateString5, strCreateString6);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(iValidateObjectHeader);
        throw new SafeParcelReader.ParseException(sb.toString(), parcel);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzr.zzd[] newArray(int i) {
        return new zzr.zzd[i];
    }
}
