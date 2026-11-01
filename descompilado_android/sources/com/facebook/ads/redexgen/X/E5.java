package com.facebook.ads.redexgen.X;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class E5 implements Parcelable.Creator<E6> {
    /* JADX INFO: Access modifiers changed from: private */
    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
    public final E6 createFromParcel(Parcel in) {
        return new E6(in);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: C, reason: merged with bridge method [inline-methods] */
    public final E6[] newArray(int size) {
        return new E6[size];
    }
}
