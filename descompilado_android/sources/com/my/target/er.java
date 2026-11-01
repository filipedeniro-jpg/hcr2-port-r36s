package com.my.target;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.common.models.ImageData;
import java.util.List;

/* JADX INFO: compiled from: SliderAdView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class er extends RelativeLayout {

    @NonNull
    private final ck aC;

    @NonNull
    private final eq dB;

    @NonNull
    private final bw dC;

    @NonNull
    private final eu dD;

    @NonNull
    private final FrameLayout dE;

    @NonNull
    private final RelativeLayout.LayoutParams dF;

    @NonNull
    private final RelativeLayout.LayoutParams dG;

    @NonNull
    private final RelativeLayout.LayoutParams dH;
    private int orientation;
    private static final int dy = ck.br();
    private static final int dz = ck.br();
    private static final int dA = ck.br();

    public er(Context context) {
        super(context);
        this.aC = ck.x(context);
        this.dB = new eq(context);
        this.dC = new bw(context);
        this.dE = new FrameLayout(context);
        this.dD = new eu(context);
        this.dD.setId(dy);
        this.dG = new RelativeLayout.LayoutParams(-2, -2);
        this.dC.setId(dA);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.gravity = 17;
        this.dD.setLayoutParams(layoutParams);
        this.dF = new RelativeLayout.LayoutParams(-2, -2);
        this.dF.addRule(14, -1);
        this.dF.addRule(12, -1);
        this.dB.setId(dz);
        this.dH = new RelativeLayout.LayoutParams(-1, -1);
        this.dH.addRule(2, dz);
        this.dE.addView(this.dD);
        addView(this.dE);
        addView(this.dB);
        addView(this.dC);
    }

    public final void setCloseClickListener(@Nullable View.OnClickListener onClickListener) {
        this.dC.setOnClickListener(onClickListener);
    }

    public final void setFSSliderCardListener(@Nullable eu.c cVar) {
        this.dD.setSliderCardListener(cVar);
    }

    public final void a(@NonNull com.my.target.core.models.sections.d dVar, @NonNull List<com.my.target.core.models.banners.h> list) {
        ImageData closeIcon = dVar.getCloseIcon();
        if (closeIcon != null && closeIcon.getBitmap() != null) {
            this.dC.a(closeIcon.getBitmap(), true);
        } else {
            this.dC.a(com.my.target.core.resources.b.d(this.aC.l(36)), false);
        }
        setBackgroundColor(dVar.getBackgroundColor());
        int size = list.size();
        if (size > 1) {
            this.dB.a(size, dVar.s(), dVar.o());
        } else {
            this.dB.setVisibility(8);
        }
        this.dD.a(list, dVar.getBackgroundColor());
    }

    public final void g(int i) {
        this.dB.g(i);
    }

    @Override // android.widget.RelativeLayout, android.view.View
    protected final void onMeasure(int i, int i2) {
        int i3 = ((float) View.MeasureSpec.getSize(i)) / ((float) View.MeasureSpec.getSize(i2)) > 1.0f ? 2 : 1;
        if (i3 != this.orientation) {
            setLayoutOrientation(i3);
        }
        super.onMeasure(i, i2);
    }

    private void setLayoutOrientation(int i) {
        this.orientation = i;
        if (this.orientation == 1) {
            this.dF.setMargins(0, this.aC.l(12), 0, this.aC.l(16));
            this.dH.topMargin = this.aC.l(56);
            this.dG.setMargins(0, 0, 0, 0);
        } else {
            this.dF.setMargins(0, this.aC.l(6), 0, this.aC.l(8));
            this.dH.topMargin = this.aC.l(28);
            this.dG.setMargins(this.aC.l(-4), this.aC.l(-8), 0, 0);
        }
        this.dE.setLayoutParams(this.dH);
        this.dB.setLayoutParams(this.dF);
        this.dC.setLayoutParams(this.dG);
    }
}
