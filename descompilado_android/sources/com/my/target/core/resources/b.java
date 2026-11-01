package com.my.target.core.resources;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.g;

/* JADX INFO: compiled from: InterstitialAdResources.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b {
    @Nullable
    public static Bitmap getVolumeOnIcon(int i) {
        Bitmap bitmapCreateBitmap;
        try {
            bitmapCreateBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
        } catch (OutOfMemoryError unused) {
            g.a("cannot build icon: OOME");
            bitmapCreateBitmap = null;
        }
        if (bitmapCreateBitmap == null) {
            return null;
        }
        float f = i / 100.0f;
        Paint paint = new Paint();
        paint.setColor(0);
        paint.setStyle(Paint.Style.STROKE);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        a(f, i, paint, canvas);
        canvas.drawArc(new RectF(57.0f * f, 45.0f * f, 67.0f * f, 55.0f * f), 90.0f, -180.0f, false, paint);
        canvas.drawArc(new RectF(52.0f * f, 40.0f * f, 72.0f * f, f * 60.0f), 90.0f, -180.0f, false, paint);
        return bitmapCreateBitmap;
    }

    @Nullable
    public static Bitmap getVolumeOffIcon(int i) {
        Bitmap bitmapCreateBitmap;
        try {
            bitmapCreateBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
        } catch (OutOfMemoryError unused) {
            g.a("cannot build icon: OOME");
            bitmapCreateBitmap = null;
        }
        if (bitmapCreateBitmap == null) {
            return null;
        }
        float f = i / 100.0f;
        Paint paint = new Paint();
        paint.setColor(0);
        paint.setStyle(Paint.Style.STROKE);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        a(f, i, paint, canvas);
        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        float f2 = 62.0f * f;
        float f3 = 40.0f * f;
        path.moveTo(f2, f3);
        float f4 = 82.0f * f;
        float f5 = f * 60.0f;
        path.lineTo(f4, f5);
        path.moveTo(f2, f5);
        path.lineTo(f4, f3);
        canvas.drawPath(path, paint);
        return bitmapCreateBitmap;
    }

    @Nullable
    public static Bitmap getPlayIcon(int i) {
        Bitmap bitmapCreateBitmap;
        int i2 = (i / 4) + (i / 32);
        int i3 = i / 8;
        try {
            bitmapCreateBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
        } catch (OutOfMemoryError unused) {
            g.a("cannot build play icon: OOME");
            bitmapCreateBitmap = null;
        }
        if (bitmapCreateBitmap == null) {
            return null;
        }
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(-2013265920);
        float f = i;
        canvas.drawOval(new RectF(0.0f, 0.0f, f, f), paint);
        Paint paint2 = new Paint();
        paint2.setColor(0);
        canvas.drawPaint(paint2);
        paint2.setStrokeWidth(4.0f);
        paint2.setColor(-16733198);
        paint2.setStyle(Paint.Style.FILL_AND_STROKE);
        paint2.setAntiAlias(true);
        int i4 = i3 * 3;
        Point point = new Point(i4, i2);
        Point point2 = new Point(i4, i - i2);
        Point point3 = new Point(i - (i3 * 2), i / 2);
        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        path.moveTo(point.x, point.y);
        path.lineTo(point2.x, point2.y);
        path.lineTo(point3.x, point3.y);
        path.lineTo(point.x, point.y);
        path.close();
        canvas.drawPath(path, paint2);
        return bitmapCreateBitmap;
    }

    @Nullable
    public static Bitmap b(int i, int i2) {
        Bitmap bitmapCreateBitmap;
        float f = i;
        float f2 = f / 10.0f;
        int i3 = (int) (f + f2);
        try {
            bitmapCreateBitmap = Bitmap.createBitmap(i3, i3, Bitmap.Config.ARGB_8888);
        } catch (OutOfMemoryError unused) {
            g.a("cannot build play icon: OOME");
            bitmapCreateBitmap = null;
        }
        if (bitmapCreateBitmap == null) {
            return null;
        }
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        paint.setShadowLayer(2.0f, 0.0f, 2.0f, -3355444);
        paint.setAntiAlias(true);
        paint.setColor(i2);
        canvas.drawOval(new RectF(f2, f2, f, f), paint);
        return bitmapCreateBitmap;
    }

    @Nullable
    public static Bitmap d(int i) {
        Bitmap bitmapCreateBitmap;
        try {
            bitmapCreateBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
        } catch (OutOfMemoryError unused) {
            g.a("cannot build icon: OOME");
            bitmapCreateBitmap = null;
        }
        if (bitmapCreateBitmap == null) {
            return null;
        }
        float f = i / 100.0f;
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        paint.setStrokeWidth(3.0f * f);
        paint.setColor(-1);
        paint.setAntiAlias(true);
        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        float f2 = 48.0f * f;
        path.moveTo(f2, 18.0f * f);
        path.lineTo(7.0f * f, 50.0f * f);
        path.lineTo(f2, 82.0f * f);
        float f3 = 65.0f * f;
        path.lineTo(f2, f3);
        float f4 = 92.0f * f;
        path.lineTo(f4, f3);
        float f5 = f * 35.0f;
        path.lineTo(f4, f5);
        path.lineTo(f2, f5);
        path.close();
        canvas.drawPath(path, paint);
        return bitmapCreateBitmap;
    }

    private static void a(float f, int i, @NonNull Paint paint, @NonNull Canvas canvas) {
        float f2 = 3.0f * f;
        Paint paint2 = new Paint();
        paint2.setAntiAlias(true);
        paint2.setColor(-2013265920);
        float f3 = i;
        canvas.drawOval(new RectF(0.0f, 0.0f, f3, f3), paint2);
        Paint paint3 = new Paint();
        paint3.setAntiAlias(true);
        paint3.setStyle(Paint.Style.STROKE);
        paint3.setStrokeWidth(f2);
        paint3.setColor(-1);
        float f4 = i / 2;
        canvas.drawCircle(f4, f4, f4 - (f2 / 2.0f), paint3);
        paint.setStrokeWidth(f2);
        paint.setColor(-1);
        paint.setAntiAlias(true);
        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        float f5 = 23.0f * f;
        float f6 = 38.0f * f;
        path.moveTo(f5, f6);
        float f7 = 60.0f * f;
        path.lineTo(f5, f7);
        path.lineTo(f6, f7);
        path.lineTo(f6, f6);
        float f8 = 56.0f * f;
        path.lineTo(f8, 27.0f * f);
        path.lineTo(f8, f * 71.0f);
        path.lineTo(f6, f7);
        path.moveTo(f6, f6);
        path.lineTo(f5, f6);
        path.close();
        canvas.drawPath(path, paint);
    }
}
