package com.inmobi.ads;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: compiled from: NativeContainerAsset.java */
/* JADX INFO: loaded from: classes2.dex */
public final class am extends ak implements Iterable<ak> {
    int A;
    ak[] B;
    int C;
    long z;

    @Override // java.lang.Iterable
    public final Iterator<ak> iterator() {
        return new a();
    }

    public am(String str, String str2, al alVar, int i, JSONObject jSONObject, int i2) {
        this(str, str2, alVar, new LinkedList(), i, jSONObject, i2);
    }

    public am(String str, String str2, al alVar, List<NativeTracker> list, int i, JSONObject jSONObject, int i2) {
        super(str, str2, "CONTAINER", alVar, list);
        this.z = 0L;
        this.f = jSONObject;
        this.B = new ak[1];
        this.i = i;
        this.C = 0;
        this.A = i2;
    }

    /* JADX INFO: compiled from: NativeContainerAsset.java */
    private class a implements Iterator<ak> {
        private int b = 0;

        public a() {
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.b < am.this.C;
        }

        @Override // java.util.Iterator
        public final void remove() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Iterator
        public final /* synthetic */ ak next() {
            ak[] akVarArr = am.this.B;
            int i = this.b;
            this.b = i + 1;
            return akVarArr[i];
        }
    }

    public final ak a(int i) {
        if (i < 0 || i >= this.C) {
            return null;
        }
        return this.B[i];
    }
}
