package com.facebook.ads.redexgen.X;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

/* JADX INFO: loaded from: assets/audience_network.dex */
public interface LF {
    void EG(OutputStream outputStream, byte[] bArr) throws IOException;

    HttpURLConnection TF(String str) throws IOException;

    InputStream UF(HttpURLConnection httpURLConnection) throws IOException;

    OutputStream VF(HttpURLConnection httpURLConnection) throws IOException;

    void ZF(HttpURLConnection httpURLConnection, LR lr, String str) throws IOException;

    byte[] hF(InputStream inputStream) throws IOException;

    boolean zD(LT lt);
}
