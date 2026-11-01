package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.drive.events.ChangeEvent;
import com.google.android.gms.drive.events.CompletionEvent;

/* JADX INFO: loaded from: classes2.dex */
public final class zzfq implements Parcelable.Creator<zzfp> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzfp[] newArray(int i) {
        return new zzfp[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzfp createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        ChangeEvent changeEvent = null;
        CompletionEvent completionEvent = null;
        com.google.android.gms.drive.events.zzo zzoVar = null;
        com.google.android.gms.drive.events.zzb zzbVar = null;
        com.google.android.gms.drive.events.zzv zzvVar = null;
        com.google.android.gms.drive.events.zzr zzrVar = null;
        int i = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 2) {
                i = SafeParcelReader.readInt(parcel, header);
            } else if (fieldId == 3) {
                changeEvent = (ChangeEvent) SafeParcelReader.createParcelable(parcel, header, ChangeEvent.CREATOR);
            } else if (fieldId == 5) {
                completionEvent = (CompletionEvent) SafeParcelReader.createParcelable(parcel, header, CompletionEvent.CREATOR);
            } else if (fieldId == 6) {
                zzoVar = (com.google.android.gms.drive.events.zzo) SafeParcelReader.createParcelable(parcel, header, com.google.android.gms.drive.events.zzo.CREATOR);
            } else if (fieldId == 7) {
                zzbVar = (com.google.android.gms.drive.events.zzb) SafeParcelReader.createParcelable(parcel, header, com.google.android.gms.drive.events.zzb.CREATOR);
            } else if (fieldId == 9) {
                zzvVar = (com.google.android.gms.drive.events.zzv) SafeParcelReader.createParcelable(parcel, header, com.google.android.gms.drive.events.zzv.CREATOR);
            } else if (fieldId == 10) {
                zzrVar = (com.google.android.gms.drive.events.zzr) SafeParcelReader.createParcelable(parcel, header, com.google.android.gms.drive.events.zzr.CREATOR);
            } else {
                SafeParcelReader.skipUnknownField(parcel, header);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzfp(i, changeEvent, completionEvent, zzoVar, zzbVar, zzvVar, zzrVar);
    }
}
