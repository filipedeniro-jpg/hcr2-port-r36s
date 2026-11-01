package com.amazon.device.ads;

import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.ironsource.sdk.constants.Constants;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
enum RelativePosition {
    TOP_LEFT,
    TOP_RIGHT,
    CENTER,
    BOTTOM_LEFT,
    BOTTOM_RIGHT,
    TOP_CENTER,
    BOTTOM_CENTER;

    private static final HashMap<String, RelativePosition> POSITIONS = new HashMap<>();

    static {
        POSITIONS.put(Constants.ForceClosePosition.TOP_LEFT, TOP_LEFT);
        POSITIONS.put(Constants.ForceClosePosition.TOP_RIGHT, TOP_RIGHT);
        POSITIONS.put("top-center", TOP_CENTER);
        POSITIONS.put(Constants.ForceClosePosition.BOTTOM_LEFT, BOTTOM_LEFT);
        POSITIONS.put(Constants.ForceClosePosition.BOTTOM_RIGHT, BOTTOM_RIGHT);
        POSITIONS.put("bottom-center", BOTTOM_CENTER);
        POSITIONS.put(TtmlNode.CENTER, CENTER);
    }

    public static RelativePosition fromString(String str) {
        return POSITIONS.get(str);
    }
}
