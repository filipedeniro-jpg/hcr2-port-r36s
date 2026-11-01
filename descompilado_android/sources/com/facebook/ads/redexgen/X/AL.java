package com.facebook.ads.redexgen.X;

import android.support.annotation.RequiresApi;
import android.view.accessibility.AccessibilityNodeInfo;

/* JADX INFO: loaded from: assets/audience_network.dex */
@RequiresApi(19)
public class AL extends AK {
    @Override // com.facebook.ads.redexgen.X.AH
    public final void A(AccessibilityNodeInfo info, Object collectionItemInfo) {
        info.setCollectionItemInfo((AccessibilityNodeInfo.CollectionItemInfo) collectionItemInfo);
    }

    @Override // com.facebook.ads.redexgen.X.AH
    public final void B(AccessibilityNodeInfo info, Object collectionInfo) {
        info.setCollectionInfo((AccessibilityNodeInfo.CollectionInfo) collectionInfo);
    }

    @Override // com.facebook.ads.redexgen.X.AH
    public Object C(int rowIndex, int rowSpan, int columnIndex, int columnSpan, boolean heading, boolean selected) {
        return AccessibilityNodeInfo.CollectionItemInfo.obtain(rowIndex, rowSpan, columnIndex, columnSpan, heading);
    }

    @Override // com.facebook.ads.redexgen.X.AH
    public Object D(int rowCount, int columnCount, boolean hierarchical, int selectionMode) {
        return AccessibilityNodeInfo.CollectionInfo.obtain(rowCount, columnCount, hierarchical);
    }
}
