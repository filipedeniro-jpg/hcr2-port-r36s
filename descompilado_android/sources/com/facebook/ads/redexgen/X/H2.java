package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class H2 {
    private final H5 B;
    private C0618Jf C;
    private final H9 E;
    private long F;
    private final boolean I;
    private final boolean J;
    private final HG K;
    private static final String L = H2.class.getSimpleName();
    private static final ExecutorService N = Executors.newSingleThreadExecutor();
    private static final ExecutorService M = Executors.newFixedThreadPool(5);
    private final Handler D = new Handler();
    private final List<Callable<Boolean>> G = new ArrayList();
    private final List<Callable<Boolean>> H = new ArrayList();

    public H2(Context context) {
        this.E = H9.B(context);
        this.K = HG.B(context);
        this.B = H5.C(context);
        this.J = JA.Z(context);
        this.I = JA.SB(context);
    }

    public final void A(H0 cacheFileData) {
        this.G.add(new CallableC0559Gx(this, cacheFileData));
    }

    public final void B(C0560Gy imageData) {
        this.G.add(new CallableC0561Gz(this, imageData));
    }

    public final void C(C0560Gy imageData) {
        this.H.add(new CallableC0561Gz(this, imageData));
    }

    public final void D(H0 videoData) {
        if (this.J) {
            this.H.add(new CallableC0559Gx(this, videoData));
        } else {
            this.H.add(new H1(this, videoData, null));
        }
    }

    public final void E(H0 videoData) {
        if (this.J) {
            this.G.add(new CallableC0559Gx(this, videoData));
        } else {
            this.G.add(new H1(this, videoData, null));
        }
    }

    public final void F() {
        C0642Kd.B("cacheCompletionHook", "Caching complete");
    }

    public final void G() {
        C0642Kd.B("cacheFailureHook", "Caching failed");
    }

    public final void H(@Nullable InterfaceC01280h interfaceC01280h, C0558Gw c0558Gw) {
        C0642Kd.B("execute", "Caching started...");
        this.F = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList(this.G);
        ArrayList arrayList2 = new ArrayList(this.H);
        if (this.I) {
            arrayList.addAll(arrayList2);
            arrayList2.clear();
        }
        N.execute(new RunnableC0557Gv(this, arrayList, interfaceC01280h, c0558Gw, arrayList2));
        this.G.clear();
        this.H.clear();
    }

    public final String I(String mUrl) {
        String strB = this.B.B(mUrl);
        return strB != null ? strB : mUrl;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static AtomicBoolean I(ArrayList<Callable<Boolean>> arrayList) {
        List<Future<Boolean>> futures = new ArrayList<>(arrayList.size());
        Iterator<Callable<Boolean>> it = arrayList.iterator();
        while (it.hasNext()) {
            futures.add(M.submit(it.next()));
        }
        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        try {
            Iterator<Future<Boolean>> it2 = futures.iterator();
            while (it2.hasNext()) {
                atomicBoolean.set(atomicBoolean.get() && it2.next().get().booleanValue());
            }
        } catch (InterruptedException | ExecutionException e) {
            Log.e(L, "Exception while executing cache downloads.", e);
            atomicBoolean.set(false);
        }
        return atomicBoolean;
    }

    public final String J(String url) {
        return this.E.B(url);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void J(EnumC0617Je event) {
        if (this.C == null) {
            return;
        }
        HashMap map = new HashMap();
        map.put("load_time_ms", C0622Jj.C(this.F));
        this.C.A(event, map);
    }

    public final String K(String mUrl) {
        if (this.J) {
            String strB = this.B.B(mUrl);
            return strB != null ? strB : mUrl;
        }
        return this.K.B(mUrl);
    }

    public final void L(C0618Jf funnelLoggingHandler) {
        this.C = funnelLoggingHandler;
    }
}
