package com.inmobi.ads;

import androidx.annotation.NonNull;

/* JADX INFO: compiled from: NativeWebViewAsset.java */
/* JADX INFO: loaded from: classes2.dex */
public final class bf extends ak {
    boolean A;
    boolean B;
    String z;

    bf(String str, String str2, al alVar, String str3, boolean z) {
        super(str, str2, "WEBVIEW", alVar);
        this.A = false;
        this.B = false;
        this.e = str3;
        this.B = z;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:17:0x0038  */
    static String c(@NonNull String str) {
        byte b;
        switch (str.trim()) {
            case "reference_html":
                b = 4;
                break;
            case "reference_iframe":
                b = 3;
                break;
            case "url":
                b = 1;
                break;
            case "html":
                b = 2;
                break;
            default:
                b = -1;
                break;
        }
        if (b == 1) {
            return "URL";
        }
        if (b == 2) {
            return "HTML";
        }
        if (b != 3) {
            return b != 4 ? "UNKNOWN" : "REF_HTML";
        }
        return "REF_IFRAME";
    }
}
