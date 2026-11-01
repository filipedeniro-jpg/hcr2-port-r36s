package net.nend.android.internal.c.c;

import android.text.TextUtils;
import java.util.ArrayList;

/* JADX INFO: compiled from: NendAdIconResponse.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b {
    private final net.nend.android.internal.a.EnumC0106a a;
    private final int b;
    private int c;
    private String d;
    private String e;
    private ArrayList<net.nend.android.internal.a> f;

    /* synthetic */ b(a aVar, AnonymousClass1 anonymousClass1) {
        this(aVar);
    }

    /* JADX INFO: renamed from: net.nend.android.internal.c.c.b$1, reason: invalid class name */
    /* JADX INFO: compiled from: NendAdIconResponse.java */
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
        if (TextUtils.isEmpty(aVar.f)) {
            throw new IllegalArgumentException("ImpressionCount Url is invalid.");
        }
        this.a = net.nend.android.internal.a.EnumC0106a.ADVIEW;
        this.b = aVar.c;
        this.c = aVar.d;
        this.d = aVar.e;
        this.e = aVar.f;
        this.f = aVar.g;
    }

    public int a() {
        return this.b;
    }

    public String b() {
        return this.e;
    }

    public ArrayList<net.nend.android.internal.a> c() {
        return this.f;
    }

    /* JADX INFO: compiled from: NendAdIconResponse.java */
    static final class a {
        static final /* synthetic */ boolean a = !b.class.desiredAssertionStatus();
        private net.nend.android.internal.a.EnumC0106a b = net.nend.android.internal.a.EnumC0106a.NONE;
        private int c;
        private int d;
        private String e;
        private String f;
        private ArrayList<net.nend.android.internal.a> g;

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

        a b(int i) {
            this.d = i;
            return this;
        }

        a a(String str) {
            this.e = str;
            return this;
        }

        a b(String str) {
            if (str != null) {
                this.f = str.replaceAll(" ", "%20");
            } else {
                this.f = null;
            }
            return this;
        }

        a a(ArrayList<net.nend.android.internal.a> arrayList) {
            this.g = arrayList;
            return this;
        }

        b a() {
            return new b(this, null);
        }
    }
}
