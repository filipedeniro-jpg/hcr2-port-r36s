package net.nend.android.internal.d;

/* JADX INFO: compiled from: Tuple.java */
/* JADX INFO: loaded from: classes2.dex */
public class m<T1, T2> implements i {
    public T1 a = null;
    public T2 b = null;

    m() {
    }

    @Override // net.nend.android.internal.d.i
    public void a(Object... objArr) {
        this.a = (T1) objArr[0];
        this.b = (T2) objArr[1];
    }
}
