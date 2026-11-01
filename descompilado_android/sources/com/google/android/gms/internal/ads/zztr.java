package com.google.android.gms.internal.ads;

import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zztr implements Parcelable.Creator<zztp> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zztp[] newArray(int i) {
        return new zztp[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zztp createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        Bundle bundleCreateBundle = null;
        ArrayList<String> arrayListCreateStringList = null;
        String strCreateString = null;
        zzxy zzxyVar = null;
        Location location = null;
        String strCreateString2 = null;
        Bundle bundleCreateBundle2 = null;
        Bundle bundleCreateBundle3 = null;
        ArrayList<String> arrayListCreateStringList2 = null;
        String strCreateString3 = null;
        String strCreateString4 = null;
        zztj zztjVar = null;
        String strCreateString5 = null;
        long j = 0;
        int i = 0;
        int i2 = 0;
        boolean z = false;
        int i3 = 0;
        boolean z2 = false;
        boolean z3 = false;
        int i4 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 1:
                    i = SafeParcelReader.readInt(parcel, header);
                    break;
                case 2:
                    j = SafeParcelReader.readLong(parcel, header);
                    break;
                case 3:
                    bundleCreateBundle = SafeParcelReader.createBundle(parcel, header);
                    break;
                case 4:
                    i2 = SafeParcelReader.readInt(parcel, header);
                    break;
                case 5:
                    arrayListCreateStringList = SafeParcelReader.createStringList(parcel, header);
                    break;
                case 6:
                    z = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 7:
                    i3 = SafeParcelReader.readInt(parcel, header);
                    break;
                case 8:
                    z2 = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 9:
                    strCreateString = SafeParcelReader.createString(parcel, header);
                    break;
                case 10:
                    zzxyVar = (zzxy) SafeParcelReader.createParcelable(parcel, header, zzxy.CREATOR);
                    break;
                case 11:
                    location = (Location) SafeParcelReader.createParcelable(parcel, header, Location.CREATOR);
                    break;
                case 12:
                    strCreateString2 = SafeParcelReader.createString(parcel, header);
                    break;
                case 13:
                    bundleCreateBundle2 = SafeParcelReader.createBundle(parcel, header);
                    break;
                case 14:
                    bundleCreateBundle3 = SafeParcelReader.createBundle(parcel, header);
                    break;
                case 15:
                    arrayListCreateStringList2 = SafeParcelReader.createStringList(parcel, header);
                    break;
                case 16:
                    strCreateString3 = SafeParcelReader.createString(parcel, header);
                    break;
                case 17:
                    strCreateString4 = SafeParcelReader.createString(parcel, header);
                    break;
                case 18:
                    z3 = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 19:
                    zztjVar = (zztj) SafeParcelReader.createParcelable(parcel, header, zztj.CREATOR);
                    break;
                case 20:
                    i4 = SafeParcelReader.readInt(parcel, header);
                    break;
                case 21:
                    strCreateString5 = SafeParcelReader.createString(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zztp(i, j, bundleCreateBundle, i2, arrayListCreateStringList, z, i3, z2, strCreateString, zzxyVar, location, strCreateString2, bundleCreateBundle2, bundleCreateBundle3, arrayListCreateStringList2, strCreateString3, strCreateString4, z3, zztjVar, i4, strCreateString5);
    }
}
