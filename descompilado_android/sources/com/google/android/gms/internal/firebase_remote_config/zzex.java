package com.google.android.gms.internal.firebase_remote_config;

import android.content.Context;
import androidx.annotation.GuardedBy;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
@ThreadSafe
public final class zzex {

    @GuardedBy("ConfigStorageClient.class")
    private static final Map<String, zzex> zzkg = new HashMap();
    private final Context zzja;
    private final String zzlq;

    private zzex(Context context, String str) {
        this.zzja = context;
        this.zzlq = str;
    }

    public final synchronized Void zzh(zzen zzenVar) throws IOException {
        FileOutputStream fileOutputStreamOpenFileOutput = this.zzja.openFileOutput(this.zzlq, 0);
        Throwable th = null;
        try {
            try {
                fileOutputStreamOpenFileOutput.write(zzenVar.toString().getBytes("UTF-8"));
                if (fileOutputStreamOpenFileOutput != null) {
                    zza((Throwable) null, fileOutputStreamOpenFileOutput);
                }
            } catch (Throwable th2) {
                th = th2;
                throw th;
            }
        } catch (Throwable th3) {
            if (fileOutputStreamOpenFileOutput != null) {
                zza(th, fileOutputStreamOpenFileOutput);
            }
            throw th3;
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:17:0x0037 A[Catch: all -> 0x003b, FileNotFoundException | JSONException -> 0x003e, FileNotFoundException | JSONException -> 0x003e, TRY_ENTER, TryCatch #2 {FileNotFoundException | JSONException -> 0x003e, blocks: (B:4:0x0002, B:7:0x0027, B:7:0x0027, B:17:0x0037, B:17:0x0037, B:18:0x003a, B:18:0x003a), top: B:26:0x0002 }] */
    /* JADX WARN: Code duplicated, block: B:29:? A[Catch: all -> 0x003b, FileNotFoundException | JSONException -> 0x003e, FileNotFoundException | JSONException -> 0x003e, SYNTHETIC, TRY_LEAVE, TryCatch #2 {FileNotFoundException | JSONException -> 0x003e, blocks: (B:4:0x0002, B:7:0x0027, B:7:0x0027, B:17:0x0037, B:17:0x0037, B:18:0x003a, B:18:0x003a), top: B:26:0x0002 }] */
    @Nullable
    public final synchronized zzen zzdb() throws IOException {
        Throwable th;
        zzen zzenVarZza;
        try {
            FileInputStream fileInputStreamOpenFileInput = this.zzja.openFileInput(this.zzlq);
            try {
                byte[] bArr = new byte[fileInputStreamOpenFileInput.available()];
                fileInputStreamOpenFileInput.read(bArr, 0, bArr.length);
                zzenVarZza = zzen.zza(new JSONObject(new String(bArr, "UTF-8")));
                if (fileInputStreamOpenFileInput != null) {
                    zza((Throwable) null, fileInputStreamOpenFileInput);
                }
            } catch (Throwable th2) {
                th = th2;
                th = null;
                if (fileInputStreamOpenFileInput != null) {
                    throw th;
                }
                zza(th, fileInputStreamOpenFileInput);
                throw th;
            }
        } catch (FileNotFoundException | JSONException unused) {
            return null;
        }
        return zzenVarZza;
    }

    public final synchronized Void zzdc() {
        this.zzja.deleteFile(this.zzlq);
        return null;
    }

    public static synchronized zzex zzb(Context context, String str) {
        if (!zzkg.containsKey(str)) {
            zzkg.put(str, new zzex(context, str));
        }
        return zzkg.get(str);
    }

    final String getFileName() {
        return this.zzlq;
    }

    private static /* synthetic */ void zza(Throwable th, FileOutputStream fileOutputStream) throws IOException {
        if (th == null) {
            fileOutputStream.close();
            return;
        }
        try {
            fileOutputStream.close();
        } catch (Throwable th2) {
            zzea.zza(th, th2);
        }
    }

    private static /* synthetic */ void zza(Throwable th, FileInputStream fileInputStream) throws IOException {
        if (th == null) {
            fileInputStream.close();
            return;
        }
        try {
            fileInputStream.close();
        } catch (Throwable th2) {
            zzea.zza(th, th2);
        }
    }
}
