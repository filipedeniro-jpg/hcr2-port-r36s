package net.nend.android.internal.c.d;

import android.text.TextUtils;
import java.util.ArrayList;
import net.nend.android.NendAdInterstitial;

/* JADX INFO: compiled from: NendAdInterstitialResponse.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b {
    private final net.nend.android.internal.a.EnumC0106a a;
    private int b;
    private String c;
    private String d;
    private String e;
    private int f;
    private int g;
    private String h;
    private int i;
    private int j;
    private int k;
    private int l;
    private ArrayList<net.nend.android.internal.a> m;
    private NendAdInterstitial.NendAdInterstitialStatusCode n;

    /* synthetic */ b(a aVar, AnonymousClass1 anonymousClass1) {
        this(aVar);
    }

    /* JADX INFO: renamed from: net.nend.android.internal.c.d.b$1, reason: invalid class name */
    /* JADX INFO: compiled from: NendAdInterstitialResponse.java */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[net.nend.android.internal.a.EnumC0106a.values().length];

        static {
            try {
                a[net.nend.android.internal.a.EnumC0106a.ADVIEW.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    private b(a aVar) {
        if (AnonymousClass1.a[aVar.b.ordinal()] != 1) {
            throw new IllegalArgumentException("Unknown view type.");
        }
        if (aVar.o == null) {
            if (TextUtils.isEmpty(aVar.e)) {
                throw new IllegalArgumentException("ImpressionCount url is invalid.");
            }
            if (TextUtils.isEmpty(aVar.f)) {
                throw new IllegalArgumentException("Request url is invalid.");
            }
        }
        this.a = net.nend.android.internal.a.EnumC0106a.ADVIEW;
        this.b = aVar.c;
        this.c = aVar.d;
        this.d = aVar.e;
        this.e = aVar.f;
        this.f = aVar.g;
        this.g = aVar.h;
        this.h = aVar.i;
        this.m = aVar.j;
        this.n = aVar.o;
        this.i = aVar.k;
        this.j = aVar.l;
        this.k = aVar.m;
        this.l = aVar.n;
    }

    public String a() {
        return this.d;
    }

    public String b() {
        return this.e;
    }

    public int c() {
        return this.f;
    }

    public int d() {
        return this.g;
    }

    public String e() {
        return this.h;
    }

    public int f() {
        return this.i;
    }

    public int g() {
        return this.j;
    }

    public int h() {
        return this.k;
    }

    public int i() {
        return this.l;
    }

    public NendAdInterstitial.NendAdInterstitialStatusCode j() {
        return this.n;
    }

    /* JADX INFO: compiled from: NendAdInterstitialResponse.java */
    static final class a {
        static final /* synthetic */ boolean a = !b.class.desiredAssertionStatus();
        private net.nend.android.internal.a.EnumC0106a b = net.nend.android.internal.a.EnumC0106a.NONE;
        private int c;
        private String d;
        private String e;
        private String f;
        private int g;
        private int h;
        private String i;
        private ArrayList<net.nend.android.internal.a> j;
        private int k;
        private int l;
        private int m;
        private int n;
        private NendAdInterstitial.NendAdInterstitialStatusCode o;

        a() {
        }

        a a(net.nend.android.internal.a.EnumC0106a enumC0106a) {
            if (!a && enumC0106a == null) {
                throw new AssertionError();
            }
            this.b = enumC0106a;
            return this;
        }

        a a(int i) {
            this.c = i;
            return this;
        }

        a a(String str) {
            this.i = str;
            return this;
        }

        a b(String str) {
            this.d = str;
            return this;
        }

        a c(String str) {
            if (str != null) {
                this.e = str.replaceAll(" ", "%20");
            } else {
                this.e = null;
            }
            return this;
        }

        a d(String str) {
            if (str != null) {
                this.f = str.replaceAll(" ", "%20");
            } else {
                this.f = null;
            }
            return this;
        }

        a b(int i) {
            this.g = i;
            return this;
        }

        a c(int i) {
            this.h = i;
            return this;
        }

        a a(NendAdInterstitial.NendAdInterstitialStatusCode nendAdInterstitialStatusCode) {
            this.o = nendAdInterstitialStatusCode;
            return this;
        }

        a d(int i) {
            this.k = i;
            return this;
        }

        a e(int i) {
            this.l = i;
            return this;
        }

        a f(int i) {
            this.m = i;
            return this;
        }

        a g(int i) {
            this.n = i;
            return this;
        }

        public b a() {
            return new b(this, null);
        }
    }
}
