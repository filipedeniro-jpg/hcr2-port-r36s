package com.facebook.ads.redexgen.X;

import android.text.TextUtils;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class M8 implements M2 {
    private String B(String url) {
        int iLastIndexOf = url.lastIndexOf(46);
        return (iLastIndexOf == -1 || iLastIndexOf <= url.lastIndexOf(47) || (iLastIndexOf + 2) + 4 <= url.length()) ? "" : url.substring(iLastIndexOf + 1, url.length());
    }

    @Override // com.facebook.ads.redexgen.X.M2
    public final String sB(String url) {
        String strB = B(url);
        String strD = C0687Ly.D(url);
        return TextUtils.isEmpty(strB) ? strD : strD + "." + strB;
    }
}
