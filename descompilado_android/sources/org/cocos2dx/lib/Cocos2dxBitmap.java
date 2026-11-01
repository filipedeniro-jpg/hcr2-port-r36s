package org.cocos2dx.lib;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes3.dex */
public final class Cocos2dxBitmap {
    private static final int HORIZONTAL_ALIGN_CENTER = 3;
    private static final int HORIZONTAL_ALIGN_LEFT = 1;
    private static final int HORIZONTAL_ALIGN_RIGHT = 2;
    private static final int VERTICAL_ALIGN_BOTTOM = 2;
    private static final int VERTICAL_ALIGN_CENTER = 3;
    private static final int VERTICAL_ALIGN_TOP = 1;
    private static Context sContext;

    private static native void nativeInitBitmapDC(int i, int i2, byte[] bArr);

    public static void setContext(Context context) {
        sContext = context;
    }

    public static boolean createTextBitmapShadowStroke(byte[] bArr, String str, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z, float f, float f2, float f3, float f4, boolean z2, int i9, int i10, int i11, int i12, float f5) {
        int i13;
        int i14 = 0;
        if (bArr != null && bArr.length != 0) {
            String str2 = new String(bArr);
            Layout.Alignment alignment = Layout.Alignment.ALIGN_NORMAL;
            int i15 = i6 & 15;
            if (i15 != 1) {
                if (i15 == 2) {
                    alignment = Layout.Alignment.ALIGN_OPPOSITE;
                } else if (i15 == 3) {
                    alignment = Layout.Alignment.ALIGN_CENTER;
                }
            }
            Layout.Alignment alignment2 = alignment;
            TextPaint textPaintNewPaint = newPaint(str, i);
            if (z2) {
                textPaintNewPaint.setStyle(Paint.Style.STROKE);
                textPaintNewPaint.setStrokeWidth(f5);
            }
            StaticLayout staticLayout = new StaticLayout(str2, textPaintNewPaint, i7 <= 0 ? (int) Math.ceil(StaticLayout.getDesiredWidth(str2, textPaintNewPaint)) : i7, alignment2, 1.0f, 0.0f, false);
            int width = staticLayout.getWidth();
            int lineTop = staticLayout.getLineTop(staticLayout.getLineCount());
            int iMax = Math.max(width, i7);
            int i16 = i8 > 0 ? i8 : lineTop;
            if (iMax != 0 && i16 != 0) {
                if (i15 == 3) {
                    i13 = (iMax - width) / 2;
                } else {
                    i13 = i15 == 2 ? iMax - width : 0;
                }
                int i17 = (i6 >> 4) & 15;
                if (i17 == 2) {
                    i14 = i16 - lineTop;
                } else if (i17 == 3) {
                    i14 = (i16 - lineTop) / 2;
                }
                Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iMax, i16, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmapCreateBitmap);
                canvas.translate(i13, i14);
                if (z2) {
                    textPaintNewPaint.setARGB(i12, i9, i10, i11);
                    staticLayout.draw(canvas);
                }
                textPaintNewPaint.setStyle(Paint.Style.FILL);
                textPaintNewPaint.setARGB(i5, i2, i3, i4);
                staticLayout.draw(canvas);
                initNativeObject(bitmapCreateBitmap);
                return true;
            }
        }
        return false;
    }

    private static TextPaint newPaint(String str, int i) {
        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(i);
        textPaint.setAntiAlias(true);
        if (str.endsWith(".ttf")) {
            try {
                textPaint.setTypeface(Cocos2dxTypefaces.get(sContext, str));
            } catch (Exception unused) {
                Log.e("Cocos2dxBitmap", "error to create ttf type face: " + str);
                textPaint.setTypeface(Typeface.create(str, 0));
            }
        } else {
            textPaint.setTypeface(Typeface.create(str, 0));
        }
        return textPaint;
    }

    private static void initNativeObject(Bitmap bitmap) {
        byte[] pixels = getPixels(bitmap);
        if (pixels == null) {
            return;
        }
        nativeInitBitmapDC(bitmap.getWidth(), bitmap.getHeight(), pixels);
    }

    private static byte[] getPixels(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        byte[] bArr = new byte[bitmap.getWidth() * bitmap.getHeight() * 4];
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.order(ByteOrder.nativeOrder());
        bitmap.copyPixelsToBuffer(byteBufferWrap);
        return bArr;
    }

    private static int getFontSizeAccordingHeight(int i) {
        TextPaint textPaint = new TextPaint();
        Rect rect = new Rect();
        textPaint.setTypeface(Typeface.DEFAULT);
        boolean z = false;
        int i2 = 1;
        while (!z) {
            textPaint.setTextSize(i2);
            textPaint.getTextBounds("SghMNy", 0, 6, rect);
            i2++;
            if (i - rect.height() <= 2) {
                z = true;
            }
        }
        return i2;
    }

    private static String getStringWithEllipsis(String str, float f, float f2) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        TextPaint textPaint = new TextPaint();
        textPaint.setTypeface(Typeface.DEFAULT);
        textPaint.setTextSize(f2);
        return TextUtils.ellipsize(str, textPaint, f, TextUtils.TruncateAt.END).toString();
    }
}
