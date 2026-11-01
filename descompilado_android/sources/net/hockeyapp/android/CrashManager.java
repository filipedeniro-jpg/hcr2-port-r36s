package net.hockeyapp.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.File;
import java.io.FilenameFilter;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import net.hockeyapp.android.objects.CrashManagerUserInput;
import net.hockeyapp.android.objects.CrashMetaData;
import net.hockeyapp.android.utils.HttpURLConnectionBuilder;
import net.hockeyapp.android.utils.PrefsUtil;
import net.hockeyapp.android.utils.Util;

/* JADX INFO: loaded from: classes2.dex */
public class CrashManager {
    private static final String ALWAYS_SEND_KEY = "always_send_crash_reports";
    private static String identifier = null;
    private static boolean submitting = false;
    private static String urlString;

    public static void register(Context context, String str) {
        register(context, Constants.BASE_URL, str, null);
    }

    public static void register(Context context, String str, CrashManagerListener crashManagerListener) {
        register(context, Constants.BASE_URL, str, crashManagerListener);
    }

    public static void register(Context context, String str, String str2, CrashManagerListener crashManagerListener) {
        initialize(context, str, str2, crashManagerListener, false);
        execute(context, crashManagerListener);
    }

    public static void initialize(Context context, String str, CrashManagerListener crashManagerListener) {
        initialize(context, Constants.BASE_URL, str, crashManagerListener, true);
    }

    public static void initialize(Context context, String str, String str2, CrashManagerListener crashManagerListener) {
        initialize(context, str, str2, crashManagerListener, true);
    }

    public static void execute(Context context, CrashManagerListener crashManagerListener) {
        Boolean boolValueOf = Boolean.valueOf(crashManagerListener != null && crashManagerListener.ignoreDefaultHandler());
        WeakReference weakReference = new WeakReference(context);
        int iHasStackTraces = hasStackTraces(weakReference);
        if (iHasStackTraces != 1) {
            if (iHasStackTraces == 2) {
                if (crashManagerListener != null) {
                    crashManagerListener.onConfirmedCrashesFound();
                }
                sendCrashes(weakReference, crashManagerListener, boolValueOf.booleanValue());
                return;
            }
            registerHandler(weakReference, crashManagerListener, boolValueOf.booleanValue());
            return;
        }
        Boolean boolValueOf2 = Boolean.valueOf(context instanceof Activity ? false : true);
        Boolean boolValueOf3 = Boolean.valueOf(PreferenceManager.getDefaultSharedPreferences(context).getBoolean(ALWAYS_SEND_KEY, false) | boolValueOf2.booleanValue());
        if (crashManagerListener != null) {
            boolValueOf3 = Boolean.valueOf(Boolean.valueOf(boolValueOf3.booleanValue() | crashManagerListener.shouldAutoUploadCrashes()).booleanValue() | crashManagerListener.onCrashesFound());
            crashManagerListener.onNewCrashesFound();
        }
        if (!boolValueOf3.booleanValue()) {
            showDialog(weakReference, crashManagerListener, boolValueOf.booleanValue());
        } else {
            sendCrashes(weakReference, crashManagerListener, boolValueOf.booleanValue());
        }
    }

    public static int hasStackTraces(WeakReference<Context> weakReference) {
        String[] strArrSearchForStackTraces = searchForStackTraces();
        if (strArrSearchForStackTraces == null || strArrSearchForStackTraces.length <= 0) {
            return 0;
        }
        List listAsList = null;
        if (weakReference != null) {
            try {
                Context context = weakReference.get();
                if (context != null) {
                    listAsList = Arrays.asList(context.getSharedPreferences(Constants.SDK_NAME, 0).getString("ConfirmedFilenames", "").split("\\|"));
                }
            } catch (Exception unused) {
            }
        }
        if (listAsList == null) {
            return 1;
        }
        for (String str : strArrSearchForStackTraces) {
            if (!listAsList.contains(str)) {
                return 1;
            }
        }
        return 2;
    }

    public static void submitStackTraces(WeakReference<Context> weakReference, CrashManagerListener crashManagerListener) throws Throwable {
        submitStackTraces(weakReference, crashManagerListener, null);
    }

    public static void submitStackTraces(WeakReference<Context> weakReference, CrashManagerListener crashManagerListener, CrashMetaData crashMetaData) throws Throwable {
        HttpURLConnection httpURLConnection;
        HttpURLConnection httpURLConnectionBuild;
        String str;
        String str2;
        HttpURLConnection httpURLConnection2;
        boolean z;
        String[] strArrSearchForStackTraces = searchForStackTraces();
        if (strArrSearchForStackTraces == null || strArrSearchForStackTraces.length <= 0) {
            return;
        }
        Log.d("HockeyApp", "Found " + strArrSearchForStackTraces.length + " stacktrace(s).");
        Boolean boolValueOf = false;
        for (int i = 0; i < strArrSearchForStackTraces.length; i++) {
            try {
                String str3 = strArrSearchForStackTraces[i];
                String strContentsOfFile = contentsOfFile(weakReference, str3);
                if (strContentsOfFile.length() > 0) {
                    Log.d("HockeyApp", "Transmitting crash data: \n" + strContentsOfFile);
                    String strContentsOfFile2 = contentsOfFile(weakReference, str3.replace(".stacktrace", ".user"));
                    String strContentsOfFile3 = contentsOfFile(weakReference, str3.replace(".stacktrace", ".contact"));
                    if (crashMetaData != null) {
                        String userID = crashMetaData.getUserID();
                        if (userID != null && userID.length() > 0) {
                            strContentsOfFile2 = userID;
                        }
                        String userEmail = crashMetaData.getUserEmail();
                        if (userEmail != null && userEmail.length() > 0) {
                            strContentsOfFile3 = userEmail;
                        }
                    }
                    String strContentsOfFile4 = contentsOfFile(weakReference, str3.replace(".stacktrace", ".description"));
                    String userDescription = crashMetaData != null ? crashMetaData.getUserDescription() : "";
                    if (strContentsOfFile4 == null || strContentsOfFile4.length() <= 0) {
                        z = true;
                    } else if (userDescription != null && userDescription.length() > 0) {
                        userDescription = String.format("%s\n\nLog:\n%s", userDescription, strContentsOfFile4);
                        z = true;
                    } else {
                        z = true;
                        userDescription = String.format("Log:\n%s", strContentsOfFile4);
                    }
                    HashMap map = new HashMap();
                    map.put("raw", strContentsOfFile);
                    map.put("userID", strContentsOfFile2);
                    map.put("contact", strContentsOfFile3);
                    map.put("description", userDescription);
                    map.put("sdk", Constants.SDK_NAME);
                    map.put("sdk_version", Constants.SDK_VERSION);
                    httpURLConnectionBuild = new HttpURLConnectionBuilder(getURLString()).setRequestMethod(HttpRequest.METHOD_POST).writeFormFields(map).build();
                    try {
                        try {
                            int responseCode = httpURLConnectionBuild.getResponseCode();
                            if (responseCode != 202 && responseCode != 201) {
                                z = false;
                            }
                            boolValueOf = Boolean.valueOf(z);
                            httpURLConnection2 = httpURLConnectionBuild;
                        } catch (Throwable th) {
                            th = th;
                            httpURLConnection = httpURLConnectionBuild;
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                            }
                            if (boolValueOf.booleanValue()) {
                                Log.d("HockeyApp", "Transmission succeeded");
                                deleteStackTrace(weakReference, strArrSearchForStackTraces[i]);
                                if (crashManagerListener != null) {
                                    crashManagerListener.onCrashesSent();
                                    deleteRetryCounter(weakReference, strArrSearchForStackTraces[i], crashManagerListener.getMaxRetryAttempts());
                                }
                            } else {
                                Log.d("HockeyApp", "Transmission failed, will retry on next register() call");
                                if (crashManagerListener != null) {
                                    crashManagerListener.onCrashesNotSent();
                                    updateRetryCounter(weakReference, strArrSearchForStackTraces[i], crashManagerListener.getMaxRetryAttempts());
                                }
                            }
                            throw th;
                        }
                    } catch (Exception e) {
                        e = e;
                        e.printStackTrace();
                        if (httpURLConnectionBuild != null) {
                            httpURLConnectionBuild.disconnect();
                        }
                        if (boolValueOf.booleanValue()) {
                            Log.d("HockeyApp", "Transmission succeeded");
                            deleteStackTrace(weakReference, strArrSearchForStackTraces[i]);
                            if (crashManagerListener != null) {
                                crashManagerListener.onCrashesSent();
                                str2 = strArrSearchForStackTraces[i];
                            }
                        } else {
                            Log.d("HockeyApp", "Transmission failed, will retry on next register() call");
                            if (crashManagerListener != null) {
                                crashManagerListener.onCrashesNotSent();
                                str = strArrSearchForStackTraces[i];
                                updateRetryCounter(weakReference, str, crashManagerListener.getMaxRetryAttempts());
                            }
                        }
                    }
                } else {
                    httpURLConnection2 = null;
                }
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                if (boolValueOf.booleanValue()) {
                    Log.d("HockeyApp", "Transmission succeeded");
                    deleteStackTrace(weakReference, strArrSearchForStackTraces[i]);
                    if (crashManagerListener != null) {
                        crashManagerListener.onCrashesSent();
                        str2 = strArrSearchForStackTraces[i];
                        deleteRetryCounter(weakReference, str2, crashManagerListener.getMaxRetryAttempts());
                    }
                } else {
                    Log.d("HockeyApp", "Transmission failed, will retry on next register() call");
                    if (crashManagerListener != null) {
                        crashManagerListener.onCrashesNotSent();
                        str = strArrSearchForStackTraces[i];
                        updateRetryCounter(weakReference, str, crashManagerListener.getMaxRetryAttempts());
                    }
                }
            } catch (Exception e2) {
                e = e2;
                httpURLConnectionBuild = null;
            } catch (Throwable th2) {
                th = th2;
                httpURLConnection = null;
            }
        }
    }

    public static void deleteStackTraces(WeakReference<Context> weakReference) {
        String[] strArrSearchForStackTraces = searchForStackTraces();
        if (strArrSearchForStackTraces == null || strArrSearchForStackTraces.length <= 0) {
            return;
        }
        Log.d("HockeyApp", "Found " + strArrSearchForStackTraces.length + " stacktrace(s).");
        for (int i = 0; i < strArrSearchForStackTraces.length; i++) {
            if (weakReference != null) {
                try {
                    Log.d("HockeyApp", "Delete stacktrace " + strArrSearchForStackTraces[i] + ".");
                    deleteStackTrace(weakReference, strArrSearchForStackTraces[i]);
                    Context context = weakReference.get();
                    if (context != null) {
                        context.deleteFile(strArrSearchForStackTraces[i]);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* JADX INFO: renamed from: net.hockeyapp.android.CrashManager$6, reason: invalid class name */
    static /* synthetic */ class AnonymousClass6 {
        static final /* synthetic */ int[] $SwitchMap$net$hockeyapp$android$objects$CrashManagerUserInput = new int[CrashManagerUserInput.values().length];

        static {
            try {
                $SwitchMap$net$hockeyapp$android$objects$CrashManagerUserInput[CrashManagerUserInput.CrashManagerUserInputDontSend.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$net$hockeyapp$android$objects$CrashManagerUserInput[CrashManagerUserInput.CrashManagerUserInputAlwaysSend.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$net$hockeyapp$android$objects$CrashManagerUserInput[CrashManagerUserInput.CrashManagerUserInputSend.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static boolean handleUserInput(CrashManagerUserInput crashManagerUserInput, CrashMetaData crashMetaData, CrashManagerListener crashManagerListener, WeakReference<Context> weakReference, boolean z) {
        int i = AnonymousClass6.$SwitchMap$net$hockeyapp$android$objects$CrashManagerUserInput[crashManagerUserInput.ordinal()];
        if (i == 1) {
            if (crashManagerListener != null) {
                crashManagerListener.onUserDeniedCrashes();
            }
            deleteStackTraces(weakReference);
            registerHandler(weakReference, crashManagerListener, z);
            return true;
        }
        if (i != 2) {
            if (i != 3) {
                return false;
            }
            sendCrashes(weakReference, crashManagerListener, z, crashMetaData);
            return true;
        }
        Context context = weakReference != null ? weakReference.get() : null;
        if (context == null) {
            return false;
        }
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(ALWAYS_SEND_KEY, true).commit();
        sendCrashes(weakReference, crashManagerListener, z, crashMetaData);
        return true;
    }

    public static void resetAlwaysSend(WeakReference<Context> weakReference) {
        Context context;
        if (weakReference == null || (context = weakReference.get()) == null) {
            return;
        }
        PreferenceManager.getDefaultSharedPreferences(context).edit().remove(ALWAYS_SEND_KEY).commit();
    }

    private static void initialize(Context context, String str, String str2, CrashManagerListener crashManagerListener, boolean z) {
        if (context != null) {
            urlString = str;
            identifier = Util.sanitizeAppIdentifier(str2);
            Constants.loadFromContext(context);
            if (identifier == null) {
                identifier = Constants.APP_PACKAGE;
            }
            if (z) {
                registerHandler(new WeakReference(context), crashManagerListener, Boolean.valueOf(crashManagerListener != null && crashManagerListener.ignoreDefaultHandler()).booleanValue());
            }
        }
    }

    private static void showDialog(final WeakReference<Context> weakReference, final CrashManagerListener crashManagerListener, final boolean z) {
        Context context = weakReference != null ? weakReference.get() : null;
        if (context == null) {
            return;
        }
        if (crashManagerListener == null || !crashManagerListener.onHandleAlertView()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle(Strings.get(crashManagerListener, 0));
            builder.setMessage(Strings.get(crashManagerListener, 1));
            builder.setNegativeButton(Strings.get(crashManagerListener, 2), new DialogInterface.OnClickListener() { // from class: net.hockeyapp.android.CrashManager.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    CrashManager.handleUserInput(CrashManagerUserInput.CrashManagerUserInputDontSend, null, crashManagerListener, weakReference, z);
                }
            });
            builder.setNeutralButton(Strings.get(crashManagerListener, 3), new DialogInterface.OnClickListener() { // from class: net.hockeyapp.android.CrashManager.2
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    CrashManager.handleUserInput(CrashManagerUserInput.CrashManagerUserInputAlwaysSend, null, crashManagerListener, weakReference, z);
                }
            });
            builder.setPositiveButton(Strings.get(crashManagerListener, 4), new DialogInterface.OnClickListener() { // from class: net.hockeyapp.android.CrashManager.3
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    CrashManager.handleUserInput(CrashManagerUserInput.CrashManagerUserInputSend, null, crashManagerListener, weakReference, z);
                }
            });
            builder.create().show();
        }
    }

    private static void sendCrashes(WeakReference<Context> weakReference, CrashManagerListener crashManagerListener, boolean z) {
        sendCrashes(weakReference, crashManagerListener, z, null);
    }

    /* JADX WARN: Type inference failed for: r2v3, types: [net.hockeyapp.android.CrashManager$4] */
    private static void sendCrashes(final WeakReference<Context> weakReference, final CrashManagerListener crashManagerListener, boolean z, final CrashMetaData crashMetaData) {
        saveConfirmedStackTraces(weakReference);
        registerHandler(weakReference, crashManagerListener, z);
        if (submitting) {
            return;
        }
        submitting = true;
        new Thread() { // from class: net.hockeyapp.android.CrashManager.4
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() throws Throwable {
                CrashManager.submitStackTraces(weakReference, crashManagerListener, crashMetaData);
                boolean unused = CrashManager.submitting = false;
            }
        }.start();
    }

    private static void registerHandler(WeakReference<Context> weakReference, CrashManagerListener crashManagerListener, boolean z) {
        if (Constants.APP_VERSION != null && Constants.APP_PACKAGE != null) {
            Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
            if (defaultUncaughtExceptionHandler != null) {
                Log.d("HockeyApp", "Current handler class = " + defaultUncaughtExceptionHandler.getClass().getName());
            }
            if (defaultUncaughtExceptionHandler instanceof ExceptionHandler) {
                ((ExceptionHandler) defaultUncaughtExceptionHandler).setListener(crashManagerListener);
                return;
            } else {
                Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(defaultUncaughtExceptionHandler, crashManagerListener, z));
                return;
            }
        }
        Log.d("HockeyApp", "Exception handler not set because version or package is null.");
    }

    private static String getURLString() {
        return urlString + "api/2/apps/" + identifier + "/crashes/";
    }

    private static void updateRetryCounter(WeakReference<Context> weakReference, String str, int i) {
        Context context;
        if (i == -1 || weakReference == null || (context = weakReference.get()) == null) {
            return;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.SDK_NAME, 0);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        int i2 = sharedPreferences.getInt("RETRY_COUNT: " + str, 0);
        if (i2 >= i) {
            deleteStackTrace(weakReference, str);
            deleteRetryCounter(weakReference, str, i);
            return;
        }
        editorEdit.putInt("RETRY_COUNT: " + str, i2 + 1);
        editorEdit.commit();
    }

    private static void deleteRetryCounter(WeakReference<Context> weakReference, String str, int i) {
        Context context;
        if (weakReference == null || (context = weakReference.get()) == null) {
            return;
        }
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(Constants.SDK_NAME, 0).edit();
        editorEdit.remove("RETRY_COUNT: " + str);
        editorEdit.commit();
    }

    private static void deleteStackTrace(WeakReference<Context> weakReference, String str) {
        Context context;
        if (weakReference == null || (context = weakReference.get()) == null) {
            return;
        }
        context.deleteFile(str);
        context.deleteFile(str.replace(".stacktrace", ".user"));
        context.deleteFile(str.replace(".stacktrace", ".contact"));
        context.deleteFile(str.replace(".stacktrace", ".description"));
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0043, code lost:
    
        if (r0 != null) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0045, code lost:
    
        r0.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x004f, code lost:
    
        if (r0 != null) goto L24;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String contentsOfFile(java.lang.ref.WeakReference<android.content.Context> r4, java.lang.String r5) throws java.lang.Throwable {
        /*
            r0 = 0
            if (r4 == 0) goto L57
            java.lang.Object r4 = r4.get()
            android.content.Context r4 = (android.content.Context) r4
            if (r4 == 0) goto L57
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L3d java.io.IOException -> L3f java.io.FileNotFoundException -> L4f
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L3d java.io.IOException -> L3f java.io.FileNotFoundException -> L4f
            java.io.FileInputStream r4 = r4.openFileInput(r5)     // Catch: java.lang.Throwable -> L3d java.io.IOException -> L3f java.io.FileNotFoundException -> L4f
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L3d java.io.IOException -> L3f java.io.FileNotFoundException -> L4f
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L3d java.io.IOException -> L3f java.io.FileNotFoundException -> L4f
        L1e:
            java.lang.String r4 = r2.readLine()     // Catch: java.lang.Throwable -> L35 java.io.IOException -> L38 java.io.FileNotFoundException -> L3b
            if (r4 == 0) goto L31
            r1.append(r4)     // Catch: java.lang.Throwable -> L35 java.io.IOException -> L38 java.io.FileNotFoundException -> L3b
            java.lang.String r4 = "line.separator"
            java.lang.String r4 = java.lang.System.getProperty(r4)     // Catch: java.lang.Throwable -> L35 java.io.IOException -> L38 java.io.FileNotFoundException -> L3b
            r1.append(r4)     // Catch: java.lang.Throwable -> L35 java.io.IOException -> L38 java.io.FileNotFoundException -> L3b
            goto L1e
        L31:
            r2.close()     // Catch: java.io.IOException -> L52
            goto L52
        L35:
            r4 = move-exception
            r0 = r2
            goto L49
        L38:
            r4 = move-exception
            r0 = r2
            goto L40
        L3b:
            r0 = r2
            goto L4f
        L3d:
            r4 = move-exception
            goto L49
        L3f:
            r4 = move-exception
        L40:
            r4.printStackTrace()     // Catch: java.lang.Throwable -> L3d
            if (r0 == 0) goto L52
        L45:
            r0.close()     // Catch: java.io.IOException -> L52
            goto L52
        L49:
            if (r0 == 0) goto L4e
            r0.close()     // Catch: java.io.IOException -> L4e
        L4e:
            throw r4
        L4f:
            if (r0 == 0) goto L52
            goto L45
        L52:
            java.lang.String r4 = r1.toString()
            return r4
        L57:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: net.hockeyapp.android.CrashManager.contentsOfFile(java.lang.ref.WeakReference, java.lang.String):java.lang.String");
    }

    private static void saveConfirmedStackTraces(WeakReference<Context> weakReference) {
        Context context;
        if (weakReference == null || (context = weakReference.get()) == null) {
            return;
        }
        try {
            String[] strArrSearchForStackTraces = searchForStackTraces();
            SharedPreferences.Editor editorEdit = context.getSharedPreferences(Constants.SDK_NAME, 0).edit();
            editorEdit.putString("ConfirmedFilenames", joinArray(strArrSearchForStackTraces, "|"));
            PrefsUtil.applyChanges(editorEdit);
        } catch (Exception unused) {
        }
    }

    private static String joinArray(String[] strArr, String str) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < strArr.length; i++) {
            stringBuffer.append(strArr[i]);
            if (i < strArr.length - 1) {
                stringBuffer.append(str);
            }
        }
        return stringBuffer.toString();
    }

    private static String[] searchForStackTraces() {
        if (Constants.FILES_PATH != null) {
            Log.d("HockeyApp", "Looking for exceptions in: " + Constants.FILES_PATH);
            File file = new File(Constants.FILES_PATH + com.appsflyer.share.Constants.URL_PATH_DELIMITER);
            if (!file.mkdir() && !file.exists()) {
                return new String[0];
            }
            return file.list(new FilenameFilter() { // from class: net.hockeyapp.android.CrashManager.5
                @Override // java.io.FilenameFilter
                public boolean accept(File file2, String str) {
                    return str.endsWith(".stacktrace");
                }
            });
        }
        Log.d("HockeyApp", "Can't search for exception as file path is null.");
        return null;
    }
}
