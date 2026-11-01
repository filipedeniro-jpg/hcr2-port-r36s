package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.widget.ImageView;
import java.util.HashMap;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Dg, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0465Dg extends AbstractC0466Dh {
    private boolean B;

    @Nullable
    private AbstractC03789x C;
    private final C0621Ji D;
    private final C5I E;
    private final C1B F;

    public C0465Dg(Context context, C01481b dataBundle, JV adEventManager, InterfaceC0525Fo listener) {
        super(context, adEventManager, listener, dataBundle);
        this.D = new C0621Ji();
        this.B = false;
        this.F = new C0477Ds(this);
        this.E = new C5I(this, 100, this.F);
        this.E.D(dataBundle.M());
    }

    private void E(int orientation, Bundle savedInstanceState) {
        C01491c c01491c = super.C.A().get(0);
        ImageView imageView = new ImageView(getContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        imageView.setAdjustViewBounds(true);
        B7 task = new B7(imageView).D(c01491c.C().A(), c01491c.C().C());
        task.C(new C0469Dk(this));
        task.E(c01491c.C().B());
        AF afA = new AG(getContext(), super.D, getAudienceNetworkListener(), super.C, imageView, this.E, this.D).G(super.E.getToolbarHeight()).D(orientation).A();
        AbstractC0390Aj abstractC0390AjB = AU.B(afA, savedInstanceState, true);
        if (this.C == null || !this.C.B()) {
            this.C = A1.B(afA, J6.C.heightPixels - abstractC0390AjB.getExactMediaHeightIfAvailable(), J6.C.widthPixels - abstractC0390AjB.getExactMediaWidthIfAvailable(), this.B);
        }
        C0467Di c0467Di = null;
        if (this.C != null) {
            c0467Di = new C0467Di(this);
        }
        A(abstractC0390AjB, this.C, c0467Di, abstractC0390AjB.getExactMediaHeightIfAvailable(), J6.C.widthPixels - abstractC0390AjB.getExactMediaWidthIfAvailable(), abstractC0390AjB.B(), orientation);
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0578Hq
    public final void bC(Intent intent, Bundle savedInstanceState, C0531Fu activityImpl) {
        super.F(activityImpl);
        activityImpl.A(new C0471Dm(this, activityImpl));
        E(activityImpl.B().getResources().getConfiguration().orientation, savedInstanceState);
        int iF = super.C.A().get(0).C().F();
        if (iF > 0) {
            I(iF);
        }
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0466Dh, com.facebook.ads.redexgen.X.InterfaceC0578Hq
    public final void nE(boolean launchAdReporting) {
        super.nE(launchAdReporting);
        if (this.C != null) {
            this.C.G();
        }
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0578Hq
    public final void oF(Bundle savedInstanceState) {
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0466Dh, android.view.View
    public final void onConfigurationChanged(Configuration config) {
        Bundle bundle = new Bundle();
        oF(bundle);
        removeAllViews();
        if (this.C != null) {
            J6.M(this.C);
            this.B = this.C.B();
        }
        E(config.orientation, bundle);
        super.onConfigurationChanged(config);
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0466Dh, com.facebook.ads.redexgen.X.InterfaceC0578Hq
    public final void onDestroy() {
        if (super.C != null && !TextUtils.isEmpty(super.C.C())) {
            HashMap map = new HashMap();
            this.E.A(map);
            map.put("touch", MJ.C(this.D.m258B()));
            super.D.iC(super.C.C(), map);
        }
        this.E.G();
        if (this.C != null) {
            this.C.F();
        }
        super.onDestroy();
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent event) {
        this.D.F(event, this, this);
        return super.onInterceptTouchEvent(event);
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC0466Dh, com.facebook.ads.redexgen.X.InterfaceC0578Hq
    public final void yE(boolean leaveAdReporting) {
        super.yE(leaveAdReporting);
        if (this.C != null) {
            this.C.H();
        }
    }
}
