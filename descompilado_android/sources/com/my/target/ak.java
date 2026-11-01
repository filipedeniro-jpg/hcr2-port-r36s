package com.my.target;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.ah;
import com.my.target.common.models.AudioData;
import com.my.target.common.models.ImageData;
import com.my.target.common.models.ShareButtonData;
import com.my.target.common.models.VideoData;
import java.util.ArrayList;

/* JADX INFO: compiled from: MediaBanner.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ak<T extends ah> extends ai {
    public static final boolean DEFAULT_ALLOW_BACK_BUTTON = true;
    public static final boolean DEFAULT_ALLOW_CLOSE = true;
    public static final float DEFAULT_ALLOW_CLOSE_DELAY = 0.0f;
    public static final boolean DEFAULT_ALLOW_PAUSE = true;
    public static final boolean DEFAULT_ALLOW_REPLAY = true;
    public static final boolean DEFAULT_ALLOW_SEEK = false;
    public static final boolean DEFAULT_ALLOW_SKIP = false;
    public static final boolean DEFAULT_ALLOW_TRACK_CHANGE = false;
    public static final boolean DEFAULT_AUTO_MUTE = false;
    public static final boolean DEFAULT_AUTO_PLAY = true;

    @NonNull
    public static final String DEFAULT_CLOSE_ACTION_TEXT = "Close";

    @NonNull
    public static final String DEFAULT_CLOSE_DELAY_ACTION_TEXT = "Ad can be skipped after %ds";
    public static final boolean DEFAULT_HAS_CTA = true;
    public static final float DEFAULT_POINT_P = 50.0f;

    @NonNull
    public static final String DEFAULT_REPLAY_ACTION_TEXT = "Replay";
    public static final boolean DEFAULT_SHOW_PLAYER_CONTROLS = true;

    @Nullable
    private String adText;

    @Nullable
    private T mediaData;
    private float point;
    private float pointP;

    @Nullable
    private ImageData preview;

    @NonNull
    private final ArrayList<aj> companionBanners = new ArrayList<>();

    @NonNull
    private final ArrayList<ShareButtonData> shareButtonDatas = new ArrayList<>();

    @NonNull
    private String closeActionText = "Close";

    @NonNull
    private String replayActionText = DEFAULT_REPLAY_ACTION_TEXT;

    @NonNull
    private String closeDelayActionText = DEFAULT_CLOSE_DELAY_ACTION_TEXT;
    private boolean autoMute = false;
    private boolean autoPlay = true;
    private boolean hasCtaButton = true;
    private boolean allowReplay = true;
    private boolean showPlayerControls = true;
    private boolean allowClose = true;
    private boolean allowSeek = false;
    private boolean allowSkip = false;
    private boolean allowTrackChange = false;
    private boolean allowBackButton = true;
    private boolean allowPause = true;
    private float allowCloseDelay = 0.0f;

    @NonNull
    public static ak<VideoData> newVideoBanner() {
        return newBanner();
    }

    @NonNull
    public static ak<AudioData> newAudioBanner() {
        return newBanner();
    }

    @NonNull
    public static <T extends ah> ak<T> newBanner() {
        return new ak<>();
    }

    private ak() {
    }

    public void setAutoMute(boolean z) {
        this.autoMute = z;
    }

    public void setAutoPlay(boolean z) {
        this.autoPlay = z;
    }

    public void setCloseActionText(@NonNull String str) {
        this.closeActionText = str;
    }

    public void setHasCtaButton(boolean z) {
        this.hasCtaButton = z;
    }

    public void setPreview(@Nullable ImageData imageData) {
        this.preview = imageData;
    }

    public void setReplayActionText(@NonNull String str) {
        this.replayActionText = str;
    }

    public void setShowPlayerControls(boolean z) {
        this.showPlayerControls = z;
    }

    public void setMediaData(@Nullable T t) {
        this.mediaData = t;
    }

    @Nullable
    public T getMediaData() {
        return this.mediaData;
    }

    @NonNull
    public String getCloseActionText() {
        return this.closeActionText;
    }

    @NonNull
    public String getReplayActionText() {
        return this.replayActionText;
    }

    public boolean isAutoMute() {
        return this.autoMute;
    }

    public boolean isShowPlayerControls() {
        return this.showPlayerControls;
    }

    public boolean isAutoPlay() {
        return this.autoPlay;
    }

    public boolean isHasCtaButton() {
        return this.hasCtaButton;
    }

    @Nullable
    public ImageData getPreview() {
        return this.preview;
    }

    public boolean isAllowReplay() {
        return this.allowReplay;
    }

    public void setAllowReplay(boolean z) {
        this.allowReplay = z;
    }

    public void setAllowCloseDelay(float f) {
        this.allowCloseDelay = f;
    }

    public float getAllowCloseDelay() {
        return this.allowCloseDelay;
    }

    public void setAllowClose(boolean z) {
        this.allowClose = z;
    }

    public boolean isAllowClose() {
        return this.allowClose;
    }

    public float getPoint() {
        return this.point;
    }

    public float getPointP() {
        return this.pointP;
    }

    public void setPoint(float f) {
        this.point = f;
    }

    public void setPointP(float f) {
        this.pointP = f;
    }

    @Override // com.my.target.ai
    public int getWidth() {
        T t = this.mediaData;
        if (t != null) {
            return t.getWidth();
        }
        return 0;
    }

    @Override // com.my.target.ai
    public int getHeight() {
        T t = this.mediaData;
        if (t != null) {
            return t.getHeight();
        }
        return 0;
    }

    @Nullable
    public String getAdText() {
        return this.adText;
    }

    public void setAdText(@Nullable String str) {
        this.adText = str;
    }

    public boolean isAllowSeek() {
        return this.allowSeek;
    }

    public void setAllowSeek(boolean z) {
        this.allowSeek = z;
    }

    public boolean isAllowSkip() {
        return this.allowSkip;
    }

    public void setAllowSkip(boolean z) {
        this.allowSkip = z;
    }

    public boolean isAllowTrackChange() {
        return this.allowTrackChange;
    }

    public void setAllowTrackChange(boolean z) {
        this.allowTrackChange = z;
    }

    @NonNull
    public ArrayList<aj> getCompanionBanners() {
        return new ArrayList<>(this.companionBanners);
    }

    @NonNull
    public ArrayList<ShareButtonData> getShareButtonDatas() {
        return new ArrayList<>(this.shareButtonDatas);
    }

    public void addCompanion(@NonNull aj ajVar) {
        this.companionBanners.add(ajVar);
    }

    public boolean isAllowBackButton() {
        return this.allowBackButton;
    }

    public void setAllowBackButton(boolean z) {
        this.allowBackButton = z;
    }

    public boolean isAllowPause() {
        return this.allowPause;
    }

    public void setAllowPause(boolean z) {
        this.allowPause = z;
    }

    @NonNull
    public String getCloseDelayActionText() {
        return this.closeDelayActionText;
    }

    public void setCloseDelayActionText(@NonNull String str) {
        this.closeDelayActionText = str;
    }

    public void addShareButtonData(@NonNull ShareButtonData shareButtonData) {
        this.shareButtonDatas.add(shareButtonData);
    }
}
