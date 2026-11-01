package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzla implements Parcelable.Creator<zzlb> {
    zzla() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzlb[] newArray(int i) {
        return new zzlb[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzlb createFromParcel(Parcel parcel) {
        return new zzlb(parcel);
    }
}
