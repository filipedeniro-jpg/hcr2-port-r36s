package com.facebook.ads.redexgen.X;

import android.view.MotionEvent;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.HashMap;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.83, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class AnonymousClass83 implements View.OnTouchListener {
    public final C01581l B;
    public final JV C;
    public final WeakReference<BB> D;

    private AnonymousClass83(BB adWebView, JV adEventManager, C01581l adData) {
        this.D = new WeakReference<>(adWebView);
        this.C = adEventManager;
        this.B = adData;
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View v, MotionEvent event) {
        if (this.D.get() != null && event.getAction() == 1) {
            HashMap map = new HashMap();
            this.D.get().getViewabilityChecker().A(map);
            map.put("touch", MJ.C(this.D.get().getTouchDataRecorder().m258B()));
            this.C.oC(this.B.E(), map);
            return false;
        }
        return false;
    }
}
