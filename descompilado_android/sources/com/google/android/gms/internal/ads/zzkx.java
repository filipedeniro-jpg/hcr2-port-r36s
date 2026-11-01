package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzkx implements Parcelable.Creator<zzkv> {
    zzkx() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzkv[] newArray(int i) {
        return new zzkv[0];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzkv createFromParcel(Parcel parcel) {
        return new zzkv(parcel);
    }
}
