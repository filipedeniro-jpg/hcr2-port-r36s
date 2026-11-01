package net.nend.android.internal.a;

import net.nend.android.internal.utilities.k;

/* JADX INFO: compiled from: NendException.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b extends Exception {
    b() {
    }

    b(String str) {
        super(str);
    }

    public b(k kVar) {
        this(kVar.b());
    }

    public b(k kVar, String str) {
        this(kVar.a(str));
    }
}
