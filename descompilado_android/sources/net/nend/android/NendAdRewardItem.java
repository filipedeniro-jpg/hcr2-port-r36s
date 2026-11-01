package net.nend.android;

/* JADX INFO: loaded from: classes2.dex */
public class NendAdRewardItem {
    private final String a;
    private final int b;

    NendAdRewardItem(String str, int i) {
        this.a = str;
        this.b = i;
    }

    public String getCurrencyName() {
        return this.a;
    }

    public int getCurrencyAmount() {
        return this.b;
    }
}
