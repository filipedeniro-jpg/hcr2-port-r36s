package com.integralads.avid.library.inmobi.activity;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.Window;
import androidx.annotation.VisibleForTesting;
import com.integralads.avid.library.inmobi.weakreference.AvidActivity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class AvidActivityStack {
    private static AvidActivityStack avidActivityStackInstance = new AvidActivityStack();
    private final ArrayList<AvidActivity> activities = new ArrayList<>();

    public static AvidActivityStack getInstance() {
        return avidActivityStackInstance;
    }

    public void addActivity(Activity activity) {
        if (find(activity) == null) {
            this.activities.add(new AvidActivity(activity));
        }
    }

    public List<View> getRootViews() {
        ArrayList arrayList = new ArrayList();
        Iterator<AvidActivity> it = this.activities.iterator();
        View view = null;
        while (it.hasNext()) {
            AvidActivity next = it.next();
            if (isFinished(next)) {
                it.remove();
            } else {
                View rootView = getRootView(next);
                if (rootView != null) {
                    view = rootView;
                }
            }
        }
        if (view != null) {
            arrayList.add(view);
        }
        return arrayList;
    }

    public void cleanup() {
        this.activities.clear();
    }

    @VisibleForTesting
    AvidActivity find(Activity activity) {
        for (AvidActivity avidActivity : this.activities) {
            if (avidActivity.contains(activity)) {
                return avidActivity;
            }
        }
        return null;
    }

    @VisibleForTesting
    List<AvidActivity> getActivities() {
        return this.activities;
    }

    @VisibleForTesting
    boolean isFinished(AvidActivity avidActivity) {
        Activity activity = avidActivity.get();
        if (activity == null) {
            return true;
        }
        if (Build.VERSION.SDK_INT >= 17) {
            return activity.isDestroyed();
        }
        return activity.isFinishing();
    }

    @VisibleForTesting
    private View getRootView(AvidActivity avidActivity) {
        Window window;
        View decorView;
        Activity activity = avidActivity.get();
        if (activity == null || (window = activity.getWindow()) == null || !activity.hasWindowFocus() || (decorView = window.getDecorView()) == null || !decorView.isShown()) {
            return null;
        }
        return decorView;
    }

    @VisibleForTesting
    static void setAvidActivityStackInstance(AvidActivityStack avidActivityStack) {
        avidActivityStackInstance = avidActivityStack;
    }
}
