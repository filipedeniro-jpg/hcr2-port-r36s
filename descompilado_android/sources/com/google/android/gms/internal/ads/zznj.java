package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.util.Log;
import com.ironsource.sdk.constants.Constants;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.NoRouteToHostException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zznj implements zznc {
    private static final Pattern zzbet = Pattern.compile("^bytes (\\d+)-(\\d+)/(\\d+)$");
    private static final AtomicReference<byte[]> zzbeu = new AtomicReference<>();
    private zznh zzbes;
    private final int zzbew;
    private final int zzbex;
    private final String zzbey;
    private final zznq<? super zznj> zzbfc;
    private HttpURLConnection zzbfd;
    private InputStream zzbfe;
    private boolean zzbff;
    private long zzbfg;
    private long zzbfh;
    private long zzbfi;
    private long zzcc;
    private final zzoc<String> zzbez = null;
    private final zznk zzbfb = new zznk();
    private final boolean zzbev = true;
    private final zznk zzbfa = null;

    public zznj(String str, zzoc<String> zzocVar, zznq<? super zznj> zznqVar, int i, int i2, boolean z, zznk zznkVar) {
        this.zzbey = zznt.checkNotEmpty(str);
        this.zzbfc = zznqVar;
        this.zzbew = i;
        this.zzbex = i2;
    }

    @Override // com.google.android.gms.internal.ads.zznc
    public final Uri getUri() {
        HttpURLConnection httpURLConnection = this.zzbfd;
        if (httpURLConnection == null) {
            return null;
        }
        return Uri.parse(httpURLConnection.getURL().toString());
    }

    public final Map<String, List<String>> getResponseHeaders() {
        HttpURLConnection httpURLConnection = this.zzbfd;
        if (httpURLConnection == null) {
            return null;
        }
        return httpURLConnection.getHeaderFields();
    }

    @Override // com.google.android.gms.internal.ads.zznc
    public final long zza(zznh zznhVar) throws zzni {
        HttpURLConnection httpURLConnectionZza;
        HttpURLConnection httpURLConnectionZza2;
        this.zzbes = zznhVar;
        long j = 0;
        this.zzcc = 0L;
        this.zzbfi = 0L;
        try {
            URL url = new URL(zznhVar.uri.toString());
            byte[] bArr = zznhVar.zzbeq;
            long j2 = zznhVar.zzams;
            long j3 = zznhVar.zzcb;
            boolean zZzba = zznhVar.zzba(1);
            if (this.zzbev) {
                URL url2 = url;
                byte[] bArr2 = bArr;
                int i = 0;
                while (true) {
                    int i2 = i + 1;
                    if (i <= 20) {
                        URL url3 = url2;
                        long j4 = j3;
                        long j5 = j2;
                        httpURLConnectionZza = zza(url2, bArr2, j2, j3, zZzba, false);
                        int responseCode = httpURLConnectionZza.getResponseCode();
                        if (responseCode != 300 && responseCode != 301 && responseCode != 302 && responseCode != 303 && (bArr2 != null || (responseCode != 307 && responseCode != 308))) {
                            break;
                        }
                        bArr2 = null;
                        String headerField = httpURLConnectionZza.getHeaderField("Location");
                        httpURLConnectionZza.disconnect();
                        if (headerField == null) {
                            throw new ProtocolException("Null location redirect");
                        }
                        url2 = new URL(url3, headerField);
                        String protocol = url2.getProtocol();
                        if (!"https".equals(protocol) && !"http".equals(protocol)) {
                            String strValueOf = String.valueOf(protocol);
                            throw new ProtocolException(strValueOf.length() != 0 ? "Unsupported protocol redirect: ".concat(strValueOf) : new String("Unsupported protocol redirect: "));
                        }
                        i = i2;
                        j3 = j4;
                        j2 = j5;
                    } else {
                        StringBuilder sb = new StringBuilder(31);
                        sb.append("Too many redirects: ");
                        sb.append(i2);
                        throw new NoRouteToHostException(sb.toString());
                    }
                }
                httpURLConnectionZza2 = httpURLConnectionZza;
            } else {
                httpURLConnectionZza2 = zza(url, bArr, j2, j3, zZzba, true);
            }
            this.zzbfd = httpURLConnectionZza2;
            try {
                int responseCode2 = this.zzbfd.getResponseCode();
                if (responseCode2 < 200 || responseCode2 > 299) {
                    Map<String, List<String>> headerFields = this.zzbfd.getHeaderFields();
                    zzia();
                    zznl zznlVar = new zznl(responseCode2, headerFields, zznhVar);
                    if (responseCode2 == 416) {
                        zznlVar.initCause(new zzne(0));
                        throw zznlVar;
                    }
                    throw zznlVar;
                }
                this.zzbfd.getContentType();
                if (responseCode2 == 200 && zznhVar.zzams != 0) {
                    j = zznhVar.zzams;
                }
                this.zzbfg = j;
                if (zznhVar.zzba(1)) {
                    this.zzbfh = zznhVar.zzcb;
                } else {
                    if (zznhVar.zzcb != -1) {
                        this.zzbfh = zznhVar.zzcb;
                    } else {
                        long jZzc = zzc(this.zzbfd);
                        this.zzbfh = jZzc != -1 ? jZzc - this.zzbfg : -1L;
                    }
                }
                try {
                    this.zzbfe = this.zzbfd.getInputStream();
                    this.zzbff = true;
                    zznq<? super zznj> zznqVar = this.zzbfc;
                    if (zznqVar != null) {
                        zznqVar.zza(this, zznhVar);
                    }
                    return this.zzbfh;
                } catch (IOException e) {
                    zzia();
                    throw new zzni(e, zznhVar, 1);
                }
            } catch (IOException e2) {
                zzia();
                String strValueOf2 = String.valueOf(zznhVar.uri.toString());
                throw new zzni(strValueOf2.length() != 0 ? "Unable to connect to ".concat(strValueOf2) : new String("Unable to connect to "), e2, zznhVar, 1);
            }
        } catch (IOException e3) {
            String strValueOf3 = String.valueOf(zznhVar.uri.toString());
            throw new zzni(strValueOf3.length() != 0 ? "Unable to connect to ".concat(strValueOf3) : new String("Unable to connect to "), e3, zznhVar, 1);
        }
    }

    @Override // com.google.android.gms.internal.ads.zznc
    public final int read(byte[] bArr, int i, int i2) throws zzni {
        try {
            if (this.zzbfi != this.zzbfg) {
                byte[] andSet = zzbeu.getAndSet(null);
                if (andSet == null) {
                    andSet = new byte[4096];
                }
                while (this.zzbfi != this.zzbfg) {
                    int i3 = this.zzbfe.read(andSet, 0, (int) Math.min(this.zzbfg - this.zzbfi, andSet.length));
                    if (Thread.interrupted()) {
                        throw new InterruptedIOException();
                    }
                    if (i3 == -1) {
                        throw new EOFException();
                    }
                    this.zzbfi += (long) i3;
                    if (this.zzbfc != null) {
                        this.zzbfc.zzc(this, i3);
                    }
                }
                zzbeu.set(andSet);
            }
            if (i2 == 0) {
                return 0;
            }
            if (this.zzbfh != -1) {
                long j = this.zzbfh - this.zzcc;
                if (j == 0) {
                    return -1;
                }
                i2 = (int) Math.min(i2, j);
            }
            int i4 = this.zzbfe.read(bArr, i, i2);
            if (i4 == -1) {
                if (this.zzbfh == -1) {
                    return -1;
                }
                throw new EOFException();
            }
            this.zzcc += (long) i4;
            if (this.zzbfc != null) {
                this.zzbfc.zzc(this, i4);
            }
            return i4;
        } catch (IOException e) {
            throw new zzni(e, this.zzbes, 2);
        }
    }

    /* JADX WARN: Code duplicated, block: B:21:0x003a A[Catch: Exception -> 0x006b, all -> 0x008e, TryCatch #0 {Exception -> 0x006b, blocks: (B:14:0x0024, B:16:0x002c, B:21:0x003a, B:23:0x004a, B:25:0x0052), top: B:45:0x0024 }] */
    /* JADX WARN: Code duplicated, block: B:25:0x0052 A[Catch: Exception -> 0x006b, all -> 0x008e, TRY_LEAVE, TryCatch #0 {Exception -> 0x006b, blocks: (B:14:0x0024, B:16:0x002c, B:21:0x003a, B:23:0x004a, B:25:0x0052), top: B:45:0x0024 }] */
    @Override // com.google.android.gms.internal.ads.zznc
    public final void close() throws zzni {
        String name;
        try {
            if (this.zzbfe != null) {
                HttpURLConnection httpURLConnection = this.zzbfd;
                long j = this.zzbfh == -1 ? this.zzbfh : this.zzbfh - this.zzcc;
                if (zzoh.SDK_INT == 19 || zzoh.SDK_INT == 20) {
                    try {
                        InputStream inputStream = httpURLConnection.getInputStream();
                        if (j == -1) {
                            if (inputStream.read() != -1) {
                                name = inputStream.getClass().getName();
                                if (!name.equals("com.android.okhttp.internal.http.HttpTransport$ChunkedInputStream") || name.equals("com.android.okhttp.internal.http.HttpTransport$FixedLengthInputStream")) {
                                    Method declaredMethod = inputStream.getClass().getSuperclass().getDeclaredMethod("unexpectedEndOfInput", new Class[0]);
                                    declaredMethod.setAccessible(true);
                                    declaredMethod.invoke(inputStream, new Object[0]);
                                }
                            }
                        } else if (j > PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH) {
                            name = inputStream.getClass().getName();
                            if (!name.equals("com.android.okhttp.internal.http.HttpTransport$ChunkedInputStream")) {
                                Method declaredMethod2 = inputStream.getClass().getSuperclass().getDeclaredMethod("unexpectedEndOfInput", new Class[0]);
                                declaredMethod2.setAccessible(true);
                                declaredMethod2.invoke(inputStream, new Object[0]);
                            } else {
                                Method declaredMethod3 = inputStream.getClass().getSuperclass().getDeclaredMethod("unexpectedEndOfInput", new Class[0]);
                                declaredMethod3.setAccessible(true);
                                declaredMethod3.invoke(inputStream, new Object[0]);
                            }
                        }
                    } catch (Exception unused) {
                    }
                }
                try {
                    this.zzbfe.close();
                } catch (IOException e) {
                    throw new zzni(e, this.zzbes, 3);
                }
            }
            this.zzbfe = null;
            zzia();
            if (this.zzbff) {
                this.zzbff = false;
                zznq<? super zznj> zznqVar = this.zzbfc;
                if (zznqVar != null) {
                    zznqVar.zze(this);
                }
            }
        } catch (Throwable th) {
            this.zzbfe = null;
            zzia();
            if (this.zzbff) {
                this.zzbff = false;
                zznq<? super zznj> zznqVar2 = this.zzbfc;
                if (zznqVar2 != null) {
                    zznqVar2.zze(this);
                }
            }
            throw th;
        }
    }

    /* JADX WARN: Code duplicated, block: B:26:0x00bf  */
    private final HttpURLConnection zza(URL url, byte[] bArr, long j, long j2, boolean z, boolean z2) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setConnectTimeout(this.zzbew);
        httpURLConnection.setReadTimeout(this.zzbex);
        for (Map.Entry<String, String> entry : this.zzbfb.zzib().entrySet()) {
            httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
        }
        if (j != 0 || j2 != -1) {
            StringBuilder sb = new StringBuilder(27);
            sb.append("bytes=");
            sb.append(j);
            sb.append("-");
            String string = sb.toString();
            if (j2 != -1) {
                String strValueOf = String.valueOf(string);
                StringBuilder sb2 = new StringBuilder(String.valueOf(strValueOf).length() + 20);
                sb2.append(strValueOf);
                sb2.append((j + j2) - 1);
                string = sb2.toString();
            }
            httpURLConnection.setRequestProperty("Range", string);
        }
        httpURLConnection.setRequestProperty("User-Agent", this.zzbey);
        if (!z) {
            httpURLConnection.setRequestProperty(HttpRequest.HEADER_ACCEPT_ENCODING, "identity");
        }
        httpURLConnection.setInstanceFollowRedirects(z2);
        httpURLConnection.setDoOutput(bArr != null);
        if (bArr != null) {
            httpURLConnection.setRequestMethod(HttpRequest.METHOD_POST);
            if (bArr.length != 0) {
                httpURLConnection.setFixedLengthStreamingMode(bArr.length);
                httpURLConnection.connect();
                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(bArr);
                outputStream.close();
            } else {
                httpURLConnection.connect();
            }
        } else {
            httpURLConnection.connect();
        }
        return httpURLConnection;
    }

    private static long zzc(HttpURLConnection httpURLConnection) {
        long j;
        String headerField = httpURLConnection.getHeaderField(HttpRequest.HEADER_CONTENT_LENGTH);
        if (TextUtils.isEmpty(headerField)) {
            j = -1;
        } else {
            try {
                j = Long.parseLong(headerField);
            } catch (NumberFormatException unused) {
                StringBuilder sb = new StringBuilder(String.valueOf(headerField).length() + 28);
                sb.append("Unexpected Content-Length [");
                sb.append(headerField);
                sb.append(Constants.RequestParameters.RIGHT_BRACKETS);
                Log.e("DefaultHttpDataSource", sb.toString());
                j = -1;
            }
        }
        String headerField2 = httpURLConnection.getHeaderField("Content-Range");
        if (TextUtils.isEmpty(headerField2)) {
            return j;
        }
        Matcher matcher = zzbet.matcher(headerField2);
        if (!matcher.find()) {
            return j;
        }
        try {
            long j2 = (Long.parseLong(matcher.group(2)) - Long.parseLong(matcher.group(1))) + 1;
            if (j < 0) {
                return j2;
            }
            if (j == j2) {
                return j;
            }
            StringBuilder sb2 = new StringBuilder(String.valueOf(headerField).length() + 26 + String.valueOf(headerField2).length());
            sb2.append("Inconsistent headers [");
            sb2.append(headerField);
            sb2.append("] [");
            sb2.append(headerField2);
            sb2.append(Constants.RequestParameters.RIGHT_BRACKETS);
            Log.w("DefaultHttpDataSource", sb2.toString());
            return Math.max(j, j2);
        } catch (NumberFormatException unused2) {
            StringBuilder sb3 = new StringBuilder(String.valueOf(headerField2).length() + 27);
            sb3.append("Unexpected Content-Range [");
            sb3.append(headerField2);
            sb3.append(Constants.RequestParameters.RIGHT_BRACKETS);
            Log.e("DefaultHttpDataSource", sb3.toString());
            return j;
        }
    }

    private final void zzia() {
        HttpURLConnection httpURLConnection = this.zzbfd;
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
            } catch (Exception e) {
                Log.e("DefaultHttpDataSource", "Unexpected error while disconnecting", e);
            }
            this.zzbfd = null;
        }
    }
}
