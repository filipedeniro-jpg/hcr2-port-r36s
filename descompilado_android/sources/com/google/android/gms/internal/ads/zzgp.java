package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzgp implements Parcelable.Creator<zzgq> {
    zzgp() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzgq[] newArray(int i) {
        return new zzgq[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzgq createFromParcel(Parcel parcel) {
        return new zzgq(parcel);
    }
}
