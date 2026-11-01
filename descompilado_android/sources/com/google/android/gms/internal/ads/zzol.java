package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzol implements Parcelable.Creator<zzoi> {
    zzol() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzoi[] newArray(int i) {
        return new zzoi[0];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzoi createFromParcel(Parcel parcel) {
        return new zzoi(parcel);
    }
}
