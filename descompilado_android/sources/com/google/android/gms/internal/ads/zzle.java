package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzle implements Parcelable.Creator<zzlf> {
    zzle() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzlf[] newArray(int i) {
        return new zzlf[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzlf createFromParcel(Parcel parcel) {
        return new zzlf(parcel);
    }
}
