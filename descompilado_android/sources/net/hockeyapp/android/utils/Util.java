package net.hockeyapp.android.utils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.text.TextUtils;
import android.util.Patterns;
import com.ironsource.sdk.constants.Constants;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes2.dex */
public class Util {
    public static final int APP_IDENTIFIER_LENGTH = 32;
    public static final String LOG_IDENTIFIER = "HockeyApp";
    public static final String PREFS_FEEDBACK_TOKEN = "net.hockeyapp.android.prefs_feedback_token";
    public static final String PREFS_KEY_FEEDBACK_TOKEN = "net.hockeyapp.android.prefs_key_feedback_token";
    public static final String PREFS_KEY_NAME_EMAIL_SUBJECT = "net.hockeyapp.android.prefs_key_name_email";
    public static final String PREFS_NAME_EMAIL_SUBJECT = "net.hockeyapp.android.prefs_name_email";
    public static final String APP_IDENTIFIER_PATTERN = "[0-9a-f]+";
    private static final Pattern appIdentifierPattern = Pattern.compile(APP_IDENTIFIER_PATTERN, 2);

    public static String encodeParam(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    @TargetApi(8)
    public static final boolean isValidEmail(String str) {
        if (Build.VERSION.SDK_INT >= 8) {
            return !TextUtils.isEmpty(str) && Patterns.EMAIL_ADDRESS.matcher(str).matches();
        }
        return !TextUtils.isEmpty(str);
    }

    @SuppressLint({"NewApi"})
    public static Boolean fragmentsSupported() {
        try {
            return Boolean.valueOf(Build.VERSION.SDK_INT >= 11 && classExists("android.app.Fragment"));
        } catch (NoClassDefFoundError unused) {
            return false;
        }
    }

    public static Boolean runsOnTablet(WeakReference<Activity> weakReference) {
        Activity activity;
        if (weakReference != null && (activity = weakReference.get()) != null) {
            Configuration configuration = activity.getResources().getConfiguration();
            return Boolean.valueOf((configuration.screenLayout & 15) == 3 || (configuration.screenLayout & 15) == 4);
        }
        return false;
    }

    public static String sanitizeAppIdentifier(String str) throws IllegalArgumentException {
        if (str == null) {
            throw new IllegalArgumentException("App ID must not be null.");
        }
        String strTrim = str.trim();
        Matcher matcher = appIdentifierPattern.matcher(strTrim);
        if (strTrim.length() != 32) {
            throw new IllegalArgumentException("App ID length must be 32 characters.");
        }
        if (matcher.matches()) {
            return strTrim;
        }
        throw new IllegalArgumentException("App ID must match regex pattern /[0-9a-f]+/i");
    }

    public static String getFormString(Map<String, String> map) throws UnsupportedEncodingException {
        ArrayList arrayList = new ArrayList();
        for (String str : map.keySet()) {
            String str2 = map.get(str);
            arrayList.add(URLEncoder.encode(str, "UTF-8") + Constants.RequestParameters.EQUAL + URLEncoder.encode(str2, "UTF-8"));
        }
        return TextUtils.join(Constants.RequestParameters.AMPERSAND, arrayList);
    }

    public static boolean classExists(String str) {
        try {
            return Class.forName(str) != null;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public static boolean isNotificationBuilderSupported() {
        return Build.VERSION.SDK_INT >= 11 && classExists("android.app.Notification.Builder");
    }

    public static Notification createNotification(Context context, PendingIntent pendingIntent, String str, String str2, int i) {
        if (isNotificationBuilderSupported()) {
            return buildNotificationWithBuilder(context, pendingIntent, str, str2, i);
        }
        return buildNotificationPreHoneycomb(context, pendingIntent, str, str2, i);
    }

    private static Notification buildNotificationPreHoneycomb(Context context, PendingIntent pendingIntent, String str, String str2, int i) {
        Notification notification = new Notification(i, "", System.currentTimeMillis());
        try {
            notification.getClass().getMethod("setLatestEventInfo", Context.class, CharSequence.class, CharSequence.class, PendingIntent.class).invoke(notification, context, str, str2, pendingIntent);
        } catch (Exception unused) {
        }
        return notification;
    }

    @TargetApi(11)
    private static Notification buildNotificationWithBuilder(Context context, PendingIntent pendingIntent, String str, String str2, int i) {
        Notification.Builder smallIcon = new Notification.Builder(context).setContentTitle(str).setContentText(str2).setContentIntent(pendingIntent).setSmallIcon(i);
        if (Build.VERSION.SDK_INT < 16) {
            return smallIcon.getNotification();
        }
        return smallIcon.build();
    }
}
