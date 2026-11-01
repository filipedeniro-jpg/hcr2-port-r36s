package com.google.android.gms.internal.ads;

import androidx.annotation.VisibleForTesting;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzar extends zzah {
    private final zzat zzcj;
    private final SSLSocketFactory zzck;

    public zzar() {
        this(null);
    }

    private zzar(zzat zzatVar) {
        this(null, null);
    }

    private zzar(zzat zzatVar, SSLSocketFactory sSLSocketFactory) {
        this.zzcj = null;
        this.zzck = null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:31:0x00d0 A[Catch: all -> 0x0125, TryCatch #1 {all -> 0x0125, blocks: (B:14:0x0068, B:15:0x0070, B:17:0x0076, B:18:0x0086, B:19:0x008a, B:20:0x008d, B:53:0x011f, B:54:0x0124, B:21:0x0091, B:22:0x009a, B:23:0x00a0, B:24:0x00a6, B:25:0x00ac, B:26:0x00b2, B:27:0x00bb, B:28:0x00c4, B:29:0x00c9, B:31:0x00d0, B:44:0x00ec, B:51:0x0117, B:52:0x011e), top: B:63:0x0068 }] */
    /* JADX WARN: Code duplicated, block: B:33:0x00d7  */
    /* JADX WARN: Code duplicated, block: B:42:0x00e9  */
    /* JADX WARN: Code duplicated, block: B:44:0x00ec A[Catch: all -> 0x0125, TRY_LEAVE, TryCatch #1 {all -> 0x0125, blocks: (B:14:0x0068, B:15:0x0070, B:17:0x0076, B:18:0x0086, B:19:0x008a, B:20:0x008d, B:53:0x011f, B:54:0x0124, B:21:0x0091, B:22:0x009a, B:23:0x00a0, B:24:0x00a6, B:25:0x00ac, B:26:0x00b2, B:27:0x00bb, B:28:0x00c4, B:29:0x00c9, B:31:0x00d0, B:44:0x00ec, B:51:0x0117, B:52:0x011e), top: B:63:0x0068 }] */
    /* JADX WARN: Code duplicated, block: B:51:0x0117 A[Catch: all -> 0x0125, TRY_ENTER, TryCatch #1 {all -> 0x0125, blocks: (B:14:0x0068, B:15:0x0070, B:17:0x0076, B:18:0x0086, B:19:0x008a, B:20:0x008d, B:53:0x011f, B:54:0x0124, B:21:0x0091, B:22:0x009a, B:23:0x00a0, B:24:0x00a6, B:25:0x00ac, B:26:0x00b2, B:27:0x00bb, B:28:0x00c4, B:29:0x00c9, B:31:0x00d0, B:44:0x00ec, B:51:0x0117, B:52:0x011e), top: B:63:0x0068 }] */
    /* JADX WARN: Code duplicated, block: B:61:0x00fd A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // com.google.android.gms.internal.ads.zzah
    public final zzap zza(zzq<?> zzqVar, Map<String, String> map) throws Throwable {
        String strZzg;
        int responseCode;
        boolean z;
        String url = zzqVar.getUrl();
        HashMap map2 = new HashMap();
        map2.putAll(map);
        map2.putAll(zzqVar.getHeaders());
        zzat zzatVar = this.zzcj;
        if (zzatVar != null) {
            strZzg = zzatVar.zzg(url);
            if (strZzg == null) {
                String strValueOf = String.valueOf(url);
                throw new IOException(strValueOf.length() != 0 ? "URL blocked by rewriter: ".concat(strValueOf) : new String("URL blocked by rewriter: "));
            }
        } else {
            strZzg = url;
        }
        URL url2 = new URL(strZzg);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url2.openConnection();
        httpURLConnection.setInstanceFollowRedirects(HttpURLConnection.getFollowRedirects());
        int iZzh = zzqVar.zzh();
        httpURLConnection.setConnectTimeout(iZzh);
        httpURLConnection.setReadTimeout(iZzh);
        boolean z2 = false;
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setDoInput(true);
        "https".equals(url2.getProtocol());
        try {
            for (String str : map2.keySet()) {
                httpURLConnection.setRequestProperty(str, (String) map2.get(str));
            }
            switch (zzqVar.getMethod()) {
                case -1:
                    responseCode = httpURLConnection.getResponseCode();
                    if (responseCode == -1) {
                        throw new IOException("Could not retrieve response code from HttpUrlConnection.");
                    }
                    if (zzqVar.getMethod() != 4 || ((100 <= responseCode && responseCode < 200) || responseCode == 204 || responseCode == 304)) {
                        z = false;
                    } else {
                        z = true;
                    }
                    if (!z) {
                        zzap zzapVar = new zzap(responseCode, zza(httpURLConnection.getHeaderFields()));
                        httpURLConnection.disconnect();
                        return zzapVar;
                    }
                    try {
                        return new zzap(responseCode, zza(httpURLConnection.getHeaderFields()), httpURLConnection.getContentLength(), new zzau(httpURLConnection));
                    } catch (Throwable th) {
                        th = th;
                        z2 = true;
                        if (!z2) {
                            httpURLConnection.disconnect();
                        }
                        throw th;
                    }
                case 0:
                    httpURLConnection.setRequestMethod(HttpRequest.METHOD_GET);
                    responseCode = httpURLConnection.getResponseCode();
                    if (responseCode == -1) {
                        throw new IOException("Could not retrieve response code from HttpUrlConnection.");
                    }
                    if (zzqVar.getMethod() != 4) {
                        z = false;
                    } else {
                        z = false;
                    }
                    if (!z) {
                        zzap zzapVar2 = new zzap(responseCode, zza(httpURLConnection.getHeaderFields()));
                        httpURLConnection.disconnect();
                        return zzapVar2;
                    }
                    return new zzap(responseCode, zza(httpURLConnection.getHeaderFields()), httpURLConnection.getContentLength(), new zzau(httpURLConnection));
                case 1:
                    httpURLConnection.setRequestMethod(HttpRequest.METHOD_POST);
                    zza(httpURLConnection, zzqVar);
                    responseCode = httpURLConnection.getResponseCode();
                    if (responseCode == -1) {
                        throw new IOException("Could not retrieve response code from HttpUrlConnection.");
                    }
                    if (zzqVar.getMethod() != 4) {
                        z = false;
                    } else {
                        z = false;
                    }
                    if (!z) {
                        zzap zzapVar3 = new zzap(responseCode, zza(httpURLConnection.getHeaderFields()));
                        httpURLConnection.disconnect();
                        return zzapVar3;
                    }
                    return new zzap(responseCode, zza(httpURLConnection.getHeaderFields()), httpURLConnection.getContentLength(), new zzau(httpURLConnection));
                case 2:
                    httpURLConnection.setRequestMethod(HttpRequest.METHOD_PUT);
                    zza(httpURLConnection, zzqVar);
                    responseCode = httpURLConnection.getResponseCode();
                    if (responseCode == -1) {
                        throw new IOException("Could not retrieve response code from HttpUrlConnection.");
                    }
                    if (zzqVar.getMethod() != 4) {
                        z = false;
                    } else {
                        z = false;
                    }
                    if (!z) {
                        zzap zzapVar4 = new zzap(responseCode, zza(httpURLConnection.getHeaderFields()));
                        httpURLConnection.disconnect();
                        return zzapVar4;
                    }
                    return new zzap(responseCode, zza(httpURLConnection.getHeaderFields()), httpURLConnection.getContentLength(), new zzau(httpURLConnection));
                case 3:
                    httpURLConnection.setRequestMethod(HttpRequest.METHOD_DELETE);
                    responseCode = httpURLConnection.getResponseCode();
                    if (responseCode == -1) {
                        throw new IOException("Could not retrieve response code from HttpUrlConnection.");
                    }
                    if (zzqVar.getMethod() != 4) {
                        z = false;
                    } else {
                        z = false;
                    }
                    if (!z) {
                        zzap zzapVar5 = new zzap(responseCode, zza(httpURLConnection.getHeaderFields()));
                        httpURLConnection.disconnect();
                        return zzapVar5;
                    }
                    return new zzap(responseCode, zza(httpURLConnection.getHeaderFields()), httpURLConnection.getContentLength(), new zzau(httpURLConnection));
                case 4:
                    httpURLConnection.setRequestMethod(HttpRequest.METHOD_HEAD);
                    responseCode = httpURLConnection.getResponseCode();
                    if (responseCode == -1) {
                        throw new IOException("Could not retrieve response code from HttpUrlConnection.");
                    }
                    if (zzqVar.getMethod() != 4) {
                        z = false;
                    } else {
                        z = false;
                    }
                    if (!z) {
                        zzap zzapVar6 = new zzap(responseCode, zza(httpURLConnection.getHeaderFields()));
                        httpURLConnection.disconnect();
                        return zzapVar6;
                    }
                    return new zzap(responseCode, zza(httpURLConnection.getHeaderFields()), httpURLConnection.getContentLength(), new zzau(httpURLConnection));
                case 5:
                    httpURLConnection.setRequestMethod(HttpRequest.METHOD_OPTIONS);
                    responseCode = httpURLConnection.getResponseCode();
                    if (responseCode == -1) {
                        throw new IOException("Could not retrieve response code from HttpUrlConnection.");
                    }
                    if (zzqVar.getMethod() != 4) {
                        z = false;
                    } else {
                        z = false;
                    }
                    if (!z) {
                        zzap zzapVar7 = new zzap(responseCode, zza(httpURLConnection.getHeaderFields()));
                        httpURLConnection.disconnect();
                        return zzapVar7;
                    }
                    return new zzap(responseCode, zza(httpURLConnection.getHeaderFields()), httpURLConnection.getContentLength(), new zzau(httpURLConnection));
                case 6:
                    httpURLConnection.setRequestMethod(HttpRequest.METHOD_TRACE);
                    responseCode = httpURLConnection.getResponseCode();
                    if (responseCode == -1) {
                        throw new IOException("Could not retrieve response code from HttpUrlConnection.");
                    }
                    if (zzqVar.getMethod() != 4) {
                        z = false;
                    } else {
                        z = false;
                    }
                    if (!z) {
                        zzap zzapVar8 = new zzap(responseCode, zza(httpURLConnection.getHeaderFields()));
                        httpURLConnection.disconnect();
                        return zzapVar8;
                    }
                    return new zzap(responseCode, zza(httpURLConnection.getHeaderFields()), httpURLConnection.getContentLength(), new zzau(httpURLConnection));
                case 7:
                    httpURLConnection.setRequestMethod("PATCH");
                    zza(httpURLConnection, zzqVar);
                    responseCode = httpURLConnection.getResponseCode();
                    if (responseCode == -1) {
                        throw new IOException("Could not retrieve response code from HttpUrlConnection.");
                    }
                    if (zzqVar.getMethod() != 4) {
                        z = false;
                    } else {
                        z = false;
                    }
                    if (!z) {
                        zzap zzapVar9 = new zzap(responseCode, zza(httpURLConnection.getHeaderFields()));
                        httpURLConnection.disconnect();
                        return zzapVar9;
                    }
                    return new zzap(responseCode, zza(httpURLConnection.getHeaderFields()), httpURLConnection.getContentLength(), new zzau(httpURLConnection));
                default:
                    throw new IllegalStateException("Unknown method type.");
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    @VisibleForTesting
    private static List<zzk> zza(Map<String, List<String>> map) {
        ArrayList arrayList = new ArrayList(map.size());
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            if (entry.getKey() != null) {
                Iterator<String> it = entry.getValue().iterator();
                while (it.hasNext()) {
                    arrayList.add(new zzk(entry.getKey(), it.next()));
                }
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static InputStream zza(HttpURLConnection httpURLConnection) {
        try {
            return httpURLConnection.getInputStream();
        } catch (IOException unused) {
            return httpURLConnection.getErrorStream();
        }
    }

    private static void zza(HttpURLConnection httpURLConnection, zzq<?> zzqVar) throws zzb, IOException {
        byte[] bArrZzf = zzqVar.zzf();
        if (bArrZzf != null) {
            httpURLConnection.setDoOutput(true);
            if (!httpURLConnection.getRequestProperties().containsKey(HttpRequest.HEADER_CONTENT_TYPE)) {
                httpURLConnection.setRequestProperty(HttpRequest.HEADER_CONTENT_TYPE, "UTF-8".length() != 0 ? "application/x-www-form-urlencoded; charset=".concat("UTF-8") : new String("application/x-www-form-urlencoded; charset="));
            }
            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            dataOutputStream.write(bArrZzf);
            dataOutputStream.close();
        }
    }
}
