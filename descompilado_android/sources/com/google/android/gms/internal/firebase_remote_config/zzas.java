package com.google.android.gms.internal.firebase_remote_config;

import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.Arrays;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: loaded from: classes2.dex */
public final class zzas extends zzag {
    private static final String[] zzca;
    private final HostnameVerifier hostnameVerifier;
    private final zzap zzcw;
    private final SSLSocketFactory zzcx;

    public zzas() {
        this(null, null, null);
    }

    private zzas(zzap zzapVar, SSLSocketFactory sSLSocketFactory, HostnameVerifier hostnameVerifier) {
        zzao zzaoVar;
        if (System.getProperty("com.google.api.client.should_use_proxy") != null) {
            zzaoVar = new zzao(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(System.getProperty("https.proxyHost"), Integer.parseInt(System.getProperty("https.proxyPort")))));
        } else {
            zzaoVar = new zzao();
        }
        this.zzcw = zzaoVar;
        this.zzcx = null;
        this.hostnameVerifier = null;
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzag
    public final boolean zzz(String str) {
        return Arrays.binarySearch(zzca, str) >= 0;
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzag
    protected final /* synthetic */ zzaj zzc(String str, String str2) throws IOException {
        Object[] objArr = {str};
        if (!zzz(str)) {
            throw new IllegalArgumentException(zzdy.zza("HTTP method %s not supported", objArr));
        }
        HttpURLConnection httpURLConnectionZza = this.zzcw.zza(new URL(str2));
        httpURLConnectionZza.setRequestMethod(str);
        boolean z = httpURLConnectionZza instanceof HttpsURLConnection;
        return new zzar(httpURLConnectionZza);
    }

    static {
        String[] strArr = {HttpRequest.METHOD_DELETE, HttpRequest.METHOD_GET, HttpRequest.METHOD_HEAD, HttpRequest.METHOD_OPTIONS, HttpRequest.METHOD_POST, HttpRequest.METHOD_PUT, HttpRequest.METHOD_TRACE};
        zzca = strArr;
        Arrays.sort(strArr);
    }
}
