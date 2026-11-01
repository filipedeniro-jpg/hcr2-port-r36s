package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzky implements Parcelable.Creator<zzkz> {
    zzky() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzkz[] newArray(int i) {
        return new zzkz[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzkz createFromParcel(Parcel parcel) {
        return new zzkz(parcel);
    }
}
