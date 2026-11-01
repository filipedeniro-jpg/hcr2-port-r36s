package com.facebook.ads.redexgen.X;

import android.view.View;
import com.facebook.ads.NativeAdBase;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Fd, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class ViewOnClickListenerC0514Fd implements View.OnClickListener {
    public final /* synthetic */ C0516Ff B;
    public final /* synthetic */ NativeAdBase C;

    public ViewOnClickListenerC0514Fd(C0516Ff this$0, NativeAdBase nativeAdBase) {
        this.B = this$0;
        this.C = nativeAdBase;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View v) {
        ((KF) this.C.getInternalNativeAd()).P();
    }
}
