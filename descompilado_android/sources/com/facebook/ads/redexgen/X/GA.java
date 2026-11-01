package com.facebook.ads.redexgen.X;

import com.facebook.ads.MediaViewListener;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class GA implements G9 {
    public final /* synthetic */ GB B;
    public final /* synthetic */ MediaViewListener C;

    public GA(GB this$0, MediaViewListener mediaViewListener) {
        this.B = this$0;
        this.C = mediaViewListener;
    }

    @Override // com.facebook.ads.redexgen.X.G9
    public final void AE() {
        this.C.onExitFullscreen(this.B.J);
    }

    @Override // com.facebook.ads.redexgen.X.G9
    public final void CE() {
        this.C.onFullscreenBackground(this.B.J);
    }

    @Override // com.facebook.ads.redexgen.X.G9
    public final void DE() {
        this.C.onFullscreenForeground(this.B.J);
    }

    @Override // com.facebook.ads.redexgen.X.G9
    public final void QF() {
        this.C.onVolumeChange(this.B.J, this.B.N.getVolume());
    }

    @Override // com.facebook.ads.redexgen.X.G9
    public final void lD() {
        this.C.onComplete(this.B.J);
    }

    @Override // com.facebook.ads.redexgen.X.G9
    public final void onPause() {
        this.C.onPause(this.B.J);
    }

    @Override // com.facebook.ads.redexgen.X.G9
    public final void pE() {
        this.C.onPlay(this.B.J);
    }

    @Override // com.facebook.ads.redexgen.X.G9
    public final void wD() {
        this.C.onEnterFullscreen(this.B.J);
    }
}
