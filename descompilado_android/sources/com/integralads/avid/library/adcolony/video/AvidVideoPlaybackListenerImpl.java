package com.integralads.avid.library.adcolony.video;

import com.integralads.avid.library.adcolony.base.AvidBaseListenerImpl;
import com.integralads.avid.library.adcolony.session.internal.InternalAvidAdSession;
import com.integralads.avid.library.adcolony.session.internal.jsbridge.AvidBridgeManager;
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

    @Override // com.integralads.avid.library.adcolony.video.AvidVideoPlaybackListener
    public void recordAdImpressionEvent() {
        a("AdImpression", null);
    }

    @Override // com.integralads.avid.library.adcolony.video.AvidVideoPlaybackListener
    public void recordAdStartedEvent() {
        a("AdStarted", null);
    }

    @Override // com.integralads.avid.library.adcolony.video.AvidVideoPlaybackListener
    public void recordAdLoadedEvent() {
        a("AdLoaded", null);
    }

    @Override // com.integralads.avid.library.adcolony.video.AvidVideoPlaybackListener
    public void recordAdVideoStartEvent() {
        a("AdVideoStart", null);
    }

    @Override // com.integralads.avid.library.adcolony.video.AvidVideoPlaybackListener
    public void recordAdStoppedEvent() {
        a("AdStopped", null);
    }

    @Override // com.integralads.avid.library.adcolony.video.AvidVideoPlaybackListener
    public void recordAdCompleteEvent() {
        a("AdVideoComplete", null);
    }

    @Override // com.integralads.avid.library.adcolony.video.AvidVideoPlaybackListener
    public void recordAdClickThruEvent() {
        a("AdClickThru", null);
    }

    @Override // com.integralads.avid.library.adcolony.video.AvidVideoPlaybackListener
    public void recordAdVideoFirstQuartileEvent() {
        a("AdVideoFirstQuartile", null);
    }

    @Override // com.integralads.avid.library.adcolony.video.AvidVideoPlaybackListener
    public void recordAdVideoMidpointEvent() {
        a("AdVideoMidpoint", null);
    }

    @Override // com.integralads.avid.library.adcolony.video.AvidVideoPlaybackListener
    public void recordAdVideoThirdQuartileEvent() {
        a("AdVideoThirdQuartile", null);
    }

    @Override // com.integralads.avid.library.adcolony.video.AvidVideoPlaybackListener
    public void recordAdPausedEvent() {
        a("AdPaused", null);
    }

    @Override // com.integralads.avid.library.adcolony.video.AvidVideoPlaybackListener
    public void recordAdPlayingEvent() {
        a("AdPlaying", null);
    }

    @Override // com.integralads.avid.library.adcolony.video.AvidVideoPlaybackListener
    public void recordAdExpandedChangeEvent() {
        a("AdExpandedChange", null);
    }

    @Override // com.integralads.avid.library.adcolony.video.AvidVideoPlaybackListener
    public void recordAdUserMinimizeEvent() {
        a("AdUserMinimize", null);
    }

    @Override // com.integralads.avid.library.adcolony.video.AvidVideoPlaybackListener
    public void recordAdUserAcceptInvitationEvent() {
        a("AdUserAcceptInvitation", null);
    }

    @Override // com.integralads.avid.library.adcolony.video.AvidVideoPlaybackListener
    public void recordAdUserCloseEvent() {
        a("AdUserClose", null);
    }

    @Override // com.integralads.avid.library.adcolony.video.AvidVideoPlaybackListener
    public void recordAdSkippedEvent() {
        a("AdSkipped", null);
    }

    @Override // com.integralads.avid.library.adcolony.video.AvidVideoPlaybackListener
    public void recordAdVolumeChangeEvent(Integer num) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("volume", num);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        a("AdVolumeChange", jSONObject);
    }

    @Override // com.integralads.avid.library.adcolony.video.AvidVideoPlaybackListener
    public void recordAdEnteredFullscreenEvent() {
        a("AdEnteredFullscreen", null);
    }

    @Override // com.integralads.avid.library.adcolony.video.AvidVideoPlaybackListener
    public void recordAdExitedFullscreenEvent() {
        a("AdExitedFullscreen", null);
    }

    @Override // com.integralads.avid.library.adcolony.video.AvidVideoPlaybackListener
    public void recordAdDurationChangeEvent(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("adDuration", str);
            jSONObject.put("adDuration", str2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        a("AdDurationChange", jSONObject);
    }

    @Override // com.integralads.avid.library.adcolony.video.AvidVideoPlaybackListener
    public void recordAdError(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("message", str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        a("AdError", jSONObject);
    }

    private void a(String str, JSONObject jSONObject) {
        assertSessionIsNotEnded();
        a();
        getAvidBridgeManager().publishVideoEvent(str, jSONObject);
    }

    private void a() {
        if (!getAvidAdSession().isReady()) {
            throw new IllegalStateException("The AVID ad session is not ready. Please ensure you have called recordReadyEvent for the deferred AVID ad session before recording any video event.");
        }
    }
}
