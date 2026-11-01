package com.google.android.gms.internal.plus;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;
import java.util.HashSet;

/* JADX INFO: loaded from: classes2.dex */
public final class zzs implements Parcelable.Creator<zzr> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzr createFromParcel(Parcel parcel) {
        int i;
        int i2;
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        HashSet hashSet = new HashSet();
        String strCreateString = null;
        zzr.zza zzaVar = null;
        String strCreateString2 = null;
        String strCreateString3 = null;
        zzr.zzb zzbVar = null;
        String strCreateString4 = null;
        String strCreateString5 = null;
        String strCreateString6 = null;
        zzr.zzc zzcVar = null;
        String strCreateString7 = null;
        zzr.zzd zzdVar = null;
        String strCreateString8 = null;
        ArrayList arrayListCreateTypedList = null;
        ArrayList arrayListCreateTypedList2 = null;
        String strCreateString9 = null;
        String strCreateString10 = null;
        ArrayList arrayListCreateTypedList3 = null;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        boolean z = false;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        boolean z2 = false;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 1:
                    i3 = SafeParcelReader.readInt(parcel, header);
                    i = 1;
                    hashSet.add(Integer.valueOf(i));
                    break;
                case 2:
                    strCreateString = SafeParcelReader.createString(parcel, header);
                    i = 2;
                    hashSet.add(Integer.valueOf(i));
                    break;
                case 3:
                    zzaVar = (zzr.zza) SafeParcelReader.createParcelable(parcel, header, zzr.zza.CREATOR);
                    i = 3;
                    hashSet.add(Integer.valueOf(i));
                    break;
                case 4:
                    strCreateString2 = SafeParcelReader.createString(parcel, header);
                    i = 4;
                    hashSet.add(Integer.valueOf(i));
                    break;
                case 5:
                    strCreateString3 = SafeParcelReader.createString(parcel, header);
                    i = 5;
                    hashSet.add(Integer.valueOf(i));
                    break;
                case 6:
                    i4 = SafeParcelReader.readInt(parcel, header);
                    i = 6;
                    hashSet.add(Integer.valueOf(i));
                    break;
                case 7:
                    zzbVar = (zzr.zzb) SafeParcelReader.createParcelable(parcel, header, zzr.zzb.CREATOR);
                    i = 7;
                    hashSet.add(Integer.valueOf(i));
                    break;
                case 8:
                    strCreateString4 = SafeParcelReader.createString(parcel, header);
                    i = 8;
                    hashSet.add(Integer.valueOf(i));
                    break;
                case 9:
                    strCreateString5 = SafeParcelReader.createString(parcel, header);
                    i = 9;
                    hashSet.add(Integer.valueOf(i));
                    break;
                case 10:
                case 11:
                case 13:
                case 17:
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
                case 12:
                    i5 = SafeParcelReader.readInt(parcel, header);
                    i = 12;
                    hashSet.add(Integer.valueOf(i));
                    break;
                case 14:
                    strCreateString6 = SafeParcelReader.createString(parcel, header);
                    i = 14;
                    hashSet.add(Integer.valueOf(i));
                    break;
                case 15:
                    zzcVar = (zzr.zzc) SafeParcelReader.createParcelable(parcel, header, zzr.zzc.CREATOR);
                    i = 15;
                    hashSet.add(Integer.valueOf(i));
                    break;
                case 16:
                    z = SafeParcelReader.readBoolean(parcel, header);
                    i = 16;
                    hashSet.add(Integer.valueOf(i));
                    break;
                case 18:
                    strCreateString7 = SafeParcelReader.createString(parcel, header);
                    i = 18;
                    hashSet.add(Integer.valueOf(i));
                    break;
                case 19:
                    zzdVar = (zzr.zzd) SafeParcelReader.createParcelable(parcel, header, zzr.zzd.CREATOR);
                    i2 = 19;
                    hashSet.add(Integer.valueOf(i2));
                    zzcVar = zzcVar;
                    break;
                case 20:
                    strCreateString8 = SafeParcelReader.createString(parcel, header);
                    i = 20;
                    hashSet.add(Integer.valueOf(i));
                    break;
                case 21:
                    i6 = SafeParcelReader.readInt(parcel, header);
                    i = 21;
                    hashSet.add(Integer.valueOf(i));
                    break;
                case 22:
                    arrayListCreateTypedList = SafeParcelReader.createTypedList(parcel, header, zzr.zze.CREATOR);
                    i2 = 22;
                    hashSet.add(Integer.valueOf(i2));
                    zzcVar = zzcVar;
                    break;
                case 23:
                    arrayListCreateTypedList2 = SafeParcelReader.createTypedList(parcel, header, zzr.zzf.CREATOR);
                    i2 = 23;
                    hashSet.add(Integer.valueOf(i2));
                    zzcVar = zzcVar;
                    break;
                case 24:
                    i7 = SafeParcelReader.readInt(parcel, header);
                    i = 24;
                    hashSet.add(Integer.valueOf(i));
                    break;
                case 25:
                    i8 = SafeParcelReader.readInt(parcel, header);
                    i = 25;
                    hashSet.add(Integer.valueOf(i));
                    break;
                case 26:
                    strCreateString9 = SafeParcelReader.createString(parcel, header);
                    i = 26;
                    hashSet.add(Integer.valueOf(i));
                    break;
                case 27:
                    strCreateString10 = SafeParcelReader.createString(parcel, header);
                    i = 27;
                    hashSet.add(Integer.valueOf(i));
                    break;
                case 28:
                    arrayListCreateTypedList3 = SafeParcelReader.createTypedList(parcel, header, zzr.zzg.CREATOR);
                    i2 = 28;
                    hashSet.add(Integer.valueOf(i2));
                    zzcVar = zzcVar;
                    break;
                case 29:
                    z2 = SafeParcelReader.readBoolean(parcel, header);
                    i = 29;
                    hashSet.add(Integer.valueOf(i));
                    break;
            }
        }
        zzr.zzc zzcVar2 = zzcVar;
        if (parcel.dataPosition() == iValidateObjectHeader) {
            return new zzr(hashSet, i3, strCreateString, zzaVar, strCreateString2, strCreateString3, i4, zzbVar, strCreateString4, strCreateString5, i5, strCreateString6, zzcVar2, z, strCreateString7, zzdVar, strCreateString8, i6, arrayListCreateTypedList, arrayListCreateTypedList2, i7, i8, strCreateString9, strCreateString10, arrayListCreateTypedList3, z2);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(iValidateObjectHeader);
        throw new SafeParcelReader.ParseException(sb.toString(), parcel);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzr[] newArray(int i) {
        return new zzr[i];
    }
}
