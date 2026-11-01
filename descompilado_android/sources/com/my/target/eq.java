package com.my.target;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: PageDotsView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class eq extends LinearLayout {

    @Nullable
    private Bitmap dt;

    @Nullable
    private Bitmap du;

    @Nullable
    private ImageView[] dv;
    private int dw;
    private boolean dx;

    public eq(Context context) {
        super(context);
    }

    public final void a(int i, int i2, int i3) {
        ck ckVarX = ck.x(getContext());
        this.dt = com.my.target.core.resources.b.b(ckVarX.l(12), i3);
        this.du = com.my.target.core.resources.b.b(ckVarX.l(12), i2);
        this.dv = new ImageView[i];
        for (int i4 = 0; i4 < i; i4++) {
            this.dv[i4] = new ImageView(getContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.setMargins(ckVarX.l(5), ckVarX.l(5), ckVarX.l(5), ckVarX.l(5));
            this.dv[i4].setLayoutParams(layoutParams);
            this.dv[i4].setImageBitmap(this.du);
            addView(this.dv[i4]);
        }
        this.dx = true;
    }

    public final void g(int i) {
        ImageView[] imageViewArr;
        int i2;
        if (this.dx && (imageViewArr = this.dv) != null && i >= 0 && i < imageViewArr.length && (i2 = this.dw) < imageViewArr.length) {
            imageViewArr[i2].setImageBitmap(this.du);
            this.dv[i].setImageBitmap(this.dt);
            this.dw = i;
        }
    }
}
