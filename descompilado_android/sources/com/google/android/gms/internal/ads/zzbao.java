package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbao extends zzbah {
    private static final Set<String> zzede = Collections.synchronizedSet(new HashSet());
    private static final DecimalFormat zzedf = new DecimalFormat("#,###");
    private File zzedg;
    private boolean zzedh;

    public zzbao(zzayt zzaytVar) {
        super(zzaytVar);
        File cacheDir = this.mContext.getCacheDir();
        if (cacheDir == null) {
            zzatm.zzeu("Context.getCacheDir() returned null");
            return;
        }
        this.zzedg = new File(cacheDir, "admobVideoStreams");
        if (!this.zzedg.isDirectory() && !this.zzedg.mkdirs()) {
            String strValueOf = String.valueOf(this.zzedg.getAbsolutePath());
            zzatm.zzeu(strValueOf.length() != 0 ? "Could not create preload cache directory at ".concat(strValueOf) : new String("Could not create preload cache directory at "));
            this.zzedg = null;
        } else {
            if (this.zzedg.setReadable(true, false) && this.zzedg.setExecutable(true, false)) {
                return;
            }
            String strValueOf2 = String.valueOf(this.zzedg.getAbsolutePath());
            zzatm.zzeu(strValueOf2.length() != 0 ? "Could not set cache file permissions at ".concat(strValueOf2) : new String("Could not set cache file permissions at "));
            this.zzedg = null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:19:0x0048  */
    /* JADX WARN: Code duplicated, block: B:249:0x0534  */
    /* JADX WARN: Code duplicated, block: B:253:0x0544  */
    /* JADX WARN: Code duplicated, block: B:254:0x0568  */
    /* JADX WARN: Code duplicated, block: B:257:0x0591  */
    /* JADX WARN: Code duplicated, block: B:261:0x05a7  */
    /* JADX WARN: Code duplicated, block: B:262:0x05ac  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 22, insn: 0x0525: MOVE (r15 I:??[OBJECT, ARRAY]) = (r22 I:??[OBJECT, ARRAY]), block:B:241:0x0525 */
    /* JADX WARN: Type inference failed for: r10v11 */
    /* JADX WARN: Type inference failed for: r10v12 */
    /* JADX WARN: Type inference failed for: r10v13 */
    /* JADX WARN: Type inference failed for: r10v5 */
    /* JADX WARN: Type inference failed for: r10v6 */
    /* JADX WARN: Type inference failed for: r10v7 */
    /* JADX WARN: Type inference failed for: r1v10, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v24 */
    /* JADX WARN: Type inference failed for: r1v25 */
    /* JADX WARN: Type inference failed for: r1v26 */
    /* JADX WARN: Type inference failed for: r1v29 */
    /* JADX WARN: Type inference failed for: r1v31 */
    /* JADX WARN: Type inference failed for: r1v35, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v41 */
    /* JADX WARN: Type inference failed for: r1v42 */
    /* JADX WARN: Type inference failed for: r1v51 */
    /* JADX WARN: Type inference failed for: r1v56 */
    /* JADX WARN: Type inference failed for: r1v63 */
    /* JADX WARN: Type inference failed for: r1v76 */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Type inference failed for: r1v92 */
    /* JADX WARN: Type inference failed for: r1v93 */
    /* JADX WARN: Type inference failed for: r1v94 */
    /* JADX WARN: Type inference failed for: r1v95 */
    /* JADX WARN: Type inference failed for: r1v96 */
    /* JADX WARN: Type inference failed for: r24v0 */
    /* JADX WARN: Type inference failed for: r24v1 */
    /* JADX WARN: Type inference failed for: r24v2 */
    /* JADX WARN: Type inference failed for: r24v3 */
    /* JADX WARN: Type inference failed for: r24v4, types: [int] */
    /* JADX WARN: Type inference failed for: r24v5 */
    /* JADX WARN: Type inference failed for: r24v6 */
    /* JADX WARN: Type inference failed for: r24v7 */
    /* JADX WARN: Type inference failed for: r24v8 */
    /* JADX WARN: Type inference failed for: r24v9 */
    @Override // com.google.android.gms.internal.ads.zzbah
    public final boolean zzfd(String str) {
        int i;
        ?? r1;
        String strConcat;
        ?? r2;
        String strValueOf;
        String str2;
        String str3;
        FileOutputStream fileOutputStream;
        ?? r3;
        ?? r4;
        ?? r24;
        String str4;
        ?? r5;
        int responseCode;
        boolean zDelete;
        FileOutputStream fileOutputStream2 = null;
        int i2 = 0;
        if (this.zzedg == null) {
            zza(str, null, "noCacheDir", null);
            return false;
        }
        do {
            File file = this.zzedg;
            if (file == null) {
                i = 0;
            } else {
                i = 0;
                for (File file2 : file.listFiles()) {
                    if (!file2.getName().endsWith(".done")) {
                        i++;
                    }
                }
            }
            if (i > ((Integer) zzuo.zzoj().zzd(zzyt.zzcha)).intValue()) {
                File file3 = this.zzedg;
                if (file3 == null) {
                    zDelete = false;
                } else {
                    long j = Long.MAX_VALUE;
                    File file4 = null;
                    for (File file5 : file3.listFiles()) {
                        if (!file5.getName().endsWith(".done")) {
                            long jLastModified = file5.lastModified();
                            if (jLastModified < j) {
                                file4 = file5;
                                j = jLastModified;
                            }
                        }
                    }
                    if (file4 != null) {
                        zDelete = file4.delete();
                        File fileZzc = zzc(file4);
                        if (fileZzc.isFile()) {
                            zDelete &= fileZzc.delete();
                        }
                    } else {
                        zDelete = false;
                    }
                }
            } else {
                File file6 = new File(this.zzedg, zzfe(str));
                File fileZzc2 = zzc(file6);
                if (file6.isFile() && fileZzc2.isFile()) {
                    int length = (int) file6.length();
                    String strValueOf2 = String.valueOf(str);
                    zzatm.zzdv(strValueOf2.length() != 0 ? "Stream cache hit at ".concat(strValueOf2) : new String("Stream cache hit at "));
                    zza(str, file6.getAbsolutePath(), length);
                    return true;
                }
                String strValueOf3 = String.valueOf(this.zzedg.getAbsolutePath());
                String strValueOf4 = String.valueOf(str);
                String strConcat2 = strValueOf4.length() != 0 ? strValueOf3.concat(strValueOf4) : new String(strValueOf3);
                synchronized (zzede) {
                    if (zzede.contains(strConcat2)) {
                        String strValueOf5 = String.valueOf(str);
                        zzatm.zzeu(strValueOf5.length() != 0 ? "Stream cache already in progress at ".concat(strValueOf5) : new String("Stream cache already in progress at "));
                        zza(str, file6.getAbsolutePath(), "inProgress", null);
                        return false;
                    }
                    zzede.add(strConcat2);
                    String str5 = "error";
                    try {
                        com.google.android.gms.ads.internal.zzp.zzkk();
                        int iIntValue = ((Integer) zzuo.zzoj().zzd(zzyt.zzchf)).intValue();
                        URL url = new URL(str);
                        int i3 = 0;
                        while (true) {
                            i3++;
                            try {
                                try {
                                    if (i3 <= 20) {
                                        URLConnection uRLConnectionOpenConnection = url.openConnection();
                                        uRLConnectionOpenConnection.setConnectTimeout(iIntValue);
                                        uRLConnectionOpenConnection.setReadTimeout(iIntValue);
                                        if (!(uRLConnectionOpenConnection instanceof HttpURLConnection)) {
                                            throw new IOException("Invalid protocol.");
                                        }
                                        HttpURLConnection httpURLConnection = (HttpURLConnection) uRLConnectionOpenConnection;
                                        zzawi zzawiVar = new zzawi();
                                        zzawiVar.zza(httpURLConnection, (byte[]) null);
                                        httpURLConnection.setInstanceFollowRedirects(false);
                                        int responseCode2 = httpURLConnection.getResponseCode();
                                        zzawiVar.zza(httpURLConnection, responseCode2);
                                        if (responseCode2 / 100 == 3) {
                                            try {
                                                String headerField = httpURLConnection.getHeaderField("Location");
                                                if (headerField == null) {
                                                    throw new IOException("Missing Location header in redirect");
                                                }
                                                URL url2 = new URL(url, headerField);
                                                String protocol = url2.getProtocol();
                                                if (protocol == null) {
                                                    throw new IOException("Protocol is null");
                                                }
                                                if (!protocol.equals("http") && !protocol.equals("https")) {
                                                    String strValueOf6 = String.valueOf(protocol);
                                                    throw new IOException(strValueOf6.length() != 0 ? "Unsupported scheme: ".concat(strValueOf6) : new String("Unsupported scheme: "));
                                                }
                                                String strValueOf7 = String.valueOf(headerField);
                                                zzatm.zzdv(strValueOf7.length() != 0 ? "Redirecting to ".concat(strValueOf7) : new String("Redirecting to "));
                                                httpURLConnection.disconnect();
                                                url = url2;
                                            } catch (IOException | RuntimeException e) {
                                                e = e;
                                                strConcat = null;
                                            }
                                        } else if ((httpURLConnection instanceof HttpURLConnection) && (responseCode = httpURLConnection.getResponseCode()) >= 400) {
                                            str5 = "badUrl";
                                            String strValueOf8 = String.valueOf(Integer.toString(responseCode));
                                            strConcat = strValueOf8.length() != 0 ? "HTTP request failed. Code: ".concat(strValueOf8) : new String("HTTP request failed. Code: ");
                                            try {
                                                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 32);
                                                sb.append("HTTP status code ");
                                                sb.append(responseCode);
                                                sb.append(" at ");
                                                sb.append(str);
                                                throw new IOException(sb.toString());
                                            } catch (IOException e2) {
                                                e = e2;
                                            } catch (RuntimeException e3) {
                                                e = e3;
                                            }
                                        } else {
                                            int contentLength = httpURLConnection.getContentLength();
                                            if (contentLength < 0) {
                                                String strValueOf9 = String.valueOf(str);
                                                zzatm.zzeu(strValueOf9.length() != 0 ? "Stream cache aborted, missing content-length header at ".concat(strValueOf9) : new String("Stream cache aborted, missing content-length header at "));
                                                zza(str, file6.getAbsolutePath(), "contentLengthMissing", null);
                                                zzede.remove(strConcat2);
                                                return false;
                                            }
                                            String str6 = zzedf.format(contentLength);
                                            int iIntValue2 = ((Integer) zzuo.zzoj().zzd(zzyt.zzchb)).intValue();
                                            if (contentLength > iIntValue2) {
                                                StringBuilder sb2 = new StringBuilder(String.valueOf(str6).length() + 33 + String.valueOf(str).length());
                                                sb2.append("Content length ");
                                                sb2.append(str6);
                                                sb2.append(" exceeds limit at ");
                                                sb2.append(str);
                                                zzatm.zzeu(sb2.toString());
                                                String strValueOf10 = String.valueOf(str6);
                                                zza(str, file6.getAbsolutePath(), "sizeExceeded", strValueOf10.length() != 0 ? "File too big for full file cache. Size: ".concat(strValueOf10) : new String("File too big for full file cache. Size: "));
                                                zzede.remove(strConcat2);
                                                return false;
                                            }
                                            StringBuilder sb3 = new StringBuilder(String.valueOf(str6).length() + 20 + String.valueOf(str).length());
                                            sb3.append("Caching ");
                                            sb3.append(str6);
                                            sb3.append(" bytes from ");
                                            sb3.append(str);
                                            zzatm.zzdv(sb3.toString());
                                            ReadableByteChannel readableByteChannelNewChannel = Channels.newChannel(httpURLConnection.getInputStream());
                                            FileOutputStream fileOutputStream3 = new FileOutputStream(file6);
                                            try {
                                                FileChannel channel = fileOutputStream3.getChannel();
                                                ByteBuffer byteBufferAllocate = ByteBuffer.allocate(1048576);
                                                Clock clockZzkf = com.google.android.gms.ads.internal.zzp.zzkf();
                                                long jCurrentTimeMillis = clockZzkf.currentTimeMillis();
                                                ?? r10 = strConcat2;
                                                try {
                                                    zzavu zzavuVar = new zzavu(((Long) zzuo.zzoj().zzd(zzyt.zzche)).longValue());
                                                    long jLongValue = ((Long) zzuo.zzoj().zzd(zzyt.zzchd)).longValue();
                                                    r10 = r10;
                                                    while (true) {
                                                        int i4 = readableByteChannelNewChannel.read(byteBufferAllocate);
                                                        if (i4 < 0) {
                                                            fileOutputStream = fileOutputStream3;
                                                            r24 = r10;
                                                            str4 = str5;
                                                            try {
                                                                fileOutputStream.close();
                                                                if (zzatm.isLoggable(3)) {
                                                                    String str7 = zzedf.format(i2);
                                                                    StringBuilder sb4 = new StringBuilder(String.valueOf(str7).length() + 22 + String.valueOf(str).length());
                                                                    sb4.append("Preloaded ");
                                                                    sb4.append(str7);
                                                                    sb4.append(" bytes from ");
                                                                    sb4.append(str);
                                                                    zzatm.zzdv(sb4.toString());
                                                                }
                                                                file6.setReadable(true, false);
                                                                if (fileZzc2.isFile()) {
                                                                    fileZzc2.setLastModified(System.currentTimeMillis());
                                                                } else {
                                                                    try {
                                                                        fileZzc2.createNewFile();
                                                                    } catch (IOException unused) {
                                                                    }
                                                                }
                                                                zza(str, file6.getAbsolutePath(), i2);
                                                                r5 = r24;
                                                                try {
                                                                    zzede.remove(r5);
                                                                    return true;
                                                                } catch (IOException e4) {
                                                                    e = e4;
                                                                } catch (RuntimeException e5) {
                                                                    e = e5;
                                                                }
                                                            } catch (IOException | RuntimeException e6) {
                                                                e = e6;
                                                                r5 = r24;
                                                            }
                                                        } else {
                                                            i2 += i4;
                                                            if (i2 > iIntValue2) {
                                                                try {
                                                                    str5 = "sizeExceeded";
                                                                    String strValueOf11 = String.valueOf(Integer.toString(i2));
                                                                    String strConcat3 = strValueOf11.length() != 0 ? "File too big for full file cache. Size: ".concat(strValueOf11) : new String("File too big for full file cache. Size: ");
                                                                    try {
                                                                        throw new IOException("stream cache file size limit exceeded");
                                                                    } catch (IOException | RuntimeException e7) {
                                                                        e = e7;
                                                                        strConcat = strConcat3;
                                                                        fileOutputStream2 = fileOutputStream3;
                                                                        r2 = r10;
                                                                        if (e instanceof RuntimeException) {
                                                                            com.google.android.gms.ads.internal.zzp.zzkc().zza(e, "VideoStreamFullFileCache.preload");
                                                                        }
                                                                        fileOutputStream2.close();
                                                                        if (this.zzedh) {
                                                                            StringBuilder sb5 = new StringBuilder(String.valueOf(str).length() + 26);
                                                                            sb5.append("Preload aborted for URL \"");
                                                                            sb5.append(str);
                                                                            sb5.append("\"");
                                                                            zzatm.zzet(sb5.toString());
                                                                        } else {
                                                                            StringBuilder sb6 = new StringBuilder(String.valueOf(str).length() + 25);
                                                                            sb6.append("Preload failed for URL \"");
                                                                            sb6.append(str);
                                                                            sb6.append("\"");
                                                                            zzatm.zzd(sb6.toString(), e);
                                                                        }
                                                                        if (file6.exists()) {
                                                                            strValueOf = String.valueOf(file6.getAbsolutePath());
                                                                            if (strValueOf.length() != 0) {
                                                                                str2 = "Could not delete partial cache file at ".concat(strValueOf);
                                                                            } else {
                                                                                str2 = new String("Could not delete partial cache file at ");
                                                                            }
                                                                            zzatm.zzeu(str2);
                                                                        }
                                                                        zza(str, file6.getAbsolutePath(), str5, strConcat);
                                                                        zzede.remove(r2);
                                                                        return false;
                                                                    }
                                                                } catch (IOException | RuntimeException e8) {
                                                                    e = e8;
                                                                    strConcat = null;
                                                                }
                                                            } else {
                                                                try {
                                                                    byteBufferAllocate.flip();
                                                                    while (channel.write(byteBufferAllocate) > 0) {
                                                                    }
                                                                    byteBufferAllocate.clear();
                                                                    r24 = ((clockZzkf.currentTimeMillis() - jCurrentTimeMillis) > (1000 * jLongValue) ? 1 : ((clockZzkf.currentTimeMillis() - jCurrentTimeMillis) == (1000 * jLongValue) ? 0 : -1));
                                                                    try {
                                                                        if (r24 > 0) {
                                                                            FileOutputStream fileOutputStream4 = fileOutputStream3;
                                                                            ?? r25 = r10;
                                                                            str5 = "downloadTimeout";
                                                                            String string = Long.toString(jLongValue);
                                                                            StringBuilder sb7 = new StringBuilder(String.valueOf(string).length() + 29);
                                                                            sb7.append("Timeout exceeded. Limit: ");
                                                                            sb7.append(string);
                                                                            sb7.append(" sec");
                                                                            String string2 = sb7.toString();
                                                                            try {
                                                                                throw new IOException("stream cache time limit exceeded");
                                                                            } catch (IOException | RuntimeException e9) {
                                                                                e = e9;
                                                                                strConcat = string2;
                                                                                r2 = r25;
                                                                                fileOutputStream2 = fileOutputStream4;
                                                                            }
                                                                        } else {
                                                                            ByteBuffer byteBuffer = byteBufferAllocate;
                                                                            if (this.zzedh) {
                                                                                throw new IOException("abort requested");
                                                                            }
                                                                            if (zzavuVar.tryAcquire()) {
                                                                                str4 = str5;
                                                                                try {
                                                                                    r24 = r10;
                                                                                    fileOutputStream = fileOutputStream3;
                                                                                    try {
                                                                                        try {
                                                                                            zzawe.zzzb.post(new zzbag(this, str, file6.getAbsolutePath(), i2, contentLength, false));
                                                                                        } catch (IOException e10) {
                                                                                            e = e10;
                                                                                            str5 = str4;
                                                                                            r4 = r24;
                                                                                            fileOutputStream2 = fileOutputStream;
                                                                                            strConcat = null;
                                                                                            r2 = r4;
                                                                                            if (e instanceof RuntimeException) {
                                                                                                com.google.android.gms.ads.internal.zzp.zzkc().zza(e, "VideoStreamFullFileCache.preload");
                                                                                            }
                                                                                            fileOutputStream2.close();
                                                                                            if (this.zzedh) {
                                                                                                StringBuilder sb8 = new StringBuilder(String.valueOf(str).length() + 26);
                                                                                                sb8.append("Preload aborted for URL \"");
                                                                                                sb8.append(str);
                                                                                                sb8.append("\"");
                                                                                                zzatm.zzet(sb8.toString());
                                                                                            } else {
                                                                                                StringBuilder sb9 = new StringBuilder(String.valueOf(str).length() + 25);
                                                                                                sb9.append("Preload failed for URL \"");
                                                                                                sb9.append(str);
                                                                                                sb9.append("\"");
                                                                                                zzatm.zzd(sb9.toString(), e);
                                                                                            }
                                                                                            if (file6.exists()) {
                                                                                                strValueOf = String.valueOf(file6.getAbsolutePath());
                                                                                                if (strValueOf.length() != 0) {
                                                                                                    str2 = "Could not delete partial cache file at ".concat(strValueOf);
                                                                                                } else {
                                                                                                    str2 = new String("Could not delete partial cache file at ");
                                                                                                }
                                                                                                zzatm.zzeu(str2);
                                                                                            }
                                                                                            zza(str, file6.getAbsolutePath(), str5, strConcat);
                                                                                            zzede.remove(r2);
                                                                                            return false;
                                                                                        }
                                                                                    } catch (RuntimeException e11) {
                                                                                        e = e11;
                                                                                        str5 = str4;
                                                                                        r4 = r24;
                                                                                        fileOutputStream2 = fileOutputStream;
                                                                                        strConcat = null;
                                                                                        r2 = r4;
                                                                                        if (e instanceof RuntimeException) {
                                                                                            com.google.android.gms.ads.internal.zzp.zzkc().zza(e, "VideoStreamFullFileCache.preload");
                                                                                        }
                                                                                        fileOutputStream2.close();
                                                                                        if (this.zzedh) {
                                                                                            StringBuilder sb10 = new StringBuilder(String.valueOf(str).length() + 26);
                                                                                            sb10.append("Preload aborted for URL \"");
                                                                                            sb10.append(str);
                                                                                            sb10.append("\"");
                                                                                            zzatm.zzet(sb10.toString());
                                                                                        } else {
                                                                                            StringBuilder sb11 = new StringBuilder(String.valueOf(str).length() + 25);
                                                                                            sb11.append("Preload failed for URL \"");
                                                                                            sb11.append(str);
                                                                                            sb11.append("\"");
                                                                                            zzatm.zzd(sb11.toString(), e);
                                                                                        }
                                                                                        if (file6.exists()) {
                                                                                            strValueOf = String.valueOf(file6.getAbsolutePath());
                                                                                            if (strValueOf.length() != 0) {
                                                                                                str2 = "Could not delete partial cache file at ".concat(strValueOf);
                                                                                            } else {
                                                                                                str2 = new String("Could not delete partial cache file at ");
                                                                                            }
                                                                                            zzatm.zzeu(str2);
                                                                                        }
                                                                                        zza(str, file6.getAbsolutePath(), str5, strConcat);
                                                                                        zzede.remove(r2);
                                                                                        return false;
                                                                                    }
                                                                                } catch (IOException e12) {
                                                                                    e = e12;
                                                                                    fileOutputStream = fileOutputStream3;
                                                                                    r5 = r10;
                                                                                    str5 = str4;
                                                                                    r4 = r5;
                                                                                    fileOutputStream2 = fileOutputStream;
                                                                                    strConcat = null;
                                                                                    r2 = r4;
                                                                                    if (e instanceof RuntimeException) {
                                                                                        com.google.android.gms.ads.internal.zzp.zzkc().zza(e, "VideoStreamFullFileCache.preload");
                                                                                    }
                                                                                    fileOutputStream2.close();
                                                                                    if (this.zzedh) {
                                                                                        StringBuilder sb12 = new StringBuilder(String.valueOf(str).length() + 26);
                                                                                        sb12.append("Preload aborted for URL \"");
                                                                                        sb12.append(str);
                                                                                        sb12.append("\"");
                                                                                        zzatm.zzet(sb12.toString());
                                                                                    } else {
                                                                                        StringBuilder sb13 = new StringBuilder(String.valueOf(str).length() + 25);
                                                                                        sb13.append("Preload failed for URL \"");
                                                                                        sb13.append(str);
                                                                                        sb13.append("\"");
                                                                                        zzatm.zzd(sb13.toString(), e);
                                                                                    }
                                                                                    if (file6.exists()) {
                                                                                        strValueOf = String.valueOf(file6.getAbsolutePath());
                                                                                        if (strValueOf.length() != 0) {
                                                                                            str2 = "Could not delete partial cache file at ".concat(strValueOf);
                                                                                        } else {
                                                                                            str2 = new String("Could not delete partial cache file at ");
                                                                                        }
                                                                                        zzatm.zzeu(str2);
                                                                                    }
                                                                                    zza(str, file6.getAbsolutePath(), str5, strConcat);
                                                                                    zzede.remove(r2);
                                                                                    return false;
                                                                                } catch (RuntimeException e13) {
                                                                                    e = e13;
                                                                                    fileOutputStream = fileOutputStream3;
                                                                                    r5 = r10;
                                                                                    str5 = str4;
                                                                                    r4 = r5;
                                                                                    fileOutputStream2 = fileOutputStream;
                                                                                    strConcat = null;
                                                                                    r2 = r4;
                                                                                    if (e instanceof RuntimeException) {
                                                                                        com.google.android.gms.ads.internal.zzp.zzkc().zza(e, "VideoStreamFullFileCache.preload");
                                                                                    }
                                                                                    fileOutputStream2.close();
                                                                                    if (this.zzedh) {
                                                                                        StringBuilder sb14 = new StringBuilder(String.valueOf(str).length() + 26);
                                                                                        sb14.append("Preload aborted for URL \"");
                                                                                        sb14.append(str);
                                                                                        sb14.append("\"");
                                                                                        zzatm.zzet(sb14.toString());
                                                                                    } else {
                                                                                        StringBuilder sb15 = new StringBuilder(String.valueOf(str).length() + 25);
                                                                                        sb15.append("Preload failed for URL \"");
                                                                                        sb15.append(str);
                                                                                        sb15.append("\"");
                                                                                        zzatm.zzd(sb15.toString(), e);
                                                                                    }
                                                                                    if (file6.exists()) {
                                                                                        strValueOf = String.valueOf(file6.getAbsolutePath());
                                                                                        if (strValueOf.length() != 0) {
                                                                                            str2 = "Could not delete partial cache file at ".concat(strValueOf);
                                                                                        } else {
                                                                                            str2 = new String("Could not delete partial cache file at ");
                                                                                        }
                                                                                        zzatm.zzeu(str2);
                                                                                    }
                                                                                    zza(str, file6.getAbsolutePath(), str5, strConcat);
                                                                                    zzede.remove(r2);
                                                                                    return false;
                                                                                }
                                                                            } else {
                                                                                fileOutputStream = fileOutputStream3;
                                                                                r24 = r10;
                                                                                str4 = str5;
                                                                            }
                                                                            zzavuVar = zzavuVar;
                                                                            byteBufferAllocate = byteBuffer;
                                                                            readableByteChannelNewChannel = readableByteChannelNewChannel;
                                                                            str5 = str4;
                                                                            r10 = r24;
                                                                            channel = channel;
                                                                            iIntValue2 = iIntValue2;
                                                                            fileOutputStream3 = fileOutputStream;
                                                                            contentLength = contentLength;
                                                                        }
                                                                    } catch (IOException e14) {
                                                                        e = e14;
                                                                        r4 = r24;
                                                                        fileOutputStream2 = fileOutputStream;
                                                                        strConcat = null;
                                                                        r2 = r4;
                                                                    } catch (RuntimeException e15) {
                                                                        e = e15;
                                                                        r4 = r24;
                                                                        fileOutputStream2 = fileOutputStream;
                                                                        strConcat = null;
                                                                        r2 = r4;
                                                                    }
                                                                } catch (IOException | RuntimeException e16) {
                                                                    e = e16;
                                                                    fileOutputStream = fileOutputStream3;
                                                                    r4 = r10;
                                                                    fileOutputStream2 = fileOutputStream;
                                                                    strConcat = null;
                                                                    r2 = r4;
                                                                }
                                                            }
                                                            if (e instanceof RuntimeException) {
                                                                com.google.android.gms.ads.internal.zzp.zzkc().zza(e, "VideoStreamFullFileCache.preload");
                                                            }
                                                            fileOutputStream2.close();
                                                            if (this.zzedh) {
                                                                StringBuilder sb16 = new StringBuilder(String.valueOf(str).length() + 26);
                                                                sb16.append("Preload aborted for URL \"");
                                                                sb16.append(str);
                                                                sb16.append("\"");
                                                                zzatm.zzet(sb16.toString());
                                                            } else {
                                                                StringBuilder sb17 = new StringBuilder(String.valueOf(str).length() + 25);
                                                                sb17.append("Preload failed for URL \"");
                                                                sb17.append(str);
                                                                sb17.append("\"");
                                                                zzatm.zzd(sb17.toString(), e);
                                                            }
                                                            if (file6.exists() && !file6.delete()) {
                                                                strValueOf = String.valueOf(file6.getAbsolutePath());
                                                                if (strValueOf.length() != 0) {
                                                                    str2 = "Could not delete partial cache file at ".concat(strValueOf);
                                                                } else {
                                                                    str2 = new String("Could not delete partial cache file at ");
                                                                }
                                                                zzatm.zzeu(str2);
                                                            }
                                                            zza(str, file6.getAbsolutePath(), str5, strConcat);
                                                            zzede.remove(r2);
                                                            return false;
                                                        }
                                                        str5 = str4;
                                                        r4 = r5;
                                                        fileOutputStream2 = fileOutputStream;
                                                        strConcat = null;
                                                        r2 = r4;
                                                        if (e instanceof RuntimeException) {
                                                            com.google.android.gms.ads.internal.zzp.zzkc().zza(e, "VideoStreamFullFileCache.preload");
                                                        }
                                                        fileOutputStream2.close();
                                                        if (this.zzedh) {
                                                            StringBuilder sb18 = new StringBuilder(String.valueOf(str).length() + 26);
                                                            sb18.append("Preload aborted for URL \"");
                                                            sb18.append(str);
                                                            sb18.append("\"");
                                                            zzatm.zzet(sb18.toString());
                                                        } else {
                                                            StringBuilder sb19 = new StringBuilder(String.valueOf(str).length() + 25);
                                                            sb19.append("Preload failed for URL \"");
                                                            sb19.append(str);
                                                            sb19.append("\"");
                                                            zzatm.zzd(sb19.toString(), e);
                                                        }
                                                        if (file6.exists()) {
                                                            strValueOf = String.valueOf(file6.getAbsolutePath());
                                                            if (strValueOf.length() != 0) {
                                                                str2 = "Could not delete partial cache file at ".concat(strValueOf);
                                                            } else {
                                                                str2 = new String("Could not delete partial cache file at ");
                                                            }
                                                            zzatm.zzeu(str2);
                                                        }
                                                        zza(str, file6.getAbsolutePath(), str5, strConcat);
                                                        zzede.remove(r2);
                                                        return false;
                                                    }
                                                } catch (IOException | RuntimeException e17) {
                                                    e = e17;
                                                    fileOutputStream = fileOutputStream3;
                                                    r3 = r10;
                                                    r4 = r3;
                                                }
                                            } catch (IOException | RuntimeException e18) {
                                                e = e18;
                                                fileOutputStream = fileOutputStream3;
                                                r3 = strConcat2;
                                            }
                                        }
                                        r2 = strConcat2;
                                        if (e instanceof RuntimeException) {
                                            com.google.android.gms.ads.internal.zzp.zzkc().zza(e, "VideoStreamFullFileCache.preload");
                                        }
                                        fileOutputStream2.close();
                                        if (this.zzedh) {
                                            StringBuilder sb110 = new StringBuilder(String.valueOf(str).length() + 26);
                                            sb110.append("Preload aborted for URL \"");
                                            sb110.append(str);
                                            sb110.append("\"");
                                            zzatm.zzet(sb110.toString());
                                        } else {
                                            StringBuilder sb111 = new StringBuilder(String.valueOf(str).length() + 25);
                                            sb111.append("Preload failed for URL \"");
                                            sb111.append(str);
                                            sb111.append("\"");
                                            zzatm.zzd(sb111.toString(), e);
                                        }
                                        if (file6.exists()) {
                                            strValueOf = String.valueOf(file6.getAbsolutePath());
                                            if (strValueOf.length() != 0) {
                                                str2 = "Could not delete partial cache file at ".concat(strValueOf);
                                            } else {
                                                str2 = new String("Could not delete partial cache file at ");
                                            }
                                            zzatm.zzeu(str2);
                                        }
                                        zza(str, file6.getAbsolutePath(), str5, strConcat);
                                        zzede.remove(r2);
                                        return false;
                                    }
                                    throw new IOException("Too many redirects (20)");
                                } catch (IOException | RuntimeException e19) {
                                    e = e19;
                                    str5 = str3;
                                    r1 = iIntValue;
                                    strConcat = null;
                                    fileOutputStream2 = null;
                                    r2 = r1;
                                }
                                fileOutputStream2.close();
                            } catch (IOException | NullPointerException unused2) {
                            }
                            strConcat = null;
                            fileOutputStream2 = null;
                            r2 = r1;
                            if (e instanceof RuntimeException) {
                                com.google.android.gms.ads.internal.zzp.zzkc().zza(e, "VideoStreamFullFileCache.preload");
                            }
                            if (this.zzedh) {
                                StringBuilder sb112 = new StringBuilder(String.valueOf(str).length() + 26);
                                sb112.append("Preload aborted for URL \"");
                                sb112.append(str);
                                sb112.append("\"");
                                zzatm.zzet(sb112.toString());
                            } else {
                                StringBuilder sb113 = new StringBuilder(String.valueOf(str).length() + 25);
                                sb113.append("Preload failed for URL \"");
                                sb113.append(str);
                                sb113.append("\"");
                                zzatm.zzd(sb113.toString(), e);
                            }
                            if (file6.exists()) {
                                strValueOf = String.valueOf(file6.getAbsolutePath());
                                if (strValueOf.length() != 0) {
                                    str2 = "Could not delete partial cache file at ".concat(strValueOf);
                                } else {
                                    str2 = new String("Could not delete partial cache file at ");
                                }
                                zzatm.zzeu(str2);
                            }
                            zza(str, file6.getAbsolutePath(), str5, strConcat);
                            zzede.remove(r2);
                            return false;
                        }
                    } catch (IOException | RuntimeException e20) {
                        e = e20;
                        r1 = strConcat2;
                    }
                }
            }
        } while (zDelete);
        zzatm.zzeu("Unable to expire stream cache");
        zza(str, null, "expireFailed", null);
        return false;
    }

    @Override // com.google.android.gms.internal.ads.zzbah
    public final void abort() {
        this.zzedh = true;
    }

    private final File zzc(File file) {
        return new File(this.zzedg, String.valueOf(file.getName()).concat(".done"));
    }
}
