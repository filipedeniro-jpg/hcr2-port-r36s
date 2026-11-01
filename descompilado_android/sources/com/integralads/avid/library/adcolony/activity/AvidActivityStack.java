package com.integralads.avid.library.adcolony.activity;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.Window;
import androidx.annotation.VisibleForTesting;
import com.integralads.avid.library.adcolony.weakreference.AvidActivity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class AvidActivityStack {
    private static AvidActivityStack a = new AvidActivityStack();
    private final ArrayList<AvidActivity> b = new ArrayList<>();

    public static AvidActivityStack getInstance() {
        return a;
    }

    public void addActivity(Activity activity) {
        if (a(activity) == null) {
            this.b.add(new AvidActivity(activity));
        }
    }

    public List<View> getRootViews() {
        ArrayList arrayList = new ArrayList();
        Iterator<AvidActivity> it = this.b.iterator();
        View view = null;
        while (it.hasNext()) {
            AvidActivity next = it.next();
            if (a(next)) {
                it.remove();
            } else {
                View viewB = b(next);
                if (viewB != null) {
                    view = viewB;
                }
            }
        }
        if (view != null) {
            arrayList.add(view);
        }
        return arrayList;
    }

    public void cleanup() {
        this.b.clear();
    }

    @VisibleForTesting
    AvidActivity a(Activity activity) {
        for (AvidActivity avidActivity : this.b) {
            if (avidActivity.contains(activity)) {
                return avidActivity;
            }
        }
        return null;
    }

    @VisibleForTesting
    List<AvidActivity> a() {
        return this.b;
    }

    @VisibleForTesting
    boolean a(AvidActivity avidActivity) {
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
    private View b(AvidActivity avidActivity) {
        Window window;
        Activity activity = avidActivity.get();
        if (activity == null || (window = activity.getWindow()) == null || !activity.hasWindowFocus()) {
            return null;
        }
        View decorView = window.getDecorView();
        if (decorView == null || !decorView.isShown()) {
            return null;
        }
        return decorView;
    }

    @VisibleForTesting
    static void a(AvidActivityStack avidActivityStack) {
        a = avidActivityStack;
    }
}
