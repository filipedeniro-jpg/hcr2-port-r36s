package com.facebook.marketing.internal;

import android.util.Log;
import android.view.View;
import androidx.annotation.Nullable;
import com.facebook.FacebookSdk;
import com.facebook.appevents.codeless.CodelessLoggingEventListener;
import com.facebook.appevents.codeless.internal.ViewHierarchy;

/* JADX INFO: loaded from: classes.dex */
public class ButtonIndexingEventListener {
    private static final String TAG = "com.facebook.marketing.internal.ButtonIndexingEventListener";

    public static ButtonIndexingAccessibilityDelegate getAccessibilityDelegate(View view, String str) {
        return new ButtonIndexingAccessibilityDelegate(view, str);
    }

    public static class ButtonIndexingAccessibilityDelegate extends CodelessLoggingEventListener.AutoLoggingAccessibilityDelegate {

        @Nullable
        private View.AccessibilityDelegate existingDelegate;
        private String mapKey;

        public ButtonIndexingAccessibilityDelegate(View view, String str) {
            if (view == null) {
                return;
            }
            this.existingDelegate = ViewHierarchy.getExistingDelegate(view);
            this.mapKey = str;
            this.supportButtonIndexing = true;
        }

        @Override // com.facebook.appevents.codeless.CodelessLoggingEventListener.AutoLoggingAccessibilityDelegate, android.view.View.AccessibilityDelegate
        public void sendAccessibilityEvent(final View view, int i) {
            if (i == -1) {
                Log.e(ButtonIndexingEventListener.TAG, "Unsupported action type");
            }
            View.AccessibilityDelegate accessibilityDelegate = this.existingDelegate;
            if (accessibilityDelegate != null && !(accessibilityDelegate instanceof ButtonIndexingAccessibilityDelegate)) {
                accessibilityDelegate.sendAccessibilityEvent(view, i);
            }
            final String str = this.mapKey;
            FacebookSdk.getExecutor().execute(new Runnable() { // from class: com.facebook.marketing.internal.ButtonIndexingEventListener.ButtonIndexingAccessibilityDelegate.1
                @Override // java.lang.Runnable
                public void run() {
                    ButtonIndexingLogger.logIndexing(FacebookSdk.getApplicationId(), view, str, FacebookSdk.getApplicationContext());
                }
            });
        }
    }
}
