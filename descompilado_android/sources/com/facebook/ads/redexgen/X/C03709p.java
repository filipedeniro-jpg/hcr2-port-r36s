package com.facebook.ads.redexgen.X;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.9p, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C03709p extends C8D {
    public static final Parcelable.Creator<C03709p> CREATOR = new Parcelable.ClassLoaderCreator<C03709p>() { // from class: com.facebook.ads.redexgen.X.9o
        /* JADX INFO: Access modifiers changed from: private */
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
        public final C03709p createFromParcel(Parcel in) {
            return new C03709p(in, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // android.os.Parcelable.ClassLoaderCreator
        /* JADX INFO: renamed from: C, reason: merged with bridge method [inline-methods] */
        public final C03709p createFromParcel(Parcel in, ClassLoader loader) {
            return new C03709p(in, loader);
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: D, reason: merged with bridge method [inline-methods] */
        public final C03709p[] newArray(int size) {
            return new C03709p[size];
        }
    };
    public Parcelable B;
    public ClassLoader C;
    public int D;

    public C03709p(Parcel in, ClassLoader loader) {
        super(in, loader);
        loader = loader == null ? getClass().getClassLoader() : loader;
        this.D = in.readInt();
        this.B = in.readParcelable(loader);
        this.C = loader;
    }

    public C03709p(Parcelable superState) {
        super(superState);
    }

    public final String toString() {
        return "FragmentPager.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " position=" + this.D + "}";
    }

    @Override // com.facebook.ads.redexgen.X.C8D, android.os.Parcelable
    public final void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
        out.writeInt(this.D);
        out.writeParcelable(this.B, flags);
    }
}
