package com.facebook.ads.redexgen.X;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Ez, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C0510Ez extends LinearLayout implements F0 {
    private final ImageView B;
    private final InterfaceC0525Fo C;
    private final ImageView D;
    private boolean E;
    private final RelativeLayout F;
    private final C0618Jf G;

    @Nullable
    private ImageView H;
    private boolean I;
    private final GK J;
    private final FO K;
    private final C03348f L;
    private final PopupMenu M;
    private PopupMenu.OnDismissListener N;
    private final C0406Az O;
    private final FrameLayout P;

    @Nullable
    private F1 Q;
    private int R;

    @Nullable
    private C0447Co S;
    public static final int a = (int) (56.0f * J6.B);
    private static final float X = Resources.getSystem().getDisplayMetrics().density;
    private static final int T = (int) (40.0f * X);
    private static final int V = (int) (44.0f * X);
    private static final int U = (int) (10.0f * X);
    private static final int Z = (int) (16.0f * X);
    private static final int W = Z - U;
    private static final int Y = (Z * 2) - U;

    public C0510Ez(Context context, InterfaceC0525Fo listener, C0618Jf funnelLoggingHandler) {
        super(context);
        this.K = new FM(this);
        this.J = new FE(this);
        this.R = 0;
        this.I = false;
        this.E = false;
        this.C = listener;
        this.G = funnelLoggingHandler;
        this.B = new ImageView(context);
        this.P = new FrameLayout(context);
        this.D = new ImageView(context);
        this.O = new C0406Az(context);
        this.L = new C03348f(context);
        this.F = new RelativeLayout(context);
        this.M = new PopupMenu(context, this.B);
    }

    public C0510Ez(Context context, InterfaceC0525Fo listener, C0618Jf funnelLoggingHandler, F2 closeButtonStyle) {
        super(context);
        this.K = new FM(this);
        this.J = new FE(this);
        this.R = 0;
        this.I = false;
        this.E = false;
        this.C = listener;
        this.G = funnelLoggingHandler;
        setGravity(16);
        if (Build.VERSION.SDK_INT >= 14) {
            this.N = new FD(this);
        }
        this.D = new ImageView(context);
        this.D.setPadding(U, U, U, U);
        this.D.setScaleType(ImageView.ScaleType.FIT_CENTER);
        this.D.setOnClickListener(new F9(this));
        setCloseButtonStyle(closeButtonStyle);
        this.O = new C0406Az(context);
        this.O.setPadding(U, U, U, U);
        this.O.setProgress(0.0f);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(W, W, Y, W);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(V, V);
        this.P = new FrameLayout(context);
        this.P.setLayoutTransition(new LayoutTransition());
        this.P.addView(this.D, layoutParams2);
        this.P.addView(this.O, layoutParams2);
        addView(this.P, layoutParams);
        this.F = new RelativeLayout(context);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(0, -2);
        layoutParams3.weight = 1.0f;
        this.L = new C03348f(context);
        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams4.gravity = 17;
        this.L.setLayoutParams(layoutParams4);
        this.F.addView(this.L);
        addView(this.F, layoutParams3);
        this.B = new ImageView(context);
        this.B.setPadding(U, U, U, U);
        this.B.setScaleType(ImageView.ScaleType.FIT_CENTER);
        this.B.setImageBitmap(C0605Ir.E(EnumC0606Is.AD_CHOICES_ICON));
        this.M = new PopupMenu(context, this.B);
        this.M.getMenu().add("Ad Choices");
        this.B.setOnClickListener(new F7(this));
        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(T, T);
        layoutParams5.setMargins(0, Z / 2, Z / 2, Z / 2);
        addView(this.B, layoutParams5);
    }

    public void A() {
        J6.M(this.L);
    }

    public void B() {
        this.E = false;
        this.P.setVisibility(8);
        this.O.setVisibility(8);
        this.D.setVisibility(8);
        ((LinearLayout.LayoutParams) this.F.getLayoutParams()).leftMargin = U;
    }

    public void C() {
        if (Build.VERSION.SDK_INT >= 14) {
            this.M.setOnDismissListener(null);
        }
        this.M.dismiss();
        if (Build.VERSION.SDK_INT >= 14) {
            this.M.setOnDismissListener(this.N);
        }
    }

    public void D(C01551i pageDetails, String clientToken, int anLogoType) {
        this.H = new ImageView(getContext());
        this.H.setPadding(U, U, U, U);
        this.H.setScaleType(ImageView.ScaleType.FIT_CENTER);
        if (1 == anLogoType) {
            this.H.setImageBitmap(C0605Ir.E(EnumC0606Is.AN_INFO_ICON));
        } else {
            this.H.setImageBitmap(C0605Ir.E(EnumC0606Is.DEFAULT_INFO_ICON));
        }
        this.H.setColorFilter(-1);
        addView(this.H, getChildCount() - 1, new LinearLayout.LayoutParams(T, T));
        this.H.setOnClickListener(Z(clientToken));
        this.B.setOnClickListener(new F6(this, pageDetails, clientToken));
    }

    public void E(boolean closeButtonEnabled) {
        this.E = closeButtonEnabled;
        this.P.setVisibility(0);
        this.O.setVisibility(closeButtonEnabled ? 8 : 0);
        this.D.setVisibility(closeButtonEnabled ? 0 : 8);
        ((LinearLayout.LayoutParams) this.F.getLayoutParams()).leftMargin = 0;
    }

    public boolean F() {
        return this.E;
    }

    public boolean G() {
        return false;
    }

    public void H(C1Y colors, boolean fullScreenEnabled) {
        int iA = colors.A(fullScreenEnabled);
        this.L.A(colors.G(fullScreenEnabled), iA);
        this.B.setColorFilter(iA);
        if (this.H != null) {
            this.H.setColorFilter(iA);
        }
        this.D.setColorFilter(iA);
        this.O.A(C5W.B(iA, 77), iA);
        if (fullScreenEnabled) {
            GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{-1778384896, 0});
            gradientDrawable.setCornerRadius(0.0f);
            J6.P(this, gradientDrawable);
            return;
        }
        J6.O(this, 0);
    }

    public void I(C01551i pageDetails, String clientToken, int unskippableSeconds, C01631q toolbarDetails, boolean isRewardedAd, int secondsForReward) {
        this.R = unskippableSeconds;
        this.L.setPageDetails(pageDetails);
        this.M.setOnMenuItemClickListener(new F4(this, pageDetails, clientToken));
        if (Build.VERSION.SDK_INT >= 14) {
            this.M.setOnDismissListener(this.N);
        }
        E(unskippableSeconds <= 0);
        this.O.setFillUp(false);
    }

    public void J(int actionMode, boolean forceAction) {
    }

    public void K(boolean shouldShow) {
        this.B.setVisibility(shouldShow ? 0 : 8);
    }

    public void L() {
        if (this.I && Build.VERSION.SDK_INT >= 14) {
            this.M.show();
        }
    }

    private View.OnClickListener Z(String clientToken) {
        return new F5(this, clientToken);
    }

    public void cC(C0447Co videoView) {
        this.S = videoView;
        this.S.getEventBus().B(this.K, this.J);
    }

    public int getToolbarHeight() {
        return a;
    }

    @Nullable
    public F1 getToolbarListener() {
        return this.Q;
    }

    public void setCloseButtonStyle(F2 closeButtonStyle) {
        EnumC0606Is enumC0606Is;
        if (this.D == null) {
            return;
        }
        switch (F3.B[closeButtonStyle.ordinal()]) {
            case 1:
                enumC0606Is = EnumC0606Is.SKIP_ARROW;
                break;
            case 2:
                enumC0606Is = EnumC0606Is.MINIMIZE_ARROW;
                break;
            default:
                enumC0606Is = EnumC0606Is.CROSS;
                break;
        }
        this.D.setImageBitmap(C0605Ir.E(enumC0606Is));
    }

    public void setPageDetailsVisibility(int visibility) {
        this.F.setVisibility(visibility);
    }

    public void setProgress(float percentage) {
        this.O.setProgressWithAnimation(percentage);
    }

    public void setShowPageDetails(boolean showPageDetails) {
        this.F.removeAllViews();
        if (showPageDetails) {
            this.F.addView(this.L);
        }
    }

    public void setToolbarActionMessage(String message) {
    }

    public void setToolbarListener(F1 toolbarListener) {
        this.Q = toolbarListener;
    }

    public void wF(C0447Co videoView) {
        if (this.S != null) {
            this.S.getEventBus().D(this.K, this.J);
            this.S = null;
        }
    }
}
