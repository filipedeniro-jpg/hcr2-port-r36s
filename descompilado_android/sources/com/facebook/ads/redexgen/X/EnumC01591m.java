package com.facebook.ads.redexgen.X;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.1m, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public enum EnumC01591m {
    WEBVIEW_PRECACHE,
    PROXY_PRECACHE,
    FILE_PRECACHE;

    public static EnumC01591m B(String name) {
        try {
            return valueOf(name);
        } catch (IllegalArgumentException e) {
            return FILE_PRECACHE;
        }
    }
}
