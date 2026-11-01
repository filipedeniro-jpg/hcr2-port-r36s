package net.nend.android.internal.c.c;

import android.content.Context;
import android.net.Uri;
import com.my.target.az;

/* JADX INFO: compiled from: NendAdIconRequest.java */
/* JADX INFO: loaded from: classes2.dex */
public class a extends net.nend.android.internal.c.b {
    private int g;

    @Override // net.nend.android.internal.c.b
    public String a() {
        return "ad3.nend.net";
    }

    @Override // net.nend.android.internal.c.b
    public String b() {
        return "nia.php";
    }

    public a(Context context, int i, String str) {
        super(context, i, str);
    }

    @Override // net.nend.android.internal.c.b
    public String a(String str) {
        return new Uri.Builder().scheme(this.a).authority(this.b).path(this.c).appendQueryParameter("apikey", this.e).appendQueryParameter("spot", String.valueOf(this.d)).appendQueryParameter("uid", str).appendQueryParameter("os", c()).appendQueryParameter("version", h()).appendQueryParameter("model", e()).appendQueryParameter("device", f()).appendQueryParameter("localize", g()).appendQueryParameter(az.b.W, d()).appendQueryParameter("ad_num", String.valueOf(j())).appendQueryParameter("gaid", i()).toString();
    }

    int j() {
        return this.g;
    }

    public void a(int i) {
        this.g = i;
    }
}
