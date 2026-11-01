package net.hockeyapp.android;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.TextUtils;
import java.lang.ref.WeakReference;
import java.util.Date;
import net.hockeyapp.android.tasks.CheckUpdateTask;
import net.hockeyapp.android.tasks.CheckUpdateTaskWithUI;
import net.hockeyapp.android.utils.AsyncTaskUtils;
import net.hockeyapp.android.utils.Util;

/* JADX INFO: loaded from: classes2.dex */
public class UpdateManager {
    private static UpdateManagerListener lastListener;
    private static CheckUpdateTask updateTask;

    public static void register(Activity activity, String str) {
        register(activity, str, true);
    }

    public static void register(Activity activity, String str, boolean z) {
        register(activity, str, (UpdateManagerListener) null, z);
    }

    public static void register(Activity activity, String str, UpdateManagerListener updateManagerListener) {
        register(activity, Constants.BASE_URL, str, updateManagerListener, true);
    }

    public static void register(Activity activity, String str, UpdateManagerListener updateManagerListener, boolean z) {
        register(activity, Constants.BASE_URL, str, updateManagerListener, z);
    }

    public static void register(Activity activity, String str, String str2, UpdateManagerListener updateManagerListener) {
        register(activity, str, str2, updateManagerListener, true);
    }

    public static void register(Activity activity, String str, String str2, UpdateManagerListener updateManagerListener, boolean z) {
        String strSanitizeAppIdentifier = Util.sanitizeAppIdentifier(str2);
        lastListener = updateManagerListener;
        WeakReference weakReference = new WeakReference(activity);
        if ((Util.fragmentsSupported().booleanValue() && dialogShown(weakReference)) || checkExpiryDate(weakReference, updateManagerListener)) {
            return;
        }
        if ((updateManagerListener == null || !updateManagerListener.canUpdateInMarket()) && installedFromMarket(weakReference)) {
            return;
        }
        startUpdateTask(weakReference, str, strSanitizeAppIdentifier, updateManagerListener, z);
    }

    public static void registerForBackground(Context context, String str, UpdateManagerListener updateManagerListener) {
        registerForBackground(context, Constants.BASE_URL, str, updateManagerListener);
    }

    public static void registerForBackground(Context context, String str, String str2, UpdateManagerListener updateManagerListener) {
        String strSanitizeAppIdentifier = Util.sanitizeAppIdentifier(str2);
        lastListener = updateManagerListener;
        WeakReference weakReference = new WeakReference(context);
        if (checkExpiryDateForBackground(updateManagerListener)) {
            return;
        }
        if ((updateManagerListener == null || !updateManagerListener.canUpdateInMarket()) && installedFromMarket(weakReference)) {
            return;
        }
        startUpdateTaskForBackground(weakReference, str, strSanitizeAppIdentifier, updateManagerListener);
    }

    public static void unregister() {
        CheckUpdateTask checkUpdateTask = updateTask;
        if (checkUpdateTask != null) {
            checkUpdateTask.cancel(true);
            updateTask.detach();
            updateTask = null;
        }
        lastListener = null;
    }

    private static boolean checkExpiryDate(WeakReference<Activity> weakReference, UpdateManagerListener updateManagerListener) {
        boolean zCheckExpiryDateForBackground = checkExpiryDateForBackground(updateManagerListener);
        boolean zOnBuildExpired = zCheckExpiryDateForBackground ? updateManagerListener.onBuildExpired() : false;
        if (zCheckExpiryDateForBackground && zOnBuildExpired) {
            startExpiryInfoIntent(weakReference);
        }
        return zCheckExpiryDateForBackground;
    }

    private static boolean checkExpiryDateForBackground(UpdateManagerListener updateManagerListener) {
        Date expiryDate;
        return (updateManagerListener == null || (expiryDate = updateManagerListener.getExpiryDate()) == null || new Date().compareTo(expiryDate) <= 0) ? false : true;
    }

    private static boolean installedFromMarket(WeakReference<? extends Context> weakReference) {
        Context context = weakReference.get();
        if (context != null) {
            try {
                return !TextUtils.isEmpty(context.getPackageManager().getInstallerPackageName(context.getPackageName()));
            } catch (Throwable unused) {
            }
        }
        return false;
    }

    private static void startExpiryInfoIntent(WeakReference<Activity> weakReference) {
        Activity activity;
        if (weakReference == null || (activity = weakReference.get()) == null) {
            return;
        }
        activity.finish();
        Intent intent = new Intent(activity, (Class<?>) ExpiryInfoActivity.class);
        intent.addFlags(335544320);
        activity.startActivity(intent);
    }

    private static void startUpdateTask(WeakReference<Activity> weakReference, String str, String str2, UpdateManagerListener updateManagerListener, boolean z) {
        CheckUpdateTask checkUpdateTask = updateTask;
        if (checkUpdateTask == null || checkUpdateTask.getStatus() == AsyncTask.Status.FINISHED) {
            updateTask = new CheckUpdateTaskWithUI(weakReference, str, str2, updateManagerListener, z);
            AsyncTaskUtils.execute(updateTask);
        } else {
            updateTask.attach(weakReference);
        }
    }

    private static void startUpdateTaskForBackground(WeakReference<Context> weakReference, String str, String str2, UpdateManagerListener updateManagerListener) {
        CheckUpdateTask checkUpdateTask = updateTask;
        if (checkUpdateTask == null || checkUpdateTask.getStatus() == AsyncTask.Status.FINISHED) {
            updateTask = new CheckUpdateTask(weakReference, str, str2, updateManagerListener);
            AsyncTaskUtils.execute(updateTask);
        } else {
            updateTask.attach(weakReference);
        }
    }

    @TargetApi(11)
    private static boolean dialogShown(WeakReference<Activity> weakReference) {
        Activity activity;
        return (weakReference == null || (activity = weakReference.get()) == null || activity.getFragmentManager().findFragmentByTag("hockey_update_dialog") == null) ? false : true;
    }

    public static UpdateManagerListener getLastListener() {
        return lastListener;
    }
}
