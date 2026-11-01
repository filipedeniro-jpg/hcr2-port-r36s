package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.drive.DriveId;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class zzfg implements Parcelable.Creator<zzff> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzff[] newArray(int i) {
        return new zzff[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzff createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        DataHolder dataHolder = null;
        ArrayList arrayListCreateTypedList = null;
        com.google.android.gms.drive.zza zzaVar = null;
        boolean z = false;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 2) {
                dataHolder = (DataHolder) SafeParcelReader.createParcelable(parcel, header, DataHolder.CREATOR);
            } else if (fieldId == 3) {
                arrayListCreateTypedList = SafeParcelReader.createTypedList(parcel, header, DriveId.CREATOR);
            } else if (fieldId == 4) {
                zzaVar = (com.google.android.gms.drive.zza) SafeParcelReader.createParcelable(parcel, header, com.google.android.gms.drive.zza.CREATOR);
            } else if (fieldId == 5) {
                z = SafeParcelReader.readBoolean(parcel, header);
            } else {
                SafeParcelReader.skipUnknownField(parcel, header);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzff(dataHolder, arrayListCreateTypedList, zzaVar, z);
    }
}
