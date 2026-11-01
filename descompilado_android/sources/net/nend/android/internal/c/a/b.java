package net.nend.android.internal.c.a;

import android.text.TextUtils;

/* JADX INFO: compiled from: NendAdResponse.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b implements net.nend.android.internal.a {
    private final net.nend.android.internal.a.EnumC0106a a;
    private final String b;
    private final String c;
    private final String d;
    private final String e;
    private final String f;
    private final String g;
    private final int h;
    private final int i;
    private final int j;
    private final boolean k;

    /* synthetic */ b(a aVar, AnonymousClass1 anonymousClass1) {
        this(aVar);
    }

    /* JADX INFO: renamed from: net.nend.android.internal.c.a.b$1, reason: invalid class name */
    /* JADX INFO: compiled from: NendAdResponse.java */
    static /* synthetic */ class AnonymousClass1 {
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

    private b(a aVar) {
        int i = AnonymousClass1.a[aVar.b.ordinal()];
        if (i == 1) {
            if (TextUtils.isEmpty(aVar.c)) {
                throw new IllegalArgumentException("Image url is invalid.");
            }
            if (TextUtils.isEmpty(aVar.d)) {
                throw new IllegalArgumentException("Click url is invalid");
            }
            this.a = net.nend.android.internal.a.EnumC0106a.ADVIEW;
            this.b = aVar.c;
            this.c = aVar.d;
            this.d = null;
            this.e = aVar.f;
            this.h = aVar.i;
            this.i = aVar.k;
            this.j = aVar.j;
            this.f = aVar.h;
            this.g = aVar.g;
            this.k = aVar.l;
            return;
        }
        if (i == 2) {
            if (TextUtils.isEmpty(aVar.e)) {
                throw new IllegalArgumentException("Web view url is invalid");
            }
            this.a = net.nend.android.internal.a.EnumC0106a.WEBVIEW;
            this.b = null;
            this.c = null;
            this.d = aVar.e;
            this.e = null;
            this.h = 0;
            this.i = aVar.k;
            this.j = aVar.j;
            this.f = null;
            this.g = null;
            this.k = false;
            return;
        }
        if (i != 3) {
            throw new IllegalArgumentException("Uknown view type.");
        }
        if (TextUtils.isEmpty(aVar.e)) {
            throw new IllegalArgumentException("Web view url is invalid");
        }
        this.a = net.nend.android.internal.a.EnumC0106a.DYNAMICRETARGETING;
        this.b = null;
        this.c = null;
        this.d = aVar.e;
        this.e = null;
        this.h = aVar.i;
        this.i = aVar.k;
        this.j = aVar.j;
        this.f = null;
        this.g = null;
        this.k = false;
    }

    @Override // net.nend.android.internal.a
    public net.nend.android.internal.a.EnumC0106a a() {
        return this.a;
    }

    @Override // net.nend.android.internal.a
    public String b() {
        return this.b;
    }

    @Override // net.nend.android.internal.a
    public String c() {
        return this.c;
    }

    @Override // net.nend.android.internal.a
    public String d() {
        return this.d;
    }

    @Override // net.nend.android.internal.a
    public String e() {
        return this.e;
    }

    @Override // net.nend.android.internal.a
    public int f() {
        return this.j;
    }

    @Override // net.nend.android.internal.a
    public int g() {
        return this.i;
    }

    @Override // net.nend.android.internal.a
    public int h() {
        return this.h;
    }

    @Override // net.nend.android.internal.a
    public String i() {
        return this.f;
    }

    @Override // net.nend.android.internal.a
    public String j() {
        return this.g;
    }

    @Override // net.nend.android.internal.a
    public boolean k() {
        return this.k;
    }

    /* JADX INFO: compiled from: NendAdResponse.java */
    public static final class a {
        static final /* synthetic */ boolean a = !b.class.desiredAssertionStatus();
        private String c;
        private String d;
        private String e;
        private String f;
        private String g;
        private String h;
        private int i;
        private int j;
        private int k;
        private net.nend.android.internal.a.EnumC0106a b = net.nend.android.internal.a.EnumC0106a.NONE;
        private boolean l = false;

        public a a(net.nend.android.internal.a.EnumC0106a enumC0106a) {
            if (!a && enumC0106a == null) {
                throw new AssertionError();
            }
            this.b = enumC0106a;
            return this;
        }

        public a a(String str) {
            if (str != null) {
                this.c = str.replaceAll(" ", "%20");
            } else {
                this.c = null;
            }
            return this;
        }

        public a b(String str) {
            if (str != null) {
                this.d = str.replaceAll(" ", "%20");
            } else {
                this.d = null;
            }
            return this;
        }

        public a c(String str) {
            if (str != null) {
                this.e = str.replaceAll(" ", "%20");
            } else {
                this.e = null;
            }
            return this;
        }

        public a d(String str) {
            this.f = str;
            return this;
        }

        public a a(int i) {
            this.i = i;
            return this;
        }

        public a b(int i) {
            this.k = i;
            return this;
        }

        public a c(int i) {
            this.j = i;
            return this;
        }

        public a e(String str) {
            this.h = str;
            return this;
        }

        public a f(String str) {
            this.l = "1".equals(str);
            return this;
        }

        public b a() {
            return new b(this, null);
        }
    }
}
