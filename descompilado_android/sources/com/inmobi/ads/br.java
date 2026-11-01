package com.inmobi.ads;

/* JADX INFO: compiled from: Utils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class br {
    public static String a(String str) {
        if (str.equalsIgnoreCase("window.mraidview")) {
            return "mraidview";
        }
        if (str.equalsIgnoreCase("window.imraidview")) {
            return "imraidview";
        }
        if (str.equalsIgnoreCase("window.imaiview")) {
            return "imaiview";
        }
        return "NA_ERROR_" + str;
    }
}
