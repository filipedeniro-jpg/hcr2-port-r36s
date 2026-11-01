package com.ironsource.mediationsdk.events;

import com.ironsource.eventsmodule.EventData;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import com.ironsource.mediationsdk.utils.SessionDepthManager;

/* JADX INFO: loaded from: classes2.dex */
public class RewardedVideoEventsManager extends BaseEventsManager {
    private static RewardedVideoEventsManager sInstance;
    private String mCurrentOWPlacement;

    @Override // com.ironsource.mediationsdk.events.BaseEventsManager
    protected boolean shouldExtractCurrentPlacement(EventData eventData) {
        return false;
    }

    private RewardedVideoEventsManager() {
        this.mFormatterType = "outcome";
        this.mAdUnitType = 3;
        this.mEventType = IronSourceConstants.REWARDED_VIDEO_EVENT_TYPE;
        this.mCurrentOWPlacement = "";
    }

    public static synchronized RewardedVideoEventsManager getInstance() {
        if (sInstance == null) {
            sInstance = new RewardedVideoEventsManager();
            sInstance.initState();
        }
        return sInstance;
    }

    @Override // com.ironsource.mediationsdk.events.BaseEventsManager
    protected boolean shouldIncludeCurrentPlacement(EventData eventData) {
        return eventData.getEventId() == 305;
    }

    @Override // com.ironsource.mediationsdk.events.BaseEventsManager
    protected boolean isTopPriorityEvent(EventData eventData) {
        int eventId = eventData.getEventId();
        return eventId == 14 || eventId == 514 || eventId == 305 || eventId == 1005 || eventId == 1203 || eventId == 1010 || eventId == 1301 || eventId == 1302;
    }

    @Override // com.ironsource.mediationsdk.events.BaseEventsManager
    protected int getSessionDepth(EventData eventData) {
        if (eventData.getEventId() == 15 || (eventData.getEventId() >= 300 && eventData.getEventId() < 400)) {
            return SessionDepthManager.getInstance().getSessionDepth(0);
        }
        return SessionDepthManager.getInstance().getSessionDepth(1);
    }

    @Override // com.ironsource.mediationsdk.events.BaseEventsManager
    protected void setCurrentPlacement(EventData eventData) {
        if (eventData.getEventId() == 15 || (eventData.getEventId() >= 300 && eventData.getEventId() < 400)) {
            this.mCurrentOWPlacement = eventData.getAdditionalDataJSON().optString("placement");
        }
    }

    @Override // com.ironsource.mediationsdk.events.BaseEventsManager
    protected String getCurrentPlacement(int i) {
        return (i == 15 || (i >= 300 && i < 400)) ? this.mCurrentOWPlacement : "";
    }

    @Override // com.ironsource.mediationsdk.events.BaseEventsManager
    protected boolean increaseSessionDepthIfNeeded(EventData eventData) {
        if (eventData.getEventId() == 1203) {
            SessionDepthManager.getInstance().increaseSessionDepth(1);
        } else if (eventData.getEventId() == 305) {
            SessionDepthManager.getInstance().increaseSessionDepth(0);
        }
        return false;
    }

    @Override // com.ironsource.mediationsdk.events.BaseEventsManager
    protected void initConnectivitySensitiveEventsSet() {
        this.mConnectivitySensitiveEventsSet.add(1001);
        this.mConnectivitySensitiveEventsSet.add(Integer.valueOf(IronSourceConstants.RV_INSTANCE_SHOW_CHANCE));
        this.mConnectivitySensitiveEventsSet.add(Integer.valueOf(IronSourceConstants.RV_INSTANCE_READY_TRUE));
        this.mConnectivitySensitiveEventsSet.add(Integer.valueOf(IronSourceConstants.RV_INSTANCE_READY_FALSE));
    }
}
