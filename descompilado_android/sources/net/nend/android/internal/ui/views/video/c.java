package net.nend.android.internal.ui.views.video;

import android.content.Context;
import android.view.TextureView;
import android.view.View;

/* JADX INFO: compiled from: NendAdTextureView.java */
/* JADX INFO: loaded from: classes2.dex */
public class c extends TextureView {
    private int a;
    private int b;

    public c(Context context) {
        super(context);
        this.a = 0;
        this.b = 0;
    }

    public void a(int i, int i2) {
        this.a = i;
        this.b = i2;
        requestLayout();
        invalidate();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int iCeil;
        double dCeil;
        if (this.a <= 0 || this.b <= 0) {
            super.onMeasure(i, i2);
            return;
        }
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int i3 = this.a;
        float f = i3 / size;
        float f2 = this.b / size2;
        if (f2 > f) {
            iCeil = (int) Math.ceil(i3 / f2);
            dCeil = Math.ceil(this.b / f2);
        } else {
            iCeil = (int) Math.ceil(i3 / f);
            dCeil = Math.ceil(this.b / f);
        }
        setMeasuredDimension(iCeil, (int) dCeil);
    }
}
