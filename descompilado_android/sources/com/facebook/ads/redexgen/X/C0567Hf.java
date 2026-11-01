package com.facebook.ads.redexgen.X;

import android.R;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.api.BuildConfigApi;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Hf, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
@TargetApi(19)
public final class C0567Hf implements InterfaceC0578Hq {
    private static final String N = C0567Hf.class.getSimpleName();
    private final JV B;
    private final C0531Fu C;
    private String E;
    private String F;
    private final BT G;
    private long I;
    private final BR L;
    private final BJ M;
    private final InterfaceC0528Fr D = new C0577Hp(this);
    private boolean J = true;
    private long K = -1;
    private boolean H = true;

    public C0567Hf(C0531Fu activityImpl, JV adEventManager, InterfaceC0525Fo listener) {
        this.C = activityImpl;
        this.B = adEventManager;
        int i = (int) (2.0f * J6.B);
        this.G = new BT(activityImpl.B());
        this.G.setId(View.generateViewId());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(10);
        this.G.setListener(new C0570Hi(this, activityImpl));
        listener.WB(this.G, layoutParams);
        this.M = new BJ(activityImpl.B());
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.addRule(3, this.G.getId());
        layoutParams2.addRule(12);
        this.M.setListener(new C0568Hg(this));
        listener.WB(this.M, layoutParams2);
        this.L = new BR(activityImpl.B(), null, R.attr.progressBarStyleHorizontal);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, i);
        layoutParams3.addRule(3, this.G.getId());
        this.L.setProgress(0);
        listener.WB(this.L, layoutParams3);
        activityImpl.A(this.D);
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0578Hq
    public final void bC(Intent intent, Bundle savedInstanceState, C0531Fu activityImpl) {
        if (this.K < 0) {
            this.K = System.currentTimeMillis();
        }
        if (savedInstanceState == null) {
            this.E = intent.getStringExtra("browserURL");
            this.F = intent.getStringExtra("clientToken");
            this.I = intent.getLongExtra("handlerTime", -1L);
        } else {
            this.E = savedInstanceState.getString("browserURL");
            this.F = savedInstanceState.getString("clientToken");
            this.I = savedInstanceState.getLong("handlerTime", -1L);
        }
        String str = this.E != null ? this.E : "about:blank";
        this.G.setUrl(str);
        this.M.loadUrl(str);
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0578Hq
    public final void nE(boolean launchAdReporting) {
        this.M.onPause();
        if (this.H) {
            this.H = false;
            BO boA = new BP(this.M.getFirstUrl()).C(this.I).E(this.K).F(this.M.getResponseEndMs()).B(this.M.getDomContentLoadedMs()).G(this.M.getScrollReadyMs()).D(this.M.getLoadFinishMs()).H(System.currentTimeMillis()).A();
            this.B.gC(this.F, boA.A());
            if (BuildConfigApi.isDebug()) {
                Log.d(N, "Browser session data logged @ " + System.currentTimeMillis() + ":\n  - Handler Time: " + boA.C + "\n  - Load Start Time: " + boA.E + "\n  - Response End Time: " + boA.F + "\n  - Dom Content Loaded Time: " + boA.B + "\n  - Scroll Ready Time: " + boA.G + "\n  - Load Finish Time: " + boA.D + "\n  - Session Finish Time: " + boA.H);
            }
        }
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0578Hq
    public final void oF(Bundle savedInstanceState) {
        savedInstanceState.putString("browserURL", this.E);
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0578Hq
    public final void onDestroy() {
        this.C.D(this.D);
        ID.C(this.M);
        this.M.destroy();
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0578Hq
    public final void yE(boolean leaveAdReporting) {
        this.M.onResume();
    }
}
