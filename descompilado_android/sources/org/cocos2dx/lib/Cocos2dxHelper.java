package org.cocos2dx.lib;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.os.Process;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.appsflyer.share.Constants;
import com.chukong.cocosplay.client.CocosPlayClient;
import com.enhance.gameservice.IGameTuningService;
import io.fabric.sdk.android.services.network.UrlUtils;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public class Cocos2dxHelper {
    private static final int BOOST_TIME = 7;
    private static final String PREFS_NAME = "Cocos2dxPrefsFile";
    private static final int RUNNABLES_PER_FRAME = 5;
    private static boolean sAccelerometerEnabled;
    private static Activity sActivity;
    private static boolean sActivityVisible;
    private static AssetManager sAssetManager;
    private static Cocos2dxMusic sCocos2dMusic;
    private static Cocos2dxSound sCocos2dSound;
    private static Cocos2dxAccelerometer sCocos2dxAccelerometer;
    private static Cocos2dxHelperListener sCocos2dxHelperListener;
    private static String sFileDirectory;
    private static String sPackageName;
    private static Set<PreferenceManager.OnActivityResultListener> onActivityResultListeners = new LinkedHashSet();
    private static IGameTuningService mGameServiceBinder = null;
    private static boolean sInited = false;
    private static ServiceConnection connection = new ServiceConnection() { // from class: org.cocos2dx.lib.Cocos2dxHelper.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            IGameTuningService unused = Cocos2dxHelper.mGameServiceBinder = IGameTuningService.Stub.asInterface(iBinder);
            Cocos2dxHelper.fastLoading(7);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            Cocos2dxHelper.sActivity.getApplicationContext().unbindService(Cocos2dxHelper.connection);
        }
    };

    public interface Cocos2dxHelperListener {
        void runOnGLThread(Runnable runnable);

        void showDialog(String str, String str2);

        void showEditTextDialog(String str, String str2, int i, int i2, int i3, int i4);
    }

    private static native void nativeSetApkPath(String str);

    private static native void nativeSetContext(Context context, AssetManager assetManager);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetEditTextDialogResult(byte[] bArr);

    public static void runOnGLThread(Runnable runnable) {
        ((Cocos2dxActivity) sActivity).runOnGLThread(runnable);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void init(Activity activity) {
        if (sInited) {
            return;
        }
        ApplicationInfo applicationInfo = activity.getApplicationInfo();
        sCocos2dxHelperListener = (Cocos2dxHelperListener) activity;
        sPackageName = applicationInfo.packageName;
        if (CocosPlayClient.isEnabled() && !CocosPlayClient.isDemo()) {
            sFileDirectory = CocosPlayClient.getGameRoot();
        } else {
            sFileDirectory = activity.getFilesDir().getAbsolutePath();
        }
        nativeSetApkPath(applicationInfo.sourceDir);
        sCocos2dxAccelerometer = new Cocos2dxAccelerometer(activity);
        sCocos2dMusic = new Cocos2dxMusic(activity);
        sCocos2dSound = new Cocos2dxSound(activity);
        sAssetManager = activity.getAssets();
        nativeSetContext(activity, sAssetManager);
        Cocos2dxBitmap.setContext(activity);
        sActivity = activity;
        sInited = true;
        Intent intent = new Intent(IGameTuningService.class.getName());
        intent.setPackage("com.enhance.gameservice");
        activity.getApplicationContext().bindService(intent, connection, 1);
    }

    public static Activity getActivity() {
        return sActivity;
    }

    public static void addOnActivityResultListener(PreferenceManager.OnActivityResultListener onActivityResultListener) {
        onActivityResultListeners.add(onActivityResultListener);
    }

    public static Set<PreferenceManager.OnActivityResultListener> getOnActivityResultListeners() {
        return onActivityResultListeners;
    }

    public static boolean isActivityVisible() {
        return sActivityVisible;
    }

    public static String getCocos2dxPackageName() {
        return sPackageName;
    }

    public static String getCocos2dxWritablePath() {
        return sFileDirectory;
    }

    public static String[] listAssetPath(String str) {
        try {
            if (str.startsWith("assets/")) {
                str = str.substring(7);
            }
            if (str.endsWith(Constants.URL_PATH_DELIMITER)) {
                str = str.substring(0, str.length() - 1);
            }
            return getAssetManager().list(str);
        } catch (IOException unused) {
            return new String[0];
        }
    }

    public static String getCurrentLanguage() {
        return Locale.getDefault().getLanguage();
    }

    public static String getDeviceModel() {
        return Build.MODEL;
    }

    public static AssetManager getAssetManager() {
        return sAssetManager;
    }

    public static void enableAccelerometer() {
        sAccelerometerEnabled = true;
        sCocos2dxAccelerometer.enable();
    }

    public static void setAccelerometerInterval(float f) {
        sCocos2dxAccelerometer.setInterval(f);
    }

    public static void disableAccelerometer() {
        sAccelerometerEnabled = false;
        sCocos2dxAccelerometer.disable();
    }

    public static void setKeepScreenOn(boolean z) {
        ((Cocos2dxActivity) sActivity).setKeepScreenOn(z);
    }

    public static boolean openURL(String str) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(str));
            sActivity.startActivity(intent);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static void preloadBackgroundMusic(String str) {
        sCocos2dMusic.preloadBackgroundMusic(str);
    }

    public static void playBackgroundMusic(String str, boolean z) {
        sCocos2dMusic.playBackgroundMusic(str, z);
    }

    public static void resumeBackgroundMusic() {
        sCocos2dMusic.resumeBackgroundMusic();
    }

    public static void pauseBackgroundMusic() {
        sCocos2dMusic.pauseBackgroundMusic();
    }

    public static void stopBackgroundMusic() {
        sCocos2dMusic.stopBackgroundMusic();
    }

    public static void rewindBackgroundMusic() {
        sCocos2dMusic.rewindBackgroundMusic();
    }

    public static boolean isBackgroundMusicPlaying() {
        return sCocos2dMusic.isBackgroundMusicPlaying();
    }

    public static float getBackgroundMusicVolume() {
        return sCocos2dMusic.getBackgroundVolume();
    }

    public static void setBackgroundMusicVolume(float f) {
        sCocos2dMusic.setBackgroundVolume(f);
    }

    public static void preloadEffect(String str) {
        sCocos2dSound.preloadEffect(str);
    }

    public static int playEffect(String str, boolean z, float f, float f2, float f3) {
        return sCocos2dSound.playEffect(str, z, f, f2, f3);
    }

    public static void resumeEffect(int i) {
        sCocos2dSound.resumeEffect(i);
    }

    public static void pauseEffect(int i) {
        sCocos2dSound.pauseEffect(i);
    }

    public static void stopEffect(int i) {
        sCocos2dSound.stopEffect(i);
    }

    public static float getEffectsVolume() {
        return sCocos2dSound.getEffectsVolume();
    }

    public static void setEffectsVolume(float f) {
        sCocos2dSound.setEffectsVolume(f);
    }

    public static void unloadEffect(String str) {
        sCocos2dSound.unloadEffect(str);
    }

    public static void setEffectVolume(int i, float f) {
        sCocos2dSound.setEffectVolume(i, f);
    }

    public static void setEffectVolumeAndPan(int i, float f, float f2) {
        sCocos2dSound.setEffectVolumeAndPan(i, f, f2);
    }

    public static void setEffectRate(int i, float f) {
        sCocos2dSound.setEffectRate(i, f);
    }

    public static int getBackgroundMusicPosition() {
        return sCocos2dMusic.getBackgroundMusicPosition();
    }

    public static void setBackgroundMusicPosition(int i) {
        sCocos2dMusic.setBackgroundMusicPosition(i);
    }

    public static void pauseAllEffects() {
        sCocos2dSound.pauseAllEffects();
    }

    public static void resumeAllEffects() {
        sCocos2dSound.resumeAllEffects();
    }

    public static void stopAllEffects() {
        sCocos2dSound.stopAllEffects();
    }

    public static void end() {
        sCocos2dMusic.end();
        sCocos2dSound.end();
    }

    public static void onResume() {
        sActivityVisible = true;
        if (sAccelerometerEnabled) {
            sCocos2dxAccelerometer.enable();
        }
    }

    public static void onPause() {
        sActivityVisible = false;
        if (sAccelerometerEnabled) {
            sCocos2dxAccelerometer.disable();
        }
    }

    public static void onEnterBackground() {
        sCocos2dSound.onEnterBackground();
        sCocos2dMusic.onEnterBackground();
    }

    public static void onEnterForeground() {
        sCocos2dSound.onEnterForeground();
        sCocos2dMusic.onEnterForeground();
    }

    public static void terminateProcess() {
        Process.killProcess(Process.myPid());
    }

    private static void showDialog(String str, String str2) {
        sCocos2dxHelperListener.showDialog(str, str2);
    }

    private static void showEditTextDialog(String str, String str2, int i, int i2, int i3, int i4) {
        sCocos2dxHelperListener.showEditTextDialog(str, str2, i, i2, i3, i4);
    }

    public static void setEditTextDialogResult(String str) {
        try {
            final byte[] bytes = str.getBytes(UrlUtils.UTF8);
            sCocos2dxHelperListener.runOnGLThread(new Runnable() { // from class: org.cocos2dx.lib.Cocos2dxHelper.2
                @Override // java.lang.Runnable
                public void run() {
                    Cocos2dxHelper.nativeSetEditTextDialogResult(bytes);
                }
            });
        } catch (UnsupportedEncodingException unused) {
        }
    }

    public static int getDPI() {
        Display defaultDisplay;
        if (sActivity == null) {
            return -1;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = sActivity.getWindowManager();
        if (windowManager == null || (defaultDisplay = windowManager.getDefaultDisplay()) == null) {
            return -1;
        }
        defaultDisplay.getMetrics(displayMetrics);
        return (int) (displayMetrics.density * 160.0f);
    }

    public static boolean getBoolForKey(String str, boolean z) {
        SharedPreferences sharedPreferences = sActivity.getSharedPreferences(PREFS_NAME, 0);
        try {
            return sharedPreferences.getBoolean(str, z);
        } catch (Exception e) {
            e.printStackTrace();
            Object obj = sharedPreferences.getAll().get(str);
            if (obj instanceof String) {
                return Boolean.parseBoolean(obj.toString());
            }
            if (obj instanceof Integer) {
                return ((Integer) obj).intValue() != 0;
            }
            return (obj instanceof Float) && ((Float) obj).floatValue() != 0.0f;
        }
    }

    public static int getIntegerForKey(String str, int i) {
        SharedPreferences sharedPreferences = sActivity.getSharedPreferences(PREFS_NAME, 0);
        try {
            return sharedPreferences.getInt(str, i);
        } catch (Exception e) {
            e.printStackTrace();
            Object obj = sharedPreferences.getAll().get(str);
            if (obj instanceof String) {
                return Integer.parseInt(obj.toString());
            }
            if (obj instanceof Float) {
                return ((Float) obj).intValue();
            }
            return ((obj instanceof Boolean) && ((Boolean) obj).booleanValue()) ? 1 : 0;
        }
    }

    public static float getFloatForKey(String str, float f) {
        SharedPreferences sharedPreferences = sActivity.getSharedPreferences(PREFS_NAME, 0);
        try {
            return sharedPreferences.getFloat(str, f);
        } catch (Exception e) {
            e.printStackTrace();
            Object obj = sharedPreferences.getAll().get(str);
            if (obj instanceof String) {
                return Float.parseFloat(obj.toString());
            }
            if (obj instanceof Integer) {
                return ((Integer) obj).floatValue();
            }
            return ((obj instanceof Boolean) && ((Boolean) obj).booleanValue()) ? 1.0f : 0.0f;
        }
    }

    public static double getDoubleForKey(String str, double d) {
        return getFloatForKey(str, (float) d);
    }

    public static String getStringForKey(String str, String str2) {
        SharedPreferences sharedPreferences = sActivity.getSharedPreferences(PREFS_NAME, 0);
        try {
            return sharedPreferences.getString(str, str2);
        } catch (Exception e) {
            e.printStackTrace();
            return sharedPreferences.getAll().get(str).toString();
        }
    }

    public static void setBoolForKey(String str, boolean z) {
        SharedPreferences.Editor editorEdit = sActivity.getSharedPreferences(PREFS_NAME, 0).edit();
        editorEdit.putBoolean(str, z);
        editorEdit.commit();
    }

    public static void setIntegerForKey(String str, int i) {
        SharedPreferences.Editor editorEdit = sActivity.getSharedPreferences(PREFS_NAME, 0).edit();
        editorEdit.putInt(str, i);
        editorEdit.commit();
    }

    public static void setFloatForKey(String str, float f) {
        SharedPreferences.Editor editorEdit = sActivity.getSharedPreferences(PREFS_NAME, 0).edit();
        editorEdit.putFloat(str, f);
        editorEdit.commit();
    }

    public static void setDoubleForKey(String str, double d) {
        SharedPreferences.Editor editorEdit = sActivity.getSharedPreferences(PREFS_NAME, 0).edit();
        editorEdit.putFloat(str, (float) d);
        editorEdit.commit();
    }

    public static void setStringForKey(String str, String str2) {
        SharedPreferences.Editor editorEdit = sActivity.getSharedPreferences(PREFS_NAME, 0).edit();
        editorEdit.putString(str, str2);
        editorEdit.commit();
    }

    public static int setResolutionPercent(int i) {
        try {
            if (mGameServiceBinder != null) {
                return mGameServiceBinder.setPreferredResolution(i);
            }
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int setFPS(int i) {
        try {
            if (mGameServiceBinder != null) {
                return mGameServiceBinder.setFramePerSecond(i);
            }
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int fastLoading(int i) {
        try {
            if (mGameServiceBinder != null) {
                return mGameServiceBinder.boostUp(i);
            }
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int getTemperature() {
        try {
            if (mGameServiceBinder != null) {
                return mGameServiceBinder.getAbstractTemperature();
            }
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int setLowPowerMode(boolean z) {
        try {
            if (mGameServiceBinder != null) {
                return mGameServiceBinder.setGamePowerSaving(z);
            }
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
