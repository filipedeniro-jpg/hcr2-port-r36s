package net.nend.android.internal.d;

/* JADX INFO: compiled from: Tuple3.java */
/* JADX INFO: loaded from: classes2.dex */
public class n<T1, T2, T3> implements i {
    public T1 a = null;
    public T2 b = null;
    public T3 c = null;

    n() {
    }

    @Override // net.nend.android.internal.d.i
    public void a(Object... objArr) {
        this.a = (T1) objArr[0];
        this.b = (T2) objArr[1];
        this.c = (T3) objArr[2];
    }
}
