package com.my.target;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;

/* JADX INFO: compiled from: ContainerAdView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class dk extends FrameLayout {
    private int aM;
    private int aN;
    private boolean aO;
    private int maxWidth;

    public dk(@NonNull Context context) {
        super(context);
    }

    public final void setFlexibleWidth(boolean z) {
        this.aO = z;
    }

    public final void setMaxWidth(int i) {
        this.maxWidth = i;
    }

    public final void a(int i, int i2) {
        this.aM = i;
        this.aN = i2;
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected final void onMeasure(int i, int i2) {
        if (this.aM <= 0 || this.aN <= 0) {
            super.onMeasure(i, i2);
            return;
        }
        int size = View.MeasureSpec.getSize(i);
        if (this.aO && this.aM < size) {
            int i3 = this.maxWidth;
            if (i3 > 0) {
                size = Math.min(size, i3);
            }
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(size, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(this.aN, 1073741824));
            return;
        }
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(this.aM, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(this.aN, 1073741824));
    }
}
