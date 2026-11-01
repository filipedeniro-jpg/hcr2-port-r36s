package net.nend.android.internal.ui.views.formats;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

/* JADX INFO: loaded from: classes2.dex */
public class RoundedImageView extends ImageView {
    Bitmap a;
    float b;

    public RoundedImageView(Context context) {
        this(context, null);
    }

    public RoundedImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = 10.0f;
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        setRoundBitmap(((BitmapDrawable) drawable).getBitmap());
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i) {
        setRoundBitmap(BitmapFactory.decodeResource(getResources(), i));
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        setRoundBitmap(bitmap);
    }

    private void setRoundBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        canvas.drawARGB(0, 0, 0, 0);
        RectF rectF = new RectF(0.0f, 0.0f, width, height);
        float f = this.b;
        canvas.drawRoundRect(rectF, f, f, new Paint(1));
        Bitmap bitmap2 = this.a;
        if (bitmap2 != null) {
            bitmap2.recycle();
            this.a = null;
        }
        this.a = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas2 = new Canvas(this.a);
        Paint paint = new Paint();
        canvas2.drawBitmap(bitmapCreateBitmap, 0.0f, 0.0f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas2.drawBitmap(bitmap, new Rect(0, 0, width, height), new Rect(0, 0, width, height), paint);
        bitmapCreateBitmap.recycle();
        super.setImageDrawable(new BitmapDrawable(getContext().getResources(), this.a));
    }

    @Override // android.widget.ImageView, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        super.setImageDrawable(null);
        if (Build.VERSION.SDK_INT >= 16) {
            setBackground(null);
        } else {
            setBackgroundDrawable(null);
        }
        destroyDrawingCache();
        Bitmap bitmap = this.a;
        if (bitmap != null) {
            bitmap.recycle();
            this.a = null;
        }
    }
}
