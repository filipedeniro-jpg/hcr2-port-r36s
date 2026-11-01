package net.nend.android.internal.c.f;

import android.net.Uri;
import com.ironsource.sdk.constants.Constants;
import com.my.target.aq;
import com.my.target.m;

/* JADX INFO: compiled from: EndpointFlavors.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b {
    static final String a = a("/interstitial");
    static final String b = a("/rewarded");
    static final String c = a("/native");
    static final String d = b("start");
    static final String e = b("start/native");
    static final String f = b(m.at);
    static final String g = b(Constants.ParametersKeys.VIEW);
    static final String h = b("close_endcard");
    static final String i = b(aq.a.dF);

    private static String a(String str) {
        return new Uri.Builder().scheme("https").authority("vdapp.nend.net").path("v1/video/ad" + str).toString();
    }

    private static String b(String str) {
        return new Uri.Builder().scheme("https").authority("vdapp.nend.net").path("v1/video/event/" + str).toString();
    }
}
