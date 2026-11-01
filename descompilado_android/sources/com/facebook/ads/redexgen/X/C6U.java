package com.facebook.ads.redexgen.X;

import android.media.AudioManager;
import java.lang.ref.WeakReference;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.6U, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C6U extends GE {
    public final /* synthetic */ C6T B;

    public C6U(C6T this$0) {
        this.B = this$0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.facebook.ads.redexgen.X.AbstractC01210a
    public final void B(AnonymousClass71 event) {
        if (this.B.B == null || this.B.B.get() == null) {
            this.B.B = new WeakReference(new C6V(this));
        }
        ((AudioManager) this.B.getContext().getApplicationContext().getSystemService("audio")).requestAudioFocus((AudioManager.OnAudioFocusChangeListener) this.B.B.get(), 3, 1);
    }
}
