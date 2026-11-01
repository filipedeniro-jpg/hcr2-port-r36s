package net.nend.android.internal.c.a;

import android.content.Context;
import android.net.Uri;
import android.util.DisplayMetrics;
import com.my.target.az;

/* JADX INFO: compiled from: NendAdRequest.java */
/* JADX INFO: loaded from: classes2.dex */
public class a extends net.nend.android.internal.c.b {
    @Override // net.nend.android.internal.c.b
    public String a() {
        return "ad1.nend.net";
    }

    @Override // net.nend.android.internal.c.b
    public String b() {
        return "na.php";
    }

    public a(Context context, int i, String str) {
        super(context, i, str);
    }

    @Override // net.nend.android.internal.c.b
    public String a(String str) {
        DisplayMetrics displayMetrics = this.f.getResources().getDisplayMetrics();
        return new Uri.Builder().scheme(this.a).authority(this.b).path(this.c).appendQueryParameter("apikey", this.e).appendQueryParameter("spot", String.valueOf(this.d)).appendQueryParameter("uid", str).appendQueryParameter("os", c()).appendQueryParameter("version", h()).appendQueryParameter("model", e()).appendQueryParameter("device", f()).appendQueryParameter("localize", g()).appendQueryParameter(az.b.W, d()).appendQueryParameter("gaid", i()).appendQueryParameter("wdpi", String.valueOf(displayMetrics.xdpi)).appendQueryParameter("hdpi", String.valueOf(displayMetrics.ydpi)).appendQueryParameter("device_width", String.valueOf(displayMetrics.widthPixels)).appendQueryParameter("device_height", String.valueOf(displayMetrics.heightPixels)).toString();
    }
}
