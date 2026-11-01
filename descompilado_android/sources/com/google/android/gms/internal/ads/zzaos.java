package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.wrappers.Wrappers;
import java.util.Locale;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzaos {
    private float zzbod;
    private int zzdgc;
    private int zzdgd;
    private int zzdlz;
    private boolean zzdma;
    private boolean zzdmb;
    private int zzdmc;
    private int zzdmd;
    private int zzdme;
    private String zzdmf;
    private int zzdmg;
    private int zzdmh;
    private int zzdmi;
    private boolean zzdmj;
    private int zzdmk;
    private double zzdml;
    private boolean zzdmm;
    private String zzdmn;
    private String zzdmo;
    private boolean zzdmp;
    private boolean zzdmq;
    private String zzdmr;
    private boolean zzdms;
    private final boolean zzdmt;
    private boolean zzdmu;
    private String zzdmv;
    private String zzdmw;
    private String zzdmx;
    private boolean zzdmy;

    public zzaos(Context context) {
        DisplayMetrics displayMetrics;
        PackageManager packageManager = context.getPackageManager();
        zzs(context);
        zzt(context);
        zzu(context);
        Locale locale = Locale.getDefault();
        this.zzdmp = zza(packageManager, "geo:0,0?q=donuts") != null;
        this.zzdmq = zza(packageManager, "http://www.google.com") != null;
        this.zzdmr = locale.getCountry();
        zzuo.zzof();
        this.zzdms = zzawe.zzwb();
        this.zzdmt = DeviceProperties.isLatchsky(context);
        this.zzdmu = DeviceProperties.isSidewinder(context);
        this.zzdmv = locale.getLanguage();
        this.zzdmw = zza(context, packageManager);
        this.zzdmx = zzv(context);
        Resources resources = context.getResources();
        if (resources == null || (displayMetrics = resources.getDisplayMetrics()) == null) {
            return;
        }
        this.zzbod = displayMetrics.density;
        this.zzdgc = displayMetrics.widthPixels;
        this.zzdgd = displayMetrics.heightPixels;
    }

    public zzaos(Context context, zzaot zzaotVar) {
        zzs(context);
        zzt(context);
        zzu(context);
        this.zzdmn = Build.FINGERPRINT;
        this.zzdmo = Build.DEVICE;
        this.zzdmy = PlatformVersion.isAtLeastIceCreamSandwichMR1() && zzzr.zzj(context);
        this.zzdmp = zzaotVar.zzdmp;
        this.zzdmq = zzaotVar.zzdmq;
        this.zzdmr = zzaotVar.zzdmr;
        this.zzdms = zzaotVar.zzdms;
        this.zzdmt = zzaotVar.zzdmt;
        this.zzdmu = zzaotVar.zzdmu;
        this.zzdmv = zzaotVar.zzdmv;
        this.zzdmw = zzaotVar.zzdmw;
        this.zzdmx = zzaotVar.zzdmx;
        this.zzbod = zzaotVar.zzbod;
        this.zzdgc = zzaotVar.zzdgc;
        this.zzdgd = zzaotVar.zzdgd;
    }

    private final void zzs(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        if (audioManager != null) {
            try {
                this.zzdlz = audioManager.getMode();
                this.zzdma = audioManager.isMusicActive();
                this.zzdmb = audioManager.isSpeakerphoneOn();
                this.zzdmc = audioManager.getStreamVolume(3);
                this.zzdmd = audioManager.getRingerMode();
                this.zzdme = audioManager.getStreamVolume(2);
                return;
            } catch (Throwable th) {
                com.google.android.gms.ads.internal.zzp.zzkc().zza(th, "DeviceInfo.gatherAudioInfo");
            }
        }
        this.zzdlz = -2;
        this.zzdma = false;
        this.zzdmb = false;
        this.zzdmc = 0;
        this.zzdmd = 2;
        this.zzdme = 0;
    }

    @TargetApi(16)
    private final void zzt(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        this.zzdmf = telephonyManager.getNetworkOperator();
        this.zzdmh = telephonyManager.getNetworkType();
        this.zzdmi = telephonyManager.getPhoneType();
        this.zzdmg = -2;
        this.zzdmj = false;
        this.zzdmk = -1;
        com.google.android.gms.ads.internal.zzp.zzjy();
        if (zzatv.zzq(context, "android.permission.ACCESS_NETWORK_STATE")) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                this.zzdmg = activeNetworkInfo.getType();
                this.zzdmk = activeNetworkInfo.getDetailedState().ordinal();
            } else {
                this.zzdmg = -1;
            }
            if (Build.VERSION.SDK_INT >= 16) {
                this.zzdmj = connectivityManager.isActiveNetworkMetered();
            }
        }
    }

    private final void zzu(Context context) {
        Intent intentRegisterReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (intentRegisterReceiver != null) {
            int intExtra = intentRegisterReceiver.getIntExtra("status", -1);
            this.zzdml = intentRegisterReceiver.getIntExtra("level", -1) / intentRegisterReceiver.getIntExtra("scale", -1);
            this.zzdmm = intExtra == 2 || intExtra == 5;
        } else {
            this.zzdml = -1.0d;
            this.zzdmm = false;
        }
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
        try {
            return packageManager.resolveActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)), 65536);
        } catch (Throwable th) {
            com.google.android.gms.ads.internal.zzp.zzkc().zza(th, "DeviceInfo.getResolveInfo");
            return null;
        }
    }

    public final zzaot zzte() {
        return new zzaot(this.zzdlz, this.zzdmp, this.zzdmq, this.zzdmf, this.zzdmr, this.zzdms, this.zzdmt, this.zzdmu, this.zzdma, this.zzdmb, this.zzdmv, this.zzdmw, this.zzdmx, this.zzdmc, this.zzdmg, this.zzdmh, this.zzdmi, this.zzdmd, this.zzdme, this.zzbod, this.zzdgc, this.zzdgd, this.zzdml, this.zzdmm, this.zzdmj, this.zzdmk, this.zzdmn, this.zzdmy, this.zzdmo);
    }
}
