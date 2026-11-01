package com.applovin.impl.sdk;

import android.content.Context;
import android.net.Uri;
import android.support.v4.media.session.PlaybackStateCompat;
import com.appsflyer.share.Constants;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class l {
    private final h b;
    private final n c;
    private final String a = "FileManager";
    private final Object d = new Object();

    l(h hVar) {
        this.b = hVar;
        this.c = hVar.w();
    }

    private long a(long j) {
        return j / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
    }

    private void a(long j, Context context) {
        n nVar;
        String str;
        long jC = c();
        if (jC == -1) {
            nVar = this.c;
            str = "Cache has no maximum size set; skipping drop...";
        } else {
            if (a(j) > jC) {
                this.c.a("FileManager", "Cache has exceeded maximum size; dropping...");
                g(context);
                this.b.G().a(com.applovin.impl.sdk.c.g.f);
                return;
            }
            nVar = this.c;
            str = "Cache is present but under size limit; not dropping...";
        }
        nVar.a("FileManager", str);
    }

    private boolean a() {
        return ((Boolean) this.b.a(com.applovin.impl.sdk.b.b.bx)).booleanValue();
    }

    private long b() {
        long jLongValue = ((Long) this.b.a(com.applovin.impl.sdk.b.b.by)).longValue();
        if (jLongValue < 0 || !a()) {
            return -1L;
        }
        return jLongValue;
    }

    /* JADX WARN: Code duplicated, block: B:48:0x0044 A[EXC_TOP_SPLITTER, PHI: r1
  0x0044: PHI (r1v5 java.io.FileOutputStream) = (r1v3 java.io.FileOutputStream), (r1v6 java.io.FileOutputStream) binds: [B:20:0x0042, B:25:0x0052] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    private boolean b(ByteArrayOutputStream byteArrayOutputStream, File file) {
        boolean z;
        this.c.a("FileManager", "Writing resource to filesystem: " + file.getName());
        synchronized (this.d) {
            FileOutputStream fileOutputStream = null;
            try {
                try {
                    try {
                        FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                        try {
                            byteArrayOutputStream.writeTo(fileOutputStream2);
                            z = true;
                            try {
                                fileOutputStream2.close();
                            } catch (Exception unused) {
                            }
                        } catch (IOException e) {
                            e = e;
                            fileOutputStream = fileOutputStream2;
                            this.c.b("FileManager", "Unable to write data to file.", e);
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (Exception unused2) {
                                }
                            }
                            z = false;
                        } catch (Throwable th) {
                            th = th;
                            fileOutputStream = fileOutputStream2;
                            this.c.b("FileManager", "Unknown failure to write file.", th);
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                            z = false;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (IOException e2) {
                    e = e2;
                } catch (Throwable th3) {
                    th = th3;
                }
            } catch (Throwable th4) {
                throw th4;
            }
        }
        return z;
    }

    private boolean b(File file) {
        boolean zDelete;
        this.c.a("FileManager", "Removing file " + file.getName() + " from filesystem...");
        synchronized (this.d) {
            try {
                try {
                    zDelete = file.delete();
                } catch (Exception e) {
                    this.c.b("FileManager", "Failed to remove file " + file.getName() + " from filesystem!", e);
                    return false;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return zDelete;
    }

    private int c() {
        int iIntValue = ((Integer) this.b.a(com.applovin.impl.sdk.b.b.bz)).intValue();
        if (iIntValue < 0 || !a()) {
            return -1;
        }
        return iIntValue;
    }

    private boolean d(Context context) {
        if (com.applovin.impl.sdk.utils.e.a("android.permission.WRITE_EXTERNAL_STORAGE", context)) {
            return true;
        }
        if (com.applovin.impl.sdk.utils.e.e() && !((Boolean) this.b.a(com.applovin.impl.sdk.b.b.bC)).booleanValue()) {
            return true;
        }
        this.b.w().c("FileManager", "Application lacks required WRITE_EXTERNAL_STORAGE manifest permission.");
        return false;
    }

    private File e(Context context) {
        return d(context) ? new File(context.getExternalFilesDir(null), "al") : new File(context.getCacheDir(), "al");
    }

    private long f(Context context) {
        long length;
        boolean z;
        long jB = b();
        boolean z2 = jB != -1;
        long seconds = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
        synchronized (this.d) {
            length = 0;
            for (File file : a(context)) {
                if (!z2 || seconds - TimeUnit.MILLISECONDS.toSeconds(file.lastModified()) <= jB) {
                    z = false;
                } else {
                    this.c.a("FileManager", "File " + file.getName() + " has expired, removing...");
                    b(file);
                    z = true;
                }
                if (z) {
                    this.b.G().a(com.applovin.impl.sdk.c.g.e);
                } else {
                    length += file.length();
                }
            }
        }
        return length;
    }

    private void g(Context context) {
        synchronized (this.d) {
            Iterator<File> it = a(context).iterator();
            while (it.hasNext()) {
                b(it.next());
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:71:0x00b1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [com.applovin.impl.sdk.n] */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v6, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public ByteArrayOutputStream a(File file) {
        FileInputStream fileInputStream;
        if (file == null) {
            return null;
        }
        ?? r1 = this.c;
        ?? r2 = "Reading resource from filesystem: " + file.getName();
        r1.a("FileManager", r2);
        synchronized (this.d) {
            try {
                try {
                    try {
                        fileInputStream = new FileInputStream(file);
                        try {
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            byte[] bArr = new byte[1024];
                            while (true) {
                                int i = fileInputStream.read(bArr, 0, bArr.length);
                                if (i >= 0) {
                                    try {
                                        try {
                                            byteArrayOutputStream.write(bArr, 0, i);
                                        } catch (Exception unused) {
                                            try {
                                                fileInputStream.close();
                                            } catch (Exception unused2) {
                                            }
                                            return null;
                                        }
                                    } catch (Exception unused3) {
                                        byteArrayOutputStream.close();
                                        fileInputStream.close();
                                        return null;
                                    }
                                } else {
                                    try {
                                        break;
                                    } catch (Exception unused4) {
                                    }
                                }
                            }
                            fileInputStream.close();
                            return byteArrayOutputStream;
                        } catch (FileNotFoundException e) {
                            e = e;
                            this.c.b("FileManager", "File not found. " + e);
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (Exception unused5) {
                                }
                            }
                            return null;
                        } catch (IOException e2) {
                            e = e2;
                            this.c.a("FileManager", "Failed to read file: " + file.getName() + e);
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (Exception unused6) {
                                }
                            }
                            return null;
                        } catch (Throwable th) {
                            th = th;
                            this.c.b("FileManager", "Unknown failure to read file.", th);
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (Exception unused7) {
                                }
                            }
                            return null;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (r2 != 0) {
                            try {
                                r2.close();
                            } catch (Exception unused8) {
                            }
                        }
                        throw th;
                    }
                } catch (FileNotFoundException e3) {
                    e = e3;
                    fileInputStream = null;
                } catch (IOException e4) {
                    e = e4;
                    fileInputStream = null;
                } catch (Throwable th3) {
                    th = th3;
                    fileInputStream = null;
                }
            } catch (Throwable th4) {
                throw th4;
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:101:0x00d3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:105:0x0131 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:113:0x013b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:117:0x0136 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:97:0x00cb A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v10 */
    /* JADX WARN: Type inference failed for: r10v13, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r10v3, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r10v4 */
    /* JADX WARN: Type inference failed for: r10v5 */
    /* JADX WARN: Type inference failed for: r10v6 */
    /* JADX WARN: Type inference failed for: r10v7, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r10v8, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r10v9 */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r2v15, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r9v10, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r9v11, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r9v15 */
    /* JADX WARN: Type inference failed for: r9v5, types: [com.applovin.impl.sdk.n] */
    /* JADX WARN: Type inference failed for: r9v6 */
    /* JADX WARN: Type inference failed for: r9v7 */
    /* JADX WARN: Type inference failed for: r9v8 */
    /* JADX WARN: Type inference failed for: r9v9, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public ByteArrayOutputStream a(String str, List<String> list, boolean z) throws Throwable {
        ?? inputStream;
        ?? r1 = 0;
        r1 = 0;
        r1 = 0;
        if (z && !com.applovin.impl.sdk.utils.n.a(str, list)) {
            this.c.a("FileManager", "Domain is not whitelisted, skipping precache for url: " + str);
            return null;
        }
        if (((Boolean) this.b.a(com.applovin.impl.sdk.b.b.dH)).booleanValue() && !str.contains("https://")) {
            this.b.w().c("FileManager", "Plaintext HTTP operation requested; upgrading to HTTPS due to universal SSL setting...");
            str = str.replace("http://", "https://");
        }
        ?? byteArrayOutputStream = this.c;
        ?? r10 = "Loading " + str + "...";
        byteArrayOutputStream.a("FileManager", r10);
        try {
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    r10 = (HttpURLConnection) new URL(str).openConnection();
                    try {
                        r10.setConnectTimeout(((Integer) this.b.a(com.applovin.impl.sdk.b.b.dF)).intValue());
                        r10.setReadTimeout(((Integer) this.b.a(com.applovin.impl.sdk.b.b.dG)).intValue());
                        r10.setDefaultUseCaches(true);
                        r10.setUseCaches(true);
                        r10.setAllowUserInteraction(false);
                        r10.setInstanceFollowRedirects(true);
                        int responseCode = r10.getResponseCode();
                        if (responseCode >= 200 && responseCode < 300) {
                            inputStream = r10.getInputStream();
                            try {
                                byte[] bArr = new byte[1024];
                                while (true) {
                                    int i = inputStream.read(bArr, 0, bArr.length);
                                    if (i < 0) {
                                        break;
                                    }
                                    try {
                                        try {
                                            byteArrayOutputStream.write(bArr, 0, i);
                                        } catch (Exception unused) {
                                            if (inputStream != 0) {
                                                try {
                                                    inputStream.close();
                                                } catch (Exception unused2) {
                                                }
                                            }
                                            try {
                                                byteArrayOutputStream.close();
                                            } catch (Exception unused3) {
                                            }
                                            if (r10 != 0) {
                                                try {
                                                    r10.disconnect();
                                                } catch (Exception unused4) {
                                                }
                                            }
                                            return null;
                                        }
                                    } catch (Exception unused5) {
                                        byteArrayOutputStream.close();
                                        if (inputStream != 0) {
                                            inputStream.close();
                                        }
                                        byteArrayOutputStream.close();
                                        if (r10 != 0) {
                                            r10.disconnect();
                                        }
                                        return null;
                                    }
                                }
                                this.c.a("FileManager", "Loaded resource at " + str);
                                if (inputStream != 0) {
                                    try {
                                        inputStream.close();
                                    } catch (Exception unused6) {
                                    }
                                }
                                try {
                                    byteArrayOutputStream.close();
                                } catch (Exception unused7) {
                                }
                                if (r10 != 0) {
                                    try {
                                        r10.disconnect();
                                    } catch (Exception unused8) {
                                    }
                                }
                                return byteArrayOutputStream;
                            } catch (IOException e) {
                                e = e;
                                this.c.b("FileManager", "Error loading " + str, e);
                                if (inputStream != 0) {
                                    try {
                                        inputStream.close();
                                    } catch (Exception unused9) {
                                    }
                                }
                                if (byteArrayOutputStream != 0) {
                                    try {
                                        byteArrayOutputStream.close();
                                    } catch (Exception unused10) {
                                    }
                                }
                                if (r10 != 0) {
                                    try {
                                        r10.disconnect();
                                    } catch (Exception unused11) {
                                    }
                                }
                                return null;
                            }
                        }
                        try {
                            byteArrayOutputStream.close();
                        } catch (Exception unused12) {
                        }
                        if (r10 != 0) {
                            try {
                                r10.disconnect();
                            } catch (Exception unused13) {
                            }
                        }
                        return null;
                    } catch (IOException e2) {
                        e = e2;
                        inputStream = 0;
                    } catch (Throwable th) {
                        th = th;
                        if (r1 != 0) {
                            try {
                                r1.close();
                            } catch (Exception unused14) {
                            }
                        }
                        if (byteArrayOutputStream != 0) {
                            try {
                                byteArrayOutputStream.close();
                            } catch (Exception unused15) {
                            }
                        }
                        if (r10 != 0) {
                            try {
                                r10.disconnect();
                            } catch (Exception unused16) {
                            }
                        }
                        throw th;
                    }
                } catch (IOException e3) {
                    e = e3;
                    r10 = 0;
                    byteArrayOutputStream = byteArrayOutputStream;
                    inputStream = r10;
                    this.c.b("FileManager", "Error loading " + str, e);
                    if (inputStream != 0) {
                        inputStream.close();
                    }
                    if (byteArrayOutputStream != 0) {
                        byteArrayOutputStream.close();
                    }
                    if (r10 != 0) {
                        r10.disconnect();
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    r10 = 0;
                }
            } catch (Throwable th3) {
                th = th3;
                r1 = "...";
            }
        } catch (IOException e4) {
            e = e4;
            byteArrayOutputStream = 0;
            r10 = 0;
        } catch (Throwable th4) {
            th = th4;
            byteArrayOutputStream = 0;
            r10 = 0;
        }
    }

    public File a(String str, Context context, boolean z) {
        File file;
        if (!com.applovin.impl.sdk.utils.k.b(str)) {
            this.b.w().a("FileManager", "Nothing to look up, skipping...");
            return null;
        }
        this.c.a("FileManager", "Looking up cached resource: " + str);
        if (!d(context) && !z) {
            return null;
        }
        if (str.contains("icon")) {
            str = str.replace(Constants.URL_PATH_DELIMITER, EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR).replace(".", EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR);
        }
        synchronized (this.d) {
            File fileE = e(context);
            file = new File(fileE, str);
            try {
                fileE.mkdirs();
            } catch (Exception unused) {
                return null;
            }
        }
        return file;
    }

    public String a(Context context, String str, String str2, List<String> list, boolean z, com.applovin.impl.sdk.c.e eVar) throws MalformedURLException {
        return a(context, str, str2, list, z, false, eVar);
    }

    public String a(Context context, String str, String str2, List<String> list, boolean z, boolean z2, com.applovin.impl.sdk.c.e eVar) throws MalformedURLException {
        if (!com.applovin.impl.sdk.utils.k.b(str)) {
            this.b.w().a("FileManager", "Nothing to cache, skipping...");
            return null;
        }
        String lastPathSegment = Uri.parse(str).getLastPathSegment();
        if (com.applovin.impl.sdk.utils.k.b(lastPathSegment) && com.applovin.impl.sdk.utils.k.b(str2)) {
            lastPathSegment = str2 + lastPathSegment;
        }
        String str3 = lastPathSegment;
        File fileA = a(str3, context, false);
        if (!a(fileA, str, list, z, eVar)) {
            return null;
        }
        this.c.a("FileManager", "Caching succeeded for file " + str3);
        return z2 ? Uri.fromFile(fileA).toString() : str3;
    }

    public List<File> a(Context context) {
        List<File> listAsList;
        File fileE = e(context);
        if (!fileE.isDirectory()) {
            return new ArrayList(0);
        }
        synchronized (this.d) {
            listAsList = Arrays.asList(fileE.listFiles());
        }
        return listAsList;
    }

    public boolean a(ByteArrayOutputStream byteArrayOutputStream, File file) {
        if (file == null) {
            return false;
        }
        this.c.a("FileManager", "Caching " + file.getAbsolutePath() + "...");
        if (byteArrayOutputStream == null || byteArrayOutputStream.size() <= 0) {
            this.c.c("FileManager", "No data for " + file.getAbsolutePath());
            return false;
        }
        if (b(byteArrayOutputStream, file)) {
            this.c.a("FileManager", "Caching completed for " + file);
            return true;
        }
        this.c.d("FileManager", "Unable to cache " + file.getAbsolutePath());
        return false;
    }

    public boolean a(File file, String str, List<String> list, com.applovin.impl.sdk.c.e eVar) {
        return a(file, str, list, true, eVar);
    }

    public boolean a(File file, String str, List<String> list, boolean z, com.applovin.impl.sdk.c.e eVar) throws Throwable {
        if (file == null || !file.exists() || file.isDirectory()) {
            ByteArrayOutputStream byteArrayOutputStreamA = a(str, list, z);
            if (eVar != null && byteArrayOutputStreamA != null) {
                eVar.a(byteArrayOutputStreamA.size());
            }
            return a(byteArrayOutputStreamA, file);
        }
        this.b.w().a("FileManager", "File exists for " + str);
        if (eVar == null) {
            return true;
        }
        eVar.b(file.length());
        return true;
    }

    public boolean a(String str, Context context) {
        boolean zB;
        synchronized (this.d) {
            zB = b(str, context, false);
        }
        return zB;
    }

    public boolean b(Context context) {
        if (((Boolean) this.b.a(com.applovin.impl.sdk.b.b.bD)).booleanValue()) {
            try {
                a(".nomedia", context, false);
                File file = new File(e(context), ".nomedia");
                if (file.exists()) {
                    return true;
                }
                this.b.w().a("FileManager", "Dropping .nomedia file at " + file.getAbsolutePath());
                return file.createNewFile();
            } catch (Exception e) {
                this.b.w().a("FileManager", "Failed to create nomedia file", e);
            }
        }
        return false;
    }

    boolean b(String str, Context context, boolean z) {
        boolean z2;
        synchronized (this.d) {
            File fileA = a(str, context, z);
            z2 = (fileA == null || !fileA.exists() || fileA.isDirectory()) ? false : true;
        }
        return z2;
    }

    public void c(Context context) {
        try {
            if (a()) {
                if (!this.b.c()) {
                    this.c.d("FileManager", "Cannot empty file cache after SDK has completed initialization and ad loads are in progress!");
                    return;
                }
                this.c.a("FileManager", "Compacting cache...");
                synchronized (this.d) {
                    a(f(context), context);
                }
            }
        } catch (Exception e) {
            this.c.b("FileManager", "Caught exception while compacting cache!", e);
            this.b.y().a((com.applovin.impl.sdk.b.b<?>) com.applovin.impl.sdk.b.b.bx, (Object) false);
            this.b.y().a();
        }
    }
}
