package net.nend.android.internal.c.d;

import android.content.Context;
import android.net.Uri;
import com.my.target.az;

/* JADX INFO: compiled from: NendAdInterstitialRequest.java */
/* JADX INFO: loaded from: classes2.dex */
public class a extends net.nend.android.internal.c.b {
    @Override // net.nend.android.internal.c.b
    public String a() {
        return "lois.nend.net";
    }

    @Override // net.nend.android.internal.c.b
    public String b() {
        return "nsa.php";
    }

    public a(Context context, int i, String str) {
        super(context, i, str);
    }

    @Override // net.nend.android.internal.c.b
    public String a(String str) {
        return new Uri.Builder().scheme(this.a).authority(this.b).path(this.c).appendQueryParameter("apikey", this.e).appendQueryParameter("spot", String.valueOf(this.d)).appendQueryParameter("uid", str).appendQueryParameter("os", c()).appendQueryParameter("version", h()).appendQueryParameter("model", e()).appendQueryParameter("device", f()).appendQueryParameter("localize", g()).appendQueryParameter(az.b.W, d()).appendQueryParameter("gaid", i()).toString();
    }
}
