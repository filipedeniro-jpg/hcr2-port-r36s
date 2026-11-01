package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.util.TypedValue;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.3x, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C02223x {
    private static final Object B = new Object();
    private static TypedValue C;

    public static final Drawable B(Context context, @DrawableRes int id) {
        int i;
        if (Build.VERSION.SDK_INT >= 21) {
            return context.getDrawable(id);
        }
        if (Build.VERSION.SDK_INT >= 16) {
            return context.getResources().getDrawable(id);
        }
        synchronized (B) {
            if (C == null) {
                C = new TypedValue();
            }
            context.getResources().getValue(id, C, true);
            i = C.resourceId;
        }
        return context.getResources().getDrawable(i);
    }
}
