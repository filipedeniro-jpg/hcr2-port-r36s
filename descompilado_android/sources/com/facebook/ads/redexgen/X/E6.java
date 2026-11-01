package com.facebook.ads.redexgen.X;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.RestrictTo;

/* JADX INFO: loaded from: assets/audience_network.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class E6 implements Parcelable {
    public static final Parcelable.Creator<E6> CREATOR = new E5();
    public boolean B;
    public int C;
    public int D;

    public E6() {
    }

    public E6(Parcel in) {
        this.D = in.readInt();
        this.C = in.readInt();
        this.B = in.readInt() == 1;
    }

    public E6(E6 other) {
        this.D = other.D;
        this.C = other.C;
        this.B = other.B;
    }

    public final boolean A() {
        return this.D >= 0;
    }

    public final void B() {
        this.D = -1;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.D);
        dest.writeInt(this.C);
        dest.writeInt(this.B ? 1 : 0);
    }
}
