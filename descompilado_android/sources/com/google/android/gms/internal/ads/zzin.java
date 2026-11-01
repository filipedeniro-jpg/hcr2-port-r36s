package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzin implements Parcelable.Creator<zzil> {
    zzin() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzil[] newArray(int i) {
        return new zzil[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzil createFromParcel(Parcel parcel) {
        return new zzil(parcel);
    }
}
