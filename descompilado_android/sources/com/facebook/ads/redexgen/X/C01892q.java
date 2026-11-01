package com.facebook.ads.redexgen.X;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.2q, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C01892q implements InterfaceC01300j<Bundle> {
    private C01912s B;
    private final List<C01922t> C;
    private final View D;

    public C01892q(View mView, List<AbstractC01260f> list) {
        this.D = mView;
        this.C = new ArrayList(list.size());
        Iterator<AbstractC01260f> it = list.iterator();
        while (it.hasNext()) {
            this.C.add(new C01922t(it.next()));
        }
        this.B = new C01912s();
    }

    public C01892q(View mView, List<AbstractC01260f> list, Bundle previousInstanceState) {
        this.D = mView;
        this.C = new ArrayList(list.size());
        ArrayList parcelableArrayList = previousInstanceState.getParcelableArrayList("TESTS");
        for (int i = 0; i < list.size(); i++) {
            this.C.add(new C01922t(list.get(i), (Bundle) parcelableArrayList.get(i)));
        }
        this.B = (C01912s) LZ.B(previousInstanceState.getByteArray("STATISTICS"));
    }

    public final C01912s A() {
        return this.B;
    }

    public final void B(double seconds, double volume) {
        if (volume >= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            this.B.D(seconds, volume);
        }
        double dA = C5I.R(this.D, 0).A();
        this.B.C(seconds, dA);
        Iterator<C01922t> it = this.C.iterator();
        while (it.hasNext()) {
            it.next().A(seconds, dA);
        }
    }

    public final void C() {
        this.B.F();
        Iterator<C01922t> it = this.C.iterator();
        while (it.hasNext()) {
            it.next().E();
        }
    }

    public final void D() {
        this.B.E();
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC01300j
    public final Bundle getSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putByteArray("STATISTICS", LZ.C(this.B));
        ArrayList<? extends Parcelable> arrayList = new ArrayList<>(this.C.size());
        Iterator<C01922t> it = this.C.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getSaveInstanceState());
        }
        bundle.putParcelableArrayList("TESTS", arrayList);
        return bundle;
    }
}
