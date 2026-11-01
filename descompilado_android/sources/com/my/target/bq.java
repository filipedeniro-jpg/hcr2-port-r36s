package com.my.target;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.Base64;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: BaseResources.java */
/* JADX INFO: loaded from: classes2.dex */
public class bq {
    private static final String eZ = "iVBORw0KGgoAAAANSUhEUgAAAGAAAABgCAQAAABIkb+zAAAAAmJLR0QAAKqNIzIAAAAJcEhZcwAACxMAAAsTAQCanBgAAAAHdElNRQfgAR0KGztQKbC4AAAA8UlEQVR42u2aQQ6EMAzE6IhH8wR+DfeVoCAySRfs82plQ0FNxTQBAAAAAMBXab0frFut4NIeBFTLX4lo4+ufJ+i1z8BI1//sHvz9HSCAAAIIIIAAAiqZo/bl0Vzdi7GECCCAAAJGDlg392wtr77/eEBufXeC/PreBGXoOxOUo+9LUJa+K0F5+p4EZeo7EpSrH5+gbP3oBOXrxyaoQj8yQTX6cQmq0o9KUJ1+TML89A+Ozot+1VznSkxkBBBAAAEEEEAAAQQQ4J8HeqPH3f3+UDMxS4iAgDXt5cVf7iruvVJx/Tuv0aWN//k9AAAAAAB8lx0xVUXCRDTw+wAAAABJRU5ErkJggg==";

    @NonNull
    public static Bitmap q(@NonNull Context context) {
        byte[] bArrDecode = Base64.decode(eZ, 0);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inDensity = 640;
        options.inTargetDensity = context.getResources().getDisplayMetrics().densityDpi;
        return BitmapFactory.decodeByteArray(bArrDecode, 0, bArrDecode.length, options);
    }

    @NonNull
    public static Bitmap b(int i, int i2) {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        paint.setStrokeWidth(i2);
        paint.setAntiAlias(true);
        paint.setColor(-7829368);
        paint.setStrokeCap(Paint.Cap.BUTT);
        paint.setStyle(Paint.Style.STROKE);
        float f = i;
        canvas.drawLine(0.0f, 0.0f, f, f, paint);
        canvas.drawLine(0.0f, f, f, 0.0f, paint);
        return bitmapCreateBitmap;
    }

    @Nullable
    public static Bitmap h(int i) {
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
        float f = i;
        float f2 = f / 100.0f;
        Paint paint = new Paint();
        paint.setColor(0);
        paint.setStyle(Paint.Style.STROKE);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        float f3 = 3.0f * f2;
        Paint paint2 = new Paint();
        paint2.setAntiAlias(true);
        paint2.setColor(-2013265920);
        canvas.drawOval(new RectF(0.0f, 0.0f, f, f), paint2);
        Paint paint3 = new Paint();
        paint3.setAntiAlias(true);
        paint3.setStyle(Paint.Style.STROKE);
        paint3.setStrokeWidth(f3);
        paint3.setColor(-1);
        float f4 = i / 2;
        canvas.drawCircle(f4, f4, f4 - (f3 / 2.0f), paint3);
        paint.setStrokeWidth(f3);
        paint.setColor(-1);
        paint.setAntiAlias(true);
        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        float f5 = 33.0f * f2;
        path.moveTo(f5, f5);
        float f6 = 66.0f * f2;
        path.lineTo(f6, f6);
        path.moveTo(f5, f6);
        float f7 = 50.0f * f2;
        path.lineTo(f7, f7);
        path.moveTo(55.0f * f2, f2 * 45.0f);
        path.lineTo(f6, f5);
        path.close();
        canvas.drawPath(path, paint);
        return bitmapCreateBitmap;
    }
}
