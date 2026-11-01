package net.hockeyapp.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import java.util.HashMap;
import net.hockeyapp.android.tasks.LoginTask;
import net.hockeyapp.android.utils.AsyncTaskUtils;
import net.hockeyapp.android.utils.PrefsUtil;
import net.hockeyapp.android.utils.Util;

/* JADX INFO: loaded from: classes2.dex */
public class LoginManager {
    static final String LOGIN_EXIT_KEY = "net.hockeyapp.android.EXIT";
    public static final int LOGIN_MODE_ANONYMOUS = 0;
    public static final int LOGIN_MODE_EMAIL_ONLY = 1;
    public static final int LOGIN_MODE_EMAIL_PASSWORD = 2;
    public static final int LOGIN_MODE_VALIDATE = 3;
    private static String identifier;
    static LoginManagerListener listener;
    static Class<?> mainActivity;
    private static int mode;
    private static String secret;
    private static String urlString;
    private static Handler validateHandler;

    public static void register(Context context, String str, String str2, int i, LoginManagerListener loginManagerListener) {
        listener = loginManagerListener;
        register(context, str, str2, i, (Class<?>) null);
    }

    public static void register(Context context, String str, String str2, int i, Class<?> cls) {
        register(context, str, str2, Constants.BASE_URL, i, cls);
    }

    public static void register(final Context context, String str, String str2, String str3, int i, Class<?> cls) {
        if (context != null) {
            identifier = Util.sanitizeAppIdentifier(str);
            secret = str2;
            urlString = str3;
            mode = i;
            mainActivity = cls;
            if (validateHandler == null) {
                validateHandler = new Handler() { // from class: net.hockeyapp.android.LoginManager.1
                    @Override // android.os.Handler
                    public void handleMessage(Message message) {
                        if (message.getData().getBoolean("success")) {
                            return;
                        }
                        LoginManager.startLoginActivity(context);
                    }
                };
            }
            Constants.loadFromContext(context);
        }
    }

    public static void verifyLogin(Activity activity, Intent intent) {
        int i;
        if (intent != null && intent.getBooleanExtra(LOGIN_EXIT_KEY, false)) {
            activity.finish();
            return;
        }
        if (activity == null || (i = mode) == 0 || i == 3) {
            return;
        }
        SharedPreferences sharedPreferences = activity.getSharedPreferences("net.hockeyapp.android.login", 0);
        if (sharedPreferences.getInt("mode", -1) != mode) {
            PrefsUtil.applyChanges(sharedPreferences.edit().remove("auid").remove("iuid").putInt("mode", mode));
        }
        String string = sharedPreferences.getString("auid", null);
        String string2 = sharedPreferences.getString("iuid", null);
        boolean z = string == null && string2 == null;
        boolean z2 = string == null && mode == 2;
        boolean z3 = string2 == null && mode == 1;
        if (z || z2 || z3) {
            startLoginActivity(activity);
            return;
        }
        HashMap map = new HashMap();
        if (string != null) {
            map.put("type", "auid");
            map.put("id", string);
        } else if (string2 != null) {
            map.put("type", "iuid");
            map.put("id", string2);
        }
        LoginTask loginTask = new LoginTask(activity, validateHandler, getURLString(3), 3, map);
        loginTask.setShowProgressDialog(false);
        AsyncTaskUtils.execute(loginTask);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void startLoginActivity(Context context) {
        Intent intent = new Intent();
        intent.setFlags(1342177280);
        intent.setClass(context, LoginActivity.class);
        intent.putExtra("url", getURLString(mode));
        intent.putExtra("mode", mode);
        intent.putExtra("secret", secret);
        context.startActivity(intent);
    }

    private static String getURLString(int i) {
        String str;
        if (i == 2) {
            str = "authorize";
        } else if (i == 1) {
            str = "check";
        } else {
            str = i == 3 ? "validate" : "";
        }
        return urlString + "api/3/apps/" + identifier + "/identity/" + str;
    }
}
