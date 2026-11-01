package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.LocaleList;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.wrappers.Wrappers;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzcrg implements zzcra<zzcrh> {
    private final zzdcs zzfnq;
    private final Context zzlk;

    public zzcrg(zzdcs zzdcsVar, Context context) {
        this.zzfnq = zzdcsVar;
        this.zzlk = context;
    }

    @Override // com.google.android.gms.internal.ads.zzcra
    public final zzdcp<zzcrh> zzalr() {
        return this.zzfnq.submit(new Callable(this) { // from class: com.google.android.gms.internal.ads.zzcrj
            private final zzcrg zzggc;

            {
                this.zzggc = this;
            }

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.zzggc.zzame();
            }
        });
    }

    private static String zzv(Context context) {
        try {
            PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo("com.android.vending", 128);
            if (packageInfo != null) {
                int i = packageInfo.versionCode;
                String str = packageInfo.packageName;
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 12);
                sb.append(i);
                sb.append(".");
                sb.append(str);
                return sb.toString();
            }
        } catch (Exception unused) {
        }
        return null;
    }

    private static String zza(Context context, PackageManager packageManager) {
        ActivityInfo activityInfo;
        ResolveInfo resolveInfoZza = zza(packageManager, "market://details?id=com.google.android.gms.ads");
        if (resolveInfoZza == null || (activityInfo = resolveInfoZza.activityInfo) == null) {
            return null;
        }
        try {
            PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo(activityInfo.packageName, 0);
            if (packageInfo != null) {
                int i = packageInfo.versionCode;
                String str = activityInfo.packageName;
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 12);
                sb.append(i);
                sb.append(".");
                sb.append(str);
                return sb.toString();
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return null;
    }

    private static ResolveInfo zza(PackageManager packageManager, String str) {
        return packageManager.resolveActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)), 65536);
    }

    /* JADX WARN: Code duplicated, block: B:28:0x00bf  */
    final /* synthetic */ zzcrh zzame() throws Exception {
        boolean zEquals;
        PackageManager packageManager = this.zzlk.getPackageManager();
        Locale locale = Locale.getDefault();
        boolean z = zza(packageManager, "geo:0,0?q=donuts") != null;
        boolean z2 = zza(packageManager, "http://www.google.com") != null;
        String country = locale.getCountry();
        boolean zStartsWith = Build.DEVICE.startsWith(MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE);
        boolean zIsLatchsky = DeviceProperties.isLatchsky(this.zzlk);
        boolean zIsSidewinder = DeviceProperties.isSidewinder(this.zzlk);
        String language = locale.getLanguage();
        ArrayList arrayList = new ArrayList();
        if (Build.VERSION.SDK_INT >= 24) {
            LocaleList localeList = LocaleList.getDefault();
            for (int i = 0; i < localeList.size(); i++) {
                arrayList.add(localeList.get(i).getLanguage());
            }
        }
        String strZza = zza(this.zzlk, packageManager);
        String strZzv = zzv(this.zzlk);
        String str = Build.FINGERPRINT;
        Context context = this.zzlk;
        if (packageManager != null) {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.example.com"));
            ResolveInfo resolveInfoResolveActivity = packageManager.resolveActivity(intent, 0);
            List<ResolveInfo> listQueryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
            if (listQueryIntentActivities == null || resolveInfoResolveActivity == null) {
                zEquals = false;
            } else {
                for (int i2 = 0; i2 < listQueryIntentActivities.size(); i2++) {
                    if (resolveInfoResolveActivity.activityInfo.name.equals(listQueryIntentActivities.get(i2).activityInfo.name)) {
                        zEquals = resolveInfoResolveActivity.activityInfo.packageName.equals(zzdvu.zzca(context));
                    }
                }
                zEquals = false;
            }
        } else {
            zEquals = false;
        }
        return new zzcrh(z, z2, country, zStartsWith, zIsLatchsky, zIsSidewinder, language, arrayList, strZza, strZzv, str, zEquals, Build.MODEL, com.google.android.gms.ads.internal.zzp.zzka().zzvq());
    }
}
