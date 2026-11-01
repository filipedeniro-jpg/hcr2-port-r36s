package com.my.target;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: BorderedTextView.java */
/* JADX INFO: loaded from: classes2.dex */
@SuppressLint({"AppCompatCustomView"})
public class bu extends TextView {
    public static final int iq = -13421773;
    private static final int ir = 2;

    @NonNull
    private final GradientDrawable is;
    private final int it;

    public bu(@NonNull Context context) {
        this(context, null);
    }

    public bu(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public bu(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.is = new GradientDrawable();
        this.is.setStroke(0, iq);
        this.is.setColor(0);
        this.it = (int) TypedValue.applyDimension(1, 2.0f, context.getResources().getDisplayMetrics());
    }

    public void c(int i, int i2) {
        a(i, i2, 0);
    }

    public void a(int i, int i2, int i3) {
        this.is.setStroke(i, i2);
        this.is.setCornerRadius(i3);
        invalidate();
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        this.is.setColor(i);
        invalidate();
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        this.is.setBounds(getPaddingLeft() - this.it, getPaddingTop(), getWidth(), getHeight());
        this.is.draw(canvas);
        super.onDraw(canvas);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setMeasuredDimension(getMeasuredWidth() + (this.it * 2), getMeasuredHeight());
    }
}
