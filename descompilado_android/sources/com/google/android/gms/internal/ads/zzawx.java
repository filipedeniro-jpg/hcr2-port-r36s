package com.google.android.gms.internal.ads;

import com.chartboost.sdk.CBLocation;
import com.facebook.appevents.AppEventsConstants;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzawx {
    public static final zzdcs zzdvx = zza(new ThreadPoolExecutor(2, Integer.MAX_VALUE, 10, TimeUnit.SECONDS, new SynchronousQueue(), zzex(CBLocation.LOCATION_DEFAULT)));
    public static final zzdcs zzdvy;
    public static final ScheduledExecutorService zzdvz;
    public static final zzdcs zzdwa;
    public static final zzdcs zzdwb;

    private static ThreadFactory zzex(String str) {
        return new zzaww(str);
    }

    private static zzdcs zza(Executor executor) {
        return new zzawy(executor, null);
    }

    static {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5, 10L, TimeUnit.SECONDS, new LinkedBlockingQueue(), zzex("Loader"));
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        zzdvy = zza(threadPoolExecutor);
        zzdvz = new ScheduledThreadPoolExecutor(3, zzex(AppEventsConstants.EVENT_NAME_SCHEDULE));
        zzdwa = zza(new zzawz());
        zzdwb = zza(zzdcr.zzapf());
    }
}
