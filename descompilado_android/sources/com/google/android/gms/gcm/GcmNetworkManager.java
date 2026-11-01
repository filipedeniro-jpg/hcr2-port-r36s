package com.google.android.gms.gcm;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.gms.internal.gcm.zzq;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: loaded from: classes.dex */
public class GcmNetworkManager {
    public static final int RESULT_FAILURE = 2;
    public static final int RESULT_RESCHEDULE = 1;
    public static final int RESULT_SUCCESS = 0;

    @GuardedBy("GcmNetworkManager.class")
    private static GcmNetworkManager zzh;
    private final Context zzi;

    @GuardedBy("this")
    private final Map<String, Map<String, Boolean>> zzj = new ArrayMap();

    public static GcmNetworkManager getInstance(Context context) {
        GcmNetworkManager gcmNetworkManager;
        synchronized (GcmNetworkManager.class) {
            if (zzh == null) {
                zzh = new GcmNetworkManager(context.getApplicationContext());
            }
            gcmNetworkManager = zzh;
        }
        return gcmNetworkManager;
    }

    private GcmNetworkManager(Context context) {
        this.zzi = context;
    }

    @NonNull
    private final zzn zze() {
        if (GoogleCloudMessaging.zzf(this.zzi) < 5000000) {
            Log.e("GcmNetworkManager", "Google Play services is not available, dropping all GcmNetworkManager requests");
            return new zzo();
        }
        return new zzm(this.zzi);
    }

    @WorkerThread
    public synchronized void schedule(Task task) {
        Map<String, Boolean> map;
        String strValueOf = String.valueOf(task.getTag());
        zzp zzpVar = new zzp(strValueOf.length() != 0 ? "nts:client:schedule:".concat(strValueOf) : new String("nts:client:schedule:"));
        Throwable th = null;
        try {
            try {
                zze(task.getServiceName());
                if (zze().zzd(task) && (map = this.zzj.get(task.getServiceName())) != null && map.containsKey(task.getTag())) {
                    map.put(task.getTag(), true);
                }
                zzd((Throwable) null, zzpVar);
            } catch (Throwable th2) {
                th = th2;
                throw th;
            }
        } catch (Throwable th3) {
            zzd(th, zzpVar);
            throw th3;
        }
    }

    @WorkerThread
    public void cancelTask(String str, Class<? extends GcmTaskService> cls) {
        ComponentName componentName = new ComponentName(this.zzi, cls);
        String strValueOf = String.valueOf(str);
        zzp zzpVar = new zzp(strValueOf.length() != 0 ? "nts:client:cancel:".concat(strValueOf) : new String("nts:client:cancel:"));
        Throwable th = null;
        try {
            try {
                zzd(str);
                zze(componentName.getClassName());
                zze().zzd(componentName, str);
                zzd((Throwable) null, zzpVar);
            } catch (Throwable th2) {
                th = th2;
                throw th;
            }
        } catch (Throwable th3) {
            zzd(th, zzpVar);
            throw th3;
        }
    }

    @WorkerThread
    public void cancelAllTasks(Class<? extends GcmTaskService> cls) {
        ComponentName componentName = new ComponentName(this.zzi, cls);
        zzp zzpVar = new zzp("nts:client:cancelAll");
        try {
            zze(componentName.getClassName());
            zze().zzd(componentName);
            zzd((Throwable) null, zzpVar);
        } catch (Throwable th) {
            zzd((Throwable) null, zzpVar);
            throw th;
        }
    }

    static void zzd(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Must provide a valid tag.");
        }
        if (100 < str.length()) {
            throw new IllegalArgumentException("Tag is larger than max permissible tag length (100)");
        }
    }

    private final boolean zze(String str) {
        Intent className;
        List<ResolveInfo> listQueryIntentServices;
        Preconditions.checkNotNull(str, "GcmTaskService must not be null.");
        PackageManager packageManager = this.zzi.getPackageManager();
        if (packageManager == null) {
            listQueryIntentServices = Collections.emptyList();
        } else {
            if (str != null) {
                className = new Intent(GcmTaskService.SERVICE_ACTION_EXECUTE_TASK).setClassName(this.zzi, str);
            } else {
                className = new Intent(GcmTaskService.SERVICE_ACTION_EXECUTE_TASK).setPackage(this.zzi.getPackageName());
            }
            listQueryIntentServices = packageManager.queryIntentServices(className, 0);
        }
        if (CollectionUtils.isEmpty(listQueryIntentServices)) {
            Log.e("GcmNetworkManager", String.valueOf(str).concat(" is not available. This may cause the task to be lost."));
            return true;
        }
        for (ResolveInfo resolveInfo : listQueryIntentServices) {
            if (resolveInfo.serviceInfo != null && resolveInfo.serviceInfo.enabled) {
                return true;
            }
        }
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 118);
        sb.append("The GcmTaskService class you provided ");
        sb.append(str);
        sb.append(" does not seem to support receiving com.google.android.gms.gcm.ACTION_TASK_READY");
        throw new IllegalArgumentException(sb.toString());
    }

    final synchronized boolean zzd(String str, String str2) {
        Map<String, Boolean> arrayMap = this.zzj.get(str2);
        if (arrayMap == null) {
            arrayMap = new ArrayMap<>();
            this.zzj.put(str2, arrayMap);
        }
        return arrayMap.put(str, false) == null;
    }

    final synchronized void zze(String str, String str2) {
        Map<String, Boolean> map = this.zzj.get(str2);
        if (map != null) {
            if ((map.remove(str) != null) && map.isEmpty()) {
                this.zzj.remove(str2);
            }
        }
    }

    final synchronized boolean zzf(String str, String str2) {
        Map<String, Boolean> map = this.zzj.get(str2);
        if (map == null) {
            return false;
        }
        Boolean bool = map.get(str);
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    final synchronized boolean zzf(String str) {
        return this.zzj.containsKey(str);
    }

    private static /* synthetic */ void zzd(Throwable th, zzp zzpVar) {
        if (th == null) {
            zzpVar.close();
            return;
        }
        try {
            zzpVar.close();
        } catch (Throwable th2) {
            zzq.zzd(th, th2);
        }
    }
}
