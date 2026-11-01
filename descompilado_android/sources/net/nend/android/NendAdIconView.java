package net.nend.android;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import java.util.Timer;
import java.util.TimerTask;
import net.nend.android.internal.ui.views.b;
import net.nend.android.internal.utilities.c;
import net.nend.android.internal.utilities.d;
import net.nend.android.internal.utilities.g;
import net.nend.android.internal.utilities.j;
import net.nend.android.internal.utilities.n;

/* JADX INFO: loaded from: classes2.dex */
public class NendAdIconView extends LinearLayout implements b.a {
    private float a;
    private int b;
    private boolean c;
    private boolean d;
    private int e;
    private int f;
    private Bitmap g;
    private Rect h;
    private Rect i;
    private int j;
    private int k;
    private int l;
    private Handler m;
    private float n;
    private net.nend.android.internal.a o;
    private String p;
    private a q;
    private b r;
    private TextView s;
    private Runnable t;

    interface a {
        void onClick(View view);

        void onClickInformation(View view);

        void onFailedToReceive(NendIconError nendIconError);

        void onReceive(View view);

        void onWindowFocusChanged(boolean z);
    }

    @Override // net.nend.android.internal.ui.views.b.a
    public boolean onValidation(int i, int i2) {
        return true;
    }

    static /* synthetic */ int f(NendAdIconView nendAdIconView) {
        int i = nendAdIconView.l;
        nendAdIconView.l = i + 1;
        return i;
    }

    static /* synthetic */ int i(NendAdIconView nendAdIconView) {
        int i = nendAdIconView.l;
        nendAdIconView.l = i - 1;
        return i;
    }

    public NendAdIconView(Context context) {
        super(context);
        this.c = true;
        this.d = true;
        this.e = ViewCompat.MEASURED_STATE_MASK;
        this.g = null;
        this.m = new Handler();
        this.t = new Runnable() { // from class: net.nend.android.NendAdIconView.1
            @Override // java.lang.Runnable
            public void run() {
                NendAdIconView nendAdIconView = NendAdIconView.this;
                nendAdIconView.a(nendAdIconView.s);
            }
        };
        a(context);
    }

    public NendAdIconView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NendAdIconView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        this.c = true;
        this.d = true;
        this.e = ViewCompat.MEASURED_STATE_MASK;
        this.g = null;
        this.m = new Handler();
        this.t = new Runnable() { // from class: net.nend.android.NendAdIconView.1
            @Override // java.lang.Runnable
            public void run() {
                NendAdIconView nendAdIconView = NendAdIconView.this;
                nendAdIconView.a(nendAdIconView.s);
            }
        };
        TypedArray typedArrayA = n.a(context, attributeSet, i);
        this.e = typedArrayA.getColor(n.a(context, "NendTitleColor"), ViewCompat.MEASURED_STATE_MASK);
        this.c = typedArrayA.getBoolean(n.a(context, "NendTitleVisible"), true);
        this.d = typedArrayA.getBoolean(n.a(context, "NendIconSpaceEnabled"), true);
        typedArrayA.recycle();
        a(context);
    }

    private void a(Context context) {
        setOrientation(1);
        this.n = context.getResources().getDisplayMetrics().density;
        this.a = this.n * 3.0f;
        this.h = new Rect();
        this.i = new Rect();
        this.g = net.nend.android.internal.utilities.a.b(context, "nend_information_icon.png");
        this.r = new b(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 1;
        addView(this.r, layoutParams);
        this.s = new TextView(context);
        this.s.setTextColor(this.e);
        this.s.setLines(1);
        this.s.setGravity(17);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams2.gravity = 1;
        addView(this.s, layoutParams2);
    }

    void a(net.nend.android.internal.a aVar, int i) {
        if (aVar == null) {
            f();
            return;
        }
        this.o = aVar;
        this.p = "https://www.nend.net/privacy/optsdkgate?uid=" + c.c(getContext()) + "&spot=" + i;
        this.r.a(aVar, this);
    }

    void a() {
        this.q = null;
        this.r.b();
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        float f = this.n;
        int i3 = (int) (57.0f * f);
        int i4 = (int) (f * 75.0f);
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        if (1073741824 == mode && 1073741824 == mode2 && size != size2) {
            size = Math.min(size, size2);
            size2 = size;
        }
        if (1073741824 != mode) {
            size = this.d ? i4 : i3;
        }
        if (1073741824 == mode2) {
            if (!this.d && this.c) {
                float f2 = size2;
                size2 = (int) (f2 + ((i4 - i3) * (f2 / i4)) + 0.5f);
            }
        } else {
            boolean z = this.d;
            size2 = (z || (!z && this.c)) ? i4 : i3;
        }
        setMeasuredDimension(size, size2);
        if (1073741824 == mode || 1073741824 == mode2) {
            if (this.d) {
                i3 = (int) ((i3 * (size / i4)) + 0.5f);
            } else {
                i3 = size;
            }
        }
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i3, 1073741824);
        this.r.measure(iMakeMeasureSpec, iMakeMeasureSpec);
        if (!this.d) {
            size = i3;
        }
        this.s.measure(View.MeasureSpec.makeMeasureSpec(size, 1073741824), View.MeasureSpec.makeMeasureSpec(size2 - i3, 1073741824));
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        int i5 = i3 - i;
        int width = this.r.getWidth();
        this.f = (i5 - width) / 2;
        float f = this.n;
        this.j = (int) (f * 12.0f * (width / (57.0f * f)));
        this.k = width + this.f;
        this.b = this.g.getWidth() - this.g.getHeight();
        Rect rect = this.i;
        rect.top = 0;
        rect.left = 0;
        rect.right = this.g.getHeight();
        this.i.bottom = this.g.getHeight();
        Rect rect2 = this.h;
        rect2.top = 0;
        int i6 = this.k;
        int i7 = this.j;
        rect2.left = i6 - i7;
        rect2.right = i6;
        rect2.bottom = i7;
        if (z) {
            a(this.s);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        Bitmap bitmap;
        super.dispatchDraw(canvas);
        if (!this.r.a() || (bitmap = this.g) == null) {
            return;
        }
        Rect rect = this.i;
        int height = bitmap.getHeight();
        int i = this.l;
        rect.right = height + i;
        Rect rect2 = this.h;
        rect2.left = (int) ((this.k - this.j) - ((i * this.n) / 2.0f));
        canvas.drawBitmap(this.g, this.i, rect2, (Paint) null);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if (motionEvent.getAction() != 1 || !this.r.a() || this.g == null || x <= this.h.left - this.a || x >= this.h.right || y <= this.h.top || y >= this.h.bottom + this.a) {
            return false;
        }
        if (this.l >= this.b) {
            g.a().a(new g.d(getContext()), new g.a<String>() { // from class: net.nend.android.NendAdIconView.2
                @Override // net.nend.android.internal.utilities.g.a
                public void a(String str, Exception exc) {
                    String str2 = NendAdIconView.this.p + "&gaid=" + str;
                    NendAdIconView.this.g();
                    d.a(NendAdIconView.this.getContext(), str2);
                }
            });
        } else {
            b();
        }
        return true;
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        a aVar = this.q;
        if (aVar != null) {
            aVar.onWindowFocusChanged(z);
        }
        if (z) {
            return;
        }
        d();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        a();
    }

    public void setTitleVisible(boolean z) {
        if (this.c != z) {
            this.c = z;
            requestLayout();
            if (z && !TextUtils.isEmpty(this.s.getText())) {
                post(this.t);
            }
            this.s.setVisibility(z ? 0 : 4);
            invalidate();
        }
    }

    public void setTitleColor(int i) {
        if (this.e != i) {
            this.e = i;
            this.s.setTextColor(i);
            invalidate();
        }
    }

    public void setIconSpaceEnabled(boolean z) {
        if (this.d != z) {
            this.d = z;
            requestLayout();
            invalidate();
        }
    }

    void setListner(a aVar) {
        this.q = aVar;
    }

    private void b() {
        new Timer().scheduleAtFixedRate(new TimerTask() { // from class: net.nend.android.NendAdIconView.3
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                NendAdIconView.this.m.post(new Runnable() { // from class: net.nend.android.NendAdIconView.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (NendAdIconView.this.l <= NendAdIconView.this.b) {
                            NendAdIconView.f(NendAdIconView.this);
                            NendAdIconView.this.invalidate();
                            return;
                        }
                        NendAdIconView.this.l = NendAdIconView.this.b;
                        NendAdIconView.this.invalidate();
                        cancel();
                        NendAdIconView.this.c();
                    }
                });
            }
        }, 0L, 4L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        new Timer().scheduleAtFixedRate(new TimerTask() { // from class: net.nend.android.NendAdIconView.4
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                if (NendAdIconView.this.l >= 0) {
                    NendAdIconView.this.m.post(new Runnable() { // from class: net.nend.android.NendAdIconView.4.1
                        @Override // java.lang.Runnable
                        public void run() {
                            NendAdIconView.i(NendAdIconView.this);
                            NendAdIconView.this.invalidate();
                        }
                    });
                } else {
                    NendAdIconView.this.d();
                    cancel();
                }
            }
        }, 1000L, 4L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        this.m.post(new Runnable() { // from class: net.nend.android.NendAdIconView.5
            @Override // java.lang.Runnable
            public void run() {
                NendAdIconView.this.l = 0;
                NendAdIconView.this.invalidate();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(TextView textView) {
        if (getWidth() == 0 || TextUtils.isEmpty(textView.getText())) {
            return;
        }
        float fB = b(textView);
        if (0.0f != fB) {
            textView.setTextSize(0, (int) fB);
        } else {
            textView.setTextSize(0, 6.0f);
            textView.setText(TextUtils.ellipsize(textView.getText(), textView.getPaint(), getWidth(), TextUtils.TruncateAt.END).toString());
        }
    }

    private float b(TextView textView) {
        String string = textView.getText().toString();
        Paint paint = new Paint(textView.getPaint());
        paint.setTextSize(this.n * 10.0f);
        int width = textView.getWidth();
        int height = textView.getHeight();
        while (true) {
            Paint.FontMetrics fontMetrics = paint.getFontMetrics();
            if (width > paint.measureText(string) && height > Math.abs(fontMetrics.ascent - fontMetrics.descent)) {
                return paint.getTextSize();
            }
            float textSize = paint.getTextSize() - 1.0f;
            if (this.n * 6.0f > textSize) {
                return 0.0f;
            }
            paint.setTextSize(textSize);
        }
    }

    private void e() {
        a aVar = this.q;
        if (aVar != null) {
            aVar.onReceive(this);
        }
    }

    private void f() {
        j.a("onFailedToImageDownload!");
        if (this.q != null) {
            NendIconError nendIconError = new NendIconError();
            nendIconError.a(this);
            nendIconError.setErrorType(1);
            nendIconError.setNendError(NendAdView.NendError.FAILED_AD_DOWNLOAD);
            this.q.onFailedToReceive(nendIconError);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        a aVar = this.q;
        if (aVar != null) {
            aVar.onClickInformation(this);
        }
    }

    @Override // net.nend.android.internal.ui.views.b.a
    public void onClickAd() {
        a aVar = this.q;
        if (aVar != null) {
            aVar.onClick(this);
        }
    }

    @Override // net.nend.android.internal.ui.views.b.a
    public void onSuccess() {
        this.s.setText(this.o.e());
        a(this.s);
        this.s.setVisibility(this.c ? 0 : 4);
        postInvalidate();
        e();
    }

    @Override // net.nend.android.internal.ui.views.b.a
    public void onFailure() {
        f();
    }
}
