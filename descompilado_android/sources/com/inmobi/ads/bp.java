package com.inmobi.ads;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/* JADX INFO: compiled from: ScrollableDeckPagesContainer.java */
/* JADX INFO: loaded from: classes2.dex */
class bp extends NativeScrollableContainer implements ViewPager.OnPageChangeListener {
    private static final String b = "bp";

    @Nullable
    NativeScrollableContainer.a a;
    private ViewPager c;
    private Point d;
    private Point e;
    private boolean f;
    private boolean g;

    public bp(Context context) {
        super(context, 0);
        this.d = new Point();
        this.e = new Point();
        this.g = false;
        setClipChildren(false);
        setLayerType(1, null);
        this.c = new ViewPager(getContext());
        this.c.addOnPageChangeListener(this);
        addView(this.c);
    }

    @Override // com.inmobi.ads.NativeScrollableContainer
    public final void a(@NonNull am amVar, @NonNull ax axVar, int i, int i2, @NonNull NativeScrollableContainer.a aVar) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) NativeViewFactory.a(amVar.a(0), this);
        if (Build.VERSION.SDK_INT >= 17) {
            layoutParams.setMarginStart(20);
            layoutParams.setMarginEnd(20);
        } else {
            layoutParams.setMargins(20, 0, 20, 0);
        }
        layoutParams.gravity = i2;
        this.c.setLayoutParams(layoutParams);
        this.c.setAdapter((ap) axVar);
        this.c.setOffscreenPageLimit(2);
        this.c.setPageMargin(16);
        this.c.setCurrentItem(i);
        this.a = aVar;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        Point point = this.d;
        point.x = i / 2;
        point.y = i2 / 2;
    }

    @Override // android.view.View
    public boolean onTouchEvent(@NonNull MotionEvent motionEvent) {
        double dCeil;
        double dCeil2;
        int action = motionEvent.getAction();
        if (action == 0) {
            this.e.x = (int) motionEvent.getX();
            this.e.y = (int) motionEvent.getY();
        } else {
            if (action == 1) {
                float f = this.e.x;
                float x = motionEvent.getX();
                int currentItem = this.c.getCurrentItem();
                int count = this.c.getAdapter().getCount();
                int width = this.c.getWidth();
                int width2 = getWidth();
                int i = 0;
                if (currentItem == 0 || count - 1 == currentItem) {
                    int i2 = width2 - width;
                    if (currentItem == 0) {
                        float f2 = i2;
                        if (f > f2 && x > f2) {
                            dCeil2 = Math.ceil((x - f2) / width);
                            i = (int) dCeil2;
                        }
                    } else {
                        float f3 = i2;
                        if (f < f3 && x < f3) {
                            dCeil = Math.ceil((f3 - x) / width);
                            i = -((int) dCeil);
                        }
                    }
                } else {
                    float f4 = (width2 - width) / 2;
                    if (f < f4 && x < f4) {
                        dCeil = Math.ceil((f4 - x) / width);
                        i = -((int) dCeil);
                    } else {
                        float f5 = (width2 + width) / 2;
                        if (f > f5 && x > f5) {
                            dCeil2 = Math.ceil((x - f5) / width);
                            i = (int) dCeil2;
                        }
                    }
                }
                if (i != 0) {
                    motionEvent.setAction(3);
                    ViewPager viewPager = this.c;
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + i);
                }
                motionEvent.offsetLocation(this.d.x - this.e.x, this.d.y - this.e.y);
            }
            return this.c.dispatchTouchEvent(motionEvent);
        }
        motionEvent.offsetLocation(this.d.x - this.e.x, this.d.y - this.e.y);
        return this.c.dispatchTouchEvent(motionEvent);
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
        if (this.f) {
            invalidate();
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.c.getLayoutParams();
        NativeScrollableContainer.a aVar = this.a;
        if (aVar != null) {
            layoutParams.gravity = aVar.a(i);
            this.c.requestLayout();
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
        this.f = i != 0;
    }
}
