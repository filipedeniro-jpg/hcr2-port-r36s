package com.facebook.ads.redexgen.X;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.facebook.ads.DefaultMediaViewVideoRenderer;
import com.facebook.ads.MediaView;
import com.facebook.ads.MediaViewListener;
import com.facebook.ads.MediaViewVideoRenderer;
import com.facebook.ads.NativeAd;
import com.facebook.ads.internal.api.AdViewConstructorParams;
import com.facebook.ads.internal.api.BuildConfigApi;
import com.facebook.ads.internal.api.MediaViewApi;
import com.facebook.ads.internal.api.MediaViewParentApi;
import com.facebook.ads.internal.api.NativeAdBaseApi;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class GB implements MediaViewApi {
    private static final String O = MediaView.class.getSimpleName();
    private View B;
    private C0503Es C;
    private C0480Dv D;
    private ImageView E;
    private BA F;
    private boolean G;
    private boolean H;

    @Nullable
    private MediaViewListener I;
    private MediaView J;
    private MediaViewParentApi K;

    @Nullable
    private String L;

    @Nullable
    private ImageView M;
    private MediaViewVideoRenderer N;

    public final void A(NativeAd nativeAd) {
        C1H c1h;
        this.H = true;
        ((KF) nativeAd.getInternalNativeAd()).f(this.J);
        this.E.setVisibility(8);
        this.E.setImageDrawable(null);
        this.L = ((KF) nativeAd.getInternalNativeAd()).I();
        if (M(nativeAd)) {
            boolean z = ((KF) nativeAd.getInternalNativeAd()).K() == KJ.RECT_DYNAMIC;
            this.C = this.D;
            ((C0480Dv) this.C).setCurrentPosition(0);
            ((C0480Dv) this.C).setShowTextInCarousel(z);
            if (z) {
                final C0480Dv c0480Dv = (C0480Dv) this.C;
                final List<KF> listF = ((KF) nativeAd.getInternalNativeAd()).F();
                final KI kiJ = ((KF) nativeAd.getInternalNativeAd()).J();
                c1h = new C1H(c0480Dv, listF, kiJ) { // from class: com.facebook.ads.redexgen.X.1I
                    private final KI B;

                    {
                        this.B = kiJ == null ? new KI() : kiJ;
                    }

                    /* JADX INFO: Access modifiers changed from: private */
                    @Override // com.facebook.ads.redexgen.X.C1G
                    /* JADX INFO: renamed from: D, reason: merged with bridge method [inline-methods] */
                    public final HZ N(ViewGroup parent, int viewType) {
                        return new HZ(new C0564Hc(parent.getContext(), this.B));
                    }

                    @Override // com.facebook.ads.redexgen.X.C1H, com.facebook.ads.redexgen.X.C1G
                    /* JADX INFO: renamed from: R */
                    public final void M(HZ holder, int position) {
                        super.M(holder, position);
                        C0564Hc c0564Hc = (C0564Hc) holder.h();
                        Q(c0564Hc.getImageCardView(), position);
                        c0564Hc.setTitle(((C1H) this).B.get(position).L("headline"));
                        c0564Hc.setSubtitle(((C1H) this).B.get(position).L("link_description"));
                        c0564Hc.setButtonText(((C1H) this).B.get(position).L("call_to_action"));
                        KF kf = ((C1H) this).B.get(position);
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(c0564Hc);
                        kf.V(c0564Hc, c0564Hc, arrayList);
                    }
                };
            } else {
                final C0480Dv c0480Dv2 = (C0480Dv) this.C;
                final List<KF> listF2 = ((KF) nativeAd.getInternalNativeAd()).F();
                c1h = new C1H(c0480Dv2, listF2) { // from class: com.facebook.ads.redexgen.X.1J
                    /* JADX INFO: Access modifiers changed from: private */
                    @Override // com.facebook.ads.redexgen.X.C1G
                    /* JADX INFO: renamed from: D, reason: merged with bridge method [inline-methods] */
                    public final HZ N(ViewGroup parent, int viewType) {
                        return new HZ(new C0478Dt(parent.getContext()));
                    }

                    @Override // com.facebook.ads.redexgen.X.C1H, com.facebook.ads.redexgen.X.C1G
                    /* JADX INFO: renamed from: R */
                    public final void M(HZ holder, int position) {
                        super.M(holder, position);
                        C0478Dt c0478Dt = (C0478Dt) holder.h();
                        CG cg = (CG) c0478Dt.getImageCardView();
                        cg.setImageDrawable(null);
                        Q(cg, position);
                        ((C1H) this).B.get(position).U(c0478Dt, c0478Dt);
                    }
                };
            }
            c1h.S(new G6(this, nativeAd));
            this.C.setAdapter(c1h);
            this.B = this.C;
            this.F.setVisibility(8);
            this.F.setImage(null, null);
            this.N.setVisibility(8);
            this.N.unsetNativeAd();
            ((GQ) this.N.getMediaViewVideoRendererApi()).E();
            bringChildToFront(this.C);
            this.C.setVisibility(0);
            E(this.C);
            return;
        }
        if (N(nativeAd)) {
            ((KF) nativeAd.getInternalNativeAd()).c(this.G);
            this.B = ((GQ) this.N.getMediaViewVideoRendererApi()).getVideoView();
            this.F.setVisibility(8);
            this.F.setImage(null, null);
            if (this.C != null) {
                this.C.setVisibility(8);
                this.C.setAdapter(null);
            }
            bringChildToFront(this.N);
            this.N.setNativeAd(nativeAd);
            ((GQ) this.N.getMediaViewVideoRendererApi()).D(nativeAd);
            this.N.setVisibility(0);
            if (nativeAd.getInternalNativeAd().getAdCoverImage() != null) {
                new B7(this.J).D(this.J.getHeight(), this.J.getWidth()).A(JA.JB(this.J.getContext())).C(new G7(this, nativeAd)).E(nativeAd.getInternalNativeAd().getAdCoverImage().getUrl());
            }
            if (BuildConfigApi.isDebug()) {
                Log.i(O, "videoUrl=" + ((GS) nativeAd.getNativeAdApi()).C());
            }
            E(this.N);
            return;
        }
        if (nativeAd.getAdCoverImage() == null) {
            return;
        }
        this.B = this.F.getBodyImageView();
        this.N.setVisibility(8);
        this.N.unsetNativeAd();
        ((GQ) this.N.getMediaViewVideoRendererApi()).E();
        if (this.C != null) {
            this.C.setVisibility(8);
            this.C.setAdapter(null);
        }
        bringChildToFront(this.F);
        this.F.setVisibility(0);
        new B7(this.F).D(this.J.getHeight(), this.J.getWidth()).A(JA.JB(this.J.getContext())).C(new G8(this, nativeAd)).E(nativeAd.getInternalNativeAd().getAdCoverImage().getUrl());
        E(this.F);
    }

    public final void B(NativeAdBaseApi nativeAdBaseApi, boolean shouldNotifyAssetLoaded) {
        this.H = true;
        ((KF) nativeAdBaseApi).e(this.J);
        this.F.setVisibility(8);
        this.F.setImage(null, null);
        this.N.setVisibility(8);
        this.N.unsetNativeAd();
        ((GQ) this.N.getMediaViewVideoRendererApi()).E();
        if (this.C != null) {
            this.C.setVisibility(8);
            this.C.setAdapter(null);
        }
        this.E.setVisibility(0);
        bringChildToFront(this.E);
        this.B = this.E;
        B7 b7B = new B7(this.E).B();
        if (shouldNotifyAssetLoaded) {
            b7B.C(new G5(this, nativeAdBaseApi));
        }
        KG adIcon = ((KF) nativeAdBaseApi).getAdIcon();
        if (adIcon != null) {
            b7B.E(adIcon.getUrl());
        } else {
            if (shouldNotifyAssetLoaded) {
                ((KF) nativeAdBaseApi).Q(false, true);
            }
            C0585Hx.D(this.J.getContext(), "api", C0584Hw.U, new C0583Hv("Native Ad Icon is null.", "Loaded: " + nativeAdBaseApi.isAdLoaded()));
        }
        this.L = ((KF) nativeAdBaseApi).I();
        E(this.E);
    }

    private final void D(View child, ViewGroup.LayoutParams params) {
        this.K.setImmutable(false);
        this.J.addView(child, params);
        this.K.setImmutable(true);
    }

    private void E(View adContentsView) {
        if (this.M != null) {
            this.J.removeView(this.M);
        }
        this.M = C8O.C(this.J.getContext(), this.L);
        if (this.M != null) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams.addRule(5, adContentsView.getId());
            layoutParams.addRule(7, adContentsView.getId());
            layoutParams.addRule(6, adContentsView.getId());
            layoutParams.addRule(8, adContentsView.getId());
            if (Build.VERSION.SDK_INT >= 17) {
                layoutParams.addRule(16, adContentsView.getId());
                layoutParams.addRule(17, adContentsView.getId());
            }
            D(this.M, layoutParams);
            this.K.bringChildToFront(this.M);
        }
    }

    @TargetApi(21)
    private final void F(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes, MediaView mediaView, MediaViewParentApi mediaViewParentApi) {
        this.J = mediaView;
        this.K = mediaViewParentApi;
        K(new ImageView(context, attrs, defStyleAttr, defStyleRes));
        L(new BA(context, attrs, defStyleAttr, defStyleRes));
        this.D = new C0480Dv(context, attrs, defStyleAttr);
        J();
        setVideoRenderer(new DefaultMediaViewVideoRenderer(context, attrs, defStyleAttr, defStyleRes));
        O();
    }

    private final void G(Context context, AttributeSet attrs, int defStyleAttr, MediaView mediaView, MediaViewParentApi mediaViewParentApi) {
        this.J = mediaView;
        this.K = mediaViewParentApi;
        K(new ImageView(context, attrs, defStyleAttr));
        L(new BA(context, attrs, defStyleAttr));
        this.D = new C0480Dv(context, attrs, defStyleAttr);
        J();
        setVideoRenderer(new DefaultMediaViewVideoRenderer(context, attrs, defStyleAttr));
        O();
    }

    private final void H(Context context, AttributeSet attrs, MediaView mediaView, MediaViewParentApi mediaViewParentApi) {
        this.J = mediaView;
        this.K = mediaViewParentApi;
        K(new ImageView(context, attrs));
        L(new BA(context, attrs));
        this.D = new C0480Dv(context, attrs);
        J();
        setVideoRenderer(new DefaultMediaViewVideoRenderer(context, attrs));
        O();
    }

    private final void I(Context context, MediaView mediaView, MediaViewParentApi mediaViewParentApi) {
        this.J = mediaView;
        this.K = mediaViewParentApi;
        K(new ImageView(context));
        L(new BA(context));
        this.D = new C0480Dv(context);
        J();
        setVideoRenderer(new DefaultMediaViewVideoRenderer(context));
        O();
    }

    private void J() {
        if (this.H) {
            throw new IllegalStateException("Carousel renderer must be set before nativeAd.");
        }
        if (this.C != null) {
            J6.M(this.D);
        }
        float f = J6.B;
        int iRound = Math.round(4.0f * f);
        int iRound2 = Math.round(12.0f * f);
        this.D.setChildSpacing(iRound);
        this.D.setPadding(0, iRound2, 0, iRound2);
        this.D.setVisibility(8);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13);
        this.J.addView(this.D, layoutParams);
    }

    private void K(ImageView newIconView) {
        if (this.H) {
            throw new IllegalStateException("Image renderer must be set before nativeBannerAd.");
        }
        if (this.E != null) {
            J6.M(this.E);
        }
        newIconView.setVisibility(8);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13);
        this.J.addView(newIconView, layoutParams);
        newIconView.setId(J6.D());
        this.E = newIconView;
    }

    private void L(BA newImageRenderer) {
        if (this.H) {
            throw new IllegalStateException("Image renderer must be set before nativeAd.");
        }
        if (this.F != null) {
            this.J.removeView(this.F);
        }
        newImageRenderer.setVisibility(8);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13);
        this.J.addView(newImageRenderer, layoutParams);
        this.F = newImageRenderer;
    }

    private boolean M(NativeAd nativeAd) {
        List<NativeAd> listA = ((GS) nativeAd.getNativeAdApi()).A();
        if (listA == null) {
            return false;
        }
        Iterator<NativeAd> it = listA.iterator();
        while (it.hasNext()) {
            if (it.next().getAdCoverImage() == null) {
                return false;
            }
        }
        return true;
    }

    private boolean N(NativeAd nativeAd) {
        return Build.VERSION.SDK_INT >= 14 && !TextUtils.isEmpty(((GS) nativeAd.getNativeAdApi()).C());
    }

    private void O() {
        MZ.C(this.J, MZ.INTERNAL_AD_MEDIA);
        MZ.C(this.F, MZ.INTERNAL_AD_MEDIA);
        MZ.C(this.N, MZ.INTERNAL_AD_MEDIA);
        MZ.C(this.C, MZ.INTERNAL_AD_MEDIA);
    }

    @Override // com.facebook.ads.internal.api.MediaViewApi
    public final void bringChildToFront(View child) {
        if (child == this.C || child == this.N || child == this.F || child == this.E) {
            this.K.bringChildToFront(child);
            if (this.M != null) {
                this.K.bringChildToFront(this.M);
            }
        }
    }

    @Override // com.facebook.ads.internal.api.MediaViewApi
    public final void destroy() {
        this.N.pause(false);
        ((GQ) this.N.getMediaViewVideoRendererApi()).destroy();
    }

    @Override // com.facebook.ads.internal.api.MediaViewApi
    public final View getAdContentsView() {
        return this.B;
    }

    @Override // com.facebook.ads.internal.api.MediaViewApi
    public final int getMediaHeight() {
        if (this.F.getVisibility() == 0) {
            return this.F.getImageHeight();
        }
        if (this.N.getVisibility() == 0) {
            return this.N.getMediaViewVideoRendererApi().getVideoView().getHeight();
        }
        return 0;
    }

    @Override // com.facebook.ads.internal.api.MediaViewApi
    public final int getMediaWidth() {
        if (this.F.getVisibility() == 0) {
            return this.F.getImageWidth();
        }
        if (this.N.getVisibility() == 0) {
            return this.N.getMediaViewVideoRendererApi().getVideoView().getWidth();
        }
        return 0;
    }

    @Override // com.facebook.ads.internal.api.MediaViewApi
    public final void initialize(AdViewConstructorParams adViewConstructorParams, MediaView mediaView, MediaViewParentApi mediaViewParentApi) {
        switch (adViewConstructorParams.getInitializationType()) {
            case 0:
                I(adViewConstructorParams.getContext(), mediaView, mediaViewParentApi);
                return;
            case 1:
                H(adViewConstructorParams.getContext(), adViewConstructorParams.getAttributeSet(), mediaView, mediaViewParentApi);
                return;
            case 2:
                G(adViewConstructorParams.getContext(), adViewConstructorParams.getAttributeSet(), adViewConstructorParams.getDefStyleAttr(), mediaView, mediaViewParentApi);
                return;
            case 3:
                F(adViewConstructorParams.getContext(), adViewConstructorParams.getAttributeSet(), adViewConstructorParams.getDefStyleAttr(), adViewConstructorParams.getDefStyleRes(), mediaView, mediaViewParentApi);
                return;
            default:
                throw new IllegalArgumentException("Invalid View constructor params type.");
        }
    }

    @Override // com.facebook.ads.internal.api.MediaViewApi
    public final void setListener(MediaViewListener listener) {
        this.I = listener;
        if (listener == null) {
            ((GQ) this.N.getMediaViewVideoRendererApi()).C(null);
        } else {
            ((GQ) this.N.getMediaViewVideoRendererApi()).C(new GA(this, listener));
        }
    }

    @Override // com.facebook.ads.internal.api.MediaViewApi
    public final void setVideoRenderer(MediaViewVideoRenderer newVideoRenderer) {
        if (this.H) {
            throw new IllegalStateException("Video renderer must be set before nativeAd.");
        }
        if (this.N != null) {
            this.J.removeView(this.N);
            this.N.getMediaViewVideoRendererApi().destroy();
        }
        ((GQ) newVideoRenderer.getMediaViewVideoRendererApi()).A(JX.E(this.J.getContext()));
        newVideoRenderer.setVisibility(8);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13);
        ((GB) this.J.getMediaViewApi()).D(newVideoRenderer, layoutParams);
        this.N = newVideoRenderer;
        this.G = !(this.N instanceof DefaultMediaViewVideoRenderer);
        newVideoRenderer.setId(J6.D());
    }
}
