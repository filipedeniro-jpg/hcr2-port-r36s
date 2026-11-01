package com.facebook.ads.redexgen.X;

import android.R;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import java.lang.ref.WeakReference;
import java.util.HashMap;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.9q, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C03719q extends AbstractC03789x {
    private static final int Q = (int) (J6.B * 4.0f);
    private final JV B;
    private final C5I C;
    private final BJ D;

    @Nullable
    private final C03559a E;
    private boolean F;
    private final C0618Jf G;
    private boolean H;
    private final C6A I;
    private final int J;
    private WeakReference<CJ> K;
    private final String L;
    private final String M;
    private final FrameLayout N;
    private final C0621Ji O;
    private final C9L P;

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public C03719q(AF params, C1Y colorInfo, int availableHeight, int availableWidth, final C0H adAction, boolean loadUrlOnStart) {
        super(params.B());
        this.H = true;
        this.B = params.A();
        this.I = (C6A) params.F();
        this.C = params.M();
        this.M = params.C().C();
        this.O = params.L();
        this.L = params.C().I().D();
        this.J = params.H();
        this.G = new C0618Jf(this.M, this.B);
        J6.O(this, 0);
        LinearLayout linearLayout = new LinearLayout(params.B());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        linearLayout.setOrientation(1);
        addView(linearLayout, layoutParams);
        C0510Ez c0510Ez = new C0510Ez(params.B(), params.D(), this.G, F2.CROSS);
        c0510Ez.setCloseButtonStyle(F2.DOWN_ARROW);
        c0510Ez.I(params.C().I(), params.C().C(), 0, params.C().K(), params.C().A().get(0).E(), params.C().A().get(0).C().E());
        c0510Ez.H(colorInfo, true);
        c0510Ez.K(false);
        c0510Ez.setToolbarListener(new A0(this));
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        this.N = new FrameLayout(params.B());
        linearLayout.addView(this.N, layoutParams2);
        J6.O(this.N, -1507645649);
        FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(-1, -2);
        if (params.H() == 1) {
            this.N.addView(c0510Ez, layoutParams3);
            this.N.setVisibility(4);
        }
        final FrameLayout frameLayout = new FrameLayout(params.B());
        FrameLayout.LayoutParams layoutParams4 = new FrameLayout.LayoutParams(-1, Q);
        final BR br = new BR(params.B(), null, R.attr.progressBarStyleHorizontal);
        frameLayout.addView(br, layoutParams2);
        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(-1, -1);
        this.D = new BJ(params.B(), new InterfaceC0569Hh() { // from class: com.facebook.ads.redexgen.X.9z
            @Override // com.facebook.ads.redexgen.X.InterfaceC0569Hh
            public final void kE(String url) {
                br.setProgress(100);
                this.B.H = false;
                frameLayout.setVisibility(8);
            }

            @Override // com.facebook.ads.redexgen.X.InterfaceC0569Hh
            public final void mE(String url) {
                this.B.H = true;
                frameLayout.setVisibility(0);
            }

            @Override // com.facebook.ads.redexgen.X.InterfaceC0569Hh
            public final void vE(int progress) {
                if (this.B.H) {
                    br.setProgress(progress);
                }
            }

            @Override // com.facebook.ads.redexgen.X.InterfaceC0569Hh
            public final void xE(String title) {
            }
        });
        this.D.addView(frameLayout, layoutParams4);
        linearLayout.addView(this.D, layoutParams5);
        layoutParams.gravity = 17;
        this.P = new C9L(params, colorInfo, availableHeight, availableWidth, new CP() { // from class: com.facebook.ads.redexgen.X.9y
            @Override // com.facebook.ads.redexgen.X.CP
            public final void pD() {
                this.B.L(adAction);
            }
        });
        this.E = this.P.B.getSwipeUpCtaButton();
        FrameLayout.LayoutParams layoutParams6 = new FrameLayout.LayoutParams(-1, -1);
        layoutParams6.setMargins(0, params.H() != 1 ? 0 : c0510Ez.getToolbarHeight(), 0, 0);
        addView(this.P, layoutParams6);
        if (loadUrlOnStart) {
            L(adAction);
        }
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC03789x
    public final boolean A() {
        if (this.J == 1 && this.K != null && this.K.get() != null) {
            if (this.K.get().A()) {
                return false;
            }
            if (this.D.canGoBack()) {
                this.D.goBack();
            } else if (this.K.get() != null) {
                this.K.get().B();
            }
            return true;
        }
        if (this.J != 2 || !this.D.canGoBack()) {
            return false;
        }
        this.D.goBack();
        return true;
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC03789x
    public final boolean B() {
        return this.P.getVisibility() != 0;
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC03789x
    public final void C(MotionEvent event) {
        boolean zM = M(event, this.I);
        if (!zM) {
            this.O.F(event, this, this);
        }
        if (event.getAction() == 0) {
            this.F = M(event, this.E);
            return;
        }
        if (event.getAction() != 1) {
            return;
        }
        if (zM) {
            this.I.performClick();
            return;
        }
        if (this.E != null && M(event, this.E) && this.F) {
            this.E.performClick();
            return;
        }
        HashMap map = new HashMap();
        this.C.A(map);
        map.put("touch", MJ.C(this.O.m258B()));
        this.B.oC(this.M, map);
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC03789x
    public final void D() {
        if (this.P.getVisibility() == 0) {
            if (this.E != null) {
                this.E.performClick();
            }
            HashMap map = new HashMap();
            this.C.A(map);
            map.put("touch", MJ.C(this.O.m258B()));
            this.B.zC(this.M, map);
        }
        this.N.setVisibility(0);
        ((CJ) getParent()).C();
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC03789x
    public final void E() {
        this.N.setVisibility(4);
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC03789x
    public final void F() {
        this.D.destroy();
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC03789x
    public final void G() {
        this.D.onPause();
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC03789x
    public final void H() {
        this.D.onResume();
    }

    public final void I(C01491c adInfo) {
        this.P.A(adInfo.D().I(), adInfo.D().H(), this.L, false, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void L(C0H appLinkAdAction) {
        if (this.P.getVisibility() == 0) {
            HashMap map = new HashMap();
            this.C.A(map);
            map.put("touch", MJ.C(this.O.m258B()));
            appLinkAdAction.E(map);
            this.P.setVisibility(4);
            this.D.loadUrl(appLinkAdAction.F().toString());
            appLinkAdAction.A();
        }
    }

    private boolean M(MotionEvent event, @Nullable View view) {
        if (view == null) {
            return false;
        }
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        return ((event.getX() > ((float) iArr[0]) ? 1 : (event.getX() == ((float) iArr[0]) ? 0 : -1)) >= 0 && (event.getX() > ((float) (iArr[0] + view.getWidth())) ? 1 : (event.getX() == ((float) (iArr[0] + view.getWidth())) ? 0 : -1)) <= 0) && ((event.getY() > ((float) iArr[1]) ? 1 : (event.getY() == ((float) iArr[1]) ? 0 : -1)) >= 0 && (event.getY() > ((float) (iArr[1] + view.getHeight())) ? 1 : (event.getY() == ((float) (iArr[1] + view.getHeight())) ? 0 : -1)) <= 0) && (this.P.getVisibility() == 0);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (getParent() instanceof CJ) {
            this.K = new WeakReference<>((CJ) getParent());
        }
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent event) {
        C(event);
        return super.onTouchEvent(event);
    }
}
