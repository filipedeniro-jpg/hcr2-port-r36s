package com.inmobi.rendering;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;
import androidx.core.view.ViewCompat;

/* JADX INFO: loaded from: classes2.dex */
public class CustomView extends View {
    private float a;
    private float b;
    private float c;
    private float d;
    private float e;
    private int f;
    private int g;
    private Paint h;
    private Path i;
    private RectF j;

    private CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, float f, int i) {
        this(context);
        this.f = i;
        this.a = f;
        this.g = 15;
        this.h = new Paint(1);
        this.j = new RectF();
        this.i = new Path();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.h.reset();
        switch (this.f) {
            case 0:
                float f = this.a;
                float f2 = (50.0f * f) / 2.0f;
                float f3 = (f * 30.0f) / 2.0f;
                float f4 = f3 / 3.0f;
                float f5 = f2 - f4;
                float f6 = f2 + f4;
                this.h.setAntiAlias(true);
                this.h.setColor(ViewCompat.MEASURED_STATE_MASK);
                this.h.setStrokeWidth(3.0f);
                this.h.setStyle(Paint.Style.FILL_AND_STROKE);
                canvas.drawCircle(f2, f2, f3, this.h);
                this.h.setColor(-1);
                this.h.setStyle(Paint.Style.STROKE);
                canvas.drawLine(f5, f5, f6, f6, this.h);
                canvas.drawLine(f5, f6, f6, f5, this.h);
                canvas.drawCircle(f2, f2, f3, this.h);
                break;
            case 1:
                float f7 = (this.a * 50.0f) / 2.0f;
                this.h.setAntiAlias(true);
                this.h.setColor(0);
                this.h.setStrokeWidth(3.0f);
                this.h.setStyle(Paint.Style.FILL_AND_STROKE);
                canvas.drawCircle(f7, f7, f7, this.h);
                break;
            case 2:
                this.h.setAntiAlias(true);
                this.h.setColor(-1);
                this.h.setStrokeWidth(5.0f);
                this.h.setStyle(Paint.Style.STROKE);
                canvas.drawLine((getWidth() / 2) - ((this.g * this.a) / 2.0f), (getHeight() / 2) - ((this.g * this.a) / 2.0f), ((this.g * this.a) / 2.0f) + (getWidth() / 2), ((this.g * this.a) / 2.0f) + (getHeight() / 2), this.h);
                canvas.drawLine((getWidth() / 2) - ((this.g * this.a) / 2.0f), ((this.g * this.a) / 2.0f) + (getHeight() / 2), ((this.g * this.a) / 2.0f) + (getWidth() / 2), (getHeight() / 2) - ((this.g * this.a) / 2.0f), this.h);
                break;
            case 3:
                float f8 = this.a;
                float f9 = (50.0f * f8) / 2.0f;
                float f10 = (f8 * 30.0f) / 2.0f;
                this.i.reset();
                this.h.setAntiAlias(true);
                this.h.setColor(ViewCompat.MEASURED_STATE_MASK);
                this.h.setStrokeWidth(3.0f);
                this.h.setStyle(Paint.Style.FILL_AND_STROKE);
                canvas.drawCircle(f9, f9, f10, this.h);
                this.h.setColor(-1);
                this.h.setStyle(Paint.Style.STROKE);
                canvas.drawCircle(f9, f9, f10, this.h);
                this.j.set((getWidth() / 2) - ((this.g * this.a) / 2.0f), (getHeight() / 2) - ((this.g * this.a) / 2.0f), (getWidth() / 2) + ((this.g * this.a) / 2.0f), (getHeight() / 2) + ((this.g * this.a) / 2.0f));
                canvas.drawArc(this.j, 0.0f, 270.0f, false, this.h);
                this.i.setFillType(Path.FillType.EVEN_ODD);
                this.i.moveTo((getWidth() / 2) + ((this.g * this.a) / 2.0f), (getHeight() / 2) - (this.a * 2.0f));
                Path path = this.i;
                float width = getWidth() / 2;
                float f11 = this.g;
                float f12 = this.a;
                path.lineTo((width + ((f11 * f12) / 2.0f)) - (f12 * 2.0f), getHeight() / 2);
                Path path2 = this.i;
                float width2 = getWidth() / 2;
                float f13 = this.g;
                float f14 = this.a;
                path2.lineTo(width2 + ((f13 * f14) / 2.0f) + (f14 * 2.0f), getHeight() / 2);
                this.i.lineTo((getWidth() / 2) + ((this.g * this.a) / 2.0f), (getHeight() / 2) - (this.a * 2.0f));
                this.i.close();
                this.h.setStyle(Paint.Style.FILL_AND_STROKE);
                canvas.drawPath(this.i, this.h);
                break;
            case 4:
                this.i.reset();
                this.i.setFillType(Path.FillType.EVEN_ODD);
                this.i.moveTo((getWidth() / 2) - ((this.g * this.a) / 2.0f), getHeight() / 2);
                this.i.lineTo((getWidth() / 2) + ((this.g * this.a) / 2.0f), (getHeight() / 2) - ((this.g * this.a) / 2.0f));
                this.i.lineTo((getWidth() / 2) + ((this.g * this.a) / 2.0f), (getHeight() / 2) + ((this.g * this.a) / 2.0f));
                this.i.lineTo((getWidth() / 2) - ((this.g * this.a) / 2.0f), getHeight() / 2);
                this.i.close();
                this.h.setAntiAlias(true);
                this.h.setColor(ViewCompat.MEASURED_STATE_MASK);
                this.h.setStrokeWidth(3.0f);
                this.h.setStyle(Paint.Style.FILL_AND_STROKE);
                canvas.drawPath(this.i, this.h);
                break;
            case 5:
                this.i.reset();
                this.i.setFillType(Path.FillType.EVEN_ODD);
                this.i.moveTo((getWidth() / 2) - ((this.g * this.a) / 2.0f), (getHeight() / 2) - ((this.g * this.a) / 2.0f));
                this.i.lineTo((getWidth() / 2) + ((this.g * this.a) / 2.0f), getHeight() / 2);
                this.i.lineTo((getWidth() / 2) - ((this.g * this.a) / 2.0f), (getHeight() / 2) + ((this.g * this.a) / 2.0f));
                this.i.lineTo((getWidth() / 2) - ((this.g * this.a) / 2.0f), (getHeight() / 2) - ((this.g * this.a) / 2.0f));
                this.i.close();
                this.h.setAntiAlias(true);
                this.h.setColor(ViewCompat.MEASURED_STATE_MASK);
                this.h.setStrokeWidth(3.0f);
                this.h.setStyle(Paint.Style.FILL_AND_STROKE);
                canvas.drawPath(this.i, this.h);
                break;
            case 6:
                this.i.reset();
                this.i.setFillType(Path.FillType.EVEN_ODD);
                this.i.moveTo((getWidth() / 2) - ((this.g * this.a) / 2.0f), (getHeight() / 2) - ((this.g * this.a) / 2.0f));
                this.i.lineTo((getWidth() / 2) + ((this.g * this.a) / 2.0f), getHeight() / 2);
                this.i.lineTo((getWidth() / 2) - ((this.g * this.a) / 2.0f), (getHeight() / 2) + ((this.g * this.a) / 2.0f));
                this.i.lineTo((getWidth() / 2) - ((this.g * this.a) / 2.0f), (getHeight() / 2) - ((this.g * this.a) / 2.0f));
                this.i.close();
                this.h.setAntiAlias(true);
                this.h.setColor(-12303292);
                this.h.setStrokeWidth(3.0f);
                this.h.setStyle(Paint.Style.FILL_AND_STROKE);
                canvas.drawPath(this.i, this.h);
                break;
            case 7:
                b(canvas);
                float f15 = this.e;
                this.b = f15 / 3.0f;
                this.c = f15 / 3.0f;
                this.h.setStyle(Paint.Style.FILL);
                Path path3 = this.i;
                float f16 = this.d;
                path3.moveTo(this.b + f16, f16);
                Path path4 = this.i;
                float f17 = this.d;
                path4.lineTo(f17 - this.b, f17 - this.c);
                Path path5 = this.i;
                float f18 = this.d;
                path5.lineTo(f18 - this.b, f18 + this.c);
                Path path6 = this.i;
                float f19 = this.d;
                path6.lineTo(this.b + f19, f19);
                canvas.drawPath(this.i, this.h);
                break;
            case 8:
                b(canvas);
                float f20 = this.e;
                this.b = f20 / 4.0f;
                this.c = f20 / 3.0f;
                float f21 = this.d;
                float f22 = this.b;
                float f23 = this.c;
                canvas.drawLine(f21 - f22, f21 - f23, f21 - f22, f21 + f23, this.h);
                float f24 = this.d;
                float f25 = this.b;
                float f26 = this.c;
                canvas.drawLine(f24 + f25, f24 - f26, f24 + f25, f24 + f26, this.h);
                break;
            case 9:
                a(canvas);
                float f27 = this.d;
                float f28 = this.a;
                float f29 = this.c;
                RectF rectF = new RectF(f27 - (f28 * 10.0f), (f27 - f29) - (f28 * 2.0f), (14.0f * f28) + f27, f27 + f29 + (f28 * 2.0f));
                float f30 = this.d;
                float f31 = this.a;
                float f32 = this.c;
                RectF rectF2 = new RectF(f30 - (10.0f * f31), (f30 - f32) - (f31 * 4.0f), (18.0f * f31) + f30, f30 + f32 + (f31 * 4.0f));
                this.h.setColor(-1);
                this.h.setStrokeWidth(4.0f);
                this.h.setStyle(Paint.Style.STROKE);
                canvas.drawArc(rectF, -45.0f, 90.0f, false, this.h);
                canvas.drawArc(rectF2, -45.0f, 90.0f, false, this.h);
                canvas.drawPath(this.i, this.h);
                canvas.drawPath(this.i, this.h);
                break;
            case 11:
                a(canvas);
                this.h.setColor(-1);
                this.h.setStrokeWidth(4.0f);
                this.h.setStyle(Paint.Style.STROKE);
                Path path7 = this.i;
                float f33 = this.d;
                path7.moveTo((this.a * 10.0f) + f33, f33 - this.c);
                Path path8 = this.i;
                float f34 = this.d;
                path8.lineTo((this.a * 18.0f) + f34, f34 + this.c);
                Path path9 = this.i;
                float f35 = this.d;
                path9.moveTo((this.a * 18.0f) + f35, f35 - this.c);
                Path path10 = this.i;
                float f36 = this.d;
                path10.lineTo((this.a * 10.0f) + f36, f36 + this.c);
                canvas.drawPath(this.i, this.h);
                break;
            case 12:
                float f37 = this.a;
                this.d = (50.0f * f37) / 2.0f;
                this.b = f37 * 3.0f;
                this.c = f37 * 3.0f;
                this.h.setStyle(Paint.Style.STROKE);
                this.h.setStrokeWidth(4.0f);
                this.h.setColor(-1);
                Path path11 = this.i;
                float f38 = this.d;
                path11.moveTo(f38 - this.b, (f38 - this.c) - (this.a * 5.0f));
                Path path12 = this.i;
                float f39 = this.d;
                path12.lineTo(f39 - this.b, f39 - this.c);
                Path path13 = this.i;
                float f40 = this.d;
                path13.lineTo((f40 - this.b) - (this.a * 5.0f), f40 - this.c);
                Path path14 = this.i;
                float f41 = this.d;
                path14.moveTo(this.b + f41, (f41 - this.c) - (this.a * 5.0f));
                Path path15 = this.i;
                float f42 = this.d;
                path15.lineTo(this.b + f42, f42 - this.c);
                Path path16 = this.i;
                float f43 = this.d;
                path16.lineTo(this.b + f43 + (this.a * 5.0f), f43 - this.c);
                Path path17 = this.i;
                float f44 = this.d;
                path17.moveTo(f44 - this.b, f44 + this.c + (this.a * 5.0f));
                Path path18 = this.i;
                float f45 = this.d;
                path18.lineTo(f45 - this.b, f45 + this.c);
                Path path19 = this.i;
                float f46 = this.d;
                path19.lineTo((f46 - this.b) - (this.a * 5.0f), f46 + this.c);
                Path path20 = this.i;
                float f47 = this.d;
                path20.moveTo(this.b + f47, f47 + this.c + (this.a * 5.0f));
                Path path21 = this.i;
                float f48 = this.d;
                path21.lineTo(this.b + f48, f48 + this.c);
                Path path22 = this.i;
                float f49 = this.d;
                path22.lineTo(this.b + f49 + (this.a * 5.0f), f49 + this.c);
                canvas.drawPath(this.i, this.h);
                break;
        }
    }

    private void a(Canvas canvas) {
        float f = this.a;
        this.d = ((30.0f * f) / 2.0f) - (f * 5.0f);
        this.b = f * 5.0f;
        this.c = f * 5.0f;
        this.h.setStyle(Paint.Style.FILL);
        this.h.setColor(-1);
        this.h.setStrokeWidth(4.0f);
        this.h.setAntiAlias(true);
        Path path = this.i;
        float f2 = this.d;
        path.moveTo(f2 - this.b, f2 - this.c);
        Path path2 = this.i;
        float f3 = this.d;
        path2.lineTo(f3, f3 - this.c);
        Path path3 = this.i;
        float f4 = this.d;
        float f5 = this.a;
        path3.lineTo((f5 * 6.0f) + f4, (f4 - this.c) - (f5 * 4.0f));
        Path path4 = this.i;
        float f6 = this.d;
        float f7 = this.a;
        path4.lineTo((6.0f * f7) + f6, f6 + this.c + (f7 * 4.0f));
        Path path5 = this.i;
        float f8 = this.d;
        path5.lineTo(f8, this.c + f8);
        Path path6 = this.i;
        float f9 = this.d;
        path6.lineTo(f9 - this.b, f9 + this.c);
        Path path7 = this.i;
        float f10 = this.d;
        path7.lineTo(f10 - this.b, f10 - this.c);
        canvas.drawPath(this.i, this.h);
    }

    private void b(Canvas canvas) {
        float f = this.a;
        this.e = 25.0f * f;
        this.d = f * 30.0f;
        this.h.setAntiAlias(true);
        this.h.setColor(-1);
        this.h.setStrokeWidth(7.0f);
        this.h.setStyle(Paint.Style.STROKE);
        float f2 = this.d;
        canvas.drawCircle(f2, f2, this.e, this.h);
    }

    public void setSwitchInt(int i) {
        this.f = i;
    }
}
