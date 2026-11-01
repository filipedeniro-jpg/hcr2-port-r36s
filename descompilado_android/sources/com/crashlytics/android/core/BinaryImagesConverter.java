package com.crashlytics.android.core;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import io.fabric.sdk.android.Fabric;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class BinaryImagesConverter {
    private static final String DATA_DIR = "/data";
    private final Context context;
    private final FileIdStrategy fileIdStrategy;

    interface FileIdStrategy {
        String createId(File file) throws IOException;
    }

    BinaryImagesConverter(Context context, FileIdStrategy fileIdStrategy) {
        this.context = context;
        this.fileIdStrategy = fileIdStrategy;
    }

    byte[] convert(String str) throws IOException {
        return generateBinaryImagesJsonString(parseProcMapsJsonFromString(str));
    }

    byte[] convert(BufferedReader bufferedReader) throws IOException {
        return generateBinaryImagesJsonString(parseProcMapsJsonFromStream(bufferedReader));
    }

    private JSONArray parseProcMapsJsonFromStream(BufferedReader bufferedReader) throws IOException {
        JSONArray jSONArray = new JSONArray();
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                return jSONArray;
            }
            JSONObject jSONObjectJsonFromMapEntryString = jsonFromMapEntryString(line);
            if (jSONObjectJsonFromMapEntryString != null) {
                jSONArray.put(jSONObjectJsonFromMapEntryString);
            }
        }
    }

    private JSONArray parseProcMapsJsonFromString(String str) {
        JSONArray jSONArray = new JSONArray();
        try {
            for (String str2 : joinMapsEntries(new JSONObject(str).getJSONArray("maps")).split("\\|")) {
                JSONObject jSONObjectJsonFromMapEntryString = jsonFromMapEntryString(str2);
                if (jSONObjectJsonFromMapEntryString != null) {
                    jSONArray.put(jSONObjectJsonFromMapEntryString);
                }
            }
            return jSONArray;
        } catch (JSONException e) {
            Fabric.getLogger().w(CrashlyticsCore.TAG, "Unable to parse proc maps string", e);
            return jSONArray;
        }
    }

    private JSONObject jsonFromMapEntryString(String str) {
        ProcMapEntry procMapEntry = ProcMapEntryParser.parse(str);
        if (procMapEntry != null && isRelevant(procMapEntry)) {
            try {
                try {
                    return createBinaryImageJson(this.fileIdStrategy.createId(getLibraryFile(procMapEntry.path)), procMapEntry);
                } catch (JSONException e) {
                    Fabric.getLogger().d(CrashlyticsCore.TAG, "Could not create a binary image json string", e);
                    return null;
                }
            } catch (IOException e2) {
                Fabric.getLogger().d(CrashlyticsCore.TAG, "Could not generate ID for file " + procMapEntry.path, e2);
            }
        }
        return null;
    }

    private File getLibraryFile(String str) {
        File file = new File(str);
        return !file.exists() ? correctDataPath(file) : file;
    }

    private File correctDataPath(File file) {
        if (Build.VERSION.SDK_INT < 9 || !file.getAbsolutePath().startsWith(DATA_DIR)) {
            return file;
        }
        try {
            return new File(this.context.getPackageManager().getApplicationInfo(this.context.getPackageName(), 0).nativeLibraryDir, file.getName());
        } catch (PackageManager.NameNotFoundException e) {
            Fabric.getLogger().e(CrashlyticsCore.TAG, "Error getting ApplicationInfo", e);
            return file;
        }
    }

    private static byte[] generateBinaryImagesJsonString(JSONArray jSONArray) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("binary_images", jSONArray);
            return jSONObject.toString().getBytes();
        } catch (JSONException e) {
            Fabric.getLogger().w(CrashlyticsCore.TAG, "Binary images string is null", e);
            return new byte[0];
        }
    }

    private static JSONObject createBinaryImageJson(String str, ProcMapEntry procMapEntry) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("base_address", procMapEntry.address);
        jSONObject.put("size", procMapEntry.size);
        jSONObject.put("name", procMapEntry.path);
        jSONObject.put("uuid", str);
        return jSONObject;
    }

    private static String joinMapsEntries(JSONArray jSONArray) throws JSONException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < jSONArray.length(); i++) {
            sb.append(jSONArray.getString(i));
        }
        return sb.toString();
    }

    private static boolean isRelevant(ProcMapEntry procMapEntry) {
        return (procMapEntry.perms.indexOf(120) == -1 || procMapEntry.path.indexOf(47) == -1) ? false : true;
    }
}
