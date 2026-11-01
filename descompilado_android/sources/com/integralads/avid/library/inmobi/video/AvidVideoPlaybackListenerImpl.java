package com.integralads.avid.library.inmobi.video;

import com.integralads.avid.library.inmobi.base.AvidBaseListenerImpl;
import com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSession;
import com.integralads.avid.library.inmobi.session.internal.jsbridge.AvidBridgeManager;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class AvidVideoPlaybackListenerImpl extends AvidBaseListenerImpl implements AvidVideoPlaybackListener {
    public static final String AD_CLICK_THRU = "AdClickThru";
    public static final String AD_DURATION = "adDuration";
    public static final String AD_DURATION_CHANGE = "AdDurationChange";
    public static final String AD_ENTERED_FULLSCREEN = "AdEnteredFullscreen";
    public static final String AD_ERROR = "AdError";
    public static final String AD_EXITED_FULLSCREEN = "AdExitedFullscreen";
    public static final String AD_EXPANDED_CHANGE = "AdExpandedChange";
    public static final String AD_IMPRESSION = "AdImpression";
    public static final String AD_LOADED = "AdLoaded";
    public static final String AD_PAUSED = "AdPaused";
    public static final String AD_PLAYING = "AdPlaying";
    public static final String AD_REMAINING_TIME = "adDuration";
    public static final String AD_SKIPPED = "AdSkipped";
    public static final String AD_STARTED = "AdStarted";
    public static final String AD_STOPPED = "AdStopped";
    public static final String AD_USER_ACCEPT_INVITATION = "AdUserAcceptInvitation";
    public static final String AD_USER_CLOSE = "AdUserClose";
    public static final String AD_USER_MINIMIZE = "AdUserMinimize";
    public static final String AD_VIDEO_COMPLETE = "AdVideoComplete";
    public static final String AD_VIDEO_FIRST_QUARTILE = "AdVideoFirstQuartile";
    public static final String AD_VIDEO_MIDPOINT = "AdVideoMidpoint";
    public static final String AD_VIDEO_START = "AdVideoStart";
    public static final String AD_VIDEO_THIRD_QUARTILE = "AdVideoThirdQuartile";
    public static final String AD_VOLUME_CHANGE = "AdVolumeChange";
    public static final String MESSAGE = "message";
    public static final String VOLUME = "volume";

    public AvidVideoPlaybackListenerImpl(InternalAvidAdSession internalAvidAdSession, AvidBridgeManager avidBridgeManager) {
        super(internalAvidAdSession, avidBridgeManager);
    }

    @Override // com.integralads.avid.library.inmobi.video.AvidVideoPlaybackListener
    public void recordAdImpressionEvent() {
        publishVideoEvent("AdImpression", null);
    }

    @Override // com.integralads.avid.library.inmobi.video.AvidVideoPlaybackListener
    public void recordAdStartedEvent() {
        publishVideoEvent("AdStarted", null);
    }

    @Override // com.integralads.avid.library.inmobi.video.AvidVideoPlaybackListener
    public void recordAdLoadedEvent() {
        publishVideoEvent("AdLoaded", null);
    }

    @Override // com.integralads.avid.library.inmobi.video.AvidVideoPlaybackListener
    public void recordAdVideoStartEvent() {
        publishVideoEvent("AdVideoStart", null);
    }

    @Override // com.integralads.avid.library.inmobi.video.AvidVideoPlaybackListener
    public void recordAdStoppedEvent() {
        publishVideoEvent("AdStopped", null);
    }

    @Override // com.integralads.avid.library.inmobi.video.AvidVideoPlaybackListener
    public void recordAdCompleteEvent() {
        publishVideoEvent("AdVideoComplete", null);
    }

    @Override // com.integralads.avid.library.inmobi.video.AvidVideoPlaybackListener
    public void recordAdClickThruEvent() {
        publishVideoEvent("AdClickThru", null);
    }

    @Override // com.integralads.avid.library.inmobi.video.AvidVideoPlaybackListener
    public void recordAdVideoFirstQuartileEvent() {
        publishVideoEvent("AdVideoFirstQuartile", null);
    }

    @Override // com.integralads.avid.library.inmobi.video.AvidVideoPlaybackListener
    public void recordAdVideoMidpointEvent() {
        publishVideoEvent("AdVideoMidpoint", null);
    }

    @Override // com.integralads.avid.library.inmobi.video.AvidVideoPlaybackListener
    public void recordAdVideoThirdQuartileEvent() {
        publishVideoEvent("AdVideoThirdQuartile", null);
    }

    @Override // com.integralads.avid.library.inmobi.video.AvidVideoPlaybackListener
    public void recordAdPausedEvent() {
        publishVideoEvent("AdPaused", null);
    }

    @Override // com.integralads.avid.library.inmobi.video.AvidVideoPlaybackListener
    public void recordAdPlayingEvent() {
        publishVideoEvent("AdPlaying", null);
    }

    @Override // com.integralads.avid.library.inmobi.video.AvidVideoPlaybackListener
    public void recordAdExpandedChangeEvent() {
        publishVideoEvent("AdExpandedChange", null);
    }

    @Override // com.integralads.avid.library.inmobi.video.AvidVideoPlaybackListener
    public void recordAdUserMinimizeEvent() {
        publishVideoEvent("AdUserMinimize", null);
    }

    @Override // com.integralads.avid.library.inmobi.video.AvidVideoPlaybackListener
    public void recordAdUserAcceptInvitationEvent() {
        publishVideoEvent("AdUserAcceptInvitation", null);
    }

    @Override // com.integralads.avid.library.inmobi.video.AvidVideoPlaybackListener
    public void recordAdUserCloseEvent() {
        publishVideoEvent("AdUserClose", null);
    }

    @Override // com.integralads.avid.library.inmobi.video.AvidVideoPlaybackListener
    public void recordAdSkippedEvent() {
        publishVideoEvent("AdSkipped", null);
    }

    @Override // com.integralads.avid.library.inmobi.video.AvidVideoPlaybackListener
    public void recordAdVolumeChangeEvent(Integer num) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("volume", num);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        publishVideoEvent("AdVolumeChange", jSONObject);
    }

    @Override // com.integralads.avid.library.inmobi.video.AvidVideoPlaybackListener
    public void recordAdEnteredFullscreenEvent() {
        publishVideoEvent("AdEnteredFullscreen", null);
    }

    @Override // com.integralads.avid.library.inmobi.video.AvidVideoPlaybackListener
    public void recordAdExitedFullscreenEvent() {
        publishVideoEvent("AdExitedFullscreen", null);
    }

    @Override // com.integralads.avid.library.inmobi.video.AvidVideoPlaybackListener
    public void recordAdDurationChangeEvent(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("adDuration", str);
            jSONObject.put("adDuration", str2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        publishVideoEvent("AdDurationChange", jSONObject);
    }

    @Override // com.integralads.avid.library.inmobi.video.AvidVideoPlaybackListener
    public void recordAdError(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("message", str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        publishVideoEvent("AdError", jSONObject);
    }

    private void publishVideoEvent(String str, JSONObject jSONObject) {
        assertSessionIsNotEnded();
        assertSessionIsReady();
        getAvidBridgeManager().publishVideoEvent(str, jSONObject);
    }

    private void assertSessionIsReady() {
        if (!getAvidAdSession().isReady()) {
            throw new IllegalStateException("The AVID ad session is not ready. Please ensure you have called recordReadyEvent for the deferred AVID ad session before recording any video event.");
        }
    }
}
