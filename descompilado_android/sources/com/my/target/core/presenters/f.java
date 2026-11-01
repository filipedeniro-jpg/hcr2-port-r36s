package com.my.target.core.presenters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import com.my.target.bq;
import com.my.target.bt;
import com.my.target.bw;
import com.my.target.ck;
import com.my.target.common.models.ImageData;
import com.my.target.v;
import org.json.JSONObject;

/* JADX INFO: compiled from: InterstitialHtmlPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class f implements bt.a, m {

    @NonNull
    private final bt C;

    @NonNull
    private final bw D;

    @NonNull
    private final FrameLayout E;

    @Nullable
    private b F;

    @Nullable
    private m.a G;
    private long H;
    private long I;

    @Nullable
    private com.my.target.core.models.banners.g e;

    @Override // com.my.target.bt.a
    public final void a(@NonNull v vVar) {
    }

    @Override // com.my.target.core.presenters.j
    public final void stop() {
    }

    @NonNull
    public static f b(@NonNull Context context) {
        return new f(context);
    }

    @Nullable
    final m.a u() {
        return this.G;
    }

    private f(@NonNull Context context) {
        this.C = new bt(context);
        this.D = new bw(context);
        this.E = new FrameLayout(context);
        this.D.setContentDescription("Close");
        ck.a(this.D, "close_button");
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = GravityCompat.END;
        this.D.setVisibility(8);
        this.D.setLayoutParams(layoutParams);
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-1, -1);
        layoutParams2.gravity = 1;
        this.C.setLayoutParams(layoutParams2);
        this.E.addView(this.C);
        if (this.D.getParent() == null) {
            this.E.addView(this.D);
        }
        Bitmap bitmapH = bq.h(ck.x(context).l(28));
        if (bitmapH != null) {
            this.D.a(bitmapH, false);
        }
    }

    @Override // com.my.target.core.presenters.m
    public final void a(@NonNull com.my.target.core.models.banners.g gVar) {
        this.e = gVar;
        this.F = new b(this.D);
        this.C.setBannerWebViewListener(this);
        String source = gVar.getSource();
        if (source != null) {
            this.C.a((JSONObject) null, source);
            ImageData closeIcon = gVar.getCloseIcon();
            if (closeIcon != null) {
                this.D.a(closeIcon.getBitmap(), false);
            }
            this.D.setOnClickListener(new a(this));
            if (gVar.getAllowCloseDelay() > 0.0f) {
                com.my.target.g.a("banner will be allowed to close in " + gVar.getAllowCloseDelay() + " seconds");
                a((long) (gVar.getAllowCloseDelay() * 1000.0f));
            } else {
                com.my.target.g.a("banner is allowed to close");
                this.D.setVisibility(0);
            }
            m.a aVar = this.G;
            if (aVar != null) {
                aVar.a(gVar, this.E.getContext());
                return;
            }
            return;
        }
        w();
    }

    @Override // com.my.target.core.presenters.m
    public final void a(@Nullable m.a aVar) {
        this.G = aVar;
    }

    @Override // com.my.target.core.presenters.j
    public final void pause() {
        if (this.H > 0) {
            long jCurrentTimeMillis = System.currentTimeMillis() - this.H;
            if (jCurrentTimeMillis > 0) {
                long j = this.I;
                if (jCurrentTimeMillis < j) {
                    this.I = j - jCurrentTimeMillis;
                    return;
                }
            }
            this.I = 0L;
        }
    }

    @Override // com.my.target.core.presenters.j
    public final void resume() {
        long j = this.I;
        if (j > 0) {
            a(j);
        }
    }

    @Override // com.my.target.core.presenters.j
    public final void destroy() {
        this.E.removeView(this.C);
        this.C.destroy();
    }

    @Override // com.my.target.core.presenters.j
    @NonNull
    public final View v() {
        return this.E;
    }

    @Override // com.my.target.bt.a
    public final void onError(@NonNull String str) {
        w();
    }

    @Override // com.my.target.bt.a
    public final void J(@NonNull String str) {
        m.a aVar = this.G;
        if (aVar != null) {
            aVar.a(this.e, str, this.E.getContext());
        }
    }

    private void a(long j) {
        this.C.removeCallbacks(this.F);
        this.H = System.currentTimeMillis();
        this.C.postDelayed(this.F, j);
    }

    private void w() {
        m.a aVar = this.G;
        if (aVar != null) {
            aVar.g();
        }
    }

    /* JADX INFO: compiled from: InterstitialHtmlPresenter.java */
    private static class a implements View.OnClickListener {

        @NonNull
        private final f J;

        a(@NonNull f fVar) {
            this.J = fVar;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            m.a aVarU = this.J.u();
            if (aVarU != null) {
                aVarU.bf();
            }
        }
    }

    /* JADX INFO: compiled from: InterstitialHtmlPresenter.java */
    private static class b implements Runnable {

        @NonNull
        private final bw D;

        b(@NonNull bw bwVar) {
            this.D = bwVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            com.my.target.g.a("banner became just closeable");
            this.D.setVisibility(0);
        }
    }
}
