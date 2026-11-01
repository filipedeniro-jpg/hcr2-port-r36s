package net.nend.android.internal.c.b;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.my.target.az;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: NendAdNativeRequest.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b extends net.nend.android.internal.c.b {
    private int g;
    private List<String> h;

    @Override // net.nend.android.internal.c.b
    public String a() {
        return "lons.nend.net";
    }

    @Override // net.nend.android.internal.c.b
    public String b() {
        return "nsfeed.php";
    }

    public b(Context context, int i, String str) {
        super(context, i, str);
        this.h = new ArrayList();
        this.g = i;
    }

    @Override // net.nend.android.internal.c.b
    public String a(String str) {
        DisplayMetrics displayMetrics = this.f.getResources().getDisplayMetrics();
        Uri.Builder builderAppendQueryParameter = new Uri.Builder().scheme(this.a).authority(this.b).path(this.c).appendQueryParameter("apikey", this.e).appendQueryParameter("spot", String.valueOf(this.g)).appendQueryParameter("uid", str).appendQueryParameter("os", c()).appendQueryParameter("version", h()).appendQueryParameter("model", e()).appendQueryParameter("device", f()).appendQueryParameter("localize", g()).appendQueryParameter(az.b.W, d()).appendQueryParameter("gaid", i()).appendQueryParameter("wdpi", String.valueOf(displayMetrics.xdpi)).appendQueryParameter("hdpi", String.valueOf(displayMetrics.ydpi)).appendQueryParameter("device_width", String.valueOf(displayMetrics.widthPixels)).appendQueryParameter("device_height", String.valueOf(displayMetrics.heightPixels));
        if (this.h.size() > 0) {
            builderAppendQueryParameter.appendQueryParameter("acquired_id", String.valueOf(TextUtils.join(",", this.h)));
        }
        return builderAppendQueryParameter.toString();
    }

    void c(String str) {
        this.h.add(str);
        if (5 == this.h.size()) {
            this.h.remove(0);
        }
    }

    int j() {
        return this.g;
    }
}
