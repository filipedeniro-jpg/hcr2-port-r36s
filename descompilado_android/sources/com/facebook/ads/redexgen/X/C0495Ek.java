package com.facebook.ads.redexgen.X;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.RestrictTo;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Ek, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class C0495Ek extends C8D {
    public static final Parcelable.Creator<C0495Ek> CREATOR = new C0494Ej();
    public Parcelable B;

    public C0495Ek(Parcel in, ClassLoader loader) {
        super(in, loader);
        this.B = in.readParcelable(loader == null ? AbstractC0481Dw.class.getClassLoader() : loader);
    }

    public C0495Ek(Parcelable superState) {
        super(superState);
    }

    public final void B(C0495Ek other) {
        this.B = other.B;
    }

    @Override // com.facebook.ads.redexgen.X.C8D, android.os.Parcelable
    public final void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(this.B, 0);
    }
}
