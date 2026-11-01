package com.my.target;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

/* JADX INFO: compiled from: StarsRatingView.java */
/* JADX INFO: loaded from: classes2.dex */
public class bz extends View {
    private static final int GRAY = -3355444;
    private static final float iY = 0.2f;
    private static final int iZ = -552162;
    private static final float ja = 0.45f;
    private static final Paint jb = new Paint();
    private int jc;
    private float jd;

    @Nullable
    private Bitmap je;
    private boolean jf;
    private float rating;

    static {
        jb.setAntiAlias(true);
        jb.setStyle(Paint.Style.FILL);
    }

    public bz(@NonNull Context context) {
        super(context);
    }

    public void setStarSize(int i) {
        this.jc = i;
    }

    public void setRating(float f) {
        setContentDescription(Float.toString(f));
        if (f > 5.0f || f < 0.0f) {
            g.a("Rating is out of bounds: " + f);
            this.rating = 0.0f;
        } else {
            this.rating = f;
        }
        invalidate();
    }

    public void setStarsPadding(float f) {
        this.jd = f;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int size = this.jc;
        if (size <= 0) {
            size = View.MeasureSpec.getSize(i2);
            this.jc = size;
        }
        setMeasuredDimension((int) ((size * 5) + (this.jd * 4.0f)), size);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Bitmap bitmap = this.je;
        if (bitmap == null) {
            if (this.jc <= 0 || this.jf) {
                return;
            }
            this.jf = true;
            post(new Runnable() { // from class: com.my.target.bz.1
                @Override // java.lang.Runnable
                public void run() {
                    bz.this.bc();
                }
            });
            return;
        }
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bc() {
        if (this.jc <= 0) {
            return;
        }
        int iFloor = (int) Math.floor(this.rating);
        int iCeil = (int) Math.ceil(5.0f - this.rating);
        float f = iFloor;
        boolean z = this.rating - f >= iY;
        try {
            this.je = Bitmap.createBitmap((int) ((this.jc + this.jd) * 5.0f), this.jc, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(this.je);
            a(0, this.jc, iZ, canvas, iFloor);
            int i = this.jc;
            int i2 = (int) (0 + ((i + this.jd) * f));
            a(i2, i, GRAY, canvas, iCeil);
            if (z) {
                int i3 = this.jc;
                float f2 = this.rating;
                double d = f2;
                double dFloor = Math.floor(f2);
                Double.isNaN(d);
                a(i2, i3, (float) (d - dFloor), canvas);
            }
            invalidate();
            this.jf = false;
        } catch (OutOfMemoryError unused) {
            g.a("Unable to create rating bitmap because of OOME");
        }
    }

    private void a(int i, int i2, int i3, @NonNull Canvas canvas, int i4) {
        jb.setColor(i3);
        canvas.drawPath(a(i, i2 / 2, i4), jb);
    }

    @NonNull
    private Path a(int i, float f, int i2) {
        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        int i3 = 0;
        while (i3 < i2) {
            float f2 = i3;
            float f3 = i + f + (f2 * f * 2.0f) + (f2 * this.jd);
            float f4 = ja * f;
            double d = f3;
            double d2 = f;
            double dSin = Math.sin(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
            Double.isNaN(d2);
            Double.isNaN(d);
            float f5 = 2.0f * f;
            double dCos = Math.cos(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
            Double.isNaN(d2);
            Double.isNaN(d2);
            path.moveTo((float) (d + (dSin * d2)), f5 - ((float) ((dCos * d2) + d2)));
            double d3 = f4;
            double dSin2 = Math.sin(0.6283185307179586d);
            Double.isNaN(d3);
            Double.isNaN(d);
            int i4 = i3;
            float f6 = (float) (d + (dSin2 * d3));
            double dCos2 = Math.cos(0.6283185307179586d);
            Double.isNaN(d3);
            Double.isNaN(d2);
            path.lineTo(f6, f5 - ((float) (d2 + (dCos2 * d3))));
            int i5 = 1;
            while (i5 < 5) {
                double d4 = i5;
                Double.isNaN(d4);
                double d5 = 1.2566370614359172d * d4;
                double dSin3 = Math.sin(d5);
                Double.isNaN(d2);
                Double.isNaN(d);
                double dCos3 = Math.cos(d5);
                Double.isNaN(d2);
                Double.isNaN(d2);
                double d6 = d;
                path.lineTo((float) ((dSin3 * d2) + d), f5 - ((float) (d2 + (dCos3 * d2))));
                double d7 = d5 + 0.6283185307179586d;
                double dSin4 = Math.sin(d7);
                Double.isNaN(d3);
                Double.isNaN(d6);
                double dCos4 = Math.cos(d7);
                Double.isNaN(d3);
                Double.isNaN(d2);
                path.lineTo((float) (d6 + (dSin4 * d3)), f5 - ((float) ((dCos4 * d3) + d2)));
                i5++;
                d = d6;
            }
            i3 = i4 + 1;
        }
        path.close();
        return path;
    }

    private void a(int i, int i2, float f, @NonNull Canvas canvas) {
        jb.setColor(iZ);
        Path pathA = a(0, i2 / 2, 1);
        float f2 = i2 * f;
        Rect rect = new Rect(i, 0, (int) (i + f2), i2);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap((int) f2, i2, Bitmap.Config.ARGB_8888);
        new Canvas(bitmapCreateBitmap).drawPath(pathA, jb);
        canvas.drawBitmap(bitmapCreateBitmap, (Rect) null, rect, jb);
    }
}
