package com.google.firebase.abt.component;

import android.content.Context;
import androidx.annotation.GuardedBy;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.firebase.abt.FirebaseABTesting;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
@KeepForSdk
public class AbtComponent {

    @GuardedBy("this")
    private final Map<String, FirebaseABTesting> zzh = new HashMap();
    private final Context zzi;
    private final AnalyticsConnector zzj;

    @VisibleForTesting(otherwise = 3)
    protected AbtComponent(Context context, AnalyticsConnector analyticsConnector) {
        this.zzi = context;
        this.zzj = analyticsConnector;
    }

    @KeepForSdk
    public synchronized FirebaseABTesting get(String str) {
        if (!this.zzh.containsKey(str)) {
            this.zzh.put(str, new FirebaseABTesting(this.zzi, this.zzj, str));
        }
        return this.zzh.get(str);
    }
}
