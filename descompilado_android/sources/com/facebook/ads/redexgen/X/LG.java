package com.facebook.ads.redexgen.X;

import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* JADX INFO: loaded from: assets/audience_network.dex */
public abstract class LG implements LF {
    private final LM B;

    public LG() {
        this(new LN());
    }

    private LG(LM logger) {
        this.B = logger;
    }

    @Override // com.facebook.ads.redexgen.X.LF
    public final void EG(OutputStream out, byte[] content) throws IOException {
        out.write(content);
    }

    @Override // com.facebook.ads.redexgen.X.LF
    public final HttpURLConnection TF(String urlString) throws IOException {
        return (HttpURLConnection) new URL(urlString).openConnection();
    }

    @Override // com.facebook.ads.redexgen.X.LF
    public final InputStream UF(HttpURLConnection urlConnection) throws IOException {
        return urlConnection.getInputStream();
    }

    @Override // com.facebook.ads.redexgen.X.LF
    public final OutputStream VF(HttpURLConnection urlConnection) throws IOException {
        return urlConnection.getOutputStream();
    }

    @Override // com.facebook.ads.redexgen.X.LF
    public final void ZF(HttpURLConnection urlConnection, LR httpMethod, String contentType) throws IOException {
        urlConnection.setRequestMethod(httpMethod.C());
        urlConnection.setDoOutput(httpMethod.B());
        urlConnection.setDoInput(httpMethod.A());
        if (contentType != null) {
            urlConnection.setRequestProperty(HttpRequest.HEADER_CONTENT_TYPE, contentType);
        }
        urlConnection.setRequestProperty(HttpRequest.HEADER_ACCEPT_CHARSET, "UTF-8");
    }

    @Override // com.facebook.ads.redexgen.X.LF
    public final byte[] hF(InputStream in) throws IOException {
        byte[] bArr = new byte[16384];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int i = in.read(bArr);
            if (i != -1) {
                byteArrayOutputStream.write(bArr, 0, i);
            } else {
                byteArrayOutputStream.flush();
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    @Override // com.facebook.ads.redexgen.X.LF
    public final boolean zD(LT e) {
        LU luA = e.A();
        if (this.B.VC()) {
            this.B.dC("BasicRequestHandler.onError got");
            e.printStackTrace();
        }
        return luA != null && luA.D() > 0;
    }
}
