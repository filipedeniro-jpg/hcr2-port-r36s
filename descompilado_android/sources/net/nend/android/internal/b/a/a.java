package net.nend.android.internal.b.a;

import android.content.Context;
import android.util.DisplayMetrics;
import com.tonyodev.fetch.FetchService;
import java.lang.ref.WeakReference;
import java.util.concurrent.Future;
import net.nend.android.NendAdView;
import net.nend.android.internal.utilities.c;
import net.nend.android.internal.utilities.g;
import net.nend.android.internal.utilities.j;
import net.nend.android.internal.utilities.k;
import net.nend.android.internal.utilities.l;

/* JADX INFO: compiled from: NendAd.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a implements net.nend.android.internal.b.a.a.a, g.b<net.nend.android.internal.a> {
    static final /* synthetic */ boolean a = !a.class.desiredAssertionStatus();
    private final String b;
    private final Context c;
    private final net.nend.android.internal.c.a.a d;
    private String m;
    private String n;
    private boolean o;
    private Future<net.nend.android.internal.a> q;
    private DisplayMetrics r;
    private net.nend.android.internal.a.EnumC0106a e = net.nend.android.internal.a.EnumC0106a.NONE;
    private String f = null;
    private String g = null;
    private String h = null;
    private String i = null;
    private int j = FetchService.ACTION_LOGGING;
    private int k = 50;
    private int l = 60;
    private WeakReference<net.nend.android.internal.b.a.a.b> p = null;

    public a(Context context, int i, String str, DisplayMetrics displayMetrics) {
        this.c = context;
        this.r = displayMetrics;
        this.d = new net.nend.android.internal.c.a.a(this.c, i, str);
        this.b = c.c(this.c);
    }

    @Override // net.nend.android.internal.a
    public net.nend.android.internal.a.EnumC0106a a() {
        return this.e;
    }

    @Override // net.nend.android.internal.a
    public String b() {
        return this.f;
    }

    @Override // net.nend.android.internal.a
    public String c() {
        return this.g;
    }

    @Override // net.nend.android.internal.a
    public String d() {
        return this.h;
    }

    @Override // net.nend.android.internal.a
    public String e() {
        return this.i;
    }

    @Override // net.nend.android.internal.a
    public int f() {
        return this.j;
    }

    @Override // net.nend.android.internal.a
    public int g() {
        return this.k;
    }

    @Override // net.nend.android.internal.a
    public int h() {
        return this.l;
    }

    @Override // net.nend.android.internal.a
    public String i() {
        return this.m;
    }

    @Override // net.nend.android.internal.a
    public String j() {
        return this.n;
    }

    @Override // net.nend.android.internal.a
    public boolean k() {
        return this.o;
    }

    @Override // net.nend.android.internal.b.a.a.a
    public String l() {
        return this.b;
    }

    @Override // net.nend.android.internal.b.a.a.a
    public boolean m() {
        Future<net.nend.android.internal.a> future = this.q;
        return future == null || future.isDone();
    }

    @Override // net.nend.android.internal.b.a.a.a
    public boolean n() {
        if (!m()) {
            return false;
        }
        this.q = g.a().a(new g.f(this), new g.a<net.nend.android.internal.a>() { // from class: net.nend.android.internal.b.a.a.1
            @Override // net.nend.android.internal.utilities.g.a
            public void a(net.nend.android.internal.a aVar, Exception exc) {
                a.this.a(aVar);
            }
        });
        return true;
    }

    @Override // net.nend.android.internal.b.a.a.a
    public void o() {
        Future<net.nend.android.internal.a> future = this.q;
        if (future != null) {
            future.cancel(true);
        }
    }

    public net.nend.android.internal.b.a.a.b p() {
        WeakReference<net.nend.android.internal.b.a.a.b> weakReference = this.p;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    @Override // net.nend.android.internal.b.a.a.a
    public void a(net.nend.android.internal.b.a.a.b bVar) {
        this.p = new WeakReference<>(bVar);
    }

    @Override // net.nend.android.internal.b.a.a.a
    public void q() {
        this.p = null;
    }

    @Override // net.nend.android.internal.utilities.g.b
    public String getRequestUrl() {
        return this.d.b(this.b);
    }

    @Override // net.nend.android.internal.utilities.g.b
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public net.nend.android.internal.a makeResponse(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        try {
            return new net.nend.android.internal.c.a.c(this.c).a(new String(bArr, "UTF-8"));
        } catch (Exception e) {
            if (!a) {
                throw new AssertionError();
            }
            j.a(k.ERR_HTTP_REQUEST, e);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(net.nend.android.internal.a aVar) {
        boolean z;
        net.nend.android.internal.b.a.a.b bVarP = p();
        if (aVar == null) {
            if (bVarP != null) {
                bVarP.onFailedToReceiveAd(NendAdView.NendError.FAILED_AD_REQUEST);
                return;
            }
            return;
        }
        float f = this.r.density;
        float f2 = aVar.f() * f;
        float fG = aVar.g() * f;
        if (f2 / 2.0f > this.r.widthPixels || fG / 2.0f > this.r.heightPixels || f2 > this.r.widthPixels || fG > this.r.heightPixels) {
            z = false;
            if (bVarP != null) {
                bVarP.onFailedToReceiveAd(NendAdView.NendError.AD_SIZE_TOO_LARGE);
            }
        } else {
            z = true;
        }
        int i = AnonymousClass2.a[aVar.a().ordinal()];
        if (i == 1) {
            b(aVar);
        } else if (i == 2) {
            c(aVar);
        } else if (i == 3) {
            d(aVar);
        } else {
            if (!a) {
                throw new AssertionError();
            }
            if (bVarP != null) {
                bVarP.onFailedToReceiveAd(NendAdView.NendError.INVALID_RESPONSE_TYPE);
                return;
            }
            return;
        }
        if (bVarP == null || !z) {
            return;
        }
        bVarP.onReceiveAd();
    }

    /* JADX INFO: renamed from: net.nend.android.internal.b.a.a$2, reason: invalid class name */
    /* JADX INFO: compiled from: NendAd.java */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] a = new int[net.nend.android.internal.a.EnumC0106a.values().length];

        static {
            try {
                a[net.nend.android.internal.a.EnumC0106a.ADVIEW.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[net.nend.android.internal.a.EnumC0106a.WEBVIEW.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[net.nend.android.internal.a.EnumC0106a.DYNAMICRETARGETING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private void b(net.nend.android.internal.a aVar) {
        if (!a && aVar == null) {
            throw new AssertionError();
        }
        this.e = net.nend.android.internal.a.EnumC0106a.ADVIEW;
        this.l = l.a(aVar.h());
        this.f = aVar.b();
        this.g = aVar.c();
        this.i = aVar.e();
        this.k = aVar.g();
        this.j = aVar.f();
        this.m = aVar.i();
        this.n = aVar.j();
        this.o = aVar.k();
        this.h = null;
    }

    private void c(net.nend.android.internal.a aVar) {
        if (!a && aVar == null) {
            throw new AssertionError();
        }
        this.e = net.nend.android.internal.a.EnumC0106a.WEBVIEW;
        this.h = aVar.d();
        this.f = null;
        this.g = null;
        this.i = null;
        this.m = null;
        this.n = null;
        this.o = aVar.k();
        this.k = aVar.g();
        this.j = aVar.f();
    }

    private void d(net.nend.android.internal.a aVar) {
        if (!a && aVar == null) {
            throw new AssertionError();
        }
        this.e = net.nend.android.internal.a.EnumC0106a.DYNAMICRETARGETING;
        this.h = aVar.d();
        this.f = null;
        this.g = null;
        this.i = null;
        this.m = null;
        this.n = null;
        this.o = aVar.k();
        this.k = aVar.g();
        this.j = aVar.f();
        this.l = l.a(aVar.h());
    }
}
