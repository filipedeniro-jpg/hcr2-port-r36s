package com.adcolony.sdk;

import com.ironsource.mediationsdk.utils.IronSourceConstants;
import com.my.target.bj;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.GZIPInputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class t {
    private LinkedList<Runnable> a = new LinkedList<>();
    private boolean b;

    t() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean f(af afVar) {
        JSONObject jSONObjectC = afVar.c();
        String strB = y.b(jSONObjectC, "filepath");
        a.a().o().b();
        JSONObject jSONObjectA = y.a();
        try {
            int iC = y.c(jSONObjectC, bj.gD);
            int iC2 = y.c(jSONObjectC, "size");
            boolean zD = y.d(jSONObjectC, "gunzip");
            String strB2 = y.b(jSONObjectC, "output_filepath");
            as asVar = new as(new FileInputStream(strB), iC, iC2);
            InputStream gZIPInputStream = zD ? new GZIPInputStream(asVar, 1024) : asVar;
            if (strB2.equals("")) {
                StringBuilder sb = new StringBuilder(gZIPInputStream.available());
                byte[] bArr = new byte[1024];
                while (true) {
                    int i = gZIPInputStream.read(bArr, 0, 1024);
                    if (i < 0) {
                        break;
                    }
                    sb.append(new String(bArr, 0, i, "ISO-8859-1"));
                }
                y.b(jSONObjectA, "size", sb.length());
                y.a(jSONObjectA, "data", sb.toString());
            } else {
                FileOutputStream fileOutputStream = new FileOutputStream(strB2);
                byte[] bArr2 = new byte[1024];
                int i2 = 0;
                while (true) {
                    int i3 = gZIPInputStream.read(bArr2, 0, 1024);
                    if (i3 < 0) {
                        break;
                    }
                    fileOutputStream.write(bArr2, 0, i3);
                    i2 += i3;
                }
                fileOutputStream.close();
                y.b(jSONObjectA, "size", i2);
            }
            gZIPInputStream.close();
            y.a(jSONObjectA, "success", true);
            afVar.a(jSONObjectA).b();
            return true;
        } catch (IOException unused) {
            y.a(jSONObjectA, "success", false);
            afVar.a(jSONObjectA).b();
            return false;
        } catch (OutOfMemoryError unused2) {
            new aa.a().a("Out of memory error - disabling AdColony.").a(aa.g);
            a.a().a(true);
            y.a(jSONObjectA, "success", false);
            afVar.a(jSONObjectA).b();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean g(af afVar) {
        boolean z;
        JSONObject jSONObjectC = afVar.c();
        String strB = y.b(jSONObjectC, "filepath");
        String strB2 = y.b(jSONObjectC, "bundle_path");
        JSONArray jSONArrayG = y.g(jSONObjectC, "bundle_filenames");
        a.a().o().b();
        JSONObject jSONObjectA = y.a();
        try {
            try {
                File file = new File(strB2);
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
                byte[] bArr = new byte[4];
                byte[] bArr2 = new byte[32];
                randomAccessFile.readInt();
                int i = randomAccessFile.readInt();
                JSONArray jSONArray = new JSONArray();
                byte[] bArr3 = new byte[1024];
                int i2 = 0;
                while (i2 < i) {
                    File file2 = file;
                    randomAccessFile.seek(8 + (i2 * 44));
                    randomAccessFile.read(bArr2);
                    new String(bArr2);
                    randomAccessFile.readInt();
                    int i3 = randomAccessFile.readInt();
                    int i4 = randomAccessFile.readInt();
                    jSONArray.put(i4);
                    try {
                        String str = strB + jSONArrayG.get(i2);
                        JSONArray jSONArray2 = jSONArrayG;
                        String str2 = strB;
                        randomAccessFile.seek(i3);
                        FileOutputStream fileOutputStream = new FileOutputStream(str);
                        int i5 = i4 % 1024;
                        int i6 = 0;
                        for (int i7 = i4 / 1024; i6 < i7; i7 = i7) {
                            randomAccessFile.read(bArr3, 0, 1024);
                            fileOutputStream.write(bArr3, 0, 1024);
                            i6++;
                        }
                        randomAccessFile.read(bArr3, 0, i5);
                        fileOutputStream.write(bArr3, 0, i5);
                        fileOutputStream.close();
                        i2++;
                        strB = str2;
                        file = file2;
                        jSONArrayG = jSONArray2;
                    } catch (JSONException unused) {
                        new aa.a().a("Could extract file name at index ").a(i2).a(" unpacking ad unit bundle at ").a(strB2).a(aa.g);
                        z = false;
                        try {
                            y.a(jSONObjectA, "success", false);
                            afVar.a(jSONObjectA).b();
                            return false;
                        } catch (IOException unused2) {
                            new aa.a().a("Failed to find or open ad unit bundle at path: ").a(strB2).a(aa.h);
                            y.a(jSONObjectA, "success", z);
                            afVar.a(jSONObjectA).b();
                            return z;
                        }
                    }
                }
                File file3 = file;
                randomAccessFile.close();
                file3.delete();
                y.a(jSONObjectA, "success", true);
                y.a(jSONObjectA, "file_sizes", jSONArray);
                afVar.a(jSONObjectA).b();
                return true;
            } catch (IOException unused3) {
                z = false;
            }
        } catch (OutOfMemoryError unused4) {
            new aa.a().a("Out of memory error - disabling AdColony.").a(aa.g);
            a.a().a(true);
            y.a(jSONObjectA, "success", false);
            afVar.a(jSONObjectA).b();
            return false;
        }
    }

    void a() {
        a.a("FileSystem.save", new ah() { // from class: com.adcolony.sdk.t.1
            @Override // com.adcolony.sdk.ah
            public void a(final af afVar) {
                t.this.a(new Runnable() { // from class: com.adcolony.sdk.t.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        t.this.a(afVar);
                        t.this.b();
                    }
                });
            }
        });
        a.a("FileSystem.delete", new ah() { // from class: com.adcolony.sdk.t.2
            @Override // com.adcolony.sdk.ah
            public void a(final af afVar) {
                t.this.a(new Runnable() { // from class: com.adcolony.sdk.t.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        t.this.a(afVar, new File(y.b(afVar.c(), "filepath")));
                        t.this.b();
                    }
                });
            }
        });
        a.a("FileSystem.listing", new ah() { // from class: com.adcolony.sdk.t.3
            @Override // com.adcolony.sdk.ah
            public void a(final af afVar) {
                t.this.a(new Runnable() { // from class: com.adcolony.sdk.t.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        t.this.b(afVar);
                        t.this.b();
                    }
                });
            }
        });
        a.a("FileSystem.load", new ah() { // from class: com.adcolony.sdk.t.4
            @Override // com.adcolony.sdk.ah
            public void a(final af afVar) {
                t.this.a(new Runnable() { // from class: com.adcolony.sdk.t.4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        t.this.c(afVar);
                        t.this.b();
                    }
                });
            }
        });
        a.a("FileSystem.rename", new ah() { // from class: com.adcolony.sdk.t.5
            @Override // com.adcolony.sdk.ah
            public void a(final af afVar) {
                t.this.a(new Runnable() { // from class: com.adcolony.sdk.t.5.1
                    @Override // java.lang.Runnable
                    public void run() {
                        t.this.d(afVar);
                        t.this.b();
                    }
                });
            }
        });
        a.a("FileSystem.exists", new ah() { // from class: com.adcolony.sdk.t.6
            @Override // com.adcolony.sdk.ah
            public void a(final af afVar) {
                t.this.a(new Runnable() { // from class: com.adcolony.sdk.t.6.1
                    @Override // java.lang.Runnable
                    public void run() {
                        t.this.e(afVar);
                        t.this.b();
                    }
                });
            }
        });
        a.a("FileSystem.extract", new ah() { // from class: com.adcolony.sdk.t.7
            @Override // com.adcolony.sdk.ah
            public void a(final af afVar) {
                t.this.a(new Runnable() { // from class: com.adcolony.sdk.t.7.1
                    @Override // java.lang.Runnable
                    public void run() {
                        t.this.f(afVar);
                        t.this.b();
                    }
                });
            }
        });
        a.a("FileSystem.unpack_bundle", new ah() { // from class: com.adcolony.sdk.t.8
            @Override // com.adcolony.sdk.ah
            public void a(final af afVar) {
                t.this.a(new Runnable() { // from class: com.adcolony.sdk.t.8.1
                    @Override // java.lang.Runnable
                    public void run() {
                        t.this.g(afVar);
                        t.this.b();
                    }
                });
            }
        });
        a.a("FileSystem.create_directory", new ah() { // from class: com.adcolony.sdk.t.9
            @Override // com.adcolony.sdk.ah
            public void a(final af afVar) {
                t.this.a(new Runnable() { // from class: com.adcolony.sdk.t.9.1
                    @Override // java.lang.Runnable
                    public void run() {
                        t.this.h(afVar);
                        t.this.b();
                    }
                });
            }
        });
    }

    boolean a(af afVar) {
        JSONObject jSONObjectC = afVar.c();
        String strB = y.b(jSONObjectC, "filepath");
        String strB2 = y.b(jSONObjectC, "data");
        String strB3 = y.b(jSONObjectC, "encoding");
        boolean z = strB3 != null && strB3.equals("utf8");
        a.a().o().b();
        JSONObject jSONObjectA = y.a();
        try {
            a(strB, strB2, z);
            y.a(jSONObjectA, "success", true);
            afVar.a(jSONObjectA).b();
            return true;
        } catch (IOException unused) {
            y.a(jSONObjectA, "success", false);
            afVar.a(jSONObjectA).b();
            return false;
        }
    }

    void a(String str, String str2, boolean z) throws IOException {
        BufferedWriter bufferedWriter = z ? new BufferedWriter(new OutputStreamWriter(new FileOutputStream(str), "UTF-8")) : new BufferedWriter(new OutputStreamWriter(new FileOutputStream(str)));
        bufferedWriter.write(str2);
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    boolean a(af afVar, File file) {
        a.a().o().b();
        JSONObject jSONObjectA = y.a();
        if (a(file)) {
            y.a(jSONObjectA, "success", true);
            afVar.a(jSONObjectA).b();
            return true;
        }
        y.a(jSONObjectA, "success", false);
        afVar.a(jSONObjectA).b();
        return false;
    }

    boolean a(File file) {
        try {
            if (file.isDirectory()) {
                if (file.list().length == 0) {
                    return file.delete();
                }
                String[] list = file.list();
                if (list.length > 0) {
                    return a(new File(file, list[0]));
                }
                if (file.list().length == 0) {
                    return file.delete();
                }
                return false;
            }
            return file.delete();
        } catch (Exception unused) {
            return false;
        }
    }

    boolean b(af afVar) {
        String strB = y.b(afVar.c(), "filepath");
        a.a().o().b();
        JSONObject jSONObjectA = y.a();
        String[] list = new File(strB).list();
        if (list != null) {
            JSONArray jSONArrayB = y.b();
            for (String str : list) {
                JSONObject jSONObjectA2 = y.a();
                y.a(jSONObjectA2, "filename", str);
                if (new File(strB + str).isDirectory()) {
                    y.a(jSONObjectA2, "is_folder", true);
                } else {
                    y.a(jSONObjectA2, "is_folder", false);
                }
                y.a(jSONArrayB, jSONObjectA2);
            }
            y.a(jSONObjectA, "success", true);
            y.a(jSONObjectA, "entries", jSONArrayB);
            afVar.a(jSONObjectA).b();
            return true;
        }
        y.a(jSONObjectA, "success", false);
        afVar.a(jSONObjectA).b();
        return false;
    }

    String c(af afVar) {
        JSONObject jSONObjectC = afVar.c();
        String strB = y.b(jSONObjectC, "filepath");
        String strB2 = y.b(jSONObjectC, "encoding");
        boolean z = strB2 != null && strB2.equals("utf8");
        a.a().o().b();
        JSONObject jSONObjectA = y.a();
        try {
            StringBuilder sbA = a(strB, z);
            y.a(jSONObjectA, "success", true);
            y.a(jSONObjectA, "data", sbA.toString());
            afVar.a(jSONObjectA).b();
            return sbA.toString();
        } catch (IOException unused) {
            y.a(jSONObjectA, "success", false);
            afVar.a(jSONObjectA).b();
            return "";
        }
    }

    StringBuilder a(String str, boolean z) throws IOException {
        BufferedReader bufferedReader;
        File file = new File(str);
        StringBuilder sb = new StringBuilder((int) file.length());
        if (z) {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file.getAbsolutePath()), "UTF-8"));
        } else {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file.getAbsolutePath())));
        }
        while (true) {
            String line = bufferedReader.readLine();
            if (line != null) {
                sb.append(line);
                sb.append("\n");
            } else {
                bufferedReader.close();
                return sb;
            }
        }
    }

    List<String> b(String str, boolean z) throws IOException {
        BufferedReader bufferedReader;
        File file = new File(str);
        file.length();
        ArrayList arrayList = new ArrayList();
        if (z) {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file.getAbsolutePath()), "UTF-8"));
        } else {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file.getAbsolutePath())));
        }
        while (true) {
            String line = bufferedReader.readLine();
            if (line != null) {
                arrayList.add(line);
            } else {
                bufferedReader.close();
                return arrayList;
            }
        }
    }

    boolean d(af afVar) {
        JSONObject jSONObjectC = afVar.c();
        String strB = y.b(jSONObjectC, "filepath");
        String strB2 = y.b(jSONObjectC, "new_filepath");
        a.a().o().b();
        JSONObject jSONObjectA = y.a();
        try {
            if (new File(strB).renameTo(new File(strB2))) {
                y.a(jSONObjectA, "success", true);
                afVar.a(jSONObjectA).b();
                return true;
            }
            y.a(jSONObjectA, "success", false);
            afVar.a(jSONObjectA).b();
            return false;
        } catch (Exception unused) {
            y.a(jSONObjectA, "success", false);
            afVar.a(jSONObjectA).b();
            return false;
        }
    }

    boolean e(af afVar) {
        String strB = y.b(afVar.c(), "filepath");
        a.a().o().b();
        JSONObject jSONObjectA = y.a();
        try {
            boolean zA = a(strB);
            y.a(jSONObjectA, IronSourceConstants.EVENTS_RESULT, zA);
            y.a(jSONObjectA, "success", true);
            afVar.a(jSONObjectA).b();
            return zA;
        } catch (Exception e) {
            y.a(jSONObjectA, IronSourceConstants.EVENTS_RESULT, false);
            y.a(jSONObjectA, "success", false);
            afVar.a(jSONObjectA).b();
            e.printStackTrace();
            return false;
        }
    }

    boolean a(String str) throws Exception {
        return new File(str).exists();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean h(af afVar) {
        String strB = y.b(afVar.c(), "filepath");
        a.a().o().b();
        JSONObject jSONObjectA = y.a();
        try {
            if (new File(strB).mkdir()) {
                y.a(jSONObjectA, "success", true);
                afVar.a(jSONObjectA).b();
                return true;
            }
            y.a(jSONObjectA, "success", false);
            return false;
        } catch (Exception unused) {
            y.a(jSONObjectA, "success", false);
            afVar.a(jSONObjectA).b();
            return false;
        }
    }

    void a(Runnable runnable) {
        if (this.a.isEmpty() && !this.b) {
            this.b = true;
            runnable.run();
        } else {
            this.a.push(runnable);
        }
    }

    void b() {
        this.b = false;
        if (this.a.isEmpty()) {
            return;
        }
        this.b = true;
        this.a.removeLast().run();
    }
}
