package com.applovin.mediation;

/* JADX INFO: loaded from: classes.dex */
public class MaxAdFormat {
    private final String a;
    public static final MaxAdFormat BANNER = new MaxAdFormat("BANNER");
    public static final MaxAdFormat MREC = new MaxAdFormat("MREC");
    public static final MaxAdFormat LEADER = new MaxAdFormat("LEADER");
    public static final MaxAdFormat INTERSTITIAL = new MaxAdFormat("INTER");
    public static final MaxAdFormat REWARDED = new MaxAdFormat("REWARDED");
    public static final MaxAdFormat NATIVE = new MaxAdFormat("NATIVE");

    private MaxAdFormat(String str) {
        this.a = str;
    }

    public String getLabel() {
        return this.a;
    }

    public String toString() {
        return "MaxAdFormat{label='" + this.a + "'}";
    }
}
