package com.facebook.marketing.internal;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.codeless.CodelessLoggingEventListener;
import com.facebook.appevents.codeless.internal.ViewHierarchy;
import com.facebook.internal.Utility;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ButtonIndexer {
    private static final String TAG = "com.facebook.marketing.internal.ButtonIndexer";
    private final Handler uiThreadHandler = new Handler(Looper.getMainLooper());
    private Set<Activity> activitiesSet = new HashSet();
    private Set<ViewProcessor> viewProcessors = new HashSet();
    private HashSet<String> delegateSet = new HashSet<>();

    public void add(Activity activity) {
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            throw new FacebookException("Can't add activity to ButtonIndexer on non-UI thread");
        }
        this.activitiesSet.add(activity);
        this.delegateSet.clear();
        startTracking();
    }

    public void remove(Activity activity) {
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            throw new FacebookException("Can't remove activity from ButtonIndexer on non-UI thread");
        }
        this.activitiesSet.remove(activity);
        this.viewProcessors.clear();
        this.delegateSet.clear();
    }

    private void startTracking() {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            processViews();
        } else {
            this.uiThreadHandler.post(new Runnable() { // from class: com.facebook.marketing.internal.ButtonIndexer.1
                @Override // java.lang.Runnable
                public void run() {
                    ButtonIndexer.this.processViews();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processViews() {
        for (Activity activity : this.activitiesSet) {
            this.viewProcessors.add(new ViewProcessor(activity.getWindow().getDecorView().getRootView(), activity.getClass().getSimpleName(), this.delegateSet, this.uiThreadHandler));
        }
    }

    protected static class ViewProcessor implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener, Runnable {
        private final String activityName;
        private HashSet<String> delegateSet;
        private final Handler handler;
        private WeakReference<View> rootView;
        public static volatile Set<String> loadedKeySet = new HashSet();
        private static volatile float displayDensity = -1.0f;
        private final String viewPlaceholder = "{\"classname\": \"placeholder\", \"id\": 1}";
        private HashMap<String, WeakReference<View>> viewMap = new HashMap<>();

        public ViewProcessor(View view, String str, HashSet<String> hashSet, Handler handler) {
            this.rootView = new WeakReference<>(view);
            this.handler = handler;
            this.activityName = str;
            this.delegateSet = hashSet;
            if (displayDensity < 0.0f) {
                displayDensity = view.getContext().getResources().getDisplayMetrics().density;
            }
            this.handler.postDelayed(this, 200L);
        }

        @Override // java.lang.Runnable
        public void run() {
            RemoteConfig remoteConfigWithoutQuery = RemoteConfigManager.getRemoteConfigWithoutQuery(FacebookSdk.getApplicationId());
            if (remoteConfigWithoutQuery == null || !remoteConfigWithoutQuery.getEnableButtonIndexing()) {
                return;
            }
            process();
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            process();
        }

        @Override // android.view.ViewTreeObserver.OnScrollChangedListener
        public void onScrollChanged() {
            process();
        }

        private void process() {
            View view = this.rootView.get();
            if (view != null) {
                attachListeners(view);
            }
        }

        public void attachListeners(View view) {
            JSONObject clickableElementsOfView = getClickableElementsOfView(view, -1, this.activityName, false);
            if (clickableElementsOfView != null) {
                ButtonIndexingLogger.logAllIndexing(clickableElementsOfView, this.activityName);
            }
            for (Map.Entry<String, WeakReference<View>> entry : this.viewMap.entrySet()) {
                attachListener(entry.getValue().get(), entry.getKey());
            }
        }

        @Nullable
        public JSONObject getClickableElementsOfView(View view, int i, String str, boolean z) {
            String str2 = str + "." + String.valueOf(i);
            if (view == null) {
                return null;
            }
            JSONObject jSONObject = new JSONObject();
            try {
                boolean z2 = ViewHierarchy.isClickableView(view) || (view instanceof Button);
                if (z2) {
                    this.viewMap.put(str2, new WeakReference<>(view));
                }
                if ((!(view instanceof TextView) && !(view instanceof ImageView)) || (!z && !z2)) {
                    JSONArray jSONArray = new JSONArray();
                    if (view instanceof ViewGroup) {
                        ViewGroup viewGroup = (ViewGroup) view;
                        int childCount = viewGroup.getChildCount();
                        int i2 = 0;
                        for (int i3 = 0; i3 < childCount; i3++) {
                            View childAt = viewGroup.getChildAt(i3);
                            if (childAt.getVisibility() == 0) {
                                int i4 = i2 + 1;
                                JSONObject clickableElementsOfView = getClickableElementsOfView(childAt, i2, str2, z || z2);
                                if (clickableElementsOfView != null) {
                                    jSONArray.put(clickableElementsOfView);
                                } else {
                                    getClass();
                                    jSONArray.put(new JSONObject("{\"classname\": \"placeholder\", \"id\": 1}"));
                                }
                                i2 = i4;
                            }
                        }
                    }
                    if (jSONArray.length() > 0) {
                        JSONObject basicInfoOfView = ViewHierarchy.setBasicInfoOfView(view, jSONObject);
                        basicInfoOfView.put("childviews", jSONArray);
                        return basicInfoOfView;
                    }
                    return null;
                }
                if (loadedKeySet.contains(str2)) {
                    return null;
                }
                loadedKeySet.add(str2);
                return ViewHierarchy.setAppearanceOfView(view, ViewHierarchy.setBasicInfoOfView(view, jSONObject), displayDensity);
            } catch (JSONException e) {
                Utility.logd(ButtonIndexer.TAG, e);
            }
        }

        private void attachListener(View view, String str) {
            if (view == null) {
                return;
            }
            try {
                View.AccessibilityDelegate existingDelegate = ViewHierarchy.getExistingDelegate(view);
                boolean z = true;
                boolean z2 = existingDelegate != null;
                boolean z3 = z2 && (existingDelegate instanceof CodelessLoggingEventListener.AutoLoggingAccessibilityDelegate);
                if (!z3 || !((CodelessLoggingEventListener.AutoLoggingAccessibilityDelegate) existingDelegate).getSupportButtonIndexing()) {
                    z = false;
                }
                if (this.delegateSet.contains(str)) {
                    return;
                }
                if (z2 && z3 && z) {
                    return;
                }
                view.setAccessibilityDelegate(ButtonIndexingEventListener.getAccessibilityDelegate(view, str));
                this.delegateSet.add(str);
            } catch (FacebookException e) {
                Log.e(ButtonIndexer.TAG, "Failed to attach auto logging event listener.", e);
            }
        }
    }
}
