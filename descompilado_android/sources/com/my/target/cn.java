package com.my.target;

import android.graphics.Rect;
import android.view.View;
import androidx.annotation.NonNull;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

/* JADX INFO: compiled from: VisibilityChecker.java */
/* JADX INFO: loaded from: classes2.dex */
public class cn {
    private static final float kz = 0.5f;

    public static double a(@NonNull View view) {
        if (view.getVisibility() == 0 && view.getParent() != null && view.getAlpha() >= kz) {
            Rect rect = new Rect();
            if (view.getGlobalVisibleRect(rect)) {
                double dWidth = rect.width() * rect.height();
                double width = view.getWidth() * view.getHeight();
                Double.isNaN(width);
                Double.isNaN(dWidth);
                return dWidth / (width / 100.0d);
            }
        }
        return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }
}
