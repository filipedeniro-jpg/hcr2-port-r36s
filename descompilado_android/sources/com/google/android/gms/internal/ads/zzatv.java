package com.google.android.gms.internal.ads;

import android.R;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.KeyguardManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.os.Process;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.browser.customtabs.CustomTabsIntent;
import com.amazon.device.ads.WebRequest;
import com.google.android.gms.common.util.CrashUtils;
import com.google.android.gms.common.util.IOUtils;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.drive.DriveFile;
import com.ironsource.sdk.constants.Constants;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzatv {
    public static final Handler zzdsk = new zzatp(Looper.getMainLooper());

    @GuardedBy("userAgentLock")
    private String zzbey;
    private AtomicReference<Pattern> zzdsi = new AtomicReference<>(null);
    private AtomicReference<Pattern> zzdsj = new AtomicReference<>(null);
    private boolean zzyf = true;
    private final Object zzdsl = new Object();
    private boolean zzdsm = false;
    private boolean zzdsn = false;

    public final void zza(Context context, String str, boolean z, HttpURLConnection httpURLConnection) {
        httpURLConnection.setConnectTimeout(60000);
        httpURLConnection.setInstanceFollowRedirects(false);
        httpURLConnection.setReadTimeout(60000);
        httpURLConnection.setRequestProperty("User-Agent", zzr(context, str));
        httpURLConnection.setUseCaches(false);
    }

    public static boolean zzq(Context context, String str) {
        Context contextZzz = zzapf.zzz(context);
        return Wrappers.packageManager(contextZzz).checkPermission(str, contextZzz.getPackageName()) == 0;
    }

    public static void zzb(Context context, String str, String str2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str2);
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            new zzavv(context, str, (String) obj).zzup();
        }
    }

    public static String zza(InputStreamReader inputStreamReader) throws IOException {
        StringBuilder sb = new StringBuilder(8192);
        char[] cArr = new char[2048];
        while (true) {
            int i = inputStreamReader.read(cArr);
            if (i != -1) {
                sb.append(cArr, 0, i);
            } else {
                return sb.toString();
            }
        }
    }

    public final boolean zzan(Context context) {
        if (this.zzdsm) {
            return false;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        context.getApplicationContext().registerReceiver(new zzaty(this, null), intentFilter);
        this.zzdsm = true;
        return true;
    }

    public final boolean zzao(Context context) {
        if (this.zzdsn) {
            return false;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.google.android.ads.intent.DEBUG_LOGGING_ENABLEMENT_CHANGED");
        context.getApplicationContext().registerReceiver(new zzatz(this, null), intentFilter);
        this.zzdsn = true;
        return true;
    }

    public final void zza(Context context, String str, WebSettings webSettings) {
        webSettings.setUserAgentString(zzr(context, str));
    }

    private static String zzvh() {
        StringBuilder sb = new StringBuilder(256);
        sb.append("Mozilla/5.0 (Linux; U; Android");
        if (Build.VERSION.RELEASE != null) {
            sb.append(" ");
            sb.append(Build.VERSION.RELEASE);
        }
        sb.append("; ");
        sb.append(Locale.getDefault());
        if (Build.DEVICE != null) {
            sb.append("; ");
            sb.append(Build.DEVICE);
            if (Build.DISPLAY != null) {
                sb.append(" Build/");
                sb.append(Build.DISPLAY);
            }
        }
        sb.append(") AppleWebKit/533 Version/4.0 Safari/533");
        return sb.toString();
    }

    public final String zzr(Context context, String str) {
        synchronized (this.zzdsl) {
            try {
                if (this.zzbey != null) {
                    return this.zzbey;
                }
                if (str == null) {
                    return zzvh();
                }
                try {
                    this.zzbey = com.google.android.gms.ads.internal.zzp.zzka().getDefaultUserAgent(context);
                } catch (Exception unused) {
                }
                if (TextUtils.isEmpty(this.zzbey)) {
                    zzuo.zzof();
                    if (!zzawe.zzwc()) {
                        this.zzbey = null;
                        zzdsk.post(new zzatx(this, context));
                        while (this.zzbey == null) {
                            try {
                                this.zzdsl.wait();
                            } catch (InterruptedException unused2) {
                                this.zzbey = zzvh();
                                String strValueOf = String.valueOf(this.zzbey);
                                zzatm.zzeu(strValueOf.length() != 0 ? "Interrupted, use default user agent: ".concat(strValueOf) : new String("Interrupted, use default user agent: "));
                            }
                        }
                    } else {
                        this.zzbey = zzap(context);
                    }
                }
                String strValueOf2 = String.valueOf(this.zzbey);
                StringBuilder sb = new StringBuilder(String.valueOf(strValueOf2).length() + 10 + String.valueOf(str).length());
                sb.append(strValueOf2);
                sb.append(" (Mobile; ");
                sb.append(str);
                this.zzbey = sb.toString();
                try {
                    if (Wrappers.packageManager(context).isCallerInstantApp()) {
                        this.zzbey = String.valueOf(this.zzbey).concat(";aia");
                    }
                } catch (Exception e) {
                    com.google.android.gms.ads.internal.zzp.zzkc().zza(e, "AdUtil.getUserAgent");
                }
                this.zzbey = String.valueOf(this.zzbey).concat(")");
                return this.zzbey;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @VisibleForTesting
    protected static String zzap(Context context) {
        try {
            return new WebView(context).getSettings().getUserAgentString();
        } catch (Throwable unused) {
            return zzvh();
        }
    }

    public final JSONObject zzi(Map<String, ?> map) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            for (String str : map.keySet()) {
                zza(jSONObject, str, map.get(str));
            }
            return jSONObject;
        } catch (ClassCastException e) {
            String strValueOf = String.valueOf(e.getMessage());
            throw new JSONException(strValueOf.length() != 0 ? "Could not convert map to JSON: ".concat(strValueOf) : new String("Could not convert map to JSON: "));
        }
    }

    public final JSONObject zza(@Nullable Bundle bundle, JSONObject jSONObject) {
        if (bundle != null) {
            try {
                return zzd(bundle);
            } catch (JSONException e) {
                zzatm.zzc("Error converting Bundle to JSON", e);
            }
        }
        return null;
    }

    public final JSONObject zzd(Bundle bundle) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (String str : bundle.keySet()) {
            zza(jSONObject, str, bundle.get(str));
        }
        return jSONObject;
    }

    private final JSONArray zza(Collection<?> collection) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            zza(jSONArray, it.next());
        }
        return jSONArray;
    }

    private final void zza(JSONArray jSONArray, Object obj) throws JSONException {
        if (obj instanceof Bundle) {
            jSONArray.put(zzd((Bundle) obj));
            return;
        }
        if (obj instanceof Map) {
            jSONArray.put(zzi((Map<String, ?>) obj));
            return;
        }
        if (obj instanceof Collection) {
            jSONArray.put(zza((Collection<?>) obj));
            return;
        }
        if (obj instanceof Object[]) {
            JSONArray jSONArray2 = new JSONArray();
            for (Object obj2 : (Object[]) obj) {
                zza(jSONArray2, obj2);
            }
            jSONArray.put(jSONArray2);
            return;
        }
        jSONArray.put(obj);
    }

    private final void zza(JSONObject jSONObject, String str, Object obj) throws JSONException {
        if (obj instanceof Bundle) {
            jSONObject.put(str, zzd((Bundle) obj));
            return;
        }
        if (obj instanceof Map) {
            jSONObject.put(str, zzi((Map<String, ?>) obj));
            return;
        }
        if (obj instanceof Collection) {
            if (str == null) {
                str = "null";
            }
            jSONObject.put(str, zza((Collection<?>) obj));
        } else if (obj instanceof Object[]) {
            jSONObject.put(str, zza(Arrays.asList((Object[]) obj)));
        } else {
            jSONObject.put(str, obj);
        }
    }

    private static int zza(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        char c2 = 'A';
        if (c < 'A' || c > 'F') {
            c2 = 'a';
            if (c < 'a' || c > 'f') {
                throw new IllegalArgumentException("Invalid Hex.");
            }
        }
        return (c - c2) + 10;
    }

    public static Map<String, String> zzi(Uri uri) {
        if (!((Boolean) zzuo.zzoj().zzd(zzyt.zzcsv)).booleanValue()) {
            if (uri == null) {
                return null;
            }
            HashMap map = new HashMap();
            com.google.android.gms.ads.internal.zzp.zzka();
            for (String str : uri.getQueryParameterNames()) {
                map.put(str, uri.getQueryParameter(str));
            }
            return map;
        }
        if (uri == null) {
            return null;
        }
        HashMap map2 = new HashMap(20);
        String encodedQuery = uri.getEncodedQuery();
        if (encodedQuery == null) {
            return map2;
        }
        int length = encodedQuery.length();
        StringBuilder sb = new StringBuilder(100);
        StringBuilder sb2 = new StringBuilder(length);
        StringBuilder sb3 = sb;
        int i = 0;
        while (i < length) {
            char cCharAt = encodedQuery.charAt(i);
            if (cCharAt == '%') {
                byte[] bArr = new byte[(length - i) / 3];
                int i2 = 0;
                while (i < length - 2 && cCharAt == '%') {
                    try {
                        bArr[i2] = (byte) ((zza(encodedQuery.charAt(i + 1)) << 4) + zza(encodedQuery.charAt(i + 2)));
                        i2++;
                    } catch (IllegalArgumentException unused) {
                    }
                    i += 3;
                    if (i < length) {
                        cCharAt = encodedQuery.charAt(i);
                    }
                }
                try {
                    sb3.append(new String(bArr, 0, i2, "UTF-8"));
                } catch (UnsupportedEncodingException unused2) {
                }
                if (cCharAt != '%') {
                }
            } else if (cCharAt == '&') {
                if (sb.length() > 0 && sb3 != sb) {
                    map2.put(sb.toString(), sb2.toString());
                    sb.setLength(0);
                    sb2.setLength(0);
                }
                sb3 = sb;
            } else if (cCharAt == '+') {
                sb3.append(' ');
            } else if (cCharAt == '=' && sb3 != sb2) {
                sb3 = sb2;
            } else {
                sb3.append(cCharAt);
            }
            i++;
        }
        if (sb.length() > 0 && sb3 != sb) {
            map2.put(sb.toString(), sb2.toString());
        }
        return map2;
    }

    public static String zzvi() {
        return UUID.randomUUID().toString();
    }

    public static int zzed(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            String strValueOf = String.valueOf(e);
            StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 22);
            sb.append("Could not parse value:");
            sb.append(strValueOf);
            zzatm.zzeu(sb.toString());
            return 0;
        }
    }

    public static String zzvj() {
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        if (str2.startsWith(str)) {
            return str2;
        }
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 1 + String.valueOf(str2).length());
        sb.append(str);
        sb.append(" ");
        sb.append(str2);
        return sb.toString();
    }

    private static int[] zzvk() {
        return new int[]{0, 0};
    }

    public static int[] zzd(Activity activity) {
        View viewFindViewById;
        Window window = activity.getWindow();
        if (window != null && (viewFindViewById = window.findViewById(R.id.content)) != null) {
            return new int[]{viewFindViewById.getWidth(), viewFindViewById.getHeight()};
        }
        return zzvk();
    }

    public final int[] zze(Activity activity) {
        int[] iArrZzd = zzd(activity);
        return new int[]{zzuo.zzof().zzb(activity, iArrZzd[0]), zzuo.zzof().zzb(activity, iArrZzd[1])};
    }

    public final int[] zzf(Activity activity) {
        int[] iArrZzvk;
        View viewFindViewById;
        Window window = activity.getWindow();
        if (window != null && (viewFindViewById = window.findViewById(R.id.content)) != null) {
            iArrZzvk = new int[]{viewFindViewById.getTop(), viewFindViewById.getBottom()};
        } else {
            iArrZzvk = zzvk();
        }
        return new int[]{zzuo.zzof().zzb(activity, iArrZzvk[0]), zzuo.zzof().zzb(activity, iArrZzvk[1])};
    }

    public static boolean zzee(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.matches("([^\\s]+(\\.(?i)(jpg|png|gif|bmp|webp))$)");
    }

    public static DisplayMetrics zza(WindowManager windowManager) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static AlertDialog.Builder zzaq(Context context) {
        return new AlertDialog.Builder(context);
    }

    public static zzye zzar(Context context) {
        return new zzye(context);
    }

    public static Bitmap zzk(View view) {
        view.setDrawingCacheEnabled(true);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        return bitmapCreateBitmap;
    }

    public static PopupWindow zza(View view, int i, int i2, boolean z) {
        return new PopupWindow(view, i, i2, false);
    }

    @SuppressLint({"NewApi"})
    public static String zzas(Context context) {
        List<ActivityManager.RunningTaskInfo> runningTasks;
        ActivityManager.RunningTaskInfo runningTaskInfo;
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            if (activityManager != null && (runningTasks = activityManager.getRunningTasks(1)) != null && !runningTasks.isEmpty() && (runningTaskInfo = runningTasks.get(0)) != null && runningTaskInfo.topActivity != null) {
                return runningTaskInfo.topActivity.getClassName();
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public static boolean zzat(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
            if (activityManager == null || keyguardManager == null || (runningAppProcesses = activityManager.getRunningAppProcesses()) == null) {
                return false;
            }
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (Process.myPid() == runningAppProcessInfo.pid) {
                    if (runningAppProcessInfo.importance != 100 || keyguardManager.inKeyguardRestrictedInputMode()) {
                        return true;
                    }
                    PowerManager powerManager = (PowerManager) context.getSystemService("power");
                    return !(powerManager == null ? false : powerManager.isScreenOn());
                }
            }
            return true;
        } catch (Throwable unused) {
        }
        return false;
    }

    public final void zza(Context context, @Nullable String str, String str2, Bundle bundle, boolean z) {
        com.google.android.gms.ads.internal.zzp.zzjy();
        bundle.putString("device", zzvj());
        bundle.putString("eids", TextUtils.join(",", zzyt.zzpn()));
        zzuo.zzof();
        zzawe.zza(context, str, str2, bundle, true, new zzatw(this, context, str));
    }

    public static void zzd(Runnable runnable) {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            runnable.run();
        } else {
            zzawx.zzdvx.execute(runnable);
        }
    }

    public static Bitmap zzl(View view) {
        if (view == null) {
            return null;
        }
        Bitmap bitmapZzn = zzn(view);
        return bitmapZzn == null ? zzm(view) : bitmapZzn;
    }

    private static Bitmap zzm(@NonNull View view) {
        try {
            int width = view.getWidth();
            int height = view.getHeight();
            if (width != 0 && height != 0) {
                Bitmap bitmapCreateBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.RGB_565);
                Canvas canvas = new Canvas(bitmapCreateBitmap);
                view.layout(0, 0, width, height);
                view.draw(canvas);
                return bitmapCreateBitmap;
            }
            zzatm.zzeu("Width or height of view is zero");
            return null;
        } catch (RuntimeException e) {
            zzatm.zzc("Fail to capture the webview", e);
            return null;
        }
    }

    private static Bitmap zzn(@NonNull View view) {
        Bitmap bitmapCreateBitmap = null;
        try {
            boolean zIsDrawingCacheEnabled = view.isDrawingCacheEnabled();
            view.setDrawingCacheEnabled(true);
            Bitmap drawingCache = view.getDrawingCache();
            bitmapCreateBitmap = drawingCache != null ? Bitmap.createBitmap(drawingCache) : null;
            view.setDrawingCacheEnabled(zIsDrawingCacheEnabled);
        } catch (RuntimeException e) {
            zzatm.zzc("Fail to capture the web view", e);
        }
        return bitmapCreateBitmap;
    }

    public static void zza(Context context, Intent intent) {
        try {
            context.startActivity(intent);
        } catch (Throwable unused) {
            intent.addFlags(DriveFile.MODE_READ_ONLY);
            context.startActivity(intent);
        }
    }

    public static int zzau(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        if (applicationInfo == null) {
            return 0;
        }
        return applicationInfo.targetSdkVersion;
    }

    public final boolean zza(View view, Context context) {
        Context applicationContext = context.getApplicationContext();
        return zza(view, applicationContext != null ? (PowerManager) applicationContext.getSystemService("power") : null, zzav(context));
    }

    @Nullable
    private static KeyguardManager zzav(Context context) {
        Object systemService = context.getSystemService("keyguard");
        if (systemService == null || !(systemService instanceof KeyguardManager)) {
            return null;
        }
        return (KeyguardManager) systemService;
    }

    /* JADX WARN: Code duplicated, block: B:12:0x001d  */
    public final boolean zza(View view, PowerManager powerManager, KeyguardManager keyguardManager) {
        boolean z;
        if (com.google.android.gms.ads.internal.zzp.zzjy().zzyf) {
            z = true;
        } else {
            if (!(keyguardManager == null ? false : keyguardManager.inKeyguardRestrictedInputMode()) || zzo(view)) {
                z = true;
            } else {
                z = false;
            }
        }
        if (view.getVisibility() == 0 && view.isShown()) {
            if ((powerManager == null || powerManager.isScreenOn()) && z) {
                if (!((Boolean) zzuo.zzoj().zzd(zzyt.zzclq)).booleanValue() || view.getLocalVisibleRect(new Rect()) || view.getGlobalVisibleRect(new Rect())) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0012  */
    public static boolean zzo(View view) {
        Activity activity;
        View rootView = view.getRootView();
        if (rootView != null) {
            Context context = rootView.getContext();
            if (context instanceof Activity) {
                activity = (Activity) context;
            } else {
                activity = null;
            }
        } else {
            activity = null;
        }
        if (activity == null) {
            return false;
        }
        Window window = activity.getWindow();
        WindowManager.LayoutParams attributes = window != null ? window.getAttributes() : null;
        return (attributes == null || (attributes.flags & 524288) == 0) ? false : true;
    }

    @TargetApi(16)
    public static boolean zzaw(Context context) {
        KeyguardManager keyguardManagerZzav;
        return context != null && PlatformVersion.isAtLeastJellyBean() && (keyguardManagerZzav = zzav(context)) != null && keyguardManagerZzav.isKeyguardLocked();
    }

    public static int zzp(@Nullable View view) {
        if (view == null) {
            return -1;
        }
        ViewParent parent = view.getParent();
        while (parent != null && !(parent instanceof AdapterView)) {
            parent = parent.getParent();
        }
        if (parent == null) {
            return -1;
        }
        return ((AdapterView) parent).getPositionForView(view);
    }

    public static boolean zzax(Context context) {
        try {
            context.getClassLoader().loadClass("com.google.android.gms.ads.internal.ClientApi");
            return false;
        } catch (ClassNotFoundException unused) {
            return true;
        } catch (Throwable th) {
            zzatm.zzc("Error loading class.", th);
            com.google.android.gms.ads.internal.zzp.zzkc().zza(th, "AdUtil.isLiteSdk");
            return false;
        }
    }

    @TargetApi(18)
    public static void zza(Context context, Uri uri) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW", uri);
            Bundle bundle = new Bundle();
            intent.putExtras(bundle);
            zzb(context, intent);
            bundle.putString("com.android.browser.application_id", context.getPackageName());
            context.startActivity(intent);
            String string = uri.toString();
            StringBuilder sb = new StringBuilder(String.valueOf(string).length() + 26);
            sb.append("Opening ");
            sb.append(string);
            sb.append(" in a new browser.");
            zzatm.zzdv(sb.toString());
        } catch (ActivityNotFoundException e) {
            zzatm.zzc("No browser is found.", e);
        }
    }

    @TargetApi(18)
    public static void zzb(Context context, Intent intent) {
        if (intent != null && PlatformVersion.isAtLeastJellyBeanMR2()) {
            Bundle extras = intent.getExtras() != null ? intent.getExtras() : new Bundle();
            extras.putBinder(CustomTabsIntent.EXTRA_SESSION, null);
            extras.putString("com.android.browser.application_id", context.getPackageName());
            intent.putExtras(extras);
        }
    }

    public static void zzc(Context context, String str, String str2) {
        try {
            FileOutputStream fileOutputStreamOpenFileOutput = context.openFileOutput(str, 0);
            fileOutputStreamOpenFileOutput.write(str2.getBytes("UTF-8"));
            fileOutputStreamOpenFileOutput.close();
        } catch (Exception e) {
            zzatm.zzc("Error writing to file in internal storage.", e);
        }
    }

    public static String zzs(Context context, String str) {
        try {
            return new String(IOUtils.readInputStreamFully(context.openFileInput(str), true), "UTF-8");
        } catch (IOException unused) {
            zzatm.zzdv("Error reading from internal storage.");
            return "";
        }
    }

    public final boolean zzef(String str) {
        return zza(str, this.zzdsi, (String) zzuo.zzoj().zzd(zzyt.zzcja));
    }

    public final boolean zzeg(String str) {
        return zza(str, this.zzdsj, (String) zzuo.zzoj().zzd(zzyt.zzcjb));
    }

    private static boolean zza(String str, AtomicReference<Pattern> atomicReference, String str2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            Pattern patternCompile = atomicReference.get();
            if (patternCompile == null || !str2.equals(patternCompile.pattern())) {
                patternCompile = Pattern.compile(str2);
                atomicReference.set(patternCompile);
            }
            return patternCompile.matcher(str).matches();
        } catch (PatternSyntaxException unused) {
            return false;
        }
    }

    public static String zzvl() {
        Resources resources = com.google.android.gms.ads.internal.zzp.zzkc().getResources();
        return resources != null ? resources.getString(com.google.android.gms.ads.impl.R.string.s7) : "Test Ad";
    }

    @Nullable
    public static WebResourceResponse zzd(Context context, String str, String str2) {
        try {
            HashMap map = new HashMap();
            map.put("User-Agent", com.google.android.gms.ads.internal.zzp.zzjy().zzr(context, str));
            map.put(HttpRequest.HEADER_CACHE_CONTROL, "max-stale=3600");
            String str3 = new zzave(context).zzc(str2, map).get(60L, TimeUnit.SECONDS);
            if (str3 != null) {
                return new WebResourceResponse(WebRequest.CONTENT_TYPE_JAVASCRIPT, "UTF-8", new ByteArrayInputStream(str3.getBytes("UTF-8")));
            }
            return null;
        } catch (IOException | InterruptedException | ExecutionException | TimeoutException e) {
            zzatm.zzd("Could not fetch MRAID JS.", e);
            return null;
        }
    }

    public static WebResourceResponse zzd(HttpURLConnection httpURLConnection) throws IOException {
        com.google.android.gms.ads.internal.zzp.zzjy();
        String contentType = httpURLConnection.getContentType();
        String strTrim = "";
        String strTrim2 = TextUtils.isEmpty(contentType) ? "" : contentType.split(";")[0].trim();
        com.google.android.gms.ads.internal.zzp.zzjy();
        String contentType2 = httpURLConnection.getContentType();
        if (!TextUtils.isEmpty(contentType2)) {
            String[] strArrSplit = contentType2.split(";");
            if (strArrSplit.length != 1) {
                for (int i = 1; i < strArrSplit.length; i++) {
                    if (strArrSplit[i].trim().startsWith(HttpRequest.PARAM_CHARSET)) {
                        String[] strArrSplit2 = strArrSplit[i].trim().split(Constants.RequestParameters.EQUAL);
                        if (strArrSplit2.length > 1) {
                            strTrim = strArrSplit2[1].trim();
                            break;
                        }
                    }
                }
            }
        }
        String str = strTrim;
        Map<String, List<String>> headerFields = httpURLConnection.getHeaderFields();
        HashMap map = new HashMap(headerFields.size());
        for (Map.Entry<String, List<String>> entry : headerFields.entrySet()) {
            if (entry.getKey() != null && entry.getValue() != null && entry.getValue().size() > 0) {
                map.put(entry.getKey(), entry.getValue().get(0));
            }
        }
        return com.google.android.gms.ads.internal.zzp.zzka().zza(strTrim2, str, httpURLConnection.getResponseCode(), httpURLConnection.getResponseMessage(), map, httpURLConnection.getInputStream());
    }

    public static void zza(Context context, Throwable th) {
        if (context == null) {
            return;
        }
        boolean zBooleanValue = false;
        try {
            zBooleanValue = ((Boolean) zzuo.zzoj().zzd(zzyt.zzcgj)).booleanValue();
        } catch (IllegalStateException unused) {
        }
        if (zBooleanValue) {
            CrashUtils.addDynamiteErrorToDropBox(context, th);
        }
    }

    public static String zzay(Context context) {
        return !((Boolean) zzuo.zzoj().zzd(zzyt.zzcsk)).booleanValue() ? "" : context.getSharedPreferences("mobileads_consent", 0).getString("consent_string", "");
    }

    public static Bundle zzaz(Context context) {
        if (!((Boolean) zzuo.zzoj().zzd(zzyt.zzcsl)).booleanValue()) {
            return null;
        }
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Bundle bundle = new Bundle();
        if (defaultSharedPreferences.contains("IABConsent_CMPPresent")) {
            bundle.putBoolean("IABConsent_CMPPresent", defaultSharedPreferences.getBoolean("IABConsent_CMPPresent", false));
        }
        String[] strArr = {"IABConsent_SubjectToGDPR", "IABConsent_ConsentString", "IABConsent_ParsedPurposeConsents", "IABConsent_ParsedVendorConsents"};
        for (int i = 0; i < 4; i++) {
            String str = strArr[i];
            if (defaultSharedPreferences.contains(str)) {
                bundle.putString(str, defaultSharedPreferences.getString(str, null));
            }
        }
        return bundle;
    }

    public static boolean zzba(Context context) {
        Window window;
        if ((context instanceof Activity) && (window = ((Activity) context).getWindow()) != null && window.getDecorView() != null) {
            Rect rect = new Rect();
            Rect rect2 = new Rect();
            window.getDecorView().getGlobalVisibleRect(rect, null);
            window.getDecorView().getWindowVisibleDisplayFrame(rect2);
            if (rect.bottom != 0 && rect2.bottom != 0 && rect.top == rect2.top) {
                return true;
            }
        }
        return false;
    }

    public static boolean zzeh(String str) {
        if (!zzawi.isEnabled()) {
            return false;
        }
        if (!((Boolean) zzuo.zzoj().zzd(zzyt.zzcqg)).booleanValue()) {
            return false;
        }
        String str2 = (String) zzuo.zzoj().zzd(zzyt.zzcqi);
        if (!str2.isEmpty()) {
            for (String str3 : str2.split(";")) {
                if (str3.equals(str)) {
                    return false;
                }
            }
        }
        String str4 = (String) zzuo.zzoj().zzd(zzyt.zzcqh);
        if (str4.isEmpty()) {
            return true;
        }
        for (String str5 : str4.split(";")) {
            if (str5.equals(str)) {
                return true;
            }
        }
        return false;
    }
}
