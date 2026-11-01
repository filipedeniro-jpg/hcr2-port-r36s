package com.ironsource.sdk.ISNAdView;

import java.util.HashMap;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
class ViewVisibilityParameters {
    private int mVisibility = 4;
    private int mWindowVisibility = 4;
    private HashMap<String, Boolean> mVisibilityParametersMap = new HashMap<String, Boolean>() { // from class: com.ironsource.sdk.ISNAdView.ViewVisibilityParameters.1
        {
            put(ISNAdViewConstants.IS_VISIBLE_KEY, Boolean.valueOf(ViewVisibilityParameters.this.mVisibility == 0));
            put(ISNAdViewConstants.IS_WINDOW_VISIBLE_KEY, Boolean.valueOf(ViewVisibilityParameters.this.mWindowVisibility == 0));
            put(ISNAdViewConstants.IS_SHOWN_KEY, false);
            put(ISNAdViewConstants.IS_VIEW_VISIBLE, false);
        }
    };

    ViewVisibilityParameters() {
    }

    void updateViewVisibilityParameters(String str, int i, boolean z) {
        if (this.mVisibilityParametersMap.containsKey(str)) {
            this.mVisibilityParametersMap.put(str, Boolean.valueOf(i == 0));
        }
        this.mVisibilityParametersMap.put(ISNAdViewConstants.IS_SHOWN_KEY, Boolean.valueOf(z));
        this.mVisibilityParametersMap.put(ISNAdViewConstants.IS_VIEW_VISIBLE, Boolean.valueOf((this.mVisibilityParametersMap.get(ISNAdViewConstants.IS_WINDOW_VISIBLE_KEY).booleanValue() || this.mVisibilityParametersMap.get(ISNAdViewConstants.IS_VISIBLE_KEY).booleanValue()) && this.mVisibilityParametersMap.get(ISNAdViewConstants.IS_SHOWN_KEY).booleanValue()));
    }

    JSONObject collectVisibilityParameters() {
        return new JSONObject(this.mVisibilityParametersMap);
    }
}
