package com.facebook.ads.redexgen.X;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.util.Base64;
import com.facebook.share.internal.MessengerShareContentUtility;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Ir, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0605Ir {
    private C0605Ir() {
    }

    public static Drawable B(Context context, EnumC0606Is encodedImage) {
        return new BitmapDrawable(context.getResources(), E(encodedImage));
    }

    @WorkerThread
    @SuppressLint({"CatchGeneralException"})
    @Nullable
    public static Bitmap C(String path, int reqHeight, int reqWidth) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(path, options);
            options.inSampleSize = F(options, reqWidth, reqHeight);
            options.inJustDecodeBounds = false;
            return BitmapFactory.decodeFile(path, options);
        } catch (Throwable t) {
            Context contextB = HM.B();
            if (contextB != null) {
                C0585Hx.D(contextB, MessengerShareContentUtility.MEDIA_IMAGE, C0584Hw.TB, new C0583Hv(t));
            }
            return null;
        }
    }

    @WorkerThread
    public static Bitmap D(InputStream stream, int reqHeight, int reqWidth) throws IOException {
        if (Build.VERSION.SDK_INT < 19) {
            return BitmapFactory.decodeStream(stream);
        }
        IM im = new IM(stream);
        im.mark(8192);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(im, null, options);
        im.reset();
        if (!im.A()) {
            options.inSampleSize = F(options, reqWidth, reqHeight);
            options.inJustDecodeBounds = false;
            return BitmapFactory.decodeStream(im, null, options);
        }
        return BitmapFactory.decodeStream(im);
    }

    public static Bitmap E(EnumC0606Is encodedImage) {
        byte[] bArrDecode = Base64.decode(encodedImage.A(J6.B), 0);
        return BitmapFactory.decodeByteArray(bArrDecode, 0, bArrDecode.length);
    }

    private static int F(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int inSampleSize = options.outHeight;
        int width = options.outWidth;
        int halfHeight = 1;
        if (inSampleSize > reqHeight || width > reqWidth) {
            int i = inSampleSize / 2;
            int inSampleSize2 = width / 2;
            while (i / halfHeight >= reqHeight && inSampleSize2 / halfHeight >= reqWidth) {
                halfHeight *= 2;
            }
        }
        return halfHeight;
    }
}
