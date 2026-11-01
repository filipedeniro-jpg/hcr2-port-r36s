package com.my.target;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Comparator;

/* JADX INFO: compiled from: DiskCache.java */
/* JADX INFO: loaded from: classes2.dex */
public final class cd {
    private static final String jH = "mytargetcache";
    private static final String jI = "mytrg_";
    private static final String jJ = ".img";
    private static final String jK = ".mp4";
    private static final String jL = ".json";
    private static final int jM = 10;
    private static final int jN = 8192;
    private static final long jO = 604800000;

    @NonNull
    private static final FilenameFilter jP = new FilenameFilter() { // from class: com.my.target.cd.1
        @Override // java.io.FilenameFilter
        public boolean accept(File file, String str) {
            return str.startsWith(cd.jI);
        }
    };

    @NonNull
    private static final FilenameFilter jQ = new FilenameFilter() { // from class: com.my.target.cd.2
        @Override // java.io.FilenameFilter
        public boolean accept(File file, String str) {
            return str.endsWith(".mp4");
        }
    };

    @Nullable
    private static volatile cd jR;

    @NonNull
    private final File jS;

    @Nullable
    @WorkerThread
    public static cd t(@NonNull Context context) {
        cd cdVar = jR;
        if (cdVar == null) {
            synchronized (cd.class) {
                cdVar = jR;
                if (cdVar == null) {
                    File cacheDir = context.getCacheDir();
                    boolean zMkdir = true;
                    if (cacheDir != null && !cacheDir.exists()) {
                        zMkdir = cacheDir.mkdir();
                    }
                    if (!zMkdir) {
                        g.c("DiskCache: unable to create cache dir");
                        return null;
                    }
                    File file = new File(cacheDir, jH);
                    if (!file.exists()) {
                        zMkdir = file.mkdir();
                    }
                    if (!zMkdir) {
                        g.c("DiskCache: unable to create cache dir");
                        return null;
                    }
                    if (file.isDirectory() && file.canWrite()) {
                        cd cdVar2 = new cd(file);
                        jR = cdVar2;
                        cdVar = cdVar2;
                    }
                }
            }
        }
        return cdVar;
    }

    @Nullable
    public static cd bi() {
        return jR;
    }

    private cd(@NonNull File file) {
        this.jS = file;
    }

    @WorkerThread
    public synchronized void sync() {
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            try {
                if (this.jS.lastModified() + jO < jCurrentTimeMillis) {
                    File[] fileArrListFiles = this.jS.listFiles(jP);
                    if (fileArrListFiles != null) {
                        for (File file : fileArrListFiles) {
                            if (file.isFile() && file.lastModified() + jO < jCurrentTimeMillis) {
                                g.a("DiskCache: remove expired file " + file.getPath());
                                if (!file.delete()) {
                                    g.a("DiskCache: unable to delete file " + file.getAbsolutePath());
                                }
                            }
                        }
                    }
                    if (!this.jS.setLastModified(jCurrentTimeMillis)) {
                        g.a("DiskCache: unable to set last modified to dir " + this.jS.getAbsolutePath());
                    }
                }
                File[] fileArrListFiles2 = this.jS.listFiles(jQ);
                if (fileArrListFiles2 != null && fileArrListFiles2.length > 10) {
                    Arrays.sort(fileArrListFiles2, new Comparator<File>() { // from class: com.my.target.cd.3
                        @Override // java.util.Comparator
                        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                        public int compare(File file2, File file3) {
                            return Long.valueOf(file3.lastModified()).compareTo(Long.valueOf(file2.lastModified()));
                        }
                    });
                    for (int length = fileArrListFiles2.length - 1; length >= 10; length--) {
                        String path = fileArrListFiles2[length].getPath();
                        g.a("DiskCache: remove redundant video " + path);
                        if (!fileArrListFiles2[length].delete()) {
                            g.a("DiskCache: unable to remove file " + path);
                        }
                    }
                }
            } catch (Exception e) {
                g.c("DiskCache exception: " + e);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    /* JADX WARN: Code duplicated, block: B:43:0x0081 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Nullable
    @WorkerThread
    public synchronized File a(@NonNull InputStream inputStream, @NonNull String str) {
        FileOutputStream fileOutputStream;
        sync();
        File fileH = h(str, jJ);
        g.a("DiskCache save image: " + fileH.getPath());
        try {
            fileOutputStream = new FileOutputStream(fileH);
            try {
                try {
                    a(inputStream, fileOutputStream);
                    try {
                        fileOutputStream.close();
                    } catch (Exception e) {
                        g.c("DiskCache exception: " + e);
                    }
                    return fileH;
                } catch (Exception e2) {
                    e = e2;
                    g.c("DiskCache exception: " + e);
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Exception e3) {
                            g.c("DiskCache exception: " + e3);
                        }
                    }
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Exception e4) {
                        g.c("DiskCache exception: " + e4);
                    }
                }
                throw th;
            }
        } catch (Exception e5) {
            e = e5;
            fileOutputStream = null;
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream = null;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Code duplicated, block: B:39:0x0081 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Nullable
    @WorkerThread
    public synchronized File b(@NonNull InputStream inputStream, @NonNull String str) {
        FileOutputStream fileOutputStream;
        sync();
        File fileH = h(str, ".mp4");
        g.a("DiskCache save video: " + fileH.getPath());
        try {
            fileOutputStream = new FileOutputStream(fileH);
            try {
                try {
                    a(inputStream, fileOutputStream);
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        g.c("DiskCache exception: " + e);
                    }
                    return fileH;
                } catch (Exception e2) {
                    e = e2;
                    g.c("DiskCache exception: " + e);
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e3) {
                            g.c("DiskCache exception: " + e3);
                        }
                    }
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e4) {
                        g.c("DiskCache exception: " + e4);
                    }
                }
                throw th;
            }
        } catch (Exception e5) {
            e = e5;
            fileOutputStream = null;
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream = null;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw th;
        }
    }

    @Nullable
    @WorkerThread
    public synchronized File a(int i, @NonNull String str, boolean z) {
        OutputStreamWriter outputStreamWriter;
        sync();
        File fileH = h(Integer.toString(i), jL);
        g.a("DiskCache save text: " + fileH.getPath());
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (fileH.exists() && z) {
            jCurrentTimeMillis = fileH.lastModified();
        }
        try {
            outputStreamWriter = new OutputStreamWriter(new FileOutputStream(fileH), Charset.forName("UTF-8").newEncoder());
            try {
                outputStreamWriter.write(str);
                outputStreamWriter.close();
                if (!fileH.setLastModified(jCurrentTimeMillis)) {
                    g.a("DiskCache: unable to set last modified to file " + fileH.getPath());
                }
                return fileH;
            } catch (Exception e) {
                e = e;
                g.c("DiskCache exception: " + e);
                if (outputStreamWriter != null) {
                    try {
                        outputStreamWriter.close();
                    } catch (IOException unused) {
                    }
                }
                return null;
            }
        } catch (Exception e2) {
            e = e2;
            outputStreamWriter = null;
        }
    }

    @Nullable
    @WorkerThread
    public String N(@NonNull String str) {
        return g(str, ".mp4");
    }

    @Nullable
    @WorkerThread
    public String O(@NonNull String str) {
        return g(str, jJ);
    }

    @Nullable
    @WorkerThread
    public synchronized Bitmap getBitmap(@NonNull String str) {
        try {
            sync();
            File fileH = h(str, jJ);
            if (fileH.exists()) {
                g.a("DiskCache get image: " + fileH.getPath());
                try {
                    return BitmapFactory.decodeFile(fileH.getAbsolutePath());
                } catch (Exception e) {
                    g.c("DiskCache exception: " + e);
                } catch (OutOfMemoryError e2) {
                    System.gc();
                    g.c("DiskCache OOME, trying once again");
                    try {
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inSampleSize = 2;
                        return BitmapFactory.decodeFile(fileH.getAbsolutePath(), options);
                    } catch (OutOfMemoryError unused) {
                        g.c("DiskCache OOME, called twice: " + e2);
                    }
                }
            }
            return null;
        } catch (Throwable th) {
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r7v20 */
    /* JADX WARN: Type inference failed for: r7v21 */
    /* JADX WARN: Type inference failed for: r7v7 */
    @Nullable
    @WorkerThread
    public synchronized String a(int i, long j) {
        ?? r7;
        FileInputStream fileInputStream;
        String str;
        long jCurrentTimeMillis;
        sync();
        File fileH = h(Integer.toString(i), jL);
        if (fileH.exists()) {
            if (fileH.isFile()) {
                long jLastModified = fileH.lastModified() + j;
                jCurrentTimeMillis = System.currentTimeMillis();
                if (jLastModified < jCurrentTimeMillis) {
                    g.a("DiskCache: remove expired file " + fileH.getPath());
                    if (!fileH.delete()) {
                        r7 = j;
                        r7 = jCurrentTimeMillis;
                        g.a("DiskCache: unable to delete file " + fileH.getAbsolutePath());
                    }
                    return null;
                }
            }
            try {
                r7 = j;
                r7 = jCurrentTimeMillis;
                r7 = j;
                g.a("DiskCache get text: " + fileH.getPath());
                try {
                    fileInputStream = new FileInputStream(fileH);
                    try {
                        StringBuilder sb = new StringBuilder();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, "UTF-8"));
                        char[] cArr = new char[1024];
                        while (true) {
                            int i2 = bufferedReader.read(cArr);
                            if (i2 == -1) {
                                break;
                            }
                            sb.append(new String(cArr, 0, i2));
                        }
                        if (sb.length() != 0) {
                            String string = sb.toString();
                            try {
                                fileInputStream.close();
                            } catch (IOException e) {
                                g.c("DiskCache exception: " + e);
                            }
                            return string;
                        }
                        g.a("DiskCache error: cache file is empty");
                        try {
                            fileInputStream.close();
                        } catch (IOException e2) {
                            str = "DiskCache exception: " + e2;
                            g.c(str);
                        }
                    } catch (Exception e3) {
                        e = e3;
                        g.c("DiskCache exception: " + e);
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e4) {
                                str = "DiskCache exception: " + e4;
                                g.c(str);
                            }
                        }
                        return null;
                    }
                } catch (Exception e5) {
                    e = e5;
                    fileInputStream = null;
                } catch (Throwable th) {
                    th = th;
                    r7 = 0;
                    if (r7 != 0) {
                        try {
                            r7.close();
                        } catch (IOException e6) {
                            g.c("DiskCache exception: " + e6);
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
            throw th;
        }
        return null;
    }

    @Nullable
    @WorkerThread
    private synchronized String g(@NonNull String str, @NonNull String str2) {
        sync();
        File fileH = h(str, str2);
        if (fileH.exists()) {
            g.a("DiskCache get path: " + fileH.getPath());
            try {
                return fileH.getAbsolutePath();
            } catch (Exception e) {
                g.c("DiskCache exception: " + e);
                return null;
            }
        }
        return null;
    }

    private int a(@NonNull InputStream inputStream, @NonNull OutputStream outputStream) throws Exception {
        byte[] bArr = new byte[8192];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 8192);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream, 8192);
        int i = 0;
        while (true) {
            try {
                int i2 = bufferedInputStream.read(bArr, 0, 8192);
                if (i2 == -1) {
                    break;
                }
                bufferedOutputStream.write(bArr, 0, i2);
                i += i2;
            } catch (Throwable th) {
                try {
                    bufferedOutputStream.close();
                } catch (IOException e) {
                    g.a(e.getMessage());
                }
                try {
                    bufferedInputStream.close();
                    throw th;
                } catch (IOException e2) {
                    g.a(e2.getMessage());
                    throw th;
                }
            }
        }
        bufferedOutputStream.flush();
        try {
            bufferedOutputStream.close();
        } catch (IOException e3) {
            g.a(e3.getMessage());
        }
        try {
            bufferedInputStream.close();
        } catch (IOException e4) {
            g.a(e4.getMessage());
        }
        return i;
    }

    @NonNull
    private File h(@NonNull String str, @NonNull String str2) {
        return new File(this.jS.getAbsolutePath() + File.separator + (jI + ce.P(str) + str2));
    }
}
