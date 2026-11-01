package com.facebook.ads.redexgen.X;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.8x, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public abstract class AbstractC03528x {
    private final DataSetObservable B = new DataSetObservable();
    private DataSetObserver C;

    public abstract int C();

    public abstract boolean H(View view, Object obj);

    public void A(ViewGroup container, int position, Object object) {
        B(container, position, object);
    }

    @Deprecated
    private final void B(View container, int position, Object object) {
        throw new UnsupportedOperationException("Required method destroyItem was not overridden");
    }

    public final void B(ViewGroup container) {
        C(container);
    }

    @Deprecated
    private final void C(View container) {
    }

    public int D(Object object) {
        return -1;
    }

    @Deprecated
    private final Object D(View container, int position) {
        throw new UnsupportedOperationException("Required method instantiateItem was not overridden");
    }

    @Deprecated
    private final void E(View container, int position, Object object) {
    }

    public final void E(ViewGroup container) {
        F(container);
    }

    public final float F(int position) {
        return 1.0f;
    }

    @Deprecated
    private final void F(View container) {
    }

    public Object G(ViewGroup container, int position) {
        return D(container, position);
    }

    public final void I() {
        synchronized (this) {
            if (this.C != null) {
                this.C.onChanged();
            }
        }
        this.B.notifyChanged();
    }

    public final void J(DataSetObserver observer) {
        synchronized (this) {
            this.C = observer;
        }
    }

    public final void K(Parcelable state, ClassLoader loader) {
    }

    public final Parcelable L() {
        return null;
    }

    public final void M(ViewGroup container, int position, Object object) {
        E(container, position, object);
    }
}
