package com.chartboost.sdk.Libraries;

import android.content.Context;
import com.chartboost.sdk.impl.bh;
import com.chartboost.sdk.impl.s;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class f {
    public final File a;
    public final File b;
    private final AtomicReference<com.chartboost.sdk.Model.e> c;
    private final g d;
    private final AtomicReference<g> e;
    private s f;

    public f(s sVar, Context context, AtomicReference<com.chartboost.sdk.Model.e> atomicReference) {
        File[] fileArrListFiles;
        g[] gVarArr;
        f fVar = this;
        fVar.f = sVar;
        fVar.d = new g(context.getCacheDir());
        fVar.e = new AtomicReference<>();
        fVar.c = atomicReference;
        try {
            File fileB = sVar.b();
            if (fileB != null) {
                fVar.e.set(new g(fileB));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        fVar.b = new File(fVar.d.a, "track");
        fVar.a = new File(fVar.d.a, SettingsJsonConstants.SESSION_KEY);
        g[] gVarArr2 = {fVar.d, fVar.e.get()};
        int length = gVarArr2.length;
        int i = 0;
        while (i < length) {
            g gVar = gVarArr2[i];
            try {
                boolean z = gVar == fVar.d;
                if (gVar == null || !(z || a())) {
                    gVarArr2 = gVarArr2;
                } else {
                    long jCurrentTimeMillis = System.currentTimeMillis() - TimeUnit.DAYS.toMillis(atomicReference.get().w);
                    File file = new File(gVar.a, "templates");
                    if (file.exists() && (fileArrListFiles = file.listFiles()) != null) {
                        int length2 = fileArrListFiles.length;
                        int i2 = 0;
                        while (i2 < length2) {
                            File file2 = fileArrListFiles[i2];
                            if (file2.isDirectory()) {
                                File[] fileArrListFiles2 = file2.listFiles();
                                if (fileArrListFiles2 != null) {
                                    int length3 = fileArrListFiles2.length;
                                    int i3 = 0;
                                    while (i3 < length3) {
                                        File file3 = fileArrListFiles2[i3];
                                        if ((z || file3.lastModified() < jCurrentTimeMillis) && !file3.delete()) {
                                            StringBuilder sb = new StringBuilder();
                                            sb.append("Unable to delete ");
                                            sb.append(file3.getPath());
                                            CBLogging.b("FileCache", sb.toString());
                                        }
                                        try {
                                            i3++;
                                            gVarArr2 = gVarArr2;
                                        } catch (Exception e2) {
                                            e = e2;
                                            CBLogging.a("FileCache", "Exception while cleaning up templates directory at " + gVar.f.getPath(), e);
                                            e.printStackTrace();
                                            i++;
                                            fVar = this;
                                            gVarArr2 = gVarArr2;
                                        }
                                    }
                                }
                                gVarArr = gVarArr2;
                                File[] fileArrListFiles3 = file2.listFiles();
                                if (fileArrListFiles3 != null && fileArrListFiles3.length == 0 && !file2.delete()) {
                                    CBLogging.b("FileCache", "Unable to delete " + file2.getPath());
                                }
                            } else {
                                gVarArr = gVarArr2;
                            }
                            i2++;
                            gVarArr2 = gVarArr;
                        }
                    }
                    gVarArr2 = gVarArr2;
                    File file4 = new File(gVar.a, ".adId");
                    if (file4.exists() && ((z || file4.lastModified() < jCurrentTimeMillis) && !file4.delete())) {
                        CBLogging.b("FileCache", "Unable to delete " + file4.getPath());
                    }
                }
            } catch (Exception e3) {
                e = e3;
                gVarArr2 = gVarArr2;
            }
            i++;
            fVar = this;
            gVarArr2 = gVarArr2;
        }
    }

    public synchronized byte[] a(File file) {
        byte[] bArrB = null;
        if (file == null) {
            return null;
        }
        try {
            bArrB = bh.b(file);
        } catch (Exception e) {
            CBLogging.a("FileCache", "Error loading cache from disk", e);
            com.chartboost.sdk.Tracking.a.a(getClass(), "readByteArrayFromDisk", e);
        }
        return bArrB;
    }

    public String a(String str) {
        File file = new File(d().g, str);
        if (file.exists()) {
            return file.getPath();
        }
        return null;
    }

    public boolean a() {
        try {
            String strC = this.f.c();
            if (strC != null && strC.equals("mounted") && !com.chartboost.sdk.i.n) {
                return true;
            }
        } catch (Exception e) {
            com.chartboost.sdk.Tracking.a.a(getClass(), "isExternalStorageAvailable", e);
        }
        CBLogging.e("FileCache", "External Storage unavailable");
        return false;
    }

    public boolean b(String str) {
        if (d().d == null || str == null) {
            return false;
        }
        return new File(d().d, str).exists();
    }

    public JSONArray b() {
        JSONArray jSONArray = new JSONArray();
        String[] list = d().g.list();
        if (list != null) {
            for (String str : list) {
                if (!str.equals(".nomedia") && !str.endsWith(".tmp")) {
                    jSONArray.put(str);
                }
            }
        }
        return jSONArray;
    }

    public JSONObject c() {
        String[] list;
        JSONObject jSONObject = new JSONObject();
        try {
            File file = d().a;
            for (String str : this.c.get().x) {
                if (!str.equals("templates")) {
                    File file2 = new File(file, str);
                    JSONArray jSONArray = new JSONArray();
                    if (file2.exists() && (list = file2.list()) != null) {
                        for (String str2 : list) {
                            if (!str2.equals(".nomedia") && !str2.endsWith(".tmp")) {
                                jSONArray.put(str2);
                            }
                        }
                    }
                    e.a(jSONObject, str, jSONArray);
                }
            }
        } catch (Exception e) {
            com.chartboost.sdk.Tracking.a.a(getClass(), "getWebViewCacheAssets", e);
        }
        return jSONObject;
    }

    public g d() {
        if (a()) {
            g gVar = this.e.get();
            if (gVar == null) {
                try {
                    File fileB = this.f.b();
                    if (fileB != null) {
                        this.e.compareAndSet(null, new g(fileB));
                        gVar = this.e.get();
                    }
                } catch (Exception e) {
                    com.chartboost.sdk.Tracking.a.a(getClass(), "currentLocations", e);
                }
            }
            if (gVar != null) {
                return gVar;
            }
        }
        return this.d;
    }

    public long b(File file) {
        long jB = 0;
        if (file != null) {
            try {
                if (file.isDirectory()) {
                    File[] fileArrListFiles = file.listFiles();
                    if (fileArrListFiles == null) {
                        return 0L;
                    }
                    for (File file2 : fileArrListFiles) {
                        jB += b(file2);
                    }
                    return jB;
                }
            } catch (Exception e) {
                com.chartboost.sdk.Tracking.a.a(f.class, "getFolderSize", e);
                return 0L;
            }
        }
        if (file != null) {
            return file.length();
        }
        return 0L;
    }

    public JSONObject e() {
        JSONObject jSONObject = new JSONObject();
        g gVar = this.e.get();
        if (gVar != null) {
            e.a(jSONObject, ".chartboost-external-folder-size", Long.valueOf(b(gVar.a)));
        }
        e.a(jSONObject, ".chartboost-internal-folder-size", Long.valueOf(b(this.d.a)));
        File file = d().a;
        String[] list = file.list();
        if (list != null && list.length > 0) {
            for (String str : list) {
                File file2 = new File(file, str);
                JSONObject jSONObject2 = new JSONObject();
                e.a(jSONObject2, file2.getName() + "-size", Long.valueOf(b(file2)));
                String[] list2 = file2.list();
                if (list2 != null) {
                    e.a(jSONObject2, com.my.target.i.aa, Integer.valueOf(list2.length));
                }
                e.a(jSONObject, file2.getName(), jSONObject2);
            }
        }
        return jSONObject;
    }

    /* JADX WARN: Code duplicated, block: B:19:0x0030 A[Catch: IOException -> 0x003d, PHI: r1
  0x0030: PHI (r1v4 java.io.RandomAccessFile) = (r1v3 java.io.RandomAccessFile), (r1v5 java.io.RandomAccessFile) binds: [B:18:0x002e, B:23:0x003a] A[DONT_GENERATE, DONT_INLINE], TRY_ENTER, TRY_LEAVE, TryCatch #6 {IOException -> 0x003d, blocks: (B:6:0x0019, B:19:0x0030), top: B:39:0x0003 }] */
    public void c(File file) throws Throwable {
        RandomAccessFile randomAccessFile = null;
        try {
            try {
                try {
                    RandomAccessFile randomAccessFile2 = new RandomAccessFile(file, "rw");
                    try {
                        randomAccessFile2.seek(0L);
                        int i = randomAccessFile2.read();
                        randomAccessFile2.seek(0L);
                        randomAccessFile2.write(i);
                        randomAccessFile2.close();
                    } catch (FileNotFoundException e) {
                        e = e;
                        randomAccessFile = randomAccessFile2;
                        CBLogging.a("FileCache", "File not found when attempting to touch", e);
                        if (randomAccessFile != null) {
                            randomAccessFile.close();
                        }
                    } catch (IOException e2) {
                        e = e2;
                        randomAccessFile = randomAccessFile2;
                        CBLogging.a("FileCache", "IOException when attempting to touch file", e);
                        if (randomAccessFile != null) {
                            randomAccessFile.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        randomAccessFile = randomAccessFile2;
                        if (randomAccessFile != null) {
                            try {
                                randomAccessFile.close();
                            } catch (IOException unused) {
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (FileNotFoundException e3) {
                e = e3;
            } catch (IOException e4) {
                e = e4;
            }
        } catch (IOException unused2) {
        }
    }
}
