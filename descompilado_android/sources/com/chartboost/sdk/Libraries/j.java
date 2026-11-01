package com.chartboost.sdk.Libraries;

import android.app.Activity;
import com.chartboost.sdk.impl.aq;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public final class j extends WeakReference<Activity> {
    public final int a;

    public j(Activity activity) {
        super(activity);
        aq.a("WeakActivity.WeakActivity", activity);
        this.a = activity.hashCode();
    }

    public boolean a(Activity activity) {
        return activity != null && activity.hashCode() == this.a;
    }

    public int hashCode() {
        return this.a;
    }
}
