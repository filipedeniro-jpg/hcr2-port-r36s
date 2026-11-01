package com.my.target.instreamads;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.aj;
import com.my.target.ak;
import com.my.target.am;
import com.my.target.b;
import com.my.target.cg;
import com.my.target.common.BaseAd;
import com.my.target.common.models.AudioData;
import com.my.target.common.models.ShareButtonData;
import com.my.target.core.engines.i;
import com.my.target.core.models.sections.f;
import com.my.target.fe;
import com.my.target.g;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes2.dex */
public final class InstreamAudioAd extends BaseAd {
    private static final int DEFAULT_LOADING_TIMEOUT_SECONDS = 10;
    private static final int MIN_LOADING_TIMEOUT_SECONDS = 5;
    private float audioDuration;

    @NonNull
    private final Context context;

    @Nullable
    private i engine;

    @Nullable
    private InstreamAudioAdListener listener;

    @NonNull
    private final AtomicBoolean loadOnce;
    private int loadingTimeoutSeconds;

    @Nullable
    private float[] midpoints;

    @Nullable
    private InstreamAudioAdPlayer player;

    @Nullable
    private f section;

    @Nullable
    private float[] userMidpoints;
    private float volume;

    public interface InstreamAudioAdListener {
        void onBannerComplete(@NonNull InstreamAudioAd instreamAudioAd, @NonNull InstreamAudioAdBanner instreamAudioAdBanner);

        void onBannerStart(@NonNull InstreamAudioAd instreamAudioAd, @NonNull InstreamAudioAdBanner instreamAudioAdBanner);

        void onBannerTimeLeftChange(float f, float f2, @NonNull InstreamAudioAd instreamAudioAd);

        void onComplete(@NonNull String str, @NonNull InstreamAudioAd instreamAudioAd);

        void onError(@NonNull String str, @NonNull InstreamAudioAd instreamAudioAd);

        void onLoad(@NonNull InstreamAudioAd instreamAudioAd);

        void onNoAd(@NonNull String str, @NonNull InstreamAudioAd instreamAudioAd);
    }

    public InstreamAudioAd(int i, @NonNull Context context) {
        super(i, b.a.i);
        this.loadOnce = new AtomicBoolean();
        this.loadingTimeoutSeconds = 10;
        this.volume = 1.0f;
        this.context = context;
        setTrackingEnvironmentEnabled(false);
        g.c("InstreamAudioAd created. Version: 5.3.9");
    }

    @Nullable
    public final InstreamAudioAdListener getListener() {
        return this.listener;
    }

    public final void setListener(@Nullable InstreamAudioAdListener instreamAudioAdListener) {
        this.listener = instreamAudioAdListener;
    }

    public final int getLoadingTimeout() {
        return this.loadingTimeoutSeconds;
    }

    public final void setLoadingTimeout(int i) {
        if (i < 5) {
            g.a("unable to set ad loading timeout < 5, set to 5 seconds");
            this.loadingTimeoutSeconds = 5;
        } else {
            g.a("ad loading timeout set to " + i + " seconds");
            this.loadingTimeoutSeconds = i;
        }
        i iVar = this.engine;
        if (iVar != null) {
            iVar.setLoadingTimeoutSeconds(this.loadingTimeoutSeconds);
        }
    }

    @Nullable
    public final InstreamAudioAdPlayer getPlayer() {
        return this.player;
    }

    public final void setPlayer(@Nullable InstreamAudioAdPlayer instreamAudioAdPlayer) {
        this.player = instreamAudioAdPlayer;
        i iVar = this.engine;
        if (iVar != null) {
            iVar.setPlayer(instreamAudioAdPlayer);
        }
    }

    public final float getVolume() {
        i iVar = this.engine;
        if (iVar != null) {
            return iVar.getVolume();
        }
        return this.volume;
    }

    public final void setVolume(float f) {
        if (Float.compare(f, 0.0f) < 0 || Float.compare(f, 1.0f) > 0) {
            g.a("unable to set volume" + f + ", volume must be in range [0..1]");
            return;
        }
        this.volume = f;
        i iVar = this.engine;
        if (iVar != null) {
            iVar.setVolume(f);
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
            g.b(this + " instance just loaded once, don't call load() more than one time per instance");
            return;
        }
        fe.newFactory(this.adConfig, this.loadingTimeoutSeconds).a(new fe.b() { // from class: com.my.target.instreamads.InstreamAudioAd.1
            @Override // com.my.target.c.b
            /* JADX INFO: renamed from: onResult, reason: merged with bridge method [inline-methods] */
            public void b(@Nullable f fVar, @Nullable String str) {
                InstreamAudioAd.this.handleResult(fVar, str);
            }
        }).a(this.context);
    }

    public final void configureMidpoints(float f) {
        configureMidpoints(f, null);
    }

    public final void configureMidpoints(float f, @Nullable float[] fArr) {
        am<AudioData> amVarA;
        if (f <= 0.0f) {
            g.a("midpoints are not configured, duration is not set or <= zero");
            return;
        }
        if (this.midpoints != null) {
            g.a("midpoints already configured");
            return;
        }
        this.userMidpoints = fArr;
        this.audioDuration = f;
        f fVar = this.section;
        if (fVar == null || (amVarA = fVar.a(am.a.dp)) == null) {
            return;
        }
        this.midpoints = cg.a(amVarA, this.userMidpoints, f);
        i iVar = this.engine;
        if (iVar != null) {
            iVar.setMidpoints(this.midpoints);
        }
    }

    public final void configureMidpointsPercents(float f, @Nullable float[] fArr) {
        if (fArr == null) {
            configureMidpoints(f);
        } else {
            configureMidpoints(f, cg.a(f, fArr));
        }
    }

    public final void pause() {
        i iVar = this.engine;
        if (iVar != null) {
            iVar.pause();
        }
    }

    public final void resume() {
        i iVar = this.engine;
        if (iVar != null) {
            iVar.resume();
        }
    }

    public final void stop() {
        i iVar = this.engine;
        if (iVar != null) {
            iVar.stop();
        }
    }

    public final void skip() {
        i iVar = this.engine;
        if (iVar != null) {
            iVar.skip();
        }
    }

    public final void skipBanner() {
        i iVar = this.engine;
        if (iVar != null) {
            iVar.skipBanner();
        }
    }

    public final void destroy() {
        this.listener = null;
        i iVar = this.engine;
        if (iVar != null) {
            iVar.destroy();
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
        i iVar = this.engine;
        if (iVar == null) {
            g.a("Unable to start ad: not loaded yet");
        } else if (iVar.getPlayer() == null) {
            g.a("Unable to start ad: player has not set");
        } else {
            this.engine.startMidroll(f);
        }
    }

    public final void handleCompanionClick(@NonNull InstreamAdCompanionBanner instreamAdCompanionBanner) {
        i iVar = this.engine;
        if (iVar != null) {
            iVar.handleCompanionClick(instreamAdCompanionBanner);
        }
    }

    public final void handleCompanionClick(@NonNull InstreamAdCompanionBanner instreamAdCompanionBanner, @NonNull Context context) {
        i iVar = this.engine;
        if (iVar != null) {
            iVar.handleCompanionClick(instreamAdCompanionBanner, context);
        }
    }

    public final void handleCompanionShow(@NonNull InstreamAdCompanionBanner instreamAdCompanionBanner) {
        i iVar = this.engine;
        if (iVar != null) {
            iVar.handleCompanionShow(instreamAdCompanionBanner);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleResult(@Nullable f fVar, @Nullable String str) {
        if (this.listener != null) {
            if (fVar == null || !fVar.hasContent()) {
                InstreamAudioAdListener instreamAudioAdListener = this.listener;
                if (str == null) {
                    str = "no ad";
                }
                instreamAudioAdListener.onNoAd(str, this);
                return;
            }
            this.section = fVar;
            this.engine = i.a(this, this.section, this.adConfig);
            this.engine.setLoadingTimeoutSeconds(this.loadingTimeoutSeconds);
            this.engine.setVolume(this.volume);
            InstreamAudioAdPlayer instreamAudioAdPlayer = this.player;
            if (instreamAudioAdPlayer != null) {
                this.engine.setPlayer(instreamAudioAdPlayer);
            }
            configureMidpoints(this.audioDuration, this.userMidpoints);
            this.listener.onLoad(this);
        }
    }

    private void start(@NonNull String str) {
        i iVar = this.engine;
        if (iVar == null) {
            g.a("Unable to start ad: not loaded yet");
        } else if (iVar.getPlayer() == null) {
            g.a("Unable to start ad: player has not set");
        } else {
            this.engine.start(str);
        }
    }

    public static final class InstreamAdCompanionBanner {

        @Nullable
        public final String adSlotID;

        @Nullable
        public final String apiFramework;
        public final int assetHeight;
        public final int assetWidth;
        public final int expandedHeight;
        public final int expandedWidth;
        public final int height;

        @Nullable
        public final String htmlResource;

        @Nullable
        public final String iframeResource;

        @Nullable
        public final String required;

        @Nullable
        public final String staticResource;
        public final int width;

        @NonNull
        public static InstreamAdCompanionBanner newBanner(@NonNull aj ajVar) {
            return new InstreamAdCompanionBanner(ajVar.getWidth(), ajVar.getHeight(), ajVar.getAssetWidth(), ajVar.getAssetHeight(), ajVar.getExpandedWidth(), ajVar.getExpandedHeight(), ajVar.getStaticResource(), ajVar.getIframeResource(), ajVar.getHtmlResource(), ajVar.getApiFramework(), ajVar.getAdSlotID(), ajVar.getRequired());
        }

        private InstreamAdCompanionBanner(int i, int i2, int i3, int i4, int i5, int i6, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6) {
            this.width = i;
            this.height = i2;
            this.assetWidth = i3;
            this.assetHeight = i4;
            this.expandedWidth = i5;
            this.expandedHeight = i6;
            this.staticResource = str;
            this.iframeResource = str2;
            this.htmlResource = str3;
            this.apiFramework = str4;
            this.adSlotID = str5;
            this.required = str6;
        }
    }

    public static final class InstreamAudioAdBanner {

        @Nullable
        public final String adText;
        public final boolean allowPause;
        public final boolean allowSeek;
        public final boolean allowSkip;
        public final boolean allowTrackChange;

        @NonNull
        public final List<InstreamAdCompanionBanner> companionBanners;
        public final float duration;

        @NonNull
        public final ArrayList<ShareButtonData> shareButtonDatas;

        @NonNull
        public static InstreamAudioAdBanner newBanner(@NonNull ak<AudioData> akVar) {
            ArrayList arrayList = new ArrayList();
            Iterator<aj> it = akVar.getCompanionBanners().iterator();
            while (it.hasNext()) {
                arrayList.add(InstreamAdCompanionBanner.newBanner(it.next()));
            }
            return new InstreamAudioAdBanner(akVar.isAllowSeek(), akVar.isAllowSkip(), akVar.isAllowTrackChange(), akVar.getDuration(), akVar.getAdText(), akVar.isAllowPause(), akVar.getShareButtonDatas(), arrayList);
        }

        private InstreamAudioAdBanner(boolean z, boolean z2, boolean z3, float f, @Nullable String str, boolean z4, @NonNull ArrayList<ShareButtonData> arrayList, @NonNull List<InstreamAdCompanionBanner> list) {
            this.allowSeek = z;
            this.allowSkip = z2;
            this.allowPause = z4;
            this.allowTrackChange = z3;
            this.duration = f;
            this.adText = str;
            this.shareButtonDatas = arrayList;
            this.companionBanners = list;
        }
    }
}
