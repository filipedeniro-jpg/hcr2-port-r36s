package com.ironsource.mediationsdk.events;

import com.ironsource.eventsmodule.EventData;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import com.ironsource.mediationsdk.utils.SessionDepthManager;

/* JADX INFO: loaded from: classes2.dex */
public class InterstitialEventsManager extends BaseEventsManager {
    private static InterstitialEventsManager sInstance;
    private String mCurrentISPlacement;

    @Override // com.ironsource.mediationsdk.events.BaseEventsManager
    protected boolean shouldExtractCurrentPlacement(EventData eventData) {
        return false;
    }

    @Override // com.ironsource.mediationsdk.events.BaseEventsManager
    protected boolean shouldIncludeCurrentPlacement(EventData eventData) {
        return false;
    }

    private InterstitialEventsManager() {
        this.mFormatterType = "ironbeast";
        this.mAdUnitType = 2;
        this.mEventType = IronSourceConstants.INTERSTITIAL_EVENT_TYPE;
        this.mCurrentISPlacement = "";
    }

    public static synchronized InterstitialEventsManager getInstance() {
        if (sInstance == null) {
            sInstance = new InterstitialEventsManager();
            sInstance.initState();
        }
        return sInstance;
    }

    @Override // com.ironsource.mediationsdk.events.BaseEventsManager
    protected boolean isTopPriorityEvent(EventData eventData) {
        return eventData.getEventId() == 2204 || eventData.getEventId() == 2005 || eventData.getEventId() == 2301 || eventData.getEventId() == 2300 || eventData.getEventId() == 3005 || eventData.getEventId() == 3015;
    }

    @Override // com.ironsource.mediationsdk.events.BaseEventsManager
    protected int getSessionDepth(EventData eventData) {
        return SessionDepthManager.getInstance().getSessionDepth(eventData.getEventId() >= 3000 && eventData.getEventId() < 4000 ? 3 : 2);
    }

    @Override // com.ironsource.mediationsdk.events.BaseEventsManager
    protected boolean increaseSessionDepthIfNeeded(EventData eventData) {
        if (eventData.getEventId() == 2204) {
            SessionDepthManager.getInstance().increaseSessionDepth(2);
            return false;
        }
        if (eventData.getEventId() != 3305) {
            return false;
        }
        SessionDepthManager.getInstance().increaseSessionDepth(3);
        return false;
    }

    @Override // com.ironsource.mediationsdk.events.BaseEventsManager
    protected void setCurrentPlacement(EventData eventData) {
        this.mCurrentISPlacement = eventData.getAdditionalDataJSON().optString("placement");
    }

    @Override // com.ironsource.mediationsdk.events.BaseEventsManager
    protected String getCurrentPlacement(int i) {
        return this.mCurrentISPlacement;
    }

    @Override // com.ironsource.mediationsdk.events.BaseEventsManager
    protected void initConnectivitySensitiveEventsSet() {
        this.mConnectivitySensitiveEventsSet.add(2001);
        this.mConnectivitySensitiveEventsSet.add(2002);
        this.mConnectivitySensitiveEventsSet.add(2004);
        this.mConnectivitySensitiveEventsSet.add(Integer.valueOf(IronSourceConstants.IS_INSTANCE_READY_TRUE));
        this.mConnectivitySensitiveEventsSet.add(Integer.valueOf(IronSourceConstants.IS_INSTANCE_READY_FALSE));
    }
}
