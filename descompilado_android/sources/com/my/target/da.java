package com.my.target;

import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;

/* JADX INFO: compiled from: ViewabilityView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class da extends View {

    @Nullable
    private a M;

    @NonNull
    private final b aM;

    /* JADX INFO: compiled from: ViewabilityView.java */
    public interface a {
        void b(boolean z);
    }

    @NonNull
    @VisibleForTesting
    final b getViewabilityState() {
        return this.aM;
    }

    public da(Context context) {
        super(context);
        this.aM = new b();
    }

    public final boolean V() {
        return this.aM.V();
    }

    public final void setViewabilityListener(@Nullable a aVar) {
        this.M = aVar;
    }

    @Override // android.view.View
    protected final void onMeasure(int i, int i2) {
        setMeasuredDimension(1, 1);
    }

    @Override // android.view.View
    public final void onWindowFocusChanged(boolean z) {
        a aVar;
        super.onWindowFocusChanged(z);
        this.aM.setFocused(z);
        if (this.aM.V()) {
            a aVar2 = this.M;
            if (aVar2 != null) {
                aVar2.b(true);
                return;
            }
            return;
        }
        if (z || (aVar = this.M) == null) {
            return;
        }
        aVar.b(false);
    }

    @Override // android.view.View
    protected final void onAttachedToWindow() {
        super.onAttachedToWindow();
        onAttachedToWindow(true);
    }

    private void onAttachedToWindow(boolean z) {
        a aVar;
        this.aM.setAttached(z);
        this.aM.setFocused(hasWindowFocus());
        if (this.aM.V()) {
            a aVar2 = this.M;
            if (aVar2 != null) {
                aVar2.b(true);
                return;
            }
            return;
        }
        if (z || (aVar = this.M) == null) {
            return;
        }
        aVar.b(false);
    }

    @Override // android.view.View
    protected final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        onAttachedToWindow(false);
    }

    /* JADX INFO: compiled from: ViewabilityView.java */
    static class b {
        private boolean aN;
        private boolean aO;

        b() {
        }

        final void setAttached(boolean z) {
            this.aN = z;
        }

        final void setFocused(boolean z) {
            this.aO = z;
        }

        final boolean V() {
            return this.aN && this.aO;
        }
    }
}
