package net.nend.android.internal.ui.views.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.Scroller;
import java.lang.ref.WeakReference;
import net.nend.android.internal.utilities.d;
import net.nend.android.internal.utilities.f;
import net.nend.android.internal.utilities.g;
import net.nend.android.internal.utilities.h;

/* JADX INFO: compiled from: OptOutImageView.java */
/* JADX INFO: loaded from: classes2.dex */
@SuppressLint({"ViewConstructor"})
public final class b extends ImageView implements View.OnClickListener {
    private final float a;
    private final String b;
    private final Scroller c;
    private final Handler d;
    private Bitmap e;
    private InterfaceC0114b f;

    /* JADX INFO: renamed from: net.nend.android.internal.ui.views.a.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: OptOutImageView.java */
    public interface InterfaceC0114b {
        void onInformationButtonClick();
    }

    public b(Context context, String str, int i, InterfaceC0114b interfaceC0114b) {
        super(context);
        this.a = getContext().getResources().getDisplayMetrics().density;
        this.c = new Scroller(context);
        this.d = new a(Looper.getMainLooper(), this);
        this.f = interfaceC0114b;
        this.b = f.a(context, h.a.OPT_OUT_URL.a(), "https://www.nend.net/privacy/optsdkgate") + "?uid=" + str + "&spot=" + i;
        setPadding(a(18), 0, a(45) * (-1), a(18));
        setOnClickListener(this);
        this.e = net.nend.android.internal.utilities.a.a(getContext(), "nend_information_icon.png");
        Bitmap bitmap = this.e;
        if (bitmap != null) {
            setImageBitmap(bitmap);
        }
    }

    public boolean a() {
        return getDrawable() != null;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.c.getCurrX() == ((int) (this.a * 45.0f))) {
            g.a().a(new g.d(getContext()), new g.a<String>() { // from class: net.nend.android.internal.ui.views.a.b.1
                @Override // net.nend.android.internal.utilities.g.a
                public void a(String str, Exception exc) {
                    String str2 = b.this.b + "&gaid=" + str;
                    b.this.e();
                    d.a(b.this.getContext(), str2);
                }
            });
        } else {
            c();
            this.d.removeMessages(718);
            this.d.sendEmptyMessageDelayed(718, 2000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        InterfaceC0114b interfaceC0114b = this.f;
        if (interfaceC0114b != null) {
            interfaceC0114b.onInformationButtonClick();
        }
    }

    public void b() {
        Bitmap bitmap = this.e;
        if (bitmap != null) {
            if (!bitmap.isRecycled()) {
                this.e.recycle();
            }
            this.e = null;
        }
    }

    @Override // android.view.View
    public void computeScroll() {
        if (this.c.computeScrollOffset()) {
            setPadding(this.c.getCurrX() + ((a(18) * (a(45) - this.c.getCurrX())) / a(45)), 0, a(45) * (-1), a(18));
            scrollTo(this.c.getCurrX(), this.c.getCurrY());
            postInvalidate();
        }
    }

    void c() {
        this.c.forceFinished(true);
        Scroller scroller = this.c;
        scroller.startScroll(scroller.getCurrX(), this.c.getCurrY(), a(45) - this.c.getCurrX(), 0, 1000);
        invalidate();
    }

    void d() {
        Scroller scroller = this.c;
        scroller.startScroll(scroller.getCurrX(), this.c.getCurrY(), this.c.getCurrX() * (-1), 0, 1000);
        invalidate();
    }

    private int a(int i) {
        return (int) (i * this.a);
    }

    /* JADX INFO: compiled from: OptOutImageView.java */
    private static class a extends Handler {
        private WeakReference<b> a;

        a(Looper looper, b bVar) {
            super(looper);
            this.a = new WeakReference<>(bVar);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            b bVar = this.a.get();
            if (bVar != null) {
                bVar.d();
            }
        }
    }
}
