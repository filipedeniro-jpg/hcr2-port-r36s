package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.internal.api.BuildConfigApi;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import com.ironsource.sdk.constants.LocationConst;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Hn, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0575Hn {
    private static final String C = C0575Hn.class.getName();
    private static final Object B = new Object();
    private static final Set<String> E = Collections.synchronizedSet(new HashSet());
    private static final Map<String, Integer> F = Collections.synchronizedMap(new HashMap());
    private static AtomicInteger D = new AtomicInteger();

    /* JADX WARN: Failed to parse debug info
    java.lang.ArrayIndexOutOfBoundsException: Index 11 out of bounds for length 10
    	at jadx.plugins.input.dex.sections.debuginfo.DebugInfoParser.startVar(DebugInfoParser.java:203)
    	at jadx.plugins.input.dex.sections.debuginfo.DebugInfoParser.process(DebugInfoParser.java:125)
    	at jadx.plugins.input.dex.sections.DexCodeReader.getDebugInfo(DexCodeReader.java:122)
    	at jadx.core.dex.nodes.MethodNode.getDebugInfo(MethodNode.java:656)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoAttachVisitor.visit(DebugInfoAttachVisitor.java:38)
     */
    @WorkerThread
    public static void K(C0574Hm c0574Hm, Context context) {
        if (c0574Hm == null || context == null) {
            return;
        }
        synchronized (B) {
            try {
                String strC = I6.C("debuglogs", context);
                File file = new File(context.getFilesDir(), strC);
                if (file.exists()) {
                    int iW = JA.W(context);
                    long length = file.length();
                    if (iW > 0 && length > iW) {
                        boolean zDelete = file.delete();
                        Q(context, 0);
                        E.clear();
                        F.clear();
                        if (zDelete) {
                            Map<String, String> mapB = C0591Id.B(context);
                            mapB.put("subtype", "de_logging");
                            mapB.put("subtype_code", String.valueOf(IronSourceConstants.IS_CAP_SESSION));
                            J("Purge debug events file.\nFile size: " + length + ", DropCounter: " + D.getAndIncrement(), context, mapB);
                            return;
                        }
                        Log.e(AudienceNetworkAds.TAG, "Can't delete debug events file.");
                    }
                }
                JSONObject jSONObjectO = O(c0574Hm);
                FileOutputStream fileOutputStreamOpenFileOutput = context.openFileOutput(strC, 32768);
                fileOutputStreamOpenFileOutput.write((jSONObjectO.toString() + "\n").getBytes());
                fileOutputStreamOpenFileOutput.close();
                R(context);
            } catch (Exception e) {
                C0585Hx.C(e);
            }
        }
    }

    private C0575Hn() {
    }

    public static void B(String eventId) {
        F.remove(eventId);
        E.add(eventId);
    }

    @WorkerThread
    public static boolean C(Context context) {
        return JA.D(context) ? N(context) : M(context);
    }

    @WorkerThread
    public static void D(Context context) {
        synchronized (B) {
            File file = new File(context.getFilesDir(), I6.C("debuglogs", context));
            if (file.exists()) {
                file.delete();
            }
            Q(context, 0);
            E.clear();
            F.clear();
        }
    }

    public static int E(Context context) {
        return context.getApplicationContext().getSharedPreferences(I6.C("DEBUG_PREF", context), 0).getInt("EventCount", 0) - E.size();
    }

    public static void F(String eventId) {
        Integer num = F.get(eventId);
        if (num == null) {
            if (BuildConfigApi.isDebug()) {
                Log.e(C, "Trying to update unrecorded debugLogEvent. adding to OngoingEvent.");
            }
            num = 0;
        } else {
            F.remove(eventId);
        }
        F.put(eventId, Integer.valueOf(num.intValue() + 1));
    }

    public static boolean G(String eventId) {
        return E.contains(eventId) || F.containsKey(eventId);
    }

    @WorkerThread
    public static JSONArray H(Context context) {
        return I(context, -1);
    }

    /* JADX WARN: Code duplicated, block: B:53:0x00c4 A[Catch: IOException -> 0x00cd, all -> 0x00e9, TryCatch #5 {IOException -> 0x00cd, blocks: (B:51:0x00bf, B:53:0x00c4, B:55:0x00c9), top: B:74:0x00bf, outer: #3 }] */
    /* JADX WARN: Code duplicated, block: B:55:0x00c9 A[Catch: IOException -> 0x00cd, all -> 0x00e9, TRY_LEAVE, TryCatch #5 {IOException -> 0x00cd, blocks: (B:51:0x00bf, B:53:0x00c4, B:55:0x00c9), top: B:74:0x00bf, outer: #3 }] */
    /* JADX WARN: Code duplicated, block: B:74:0x00bf A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @WorkerThread
    public static JSONArray I(Context context, int limit) {
        JSONArray jSONArray = new JSONArray();
        synchronized (B) {
            FileInputStream fileInputStreamOpenFileInput = null;
            InputStreamReader inputStreamReader = null;
            BufferedReader bufferedReader = null;
            try {
                try {
                    if (new File(context.getFilesDir(), I6.C("debuglogs", context)).exists()) {
                        fileInputStreamOpenFileInput = context.openFileInput(I6.C("debuglogs", context));
                        inputStreamReader = new InputStreamReader(fileInputStreamOpenFileInput);
                        try {
                            BufferedReader bufferedReader2 = new BufferedReader(inputStreamReader);
                            while (true) {
                                try {
                                    String line = bufferedReader2.readLine();
                                    if (line == null || limit == 0) {
                                        break;
                                    }
                                    JSONObject jSONObject = new JSONObject(line);
                                    if (!jSONObject.has("attempt")) {
                                        jSONObject.put("attempt", String.valueOf(0));
                                    }
                                    String string = jSONObject.getString("id");
                                    if (!E.contains(string)) {
                                        int i = jSONObject.getInt("attempt");
                                        if (F.containsKey(string)) {
                                            jSONObject.put("attempt", String.valueOf(F.get(string)));
                                        } else {
                                            L(string, i);
                                        }
                                        jSONArray.put(jSONObject);
                                        if (limit > 0) {
                                            limit--;
                                        }
                                    }
                                } catch (IOException e) {
                                    e = e;
                                    bufferedReader = bufferedReader2;
                                    C0585Hx.C(e);
                                    if (bufferedReader != null) {
                                        try {
                                            bufferedReader.close();
                                        } catch (IOException ex) {
                                            C0585Hx.C(ex);
                                        }
                                    }
                                    if (inputStreamReader != null) {
                                        inputStreamReader.close();
                                    }
                                    if (fileInputStreamOpenFileInput != null) {
                                        fileInputStreamOpenFileInput.close();
                                    }
                                } catch (JSONException e2) {
                                    e = e2;
                                    bufferedReader = bufferedReader2;
                                    C0585Hx.C(e);
                                    if (bufferedReader != null) {
                                        bufferedReader.close();
                                    }
                                    if (inputStreamReader != null) {
                                        inputStreamReader.close();
                                    }
                                    if (fileInputStreamOpenFileInput != null) {
                                        fileInputStreamOpenFileInput.close();
                                    }
                                } catch (Throwable th) {
                                    th = th;
                                    bufferedReader = bufferedReader2;
                                    inputStreamReader = inputStreamReader;
                                    if (bufferedReader != null) {
                                        try {
                                            bufferedReader.close();
                                        } catch (IOException ex2) {
                                            C0585Hx.C(ex2);
                                            throw th;
                                        }
                                    }
                                    if (inputStreamReader != null) {
                                        inputStreamReader.close();
                                    }
                                    if (fileInputStreamOpenFileInput != null) {
                                        fileInputStreamOpenFileInput.close();
                                    }
                                    throw th;
                                }
                            }
                            bufferedReader = bufferedReader2;
                            inputStreamReader = inputStreamReader;
                        } catch (IOException e3) {
                            e = e3;
                        } catch (JSONException e4) {
                            e = e4;
                        } catch (Throwable th2) {
                            th = th2;
                            inputStreamReader = inputStreamReader;
                        }
                    }
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException ex3) {
                            C0585Hx.C(ex3);
                        }
                    }
                    if (inputStreamReader != null) {
                        inputStreamReader.close();
                    }
                    if (fileInputStreamOpenFileInput != null) {
                        fileInputStreamOpenFileInput.close();
                    }
                } catch (IOException e5) {
                    e = e5;
                } catch (JSONException e6) {
                    e = e6;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
        return jSONArray;
    }

    public static C0574Hm J(String stackTrace, Context context, Map<String, String> map) {
        try {
            C0574Hm c0574Hm = new C0574Hm(LX.D(), LX.B(), new C0565Hd(stackTrace, map, true).A());
            try {
                K(c0574Hm, context);
                return c0574Hm;
            } catch (Exception e) {
                return c0574Hm;
            }
        } catch (Exception e2) {
            return null;
        }
    }

    private static void L(String eventId, int attempt) {
        if (E.contains(eventId)) {
            throw new RuntimeException("finished event should not be updated to OngoingEvent.");
        }
        if (F.containsKey(eventId)) {
            F.remove(eventId);
        }
        F.put(eventId, Integer.valueOf(attempt));
    }

    /* JADX WARN: Code duplicated, block: B:45:0x010b A[Catch: IOException -> 0x0119, all -> 0x0182, TryCatch #0 {IOException -> 0x0119, blocks: (B:43:0x0106, B:45:0x010b, B:47:0x0110, B:49:0x0115), top: B:94:0x0106, outer: #1 }] */
    /* JADX WARN: Code duplicated, block: B:47:0x0110 A[Catch: IOException -> 0x0119, all -> 0x0182, TryCatch #0 {IOException -> 0x0119, blocks: (B:43:0x0106, B:45:0x010b, B:47:0x0110, B:49:0x0115), top: B:94:0x0106, outer: #1 }] */
    /* JADX WARN: Code duplicated, block: B:49:0x0115 A[Catch: IOException -> 0x0119, all -> 0x0182, TRY_LEAVE, TryCatch #0 {IOException -> 0x0119, blocks: (B:43:0x0106, B:45:0x010b, B:47:0x0110, B:49:0x0115), top: B:94:0x0106, outer: #1 }] */
    /* JADX WARN: Code duplicated, block: B:67:0x013d A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:68:0x013f A[Catch: IOException -> 0x014d, all -> 0x0182, TryCatch #7 {IOException -> 0x014d, blocks: (B:66:0x013a, B:68:0x013f, B:70:0x0144, B:72:0x0149), top: B:99:0x013a, outer: #1 }] */
    /* JADX WARN: Code duplicated, block: B:70:0x0144 A[Catch: IOException -> 0x014d, all -> 0x0182, TryCatch #7 {IOException -> 0x014d, blocks: (B:66:0x013a, B:68:0x013f, B:70:0x0144, B:72:0x0149), top: B:99:0x013a, outer: #1 }] */
    /* JADX WARN: Code duplicated, block: B:72:0x0149 A[Catch: IOException -> 0x014d, all -> 0x0182, TRY_LEAVE, TryCatch #7 {IOException -> 0x014d, blocks: (B:66:0x013a, B:68:0x013f, B:70:0x0144, B:72:0x0149), top: B:99:0x013a, outer: #1 }] */
    /* JADX WARN: Code duplicated, block: B:99:0x013a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @WorkerThread
    private static boolean M(Context context) {
        boolean z;
        JSONArray jSONArray = new JSONArray();
        synchronized (B) {
            int i = 0;
            FileInputStream fileInputStreamOpenFileInput = null;
            InputStreamReader inputStreamReader = null;
            BufferedReader bufferedReader = null;
            FileOutputStream fileOutputStreamOpenFileOutput = null;
            try {
                try {
                    if (new File(context.getFilesDir(), I6.C("debuglogs", context)).exists()) {
                        fileInputStreamOpenFileInput = context.openFileInput(I6.C("debuglogs", context));
                        InputStreamReader inputStreamReader2 = new InputStreamReader(fileInputStreamOpenFileInput);
                        try {
                            BufferedReader bufferedReader2 = new BufferedReader(inputStreamReader2);
                            while (true) {
                                try {
                                    String line = bufferedReader2.readLine();
                                    if (line == null) {
                                        break;
                                    }
                                    JSONObject jSONObject = new JSONObject(line);
                                    String string = jSONObject.getString("id");
                                    if (!E.contains(string)) {
                                        if (F.containsKey(string)) {
                                            Integer num = F.get(string);
                                            int retryLimit = num != null ? num.intValue() : 0;
                                            Integer numValueOf = Integer.valueOf(retryLimit);
                                            int iC = JA.C(context);
                                            if (iC > -1) {
                                                int retryLimit2 = iC - 1;
                                                if (numValueOf.intValue() > retryLimit2) {
                                                    B(string);
                                                    i++;
                                                }
                                            }
                                            jSONObject.put("attempt", String.valueOf(numValueOf));
                                            jSONArray.put(jSONObject);
                                        } else {
                                            jSONArray.put(jSONObject);
                                        }
                                    }
                                } catch (IOException e) {
                                    e = e;
                                    bufferedReader = bufferedReader2;
                                    inputStreamReader = inputStreamReader2;
                                    C0585Hx.C(e);
                                    z = false;
                                    if (bufferedReader != null) {
                                        try {
                                            bufferedReader.close();
                                            if (inputStreamReader != null) {
                                                inputStreamReader.close();
                                            }
                                            if (fileInputStreamOpenFileInput != null) {
                                                fileInputStreamOpenFileInput.close();
                                            }
                                            if (fileOutputStreamOpenFileOutput != null) {
                                                fileOutputStreamOpenFileOutput.close();
                                            }
                                        } catch (IOException e2) {
                                            C0585Hx.C(e2);
                                            E.clear();
                                            F.clear();
                                            return z;
                                        }
                                        E.clear();
                                        F.clear();
                                    } else {
                                        if (inputStreamReader != null) {
                                            inputStreamReader.close();
                                        }
                                        if (fileInputStreamOpenFileInput != null) {
                                            fileInputStreamOpenFileInput.close();
                                        }
                                        if (fileOutputStreamOpenFileOutput != null) {
                                            fileOutputStreamOpenFileOutput.close();
                                        }
                                        E.clear();
                                        F.clear();
                                    }
                                } catch (JSONException e3) {
                                    e = e3;
                                    bufferedReader = bufferedReader2;
                                    inputStreamReader = inputStreamReader2;
                                    C0585Hx.C(e);
                                    z = false;
                                    if (bufferedReader != null) {
                                        bufferedReader.close();
                                        if (inputStreamReader != null) {
                                            inputStreamReader.close();
                                        }
                                        if (fileInputStreamOpenFileInput != null) {
                                            fileInputStreamOpenFileInput.close();
                                        }
                                        if (fileOutputStreamOpenFileOutput != null) {
                                            fileOutputStreamOpenFileOutput.close();
                                        }
                                        E.clear();
                                        F.clear();
                                    } else {
                                        if (inputStreamReader != null) {
                                            inputStreamReader.close();
                                        }
                                        if (fileInputStreamOpenFileInput != null) {
                                            fileInputStreamOpenFileInput.close();
                                        }
                                        if (fileOutputStreamOpenFileOutput != null) {
                                            fileOutputStreamOpenFileOutput.close();
                                        }
                                        E.clear();
                                        F.clear();
                                    }
                                } catch (Throwable th) {
                                    th = th;
                                    bufferedReader = bufferedReader2;
                                    inputStreamReader = inputStreamReader2;
                                    if (bufferedReader != null) {
                                        try {
                                            bufferedReader.close();
                                        } catch (IOException e4) {
                                            C0585Hx.C(e4);
                                            E.clear();
                                            F.clear();
                                            throw th;
                                        }
                                    }
                                    if (inputStreamReader != null) {
                                        inputStreamReader.close();
                                    }
                                    if (fileInputStreamOpenFileInput != null) {
                                        fileInputStreamOpenFileInput.close();
                                    }
                                    if (fileOutputStreamOpenFileOutput != null) {
                                        fileOutputStreamOpenFileOutput.close();
                                    }
                                    E.clear();
                                    F.clear();
                                    throw th;
                                }
                            }
                            StringBuilder sb = new StringBuilder();
                            int length = jSONArray.length();
                            for (int i2 = 0; i2 < length; i2++) {
                                sb.append(jSONArray.getJSONObject(i2).toString()).append('\n');
                            }
                            fileOutputStreamOpenFileOutput = context.openFileOutput(I6.C("debuglogs", context), 0);
                            fileOutputStreamOpenFileOutput.write(sb.toString().getBytes());
                            bufferedReader = bufferedReader2;
                            inputStreamReader = inputStreamReader2;
                        } catch (IOException e5) {
                            e = e5;
                            inputStreamReader = inputStreamReader2;
                        } catch (JSONException e6) {
                            e = e6;
                            inputStreamReader = inputStreamReader2;
                        } catch (Throwable th2) {
                            th = th2;
                            inputStreamReader = inputStreamReader2;
                        }
                    }
                    int i3 = E(context);
                    Q(context, i3);
                    if (i > 0) {
                        C0585Hx.D(context, "de_logging", C0584Hw.qB, new C0583Hv("Skipped events.", "skippedEvents = " + i));
                    }
                    z = true;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                            if (inputStreamReader != null) {
                                inputStreamReader.close();
                            }
                            if (fileInputStreamOpenFileInput != null) {
                                fileInputStreamOpenFileInput.close();
                            }
                            if (fileOutputStreamOpenFileOutput != null) {
                                fileOutputStreamOpenFileOutput.close();
                            }
                        } catch (IOException e7) {
                            C0585Hx.C(e7);
                        }
                        E.clear();
                        F.clear();
                    } else {
                        if (inputStreamReader != null) {
                            inputStreamReader.close();
                        }
                        if (fileInputStreamOpenFileInput != null) {
                            fileInputStreamOpenFileInput.close();
                        }
                        if (fileOutputStreamOpenFileOutput != null) {
                            fileOutputStreamOpenFileOutput.close();
                        }
                        E.clear();
                        F.clear();
                    }
                } catch (IOException e8) {
                    e = e8;
                } catch (JSONException e9) {
                    e = e9;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
        return z;
    }

    /* JADX WARN: Code duplicated, block: B:111:0x0177 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:44:0x013e A[Catch: IOException -> 0x0156, all -> 0x01d7, TryCatch #2 {IOException -> 0x0156, blocks: (B:42:0x0139, B:44:0x013e, B:46:0x0143, B:48:0x0148, B:50:0x014d, B:52:0x0152), top: B:109:0x0139, outer: #12 }] */
    /* JADX WARN: Code duplicated, block: B:46:0x0143 A[Catch: IOException -> 0x0156, all -> 0x01d7, TryCatch #2 {IOException -> 0x0156, blocks: (B:42:0x0139, B:44:0x013e, B:46:0x0143, B:48:0x0148, B:50:0x014d, B:52:0x0152), top: B:109:0x0139, outer: #12 }] */
    /* JADX WARN: Code duplicated, block: B:48:0x0148 A[Catch: IOException -> 0x0156, all -> 0x01d7, TryCatch #2 {IOException -> 0x0156, blocks: (B:42:0x0139, B:44:0x013e, B:46:0x0143, B:48:0x0148, B:50:0x014d, B:52:0x0152), top: B:109:0x0139, outer: #12 }] */
    /* JADX WARN: Code duplicated, block: B:50:0x014d A[Catch: IOException -> 0x0156, all -> 0x01d7, TryCatch #2 {IOException -> 0x0156, blocks: (B:42:0x0139, B:44:0x013e, B:46:0x0143, B:48:0x0148, B:50:0x014d, B:52:0x0152), top: B:109:0x0139, outer: #12 }] */
    /* JADX WARN: Code duplicated, block: B:52:0x0152 A[Catch: IOException -> 0x0156, all -> 0x01d7, TRY_LEAVE, TryCatch #2 {IOException -> 0x0156, blocks: (B:42:0x0139, B:44:0x013e, B:46:0x0143, B:48:0x0148, B:50:0x014d, B:52:0x0152), top: B:109:0x0139, outer: #12 }] */
    /* JADX WARN: Code duplicated, block: B:70:0x017a A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:71:0x017c A[Catch: IOException -> 0x0194, all -> 0x01d7, TryCatch #6 {IOException -> 0x0194, blocks: (B:69:0x0177, B:71:0x017c, B:73:0x0181, B:75:0x0186, B:77:0x018b, B:79:0x0190), top: B:111:0x0177, outer: #12 }] */
    /* JADX WARN: Code duplicated, block: B:73:0x0181 A[Catch: IOException -> 0x0194, all -> 0x01d7, TryCatch #6 {IOException -> 0x0194, blocks: (B:69:0x0177, B:71:0x017c, B:73:0x0181, B:75:0x0186, B:77:0x018b, B:79:0x0190), top: B:111:0x0177, outer: #12 }] */
    /* JADX WARN: Code duplicated, block: B:75:0x0186 A[Catch: IOException -> 0x0194, all -> 0x01d7, TryCatch #6 {IOException -> 0x0194, blocks: (B:69:0x0177, B:71:0x017c, B:73:0x0181, B:75:0x0186, B:77:0x018b, B:79:0x0190), top: B:111:0x0177, outer: #12 }] */
    /* JADX WARN: Code duplicated, block: B:77:0x018b A[Catch: IOException -> 0x0194, all -> 0x01d7, TryCatch #6 {IOException -> 0x0194, blocks: (B:69:0x0177, B:71:0x017c, B:73:0x0181, B:75:0x0186, B:77:0x018b, B:79:0x0190), top: B:111:0x0177, outer: #12 }] */
    /* JADX WARN: Code duplicated, block: B:79:0x0190 A[Catch: IOException -> 0x0194, all -> 0x01d7, TRY_LEAVE, TryCatch #6 {IOException -> 0x0194, blocks: (B:69:0x0177, B:71:0x017c, B:73:0x0181, B:75:0x0186, B:77:0x018b, B:79:0x0190), top: B:111:0x0177, outer: #12 }] */
    @WorkerThread
    private static boolean N(Context context) {
        boolean z;
        synchronized (B) {
            int i = 0;
            FileInputStream fileInputStreamOpenFileInput = null;
            InputStreamReader inputStreamReader = null;
            BufferedReader bufferedReader = null;
            FileOutputStream fileOutputStreamOpenFileOutput = null;
            FileInputStream fileInputStreamOpenFileInput2 = null;
            FileOutputStream fileOutputStreamOpenFileOutput2 = null;
            try {
                try {
                    String processSpecificDebugFileName = I6.C("debuglogs", context);
                    File file = new File(context.getFilesDir(), processSpecificDebugFileName);
                    String str = processSpecificDebugFileName + "_temp";
                    P(context, str);
                    if (file.exists()) {
                        fileOutputStreamOpenFileOutput2 = context.openFileOutput(str, 32768);
                        fileInputStreamOpenFileInput = context.openFileInput(processSpecificDebugFileName);
                        InputStreamReader inputStreamReader2 = new InputStreamReader(fileInputStreamOpenFileInput);
                        try {
                            BufferedReader bufferedReader2 = new BufferedReader(inputStreamReader2);
                            while (true) {
                                try {
                                    String line = bufferedReader2.readLine();
                                    if (line == null) {
                                        break;
                                    }
                                    JSONObject jSONObject = new JSONObject(line);
                                    String string = jSONObject.getString("id");
                                    if (!E.contains(string)) {
                                        if (F.containsKey(string)) {
                                            Integer num = F.get(string);
                                            Integer numValueOf = Integer.valueOf(num != null ? num.intValue() : 0);
                                            int iC = JA.C(context);
                                            if (iC > -1) {
                                                int retryLimit = numValueOf.intValue();
                                                if (retryLimit > iC - 1) {
                                                    B(string);
                                                    i++;
                                                }
                                            }
                                            jSONObject.put("attempt", String.valueOf(numValueOf));
                                            fileOutputStreamOpenFileOutput2.write((jSONObject.toString() + '\n').getBytes());
                                        } else {
                                            fileOutputStreamOpenFileOutput2.write((jSONObject.toString() + '\n').getBytes());
                                        }
                                    }
                                } catch (IOException e) {
                                    e = e;
                                    bufferedReader = bufferedReader2;
                                    inputStreamReader = inputStreamReader2;
                                    C0585Hx.C(e);
                                    z = false;
                                    if (bufferedReader != null) {
                                        try {
                                            bufferedReader.close();
                                            if (inputStreamReader != null) {
                                                inputStreamReader.close();
                                            }
                                            if (fileInputStreamOpenFileInput != null) {
                                                fileInputStreamOpenFileInput.close();
                                            }
                                            if (fileOutputStreamOpenFileOutput != null) {
                                                fileOutputStreamOpenFileOutput.close();
                                            }
                                            if (fileInputStreamOpenFileInput2 != null) {
                                                fileInputStreamOpenFileInput2.close();
                                            }
                                            if (fileOutputStreamOpenFileOutput2 != null) {
                                                fileOutputStreamOpenFileOutput2.close();
                                            }
                                        } catch (IOException e2) {
                                            C0585Hx.C(e2);
                                            E.clear();
                                            F.clear();
                                            return z;
                                        }
                                        E.clear();
                                        F.clear();
                                    } else {
                                        if (inputStreamReader != null) {
                                            inputStreamReader.close();
                                        }
                                        if (fileInputStreamOpenFileInput != null) {
                                            fileInputStreamOpenFileInput.close();
                                        }
                                        if (fileOutputStreamOpenFileOutput != null) {
                                            fileOutputStreamOpenFileOutput.close();
                                        }
                                        if (fileInputStreamOpenFileInput2 != null) {
                                            fileInputStreamOpenFileInput2.close();
                                        }
                                        if (fileOutputStreamOpenFileOutput2 != null) {
                                            fileOutputStreamOpenFileOutput2.close();
                                        }
                                        E.clear();
                                        F.clear();
                                    }
                                } catch (JSONException e3) {
                                    e = e3;
                                    bufferedReader = bufferedReader2;
                                    inputStreamReader = inputStreamReader2;
                                    C0585Hx.C(e);
                                    z = false;
                                    if (bufferedReader != null) {
                                        bufferedReader.close();
                                        if (inputStreamReader != null) {
                                            inputStreamReader.close();
                                        }
                                        if (fileInputStreamOpenFileInput != null) {
                                            fileInputStreamOpenFileInput.close();
                                        }
                                        if (fileOutputStreamOpenFileOutput != null) {
                                            fileOutputStreamOpenFileOutput.close();
                                        }
                                        if (fileInputStreamOpenFileInput2 != null) {
                                            fileInputStreamOpenFileInput2.close();
                                        }
                                        if (fileOutputStreamOpenFileOutput2 != null) {
                                            fileOutputStreamOpenFileOutput2.close();
                                        }
                                        E.clear();
                                        F.clear();
                                    } else {
                                        if (inputStreamReader != null) {
                                            inputStreamReader.close();
                                        }
                                        if (fileInputStreamOpenFileInput != null) {
                                            fileInputStreamOpenFileInput.close();
                                        }
                                        if (fileOutputStreamOpenFileOutput != null) {
                                            fileOutputStreamOpenFileOutput.close();
                                        }
                                        if (fileInputStreamOpenFileInput2 != null) {
                                            fileInputStreamOpenFileInput2.close();
                                        }
                                        if (fileOutputStreamOpenFileOutput2 != null) {
                                            fileOutputStreamOpenFileOutput2.close();
                                        }
                                        E.clear();
                                        F.clear();
                                    }
                                } catch (Throwable th) {
                                    th = th;
                                    bufferedReader = bufferedReader2;
                                    inputStreamReader = inputStreamReader2;
                                    if (bufferedReader != null) {
                                        try {
                                            bufferedReader.close();
                                        } catch (IOException e4) {
                                            C0585Hx.C(e4);
                                            E.clear();
                                            F.clear();
                                            throw th;
                                        }
                                    }
                                    if (inputStreamReader != null) {
                                        inputStreamReader.close();
                                    }
                                    if (fileInputStreamOpenFileInput != null) {
                                        fileInputStreamOpenFileInput.close();
                                    }
                                    if (fileOutputStreamOpenFileOutput != null) {
                                        fileOutputStreamOpenFileOutput.close();
                                    }
                                    if (fileInputStreamOpenFileInput2 != null) {
                                        fileInputStreamOpenFileInput2.close();
                                    }
                                    if (fileOutputStreamOpenFileOutput2 != null) {
                                        fileOutputStreamOpenFileOutput2.close();
                                    }
                                    E.clear();
                                    F.clear();
                                    throw th;
                                }
                            }
                            fileInputStreamOpenFileInput2 = context.openFileInput(str);
                            fileOutputStreamOpenFileOutput = context.openFileOutput(processSpecificDebugFileName, 0);
                            byte[] bArr = new byte[1024];
                            while (true) {
                                int i2 = fileInputStreamOpenFileInput2.read(bArr);
                                if (i2 <= 0) {
                                    break;
                                }
                                fileOutputStreamOpenFileOutput.write(bArr, 0, i2);
                            }
                            fileInputStreamOpenFileInput2.close();
                            fileOutputStreamOpenFileOutput2.close();
                            P(context, str);
                            bufferedReader = bufferedReader2;
                            inputStreamReader = inputStreamReader2;
                        } catch (IOException e5) {
                            e = e5;
                            inputStreamReader = inputStreamReader2;
                        } catch (JSONException e6) {
                            e = e6;
                            inputStreamReader = inputStreamReader2;
                        } catch (Throwable th2) {
                            th = th2;
                            inputStreamReader = inputStreamReader2;
                        }
                    }
                    int length = E(context);
                    Q(context, length);
                    if (i > 0) {
                        C0585Hx.D(context, "de_logging", C0584Hw.qB, new C0583Hv("Skipped events.", "skippedEvents = " + i));
                    }
                    z = true;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                            if (inputStreamReader != null) {
                                inputStreamReader.close();
                            }
                            if (fileInputStreamOpenFileInput != null) {
                                fileInputStreamOpenFileInput.close();
                            }
                            if (fileOutputStreamOpenFileOutput != null) {
                                fileOutputStreamOpenFileOutput.close();
                            }
                            if (fileInputStreamOpenFileInput2 != null) {
                                fileInputStreamOpenFileInput2.close();
                            }
                            if (fileOutputStreamOpenFileOutput2 != null) {
                                fileOutputStreamOpenFileOutput2.close();
                            }
                        } catch (IOException e7) {
                            C0585Hx.C(e7);
                        }
                        E.clear();
                        F.clear();
                    } else {
                        if (inputStreamReader != null) {
                            inputStreamReader.close();
                        }
                        if (fileInputStreamOpenFileInput != null) {
                            fileInputStreamOpenFileInput.close();
                        }
                        if (fileOutputStreamOpenFileOutput != null) {
                            fileOutputStreamOpenFileOutput.close();
                        }
                        if (fileInputStreamOpenFileInput2 != null) {
                            fileInputStreamOpenFileInput2.close();
                        }
                        if (fileOutputStreamOpenFileOutput2 != null) {
                            fileOutputStreamOpenFileOutput2.close();
                        }
                        E.clear();
                        F.clear();
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            } catch (IOException e8) {
                e = e8;
            } catch (JSONException e9) {
                e = e9;
            }
        }
        return z;
    }

    private static JSONObject O(C0574Hm event) throws JSONException {
        JSONObject jSONObject;
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("id", UUID.randomUUID().toString());
        jSONObject2.put("type", event.E());
        jSONObject2.put(LocationConst.TIME, C0622Jj.E(event.D()));
        jSONObject2.put("session_time", C0622Jj.E(event.C()));
        jSONObject2.put("session_id", event.B());
        if (event.A() != null) {
            jSONObject = new JSONObject(event.A());
        } else {
            jSONObject = new JSONObject();
        }
        jSONObject2.put("data", jSONObject);
        jSONObject2.put("attempt", String.valueOf(0));
        return jSONObject2;
    }

    private static void P(Context context, String fileName) {
        File file = new File(context.getFilesDir(), fileName);
        if (file.exists() && !file.delete()) {
            C0585Hx.C(new RuntimeException("Can't delete file!"));
        }
    }

    private static void Q(Context context, int count) {
        SharedPreferences.Editor editorEdit = context.getApplicationContext().getSharedPreferences(I6.C("DEBUG_PREF", context), 0).edit();
        if (count < 0) {
            count = 0;
        }
        editorEdit.putInt("EventCount", count).apply();
    }

    private static void R(Context context) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(I6.C("DEBUG_PREF", context), 0);
        Q(context, sharedPreferences.getInt("EventCount", 0) + 1);
    }
}
