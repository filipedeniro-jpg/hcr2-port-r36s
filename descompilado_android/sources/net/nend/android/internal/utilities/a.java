package net.nend.android.internal.utilities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import java.io.IOException;

/* JADX INFO: compiled from: AssetsUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public class a {
    public static Bitmap a(Context context, String str) {
        Bitmap bitmapB = b(context, str);
        if (bitmapB == null) {
            return null;
        }
        float f = context.getResources().getDisplayMetrics().density;
        Matrix matrix = new Matrix();
        float f2 = f / 2.0f;
        matrix.setScale(f2, f2);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmapB, 0, 0, bitmapB.getWidth(), bitmapB.getHeight(), matrix, true);
        if (bitmapB != bitmapCreateBitmap) {
            bitmapB.recycle();
        }
        return bitmapCreateBitmap;
    }

    public static Bitmap b(Context context, String str) {
        try {
            return BitmapFactory.decodeStream(context.getResources().getAssets().open(str));
        } catch (IOException unused) {
            return null;
        }
    }
}
