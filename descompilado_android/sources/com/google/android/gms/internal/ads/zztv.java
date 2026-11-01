package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zztv implements Parcelable.Creator<zztw> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zztw[] newArray(int i) {
        return new zztw[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zztw createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String strCreateString = null;
        zztw[] zztwVarArr = null;
        int i = 0;
        int i2 = 0;
        boolean z = false;
        int i3 = 0;
        int i4 = 0;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        boolean z7 = false;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 2:
                    strCreateString = SafeParcelReader.createString(parcel, header);
                    break;
                case 3:
                    i = SafeParcelReader.readInt(parcel, header);
                    break;
                case 4:
                    i2 = SafeParcelReader.readInt(parcel, header);
                    break;
                case 5:
                    z = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 6:
                    i3 = SafeParcelReader.readInt(parcel, header);
                    break;
                case 7:
                    i4 = SafeParcelReader.readInt(parcel, header);
                    break;
                case 8:
                    zztwVarArr = (zztw[]) SafeParcelReader.createTypedArray(parcel, header, zztw.CREATOR);
                    break;
                case 9:
                    z2 = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 10:
                    z3 = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 11:
                    z4 = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 12:
                    z5 = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 13:
                    z6 = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 14:
                    z7 = SafeParcelReader.readBoolean(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zztw(strCreateString, i, i2, z, i3, i4, zztwVarArr, z2, z3, z4, z5, z6, z7);
    }
}
