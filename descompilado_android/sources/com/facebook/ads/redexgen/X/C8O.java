package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.widget.ImageView;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.8O, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C8O {
    private static boolean B = true;

    private C8O() {
    }

    @Nullable
    public static BitmapDrawable B(Context context, String mediationData) {
        byte[] bArrDecode = Base64.decode(mediationData, 0);
        Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArrDecode, 0, bArrDecode.length);
        if (bitmapDecodeByteArray == null || (B && !D(context, bitmapDecodeByteArray))) {
            return null;
        }
        BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmapDecodeByteArray);
        bitmapDrawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        Resources resources = context.getResources();
        if (resources != null) {
            DisplayMetrics displayMetrics = resources.getDisplayMetrics();
            if (displayMetrics != null) {
                bitmapDrawable.setTargetDensity(displayMetrics.densityDpi);
                return bitmapDrawable;
            }
            E(context, "DisplayMetrics is null.");
            return bitmapDrawable;
        }
        E(context, "Resources is null.");
        return bitmapDrawable;
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [com.facebook.ads.redexgen.X.8N] */
    @Nullable
    public static C8N C(final Context context, @Nullable String mediationData) {
        BitmapDrawable bitmapDrawableB;
        try {
            if (!TextUtils.isEmpty(mediationData) && (bitmapDrawableB = B(context, mediationData)) != null) {
                ?? r3 = new ImageView(context) { // from class: com.facebook.ads.redexgen.X.8N
                };
                if (Build.VERSION.SDK_INT >= 16) {
                    r3.setBackground(bitmapDrawableB);
                } else {
                    r3.setBackgroundDrawable(bitmapDrawableB);
                }
                r3.setClickable(false);
                r3.setFocusable(false);
                return r3;
            }
            return null;
        } catch (Throwable th) {
            C0585Hx.D(context, "mol", C0584Hw.aB, new C0583Hv(th));
            return null;
        }
    }

    @VisibleForTesting
    private static boolean D(Context context, Bitmap bitmap) {
        for (int x = 0; x < bitmap.getWidth(); x++) {
            for (int i = 0; i < y; i++) {
                int y = bitmap.getPixel(x, i);
                if (Color.alpha(y) / 255.0f > 0.03f) {
                    E(context, "Validation failed.");
                    return false;
                }
            }
        }
        return true;
    }

    private static void E(Context context, String message) {
        C0585Hx.D(context, "mol", C0584Hw.aB, new C0583Hv(message));
    }
}
