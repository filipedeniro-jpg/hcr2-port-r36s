package com.unity3d.services.core.misc;

import android.os.Handler;
import android.os.Looper;
import com.facebook.appevents.AppEventsConstants;
import com.unity3d.services.core.log.DeviceLog;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class Utilities {
    public static void runOnUiThread(Runnable runnable) {
        runOnUiThread(runnable, 0L);
    }

    public static void runOnUiThread(Runnable runnable, long j) {
        Handler handler = new Handler(Looper.getMainLooper());
        if (j > 0) {
            handler.postDelayed(runnable, j);
        } else {
            handler.post(runnable);
        }
    }

    public static String Sha256(String str) {
        return Sha256(str.getBytes());
    }

    public static String Sha256(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(CommonUtils.SHA256_INSTANCE);
            messageDigest.update(bArr, 0, bArr.length);
            return toHexString(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            DeviceLog.exception("SHA-256 algorithm not found", e);
            return null;
        }
    }

    public static String Sha256(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(CommonUtils.SHA256_INSTANCE);
            byte[] bArr = new byte[4096];
            while (true) {
                int i = inputStream.read(bArr);
                if (i != -1) {
                    messageDigest.update(bArr, 0, i);
                } else {
                    return toHexString(messageDigest.digest());
                }
            }
        } catch (NoSuchAlgorithmException e) {
            DeviceLog.exception("SHA-256 algorithm not found", e);
            return null;
        }
    }

    public static String toHexString(byte[] bArr) {
        String str = "";
        for (byte b : bArr) {
            int i = b & 255;
            if (i <= 15) {
                str = str + AppEventsConstants.EVENT_PARAM_VALUE_NO;
            }
            str = str + Integer.toHexString(i);
        }
        return str;
    }

    public static boolean writeFile(File file, String str) throws Throwable {
        boolean z = false;
        if (file == null) {
            return false;
        }
        FileOutputStream fileOutputStream = null;
        try {
            try {
                FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                try {
                    fileOutputStream2.write(str.getBytes());
                    fileOutputStream2.flush();
                    try {
                        fileOutputStream2.close();
                    } catch (Exception e) {
                        DeviceLog.exception("Error closing FileOutputStream", e);
                    }
                    z = true;
                } catch (Exception e2) {
                    e = e2;
                    fileOutputStream = fileOutputStream2;
                    DeviceLog.exception("Could not write file", e);
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Exception e3) {
                            DeviceLog.exception("Error closing FileOutputStream", e3);
                        }
                    }
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Exception e4) {
                            DeviceLog.exception("Error closing FileOutputStream", e4);
                        }
                    }
                    throw th;
                }
            } catch (Exception e5) {
                e = e5;
            }
            if (z) {
                DeviceLog.debug("Wrote file: " + file.getAbsolutePath());
            }
            return z;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static String readFile(File file) {
        BufferedReader bufferedReader;
        FileReader fileReader;
        String str = null;
        if (file == null) {
            return null;
        }
        if (file.exists() && file.canRead()) {
            try {
                fileReader = new FileReader(file);
                try {
                    bufferedReader = new BufferedReader(fileReader);
                    String strConcat = "";
                    while (true) {
                        try {
                            String line = bufferedReader.readLine();
                            if (line == null) {
                                break;
                            }
                            strConcat = strConcat.concat(line);
                        } catch (Exception e) {
                            e = e;
                            DeviceLog.exception("Problem reading file", e);
                        }
                    }
                    str = strConcat;
                } catch (Exception e2) {
                    e = e2;
                    bufferedReader = null;
                }
            } catch (Exception e3) {
                e = e3;
                bufferedReader = null;
                fileReader = null;
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception e4) {
                    DeviceLog.exception("Couldn't close BufferedReader", e4);
                }
            }
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (Exception e5) {
                    DeviceLog.exception("Couldn't close FileReader", e5);
                }
            }
            return str;
        }
        DeviceLog.error("File did not exist or couldn't be read");
        return null;
    }

    public static byte[] readFileBytes(File file) throws IOException {
        if (file == null) {
            return null;
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bArr = new byte[(int) file.length()];
        int i = 0;
        long j = 4096;
        int length = file.length() < j ? (int) file.length() : 4096;
        while (true) {
            int i2 = fileInputStream.read(bArr, i, length);
            if (i2 <= 0) {
                fileInputStream.close();
                return bArr;
            }
            i += i2;
            if (file.length() - ((long) i) < j) {
                length = ((int) file.length()) - i;
            }
        }
    }

    public static JSONObject mergeJsonObjects(JSONObject jSONObject, JSONObject jSONObject2) throws JSONException {
        if (jSONObject == null) {
            return jSONObject2;
        }
        if (jSONObject2 == null) {
            return jSONObject;
        }
        JSONObject jSONObject3 = new JSONObject();
        Iterator<String> itKeys = jSONObject2.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            jSONObject3.put(next, jSONObject2.get(next));
        }
        Iterator<String> itKeys2 = jSONObject.keys();
        while (itKeys2.hasNext()) {
            String next2 = itKeys2.next();
            if (jSONObject3.has(next2) && (jSONObject3.get(next2) instanceof JSONObject) && (jSONObject.get(next2) instanceof JSONObject)) {
                jSONObject3.put(next2, mergeJsonObjects(jSONObject.getJSONObject(next2), jSONObject3.getJSONObject(next2)));
            } else {
                jSONObject3.put(next2, jSONObject.get(next2));
            }
        }
        return jSONObject3;
    }
}
