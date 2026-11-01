package net.nend.android.internal.c.f;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Pair;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.google.ads.mediation.unity.BuildConfig;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import net.nend.android.internal.d.k;
import net.nend.android.internal.d.l;
import net.nend.android.internal.utilities.i;
import net.nend.android.internal.utilities.j;
import net.nend.android.internal.utilities.o;
import net.nend.android.internal.utilities.video.NendVideoAdClientError;

/* JADX INFO: compiled from: CacheManager.java */
/* JADX INFO: loaded from: classes2.dex */
public class a {
    private static a c;
    private static long d;

    @VisibleForTesting
    public File b;

    @VisibleForTesting
    public final Map<String, AtomicInteger> a = new HashMap();
    private final ExecutorService e = Executors.newSingleThreadExecutor();

    private a(Context context) {
        b(context);
        a(86400000L);
    }

    private static boolean b() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean c(File file) {
        return System.currentTimeMillis() - file.lastModified() > d;
    }

    @VisibleForTesting
    public static void a(long j) {
        d = j;
    }

    public static synchronized a a(Context context) {
        if (c == null) {
            c = new a(context);
        }
        return c;
    }

    private static void a(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[1024];
        while (true) {
            int i = inputStream.read(bArr);
            if (i != -1) {
                outputStream.write(bArr, 0, i);
            } else {
                outputStream.flush();
                return;
            }
        }
    }

    private static Callable<Boolean> c(final String str, final File file) {
        return new Callable<Boolean>() { // from class: net.nend.android.internal.c.f.a.1
            @Override // java.util.concurrent.Callable
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public Boolean call() throws Throwable {
                try {
                    a.e(str, file);
                    j.a("Cache a file from " + str + " to " + file.getAbsolutePath());
                    return true;
                } catch (Exception e) {
                    j.b("Failed to cache file at " + str, e.getCause());
                    return false;
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:17:0x0029 A[Catch: IOException -> 0x002c, TRY_LEAVE, TryCatch #4 {IOException -> 0x002c, blocks: (B:15:0x0024, B:17:0x0029), top: B:29:0x0024 }] */
    public static void d(String str, File file) throws Throwable {
        ByteArrayInputStream byteArrayInputStream;
        FileOutputStream fileOutputStream = null;
        try {
            byteArrayInputStream = new ByteArrayInputStream(str.getBytes("utf-8"));
            try {
                FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                try {
                    a(byteArrayInputStream, fileOutputStream2);
                    try {
                        fileOutputStream2.close();
                        byteArrayInputStream.close();
                    } catch (IOException unused) {
                    }
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                            if (byteArrayInputStream != null) {
                                byteArrayInputStream.close();
                            }
                        } catch (IOException unused2) {
                            throw th;
                        }
                    } else if (byteArrayInputStream != null) {
                        byteArrayInputStream.close();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
            byteArrayInputStream = null;
        }
    }

    static void a(net.nend.android.internal.b.c.d dVar) {
        i.b(new File(dVar.o));
        if (TextUtils.isEmpty(dVar.p)) {
            return;
        }
        i.b(new File(dVar.p));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:25:0x004b A[Catch: IOException -> 0x004e, TRY_LEAVE, TryCatch #4 {IOException -> 0x004e, blocks: (B:23:0x0046, B:25:0x004b), top: B:39:0x0046 }] */
    /* JADX WARN: Code duplicated, block: B:27:0x0050  */
    /* JADX WARN: Code duplicated, block: B:44:? A[SYNTHETIC] */
    public static void e(String str, File file) throws Throwable {
        HttpURLConnection httpURLConnection;
        InputStream inputStream;
        FileOutputStream fileOutputStream = null;
        try {
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            try {
                httpURLConnection.setConnectTimeout(BuildConfig.VERSION_CODE);
                httpURLConnection.setReadTimeout(BuildConfig.VERSION_CODE);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setUseCaches(false);
                httpURLConnection.connect();
                inputStream = httpURLConnection.getInputStream();
                try {
                    FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                    try {
                        a(inputStream, fileOutputStream2);
                        try {
                            fileOutputStream2.close();
                            if (inputStream != null) {
                                inputStream.close();
                            }
                        } catch (IOException unused) {
                        }
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = fileOutputStream2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                                if (inputStream != null) {
                                    inputStream.close();
                                }
                            } catch (IOException unused2) {
                                if (httpURLConnection != null) {
                                    throw th;
                                }
                                httpURLConnection.disconnect();
                                throw th;
                            }
                        } else if (inputStream != null) {
                            inputStream.close();
                        }
                        if (httpURLConnection != null) {
                            throw th;
                        }
                        httpURLConnection.disconnect();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                inputStream = null;
            }
        } catch (Throwable th4) {
            th = th4;
            httpURLConnection = null;
            inputStream = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(File file) {
        for (File file2 : file.listFiles()) {
            if (file2.delete()) {
                j.a("Delete file at " + file2.getAbsolutePath());
            }
        }
        if (file.delete()) {
            j.a("Delete directory at " + file.getAbsolutePath());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String c(String str) {
        return o.a(str).toUpperCase();
    }

    @WorkerThread
    private static boolean a(List<Pair<String, File>> list) {
        int size = list.size();
        ExecutorService executorServiceNewFixedThreadPool = Executors.newFixedThreadPool(size);
        ExecutorCompletionService executorCompletionService = new ExecutorCompletionService(executorServiceNewFixedThreadPool);
        for (Pair<String, File> pair : list) {
            executorCompletionService.submit(c((String) pair.first, (File) pair.second));
        }
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= size) {
                z = true;
                break;
            }
            try {
                if (!((Boolean) executorCompletionService.take().get()).booleanValue()) {
                    executorServiceNewFixedThreadPool.shutdownNow();
                    break;
                }
                i++;
            } catch (InterruptedException | ExecutionException unused) {
                executorServiceNewFixedThreadPool.shutdownNow();
            }
        }
        if (z) {
            executorServiceNewFixedThreadPool.shutdown();
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Context context) {
        File externalCacheDir;
        boolean z = true;
        if (Build.VERSION.SDK_INT < 19 && context.getPackageManager().checkPermission("android.permission.WRITE_EXTERNAL_STORAGE", context.getPackageName()) != 0) {
            z = false;
        }
        String absolutePath = (z && b() && (externalCacheDir = context.getExternalCacheDir()) != null && externalCacheDir.canWrite() && externalCacheDir.canRead()) ? externalCacheDir.getAbsolutePath() : null;
        if (TextUtils.isEmpty(absolutePath)) {
            absolutePath = context.getFilesDir().getAbsolutePath();
        }
        this.b = new File(absolutePath, ".nend");
        if (this.b.exists()) {
            return;
        }
        if (!this.b.mkdir()) {
            this.b = null;
            return;
        }
        j.a("Create cache directory at " + this.b.getAbsolutePath());
    }

    <V extends net.nend.android.internal.b.c.d> k<V> a(V v, final Context context) {
        return a(v, context).b((net.nend.android.internal.d.g) new net.nend.android.internal.d.g<V, k<? extends V>>() { // from class: net.nend.android.internal.c.f.a.2
            /* JADX WARN: Incorrect types in method signature: (TV;)Lnet/nend/android/internal/d/k<+TV;>; */
            @Override // net.nend.android.internal.d.g
            public k a(net.nend.android.internal.b.c.d dVar) {
                return a.this.b(dVar, context);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <V extends net.nend.android.internal.b.c.d> k<V> b(final V v, final Context context) {
        if (this.b == null) {
            return l.a((Throwable) new IOException("Cache directory is not exist."));
        }
        return l.a(this.e, new Callable<V>() { // from class: net.nend.android.internal.c.f.a.3
            /* JADX WARN: Incorrect return type in method signature: ()TV; */
            @Override // java.util.concurrent.Callable
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public net.nend.android.internal.b.c.d call() throws Throwable {
                C0110a c0110aA;
                File file;
                ArrayList arrayList = new ArrayList();
                File file2 = new File(a.this.b.getAbsolutePath(), a.c(v.d));
                String absolutePath = file2.getAbsolutePath();
                File file3 = new File(absolutePath, "mapping.dat");
                if (!file3.exists()) {
                    c0110aA = new C0110a();
                } else {
                    c0110aA = C0110a.a(file3);
                }
                for (String str : v.k) {
                    String strA = i.a(str);
                    File file4 = new File(absolutePath, strA);
                    net.nend.android.internal.b.c.d dVar = v;
                    dVar.m = dVar.m.replace(str, strA);
                    if (!TextUtils.isEmpty(v.n)) {
                        net.nend.android.internal.b.c.d dVar2 = v;
                        dVar2.n = dVar2.n.replace(str, strA);
                    }
                    if (c0110aA.contains(str)) {
                        if (!file4.exists()) {
                            c0110aA.remove(str);
                            arrayList.add(Pair.create(str, file4));
                            c0110aA.add(str);
                        }
                    } else {
                        arrayList.add(Pair.create(str, file4));
                        c0110aA.add(str);
                    }
                }
                String strValueOf = String.valueOf(System.currentTimeMillis());
                File file5 = new File(absolutePath, strValueOf + "_endcard.html");
                a.d(v.m, file5);
                if (TextUtils.isEmpty(v.n)) {
                    file = null;
                } else {
                    file = new File(absolutePath, strValueOf + "_htmlOnPlaying.html");
                    a.d(v.n, file);
                }
                a.this.a(context, arrayList, file2, c0110aA);
                v.b(file5.getAbsolutePath(), file != null ? file.getAbsolutePath() : null);
                return v;
            }
        });
    }

    <V extends net.nend.android.internal.b.a> k<V> a(final V v, final Context context) {
        if (this.b == null) {
            return l.a((Throwable) new IOException("Cache directory is not exist."));
        }
        return l.a(this.e, new Callable<V>() { // from class: net.nend.android.internal.c.f.a.4
            /* JADX WARN: Incorrect return type in method signature: ()TV; */
            @Override // java.util.concurrent.Callable
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public net.nend.android.internal.b.a call() throws Throwable {
                if (!a.this.b.exists()) {
                    a.this.b(context);
                } else {
                    a.this.a();
                }
                String strC = a.c(v.d);
                File file = new File(a.this.b.getAbsolutePath(), strC);
                String absolutePath = file.getAbsolutePath();
                ArrayList arrayList = new ArrayList();
                File file2 = new File(absolutePath, strC);
                if (!file2.exists()) {
                    arrayList.add(Pair.create(v.d, file2));
                }
                if (!file.exists() && !file.mkdir()) {
                    throw new IOException("Failed to create cache directory.");
                }
                long jCurrentTimeMillis = System.currentTimeMillis();
                a.this.a(context, arrayList, file, null);
                net.nend.android.internal.utilities.b.a("DownloadTimeEvent", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
                a.this.d(absolutePath);
                v.a(absolutePath, file2.getAbsolutePath());
                return v;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context, List<Pair<String, File>> list, File file, C0110a c0110a) throws Throwable {
        if (list.isEmpty()) {
            return;
        }
        if (!net.nend.android.internal.c.g.a(context) || !a(list)) {
            d(file);
            throw new net.nend.android.internal.a.a(NendVideoAdClientError.FAILED_AD_DOWNLOAD);
        }
        if (c0110a != null) {
            c0110a.b(new File(file, "mapping.dat"));
        }
        j.a("Success to create a cache directory at " + file.getAbsolutePath());
    }

    @VisibleForTesting
    void a() {
        File[] fileArrListFiles;
        File file = this.b;
        if (file == null || (fileArrListFiles = file.listFiles(new FileFilter() { // from class: net.nend.android.internal.c.f.a.5
            @Override // java.io.FileFilter
            public boolean accept(File file2) {
                return file2.isDirectory();
            }
        })) == null || fileArrListFiles.length <= 0) {
            return;
        }
        for (File file2 : fileArrListFiles) {
            String absolutePath = file2.getAbsolutePath();
            AtomicInteger atomicInteger = this.a.get(absolutePath);
            if (atomicInteger == null || atomicInteger.get() <= 0) {
                if (c(file2)) {
                    d(file2);
                } else if (!new File(absolutePath, "mapping.dat").exists()) {
                    d(file2);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str) {
        if (this.a.containsKey(str)) {
            this.a.get(str).incrementAndGet();
        } else {
            this.a.put(str, new AtomicInteger(1));
        }
    }

    void a(final String str) {
        this.e.execute(new Runnable() { // from class: net.nend.android.internal.c.f.a.6
            @Override // java.lang.Runnable
            public void run() {
                if (a.this.a.containsKey(str) && a.this.a.get(str).decrementAndGet() == 0) {
                    File file = new File(str);
                    if (file.exists() && a.c(file)) {
                        a.d(file);
                        a.this.a.remove(str);
                    }
                }
            }
        });
    }

    /* JADX INFO: renamed from: net.nend.android.internal.c.f.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CacheManager.java */
    @VisibleForTesting
    static class C0110a extends ArrayList<String> {
        C0110a() {
        }

        @VisibleForTesting
        static C0110a a(File file) throws Throwable {
            C0110a c0110a = new C0110a();
            c0110a.c(file);
            return c0110a;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b(File file) throws Throwable {
            ObjectOutputStream objectOutputStream = null;
            try {
                ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(new FileOutputStream(file));
                try {
                    objectOutputStream2.writeObject(this);
                    objectOutputStream2.flush();
                    try {
                        objectOutputStream2.close();
                    } catch (IOException unused) {
                    }
                } catch (Throwable th) {
                    th = th;
                    objectOutputStream = objectOutputStream2;
                    if (objectOutputStream != null) {
                        try {
                            objectOutputStream.close();
                        } catch (IOException unused2) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }

        private void c(File file) throws Throwable {
            ObjectInputStream objectInputStream = null;
            try {
                ObjectInputStream objectInputStream2 = new ObjectInputStream(new FileInputStream(file));
                try {
                    addAll((List) objectInputStream2.readObject());
                    try {
                        objectInputStream2.close();
                    } catch (IOException unused) {
                    }
                } catch (Throwable th) {
                    th = th;
                    objectInputStream = objectInputStream2;
                    if (objectInputStream != null) {
                        try {
                            objectInputStream.close();
                        } catch (IOException unused2) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }
}
