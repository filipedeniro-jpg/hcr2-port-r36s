package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import com.facebook.ads.MediaViewVideoRenderer;
import com.facebook.ads.internal.api.DefaultMediaViewVideoRendererApi;
import com.facebook.ads.internal.api.MediaViewVideoRendererApi;
import com.facebook.ads.internal.settings.AdInternalSettings;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Jz, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0638Jz extends C0515Fe implements DefaultMediaViewVideoRendererApi {
    private static final String P = C0638Jz.class.getSimpleName();
    private MediaViewVideoRenderer B;
    private Context C;
    private boolean E;

    @Nullable
    private InterfaceC0636Jx F;
    private AnonymousClass67 G;

    @Nullable
    private C0446Cn I;
    private boolean K;
    private boolean L;
    private C5I N;
    private C1B O;
    private final InterfaceC0627Jo J = new C0628Jp(this);
    private final AtomicBoolean D = new AtomicBoolean(false);
    private final AtomicBoolean M = new AtomicBoolean(false);
    private KL H = KL.DEFAULT;

    private void N() {
        if (this.I != null) {
            ((C7C) this.I.getVideoView()).setViewImplInflationListener(this.J);
        }
    }

    private InterfaceC0629Jq O(MediaViewVideoRendererApi mediaViewVideoRendererApi) {
        return new C0630Jr(this, mediaViewVideoRendererApi);
    }

    private InterfaceC0629Jq P(MediaViewVideoRendererApi mediaViewVideoRendererApi) {
        return new C0631Js(this, mediaViewVideoRendererApi);
    }

    private C5I Q() {
        return new C5I(this.B, 50, true, this.O);
    }

    private C1B R() {
        return new C0635Jw(this);
    }

    private void S() {
        if (this.I != null) {
            this.I.getVideoView().setOnTouchListener(new ViewOnTouchListenerC0634Jv(this));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void T() {
        this.H = KL.DEFAULT;
        V();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void U(C7A videoStartReason) {
        if (this.I != null) {
            this.I.O(videoStartReason);
        } else {
            if (!AdInternalSettings.isDebugBuild()) {
                return;
            }
            Log.e(P, "MediaViewVideo is null; unable to find it.");
        }
    }

    private void V() {
        if (this.I != null) {
            ((C7C) this.I.getVideoView()).setViewImplInflationListener(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void W(KF nativeAd, @Nullable InterfaceC0636Jx placeholderListener) {
        String url;
        this.K = false;
        this.L = false;
        this.F = placeholderListener;
        N();
        AnonymousClass67 anonymousClass67 = this.G;
        if (nativeAd != null && nativeAd.getAdCoverImage() != null) {
            url = nativeAd.getAdCoverImage().getUrl();
        } else {
            url = null;
        }
        anonymousClass67.setImage(url, new C0632Jt(this));
        this.H = nativeAd.M();
        this.N.F();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean X() {
        return (this.I == null || this.I.getState() == C5K.PLAYBACK_COMPLETED || this.H != KL.ON) ? false : true;
    }

    private void Y() {
        if (this.B.getVisibility() == 0 && this.E && this.B.hasWindowFocus()) {
            this.N.F();
            return;
        }
        if (this.I != null && this.I.getState() == C5K.PAUSED) {
            this.L = true;
        }
        this.N.G();
    }

    /* JADX WARN: Code duplicated, block: B:16:0x007c  */
    /* JADX WARN: Code duplicated, block: B:18:0x0082  */
    /* JADX WARN: Code duplicated, block: B:21:0x0099  */
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
    @Override // com.facebook.ads.internal.api.DefaultMediaViewVideoRendererApi
    public final void initialize(Context context, MediaViewVideoRenderer adView, MediaViewVideoRendererApi mediaViewVideoRendererApi, int mChildConfig) {
        InterfaceC0629Jq interfaceC0629JqP;
        ((C0515Fe) mediaViewVideoRendererApi.getAdComponentViewApi()).A(this);
        switch (mChildConfig) {
            case 0:
                interfaceC0629JqP = P(mediaViewVideoRendererApi);
                break;
            case 1:
                interfaceC0629JqP = O(mediaViewVideoRendererApi);
                break;
            default:
                throw new IllegalArgumentException("Invalid renderer child config.");
        }
        ((GQ) mediaViewVideoRendererApi).B(interfaceC0629JqP);
        this.C = context;
        this.B = adView;
        this.G = new AnonymousClass67(context);
        this.O = R();
        this.N = Q();
        float f = J6.B;
        int i = (int) (2.0f * f);
        int i2 = (int) (25.0f * f);
        AnonymousClass62 anonymousClass62 = new AnonymousClass62(this.C);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(9);
        layoutParams.addRule(12);
        anonymousClass62.setPadding(i, i2, i2, i);
        anonymousClass62.setLayoutParams(layoutParams);
        for (int i3 = 0; i3 < smallPadding; i3++) {
            View childAt = this.B.getChildAt(0);
            if (childAt instanceof C0446Cn) {
                this.I = (C0446Cn) childAt;
                if (this.I == null) {
                    if (AdInternalSettings.isDebugBuild()) {
                        Log.e(P, "Unable to find MediaViewVideo child.");
                    }
                } else {
                    this.I.A(this.G);
                    this.I.A(anonymousClass62);
                }
                this.N.D(0);
                this.N.E(250);
                interfaceC0629JqP.pF();
            }
        }
        if (this.I == null) {
            if (AdInternalSettings.isDebugBuild()) {
                Log.e(P, "Unable to find MediaViewVideo child.");
            }
        } else {
            this.I.A(this.G);
            this.I.A(anonymousClass62);
        }
        this.N.D(0);
        this.N.E(250);
        interfaceC0629JqP.pF();
    }

    @Override // com.facebook.ads.redexgen.X.C0515Fe, com.facebook.ads.internal.api.AdComponentViewParentApi
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.E = true;
        Y();
    }

    @Override // com.facebook.ads.redexgen.X.C0515Fe, com.facebook.ads.internal.api.AdComponentViewParentApi
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.E = false;
        Y();
    }

    @Override // com.facebook.ads.internal.api.DefaultMediaViewVideoRendererApi
    public final void onPrepared() {
        this.B.setOnTouchListener(new ViewOnTouchListenerC0633Ju(this));
        S();
    }

    @Override // com.facebook.ads.redexgen.X.C0515Fe, com.facebook.ads.internal.api.AdComponentViewParentApi
    public final void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        Y();
    }

    @Override // com.facebook.ads.redexgen.X.C0515Fe, com.facebook.ads.internal.api.AdComponentViewParentApi
    public final void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        Y();
    }
}
