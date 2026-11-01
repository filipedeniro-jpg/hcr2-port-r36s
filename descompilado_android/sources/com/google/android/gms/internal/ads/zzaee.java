package com.google.android.gms.internal.ads;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.drive.DriveFile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
@VisibleForTesting
public final class zzaee {
    private final View view;
    private final zzdf zzbln;
    private final Context zzlk;

    public zzaee(Context context, zzdf zzdfVar, View view) {
        this.zzlk = context;
        this.zzbln = zzdfVar;
        this.view = view;
    }

    private static Intent zzf(Uri uri) {
        if (uri == null) {
            return null;
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(DriveFile.MODE_READ_ONLY);
        intent.setData(uri);
        intent.setAction("android.intent.action.VIEW");
        return intent;
    }

    @VisibleForTesting
    private final ResolveInfo zzb(Intent intent) {
        return zza(intent, new ArrayList<>());
    }

    @VisibleForTesting
    private final ResolveInfo zza(Intent intent, ArrayList<ResolveInfo> arrayList) {
        ResolveInfo resolveInfo = null;
        try {
            PackageManager packageManager = this.zzlk.getPackageManager();
            if (packageManager == null) {
                return null;
            }
            List<ResolveInfo> listQueryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
            ResolveInfo resolveInfoResolveActivity = packageManager.resolveActivity(intent, 65536);
            if (listQueryIntentActivities != null && resolveInfoResolveActivity != null) {
                for (int i = 0; i < listQueryIntentActivities.size(); i++) {
                    ResolveInfo resolveInfo2 = listQueryIntentActivities.get(i);
                    if (resolveInfoResolveActivity != null && resolveInfoResolveActivity.activityInfo.name.equals(resolveInfo2.activityInfo.name)) {
                        resolveInfo = resolveInfoResolveActivity;
                        break;
                    }
                }
            }
            arrayList.addAll(listQueryIntentActivities);
        } catch (Throwable th) {
            com.google.android.gms.ads.internal.zzp.zzkc().zza(th, "OpenSystemBrowserHandler.getDefaultBrowserResolverForIntent");
        }
        return resolveInfo;
    }

    private static Intent zza(Intent intent, ResolveInfo resolveInfo) {
        Intent intent2 = new Intent(intent);
        intent2.setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
        return intent2;
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0064  */
    @VisibleForTesting
    public final Intent zze(Map<String, String> map) {
        boolean z;
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        ResolveInfo resolveInfoZzb;
        ActivityManager activityManager = (ActivityManager) this.zzlk.getSystemService("activity");
        String str = map.get("u");
        Uri uriBuild = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Uri uri = Uri.parse(zzaeb.zza(this.zzlk, this.zzbln, str, this.view, null));
        boolean z2 = Boolean.parseBoolean(map.get("use_first_package"));
        boolean z3 = Boolean.parseBoolean(map.get("use_running_process"));
        if (Boolean.parseBoolean(map.get("use_custom_tabs"))) {
            z = true;
        } else if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcpq)).booleanValue()) {
            z = true;
        } else {
            z = false;
        }
        if ("http".equalsIgnoreCase(uri.getScheme())) {
            uriBuild = uri.buildUpon().scheme("https").build();
        } else if ("https".equalsIgnoreCase(uri.getScheme())) {
            uriBuild = uri.buildUpon().scheme("http").build();
        }
        ArrayList<ResolveInfo> arrayList = new ArrayList<>();
        Intent intentZzf = zzf(uri);
        Intent intentZzf2 = zzf(uriBuild);
        if (z) {
            com.google.android.gms.ads.internal.zzp.zzjy();
            zzatv.zzb(this.zzlk, intentZzf);
            com.google.android.gms.ads.internal.zzp.zzjy();
            zzatv.zzb(this.zzlk, intentZzf2);
        }
        ResolveInfo resolveInfoZza = zza(intentZzf, arrayList);
        if (resolveInfoZza != null) {
            return zza(intentZzf, resolveInfoZza);
        }
        if (intentZzf2 != null && (resolveInfoZzb = zzb(intentZzf2)) != null) {
            Intent intentZza = zza(intentZzf, resolveInfoZzb);
            if (zzb(intentZza) != null) {
                return intentZza;
            }
        }
        if (arrayList.size() == 0) {
            return intentZzf;
        }
        if (z3 && activityManager != null && (runningAppProcesses = activityManager.getRunningAppProcesses()) != null) {
            ArrayList<ResolveInfo> arrayList2 = arrayList;
            int size = arrayList2.size();
            int i = 0;
            while (i < size) {
                ResolveInfo resolveInfo = arrayList2.get(i);
                i++;
                ResolveInfo resolveInfo2 = resolveInfo;
                Iterator<ActivityManager.RunningAppProcessInfo> it = runningAppProcesses.iterator();
                while (it.hasNext()) {
                    if (it.next().processName.equals(resolveInfo2.activityInfo.packageName)) {
                        return zza(intentZzf, resolveInfo2);
                    }
                }
            }
        }
        return z2 ? zza(intentZzf, arrayList.get(0)) : intentZzf;
    }
}
