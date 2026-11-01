package net.hockeyapp.android.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;
import net.hockeyapp.android.UpdateInfoListener;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class VersionHelper {
    public static final String VERSION_MAX = "99.0";
    private int currentVersionCode;
    private UpdateInfoListener listener;
    private JSONObject newest;
    private ArrayList<JSONObject> sortedVersions;

    private Object getSeparator() {
        return "<hr style='border-top: 1px solid #c8c8c8; border-bottom: 0px; margin: 40px 10px 0px 10px;' />";
    }

    public VersionHelper(Context context, String str, UpdateInfoListener updateInfoListener) {
        this.listener = updateInfoListener;
        loadVersions(context, str);
        sortVersions();
    }

    private void loadVersions(Context context, String str) {
        this.newest = new JSONObject();
        this.sortedVersions = new ArrayList<>();
        this.currentVersionCode = this.listener.getCurrentVersionCode();
        try {
            JSONArray jSONArray = new JSONArray(str);
            int currentVersionCode = this.listener.getCurrentVersionCode();
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                boolean z = true;
                boolean z2 = jSONObject.getInt("version") > currentVersionCode;
                if (jSONObject.getInt("version") != currentVersionCode || !isNewerThanLastUpdateTime(context, jSONObject.getLong("timestamp"))) {
                    z = false;
                }
                if (z2 || z) {
                    this.newest = jSONObject;
                    currentVersionCode = jSONObject.getInt("version");
                }
                this.sortedVersions.add(jSONObject);
            }
        } catch (NullPointerException | JSONException unused) {
        }
    }

    private void sortVersions() {
        Collections.sort(this.sortedVersions, new Comparator<JSONObject>() { // from class: net.hockeyapp.android.utils.VersionHelper.1
            @Override // java.util.Comparator
            public int compare(JSONObject jSONObject, JSONObject jSONObject2) {
                try {
                    if (jSONObject.getInt("version") > jSONObject2.getInt("version")) {
                    }
                } catch (NullPointerException | JSONException unused) {
                }
                return 0;
            }
        });
    }

    public String getVersionString() {
        return failSafeGetStringFromJSON(this.newest, "shortversion", "") + " (" + failSafeGetStringFromJSON(this.newest, "version", "") + ")";
    }

    public String getFileDateString() {
        return new SimpleDateFormat("dd.MM.yyyy").format(new Date(failSafeGetLongFromJSON(this.newest, "timestamp", 0L) * 1000));
    }

    public long getFileSizeBytes() {
        boolean zBooleanValue = Boolean.valueOf(failSafeGetStringFromJSON(this.newest, "external", "false")).booleanValue();
        long jFailSafeGetLongFromJSON = failSafeGetLongFromJSON(this.newest, "appsize", 0L);
        if (zBooleanValue && jFailSafeGetLongFromJSON == 0) {
            return -1L;
        }
        return jFailSafeGetLongFromJSON;
    }

    private static String failSafeGetStringFromJSON(JSONObject jSONObject, String str, String str2) {
        try {
            return jSONObject.getString(str);
        } catch (JSONException unused) {
            return str2;
        }
    }

    private static long failSafeGetLongFromJSON(JSONObject jSONObject, String str, long j) {
        try {
            return jSONObject.getLong(str);
        } catch (JSONException unused) {
            return j;
        }
    }

    public String getReleaseNotes(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<body style='padding: 0px 0px 20px 0px'>");
        int i = 0;
        for (JSONObject jSONObject : this.sortedVersions) {
            if (i > 0) {
                sb.append(getSeparator());
                if (z) {
                    sb.append(getRestoreButton(i, jSONObject));
                }
            }
            sb.append(getVersionLine(i, jSONObject));
            sb.append(getVersionNotes(i, jSONObject));
            i++;
        }
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }

    private String getRestoreButton(int i, JSONObject jSONObject) {
        StringBuilder sb = new StringBuilder();
        String versionID = getVersionID(jSONObject);
        if (versionID.length() > 0) {
            sb.append("<a href='restore:" + versionID + "'  style='background: #c8c8c8; color: #000; display: block; float: right; padding: 7px; margin: 0px 10px 10px; text-decoration: none;'>Restore</a>");
        }
        return sb.toString();
    }

    private String getVersionID(JSONObject jSONObject) {
        try {
            return jSONObject.getString("id");
        } catch (JSONException unused) {
            return "";
        }
    }

    private String getVersionLine(int i, JSONObject jSONObject) {
        StringBuilder sb = new StringBuilder();
        int versionCode = getVersionCode(this.newest);
        int versionCode2 = getVersionCode(jSONObject);
        String versionName = getVersionName(jSONObject);
        sb.append("<div style='padding: 20px 10px 10px;'><strong>");
        if (i == 0) {
            sb.append("Newest version:");
        } else {
            sb.append("Version " + versionName + " (" + versionCode2 + "): ");
            if (versionCode2 != versionCode && versionCode2 == this.currentVersionCode) {
                this.currentVersionCode = -1;
                sb.append("[INSTALLED]");
            }
        }
        sb.append("</strong></div>");
        return sb.toString();
    }

    private int getVersionCode(JSONObject jSONObject) {
        try {
            return jSONObject.getInt("version");
        } catch (JSONException unused) {
            return 0;
        }
    }

    private String getVersionName(JSONObject jSONObject) {
        try {
            return jSONObject.getString("shortversion");
        } catch (JSONException unused) {
            return "";
        }
    }

    private String getVersionNotes(int i, JSONObject jSONObject) {
        StringBuilder sb = new StringBuilder();
        String strFailSafeGetStringFromJSON = failSafeGetStringFromJSON(jSONObject, "notes", "");
        sb.append("<div style='padding: 0px 10px;'>");
        if (strFailSafeGetStringFromJSON.trim().length() == 0) {
            sb.append("<em>No information.</em>");
        } else {
            sb.append(strFailSafeGetStringFromJSON);
        }
        sb.append("</div>");
        return sb.toString();
    }

    public static int compareVersionStrings(String str, String str2) {
        if (str != null && str2 != null) {
            try {
                Scanner scanner = new Scanner(str.replaceAll("\\-.*", ""));
                Scanner scanner2 = new Scanner(str2.replaceAll("\\-.*", ""));
                scanner.useDelimiter("\\.");
                scanner2.useDelimiter("\\.");
                while (scanner.hasNextInt() && scanner2.hasNextInt()) {
                    int iNextInt = scanner.nextInt();
                    int iNextInt2 = scanner2.nextInt();
                    if (iNextInt < iNextInt2) {
                        return -1;
                    }
                    if (iNextInt > iNextInt2) {
                        return 1;
                    }
                }
                if (scanner.hasNextInt()) {
                    return 1;
                }
                if (scanner2.hasNextInt()) {
                    return -1;
                }
            } catch (Exception unused) {
            }
        }
        return 0;
    }

    public static boolean isNewerThanLastUpdateTime(Context context, long j) {
        if (context == null) {
            return false;
        }
        try {
            return j > (new File(context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).sourceDir).lastModified() / 1000) + 1800;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String mapGoogleVersion(String str) {
        if (str == null || str.equalsIgnoreCase("L")) {
            return "5.0";
        }
        if (str.equalsIgnoreCase("M")) {
            return "6.0";
        }
        return Pattern.matches("^[a-zA-Z]+", str) ? VERSION_MAX : str;
    }
}
