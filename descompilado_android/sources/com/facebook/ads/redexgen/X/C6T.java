package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.media.AudioManager;
import java.lang.ref.WeakReference;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.6T, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C6T extends AnonymousClass79 {
    private WeakReference<AudioManager.OnAudioFocusChangeListener> B;
    private final GK C;
    private final GG D;
    private final GE E;

    public C6T(Context context) {
        super(context);
        this.B = null;
        this.C = new GK() { // from class: com.facebook.ads.redexgen.X.6Y
            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final void B(AnonymousClass76 event) {
                ((AudioManager) this.B.getContext().getApplicationContext().getSystemService("audio")).abandonAudioFocus(this.B.B == null ? null : (AudioManager.OnAudioFocusChangeListener) this.B.B.get());
            }
        };
        this.D = new GG() { // from class: com.facebook.ads.redexgen.X.6X
            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.facebook.ads.redexgen.X.AbstractC01210a
            public final void B(AnonymousClass72 event) {
                ((AudioManager) this.B.getContext().getApplicationContext().getSystemService("audio")).abandonAudioFocus(this.B.B == null ? null : (AudioManager.OnAudioFocusChangeListener) this.B.B.get());
            }
        };
        this.E = new C6U(this);
    }

    @Override // com.facebook.ads.redexgen.X.AnonymousClass79
    public final void A() {
        super.A();
        if (getVideoView() != null) {
            getVideoView().getEventBus().B(this.E, this.C, this.D);
        }
    }

    @Override // com.facebook.ads.redexgen.X.AnonymousClass79
    public final void B() {
        if (getVideoView() != null) {
            getVideoView().getEventBus().D(this.D, this.C, this.E);
        }
        super.B();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        ((AudioManager) getContext().getApplicationContext().getSystemService("audio")).abandonAudioFocus(this.B == null ? null : this.B.get());
        super.onDetachedFromWindow();
    }
}
