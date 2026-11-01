package com.facebook.ads.redexgen.X;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.8D, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public abstract class C8D implements Parcelable {
    public static final C8D C = new C8D() { // from class: com.facebook.ads.redexgen.X.8E
    };
    public static final Parcelable.Creator<C8D> CREATOR = new Parcelable.ClassLoaderCreator<C8D>() { // from class: com.facebook.ads.redexgen.X.8F
        /* JADX INFO: Access modifiers changed from: private */
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
        public final C8D createFromParcel(Parcel in) {
            return createFromParcel(in, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // android.os.Parcelable.ClassLoaderCreator
        /* JADX INFO: renamed from: C, reason: merged with bridge method [inline-methods] */
        public final C8D createFromParcel(Parcel in, ClassLoader loader) {
            if (in.readParcelable(loader) != null) {
                throw new IllegalStateException("superState must be null");
            }
            return C8D.C;
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: D, reason: merged with bridge method [inline-methods] */
        public final C8D[] newArray(int size) {
            return new C8D[size];
        }
    };
    private final Parcelable B;

    private C8D() {
        this.B = null;
    }

    public C8D(Parcel source, ClassLoader loader) {
        Parcelable parcelable = source.readParcelable(loader);
        this.B = parcelable == null ? C : parcelable;
    }

    public C8D(Parcelable superState) {
        if (superState == null) {
            throw new IllegalArgumentException("superState must not be null");
        }
        this.B = superState == C ? null : superState;
    }

    public final Parcelable A() {
        return this.B;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.B, flags);
    }
}
