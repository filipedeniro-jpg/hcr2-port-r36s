package com.appsflyer;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.google.android.gms.iid.InstanceIDListenerService;
import com.google.firebase.iid.FirebaseInstanceIdService;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
final class u {
    u() {
    }

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    static boolean m214(Context context) {
        return m212(context) | m215(context);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0049 A[Catch: Throwable -> 0x0091, ClassNotFoundException -> 0x0098, TryCatch #2 {ClassNotFoundException -> 0x0098, Throwable -> 0x0091, blocks: (B:5:0x000e, B:10:0x0036, B:15:0x0049, B:20:0x0069, B:23:0x0085, B:24:0x008b), top: B:32:0x000e }] */
    /* JADX WARN: Code duplicated, block: B:17:0x0064  */
    /* JADX WARN: Code duplicated, block: B:18:0x0066  */
    /* JADX WARN: Code duplicated, block: B:20:0x0069 A[Catch: Throwable -> 0x0091, ClassNotFoundException -> 0x0098, TryCatch #2 {ClassNotFoundException -> 0x0098, Throwable -> 0x0091, blocks: (B:5:0x000e, B:10:0x0036, B:15:0x0049, B:20:0x0069, B:23:0x0085, B:24:0x008b), top: B:32:0x000e }] */
    /* JADX WARN: Code duplicated, block: B:22:0x0084 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:23:0x0085 A[Catch: Throwable -> 0x0091, ClassNotFoundException -> 0x0098, TryCatch #2 {ClassNotFoundException -> 0x0098, Throwable -> 0x0091, blocks: (B:5:0x000e, B:10:0x0036, B:15:0x0049, B:20:0x0069, B:23:0x0085, B:24:0x008b), top: B:32:0x000e }] */
    /* JADX WARN: Code duplicated, block: B:24:0x008b A[Catch: Throwable -> 0x0091, ClassNotFoundException -> 0x0098, TRY_LEAVE, TryCatch #2 {ClassNotFoundException -> 0x0098, Throwable -> 0x0091, blocks: (B:5:0x000e, B:10:0x0036, B:15:0x0049, B:20:0x0069, B:23:0x0085, B:24:0x008b), top: B:32:0x000e }] */
    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    private static boolean m215(Context context) {
        boolean z;
        StringBuilder sb;
        if (AppsFlyerLib.getInstance().isTrackingStopped()) {
            return false;
        }
        try {
            Class.forName("com.google.android.gms.iid.InstanceIDListenerService");
            Intent intent = new Intent("com.google.android.gms.iid.InstanceID", null, context, GcmInstanceIdListener.class);
            Intent intent2 = new Intent("com.google.android.gms.iid.InstanceID", null, context, InstanceIDListenerService.class);
            if (!(context.getPackageManager().queryIntentServices(intent, 0).size() > 0)) {
                if (context.getPackageManager().queryIntentServices(intent2, 0).size() > 0) {
                    if (context.getPackageManager().queryBroadcastReceivers(new Intent("com.google.android.c2dm.intent.RECEIVE", null, context, Class.forName("com.google.android.gms.gcm.GcmReceiver")), 0).size() > 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (z) {
                        String packageName = context.getPackageName();
                        sb = new StringBuilder();
                        sb.append(packageName);
                        sb.append(".permission.C2D_MESSAGE");
                        if (f.AnonymousClass1.m125(context, sb.toString())) {
                            return true;
                        }
                        AFLogger.afWarnLog("Cannot verify existence of the app's \"permission.C2D_MESSAGE\" permission in the manifest. Please refer to documentation.");
                    } else {
                        AFLogger.afWarnLog("Cannot verify existence of GcmReceiver receiver in the manifest. Please refer to documentation.");
                    }
                }
            } else {
                if (context.getPackageManager().queryBroadcastReceivers(new Intent("com.google.android.c2dm.intent.RECEIVE", null, context, Class.forName("com.google.android.gms.gcm.GcmReceiver")), 0).size() > 0) {
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    String packageName2 = context.getPackageName();
                    sb = new StringBuilder();
                    sb.append(packageName2);
                    sb.append(".permission.C2D_MESSAGE");
                    if (f.AnonymousClass1.m125(context, sb.toString())) {
                        return true;
                    }
                    AFLogger.afWarnLog("Cannot verify existence of the app's \"permission.C2D_MESSAGE\" permission in the manifest. Please refer to documentation.");
                } else {
                    AFLogger.afWarnLog("Cannot verify existence of GcmReceiver receiver in the manifest. Please refer to documentation.");
                }
            }
        } catch (ClassNotFoundException e) {
            AFLogger.afRDLog(e.getMessage());
        } catch (Throwable th) {
            AFLogger.afErrorLog("An error occurred while trying to verify manifest declarations: ", th);
        }
        return false;
    }

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    private static boolean m212(Context context) {
        if (AppsFlyerLib.getInstance().isTrackingStopped()) {
            return false;
        }
        try {
            Class.forName("com.google.firebase.iid.FirebaseInstanceIdService");
            Intent intent = new Intent("com.google.firebase.INSTANCE_ID_EVENT", null, context, FirebaseInstanceIdListener.class);
            Intent intent2 = new Intent("com.google.firebase.INSTANCE_ID_EVENT", null, context, FirebaseInstanceIdService.class);
            if (!(context.getPackageManager().queryIntentServices(intent, 0).size() > 0)) {
                if (!(context.getPackageManager().queryIntentServices(intent2, 0).size() > 0)) {
                    AFLogger.afWarnLog("Cannot verify existence of our InstanceID Listener Service in the manifest. Please refer to documentation.");
                }
                return false;
            }
            return true;
        } catch (ClassNotFoundException unused) {
        } catch (Throwable th) {
            AFLogger.afErrorLog("An error occurred while trying to verify manifest declarations: ", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    public static String m213(WeakReference<Context> weakReference, String str) {
        try {
            Class<?> cls = Class.forName("com.google.android.gms.iid.InstanceID");
            Class.forName("com.google.android.gms.gcm.GcmReceiver");
            String str2 = (String) cls.getDeclaredMethod("getToken", String.class, String.class).invoke(cls.getDeclaredMethod("getInstance", Context.class).invoke(cls, weakReference.get()), str, "GCM");
            if (str2 != null) {
                return str2;
            }
            AFLogger.afWarnLog("Couldn't get token using reflection.");
            return null;
        } catch (ClassNotFoundException unused) {
            return null;
        } catch (Throwable th) {
            AFLogger.afErrorLog("Couldn't get token using GoogleCloudMessaging. ", th);
            return null;
        }
    }

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    static void m217(Context context, c.a.C0031c c0031c) {
        StringBuilder sb = new StringBuilder("updateServerUninstallToken called with: ");
        sb.append(c0031c.toString());
        AFLogger.afInfoLog(sb.toString());
        c.a.C0031c c0031cM113 = c.a.C0031c.m113(AppsFlyerProperties.getInstance().getString("afUninstallToken"));
        if (context.getSharedPreferences("appsflyer-data", 0).getBoolean("sentRegisterRequestToAF", false) && c0031cM113.m114() != null && c0031cM113.m114().equals(c0031c.m114())) {
            return;
        }
        AppsFlyerProperties.getInstance().set("afUninstallToken", c0031c.toString());
        AppsFlyerLib.getInstance().m81(context, c0031c.m114());
    }

    static class a extends AsyncTask<Void, Void, String> {

        /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
        private final WeakReference<Context> f257;

        /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
        private String f258;

        @Override // android.os.AsyncTask
        protected final /* synthetic */ String doInBackground(Void[] voidArr) {
            return m218();
        }

        @Override // android.os.AsyncTask
        protected final /* synthetic */ void onPostExecute(String str) {
            String str2 = str;
            if (TextUtils.isEmpty(str2)) {
                return;
            }
            String string = AppsFlyerProperties.getInstance().getString("afUninstallToken");
            c.a.C0031c c0031c = new c.a.C0031c(str2);
            if (string == null) {
                u.m217(this.f257.get(), c0031c);
                return;
            }
            c.a.C0031c c0031cM113 = c.a.C0031c.m113(string);
            if (c0031cM113.m115(c0031c)) {
                u.m217(this.f257.get(), c0031cM113);
            }
        }

        a(WeakReference<Context> weakReference) {
            this.f257 = weakReference;
        }

        @Override // android.os.AsyncTask
        protected final void onPreExecute() {
            super.onPreExecute();
            this.f258 = AppsFlyerProperties.getInstance().getString("gcmProjectNumber");
        }

        /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
        private String m218() {
            try {
                if (this.f258 != null) {
                    return u.m213(this.f257, this.f258);
                }
                return null;
            } catch (Throwable th) {
                AFLogger.afErrorLog("Error registering for uninstall feature", th);
                return null;
            }
        }
    }
}
