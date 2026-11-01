package com.my.target;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/* JADX INFO: compiled from: DeviceParamsDataProvider.java */
/* JADX INFO: loaded from: classes2.dex */
public final class bk extends bm {

    @NonNull
    private static final String gW = "Android";
    private boolean gX = false;

    @NonNull
    private String gY = "";

    @NonNull
    private String gZ = "";

    @NonNull
    private String ha = "";

    @NonNull
    private String hb = "";

    @NonNull
    private String hc = "";

    @NonNull
    private String hd = "";

    @NonNull
    private String he = "";

    @NonNull
    private String hf = "";

    @NonNull
    private String hg = "";

    @NonNull
    private String hh = "";

    @NonNull
    private String hi = "";
    private int width = 0;
    private int height = 0;
    private int hj = 0;
    private float density = 0.0f;

    @NonNull
    private String hk = "";

    @NonNull
    private String hl = "";

    @NonNull
    private String hm = "";

    @NonNull
    private String timezone = "";

    @NonNull
    private String hn = "";

    @NonNull
    public String ax() {
        return this.gY;
    }

    @NonNull
    public String getOsVersion() {
        return this.ha;
    }

    @NonNull
    public String ay() {
        return this.hb;
    }

    @NonNull
    public String az() {
        return this.hc;
    }

    @NonNull
    public String aA() {
        return this.he;
    }

    @NonNull
    public String aB() {
        return this.hf;
    }

    @NonNull
    public String getLang() {
        return this.hg;
    }

    @NonNull
    public String aC() {
        return this.hh;
    }

    @NonNull
    public String getCountryCode() {
        return this.hi;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int aD() {
        return this.hj;
    }

    public float aE() {
        return this.density;
    }

    @NonNull
    public String aF() {
        return this.hk;
    }

    @NonNull
    public String aG() {
        return this.hm;
    }

    @NonNull
    public String aH() {
        return this.gZ;
    }

    @NonNull
    public String aI() {
        return this.hl;
    }

    @NonNull
    public String aJ() {
        return this.timezone;
    }

    @NonNull
    public String aK() {
        return this.hn;
    }

    @Override // com.my.target.bm
    @SuppressLint({"HardwareIds"})
    @WorkerThread
    public synchronized void collectData(@NonNull Context context) {
        if (this.gX) {
            return;
        }
        g.a("collect application info...");
        h.b(new Runnable() { // from class: com.my.target.bk.1
            @Override // java.lang.Runnable
            public void run() throws Throwable {
                bk.this.addParam(i.ad, String.valueOf(bk.this.aL() ? 1 : 0));
            }
        });
        this.gY = Build.DEVICE;
        this.he = Build.MANUFACTURER;
        this.hf = Build.MODEL;
        this.ha = Build.VERSION.RELEASE;
        this.hb = context.getPackageName();
        this.hg = Locale.getDefault().getLanguage();
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(this.hb, 0);
            this.hc = packageInfo.versionName;
            this.hd = Integer.toString(packageInfo.versionCode);
        } catch (PackageManager.NameNotFoundException unused) {
        }
        ContentResolver contentResolver = context.getContentResolver();
        if (contentResolver != null) {
            this.gZ = Settings.Secure.getString(contentResolver, i.ANDROID_ID);
            if (this.gZ == null) {
                this.gZ = "";
            }
        }
        this.hh = context.getResources().getConfiguration().locale.getLanguage();
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager != null) {
            this.hl = telephonyManager.getNetworkOperatorName();
            if (telephonyManager.getSimState() == 5) {
                this.hm = telephonyManager.getSimOperator();
            }
            String networkOperator = telephonyManager.getNetworkOperator();
            if (!TextUtils.isEmpty(networkOperator) && networkOperator.length() > 3) {
                this.hk = networkOperator.substring(3);
                this.hi = networkOperator.substring(0, 3);
            } else {
                this.hk = networkOperator;
            }
        }
        h(context);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.hj = displayMetrics.densityDpi;
        this.density = displayMetrics.density;
        TimeZone timeZone = TimeZone.getDefault();
        this.timezone = timeZone.getDisplayName(false, 0) + " " + timeZone.getID();
        String strI = i(context);
        if (strI != null) {
            this.hn = strI;
        }
        addParam(i.ANDROID_ID, this.gZ);
        addParam("device", this.gY);
        addParam("os", "Android");
        addParam(i.B, this.he);
        addParam("osver", this.ha);
        addParam("app", this.hb);
        addParam(i.E, this.hc);
        addParam(i.F, this.hd);
        addParam(i.G, this.hg);
        addParam(i.J, this.hh);
        addParam(i.L, this.hi);
        addParam(i.M, this.hf);
        addParam(i.WIDTH, "" + this.width);
        addParam(i.HEIGHT, "" + this.height);
        addParam(i.N, "" + this.hj);
        addParam(i.O, "" + this.density);
        addParam(i.P, this.hk);
        addParam(i.Q, this.hl);
        addParam(i.R, this.hm);
        addParam(i.K, this.timezone);
        addParam(i.af, this.hn);
        for (Map.Entry<String, String> entry : getMap().entrySet()) {
            g.a(entry.getKey() + " = " + entry.getValue());
        }
        this.gX = true;
        g.a("collected");
    }

    @NonNull
    public String g(@NonNull Context context) {
        Account[] accountsByType;
        try {
            accountsByType = AccountManager.get(context).getAccountsByType("com.google");
        } catch (Throwable unused) {
            accountsByType = null;
        }
        return (accountsByType == null || accountsByType.length <= 0) ? "" : accountsByType[0].name;
    }

    private void h(@NonNull Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (windowManager == null) {
            return;
        }
        Display defaultDisplay = windowManager.getDefaultDisplay();
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= 17) {
            defaultDisplay.getRealSize(point);
        } else {
            defaultDisplay.getSize(point);
        }
        this.width = point.x;
        this.height = point.y;
    }

    @Nullable
    private String i(@NonNull Context context) {
        ch chVarW;
        String strP = null;
        try {
            chVarW = ch.w(context);
            try {
                strP = chVarW.bn();
            } catch (Throwable th) {
                th = th;
                g.a("PreferencesManager error");
                th.printStackTrace();
            }
        } catch (Throwable th2) {
            th = th2;
            chVarW = null;
        }
        if (TextUtils.isEmpty(strP)) {
            strP = ce.P(this.gZ + Build.SERIAL + (j(context) ? g(context) : ""));
            if (chVarW != null) {
                chVarW.Q(strP);
            }
        }
        return strP;
    }

    private boolean j(@NonNull Context context) {
        int iCheckCallingOrSelfPermission;
        try {
            iCheckCallingOrSelfPermission = context.checkCallingOrSelfPermission("android.permission.GET_ACCOUNTS");
        } catch (Throwable th) {
            g.a("unable to check android.permission.GET_ACCOUNTS permission! Unexpected throwable in Context.checkCallingOrSelfPermission() method: " + th.getMessage());
            iCheckCallingOrSelfPermission = -1;
        }
        return iCheckCallingOrSelfPermission == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:75:0x00cd A[EXC_TOP_SPLITTER, PHI: r6
  0x00cd: PHI (r6v8 java.lang.Process) = (r6v6 java.lang.Process), (r6v9 java.lang.Process) binds: [B:57:0x00da, B:48:0x00cb] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:90:0x00dd A[SYNTHETIC] */
    public boolean aL() throws Throwable {
        Process processExec;
        BufferedReader bufferedReader;
        Throwable th;
        String str = Build.TAGS;
        boolean z = str != null && str.contains("test-keys");
        if (!z) {
            for (String str2 : new String[]{"/system/app/Superuser.apk", "/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su", "/su/bin/su"}) {
                if (new File(str2).exists()) {
                    z = true;
                    break;
                }
            }
        }
        if (z) {
            return z;
        }
        Runtime runtime = Runtime.getRuntime();
        for (String str3 : new String[]{"/system/xbin/which su", "/system/bin/which su", "which su"}) {
            try {
                processExec = runtime.exec(str3);
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(processExec.getInputStream()));
                    try {
                        StringBuilder sb = new StringBuilder();
                        while (true) {
                            String line = bufferedReader.readLine();
                            if (line == null) {
                                break;
                            }
                            sb.append(line);
                        }
                        processExec.destroy();
                        if (!TextUtils.isEmpty(sb.toString())) {
                            try {
                                bufferedReader.close();
                            } catch (Exception unused) {
                            }
                            if (processExec != null) {
                                try {
                                    processExec.destroy();
                                } catch (Exception unused2) {
                                }
                            }
                            return true;
                        }
                    } catch (Exception unused3) {
                        if (bufferedReader != null) {
                        }
                        if (processExec != null) {
                            try {
                                processExec.destroy();
                            } catch (Exception unused4) {
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (Exception unused5) {
                            }
                        }
                        try {
                            throw th;
                        } catch (Exception unused6) {
                            if (processExec != null) {
                                processExec.destroy();
                            }
                        }
                    }
                } catch (Exception unused7) {
                    bufferedReader = null;
                } catch (Throwable th3) {
                    bufferedReader = null;
                    th = th3;
                }
                try {
                    try {
                        bufferedReader.close();
                    } catch (Throwable th4) {
                        th = th4;
                        if (processExec != null) {
                            try {
                                processExec.destroy();
                            } catch (Exception unused8) {
                            }
                        }
                        throw th;
                    }
                } catch (Exception unused9) {
                }
                if (processExec != null) {
                    processExec.destroy();
                }
            } catch (Exception unused10) {
                processExec = null;
            } catch (Throwable th5) {
                th = th5;
                processExec = null;
            }
        }
        return z;
    }
}
