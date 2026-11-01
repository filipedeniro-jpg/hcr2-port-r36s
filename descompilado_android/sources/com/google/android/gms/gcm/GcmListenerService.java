package com.google.android.gms.gcm;

import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;
import androidx.annotation.CallSuper;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class GcmListenerService extends com.google.android.gms.iid.zze {
    private com.google.android.gms.internal.gcm.zzl zzg = com.google.android.gms.internal.gcm.zzm.zzdk;

    public void onDeletedMessages() {
    }

    public void onMessageReceived(String str, Bundle bundle) {
    }

    public void onMessageSent(String str) {
    }

    public void onSendError(String str, String str2) {
    }

    @Override // android.app.Service
    @CallSuper
    public void onCreate() {
        super.onCreate();
        com.google.android.gms.internal.gcm.zzm.zzab();
        getClass();
        this.zzg = com.google.android.gms.internal.gcm.zzm.zzdk;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.google.android.gms.iid.zze
    public void handleIntent(Intent intent) {
        if (!"com.google.android.c2dm.intent.RECEIVE".equals(intent.getAction())) {
            String strValueOf = String.valueOf(intent.getAction());
            Log.w("GcmListenerService", strValueOf.length() != 0 ? "Unknown intent action: ".concat(strValueOf) : new String("Unknown intent action: "));
            return;
        }
        String stringExtra = intent.getStringExtra("message_type");
        if (stringExtra == null) {
            stringExtra = GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE;
        }
        byte b = -1;
        boolean z = false;
        switch (stringExtra.hashCode()) {
            case -2062414158:
                if (stringExtra.equals(GoogleCloudMessaging.MESSAGE_TYPE_DELETED)) {
                    b = 1;
                }
                break;
            case 102161:
                if (stringExtra.equals(GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE)) {
                    b = 0;
                }
                break;
            case 814694033:
                if (stringExtra.equals(GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR)) {
                    b = 3;
                }
                break;
            case 814800675:
                if (stringExtra.equals(GoogleCloudMessaging.MESSAGE_TYPE_SEND_EVENT)) {
                    b = 2;
                }
                break;
        }
        if (b != 0) {
            if (b == 1) {
                onDeletedMessages();
                return;
            }
            if (b == 2) {
                onMessageSent(intent.getStringExtra("google.message_id"));
                return;
            }
            if (b == 3) {
                String stringExtra2 = intent.getStringExtra("google.message_id");
                if (stringExtra2 == null) {
                    stringExtra2 = intent.getStringExtra("message_id");
                }
                onSendError(stringExtra2, intent.getStringExtra("error"));
                return;
            }
            String strValueOf2 = String.valueOf(stringExtra);
            Log.w("GcmListenerService", strValueOf2.length() != 0 ? "Received message with unknown type: ".concat(strValueOf2) : new String("Received message with unknown type: "));
            return;
        }
        Bundle extras = intent.getExtras();
        extras.remove("message_type");
        extras.remove("androidx.contentpager.content.wakelockid");
        if ("1".equals(zzd.zzd(extras, "gcm.n.e")) || zzd.zzd(extras, "gcm.n.icon") != null) {
            if (!((KeyguardManager) getSystemService("keyguard")).inKeyguardRestrictedInputMode()) {
                int iMyPid = Process.myPid();
                List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) getSystemService("activity")).getRunningAppProcesses();
                if (runningAppProcesses != null) {
                    for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                        if (runningAppProcessInfo.pid == iMyPid) {
                            if (runningAppProcessInfo.importance == 100) {
                                z = true;
                            }
                        }
                    }
                }
            }
            if (!z) {
                zzd.zzd(this).zze(extras);
                return;
            }
            Bundle bundle = new Bundle();
            Iterator<String> it = extras.keySet().iterator();
            while (it.hasNext()) {
                String next = it.next();
                String string = extras.getString(next);
                if (next.startsWith("gcm.notification.")) {
                    next = next.replace("gcm.notification.", "gcm.n.");
                }
                if (next.startsWith("gcm.n.")) {
                    if (!"gcm.n.e".equals(next)) {
                        bundle.putString(next.substring(6), string);
                    }
                    it.remove();
                }
            }
            String string2 = bundle.getString("sound2");
            if (string2 != null) {
                bundle.remove("sound2");
                bundle.putString("sound", string2);
            }
            if (!bundle.isEmpty()) {
                extras.putBundle("notification", bundle);
            }
        }
        String string3 = extras.getString("from");
        extras.remove("from");
        zzd(extras);
        this.zzg.zzl("onMessageReceived");
        onMessageReceived(string3, extras);
    }

    static void zzd(Bundle bundle) {
        Iterator<String> it = bundle.keySet().iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (next != null && next.startsWith("google.c.")) {
                it.remove();
            }
        }
    }
}
