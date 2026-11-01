package com.google.android.gms.gcm;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.drive.DriveFile;
import com.my.target.aa;
import java.util.MissingFormatArgumentException;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;

/* JADX INFO: loaded from: classes.dex */
final class zzd {
    static zzd zzk;
    private final Context zzl;
    private String zzm;
    private final AtomicInteger zzn = new AtomicInteger((int) SystemClock.elapsedRealtime());

    static synchronized zzd zzd(Context context) {
        if (zzk == null) {
            zzk = new zzd(context);
        }
        return zzk;
    }

    static String zzd(Bundle bundle, String str) {
        String string = bundle.getString(str);
        return string == null ? bundle.getString(str.replace("gcm.n.", "gcm.notification.")) : string;
    }

    private zzd(Context context) {
        this.zzl = context.getApplicationContext();
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0081  */
    /* JADX WARN: Code duplicated, block: B:17:0x0088  */
    /* JADX WARN: Code duplicated, block: B:49:0x019b  */
    /* JADX WARN: Code duplicated, block: B:52:0x01a2  */
    /* JADX WARN: Code duplicated, block: B:53:0x01c5  */
    /* JADX WARN: Code duplicated, block: B:55:0x01c9 A[PHI: r8
  0x01c9: PHI (r8v26 java.lang.String) = (r8v16 java.lang.String), (r8v12 java.lang.String) binds: [B:54:0x01c7, B:50:0x019f] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:56:0x01cb  */
    /* JADX WARN: Code duplicated, block: B:58:0x01df  */
    /* JADX WARN: Code duplicated, block: B:60:0x01e7  */
    /* JADX WARN: Code duplicated, block: B:61:0x01ea  */
    /* JADX WARN: Code duplicated, block: B:62:0x01f0  */
    /* JADX WARN: Code duplicated, block: B:65:0x01fd  */
    /* JADX WARN: Code duplicated, block: B:69:0x0227  */
    /* JADX WARN: Code duplicated, block: B:72:0x0230  */
    /* JADX WARN: Code duplicated, block: B:75:0x0245  */
    /* JADX WARN: Code duplicated, block: B:77:0x024e  */
    /* JADX WARN: Code duplicated, block: B:79:0x0253  */
    /* JADX WARN: Code duplicated, block: B:81:0x0258  */
    /* JADX WARN: Code duplicated, block: B:84:0x026b  */
    /* JADX WARN: Code duplicated, block: B:87:0x0280  */
    final boolean zze(Bundle bundle) {
        int i;
        int identifier;
        Uri defaultUri;
        Intent launchIntentForPackage;
        PendingIntent activity;
        String strZzd;
        NotificationCompat.Builder smallIcon;
        String strZzd2;
        NotificationManager notificationManager;
        CharSequence charSequenceZze = zze(bundle, "gcm.n.title");
        if (TextUtils.isEmpty(charSequenceZze)) {
            charSequenceZze = this.zzl.getApplicationInfo().loadLabel(this.zzl.getPackageManager());
        }
        String strZze = zze(bundle, "gcm.n.body");
        String strZzd3 = zzd(bundle, "gcm.n.icon");
        if (!TextUtils.isEmpty(strZzd3)) {
            Resources resources = this.zzl.getResources();
            identifier = resources.getIdentifier(strZzd3, "drawable", this.zzl.getPackageName());
            if (identifier == 0 && (identifier = resources.getIdentifier(strZzd3, "mipmap", this.zzl.getPackageName())) == 0) {
                StringBuilder sb = new StringBuilder(String.valueOf(strZzd3).length() + 57);
                sb.append("Icon resource ");
                sb.append(strZzd3);
                sb.append(" not found. Notification will use app icon.");
                Log.w("GcmNotification", sb.toString());
                i = this.zzl.getApplicationInfo().icon;
                if (i == 0) {
                    identifier = android.R.drawable.sym_def_app_icon;
                } else {
                    identifier = i;
                }
            }
        } else {
            i = this.zzl.getApplicationInfo().icon;
            if (i == 0) {
                identifier = android.R.drawable.sym_def_app_icon;
            } else {
                identifier = i;
            }
        }
        String strZzd4 = zzd(bundle, "gcm.n.color");
        String strZzd5 = zzd(bundle, "gcm.n.sound2");
        String str = null;
        if (TextUtils.isEmpty(strZzd5)) {
            defaultUri = null;
        } else if (!aa.f.bq.equals(strZzd5) && this.zzl.getResources().getIdentifier(strZzd5, "raw", this.zzl.getPackageName()) != 0) {
            String packageName = this.zzl.getPackageName();
            StringBuilder sb2 = new StringBuilder(String.valueOf(packageName).length() + 24 + String.valueOf(strZzd5).length());
            sb2.append("android.resource://");
            sb2.append(packageName);
            sb2.append("/raw/");
            sb2.append(strZzd5);
            defaultUri = Uri.parse(sb2.toString());
        } else {
            defaultUri = RingtoneManager.getDefaultUri(2);
        }
        String strZzd6 = zzd(bundle, "gcm.n.click_action");
        if (!TextUtils.isEmpty(strZzd6)) {
            launchIntentForPackage = new Intent(strZzd6);
            launchIntentForPackage.setPackage(this.zzl.getPackageName());
            launchIntentForPackage.setFlags(DriveFile.MODE_READ_ONLY);
        } else {
            launchIntentForPackage = this.zzl.getPackageManager().getLaunchIntentForPackage(this.zzl.getPackageName());
            if (launchIntentForPackage == null) {
                Log.w("GcmNotification", "No activity found to launch app");
                activity = null;
            }
            strZzd = zzd(bundle, "gcm.n.android_channel_id");
            if (PlatformVersion.isAtLeastO() && this.zzl.getApplicationInfo().targetSdkVersion >= 26) {
                notificationManager = (NotificationManager) this.zzl.getSystemService(NotificationManager.class);
                if (TextUtils.isEmpty(strZzd)) {
                    if (notificationManager.getNotificationChannel(strZzd) != null) {
                        str = strZzd;
                    } else {
                        StringBuilder sb3 = new StringBuilder(String.valueOf(strZzd).length() + 122);
                        sb3.append("Notification Channel requested (");
                        sb3.append(strZzd);
                        sb3.append(") has not been created by the app. Manifest configuration, or default, value will be used.");
                        Log.w("GcmNotification", sb3.toString());
                        strZzd = this.zzm;
                        if (strZzd != null) {
                            str = strZzd;
                        } else {
                            this.zzm = zzf().getString("com.google.android.gms.gcm.default_notification_channel_id");
                            if (!TextUtils.isEmpty(this.zzm)) {
                                if (notificationManager.getNotificationChannel(this.zzm) != null) {
                                    str = this.zzm;
                                } else {
                                    Log.w("GcmNotification", "Notification Channel set in AndroidManifest.xml has not been created by the app. Default value will be used.");
                                }
                            } else {
                                Log.w("GcmNotification", "Missing Default Notification Channel metadata in AndroidManifest. Default value will be used.");
                            }
                            if (notificationManager.getNotificationChannel("fcm_fallback_notification_channel") == null) {
                                notificationManager.createNotificationChannel(new NotificationChannel("fcm_fallback_notification_channel", this.zzl.getString(R.string.gcm_fallback_notification_channel_label), 3));
                            }
                            this.zzm = "fcm_fallback_notification_channel";
                            str = this.zzm;
                        }
                    }
                } else {
                    strZzd = this.zzm;
                    if (strZzd != null) {
                        str = strZzd;
                    } else {
                        this.zzm = zzf().getString("com.google.android.gms.gcm.default_notification_channel_id");
                        if (!TextUtils.isEmpty(this.zzm)) {
                            if (notificationManager.getNotificationChannel(this.zzm) != null) {
                                str = this.zzm;
                            } else {
                                Log.w("GcmNotification", "Notification Channel set in AndroidManifest.xml has not been created by the app. Default value will be used.");
                            }
                        } else {
                            Log.w("GcmNotification", "Missing Default Notification Channel metadata in AndroidManifest. Default value will be used.");
                        }
                        if (notificationManager.getNotificationChannel("fcm_fallback_notification_channel") == null) {
                            notificationManager.createNotificationChannel(new NotificationChannel("fcm_fallback_notification_channel", this.zzl.getString(R.string.gcm_fallback_notification_channel_label), 3));
                        }
                        this.zzm = "fcm_fallback_notification_channel";
                        str = this.zzm;
                    }
                }
            }
            smallIcon = new NotificationCompat.Builder(this.zzl).setAutoCancel(true).setSmallIcon(identifier);
            if (!TextUtils.isEmpty(charSequenceZze)) {
                smallIcon.setContentTitle(charSequenceZze);
            }
            if (!TextUtils.isEmpty(strZze)) {
                smallIcon.setContentText(strZze);
                smallIcon.setStyle(new NotificationCompat.BigTextStyle().bigText(strZze));
            }
            if (!TextUtils.isEmpty(strZzd4)) {
                smallIcon.setColor(Color.parseColor(strZzd4));
            }
            if (defaultUri != null) {
                smallIcon.setSound(defaultUri);
            }
            if (activity != null) {
                smallIcon.setContentIntent(activity);
            }
            if (str != null) {
                smallIcon.setChannelId(str);
            }
            Notification notificationBuild = smallIcon.build();
            strZzd2 = zzd(bundle, "gcm.n.tag");
            if (Log.isLoggable("GcmNotification", 3)) {
                Log.d("GcmNotification", "Showing notification");
            }
            NotificationManager notificationManager2 = (NotificationManager) this.zzl.getSystemService("notification");
            if (TextUtils.isEmpty(strZzd2)) {
                long jUptimeMillis = SystemClock.uptimeMillis();
                StringBuilder sb4 = new StringBuilder(37);
                sb4.append("GCM-Notification:");
                sb4.append(jUptimeMillis);
                strZzd2 = sb4.toString();
            }
            notificationManager2.notify(strZzd2, 0, notificationBuild);
            return true;
        }
        Bundle bundle2 = new Bundle(bundle);
        GcmListenerService.zzd(bundle2);
        launchIntentForPackage.putExtras(bundle2);
        for (String str2 : bundle2.keySet()) {
            if (str2.startsWith("gcm.n.") || str2.startsWith("gcm.notification.")) {
                launchIntentForPackage.removeExtra(str2);
            }
        }
        activity = PendingIntent.getActivity(this.zzl, this.zzn.getAndIncrement(), launchIntentForPackage, 1073741824);
        strZzd = zzd(bundle, "gcm.n.android_channel_id");
        if (PlatformVersion.isAtLeastO()) {
            notificationManager = (NotificationManager) this.zzl.getSystemService(NotificationManager.class);
            if (TextUtils.isEmpty(strZzd)) {
                if (notificationManager.getNotificationChannel(strZzd) != null) {
                    str = strZzd;
                } else {
                    StringBuilder sb5 = new StringBuilder(String.valueOf(strZzd).length() + 122);
                    sb5.append("Notification Channel requested (");
                    sb5.append(strZzd);
                    sb5.append(") has not been created by the app. Manifest configuration, or default, value will be used.");
                    Log.w("GcmNotification", sb5.toString());
                    strZzd = this.zzm;
                    if (strZzd != null) {
                        str = strZzd;
                    } else {
                        this.zzm = zzf().getString("com.google.android.gms.gcm.default_notification_channel_id");
                        if (!TextUtils.isEmpty(this.zzm)) {
                            if (notificationManager.getNotificationChannel(this.zzm) != null) {
                                str = this.zzm;
                            } else {
                                Log.w("GcmNotification", "Notification Channel set in AndroidManifest.xml has not been created by the app. Default value will be used.");
                            }
                        } else {
                            Log.w("GcmNotification", "Missing Default Notification Channel metadata in AndroidManifest. Default value will be used.");
                        }
                        if (notificationManager.getNotificationChannel("fcm_fallback_notification_channel") == null) {
                            notificationManager.createNotificationChannel(new NotificationChannel("fcm_fallback_notification_channel", this.zzl.getString(R.string.gcm_fallback_notification_channel_label), 3));
                        }
                        this.zzm = "fcm_fallback_notification_channel";
                        str = this.zzm;
                    }
                }
            } else {
                strZzd = this.zzm;
                if (strZzd != null) {
                    str = strZzd;
                } else {
                    this.zzm = zzf().getString("com.google.android.gms.gcm.default_notification_channel_id");
                    if (!TextUtils.isEmpty(this.zzm)) {
                        if (notificationManager.getNotificationChannel(this.zzm) != null) {
                            str = this.zzm;
                        } else {
                            Log.w("GcmNotification", "Notification Channel set in AndroidManifest.xml has not been created by the app. Default value will be used.");
                        }
                    } else {
                        Log.w("GcmNotification", "Missing Default Notification Channel metadata in AndroidManifest. Default value will be used.");
                    }
                    if (notificationManager.getNotificationChannel("fcm_fallback_notification_channel") == null) {
                        notificationManager.createNotificationChannel(new NotificationChannel("fcm_fallback_notification_channel", this.zzl.getString(R.string.gcm_fallback_notification_channel_label), 3));
                    }
                    this.zzm = "fcm_fallback_notification_channel";
                    str = this.zzm;
                }
            }
        }
        smallIcon = new NotificationCompat.Builder(this.zzl).setAutoCancel(true).setSmallIcon(identifier);
        if (!TextUtils.isEmpty(charSequenceZze)) {
            smallIcon.setContentTitle(charSequenceZze);
        }
        if (!TextUtils.isEmpty(strZze)) {
            smallIcon.setContentText(strZze);
            smallIcon.setStyle(new NotificationCompat.BigTextStyle().bigText(strZze));
        }
        if (!TextUtils.isEmpty(strZzd4)) {
            smallIcon.setColor(Color.parseColor(strZzd4));
        }
        if (defaultUri != null) {
            smallIcon.setSound(defaultUri);
        }
        if (activity != null) {
            smallIcon.setContentIntent(activity);
        }
        if (str != null) {
            smallIcon.setChannelId(str);
        }
        Notification notificationBuild2 = smallIcon.build();
        strZzd2 = zzd(bundle, "gcm.n.tag");
        if (Log.isLoggable("GcmNotification", 3)) {
            Log.d("GcmNotification", "Showing notification");
        }
        NotificationManager notificationManager3 = (NotificationManager) this.zzl.getSystemService("notification");
        if (TextUtils.isEmpty(strZzd2)) {
            long jUptimeMillis2 = SystemClock.uptimeMillis();
            StringBuilder sb6 = new StringBuilder(37);
            sb6.append("GCM-Notification:");
            sb6.append(jUptimeMillis2);
            strZzd2 = sb6.toString();
        }
        notificationManager3.notify(strZzd2, 0, notificationBuild2);
        return true;
    }

    private final String zze(Bundle bundle, String str) {
        String strZzd = zzd(bundle, str);
        if (!TextUtils.isEmpty(strZzd)) {
            return strZzd;
        }
        String strValueOf = String.valueOf(str);
        String strZzd2 = zzd(bundle, "_loc_key".length() != 0 ? strValueOf.concat("_loc_key") : new String(strValueOf));
        if (TextUtils.isEmpty(strZzd2)) {
            return null;
        }
        Resources resources = this.zzl.getResources();
        int identifier = resources.getIdentifier(strZzd2, "string", this.zzl.getPackageName());
        if (identifier == 0) {
            String strValueOf2 = String.valueOf(str);
            String strSubstring = ("_loc_key".length() != 0 ? strValueOf2.concat("_loc_key") : new String(strValueOf2)).substring(6);
            StringBuilder sb = new StringBuilder(String.valueOf(strSubstring).length() + 49 + String.valueOf(strZzd2).length());
            sb.append(strSubstring);
            sb.append(" resource not found: ");
            sb.append(strZzd2);
            sb.append(" Default value will be used.");
            Log.w("GcmNotification", sb.toString());
            return null;
        }
        String strValueOf3 = String.valueOf(str);
        String strZzd3 = zzd(bundle, "_loc_args".length() != 0 ? strValueOf3.concat("_loc_args") : new String(strValueOf3));
        if (TextUtils.isEmpty(strZzd3)) {
            return resources.getString(identifier);
        }
        try {
            JSONArray jSONArray = new JSONArray(strZzd3);
            Object[] objArr = new String[jSONArray.length()];
            for (int i = 0; i < objArr.length; i++) {
                objArr[i] = jSONArray.opt(i);
            }
            return resources.getString(identifier, objArr);
        } catch (MissingFormatArgumentException e) {
            StringBuilder sb2 = new StringBuilder(String.valueOf(strZzd2).length() + 58 + String.valueOf(strZzd3).length());
            sb2.append("Missing format argument for ");
            sb2.append(strZzd2);
            sb2.append(": ");
            sb2.append(strZzd3);
            sb2.append(" Default value will be used.");
            Log.w("GcmNotification", sb2.toString(), e);
            return null;
        } catch (JSONException unused) {
            String strValueOf4 = String.valueOf(str);
            String strSubstring2 = ("_loc_args".length() != 0 ? strValueOf4.concat("_loc_args") : new String(strValueOf4)).substring(6);
            StringBuilder sb3 = new StringBuilder(String.valueOf(strSubstring2).length() + 41 + String.valueOf(strZzd3).length());
            sb3.append("Malformed ");
            sb3.append(strSubstring2);
            sb3.append(": ");
            sb3.append(strZzd3);
            sb3.append("  Default value will be used.");
            Log.w("GcmNotification", sb3.toString());
            return null;
        }
    }

    private final Bundle zzf() {
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = this.zzl.getPackageManager().getApplicationInfo(this.zzl.getPackageName(), 128);
        } catch (PackageManager.NameNotFoundException unused) {
            applicationInfo = null;
        }
        if (applicationInfo != null && applicationInfo.metaData != null) {
            return applicationInfo.metaData;
        }
        return Bundle.EMPTY;
    }
}
