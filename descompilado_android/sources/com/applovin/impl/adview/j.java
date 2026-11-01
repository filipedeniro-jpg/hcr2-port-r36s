package com.applovin.impl.adview;

import android.R;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.applovin.adview.AppLovinAdView;
import com.applovin.impl.sdk.d.aa;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinSdkUtils;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public abstract class j extends Activity implements h {
    private static final String COUNTDOWN_IDENTIFIER_COUNTDOWN_CLOCK = "COUNTDOWN_CLOCK";
    private static final String COUNTDOWN_IDENTIFIER_PROGRESS_BAR = "PROGRESS_BAR";
    private static final String KEY_INSTANCE_IMPRESSION_TRACKED = "instance_impression_tracked";
    private static final String KEY_ORIGINAL_ORIENTATION = "original_orientation";
    public static final String KEY_WRAPPER_ID = "com.applovin.interstitial.wrapper_id";
    private static final String TAG = "InterActivity";
    public static volatile k lastKnownWrapper;
    private AppLovinAdView adView;
    private a bufferIndicator;
    private f closeButton;
    private View closeButtonExpandedClickArea;
    private e countdownClock;
    private Handler countdownHandler;
    protected g countdownManager;
    public volatile com.applovin.impl.sdk.ad.g currentAd;
    private com.applovin.impl.sdk.a.d incentivizedWarningManager;
    public com.applovin.impl.sdk.n logger;
    private ImageView muteButtonImageView;
    private ProgressBar progressBar;
    private com.applovin.impl.sdk.utils.l reportRewardTimer;
    public com.applovin.impl.sdk.h sdk;
    private f skipButton;
    private View skipButtonExpandedClickArea;
    private com.applovin.impl.sdk.c.d statsManagerHelper;
    private r videoButton;
    private s.a videoButtonListener;
    private Handler videoHandler;
    private FrameLayout videoLayout;
    public q videoView;
    private k wrapper;
    private volatile boolean orientationChangeRequested = false;
    private volatile boolean videoWasCompleted = false;
    private volatile boolean displayListenerNotified = false;
    private volatile boolean hideListenerNotified = false;
    private volatile boolean videoBeginListenerNotified = false;
    private volatile boolean videoEndListenerNotified = false;
    private volatile boolean closeButtonWasFadedIn = false;
    private volatile boolean skipButtonWasFadedIn = false;
    protected volatile boolean poststitialWasDisplayed = false;
    private boolean isSkippable = false;
    protected volatile boolean videoMuted = false;
    private volatile boolean usedVideoStream = false;
    private boolean firstLoad = true;
    private boolean countdownCompleted = false;
    protected int computedLengthSeconds = 0;
    private boolean rotationalManifestFlagsSet = false;
    private long startTimeMillis = 0;
    private long pausedTimeMillis = 0;
    private int savedVideoPerecentViewed = 0;
    private int originalOrientation = Integer.MIN_VALUE;
    private AtomicBoolean mediaErrorHandled = new AtomicBoolean(false);
    private AtomicBoolean impressionTracked = new AtomicBoolean(false);
    private WeakReference<MediaPlayer> mediaPlayerRef = new WeakReference<>(null);

    /* JADX INFO: renamed from: com.applovin.impl.adview.j$22, reason: invalid class name */
    class AnonymousClass22 implements MediaPlayer.OnPreparedListener {
        AnonymousClass22() {
        }

        @Override // android.media.MediaPlayer.OnPreparedListener
        public void onPrepared(MediaPlayer mediaPlayer) throws Throwable {
            j.this.mediaPlayerRef = new WeakReference(mediaPlayer);
            boolean videoMutedInitially = j.this.getVideoMutedInitially();
            float f = !videoMutedInitially ? 1 : 0;
            mediaPlayer.setVolume(f, f);
            if (j.this.statsManagerHelper != null) {
                j.this.statsManagerHelper.e(videoMutedInitially ? 1L : 0L);
            }
            int videoWidth = mediaPlayer.getVideoWidth();
            int videoHeight = mediaPlayer.getVideoHeight();
            j.this.computedLengthSeconds = (int) TimeUnit.MILLISECONDS.toSeconds(mediaPlayer.getDuration());
            j.this.videoView.setVideoSize(videoWidth, videoHeight);
            if (j.this.videoView instanceof AppLovinVideoView) {
                mediaPlayer.setDisplay(((AppLovinVideoView) j.this.videoView).getHolder());
            }
            mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: com.applovin.impl.adview.j.22.1
                @Override // android.media.MediaPlayer.OnErrorListener
                public boolean onError(MediaPlayer mediaPlayer2, final int i, final int i2) {
                    j.this.videoHandler.post(new Runnable() { // from class: com.applovin.impl.adview.j.22.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            j.this.logger.d(j.TAG, "Media player error (" + i + "," + i2 + ").");
                            j.this.handleMediaError();
                        }
                    });
                    return true;
                }
            });
            mediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() { // from class: com.applovin.impl.adview.j.22.2
                @Override // android.media.MediaPlayer.OnInfoListener
                public boolean onInfo(MediaPlayer mediaPlayer2, int i, int i2) {
                    if (i != 3) {
                        if (i == 701) {
                            j.this.maybeShowBufferIndicator();
                            return false;
                        }
                        if (i != 702) {
                            return false;
                        }
                    }
                    j.this.maybeHideBufferIndicator();
                    return false;
                }
            });
            if (j.this.pausedTimeMillis == 0) {
                j.this.maybeAttachCountdownClock();
                j.this.maybeAttachMuteButton();
                j.this.maybeAttachVideoButton();
                j.this.maybeAttachProgressBar();
                j.this.playVideo();
                j.this.maybeScheduleReportRewardTask();
            }
        }
    }

    private void attachCloseButtons() {
        this.closeButton = f.a(this.sdk, this, this.currentAd.q());
        this.closeButton.setVisibility(8);
        this.closeButton.setOnClickListener(new View.OnClickListener() { // from class: com.applovin.impl.adview.j.27
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                j.this.handleCloseButtonClick();
            }
        });
        int iDpToPx = dpToPx(this.currentAd.W());
        int i = (this.currentAd.Z() ? 3 : 5) | 48;
        int i2 = (this.currentAd.aa() ? 3 : 5) | 48;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(iDpToPx, iDpToPx, i | 48);
        this.closeButton.a(iDpToPx);
        int iDpToPx2 = dpToPx(this.currentAd.X());
        int iDpToPx3 = dpToPx(this.currentAd.Y());
        layoutParams.setMargins(iDpToPx3, iDpToPx2, iDpToPx3, iDpToPx2);
        this.videoLayout.addView(this.closeButton, layoutParams);
        this.skipButton = f.a(this.sdk, this, this.currentAd.r());
        this.skipButton.setVisibility(8);
        this.skipButton.setOnClickListener(new View.OnClickListener() { // from class: com.applovin.impl.adview.j.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                j.this.handleSkipButtonClick();
            }
        });
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(iDpToPx, iDpToPx, i2);
        layoutParams2.setMargins(iDpToPx3, iDpToPx2, iDpToPx3, iDpToPx2);
        this.skipButton.a(iDpToPx);
        this.videoLayout.addView(this.skipButton, layoutParams2);
        this.skipButton.bringToFront();
        if (isExtendedCloseAreaEnabled()) {
            int iDpToPx4 = dpToPx(((Integer) this.sdk.a(com.applovin.impl.sdk.b.b.cm)).intValue());
            this.closeButtonExpandedClickArea = new View(this);
            this.closeButtonExpandedClickArea.setBackgroundColor(0);
            this.closeButtonExpandedClickArea.setVisibility(8);
            this.skipButtonExpandedClickArea = new View(this);
            this.skipButtonExpandedClickArea.setBackgroundColor(0);
            this.skipButtonExpandedClickArea.setVisibility(8);
            int i3 = iDpToPx + iDpToPx4;
            int iDpToPx5 = iDpToPx2 - dpToPx(5);
            FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(i3, i3, i);
            layoutParams3.setMargins(iDpToPx5, iDpToPx5, iDpToPx5, iDpToPx5);
            FrameLayout.LayoutParams layoutParams4 = new FrameLayout.LayoutParams(i3, i3, i2);
            layoutParams4.setMargins(iDpToPx5, iDpToPx5, iDpToPx5, iDpToPx5);
            this.closeButtonExpandedClickArea.setOnClickListener(new View.OnClickListener() { // from class: com.applovin.impl.adview.j.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    j.this.closeButton.performClick();
                }
            });
            this.skipButtonExpandedClickArea.setOnClickListener(new View.OnClickListener() { // from class: com.applovin.impl.adview.j.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    j.this.skipButton.performClick();
                }
            });
            this.videoLayout.addView(this.closeButtonExpandedClickArea, layoutParams3);
            this.closeButtonExpandedClickArea.bringToFront();
            this.videoLayout.addView(this.skipButtonExpandedClickArea, layoutParams4);
            this.skipButtonExpandedClickArea.bringToFront();
        }
    }

    private void clearLastAdData() {
        com.applovin.impl.sdk.h hVar = this.sdk;
        if (hVar != null) {
            hVar.a((com.applovin.impl.sdk.b.d<boolean>) com.applovin.impl.sdk.b.d.q, false);
            this.sdk.a((com.applovin.impl.sdk.b.d<int>) com.applovin.impl.sdk.b.d.p, 0);
        }
    }

    private void createDependencies(com.applovin.impl.sdk.ad.g gVar) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        this.videoLayout = new FrameLayout(this);
        this.videoLayout.setLayoutParams(layoutParams);
        this.videoLayout.setBackgroundColor(gVar.E());
        this.videoHandler = new Handler();
        this.countdownHandler = new Handler();
        this.countdownManager = new g(this.countdownHandler, this.sdk);
    }

    private r createVideoButton() {
        this.logger.a(TAG, "Create video button with HTML = " + this.currentAd.u());
        s sVar = new s(this.sdk);
        this.videoButtonListener = new s.a() { // from class: com.applovin.impl.adview.j.14
            @Override // com.applovin.impl.adview.s.a
            public void a(r rVar) {
                j.this.logger.a(j.TAG, "Clicking through from video button...");
                j.this.clickThroughFromVideo();
            }

            @Override // com.applovin.impl.adview.s.a
            public void b(r rVar) {
                j.this.logger.a(j.TAG, "Closing ad from video button...");
                j.this.dismiss();
            }

            @Override // com.applovin.impl.adview.s.a
            public void c(r rVar) {
                j.this.logger.a(j.TAG, "Skipping video from video button...");
                j.this.skipVideo();
            }
        };
        sVar.a(new WeakReference<>(this.videoButtonListener));
        r rVar = new r(sVar, getApplicationContext());
        rVar.a(this.currentAd.u());
        return rVar;
    }

    private int dpToPx(int i) {
        return AppLovinSdkUtils.dpToPx(this, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fadeInCloseButton() {
        runOnUiThread(new Runnable() { // from class: com.applovin.impl.adview.j.6
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (j.this.closeButtonWasFadedIn) {
                        j.this.closeButton.setVisibility(0);
                        return;
                    }
                    j.this.closeButtonWasFadedIn = true;
                    if (j.this.isExtendedCloseAreaEnabled() && j.this.closeButtonExpandedClickArea != null) {
                        j.this.closeButtonExpandedClickArea.setVisibility(0);
                        j.this.closeButtonExpandedClickArea.bringToFront();
                    }
                    j.this.closeButton.setVisibility(0);
                    j.this.closeButton.bringToFront();
                    AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
                    alphaAnimation.setDuration(((Integer) j.this.sdk.a(com.applovin.impl.sdk.b.b.cN)).intValue());
                    alphaAnimation.setRepeatCount(0);
                    j.this.closeButton.startAnimation(alphaAnimation);
                } catch (Throwable unused) {
                    j.this.dismiss();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fadeInSkipButton() {
        runOnUiThread(new Runnable() { // from class: com.applovin.impl.adview.j.7
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (j.this.skipButtonWasFadedIn || j.this.skipButton == null) {
                        return;
                    }
                    j.this.skipButtonWasFadedIn = true;
                    j.this.skipButton.setVisibility(0);
                    AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
                    alphaAnimation.setDuration(((Integer) j.this.sdk.a(com.applovin.impl.sdk.b.b.cN)).intValue());
                    alphaAnimation.setRepeatCount(0);
                    j.this.skipButton.startAnimation(alphaAnimation);
                    if (!j.this.isExtendedCloseAreaEnabled() || j.this.skipButtonExpandedClickArea == null) {
                        return;
                    }
                    j.this.skipButtonExpandedClickArea.setVisibility(0);
                    j.this.skipButtonExpandedClickArea.bringToFront();
                } catch (Throwable th) {
                    j.this.logger.c(j.TAG, "Unable to show skip button: " + th);
                }
            }
        });
    }

    private int getCanonicalCountdownLength() {
        int iC = this.currentAd.C();
        return (iC <= 0 && ((Boolean) this.sdk.a(com.applovin.impl.sdk.b.b.du)).booleanValue()) ? this.computedLengthSeconds + 1 : iC;
    }

    private long getCanonicalCountdownLengthMillis() {
        return TimeUnit.SECONDS.toMillis(getCanonicalCountdownLength());
    }

    private int getCurrentScreenOrientation(int i, boolean z) {
        if (z) {
            if (i == 0) {
                return 0;
            }
            if (i == 1) {
                return 9;
            }
            if (i == 2) {
                return 8;
            }
            return i == 3 ? 1 : -1;
        }
        if (i == 0) {
            return 1;
        }
        if (i == 1) {
            return 0;
        }
        if (i == 2) {
            return 9;
        }
        return i == 3 ? 8 : -1;
    }

    private int getIncentivizedNonVideoAdPercentEngaged() {
        if (!(this.currentAd instanceof com.applovin.impl.sdk.ad.a)) {
            return 0;
        }
        float fI = ((com.applovin.impl.sdk.ad.a) this.currentAd).i();
        if (fI <= 0.0f) {
            fI = this.currentAd.p();
        }
        double dA = com.applovin.impl.sdk.utils.n.a(System.currentTimeMillis() - this.startTimeMillis);
        double d = fI;
        Double.isNaN(d);
        return (int) Math.min((dA / d) * 100.0d, 100.0d);
    }

    private static int getScreenOrientation(Display display) {
        if (display.getWidth() == display.getHeight()) {
            return 3;
        }
        return display.getWidth() < display.getHeight() ? 1 : 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean getVideoMutedInitially() {
        if (((Integer) this.sdk.b(com.applovin.impl.sdk.b.d.p, 0)).intValue() > 0) {
            return this.videoMuted;
        }
        return ((Boolean) this.sdk.a(com.applovin.impl.sdk.b.b.dj)).booleanValue() ? this.sdk.m().isMuted() : ((Boolean) this.sdk.a(com.applovin.impl.sdk.b.b.dh)).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleCloseButtonClick() {
        c adWebView;
        if (this.currentAd.U() && (adWebView = ((AdViewControllerImpl) this.adView.getAdViewController()).getAdWebView()) != null) {
            adWebView.a("javascript:al_onCloseButtonTapped();");
        }
        if (!shouldWarnIncentivizedNonVideoAdClose()) {
            dismiss();
        } else {
            this.logger.a(TAG, "Prompting incentivized non-video ad close warning");
            this.incentivizedWarningManager.c();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleSkipButtonClick() {
        if (!shouldWarnIncentivizedAdClose()) {
            skipVideo();
            return;
        }
        pauseVideo();
        pauseReportRewardTask();
        this.logger.a(TAG, "Prompting incentivized ad close warning");
        this.incentivizedWarningManager.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleVideoClick() {
        if (!this.currentAd.w() || this.currentAd.h() == null) {
            maybeFadeCountdownClock();
            maybeFadeVideoButton();
        } else {
            this.sdk.w().a(TAG, "Clicking through video...");
            clickThroughFromVideo();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleVideoCompletion() {
        this.videoWasCompleted = true;
        showPoststitial();
    }

    private boolean hasSoftButtons() {
        int identifier = getResources().getIdentifier((String) this.sdk.a(com.applovin.impl.sdk.b.b.dd), "bool", "android");
        return identifier > 0 && getResources().getBoolean(identifier);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isExtendedCloseAreaEnabled() {
        return ((Integer) this.sdk.a(com.applovin.impl.sdk.b.b.cm)).intValue() > 0;
    }

    private boolean isIncentivizedAd() {
        return AppLovinAdType.INCENTIVIZED.equals(this.currentAd.getType());
    }

    private boolean isIncentivizedNonVideoAd() {
        return !this.currentAd.hasVideoUrl() && isIncentivizedAd();
    }

    private boolean isIncentivizedNonVideoAdFullyEngaged() {
        return getIncentivizedNonVideoAdPercentEngaged() >= this.currentAd.R();
    }

    private boolean isVideoPlayerMuted() {
        return this.videoMuted;
    }

    private void maybeAttachBufferIndicator() {
        if (this.usedVideoStream && this.currentAd.L()) {
            this.bufferIndicator = new a(this, ((Integer) this.sdk.a(com.applovin.impl.sdk.b.b.dt)).intValue(), this.currentAd.N());
            this.bufferIndicator.setColor(this.currentAd.O());
            this.bufferIndicator.setBackgroundColor(this.currentAd.P());
            this.bufferIndicator.setVisibility(8);
            this.videoLayout.addView(this.bufferIndicator, new FrameLayout.LayoutParams(-1, -1, 17));
            this.videoLayout.bringChildToFront(this.bufferIndicator);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeAttachCountdownClock() {
        boolean z = ((Boolean) this.sdk.a(com.applovin.impl.sdk.b.b.cU)).booleanValue() && getCanonicalCountdownLength() > 0;
        if (this.countdownClock == null && z) {
            this.countdownClock = new e(this);
            int iD = this.currentAd.D();
            this.countdownClock.setTextColor(iD);
            this.countdownClock.setTextSize(((Integer) this.sdk.a(com.applovin.impl.sdk.b.b.cT)).intValue());
            this.countdownClock.setFinishedStrokeColor(iD);
            this.countdownClock.setFinishedStrokeWidth(((Integer) this.sdk.a(com.applovin.impl.sdk.b.b.cS)).intValue());
            this.countdownClock.setMax(getCanonicalCountdownLength());
            this.countdownClock.setProgress(getCanonicalCountdownLength());
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(dpToPx(((Integer) this.sdk.a(com.applovin.impl.sdk.b.b.cR)).intValue()), dpToPx(((Integer) this.sdk.a(com.applovin.impl.sdk.b.b.cR)).intValue()), ((Integer) this.sdk.a(com.applovin.impl.sdk.b.b.cQ)).intValue());
            int iDpToPx = dpToPx(((Integer) this.sdk.a(com.applovin.impl.sdk.b.b.cP)).intValue());
            layoutParams.setMargins(iDpToPx, iDpToPx, iDpToPx, iDpToPx);
            this.videoLayout.addView(this.countdownClock, layoutParams);
            this.countdownClock.bringToFront();
            this.countdownClock.setVisibility(0);
            final long canonicalCountdownLengthMillis = getCanonicalCountdownLengthMillis();
            this.countdownManager.a(COUNTDOWN_IDENTIFIER_COUNTDOWN_CLOCK, 1000L, new g.a() { // from class: com.applovin.impl.adview.j.9
                @Override // com.applovin.impl.adview.g.a
                public void a() {
                    if (j.this.countdownClock != null) {
                        long seconds = TimeUnit.MILLISECONDS.toSeconds(canonicalCountdownLengthMillis - ((long) j.this.videoView.getCurrentPosition()));
                        if (seconds <= 0) {
                            j.this.countdownClock.setVisibility(8);
                            j.this.countdownCompleted = true;
                        } else if (j.this.shouldContinueCountdownClockCountdown()) {
                            j.this.countdownClock.setProgress((int) seconds);
                        }
                    }
                }

                @Override // com.applovin.impl.adview.g.a
                public boolean b() {
                    return j.this.shouldContinueCountdownClockCountdown();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeAttachMuteButton() throws Throwable {
        if (this.muteButtonImageView == null) {
            try {
                this.videoMuted = getVideoMutedInitially();
                this.muteButtonImageView = new ImageView(this);
                if (muteButtonShouldBeHidden()) {
                    this.sdk.w().a(TAG, "Mute button should be hidden");
                    return;
                }
                int iDpToPx = dpToPx(((Integer) this.sdk.a(com.applovin.impl.sdk.b.b.dk)).intValue());
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(iDpToPx, iDpToPx, ((Integer) this.sdk.a(com.applovin.impl.sdk.b.b.dm)).intValue());
                this.muteButtonImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                int iDpToPx2 = dpToPx(((Integer) this.sdk.a(com.applovin.impl.sdk.b.b.dl)).intValue());
                layoutParams.setMargins(iDpToPx2, iDpToPx2, iDpToPx2, iDpToPx2);
                if ((this.videoMuted ? this.currentAd.ay() : this.currentAd.az()) == null) {
                    this.sdk.w().d(TAG, "Attempting to add mute button but could not find uri");
                    return;
                }
                this.sdk.w().a(TAG, "Added mute button with params: " + layoutParams);
                populateMuteImage(this.videoMuted);
                this.muteButtonImageView.setClickable(true);
                this.muteButtonImageView.setOnClickListener(new View.OnClickListener() { // from class: com.applovin.impl.adview.j.5
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        j.this.toggleMute();
                    }
                });
                this.videoLayout.addView(this.muteButtonImageView, layoutParams);
                this.muteButtonImageView.bringToFront();
            } catch (Exception e) {
                this.sdk.w().a(TAG, "Failed to attach mute button", e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"NewApi"})
    public void maybeAttachProgressBar() {
        if (this.progressBar == null && this.currentAd.J()) {
            this.logger.b(TAG, "Attaching video progress bar...");
            this.progressBar = new ProgressBar(this, null, R.attr.progressBarStyleHorizontal);
            this.progressBar.setMax(((Integer) this.sdk.a(com.applovin.impl.sdk.b.b.dp)).intValue());
            this.progressBar.setPadding(0, 0, 0, 0);
            if (com.applovin.impl.sdk.utils.e.f()) {
                try {
                    this.progressBar.setProgressTintList(ColorStateList.valueOf(this.currentAd.K()));
                } catch (Throwable th) {
                    this.logger.b(TAG, "Unable to update progress bar color.", th);
                }
            }
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(this.videoView.getWidth(), 20, 80);
            layoutParams.setMargins(0, 0, 0, ((Integer) this.sdk.a(com.applovin.impl.sdk.b.b.dq)).intValue());
            this.videoLayout.addView(this.progressBar, layoutParams);
            this.progressBar.bringToFront();
            this.countdownManager.a(COUNTDOWN_IDENTIFIER_PROGRESS_BAR, ((Long) this.sdk.a(com.applovin.impl.sdk.b.b.f0do)).longValue(), new g.a() { // from class: com.applovin.impl.adview.j.10
                @Override // com.applovin.impl.adview.g.a
                public void a() {
                    if (j.this.progressBar != null) {
                        if (!j.this.shouldContinueFullLengthVideoCountdown()) {
                            j.this.progressBar.setVisibility(8);
                            return;
                        }
                        j.this.progressBar.setProgress((int) ((j.this.videoView.getCurrentPosition() / j.this.videoView.getDuration()) * ((Integer) j.this.sdk.a(com.applovin.impl.sdk.b.b.dp)).intValue()));
                    }
                }

                @Override // com.applovin.impl.adview.g.a
                public boolean b() {
                    return j.this.shouldContinueFullLengthVideoCountdown();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeAttachVideoButton() {
        final p pVarV = this.currentAd.v();
        if (com.applovin.impl.sdk.utils.k.b(this.currentAd.u()) && pVarV != null && this.videoButton == null) {
            this.logger.b(TAG, "Attaching video button...");
            this.videoButton = createVideoButton();
            double dA = pVarV.a();
            Double.isNaN(dA);
            double dB = pVarV.b();
            Double.isNaN(dB);
            int width = this.videoView.getWidth();
            int height = this.videoView.getHeight();
            double d = width;
            Double.isNaN(d);
            double d2 = height;
            Double.isNaN(d2);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams((int) ((dA / 100.0d) * d), (int) ((dB / 100.0d) * d2), pVarV.d());
            int iDpToPx = dpToPx(pVarV.c());
            layoutParams.setMargins(iDpToPx, iDpToPx, iDpToPx, iDpToPx);
            this.videoLayout.addView(this.videoButton, layoutParams);
            this.videoButton.bringToFront();
            if (pVarV.i() > 0.0f) {
                this.videoButton.setVisibility(4);
                this.videoHandler.postDelayed(new Runnable() { // from class: com.applovin.impl.adview.j.11
                    @Override // java.lang.Runnable
                    public void run() {
                        long jG = pVarV.g();
                        j jVar = j.this;
                        jVar.toggleFade(jVar.videoButton, true, jG);
                    }
                }, com.applovin.impl.sdk.utils.n.b(pVarV.i()));
            }
            if (pVarV.j() > 0.0f) {
                this.videoHandler.postDelayed(new Runnable() { // from class: com.applovin.impl.adview.j.13
                    @Override // java.lang.Runnable
                    public void run() {
                        long jH = pVarV.h();
                        j jVar = j.this;
                        jVar.toggleFade(jVar.videoButton, false, jH);
                    }
                }, com.applovin.impl.sdk.utils.n.b(pVarV.j()));
            }
        }
    }

    private void maybeFadeCountdownClock() {
        e eVar;
        if (!((Boolean) this.sdk.a(com.applovin.impl.sdk.b.b.de)).booleanValue() || (eVar = this.countdownClock) == null || eVar.getVisibility() == 8) {
            return;
        }
        toggleFade(this.countdownClock, this.countdownClock.getVisibility() == 4, 750L);
    }

    private void maybeFadeVideoButton() {
        r rVar;
        p pVarV = this.currentAd.v();
        if (pVarV == null || !pVarV.e() || this.poststitialWasDisplayed || (rVar = this.videoButton) == null) {
            return;
        }
        toggleFade(this.videoButton, rVar.getVisibility() == 4, pVarV.f());
    }

    @SuppressLint({"WrongConstant"})
    private void maybeFinish() {
        int i;
        com.applovin.impl.sdk.h hVar = this.sdk;
        if (hVar != null && ((Boolean) hVar.a(com.applovin.impl.sdk.b.b.fi)).booleanValue() && isFinishing()) {
            return;
        }
        if (this.currentAd != null && this.currentAd.ai() && (i = this.originalOrientation) != Integer.MIN_VALUE) {
            setRequestedOrientation(i);
        }
        finish();
    }

    private void maybeForwardAppLifecycleEventToWebView(String str) {
        com.applovin.impl.sdk.ad.g gVar = this.currentAd;
        if (gVar == null || !gVar.V()) {
            return;
        }
        maybeForwardToWebView(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeForwardToWebView(String str) {
        c adWebView = ((AdViewControllerImpl) this.adView.getAdViewController()).getAdWebView();
        if (adWebView == null || !com.applovin.impl.sdk.utils.k.b(str)) {
            return;
        }
        adWebView.a(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeHideBufferIndicator() {
        a aVar = this.bufferIndicator;
        if (aVar != null) {
            aVar.b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0066, code lost:
    
        if (r0 > 0) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0098, code lost:
    
        if (r0 > 0) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x009a, code lost:
    
        r0 = java.util.concurrent.TimeUnit.SECONDS.toMillis(r0);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void maybeScheduleReportRewardTask() {
        /*
            Method dump skipped, instruction units count: 231
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.adview.j.maybeScheduleReportRewardTask():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeSetPoststitialMuted(boolean z) {
        c adWebView;
        if (!this.currentAd.T() || (adWebView = ((AdViewControllerImpl) this.adView.getAdViewController()).getAdWebView()) == null) {
            return;
        }
        try {
            adWebView.a(z ? "javascript:al_mute();" : "javascript:al_unmute();");
        } catch (Throwable th) {
            this.logger.b(TAG, "Unable to forward mute setting to template.", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeShowBufferIndicator() {
        a aVar = this.bufferIndicator;
        if (aVar != null) {
            aVar.a();
        }
    }

    private boolean muteButtonShouldBeHidden() {
        if (!((Boolean) this.sdk.a(com.applovin.impl.sdk.b.b.df)).booleanValue()) {
            return true;
        }
        if (!((Boolean) this.sdk.a(com.applovin.impl.sdk.b.b.dg)).booleanValue() || getVideoMutedInitially()) {
            return false;
        }
        return !((Boolean) this.sdk.a(com.applovin.impl.sdk.b.b.di)).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyAdDisplayed(AppLovinAd appLovinAd) {
        com.applovin.impl.sdk.utils.h.a(this.wrapper.d(), appLovinAd, this.sdk);
        this.displayListenerNotified = true;
        this.sdk.U().c();
        AppLovinSdkUtils.runOnUiThreadDelayed(new Runnable() { // from class: com.applovin.impl.adview.j.20
            @Override // java.lang.Runnable
            public void run() {
                j jVar = j.this;
                jVar.maybeSetPoststitialMuted(jVar.videoMuted);
            }
        }, ((Long) this.sdk.a(com.applovin.impl.sdk.b.b.dv)).longValue());
    }

    private void notifyAdHidden(AppLovinAd appLovinAd) {
        if (this.hideListenerNotified) {
            return;
        }
        this.hideListenerNotified = true;
        k kVar = this.wrapper;
        if (kVar != null) {
            com.applovin.impl.sdk.utils.h.b(kVar.d(), appLovinAd, this.sdk);
        }
        this.sdk.U().d();
    }

    private void notifyEndListenerIfRequired() {
        if (this.videoEndListenerNotified) {
            return;
        }
        try {
            if (this.currentAd.hasVideoUrl()) {
                int videoPercentViewed = getVideoPercentViewed();
                this.sdk.p().trackVideoEnd(this.currentAd, videoPercentViewed, this.usedVideoStream);
                notifyVideoPlaybackEnded(this.currentAd, videoPercentViewed, isFullyWatched());
                if (this.statsManagerHelper != null) {
                    this.statsManagerHelper.c(videoPercentViewed);
                }
            } else if ((this.currentAd instanceof com.applovin.impl.sdk.ad.a) && isIncentivizedNonVideoAd() && ((Boolean) this.sdk.a(com.applovin.impl.sdk.b.b.cf)).booleanValue()) {
                int incentivizedNonVideoAdPercentEngaged = getIncentivizedNonVideoAdPercentEngaged();
                this.logger.a(TAG, "Rewarded playable engaged at " + incentivizedNonVideoAdPercentEngaged + " percent");
                notifyVideoPlaybackEnded(this.currentAd, incentivizedNonVideoAdPercentEngaged, incentivizedNonVideoAdPercentEngaged >= this.currentAd.R());
            }
        } catch (Throwable th) {
            com.applovin.impl.sdk.n nVar = this.logger;
            if (nVar != null) {
                nVar.b(TAG, "Failed to notify end listener.", th);
            }
        }
    }

    private void notifyVideoPlaybackBegan(AppLovinAd appLovinAd) {
        if (this.videoBeginListenerNotified) {
            return;
        }
        this.videoBeginListenerNotified = true;
        com.applovin.impl.sdk.utils.h.a(this.wrapper.c(), appLovinAd, this.sdk);
    }

    private void notifyVideoPlaybackEnded(AppLovinAd appLovinAd, double d, boolean z) {
        this.videoEndListenerNotified = true;
        com.applovin.impl.sdk.utils.h.a(this.wrapper.c(), appLovinAd, d, z, this.sdk);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDialogHidden(AppLovinAd appLovinAd) {
        dismiss();
        notifyAdHidden(appLovinAd);
    }

    private void pauseVideo() {
        q qVar = this.videoView;
        this.sdk.a(com.applovin.impl.sdk.b.d.p, Integer.valueOf(qVar != null ? qVar.getCurrentPosition() : 0));
        this.sdk.a((com.applovin.impl.sdk.b.d<boolean>) com.applovin.impl.sdk.b.d.q, true);
        try {
            this.countdownManager.c();
        } catch (Throwable th) {
            this.logger.b(TAG, "Unable to pause countdown timers", th);
        }
        this.videoView.pause();
    }

    private void populateMuteImage(boolean z) throws Throwable {
        Uri uriAy = z ? this.currentAd.ay() : this.currentAd.az();
        int iDpToPx = dpToPx(((Integer) this.sdk.a(com.applovin.impl.sdk.b.b.dk)).intValue());
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
        AppLovinSdkUtils.safePopulateImageView(this.muteButtonImageView, uriAy, iDpToPx);
        StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
    }

    /* JADX WARN: Code duplicated, block: B:29:0x0055  */
    /* JADX WARN: Code restructure failed: missing block: B:10:0x0026, code lost:
    
        if (r7 == 1) goto L11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void populateRequestedOrientation(int r7, boolean r8) {
        /*
            r6 = this;
            com.applovin.impl.sdk.h r0 = r6.sdk
            com.applovin.impl.sdk.b.b<java.lang.Boolean> r1 = com.applovin.impl.sdk.b.b.db
            java.lang.Object r0 = r0.a(r1)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            com.applovin.impl.adview.k r1 = r6.wrapper
            com.applovin.impl.sdk.ad.g$b r1 = r1.f()
            com.applovin.impl.sdk.ad.g$b r2 = com.applovin.impl.sdk.ad.g.b.ACTIVITY_PORTRAIT
            r3 = 3
            r4 = 2
            r5 = 1
            if (r1 != r2) goto L3c
            r1 = 9
            if (r8 == 0) goto L2c
            if (r7 == r5) goto L24
            if (r7 == r3) goto L24
            goto L30
        L24:
            if (r0 == 0) goto L66
            if (r7 != r5) goto L32
        L28:
            r6.tryToSetRequestedOrientation(r1)
            goto L66
        L2c:
            if (r7 == 0) goto L36
            if (r7 == r4) goto L36
        L30:
            r6.orientationChangeRequested = r5
        L32:
            r6.tryToSetRequestedOrientation(r5)
            goto L66
        L36:
            if (r0 == 0) goto L66
            if (r7 != 0) goto L28
            r1 = 1
            goto L28
        L3c:
            com.applovin.impl.adview.k r1 = r6.wrapper
            com.applovin.impl.sdk.ad.g$b r1 = r1.f()
            com.applovin.impl.sdk.ad.g$b r2 = com.applovin.impl.sdk.ad.g.b.ACTIVITY_LANDSCAPE
            if (r1 != r2) goto L66
            r1 = 8
            r2 = 0
            if (r8 == 0) goto L57
            if (r7 == 0) goto L50
            if (r7 == r4) goto L50
            goto L5b
        L50:
            if (r0 == 0) goto L66
            if (r7 != r4) goto L55
            goto L28
        L55:
            r1 = 0
            goto L28
        L57:
            if (r7 == r5) goto L61
            if (r7 == r3) goto L61
        L5b:
            r6.orientationChangeRequested = r5
            r6.tryToSetRequestedOrientation(r2)
            goto L66
        L61:
            if (r0 == 0) goto L66
            if (r7 != r5) goto L28
            goto L55
        L66:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.adview.j.populateRequestedOrientation(int, boolean):void");
    }

    private void prepareVideo(final Uri uri) {
        this.videoView = this.currentAd.ax() ? new m(this.sdk, this, new Runnable() { // from class: com.applovin.impl.adview.j.21
            @Override // java.lang.Runnable
            public void run() {
                j.this.handleMediaError();
            }
        }) : new AppLovinVideoView(this, this.sdk);
        if (uri != null) {
            this.videoView.setOnPreparedListener(new AnonymousClass22());
            this.videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.applovin.impl.adview.j.23
                @Override // android.media.MediaPlayer.OnCompletionListener
                public void onCompletion(MediaPlayer mediaPlayer) {
                    j.this.handleVideoCompletion();
                }
            });
            this.videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: com.applovin.impl.adview.j.24
                @Override // android.media.MediaPlayer.OnErrorListener
                public boolean onError(MediaPlayer mediaPlayer, final int i, final int i2) {
                    j.this.videoHandler.post(new Runnable() { // from class: com.applovin.impl.adview.j.24.1
                        @Override // java.lang.Runnable
                        public void run() {
                            j.this.logger.d(j.TAG, "Video view error (" + i + "," + i2 + ").");
                            j.this.handleMediaError();
                        }
                    });
                    return true;
                }
            });
            if (((Boolean) this.sdk.a(com.applovin.impl.sdk.b.b.fm)).booleanValue()) {
                this.sdk.F().a(new aa(this.sdk, new Runnable() { // from class: com.applovin.impl.adview.j.25
                    @Override // java.lang.Runnable
                    public void run() {
                        j.this.videoView.setVideoURI(uri);
                    }
                }), com.applovin.impl.sdk.d.q.a.MAIN);
            } else {
                StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
                this.videoView.setVideoURI(uri);
                StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
            }
        }
        this.videoView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1, 17));
        this.videoView.setOnTouchListener(new AppLovinTouchToClickListener(this, new View.OnClickListener() { // from class: com.applovin.impl.adview.j.26
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                j.this.handleVideoClick();
            }
        }));
        this.videoLayout.addView((View) this.videoView);
        setContentView(this.videoLayout);
        scheduleCloseButtonFromAd();
        maybeAttachBufferIndicator();
    }

    private void renderAd() {
        View view;
        com.applovin.impl.sdk.n nVar;
        StringBuilder sb;
        String str;
        if (!this.orientationChangeRequested || this.rotationalManifestFlagsSet) {
            AppLovinAdView appLovinAdView = this.adView;
            if (appLovinAdView == null) {
                exitWithError("AdView was null");
                return;
            }
            appLovinAdView.setAdDisplayListener(new AppLovinAdDisplayListener() { // from class: com.applovin.impl.adview.j.16
                @Override // com.applovin.sdk.AppLovinAdDisplayListener
                public void adDisplayed(AppLovinAd appLovinAd) {
                    if (j.this.displayListenerNotified) {
                        return;
                    }
                    j.this.notifyAdDisplayed(appLovinAd);
                }

                @Override // com.applovin.sdk.AppLovinAdDisplayListener
                public void adHidden(AppLovinAd appLovinAd) {
                    j.this.onDialogHidden(appLovinAd);
                }
            });
            this.adView.setAdClickListener(new AppLovinAdClickListener() { // from class: com.applovin.impl.adview.j.17
                @Override // com.applovin.sdk.AppLovinAdClickListener
                public void adClicked(AppLovinAd appLovinAd) {
                    com.applovin.impl.sdk.utils.h.a(j.this.wrapper.e(), appLovinAd, j.this.sdk);
                }
            });
            this.currentAd = (com.applovin.impl.sdk.ad.g) this.wrapper.b();
            if (this.impressionTracked.compareAndSet(false, true)) {
                this.sdk.p().trackImpression(this.currentAd);
                this.currentAd.setHasShown(true);
            }
            createDependencies(this.currentAd);
            attachCloseButtons();
            if (this.currentAd.isVideoAd()) {
                this.usedVideoStream = this.currentAd.b();
                if (this.usedVideoStream) {
                    nVar = this.logger;
                    sb = new StringBuilder();
                    str = "Preparing stream for ";
                } else {
                    nVar = this.logger;
                    sb = new StringBuilder();
                    str = "Preparing cached video playback for ";
                }
                sb.append(str);
                sb.append(this.currentAd.e());
                nVar.a(TAG, sb.toString());
                com.applovin.impl.sdk.c.d dVar = this.statsManagerHelper;
                if (dVar != null) {
                    dVar.b(this.usedVideoStream ? 1L : 0L);
                }
            }
            this.videoMuted = getVideoMutedInitially();
            Uri uriE = this.currentAd.e();
            prepareVideo(uriE);
            if (uriE == null) {
                maybeScheduleReportRewardTask();
            }
            this.closeButton.bringToFront();
            if (isExtendedCloseAreaEnabled() && (view = this.closeButtonExpandedClickArea) != null) {
                view.bringToFront();
            }
            f fVar = this.skipButton;
            if (fVar != null) {
                fVar.bringToFront();
            }
            if (!this.currentAd.ao() && !this.currentAd.ap()) {
                this.adView.renderAd(this.currentAd);
            }
            this.wrapper.a(true);
            if (this.currentAd.hasVideoUrl()) {
                return;
            }
            if (isIncentivizedNonVideoAd() && ((Boolean) this.sdk.a(com.applovin.impl.sdk.b.b.cf)).booleanValue()) {
                notifyVideoPlaybackBegan(this.currentAd);
            }
            showPoststitial();
        }
    }

    private void resumeVideoFromLastPositionWithDelay() {
        long jMax = Math.max(0L, ((Long) this.sdk.a(com.applovin.impl.sdk.b.b.dr)).longValue());
        if (jMax <= 0) {
            this.sdk.w().a(TAG, "Resuming video immediately");
            resumeVideoFromLastPostition();
            return;
        }
        this.sdk.w().a(TAG, "Resuming video with delay of " + jMax);
        this.videoHandler.postDelayed(new Runnable() { // from class: com.applovin.impl.adview.j.19
            @Override // java.lang.Runnable
            public void run() {
                j.this.resumeVideoFromLastPostition();
            }
        }, jMax);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resumeVideoFromLastPostition() {
        q qVar;
        if (this.poststitialWasDisplayed || (qVar = this.videoView) == null || qVar.isPlaying()) {
            return;
        }
        this.videoView.seekTo(((Integer) this.sdk.b(com.applovin.impl.sdk.b.d.p, Integer.valueOf(this.videoView.getDuration()))).intValue());
        this.videoView.start();
        this.countdownManager.a();
    }

    private void scheduleCloseButton(long j, final f fVar) {
        this.videoHandler.postDelayed(new Runnable() { // from class: com.applovin.impl.adview.j.8
            @Override // java.lang.Runnable
            public void run() {
                if (fVar.equals(j.this.closeButton)) {
                    j.this.fadeInCloseButton();
                } else if (fVar.equals(j.this.skipButton)) {
                    j.this.fadeInSkipButton();
                }
            }
        }, j);
    }

    private void scheduleCloseButtonFromAd() {
        f fVar;
        if (this.currentAd.o() >= 0.0f) {
            if (!this.isSkippable || (fVar = this.skipButton) == null) {
                fVar = this.closeButton;
            }
            scheduleCloseButton(com.applovin.impl.sdk.utils.n.b(this.currentAd.o()), fVar);
        }
    }

    private void setVideoPlayerMuted(boolean z) {
        this.videoMuted = z;
        MediaPlayer mediaPlayer = this.mediaPlayerRef.get();
        if (mediaPlayer != null) {
            float f = z ? 0 : 1;
            try {
                mediaPlayer.setVolume(f, f);
            } catch (IllegalStateException e) {
                this.logger.b(TAG, "Failed to set MediaPlayer muted: " + z, e);
            }
        }
    }

    private boolean shouldAndroidHandleBackButton() {
        com.applovin.impl.sdk.h hVar;
        if (this.wrapper == null || (hVar = this.sdk) == null || ((Boolean) hVar.a(com.applovin.impl.sdk.b.b.cV)).booleanValue()) {
            return true;
        }
        if (((Boolean) this.sdk.a(com.applovin.impl.sdk.b.b.cW)).booleanValue() && this.closeButtonWasFadedIn) {
            return true;
        }
        return ((Boolean) this.sdk.a(com.applovin.impl.sdk.b.b.cX)).booleanValue() && this.poststitialWasDisplayed;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean shouldContinueCountdownClockCountdown() {
        return (this.countdownCompleted || this.poststitialWasDisplayed || !this.videoView.isPlaying()) ? false : true;
    }

    private boolean shouldWarnIncentivizedAdClose() {
        return isIncentivizedAd() && !isFullyWatched() && ((Boolean) this.sdk.a(com.applovin.impl.sdk.b.b.bT)).booleanValue() && this.incentivizedWarningManager != null;
    }

    private boolean shouldWarnIncentivizedNonVideoAdClose() {
        return isIncentivizedNonVideoAd() && !isIncentivizedNonVideoAdFullyEngaged() && ((Boolean) this.sdk.a(com.applovin.impl.sdk.b.b.bY)).booleanValue() && this.incentivizedWarningManager != null;
    }

    private void stopPlayback() {
        if (this.videoView != null) {
            this.savedVideoPerecentViewed = getVideoPercentViewed();
            this.videoView.stopPlayback();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toggleFade(final View view, final boolean z, long j) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(z ? 0.0f : 1.0f, z ? 1.0f : 0.0f);
        alphaAnimation.setDuration(j);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.applovin.impl.adview.j.12
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                if (z) {
                    return;
                }
                view.setVisibility(4);
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                view.setVisibility(0);
            }
        });
        view.startAnimation(alphaAnimation);
    }

    private void tryToSetRequestedOrientation(int i) {
        try {
            setRequestedOrientation(i);
        } catch (Throwable th) {
            this.sdk.w().b(TAG, "Failed to set requested orientation", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @TargetApi(16)
    public void updateWindowFlags() {
        getWindow().getDecorView().setSystemUiVisibility(5894);
    }

    public void clickThroughFromVideo() {
        try {
            if (this.currentAd.ah() && this.isSkippable) {
                fadeInSkipButton();
            }
            this.sdk.p().trackAndLaunchVideoClick(this.currentAd, this.adView, this.currentAd.h());
            com.applovin.impl.sdk.utils.h.a(this.wrapper.e(), this.currentAd, this.sdk);
            if (this.statsManagerHelper != null) {
                this.statsManagerHelper.b();
            }
        } catch (Throwable th) {
            this.sdk.w().b(TAG, "Encountered error while clicking through video.", th);
        }
    }

    public void continueVideo() {
        resumeVideoFromLastPostition();
    }

    @Override // com.applovin.impl.adview.h, android.content.DialogInterface
    public void dismiss() {
        long jCurrentTimeMillis = System.currentTimeMillis() - this.startTimeMillis;
        this.logger.b(TAG, "Dismissing ad after " + jCurrentTimeMillis + " milliseconds elapsed");
        clearLastAdData();
        notifyEndListenerIfRequired();
        if (this.wrapper != null) {
            if (this.currentAd != null) {
                notifyAdHidden(this.currentAd);
                com.applovin.impl.sdk.c.d dVar = this.statsManagerHelper;
                if (dVar != null) {
                    dVar.c();
                    this.statsManagerHelper = null;
                }
            }
            this.wrapper.a(false);
            this.wrapper.g();
        }
        lastKnownWrapper = null;
        maybeFinish();
    }

    public void exitWithError(String str) {
        try {
            Log.e(TAG, "Failed to properly render an Interstitial Activity, due to error: " + str, new Throwable("Initialized = " + k.b + "; CleanedUp = " + k.c));
            notifyAdHidden(new com.applovin.impl.sdk.ad.i());
        } catch (Exception e) {
            Log.e(TAG, "Failed to show a video ad due to error:", e);
        }
        maybeFinish();
    }

    public boolean getPoststitialWasDisplayed() {
        return this.poststitialWasDisplayed;
    }

    public int getVideoPercentViewed() {
        if (this.videoWasCompleted) {
            return 100;
        }
        q qVar = this.videoView;
        if (qVar == null) {
            this.logger.d(TAG, "No video view detected on video end");
            return 0;
        }
        int duration = qVar.getDuration();
        if (duration <= 0) {
            return this.savedVideoPerecentViewed;
        }
        double currentPosition = this.videoView.getCurrentPosition();
        double d = duration;
        Double.isNaN(currentPosition);
        Double.isNaN(d);
        return (int) ((currentPosition / d) * 100.0d);
    }

    public void handleMediaError() {
        if (!this.mediaErrorHandled.compareAndSet(false, true)) {
            this.logger.d(TAG, "Already handled media player error. Doing nothing...");
        } else {
            this.logger.d(TAG, "Handling media player error - Finishing activity...");
            maybeFinish();
        }
    }

    protected boolean isFullyWatched() {
        return getVideoPercentViewed() >= this.currentAd.R();
    }

    protected boolean isVastAd() {
        return this.currentAd instanceof com.applovin.impl.a.a;
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        f fVar;
        if (shouldAndroidHandleBackButton()) {
            this.logger.a(TAG, "Back button was pressed; forwarding to Android for handling...");
        } else {
            try {
                if (!this.isSkippable || this.skipButton == null || this.skipButton.getVisibility() != 0 || this.skipButton.getAlpha() <= 0.0f || this.closeButtonWasFadedIn) {
                    if (this.closeButton == null || this.closeButton.getVisibility() != 0 || this.closeButton.getAlpha() <= 0.0f) {
                        this.logger.a(TAG, "Back button was pressed, but was not eligible for dismissal.");
                    } else {
                        this.logger.a(TAG, "Back button was pressed; forwarding as a click to close button.");
                        fVar = this.closeButton;
                    }
                    maybeForwardAppLifecycleEventToWebView("javascript:al_onBackPressed();");
                    return;
                }
                this.logger.a(TAG, "Back button was pressed; forwarding as a click to skip button.");
                fVar = this.skipButton;
                fVar.performClick();
                maybeForwardAppLifecycleEventToWebView("javascript:al_onBackPressed();");
                return;
            } catch (Exception unused) {
            }
        }
        super.onBackPressed();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (configuration.orientation == 0 || !(this.videoView instanceof m) || this.mediaPlayerRef.get() == null) {
            return;
        }
        MediaPlayer mediaPlayer = this.mediaPlayerRef.get();
        this.videoView.setVideoSize(mediaPlayer.getVideoWidth(), mediaPlayer.getVideoHeight());
    }

    /* JADX WARN: Code duplicated, block: B:71:0x01a2  */
    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        com.applovin.impl.sdk.c.d dVar;
        String str;
        super.onCreate(bundle);
        if (bundle != null) {
            this.impressionTracked.set(bundle.getBoolean(KEY_INSTANCE_IMPRESSION_TRACKED));
        }
        boolean z = true;
        requestWindowFeature(1);
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            try {
                String stringExtra = getIntent().getStringExtra(KEY_WRAPPER_ID);
                if (stringExtra == null || stringExtra.isEmpty()) {
                    str = "Wrapper ID is null";
                } else {
                    this.wrapper = k.a(stringExtra);
                    if (this.wrapper == null && lastKnownWrapper != null) {
                        this.wrapper = lastKnownWrapper;
                    }
                    if (this.wrapper != null) {
                        AppLovinAd appLovinAdB = this.wrapper.b();
                        this.sdk = this.wrapper.a();
                        this.logger = this.wrapper.a().w();
                        this.statsManagerHelper = new com.applovin.impl.sdk.c.d(appLovinAdB, this.sdk);
                        if (appLovinAdB != null) {
                            com.applovin.impl.sdk.ad.g gVar = (com.applovin.impl.sdk.ad.g) appLovinAdB;
                            View viewFindViewById = findViewById(R.id.content);
                            if (viewFindViewById != null) {
                                viewFindViewById.setBackgroundColor(gVar.hasVideoUrl() ? gVar.E() : gVar.F());
                            }
                            this.startTimeMillis = System.currentTimeMillis();
                            if (gVar.x()) {
                                getWindow().setFlags(16777216, 16777216);
                            }
                            if (gVar.y()) {
                                getWindow().addFlags(128);
                            }
                            Display defaultDisplay = ((WindowManager) getSystemService("window")).getDefaultDisplay();
                            int screenOrientation = getScreenOrientation(defaultDisplay);
                            int rotation = defaultDisplay.getRotation();
                            if ((screenOrientation != 2 || rotation != 0) && ((screenOrientation != 2 || rotation != 2) && ((screenOrientation != 1 || rotation != 1) && (screenOrientation != 1 || rotation != 3)))) {
                                z = false;
                            }
                            int currentScreenOrientation = getCurrentScreenOrientation(rotation, z);
                            if (bundle == null) {
                                this.originalOrientation = currentScreenOrientation;
                            } else {
                                this.originalOrientation = bundle.getInt(KEY_ORIGINAL_ORIENTATION, currentScreenOrientation);
                            }
                            if (gVar.B()) {
                                if (currentScreenOrientation != -1) {
                                    this.logger.a(TAG, "Locking activity orientation to current orientation: " + currentScreenOrientation);
                                    tryToSetRequestedOrientation(currentScreenOrientation);
                                } else {
                                    this.logger.d(TAG, "Unable to detect current orientation. Locking to targeted orientation...");
                                }
                                this.adView = new AppLovinAdView(this.sdk.N(), AppLovinAdSize.INTERSTITIAL, this);
                                this.adView.setAutoDestroy(false);
                                ((AdViewControllerImpl) this.adView.getAdViewController()).setStatsManagerHelper(this.statsManagerHelper);
                                this.wrapper.a(this);
                                this.isSkippable = ((Boolean) this.sdk.a(com.applovin.impl.sdk.b.b.ds)).booleanValue();
                                this.rotationalManifestFlagsSet = com.applovin.impl.sdk.utils.e.b(getApplicationContext());
                                this.incentivizedWarningManager = new com.applovin.impl.sdk.a.d(this, this.sdk);
                            } else {
                                this.logger.a(TAG, "Locking activity orientation to targeted orientation...");
                            }
                            populateRequestedOrientation(rotation, z);
                            this.adView = new AppLovinAdView(this.sdk.N(), AppLovinAdSize.INTERSTITIAL, this);
                            this.adView.setAutoDestroy(false);
                            ((AdViewControllerImpl) this.adView.getAdViewController()).setStatsManagerHelper(this.statsManagerHelper);
                            this.wrapper.a(this);
                            this.isSkippable = ((Boolean) this.sdk.a(com.applovin.impl.sdk.b.b.ds)).booleanValue();
                            this.rotationalManifestFlagsSet = com.applovin.impl.sdk.utils.e.b(getApplicationContext());
                            this.incentivizedWarningManager = new com.applovin.impl.sdk.a.d(this, this.sdk);
                        } else {
                            str = "No current ad found.";
                        }
                        StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
                        clearLastAdData();
                        dVar = this.statsManagerHelper;
                        if (dVar != null) {
                            dVar.a();
                        }
                        renderAd();
                    }
                    str = "Wrapper is null; initialized state: " + Boolean.toString(k.b);
                }
                exitWithError(str);
            } catch (Throwable th) {
                if (this.logger != null) {
                    this.logger.b(TAG, "Encountered error during onCreate.", th);
                }
                exitWithError("An error was encountered during interstitial ad creation.");
            }
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
            clearLastAdData();
            dVar = this.statsManagerHelper;
            if (dVar != null) {
                dVar.a();
            }
            renderAd();
        } catch (Throwable th2) {
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
            throw th2;
        }
    }

    /* JADX WARN: Code duplicated, block: B:38:0x0080  */
    @Override // android.app.Activity
    protected void onDestroy() {
        MediaPlayer mediaPlayer;
        try {
            try {
                if (this.adView != null) {
                    ViewParent parent = this.adView.getParent();
                    if (parent instanceof ViewGroup) {
                        ((ViewGroup) parent).removeView(this.adView);
                    }
                    this.adView.destroy();
                    this.adView = null;
                }
                if (this.videoView != null) {
                    this.videoView.pause();
                    this.videoView.stopPlayback();
                }
                if (this.sdk != null && ((Boolean) this.sdk.a(com.applovin.impl.sdk.b.b.fg)).booleanValue() && (mediaPlayer = this.mediaPlayerRef.get()) != null) {
                    mediaPlayer.release();
                }
                if (this.countdownManager != null) {
                    this.countdownManager.b();
                }
                if (this.videoHandler != null) {
                    this.videoHandler.removeCallbacksAndMessages(null);
                }
                if (this.countdownHandler != null) {
                    this.countdownHandler.removeCallbacksAndMessages(null);
                }
                if (this.currentAd != null) {
                    notifyEndListenerIfRequired();
                    notifyAdHidden(this.currentAd);
                }
            } catch (Throwable th) {
                if (this.logger != null) {
                    this.logger.a(TAG, "Unable to destroy video view", th);
                }
                if (this.currentAd != null) {
                    notifyEndListenerIfRequired();
                    notifyAdHidden(this.currentAd);
                }
            }
            super.onDestroy();
        } catch (Throwable th2) {
            if (this.currentAd != null) {
                notifyEndListenerIfRequired();
                notifyAdHidden(this.currentAd);
            }
            throw th2;
        }
    }

    @Override // android.app.Activity
    protected void onPause() {
        this.logger.a(TAG, "App paused...");
        this.pausedTimeMillis = System.currentTimeMillis();
        if (this.rotationalManifestFlagsSet || !this.orientationChangeRequested) {
            pauseVideo();
        }
        this.wrapper.a(false);
        this.incentivizedWarningManager.a();
        pauseReportRewardTask();
        maybeForwardAppLifecycleEventToWebView("javascript:al_onAppPaused();");
        super.onPause();
    }

    /* JADX WARN: Code duplicated, block: B:43:0x00ae A[PHI: r0
  0x00ae: PHI (r0v29 com.applovin.impl.adview.f) = (r0v25 com.applovin.impl.adview.f), (r0v42 com.applovin.impl.adview.f) binds: [B:42:0x00ac, B:24:0x0073] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // android.app.Activity
    protected void onResume() {
        f fVar;
        super.onResume();
        this.logger.a(TAG, "App resumed...");
        this.wrapper.a(true);
        if (!this.firstLoad) {
            com.applovin.impl.sdk.c.d dVar = this.statsManagerHelper;
            if (dVar != null) {
                dVar.d(System.currentTimeMillis() - this.pausedTimeMillis);
            }
            if (!((Boolean) this.sdk.b(com.applovin.impl.sdk.b.d.q, false)).booleanValue() || this.incentivizedWarningManager.d() || this.poststitialWasDisplayed) {
                boolean z = (this.currentAd instanceof com.applovin.impl.sdk.ad.a) && ((com.applovin.impl.sdk.ad.a) this.currentAd).j();
                if (this.currentAd != null && ((Boolean) this.sdk.a(com.applovin.impl.sdk.b.b.cO)).booleanValue() && !this.currentAd.z() && this.poststitialWasDisplayed && (fVar = this.closeButton) != null && !z) {
                    scheduleCloseButton(0L, fVar);
                }
            } else {
                resumeVideoFromLastPositionWithDelay();
                maybeShowBufferIndicator();
                if (this.currentAd != null && ((Boolean) this.sdk.a(com.applovin.impl.sdk.b.b.cO)).booleanValue() && !this.currentAd.A() && !this.poststitialWasDisplayed && this.isSkippable && (fVar = this.skipButton) != null) {
                    scheduleCloseButton(0L, fVar);
                }
            }
            resumeReportRewardTask();
        } else if (!this.incentivizedWarningManager.d() && !this.poststitialWasDisplayed && this.currentAd != null && this.currentAd.M()) {
            maybeShowBufferIndicator();
        }
        maybeForwardAppLifecycleEventToWebView("javascript:al_onAppResumed();");
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putBoolean(KEY_INSTANCE_IMPRESSION_TRACKED, this.impressionTracked.get());
        bundle.putInt(KEY_ORIGINAL_ORIENTATION, this.originalOrientation);
        super.onSaveInstanceState(bundle);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            this.logger.a(TAG, "Window gained focus");
            try {
                if (com.applovin.impl.sdk.utils.e.e() && ((Boolean) this.sdk.a(com.applovin.impl.sdk.b.b.dn)).booleanValue() && hasSoftButtons()) {
                    updateWindowFlags();
                    if (((Long) this.sdk.a(com.applovin.impl.sdk.b.b.cY)).longValue() > 0) {
                        this.videoHandler.postDelayed(new Runnable() { // from class: com.applovin.impl.adview.j.1
                            @Override // java.lang.Runnable
                            public void run() {
                                j.this.updateWindowFlags();
                            }
                        }, ((Long) this.sdk.a(com.applovin.impl.sdk.b.b.cY)).longValue());
                    }
                } else {
                    getWindow().setFlags(1024, 1024);
                }
                if (((Boolean) this.sdk.a(com.applovin.impl.sdk.b.b.cZ)).booleanValue() && !this.poststitialWasDisplayed) {
                    resumeVideoFromLastPositionWithDelay();
                    resumeReportRewardTask();
                }
            } catch (Throwable th) {
                this.logger.b(TAG, "Setting window flags failed.", th);
            }
        } else {
            this.logger.a(TAG, "Window lost focus");
            if (((Boolean) this.sdk.a(com.applovin.impl.sdk.b.b.cZ)).booleanValue() && !this.poststitialWasDisplayed) {
                pauseVideo();
                pauseReportRewardTask();
            }
        }
        this.firstLoad = false;
        maybeForwardAppLifecycleEventToWebView("javascript:al_onWindowFocusChanged( " + z + " );");
    }

    public void pauseReportRewardTask() {
        com.applovin.impl.sdk.utils.l lVar = this.reportRewardTimer;
        if (lVar != null) {
            lVar.b();
        }
    }

    protected void playVideo() {
        notifyVideoPlaybackBegan(this.currentAd);
        this.videoView.start();
        this.countdownManager.a();
    }

    public void resumeReportRewardTask() {
        com.applovin.impl.sdk.utils.l lVar = this.reportRewardTimer;
        if (lVar != null) {
            lVar.c();
        }
    }

    protected boolean shouldContinueFullLengthVideoCountdown() {
        return (this.videoWasCompleted || this.poststitialWasDisplayed) ? false : true;
    }

    public void showPoststitial() {
        long jB;
        f fVar;
        try {
            if (this.statsManagerHelper != null) {
                this.statsManagerHelper.g();
            }
            if (!this.currentAd.ab()) {
                stopPlayback();
            }
            if (this.adView != null) {
                ViewParent parent = this.adView.getParent();
                if (parent instanceof ViewGroup) {
                    ((ViewGroup) parent).removeView(this.adView);
                }
                FrameLayout frameLayout = new FrameLayout(this);
                frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                frameLayout.setBackgroundColor(this.currentAd.F());
                frameLayout.addView(this.adView);
                if (this.currentAd.ao()) {
                    this.adView.renderAd(this.currentAd);
                }
                if (this.currentAd.ab()) {
                    stopPlayback();
                }
                if (this.videoLayout != null) {
                    this.videoLayout.removeAllViewsInLayout();
                }
                if (isExtendedCloseAreaEnabled() && this.closeButtonExpandedClickArea != null) {
                    if (this.closeButtonExpandedClickArea.getParent() instanceof ViewGroup) {
                        ((ViewGroup) this.closeButtonExpandedClickArea.getParent()).removeView(this.closeButtonExpandedClickArea);
                    }
                    frameLayout.addView(this.closeButtonExpandedClickArea);
                    this.closeButtonExpandedClickArea.bringToFront();
                }
                if (this.closeButton != null) {
                    ViewParent parent2 = this.closeButton.getParent();
                    if (parent2 instanceof ViewGroup) {
                        ((ViewGroup) parent2).removeView(this.closeButton);
                    }
                    frameLayout.addView(this.closeButton);
                    this.closeButton.bringToFront();
                }
                setContentView(frameLayout);
                if (this.currentAd.ap()) {
                    this.adView.renderAd(this.currentAd);
                }
                if (((Boolean) this.sdk.a(com.applovin.impl.sdk.b.b.fc)).booleanValue()) {
                    this.adView.setVisibility(4);
                    this.adView.setVisibility(0);
                }
                int iS = this.currentAd.S();
                if (iS >= 0) {
                    this.videoHandler.postDelayed(new Runnable() { // from class: com.applovin.impl.adview.j.18
                        @Override // java.lang.Runnable
                        public void run() {
                            j.this.maybeForwardToWebView("javascript:al_onPoststitialShow();");
                        }
                    }, iS);
                }
            }
            if ((this.currentAd instanceof com.applovin.impl.sdk.ad.a) && ((com.applovin.impl.sdk.ad.a) this.currentAd).j()) {
                this.logger.a(TAG, "Skip showing of close button");
            } else {
                if (this.currentAd.p() >= 0.0f) {
                    jB = com.applovin.impl.sdk.utils.n.b(this.currentAd.p());
                    fVar = this.closeButton;
                } else if (this.currentAd.p() == -2.0f) {
                    this.closeButton.setVisibility(0);
                } else {
                    jB = 0;
                    fVar = this.closeButton;
                }
                scheduleCloseButton(jB, fVar);
            }
            this.poststitialWasDisplayed = true;
        } catch (Throwable th) {
            this.logger.b(TAG, "Encountered error while showing poststitial. Dismissing...", th);
            dismiss();
        }
    }

    public void skipVideo() {
        com.applovin.impl.sdk.c.d dVar = this.statsManagerHelper;
        if (dVar != null) {
            dVar.f();
        }
        if (this.currentAd.s()) {
            dismiss();
        } else {
            showPoststitial();
        }
    }

    public void toggleMute() {
        boolean z = !isVideoPlayerMuted();
        com.applovin.impl.sdk.c.d dVar = this.statsManagerHelper;
        if (dVar != null) {
            dVar.h();
        }
        try {
            setVideoPlayerMuted(z);
            populateMuteImage(z);
            maybeSetPoststitialMuted(z);
        } catch (Throwable th) {
            this.logger.b(TAG, "Unable to set volume to " + z, th);
        }
    }
}
