package com.my.target;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;

/* JADX INFO: compiled from: CloseableLayout.java */
/* JADX INFO: loaded from: classes2.dex */
public class bv extends FrameLayout {
    public static final int iu = 50;
    private static final int iv = 30;
    private static final int iw = 8;

    @NonNull
    private final ck ca;
    private final int iA;
    private final int iB;

    @NonNull
    private final Rect iC;

    @NonNull
    private final Rect iD;

    @NonNull
    private final Rect iE;

    @NonNull
    private final Rect iF;

    @Nullable
    private a iG;
    private boolean iH;
    private boolean iI;
    private int iJ;
    private final int ix;

    @NonNull
    private final BitmapDrawable iy;
    private final int iz;

    /* JADX INFO: compiled from: CloseableLayout.java */
    public interface a {
        void onClose();
    }

    public bv(@NonNull Context context) {
        super(context);
        this.iC = new Rect();
        this.iD = new Rect();
        this.iE = new Rect();
        this.iF = new Rect();
        this.iJ = 8388661;
        this.ca = ck.x(context);
        this.iy = new BitmapDrawable(bq.h(this.ca.l(30)));
        this.iy.setState(EMPTY_STATE_SET);
        this.iy.setCallback(this);
        this.ix = ViewConfiguration.get(context).getScaledTouchSlop();
        this.iz = ck.a(50, context);
        this.iA = ck.a(30, context);
        this.iB = ck.a(8, context);
        setWillNotDraw(false);
    }

    public void a(int i, Rect rect, Rect rect2) {
        int i2 = this.iA;
        Gravity.apply(i, i2, i2, rect, rect2);
    }

    public void setOnCloseListener(@Nullable a aVar) {
        this.iG = aVar;
    }

    public void setCloseVisible(boolean z) {
        if (this.iy.setVisible(z, false)) {
            invalidate(this.iD);
        }
    }

    @Override // android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!b((int) motionEvent.getX(), (int) motionEvent.getY(), this.ix)) {
            super.onTouchEvent(motionEvent);
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            this.iI = true;
        } else if (action != 1) {
            if (action == 3) {
                this.iI = false;
            }
        } else if (this.iI) {
            aZ();
            this.iI = false;
        }
        return true;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.iH) {
            this.iH = false;
            this.iC.set(0, 0, getWidth(), getHeight());
            b(this.iz, this.iC, this.iD);
            this.iF.set(this.iD);
            Rect rect = this.iF;
            int i = this.iB;
            rect.inset(i, i);
            b(this.iA, this.iF, this.iE);
            this.iy.setBounds(this.iE);
        }
        if (this.iy.isVisible()) {
            this.iy.draw(canvas);
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0) {
            return false;
        }
        return b((int) motionEvent.getX(), (int) motionEvent.getY(), 0);
    }

    public boolean aY() {
        return this.iy.isVisible();
    }

    public void setCloseGravity(int i) {
        this.iJ = i;
    }

    @VisibleForTesting
    void setCloseBounds(@NonNull Rect rect) {
        this.iD.set(rect);
    }

    @VisibleForTesting
    boolean b(int i, int i2, int i3) {
        return i >= this.iD.left - i3 && i2 >= this.iD.top - i3 && i < this.iD.right + i3 && i2 < this.iD.bottom + i3;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.iH = true;
    }

    private void b(int i, Rect rect, Rect rect2) {
        Gravity.apply(this.iJ, i, i, rect, rect2);
    }

    private void aZ() {
        playSoundEffect(0);
        a aVar = this.iG;
        if (aVar != null) {
            aVar.onClose();
        }
    }
}
