package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.firebase.auth.EmailAuthCredential;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdm implements Parcelable.Creator<zzdj> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzdj[] newArray(int i) {
        return new zzdj[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzdj createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        EmailAuthCredential emailAuthCredential = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            if (SafeParcelReader.getFieldId(header) == 1) {
                emailAuthCredential = (EmailAuthCredential) SafeParcelReader.createParcelable(parcel, header, EmailAuthCredential.CREATOR);
            } else {
                SafeParcelReader.skipUnknownField(parcel, header);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzdj(emailAuthCredential);
    }
}
