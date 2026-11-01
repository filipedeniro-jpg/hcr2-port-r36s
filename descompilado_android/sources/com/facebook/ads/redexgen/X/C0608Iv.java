package com.facebook.ads.redexgen.X;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RSRuntimeException;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Iv, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0608Iv {
    private C0608Iv() {
    }

    @WorkerThread
    @Nullable
    public static Bitmap B(Context context, Bitmap original, int radius, int sampling) {
        int height = original.getWidth() / sampling;
        int width = original.getHeight() / sampling;
        if (height == 0 || width == 0 || Build.VERSION.SDK_INT < 18) {
            return null;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(height, width, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        canvas.scale(1.0f / sampling, 1.0f / sampling);
        Paint paint = new Paint();
        paint.setFlags(3);
        paint.setColorFilter(new PorterDuffColorFilter(0, PorterDuff.Mode.SRC_ATOP));
        canvas.drawBitmap(original, 0.0f, 0.0f, paint);
        try {
            Bitmap bitmapC = C(context, bitmapCreateBitmap, radius);
            if (sampling == 1) {
                return bitmapC;
            }
            Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmapC, original.getWidth(), original.getHeight(), true);
            bitmapC.recycle();
            return bitmapCreateScaledBitmap;
        } catch (RSRuntimeException e) {
            return new IS().A(bitmapCreateBitmap, radius);
        }
    }

    @WorkerThread
    @TargetApi(18)
    private static Bitmap C(Context context, Bitmap bitmap, int radius) throws RSRuntimeException {
        RenderScript rs = null;
        Allocation allocationCreateFromBitmap = null;
        Allocation allocationCreateTyped = null;
        ScriptIntrinsicBlur scriptIntrinsicBlurCreate = null;
        try {
            rs = RenderScript.create(context);
            rs.setMessageHandler(new RenderScript.RSMessageHandler());
            allocationCreateFromBitmap = Allocation.createFromBitmap(rs, bitmap, Allocation.MipmapControl.MIPMAP_NONE, 1);
            allocationCreateTyped = Allocation.createTyped(rs, allocationCreateFromBitmap.getType());
            scriptIntrinsicBlurCreate = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
            scriptIntrinsicBlurCreate.setInput(allocationCreateFromBitmap);
            scriptIntrinsicBlurCreate.setRadius(radius);
            scriptIntrinsicBlurCreate.forEach(allocationCreateTyped);
            allocationCreateTyped.copyTo(bitmap);
            return bitmap;
        } finally {
            if (rs != null) {
                rs.destroy();
            }
            if (allocationCreateFromBitmap != null) {
                allocationCreateFromBitmap.destroy();
            }
            if (allocationCreateTyped != null) {
                allocationCreateTyped.destroy();
            }
            if (scriptIntrinsicBlurCreate != null) {
                scriptIntrinsicBlurCreate.destroy();
            }
        }
    }
}
