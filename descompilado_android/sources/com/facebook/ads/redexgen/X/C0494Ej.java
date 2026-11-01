package com.facebook.ads.redexgen.X;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Ej, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C0494Ej implements Parcelable.ClassLoaderCreator<C0495Ek> {
    /* JADX INFO: Access modifiers changed from: private */
    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
    public final C0495Ek createFromParcel(Parcel in) {
        return new C0495Ek(in, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // android.os.Parcelable.ClassLoaderCreator
    /* JADX INFO: renamed from: C, reason: merged with bridge method [inline-methods] */
    public final C0495Ek createFromParcel(Parcel in, ClassLoader loader) {
        return new C0495Ek(in, loader);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: D, reason: merged with bridge method [inline-methods] */
    public final C0495Ek[] newArray(int size) {
        return new C0495Ek[size];
    }
}
