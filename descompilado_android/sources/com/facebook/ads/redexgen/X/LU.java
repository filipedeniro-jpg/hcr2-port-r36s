package com.facebook.ads.redexgen.X;

import android.support.annotation.Nullable;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class LU {

    @Nullable
    private byte[] B;

    @Nullable
    private Map<String, List<String>> C;
    private int D;

    @Nullable
    private String E;

    public LU(HttpURLConnection urlConnection, byte[] body) {
        try {
            this.D = urlConnection.getResponseCode();
            this.E = urlConnection.getURL().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.C = urlConnection.getHeaderFields();
        this.B = body;
    }

    @Nullable
    public final byte[] A() {
        return this.B;
    }

    @Nullable
    public final String B() {
        if (this.B != null) {
            return new String(this.B);
        }
        return null;
    }

    @Nullable
    public final Map<String, List<String>> C() {
        return this.C;
    }

    public final int D() {
        return this.D;
    }

    @Nullable
    public final String E() {
        return this.E;
    }
}
