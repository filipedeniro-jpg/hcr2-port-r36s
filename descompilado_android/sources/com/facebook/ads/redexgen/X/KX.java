package com.facebook.ads.redexgen.X;

import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class KX implements View.OnSystemUiVisibilityChangeListener {
    private int B;
    private final View E;

    @Nullable
    private Window F;
    private EnumC0641Kc D = EnumC0641Kc.DEFAULT;
    private final Runnable C = new RunnableC0644Kf(this);

    public KX(View view) {
        this.E = view;
        this.E.setOnSystemUiVisibilityChangeListener(this);
    }

    public final void A(Window window) {
        this.F = window;
    }

    public final void B() {
        this.F = null;
    }

    public final void C(EnumC0641Kc systemUIVisibilityMode) {
        this.D = systemUIVisibilityMode;
        switch (this.D) {
            case FULL_SCREEN:
                D(67108864, true);
                D(134217728, true);
                C(false);
                break;
            default:
                D(67108864, false);
                D(134217728, false);
                this.E.setSystemUiVisibility(0);
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void C(boolean visible) {
        if (EnumC0641Kc.DEFAULT.equals(this.D)) {
            return;
        }
        int i = 3840;
        if (!visible) {
            i = 3840 | 7;
        }
        Handler handler = this.E.getHandler();
        if (handler != null && visible) {
            handler.removeCallbacks(this.C);
            handler.postDelayed(this.C, 2000L);
        }
        this.E.setSystemUiVisibility(i);
    }

    private void D(int flag, boolean value) {
        if (this.F == null) {
            return;
        }
        WindowManager.LayoutParams attributes = this.F.getAttributes();
        if (value) {
            attributes.flags |= flag;
        } else {
            attributes.flags &= flag ^ (-1);
        }
        this.F.setAttributes(attributes);
    }

    @Override // android.view.View.OnSystemUiVisibilityChangeListener
    public final void onSystemUiVisibilityChange(int visibility) {
        int i = this.B ^ visibility;
        this.B = visibility;
        if ((i & 2) != 0 && (visibility & 2) == 0) {
            C(true);
        }
    }
}
