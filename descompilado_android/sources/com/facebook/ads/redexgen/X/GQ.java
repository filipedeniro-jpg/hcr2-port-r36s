package com.facebook.ads.redexgen.X;

import android.net.Uri;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.ads.MediaViewVideoRenderer;
import com.facebook.ads.NativeAd;
import com.facebook.ads.VideoAutoplayBehavior;
import com.facebook.ads.VideoStartReason;
import com.facebook.ads.internal.api.AdComponentViewApi;
import com.facebook.ads.internal.api.AdViewConstructorParams;
import com.facebook.ads.internal.api.MediaViewVideoRendererApi;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class GQ implements MediaViewVideoRendererApi {
    private static final String Q = MediaViewVideoRenderer.class.getSimpleName();

    @Nullable
    public NativeAd B;

    @Nullable
    private InterfaceC0629Jq D;
    private boolean E;
    private MediaViewVideoRenderer F;
    private VideoAutoplayBehavior N;
    private C0446Cn O;
    private boolean P;
    private final GC K = new GD(this);
    private final GE J = new GF(this);
    private final GG I = new GH(this);
    private final GI L = new GJ(this);
    private final GK G = new GL(this);
    private final GM M = new GN(this);
    private final GO H = new GP(this);
    private C0515Fe C = new C0515Fe();

    public final void A(JV adEventManager) {
        this.O.setAdEventManager(adEventManager);
    }

    public final void B(@Nullable InterfaceC0629Jq internalMediaViewVideoRendererChild) {
        this.D = internalMediaViewVideoRendererChild;
    }

    public final void C(@Nullable G9 listener) {
        this.O.setListener(listener);
    }

    public final void D(NativeAd nativeAd) {
        this.B = nativeAd;
        GS gs = (GS) nativeAd.getNativeAdApi();
        this.O.setClientToken(((KF) nativeAd.getInternalNativeAd()).G());
        this.O.setVideoMPD(gs.m252B());
        this.O.setVideoURI(gs.C());
        C01360p c01360pE = ((KF) nativeAd.getInternalNativeAd()).E();
        if (c01360pE != null) {
            this.O.setVideoProgressReportIntervalMs(c01360pE.R());
        }
        this.O.setVideoCTA(nativeAd.getAdCallToAction());
        this.O.setNativeAd(nativeAd);
        this.N = gs.getVideoAutoplayBehavior();
        if (this.D != null) {
            this.D.qF(nativeAd);
        }
    }

    public final void E() {
        this.F.pause(false);
        this.O.setClientToken(null);
        this.O.setVideoMPD(null);
        this.O.setVideoURI((Uri) null);
        this.O.setVideoCTA(null);
        this.O.setNativeAd(null);
        this.N = VideoAutoplayBehavior.DEFAULT;
        if (this.B != null) {
            ((KF) this.B.getInternalNativeAd()).Q(false, false);
        }
        this.B = null;
        if (this.D != null) {
            this.D.yF();
        }
    }

    @Override // com.facebook.ads.internal.api.MediaViewVideoRendererApi
    public final void destroy() {
        this.O.B();
    }

    @Override // com.facebook.ads.internal.api.MediaViewVideoRendererApi
    public final void disengageSeek(VideoStartReason startReason) {
        if (!this.E) {
            Log.w(Q, "disengageSeek called without engageSeek.");
            return;
        }
        this.E = false;
        if (this.P) {
            this.O.O(C7A.B(startReason));
        }
        this.F.onSeekDisengaged();
    }

    @Override // com.facebook.ads.internal.api.MediaViewVideoRendererApi
    public final void engageSeek() {
        if (this.E) {
            Log.w(Q, "engageSeek called without disengageSeek.");
            return;
        }
        this.E = true;
        this.P = C5K.STARTED.equals(this.O.getState());
        this.O.H(false);
        this.F.onSeekEngaged();
    }

    @Override // com.facebook.ads.internal.api.AdComponentViewApiProvider
    public final AdComponentViewApi getAdComponentViewApi() {
        return this.C;
    }

    @Override // com.facebook.ads.internal.api.MediaViewVideoRendererApi
    @IntRange(from = 0)
    public final int getCurrentTimeMs() {
        return this.O.getCurrentPositionInMillis();
    }

    @Override // com.facebook.ads.internal.api.MediaViewVideoRendererApi
    @IntRange(from = 0)
    public final int getDuration() {
        return this.O.getDuration();
    }

    @Override // com.facebook.ads.internal.api.MediaViewVideoRendererApi
    public final View getVideoView() {
        return this.O.getVideoView();
    }

    @Override // com.facebook.ads.internal.api.MediaViewVideoRendererApi
    @FloatRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, to = 1.0d)
    public final float getVolume() {
        return this.O.getVolume();
    }

    @Override // com.facebook.ads.internal.api.MediaViewVideoRendererApi
    public final void initialize(AdViewConstructorParams adViewConstructorParams, MediaViewVideoRenderer mediaViewVideoRenderer) {
        this.F = mediaViewVideoRenderer;
        switch (adViewConstructorParams.getInitializationType()) {
            case 0:
                this.O = new C0446Cn(adViewConstructorParams.getContext());
                break;
            case 1:
                this.O = new C0446Cn(adViewConstructorParams.getContext(), adViewConstructorParams.getAttributeSet());
                break;
            case 2:
                this.O = new C0446Cn(adViewConstructorParams.getContext(), adViewConstructorParams.getAttributeSet(), adViewConstructorParams.getDefStyleAttr());
                break;
            case 3:
                this.O = new C0446Cn(adViewConstructorParams.getContext(), adViewConstructorParams.getAttributeSet(), adViewConstructorParams.getDefStyleRes());
                break;
            default:
                throw new IllegalArgumentException("Invalid View constructor params type.");
        }
        this.O.setEnableBackgroundVideo(mediaViewVideoRenderer.shouldAllowBackgroundPlayback());
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
        this.O.setLayoutParams(layoutParams);
        this.C.addView(this.O, -1, layoutParams);
        MZ.C(this.O, MZ.INTERNAL_AD_MEDIA);
        this.O.getEventBus().B(this.K, this.J, this.I, this.L, this.G, this.M, this.H);
    }

    @Override // com.facebook.ads.internal.api.MediaViewVideoRendererApi
    public final void pause(boolean pausedByUser) {
        this.O.H(pausedByUser);
    }

    @Override // com.facebook.ads.internal.api.MediaViewVideoRendererApi
    public final void play(VideoStartReason startReason) {
        this.O.O(C7A.B(startReason));
    }

    @Override // com.facebook.ads.internal.api.MediaViewVideoRendererApi
    public final void seekTo(@IntRange(from = 0) int timeMs) {
        if (!this.E) {
            Log.w(Q, "Seeking must be preceded by a call to engageSeek, and followed by a call to disengageSeek.");
        } else {
            this.O.L(timeMs);
        }
    }

    @Override // com.facebook.ads.internal.api.MediaViewVideoRendererApi
    public final void setVolume(@FloatRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, to = 1.0d) float volume) {
        this.O.setVolume(volume);
    }

    @Override // com.facebook.ads.internal.api.MediaViewVideoRendererApi
    public final boolean shouldAutoplay() {
        return (this.O == null || this.O.getState() == C5K.PLAYBACK_COMPLETED || this.N != VideoAutoplayBehavior.ON) ? false : true;
    }
}
