package com.ironsource.mediationsdk.model;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class OfferwallConfigurations {
    private static final int DEFAULT_OW_PLACEMENT_ID = 0;
    private OfferwallPlacement mDefaultOWPlacement;
    private ApplicationEvents mEvents;
    private ArrayList<OfferwallPlacement> mOWPlacements = new ArrayList<>();

    public OfferwallConfigurations(ApplicationEvents applicationEvents) {
        this.mEvents = applicationEvents;
    }

    public void addOfferwallPlacement(OfferwallPlacement offerwallPlacement) {
        if (offerwallPlacement != null) {
            this.mOWPlacements.add(offerwallPlacement);
            if (this.mDefaultOWPlacement == null) {
                this.mDefaultOWPlacement = offerwallPlacement;
            } else if (offerwallPlacement.getPlacementId() == 0) {
                this.mDefaultOWPlacement = offerwallPlacement;
            }
        }
    }

    public OfferwallPlacement getOfferwallPlacement(String str) {
        for (OfferwallPlacement offerwallPlacement : this.mOWPlacements) {
            if (offerwallPlacement.getPlacementName().equals(str)) {
                return offerwallPlacement;
            }
        }
        return null;
    }

    public OfferwallPlacement getDefaultOfferwallPlacement() {
        for (OfferwallPlacement offerwallPlacement : this.mOWPlacements) {
            if (offerwallPlacement.isDefault()) {
                return offerwallPlacement;
            }
        }
        return this.mDefaultOWPlacement;
    }

    public ApplicationEvents getOfferWallEventsConfigurations() {
        return this.mEvents;
    }
}
