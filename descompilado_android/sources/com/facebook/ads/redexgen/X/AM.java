package com.facebook.ads.redexgen.X;

import android.support.annotation.RequiresApi;
import android.view.accessibility.AccessibilityNodeInfo;

/* JADX INFO: loaded from: assets/audience_network.dex */
@RequiresApi(21)
public class AM extends AL {
    @Override // com.facebook.ads.redexgen.X.AL, com.facebook.ads.redexgen.X.AH
    public final Object C(int rowIndex, int rowSpan, int columnIndex, int columnSpan, boolean heading, boolean selected) {
        return AccessibilityNodeInfo.CollectionItemInfo.obtain(rowIndex, rowSpan, columnIndex, columnSpan, heading, selected);
    }

    @Override // com.facebook.ads.redexgen.X.AL, com.facebook.ads.redexgen.X.AH
    public final Object D(int rowCount, int columnCount, boolean hierarchical, int selectionMode) {
        return AccessibilityNodeInfo.CollectionInfo.obtain(rowCount, columnCount, hierarchical, selectionMode);
    }
}
