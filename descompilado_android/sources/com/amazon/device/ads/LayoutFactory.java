package com.amazon.device.ads;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/* JADX INFO: loaded from: classes.dex */
class LayoutFactory {

    enum LayoutType {
        RELATIVE_LAYOUT,
        LINEAR_LAYOUT,
        FRAME_LAYOUT
    }

    LayoutFactory() {
    }

    /* JADX INFO: renamed from: com.amazon.device.ads.LayoutFactory$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$ads$LayoutFactory$LayoutType = new int[LayoutType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$device$ads$LayoutFactory$LayoutType[LayoutType.RELATIVE_LAYOUT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$LayoutFactory$LayoutType[LayoutType.FRAME_LAYOUT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$LayoutFactory$LayoutType[LayoutType.LINEAR_LAYOUT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public ViewGroup createLayout(Context context, LayoutType layoutType, String str) {
        ViewGroup relativeLayout;
        int i = AnonymousClass1.$SwitchMap$com$amazon$device$ads$LayoutFactory$LayoutType[layoutType.ordinal()];
        if (i == 1) {
            relativeLayout = new RelativeLayout(context);
        } else if (i == 2) {
            relativeLayout = new FrameLayout(context);
        } else {
            relativeLayout = new LinearLayout(context);
        }
        relativeLayout.setContentDescription(str);
        return relativeLayout;
    }
}
