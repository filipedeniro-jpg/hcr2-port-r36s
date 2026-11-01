package com.facebook.ads.redexgen.X;

import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Lh, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0670Lh {
    private static final Pattern E = Pattern.compile("[R,r]ange:[ ]?bytes=(\\d*)-");
    private static final Pattern F = Pattern.compile("GET /(.*) HTTP");
    public final boolean B;
    public final long C;
    public final String D;

    private C0670Lh(String request) {
        C0684Lv.C(request);
        long jC = C(request);
        this.C = Math.max(0L, jC);
        this.B = jC >= 0;
        this.D = D(request);
    }

    public static C0670Lh B(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String line = reader.readLine();
            if (!TextUtils.isEmpty(line)) {
                StringBuilder stringRequest = sb.append(line);
                stringRequest.append('\n');
            } else {
                return new C0670Lh(sb.toString());
            }
        }
    }

    private long C(String request) {
        Matcher matcher = E.matcher(request);
        if (matcher.find()) {
            return Long.parseLong(matcher.group(1));
        }
        return -1L;
    }

    private String D(String request) {
        Matcher matcher = F.matcher(request);
        if (matcher.find()) {
            return matcher.group(1);
        }
        throw new IllegalArgumentException("Invalid request `" + request + "`: url not found!");
    }

    public final String toString() {
        return "GetRequest{rangeOffset=" + this.C + ", partial=" + this.B + ", uri='" + this.D + "'}";
    }
}
