package net.nend.android.internal.ui.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ViewSwitcher;
import java.util.concurrent.Future;
import net.nend.android.internal.utilities.d;
import net.nend.android.internal.utilities.g;
import net.nend.android.internal.utilities.j;
import net.nend.android.internal.utilities.k;

/* JADX INFO: compiled from: NendAdViewSwitcher.java */
/* JADX INFO: loaded from: classes2.dex */
public class b extends ViewSwitcher implements View.OnClickListener, g.b<Bitmap> {
    private static final Object[] a = new Object[0];
    private net.nend.android.internal.a b;
    private a c;
    private Bitmap d;
    private ImageView e;
    private net.nend.android.internal.ui.views.a f;
    private String g;
    private Future<Bitmap> h;
    private boolean i;
    private float j;
    private float k;

    /* JADX INFO: compiled from: NendAdViewSwitcher.java */
    public interface a {
        void onClickAd();

        void onFailure();

        void onSuccess();

        boolean onValidation(int i, int i2);
    }

    public b(Context context) {
        super(context);
        this.g = "";
        this.i = false;
        a(context);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (TextUtils.isEmpty(this.g) || this.i || !a()) {
            return;
        }
        i();
        d.a(getContext(), this.g);
    }

    @Override // net.nend.android.internal.utilities.g.b
    public String getRequestUrl() {
        net.nend.android.internal.a aVar = this.b;
        return aVar != null ? aVar.b() : "";
    }

    @Override // net.nend.android.internal.utilities.g.b
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Bitmap makeResponse(byte[] bArr) {
        Bitmap bitmapDecodeByteArray;
        if (bArr == null) {
            return null;
        }
        try {
            synchronized (a) {
                try {
                    bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
                } catch (Throwable th) {
                    throw th;
                }
            }
            return bitmapDecodeByteArray;
        } catch (IllegalStateException e) {
            j.a(k.ERR_HTTP_REQUEST, e);
            return null;
        } catch (OutOfMemoryError e2) {
            System.gc();
            j.a(k.ERR_HTTP_REQUEST, e2);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Bitmap bitmap) {
        if (bitmap != null) {
            if (a(bitmap.getWidth(), bitmap.getHeight())) {
                this.f.a();
                d();
                this.d = bitmap;
                this.e.setImageBitmap(bitmap);
                setDisplayedChild(0);
                f();
                return;
            }
            return;
        }
        h();
    }

    public void a(net.nend.android.internal.a aVar, a aVar2) {
        if (aVar == null) {
            return;
        }
        c();
        this.b = aVar;
        this.c = aVar2;
        a(getContext());
        if (aVar.k()) {
            this.f.a(aVar.b(), new net.nend.android.internal.ui.views.a.b() { // from class: net.nend.android.internal.ui.views.b.1
                @Override // net.nend.android.internal.ui.views.a.b
                public void a() {
                    b.this.setDisplayedChild(1);
                    b.this.f();
                }

                @Override // net.nend.android.internal.ui.views.a.b
                public void b() {
                    b.this.h();
                }

                @Override // net.nend.android.internal.ui.views.a.b
                public boolean a(int i, int i2) {
                    return b.this.a(i, i2);
                }
            });
        } else {
            this.h = g.a().a(new g.f(this), new g.a<Bitmap>() { // from class: net.nend.android.internal.ui.views.b.2
                @Override // net.nend.android.internal.utilities.g.a
                public void a(Bitmap bitmap, Exception exc) {
                    b.this.a(bitmap);
                }
            });
        }
    }

    public boolean a() {
        if (j()) {
            return false;
        }
        int displayedChild = getDisplayedChild();
        if (displayedChild == 0) {
            return this.e.getDrawable() != null && (this.e.getDrawable() instanceof BitmapDrawable);
        }
        if (displayedChild != 1) {
            return false;
        }
        return this.f.b();
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.i = false;
            this.j = motionEvent.getX();
            this.k = motionEvent.getY();
        } else if (action == 2 && a(motionEvent)) {
            this.i = true;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    private boolean a(MotionEvent motionEvent) {
        return 22.0f < Math.abs(motionEvent.getX() - this.j) || 22.0f < Math.abs(motionEvent.getY() - this.k);
    }

    public void b() {
        this.c = null;
        c();
        e();
    }

    private void a(Context context) {
        if (this.e == null) {
            this.e = new ImageView(context);
            this.e.setScaleType(ImageView.ScaleType.FIT_XY);
            addView(this.e, new FrameLayout.LayoutParams(-1, -1));
        }
        if (this.f == null) {
            this.f = new net.nend.android.internal.ui.views.a(context);
            addView(this.f, new FrameLayout.LayoutParams(-1, -1));
        }
    }

    private void c() {
        Future<Bitmap> future = this.h;
        if (future != null) {
            future.cancel(true);
        }
        net.nend.android.internal.ui.views.a aVar = this.f;
        if (aVar != null) {
            aVar.a();
        }
    }

    private void d() {
        Bitmap bitmap = this.d;
        if (bitmap != null && !bitmap.isRecycled()) {
            this.d.recycle();
        }
        this.d = null;
        ImageView imageView = this.e;
        if (imageView == null || imageView.getDrawable() == null) {
            return;
        }
        this.e.getDrawable().setCallback(null);
        this.e.setImageDrawable(null);
    }

    private void e() {
        removeAllViews();
        d();
        this.e = null;
        net.nend.android.internal.ui.views.a aVar = this.f;
        if (aVar != null) {
            aVar.stopLoading();
            this.f.clearCache(true);
            this.f.setWebViewClient(null);
            this.f.setWebChromeClient(null);
            this.f.destroy();
            this.f = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        if (j()) {
            return;
        }
        this.g = this.b.c();
        this.e.setOnClickListener(this);
        this.f.setOnClickListener(this);
        g();
    }

    private void g() {
        a aVar = this.c;
        if (aVar != null) {
            aVar.onSuccess();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        a aVar = this.c;
        if (aVar != null) {
            aVar.onFailure();
        }
    }

    private void i() {
        a aVar = this.c;
        if (aVar != null) {
            aVar.onClickAd();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(int i, int i2) {
        a aVar = this.c;
        if (aVar != null) {
            return aVar.onValidation(i, i2);
        }
        return false;
    }

    private boolean j() {
        return this.e == null || this.f == null;
    }
}
