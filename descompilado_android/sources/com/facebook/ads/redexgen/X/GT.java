package com.facebook.ads.redexgen.X;

import android.view.View;
import android.view.ViewGroup;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class GT extends AbstractC03528x {
    public final /* synthetic */ GV B;
    private List<NativeAd> C = new ArrayList();

    public GT(GV gv) {
        this.B = gv;
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC03528x
    public final void A(ViewGroup container, int position, Object object) {
        if (position < this.C.size()) {
            if (this.B.H == null) {
                this.C.get(position).unregisterView();
            } else {
                this.B.H.destroyView(this.C.get(position), (View) object);
            }
        }
        container.removeView((View) object);
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC03528x
    public final int C() {
        return this.C.size();
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC03528x
    public final int D(Object object) {
        int iIndexOf = this.C.indexOf(object);
        if (iIndexOf >= 0) {
            return iIndexOf;
        }
        return -2;
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC03528x
    public final Object G(ViewGroup container, int position) {
        View viewRender;
        if (this.B.H != null) {
            viewRender = this.B.H.createView(this.C.get(position), position);
        } else {
            viewRender = this.B.F != null ? NativeAdView.render(this.B.C, this.C.get(position), this.B.F, this.B.B) : NativeAdView.render(this.B.C, this.C.get(position), this.B.B);
        }
        container.addView(viewRender);
        return viewRender;
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC03528x
    public final boolean H(View view, Object object) {
        return view == object;
    }

    public final void N() {
        this.C.clear();
        int iMin = Math.min(this.B.E, this.B.D.getUniqueNativeAdCount());
        for (int i = 0; i < iMin; i++) {
            NativeAd nativeAdNextNativeAd = this.B.D.nextNativeAd();
            ((KF) nativeAdNextNativeAd.getInternalNativeAd()).Y(true);
            this.C.add(nativeAdNextNativeAd);
        }
        I();
    }
}
