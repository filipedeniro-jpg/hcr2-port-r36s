package net.nend.android.internal.ui.a;

/* JADX INFO: compiled from: JSCallback.java */
/* JADX INFO: loaded from: classes2.dex */
public class d {
    static final d a = new d(a.CLICK_INFORMATION);
    static final d b = new d(a.CLICK_CLOSE);
    private final a c;
    private final Object d;
    private final String e;

    /* JADX INFO: compiled from: JSCallback.java */
    public enum a {
        VIEW_SOURCE,
        CLICK_AD,
        CLICK_INFORMATION,
        CLICK_CLOSE,
        VIDEO_RECT
    }

    d(a aVar, String str) {
        this(aVar, str, null);
    }

    d(a aVar, String str, Object obj) {
        this.c = aVar;
        this.d = obj;
        this.e = str;
    }

    private d(a aVar) {
        this(aVar, null, null);
    }

    public a a() {
        return this.c;
    }

    public Object b() {
        return this.d;
    }

    public String c() {
        return this.e;
    }
}
