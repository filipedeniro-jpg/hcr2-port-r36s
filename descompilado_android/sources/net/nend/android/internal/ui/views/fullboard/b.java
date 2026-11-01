package net.nend.android.internal.ui.views.fullboard;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

/* JADX INFO: compiled from: CardViewBaseImpl.java */
/* JADX INFO: loaded from: classes2.dex */
class b implements d {
    private final RectF a = new RectF();

    b() {
    }

    @Override // net.nend.android.internal.ui.views.fullboard.d
    public void a() {
        f.a = new f.a() { // from class: net.nend.android.internal.ui.views.fullboard.b.1
            @Override // net.nend.android.internal.ui.views.fullboard.f.a
            public void a(Canvas canvas, RectF rectF, float f, Paint paint) {
                float f2 = 2.0f * f;
                float fWidth = (rectF.width() - f2) - 1.0f;
                float fHeight = (rectF.height() - f2) - 1.0f;
                if (f >= 1.0f) {
                    float f3 = f + 0.5f;
                    float f4 = -f3;
                    b.this.a.set(f4, f4, f3, f3);
                    int iSave = canvas.save();
                    canvas.translate(rectF.left + f3, rectF.top + f3);
                    canvas.drawArc(b.this.a, 180.0f, 90.0f, true, paint);
                    canvas.translate(fWidth, 0.0f);
                    canvas.rotate(90.0f);
                    canvas.drawArc(b.this.a, 180.0f, 90.0f, true, paint);
                    canvas.translate(fHeight, 0.0f);
                    canvas.rotate(90.0f);
                    canvas.drawArc(b.this.a, 180.0f, 90.0f, true, paint);
                    canvas.translate(fWidth, 0.0f);
                    canvas.rotate(90.0f);
                    canvas.drawArc(b.this.a, 180.0f, 90.0f, true, paint);
                    canvas.restoreToCount(iSave);
                    canvas.drawRect((rectF.left + f3) - 1.0f, rectF.top, (rectF.right - f3) + 1.0f, rectF.top + f3, paint);
                    canvas.drawRect((rectF.left + f3) - 1.0f, rectF.bottom - f3, (rectF.right - f3) + 1.0f, rectF.bottom, paint);
                }
                canvas.drawRect(rectF.left, rectF.top + f, rectF.right, rectF.bottom - f, paint);
            }
        };
    }

    @Override // net.nend.android.internal.ui.views.fullboard.d
    public void a(c cVar, Context context, ColorStateList colorStateList, float f, float f2, float f3) {
        f fVar = new f(context.getResources(), colorStateList, f, f2, f3);
        fVar.a(cVar.a());
        cVar.a(fVar);
        Rect rect = new Rect();
        fVar.a(rect);
        cVar.a(rect.left, rect.top, rect.right, rect.bottom);
    }
}
