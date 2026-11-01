package com.facebook.ads.redexgen.X;

import java.util.StringTokenizer;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Kz, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0662Kz {
    private C0662Kz() {
    }

    public static String B(String body) {
        if (body != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(body, " ", true);
            int i = 0;
            if (body.length() > 90) {
                if (body.length() > 93 || !body.endsWith("...")) {
                    while (stringTokenizer.hasMoreTokens()) {
                        int length = stringTokenizer.nextToken().length();
                        if (i + length < 90) {
                            i += length;
                        }
                    }
                    if (i == 0) {
                        return body.substring(0, 90) + "...";
                    }
                    return body.substring(0, i) + "...";
                }
                return body;
            }
            return body;
        }
        return body;
    }
}
