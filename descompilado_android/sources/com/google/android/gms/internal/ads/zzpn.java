package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzpn implements zzps {
    private final /* synthetic */ Activity val$activity;
    private final /* synthetic */ Bundle zzxg;

    zzpn(zzpk zzpkVar, Activity activity, Bundle bundle) {
        this.val$activity = activity;
        this.zzxg = bundle;
    }

    @Override // com.google.android.gms.internal.ads.zzps
    public final void zza(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        activityLifecycleCallbacks.onActivityCreated(this.val$activity, this.zzxg);
    }
}
