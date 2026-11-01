package com.chartboost.sdk.impl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public abstract class az extends RelativeLayout {
    private final Rect a;
    final a c;
    protected Button d;
    boolean e;

    protected abstract void a(MotionEvent motionEvent);

    public az(Context context) {
        this(context, null);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public az(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = new Rect();
        this.d = null;
        this.e = true;
        this.c = new a(getContext());
        this.c.setOnTouchListener(new View.OnTouchListener() { // from class: com.chartboost.sdk.impl.az.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                boolean zA = az.this.a(view, motionEvent);
                int actionMasked = motionEvent.getActionMasked();
                if (actionMasked == 0) {
                    az.this.c.a(zA);
                    return zA;
                }
                if (actionMasked == 1) {
                    if (az.this.getVisibility() == 0 && az.this.isEnabled() && zA) {
                        az.this.a(motionEvent);
                    }
                    az.this.c.a(false);
                } else if (actionMasked == 2) {
                    az.this.c.a(zA);
                } else if (actionMasked == 3 || actionMasked == 4) {
                    az.this.c.a(false);
                }
                return true;
            }
        });
        addView(this.c, new RelativeLayout.LayoutParams(-1, -1));
    }

    boolean a(View view, MotionEvent motionEvent) {
        view.getLocalVisibleRect(this.a);
        this.a.left += view.getPaddingLeft();
        this.a.top += view.getPaddingTop();
        this.a.right -= view.getPaddingRight();
        this.a.bottom -= view.getPaddingBottom();
        return this.a.contains(Math.round(motionEvent.getX()), Math.round(motionEvent.getY()));
    }

    private class a extends ay {
        private boolean c;

        public a(Context context) {
            super(context);
            this.c = false;
            this.c = false;
        }

        @Override // android.view.View
        public boolean performClick() {
            super.performClick();
            return true;
        }

        protected void a(boolean z) {
            if (az.this.e && z) {
                if (this.c) {
                    return;
                }
                if (getDrawable() != null) {
                    getDrawable().setColorFilter(1996488704, PorterDuff.Mode.SRC_ATOP);
                } else if (getBackground() != null) {
                    getBackground().setColorFilter(1996488704, PorterDuff.Mode.SRC_ATOP);
                }
                invalidate();
                this.c = true;
                return;
            }
            if (this.c) {
                if (getDrawable() != null) {
                    getDrawable().clearColorFilter();
                } else if (getBackground() != null) {
                    getBackground().clearColorFilter();
                }
                invalidate();
                this.c = false;
            }
        }
    }

    public void a(String str) {
        if (str != null) {
            a().setText(str);
            addView(a(), new RelativeLayout.LayoutParams(-1, -1));
            this.c.setVisibility(8);
            a(false);
            this.d.setOnClickListener(new View.OnClickListener() { // from class: com.chartboost.sdk.impl.az.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    az.this.a((MotionEvent) null);
                }
            });
            return;
        }
        if (this.d != null) {
            removeView(a());
            this.d = null;
            this.c.setVisibility(0);
            a(true);
        }
    }

    public TextView a() {
        if (this.d == null) {
            this.d = new Button(getContext());
            this.d.setGravity(17);
        }
        this.d.postInvalidate();
        return this.d;
    }

    public void a(com.chartboost.sdk.Libraries.h hVar) {
        if (hVar == null || !hVar.c()) {
            return;
        }
        this.c.a(hVar);
        a((String) null);
    }

    public void a(ImageView.ScaleType scaleType) {
        this.c.setScaleType(scaleType);
    }

    public void a(boolean z) {
        this.e = z;
    }
}
