package net.nend.android.internal.c;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import java.util.Locale;
import net.nend.android.BuildConfig;
import net.nend.android.internal.utilities.h;
import net.nend.android.internal.utilities.k;
import net.nend.android.internal.utilities.m;

/* JADX INFO: compiled from: AbsNendAdRequest.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class b {
    protected final String a;
    protected final String b;
    protected final String c;
    protected final int d;
    protected final String e;
    protected final Context f;

    public abstract String a();

    public abstract String a(String str);

    public abstract String b();

    protected String c() {
        return "android";
    }

    protected String d() {
        return BuildConfig.VERSION_NAME;
    }

    public b(Context context, int i, String str) {
        this.f = (Context) m.a(context);
        this.d = m.a(i, k.ERR_INVALID_SPOT_ID.a("spot id : " + i));
        this.e = (String) m.a(str, (Object) k.ERR_INVALID_API_KEY.a("api key : " + str));
        this.a = net.nend.android.internal.utilities.f.a(this.f, h.a.ADSCHEME.a(), "https");
        this.b = net.nend.android.internal.utilities.f.a(this.f, h.a.ADAUTHORITY.a(), a());
        this.c = net.nend.android.internal.utilities.f.a(this.f, h.a.ADPATH.a(), b());
    }

    protected String e() {
        return Build.MODEL;
    }

    protected String f() {
        return Build.DEVICE;
    }

    protected String g() {
        return Locale.getDefault().toString();
    }

    protected String h() {
        return Build.VERSION.RELEASE;
    }

    public String b(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("UID is invalid. uid : " + str);
        }
        return a(str);
    }

    protected String i() {
        return net.nend.android.internal.utilities.c.a(this.f);
    }
}
