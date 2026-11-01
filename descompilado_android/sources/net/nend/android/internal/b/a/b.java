package net.nend.android.internal.b.a;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: NendAdController.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b {
    private final net.nend.android.internal.b.a.a.a a;
    private final Handler b;
    private boolean c = false;
    private boolean d = true;

    public b(net.nend.android.internal.b.a.a.a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Ad object is null.");
        }
        this.a = aVar;
        this.b = new a(Looper.getMainLooper(), aVar);
    }

    public void a(boolean z) {
        this.d = z;
        if (z) {
            b();
        } else {
            c();
        }
    }

    public void a() {
        c();
        this.b.sendEmptyMessage(718);
    }

    public boolean b() {
        if (!this.d || !this.c || this.b.hasMessages(718)) {
            return false;
        }
        this.b.sendEmptyMessageDelayed(718, this.a.h() * 1000);
        return true;
    }

    public void c() {
        this.b.removeMessages(718);
        this.a.o();
    }

    public void b(boolean z) {
        this.c = z;
        if (z && this.a.m()) {
            b();
        }
    }

    /* JADX INFO: compiled from: NendAdController.java */
    private static class a extends Handler {
        private WeakReference<net.nend.android.internal.b.a.a.a> a;

        a(Looper looper, net.nend.android.internal.b.a.a.a aVar) {
            super(looper);
            this.a = new WeakReference<>(aVar);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            net.nend.android.internal.b.a.a.a aVar = this.a.get();
            if (aVar != null) {
                aVar.n();
            }
        }
    }
}
