package com.my.target.instreamads;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.ak;
import com.my.target.am;
import com.my.target.b;
import com.my.target.cg;
import com.my.target.common.BaseAd;
import com.my.target.common.models.VideoData;
import com.my.target.core.engines.j;
import com.my.target.core.models.sections.g;
import com.my.target.fj;
import com.my.target.fn;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes2.dex */
public final class InstreamAd extends BaseAd {
    private static final int DEFAULT_LOADING_TIMEOUT_SECONDS = 10;
    private static final int MIN_LOADING_TIMEOUT_SECONDS = 5;

    @NonNull
    private final Context context;

    @Nullable
    private j engine;
    private boolean isFullscreen;

    @Nullable
    private InstreamAdListener listener;

    @NonNull
    private final AtomicBoolean loadOnce;
    private int loadingTimeoutSeconds;

    @Nullable
    private float[] midpoints;

    @Nullable
    private InstreamAdPlayer player;

    @Nullable
    private g section;

    @Nullable
    private float[] userMidpoints;
    private float videoDuration;
    private float volume;

    public interface InstreamAdListener {
        void onBannerComplete(@NonNull InstreamAd instreamAd, @NonNull InstreamAdBanner instreamAdBanner);

        void onBannerPause(@NonNull InstreamAd instreamAd, @NonNull InstreamAdBanner instreamAdBanner);

        void onBannerResume(@NonNull InstreamAd instreamAd, @NonNull InstreamAdBanner instreamAdBanner);

        void onBannerStart(@NonNull InstreamAd instreamAd, @NonNull InstreamAdBanner instreamAdBanner);

        void onBannerTimeLeftChange(float f, float f2, @NonNull InstreamAd instreamAd);

        void onComplete(@NonNull String str, @NonNull InstreamAd instreamAd);

        void onError(@NonNull String str, @NonNull InstreamAd instreamAd);

        void onLoad(@NonNull InstreamAd instreamAd);

        void onNoAd(@NonNull String str, @NonNull InstreamAd instreamAd);
    }

    public InstreamAd(int i, @NonNull Context context) {
        super(i, b.a.h);
        this.loadOnce = new AtomicBoolean();
        this.loadingTimeoutSeconds = 10;
        this.volume = 1.0f;
        this.context = context;
        setTrackingEnvironmentEnabled(false);
        com.my.target.g.c("InstreamAd created. Version: 5.3.9");
    }

    @Nullable
    public final InstreamAdListener getListener() {
        return this.listener;
    }

    public final void setListener(@Nullable InstreamAdListener instreamAdListener) {
        this.listener = instreamAdListener;
    }

    public final int getLoadingTimeout() {
        return this.loadingTimeoutSeconds;
    }

    public final void setLoadingTimeout(int i) {
        if (i < 5) {
            com.my.target.g.a("unable to set ad loading timeout < 5, set to 5 seconds");
            this.loadingTimeoutSeconds = 5;
        } else {
            com.my.target.g.a("ad loading timeout set to " + i + " seconds");
            this.loadingTimeoutSeconds = i;
        }
        j jVar = this.engine;
        if (jVar != null) {
            jVar.setLoadingTimeoutSeconds(this.loadingTimeoutSeconds);
        }
    }

    public final int getVideoQuality() {
        return this.adConfig.getVideoQuality();
    }

    public final void setVideoQuality(int i) {
        this.adConfig.setVideoQuality(i);
    }

    @Nullable
    public final InstreamAdPlayer getPlayer() {
        return this.player;
    }

    public final void setPlayer(@Nullable InstreamAdPlayer instreamAdPlayer) {
        this.player = instreamAdPlayer;
        j jVar = this.engine;
        if (jVar != null) {
            jVar.setPlayer(instreamAdPlayer);
        }
    }

    public final void swapPlayer(@Nullable InstreamAdPlayer instreamAdPlayer) {
        this.player = instreamAdPlayer;
        j jVar = this.engine;
        if (jVar != null) {
            jVar.swapPlayer(instreamAdPlayer);
        }
    }

    public final boolean isFullscreen() {
        return this.isFullscreen;
    }

    public final void setFullscreen(boolean z) {
        this.isFullscreen = z;
        j jVar = this.engine;
        if (jVar != null) {
            jVar.setFullscreen(z);
        }
    }

    public final float getVolume() {
        j jVar = this.engine;
        if (jVar != null) {
            return jVar.getVolume();
        }
        return this.volume;
    }

    public final void setVolume(float f) {
        if (Float.compare(f, 0.0f) < 0 || Float.compare(f, 1.0f) > 0) {
            com.my.target.g.a("unable to set volume" + f + ", volume must be in range [0..1]");
            return;
        }
        this.volume = f;
        j jVar = this.engine;
        if (jVar != null) {
            jVar.setVolume(f);
        }
    }

    @NonNull
    public final float[] getMidPoints() {
        float[] fArr = this.midpoints;
        if (fArr == null) {
            return new float[0];
        }
        return (float[]) fArr.clone();
    }

    public final void load() {
        if (!this.loadOnce.compareAndSet(false, true)) {
            com.my.target.g.b(this + " instance just loaded once, don't call load() more than one time per instance");
            return;
        }
        fj.newFactory(this.adConfig, this.loadingTimeoutSeconds).a(new fj.b() { // from class: com.my.target.instreamads.InstreamAd.1
            @Override // com.my.target.c.b
            /* JADX INFO: renamed from: onResult, reason: merged with bridge method [inline-methods] */
            public void b(@Nullable g gVar, @Nullable String str) {
                InstreamAd.this.handleResult(gVar, str);
            }
        }).a(this.context);
    }

    public final void configureMidpoints(float f) {
        configureMidpoints(f, null);
    }

    public final void configureMidpoints(float f, @Nullable float[] fArr) {
        am<VideoData> amVarA;
        if (f <= 0.0f) {
            com.my.target.g.a("midpoints are not configured, duration is not set or <= zero");
            return;
        }
        if (this.midpoints != null) {
            com.my.target.g.a("midpoints already configured");
            return;
        }
        this.userMidpoints = fArr;
        this.videoDuration = f;
        g gVar = this.section;
        if (gVar == null || (amVarA = gVar.a(am.a.dp)) == null) {
            return;
        }
        this.midpoints = cg.a(amVarA, this.userMidpoints, f);
        j jVar = this.engine;
        if (jVar != null) {
            jVar.setMidpoints(this.midpoints);
        }
    }

    public final void configureMidpointsPercents(float f, @Nullable float[] fArr) {
        if (fArr == null) {
            configureMidpoints(f);
        } else {
            configureMidpoints(f, cg.a(f, fArr));
        }
    }

    public final void useDefaultPlayer() {
        setPlayer(new fn(this.context, (byte) 0));
    }

    public final void pause() {
        j jVar = this.engine;
        if (jVar != null) {
            jVar.pause();
        }
    }

    public final void resume() {
        j jVar = this.engine;
        if (jVar != null) {
            jVar.resume();
        }
    }

    public final void stop() {
        j jVar = this.engine;
        if (jVar != null) {
            jVar.stop();
        }
    }

    public final void skip() {
        j jVar = this.engine;
        if (jVar != null) {
            jVar.skip();
        }
    }

    public final void skipBanner() {
        j jVar = this.engine;
        if (jVar != null) {
            jVar.skipBanner();
        }
    }

    public final void handleClick() {
        j jVar = this.engine;
        if (jVar != null) {
            jVar.handleClick();
        }
    }

    public final void destroy() {
        this.listener = null;
        j jVar = this.engine;
        if (jVar != null) {
            jVar.destroy();
        }
    }

    public final void startPreroll() {
        start(am.a.dm);
    }

    public final void startPostroll() {
        start("postroll");
    }

    public final void startPauseroll() {
        start(am.a.f703do);
    }

    public final void startMidroll(float f) {
        j jVar = this.engine;
        if (jVar == null) {
            com.my.target.g.a("Unable to start ad: not loaded yet");
        } else if (jVar.getPlayer() == null) {
            com.my.target.g.a("Unable to start ad: player has not set");
        } else {
            this.engine.startMidroll(f);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleResult(@Nullable g gVar, @Nullable String str) {
        if (this.listener != null) {
            if (gVar == null || !gVar.hasContent()) {
                InstreamAdListener instreamAdListener = this.listener;
                if (str == null) {
                    str = "no ad";
                }
                instreamAdListener.onNoAd(str, this);
                return;
            }
            this.section = gVar;
            this.engine = j.a(this, this.section, this.adConfig);
            this.engine.setLoadingTimeoutSeconds(this.loadingTimeoutSeconds);
            this.engine.setVolume(this.volume);
            InstreamAdPlayer instreamAdPlayer = this.player;
            if (instreamAdPlayer != null) {
                this.engine.setPlayer(instreamAdPlayer);
            }
            configureMidpoints(this.videoDuration, this.userMidpoints);
            this.listener.onLoad(this);
        }
    }

    private void start(@NonNull String str) {
        j jVar = this.engine;
        if (jVar == null) {
            com.my.target.g.a("Unable to start ad: not loaded yet");
        } else if (jVar.getPlayer() == null) {
            com.my.target.g.a("Unable to start ad: player has not set");
        } else {
            this.engine.start(str);
        }
    }

    public static final class InstreamAdBanner {
        public final boolean allowClose;
        public final float allowCloseDelay;
        public final boolean allowPause;

        @Nullable
        public final String ctaText;
        public final float duration;
        public final int videoHeight;
        public final int videoWidth;

        @NonNull
        public static InstreamAdBanner newBanner(@NonNull ak akVar) {
            return new InstreamAdBanner(akVar.isAllowClose(), akVar.getAllowCloseDelay(), akVar.getDuration(), akVar.getWidth(), akVar.getHeight(), akVar.getCtaText(), akVar.isAllowPause());
        }

        private InstreamAdBanner(boolean z, float f, float f2, int i, int i2, @Nullable String str, boolean z2) {
            this.allowClose = z;
            this.allowCloseDelay = f;
            this.duration = f2;
            this.videoHeight = i2;
            this.videoWidth = i;
            this.ctaText = str;
            this.allowPause = z2;
        }
    }
}
