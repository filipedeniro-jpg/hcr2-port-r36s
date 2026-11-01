package com.facebook.ads.redexgen.X;

import android.content.Context;
import dalvik.system.DexClassLoader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class JF {
    private JF() {
    }

    public static JM B(Context ctx) {
        JL jlB = JM.B();
        try {
            File extractedDex = D(ctx, "impl.dex");
            File fileE = E(ctx);
            for (JE je : JE.values()) {
                if (je.C()) {
                    jlB.A(je, G(ctx, "process-" + je.m257B()));
                } else {
                    jlB.A(je, H(ctx, "lib-" + je.A() + ".so", new DexClassLoader(extractedDex.getCanonicalPath(), fileE.getCanonicalPath(), "", JF.class.getClassLoader())));
                }
            }
            C(extractedDex);
            C(fileE);
            return jlB.C();
        } catch (Throwable th) {
            return jlB.B(th);
        }
    }

    private static void C(File file) throws Exception {
        if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                C(file2);
            }
        }
        if (!file.delete()) {
            throw new Exception("Failed to delete '" + file + "'");
        }
    }

    private static File D(Context ctx, String rsrc) throws IOException {
        GZIPInputStream gZIPInputStream = new GZIPInputStream(new ByteArrayInputStream(JG.B()));
        byte[] bArrF = F(gZIPInputStream);
        gZIPInputStream.close();
        ZipInputStream zipIn = new ZipInputStream(new ByteArrayInputStream(bArrF));
        File fileCreateTempFile = null;
        while (true) {
            ZipEntry nextEntry = zipIn.getNextEntry();
            if (nextEntry == null) {
                break;
            }
            if (nextEntry.getName().equals(rsrc)) {
                fileCreateTempFile = File.createTempFile("lib", null, ctx.getCacheDir());
                FileOutputStream fileOutputStream = new FileOutputStream(fileCreateTempFile);
                fileOutputStream.write(F(zipIn));
                fileOutputStream.close();
                break;
            }
        }
        zipIn.close();
        if (fileCreateTempFile != null) {
            return fileCreateTempFile;
        }
        throw new IOException("Could not find resource '" + rsrc + "' in data");
    }

    private static File E(Context ctx) throws Exception {
        File tempFile = File.createTempFile("tempdir", null, ctx.getCacheDir());
        if (!tempFile.delete()) {
            throw new Exception("Failed to delete tmp file '" + tempFile.getCanonicalPath() + "'");
        }
        if (!tempFile.mkdir()) {
            throw new Exception("Failed to mkdir '" + tempFile.getCanonicalPath() + "'");
        }
        return tempFile;
    }

    private static byte[] F(InputStream stream) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] bArr = new byte[4096];
        while (true) {
            int i = stream.read(bArr);
            if (i > 0) {
                output.write(bArr, 0, i);
            } else {
                return output.toByteArray();
            }
        }
    }

    private static JK G(Context ctx, String exe) {
        try {
            File fileD = D(ctx, exe);
            if (!fileD.setExecutable(true)) {
                throw new Exception("Cannot set '" + fileD.getCanonicalPath() + "' as executable");
            }
            Process processExec = Runtime.getRuntime().exec(new String[]{fileD.getCanonicalPath()});
            String str = new String(F(processExec.getInputStream()));
            String str2 = new String(F(processExec.getErrorStream()));
            int ec = processExec.waitFor();
            if (ec != 0) {
                throw new Exception("Process returned " + ec + " (exe: " + fileD.getCanonicalPath() + ", output:" + str + ", error: " + str2 + ")");
            }
            if (str.equals("Hello world!")) {
                throw new Exception("Invalid message: '" + str);
            }
            C(fileD);
            return new JK(true);
        } catch (Throwable th) {
            return new JK(false);
        }
    }

    private static JK H(Context ctx, String libRsrcName, ClassLoader abiLoader) {
        try {
            File fileD = D(ctx, libRsrcName);
            JN jn = (JN) abiLoader.loadClass("com.facebook.ads.internal.jni.NativeModuleImpl").newInstance();
            jn.load(fileD.getCanonicalPath());
            String strSayHello = jn.sayHello();
            if (!strSayHello.equals("Hello world!")) {
                throw new Exception("Invalid message: '" + strSayHello + "'");
            }
            C(fileD);
            return new JK(true);
        } catch (Throwable th) {
            return new JK(false);
        }
    }
}
