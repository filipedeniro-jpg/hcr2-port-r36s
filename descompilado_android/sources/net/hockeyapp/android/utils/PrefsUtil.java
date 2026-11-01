package net.hockeyapp.android.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

/* JADX INFO: loaded from: classes2.dex */
public class PrefsUtil {
    private SharedPreferences feedbackTokenPrefs;
    private SharedPreferences.Editor feedbackTokenPrefsEditor;
    private SharedPreferences nameEmailSubjectPrefs;
    private SharedPreferences.Editor nameEmailSubjectPrefsEditor;

    private PrefsUtil() {
    }

    private static class PrefsUtilHolder {
        public static final PrefsUtil INSTANCE = new PrefsUtil();

        private PrefsUtilHolder() {
        }
    }

    public static PrefsUtil getInstance() {
        return PrefsUtilHolder.INSTANCE;
    }

    public void saveFeedbackTokenToPrefs(Context context, String str) {
        if (context != null) {
            this.feedbackTokenPrefs = context.getSharedPreferences(Util.PREFS_FEEDBACK_TOKEN, 0);
            SharedPreferences sharedPreferences = this.feedbackTokenPrefs;
            if (sharedPreferences != null) {
                this.feedbackTokenPrefsEditor = sharedPreferences.edit();
                this.feedbackTokenPrefsEditor.putString(Util.PREFS_KEY_FEEDBACK_TOKEN, str);
                applyChanges(this.feedbackTokenPrefsEditor);
            }
        }
    }

    public String getFeedbackTokenFromPrefs(Context context) {
        if (context == null) {
            return null;
        }
        this.feedbackTokenPrefs = context.getSharedPreferences(Util.PREFS_FEEDBACK_TOKEN, 0);
        SharedPreferences sharedPreferences = this.feedbackTokenPrefs;
        if (sharedPreferences == null) {
            return null;
        }
        return sharedPreferences.getString(Util.PREFS_KEY_FEEDBACK_TOKEN, null);
    }

    public void saveNameEmailSubjectToPrefs(Context context, String str, String str2, String str3) {
        if (context != null) {
            this.nameEmailSubjectPrefs = context.getSharedPreferences(Util.PREFS_NAME_EMAIL_SUBJECT, 0);
            SharedPreferences sharedPreferences = this.nameEmailSubjectPrefs;
            if (sharedPreferences != null) {
                this.nameEmailSubjectPrefsEditor = sharedPreferences.edit();
                if (str == null || str2 == null || str3 == null) {
                    this.nameEmailSubjectPrefsEditor.putString(Util.PREFS_KEY_NAME_EMAIL_SUBJECT, null);
                } else {
                    this.nameEmailSubjectPrefsEditor.putString(Util.PREFS_KEY_NAME_EMAIL_SUBJECT, String.format("%s|%s|%s", str, str2, str3));
                }
                applyChanges(this.nameEmailSubjectPrefsEditor);
            }
        }
    }

    public String getNameEmailFromPrefs(Context context) {
        if (context == null) {
            return null;
        }
        this.nameEmailSubjectPrefs = context.getSharedPreferences(Util.PREFS_NAME_EMAIL_SUBJECT, 0);
        SharedPreferences sharedPreferences = this.nameEmailSubjectPrefs;
        if (sharedPreferences == null) {
            return null;
        }
        return sharedPreferences.getString(Util.PREFS_KEY_NAME_EMAIL_SUBJECT, null);
    }

    public static void applyChanges(SharedPreferences.Editor editor) {
        if (applySupported().booleanValue()) {
            editor.apply();
        } else {
            editor.commit();
        }
    }

    public static Boolean applySupported() {
        try {
            return Boolean.valueOf(Build.VERSION.SDK_INT >= 9);
        } catch (NoClassDefFoundError unused) {
            return false;
        }
    }
}
