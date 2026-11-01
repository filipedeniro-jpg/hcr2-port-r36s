package net.hockeyapp.android.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.Settings;
import com.my.target.i;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Locale;
import net.hockeyapp.android.Constants;
import net.hockeyapp.android.Tracking;
import net.hockeyapp.android.UpdateManagerListener;
import net.hockeyapp.android.utils.VersionCache;
import net.hockeyapp.android.utils.VersionHelper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class CheckUpdateTask extends AsyncTask<Void, String, JSONArray> {
    protected static final String APK = "apk";
    protected static final String INTENT_EXTRA_JSON = "json";
    protected static final String INTENT_EXTRA_URL = "url";
    private static final int MAX_NUMBER_OF_VERSIONS = 25;
    protected String appIdentifier;
    private Context context;
    protected UpdateManagerListener listener;
    protected Boolean mandatory;
    protected String urlString;
    private long usageTime;

    protected boolean getCachingEnabled() {
        return true;
    }

    public CheckUpdateTask(WeakReference<? extends Context> weakReference, String str) {
        this(weakReference, str, null);
    }

    public CheckUpdateTask(WeakReference<? extends Context> weakReference, String str, String str2) {
        this(weakReference, str, str2, null);
    }

    public CheckUpdateTask(WeakReference<? extends Context> weakReference, String str, String str2, UpdateManagerListener updateManagerListener) {
        this.urlString = null;
        this.appIdentifier = null;
        this.context = null;
        this.mandatory = false;
        this.usageTime = 0L;
        this.appIdentifier = str2;
        this.urlString = str;
        this.listener = updateManagerListener;
        Context context = weakReference != null ? weakReference.get() : null;
        if (context != null) {
            this.context = context.getApplicationContext();
            this.usageTime = Tracking.getUsageTime(context);
            Constants.loadFromContext(context);
        }
    }

    public void attach(WeakReference<? extends Context> weakReference) {
        Context context = weakReference != null ? weakReference.get() : null;
        if (context != null) {
            this.context = context.getApplicationContext();
            Constants.loadFromContext(context);
        }
    }

    public void detach() {
        this.context = null;
    }

    protected int getVersionCode() {
        return Integer.parseInt(Constants.APP_VERSION);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public JSONArray doInBackground(Void... voidArr) {
        try {
            int versionCode = getVersionCode();
            JSONArray jSONArray = new JSONArray(VersionCache.getVersionInfo(this.context));
            if (getCachingEnabled() && findNewVersion(jSONArray, versionCode)) {
                return jSONArray;
            }
            URLConnection uRLConnectionCreateConnection = createConnection(new URL(getURLString(INTENT_EXTRA_JSON)));
            uRLConnectionCreateConnection.connect();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(uRLConnectionCreateConnection.getInputStream());
            String strConvertStreamToString = convertStreamToString(bufferedInputStream);
            bufferedInputStream.close();
            JSONArray jSONArray2 = new JSONArray(strConvertStreamToString);
            if (findNewVersion(jSONArray2, versionCode)) {
                return limitResponseSize(jSONArray2);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    protected URLConnection createConnection(URL url) throws IOException {
        URLConnection uRLConnectionOpenConnection = url.openConnection();
        uRLConnectionOpenConnection.addRequestProperty("User-Agent", "HockeySDK/Android");
        if (Build.VERSION.SDK_INT <= 9) {
            uRLConnectionOpenConnection.setRequestProperty(i.z, "close");
        }
        return uRLConnectionOpenConnection;
    }

    private boolean findNewVersion(JSONArray jSONArray, int i) {
        boolean z = false;
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i2);
                boolean z2 = jSONObject.getInt("version") > i;
                boolean z3 = jSONObject.getInt("version") == i && VersionHelper.isNewerThanLastUpdateTime(this.context, jSONObject.getLong("timestamp"));
                boolean z4 = VersionHelper.compareVersionStrings(jSONObject.getString("minimum_os_version"), VersionHelper.mapGoogleVersion(Build.VERSION.RELEASE)) <= 0;
                if ((z2 || z3) && z4) {
                    if (jSONObject.has("mandatory")) {
                        this.mandatory = Boolean.valueOf(this.mandatory.booleanValue() | jSONObject.getBoolean("mandatory"));
                    }
                    z = true;
                }
            } catch (JSONException unused) {
                return false;
            }
        }
        return z;
    }

    private JSONArray limitResponseSize(JSONArray jSONArray) {
        JSONArray jSONArray2 = new JSONArray();
        for (int i = 0; i < Math.min(jSONArray.length(), 25); i++) {
            try {
                jSONArray2.put(jSONArray.get(i));
            } catch (JSONException unused) {
            }
        }
        return jSONArray2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(JSONArray jSONArray) {
        if (jSONArray != null) {
            UpdateManagerListener updateManagerListener = this.listener;
            if (updateManagerListener != null) {
                updateManagerListener.onUpdateAvailable(jSONArray, getURLString(APK));
                return;
            }
            return;
        }
        UpdateManagerListener updateManagerListener2 = this.listener;
        if (updateManagerListener2 != null) {
            updateManagerListener2.onNoUpdateAvailable();
        }
    }

    protected void cleanUp() {
        this.urlString = null;
        this.appIdentifier = null;
    }

    protected String getURLString(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.urlString);
        sb.append("api/2/apps/");
        String packageName = this.appIdentifier;
        if (packageName == null) {
            packageName = this.context.getPackageName();
        }
        sb.append(packageName);
        sb.append("?format=" + str);
        if (Settings.Secure.getString(this.context.getContentResolver(), i.ANDROID_ID) != null) {
            sb.append("&udid=" + encodeParam(Settings.Secure.getString(this.context.getContentResolver(), i.ANDROID_ID)));
        }
        sb.append("&os=Android");
        sb.append("&os_version=" + encodeParam(Constants.ANDROID_VERSION));
        sb.append("&device=" + encodeParam(Constants.PHONE_MODEL));
        sb.append("&oem=" + encodeParam(Constants.PHONE_MANUFACTURER));
        sb.append("&app_version=" + encodeParam(Constants.APP_VERSION));
        sb.append("&sdk=" + encodeParam(Constants.SDK_NAME));
        sb.append("&sdk_version=" + encodeParam(Constants.SDK_VERSION));
        sb.append("&lang=" + encodeParam(Locale.getDefault().getLanguage()));
        sb.append("&usage_time=" + this.usageTime);
        return sb.toString();
    }

    private String encodeParam(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return "";
        }
    }

    private static String convertStreamToString(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream), 1024);
        StringBuilder sb = new StringBuilder();
        while (true) {
            try {
                try {
                    try {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        sb.append(line + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                        inputStream.close();
                    }
                } catch (Throwable th) {
                    try {
                        inputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    throw th;
                }
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        }
        inputStream.close();
        return sb.toString();
    }
}
