package com.my.target.core.controllers;

import android.content.Context;
import android.media.AudioManager;
import android.view.TextureView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.ak;
import com.my.target.ap;
import com.my.target.aq;
import com.my.target.ar;
import com.my.target.br;
import com.my.target.cj;
import com.my.target.cm;
import com.my.target.co;
import com.my.target.common.models.ImageData;
import com.my.target.common.models.VideoData;
import com.my.target.cp;
import com.my.target.cy;
import com.my.target.g;
import com.my.target.nativeads.views.MediaAdView;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: NativeAdVideoController.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a implements br.a, co.a, cy.d {

    @NonNull
    private final VideoData a;

    @NonNull
    private final AudioManager.OnAudioFocusChangeListener b;

    @NonNull
    private final com.my.target.core.models.banners.a c;

    @NonNull
    private final HashSet<ap> d = new HashSet<>();

    @NonNull
    private final cm e;

    @Nullable
    private View.OnClickListener f;

    @Nullable
    private WeakReference<MediaAdView> g;

    @Nullable
    private WeakReference<br> h;

    @Nullable
    private WeakReference<cy> i;
    private boolean j;
    private boolean k;
    private boolean l;
    private boolean m;

    @Nullable
    private b n;

    @Nullable
    private cp o;
    private boolean p;
    private long q;
    private int state;

    @NonNull
    private final ak videoBanner;

    /* JADX INFO: compiled from: NativeAdVideoController.java */
    public interface b {
        void s();

        void t();

        void u();
    }

    @Override // com.my.target.co.a
    public final void bz() {
    }

    @Override // com.my.target.co.a
    public final void onComplete() {
    }

    public a(@NonNull com.my.target.core.models.banners.a aVar, @NonNull ak akVar, @NonNull VideoData videoData) {
        this.videoBanner = akVar;
        this.c = aVar;
        this.a = videoData;
        this.j = this.videoBanner.isAutoPlay();
        this.m = this.videoBanner.isAutoMute();
        ar statHolder = this.videoBanner.getStatHolder();
        this.e = cm.a(statHolder);
        this.d.addAll(statHolder.ad());
        this.b = new C0092a(this, (byte) 0);
    }

    public final void a(@Nullable View.OnClickListener onClickListener) {
        this.f = onClickListener;
    }

    public final void a(@NonNull MediaAdView mediaAdView) {
        g.a("register video ad with view ".concat(String.valueOf(mediaAdView)));
        unregister();
        this.g = new WeakReference<>(mediaAdView);
        TextureView textureView = new TextureView(mediaAdView.getContext());
        mediaAdView.addView(textureView, 0);
        this.e.setView(textureView);
        if (!this.k) {
            if (this.j) {
                bA();
            } else {
                bw();
            }
        }
        mediaAdView.setOnClickListener(new View.OnClickListener() { // from class: com.my.target.core.controllers.a.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                a.a(a.this, view);
            }
        });
    }

    public final void unregister() {
        MediaAdView mediaAdView;
        h();
        this.e.setView(null);
        o();
        WeakReference<MediaAdView> weakReference = this.g;
        if (weakReference != null) {
            mediaAdView = weakReference.get();
            if (mediaAdView != null && (mediaAdView.getChildAt(0) instanceof TextureView)) {
                mediaAdView.removeViewAt(0);
            }
        } else {
            mediaAdView = null;
        }
        if (this.k) {
            return;
        }
        if (mediaAdView != null) {
            mediaAdView.setOnClickListener(null);
        }
        this.g = null;
    }

    public final void g() {
        MediaAdView mediaAdViewN = n();
        if (mediaAdViewN == null) {
            g.a("Trying to play video in unregistered view");
            o();
            return;
        }
        if (mediaAdViewN.getWindowVisibility() != 0) {
            if (this.state == 1) {
                cp cpVar = this.o;
                if (cpVar != null) {
                    this.q = cpVar.getPosition();
                }
                o();
                this.state = 4;
                this.p = false;
                bA();
                return;
            }
            o();
            return;
        }
        if (this.p) {
            return;
        }
        this.p = true;
        TextureView textureView = mediaAdViewN.getChildAt(0) instanceof TextureView ? (TextureView) mediaAdViewN.getChildAt(0) : null;
        if (textureView == null) {
            o();
            return;
        }
        cp cpVar2 = this.o;
        if (cpVar2 != null && this.a != cpVar2.bs()) {
            o();
        }
        if (!this.j) {
            mediaAdViewN.getImageView().setVisibility(0);
            mediaAdViewN.getPlayButtonView().setVisibility(0);
            mediaAdViewN.getProgressBarView().setVisibility(8);
        }
        if (!this.j || this.k) {
            return;
        }
        cp cpVar3 = this.o;
        if (cpVar3 != null && cpVar3.isPaused()) {
            this.o.resume();
        } else {
            a(textureView, true);
        }
        bu();
    }

    public final void h() {
        cp cpVar;
        if (!this.p || this.k) {
            return;
        }
        this.p = false;
        if (this.state != 1 || (cpVar = this.o) == null) {
            return;
        }
        cpVar.pause();
        this.state = 2;
    }

    @Override // com.my.target.co.a
    public final void bw() {
        Context context;
        WeakReference<cy> weakReference;
        cy cyVar;
        this.l = false;
        MediaAdView mediaAdViewN = n();
        if (mediaAdViewN != null) {
            ImageView imageView = mediaAdViewN.getImageView();
            ImageData image = this.videoBanner.getImage();
            if (image != null) {
                imageView.setImageBitmap(image.getBitmap());
            }
            imageView.setVisibility(0);
            mediaAdViewN.getPlayButtonView().setVisibility(0);
            mediaAdViewN.getProgressBarView().setVisibility(8);
            context = mediaAdViewN.getContext();
        } else {
            context = null;
        }
        if (this.k && (weakReference = this.i) != null && (cyVar = weakReference.get()) != null) {
            cyVar.J();
            context = cyVar.getContext();
        }
        if (context != null) {
            a(context);
        }
    }

    @Override // com.my.target.co.a
    public final void f(float f) {
        cy cyVar;
        WeakReference<cy> weakReference = this.i;
        if (weakReference == null || (cyVar = weakReference.get()) == null) {
            return;
        }
        if (f > 0.0f) {
            cyVar.c(false);
        } else {
            cyVar.c(true);
        }
    }

    @Override // com.my.target.co.a
    public final void bx() {
        WeakReference<cy> weakReference;
        cy cyVar;
        if (this.state == 1) {
            return;
        }
        this.state = 1;
        MediaAdView mediaAdViewN = n();
        if (mediaAdViewN != null) {
            mediaAdViewN.getImageView().setVisibility(4);
            mediaAdViewN.getProgressBarView().setVisibility(8);
            mediaAdViewN.getPlayButtonView().setVisibility(8);
        }
        if (!this.k || (weakReference = this.i) == null || (cyVar = weakReference.get()) == null) {
            return;
        }
        cyVar.M();
    }

    @Override // com.my.target.co.a
    public final void by() {
        Context context;
        MediaAdView mediaAdViewN = n();
        if (mediaAdViewN != null) {
            context = mediaAdViewN.getContext();
            mediaAdViewN.getPlayButtonView().setVisibility(0);
            mediaAdViewN.getProgressBarView().setVisibility(8);
        } else {
            context = null;
        }
        p();
        if (mediaAdViewN != null) {
            a(context);
        }
        b bVar = this.n;
        if (bVar != null) {
            bVar.t();
        }
    }

    @Override // com.my.target.co.a
    public final void bA() {
        WeakReference<cy> weakReference;
        cy cyVar;
        this.state = 4;
        MediaAdView mediaAdViewN = n();
        if (mediaAdViewN != null) {
            mediaAdViewN.getProgressBarView().setVisibility(0);
            mediaAdViewN.getImageView().setVisibility(0);
            mediaAdViewN.getPlayButtonView().setVisibility(8);
        }
        if (!this.k || (weakReference = this.i) == null || (cyVar = weakReference.get()) == null) {
            return;
        }
        cyVar.K();
    }

    @Override // com.my.target.co.a
    public final void b(float f, float f2) {
        Context context;
        float duration;
        cy cyVar;
        MediaAdView mediaAdView;
        while (true) {
            context = null;
            this.q = 0L;
            WeakReference<MediaAdView> weakReference = this.g;
            if (weakReference != null && (mediaAdView = weakReference.get()) != null) {
                context = mediaAdView.getContext();
            }
            bx();
            this.e.e(f);
            if (!this.l) {
                b bVar = this.n;
                if (bVar != null) {
                    bVar.s();
                }
                if (context != null) {
                    sendStat(aq.a.du, context);
                    this.d.clear();
                    this.d.addAll(this.videoBanner.getStatHolder().ad());
                    a(0.0f, context);
                }
                this.l = true;
            }
            duration = this.videoBanner.getDuration();
            WeakReference<cy> weakReference2 = this.i;
            if (weakReference2 != null && (cyVar = weakReference2.get()) != null) {
                cyVar.b(f, duration);
            }
            if (f <= duration) {
                break;
            } else {
                f = duration;
            }
        }
        if (f > 0.0f && context != null) {
            a(f, context);
        }
        if (f == duration) {
            bw();
            this.state = 3;
            this.j = false;
            cp cpVar = this.o;
            if (cpVar != null) {
                cpVar.stop();
            }
            b bVar2 = this.n;
            if (bVar2 != null) {
                bVar2.u();
            }
        }
    }

    @Override // com.my.target.co.a
    public final void W(String str) {
        this.state = 3;
        bw();
    }

    public final void a(@Nullable b bVar) {
        this.n = bVar;
    }

    @Override // com.my.target.br.a
    public final void a(@NonNull br brVar, @NonNull FrameLayout frameLayout) {
        cy cyVar = new cy(frameLayout.getContext());
        this.state = 4;
        this.h = new WeakReference<>(brVar);
        cyVar.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        frameLayout.addView(cyVar);
        this.i = new WeakReference<>(cyVar);
        cyVar.a(this.c, this.a);
        cyVar.setVideoDialogViewListener(this);
        cyVar.c(this.m);
        sendStat(aq.a.dz, frameLayout.getContext());
        a(cyVar.getTextureView(), this.m);
    }

    @Override // com.my.target.br.a
    public final void i(boolean z) {
        cp cpVar = this.o;
        if (cpVar == null || z) {
            return;
        }
        this.q = cpVar.getPosition();
        o();
        by();
    }

    @Override // com.my.target.br.a
    public final void aV() {
        g.a("Dismiss dialog");
        this.h = null;
        this.k = false;
        bu();
        MediaAdView mediaAdViewN = n();
        if (mediaAdViewN == null) {
            return;
        }
        a(mediaAdViewN.getContext());
        int i = this.state;
        if (i == 1) {
            this.state = 4;
            bx();
            if (this.videoBanner.isAutoPlay()) {
                this.j = true;
            }
            View childAt = mediaAdViewN.getChildAt(0);
            if (childAt instanceof TextureView) {
                a((TextureView) childAt, true);
            }
        } else if (i == 2 || i == 3) {
            this.j = false;
            bw();
        } else if (i == 4) {
            this.j = true;
            bA();
            View childAt2 = mediaAdViewN.getChildAt(0);
            if (childAt2 instanceof TextureView) {
                a((TextureView) childAt2, true);
            }
        } else {
            this.j = false;
        }
        sendStat(aq.a.dA, mediaAdViewN.getContext());
        this.i = null;
    }

    @Override // com.my.target.cy.d
    public final void i() {
        br brVar;
        WeakReference<br> weakReference = this.h;
        if (weakReference != null && (brVar = weakReference.get()) != null) {
            Context context = brVar.getContext();
            r();
            sendStat(aq.a.dw, context);
        }
        b bVar = this.n;
        if (bVar != null) {
            bVar.s();
        }
    }

    @Override // com.my.target.cy.d
    public final void j() {
        cy cyVar;
        r();
        WeakReference<cy> weakReference = this.i;
        if (weakReference != null && (cyVar = weakReference.get()) != null) {
            cyVar.getMediaAdView().getImageView().setVisibility(8);
            cyVar.N();
        }
        b bVar = this.n;
        if (bVar != null) {
            bVar.s();
        }
    }

    @Override // com.my.target.cy.d
    public final void a(View view) {
        if (this.state == 1) {
            cp cpVar = this.o;
            if (cpVar != null) {
                cpVar.pause();
            }
            by();
        }
        View.OnClickListener onClickListener = this.f;
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }

    @Override // com.my.target.cy.d
    public final void k() {
        br brVar;
        if (this.state == 1) {
            p();
            this.state = 2;
            b bVar = this.n;
            if (bVar != null) {
                bVar.t();
            }
            WeakReference<br> weakReference = this.h;
            if (weakReference == null || (brVar = weakReference.get()) == null) {
                return;
            }
            sendStat(aq.a.dv, brVar.getContext());
        }
    }

    @Override // com.my.target.cy.d
    public final void l() {
        WeakReference<br> weakReference = this.h;
        br brVar = weakReference == null ? null : weakReference.get();
        if (brVar == null || !brVar.isShowing()) {
            return;
        }
        brVar.dismiss();
    }

    @Override // com.my.target.cy.d
    public final void m() {
        MediaAdView mediaAdViewN = n();
        boolean z = true;
        if (mediaAdViewN != null && this.o != null) {
            Context context = mediaAdViewN.getContext();
            if (this.o.isMuted()) {
                this.o.bv();
                sendStat(aq.a.dC, context);
            } else {
                this.o.bu();
                sendStat(aq.a.dD, context);
                this.m = true;
                return;
            }
        } else {
            if (this.m) {
            }
            this.m = z;
        }
        z = false;
        this.m = z;
    }

    private void a(@NonNull TextureView textureView, boolean z) {
        if (this.o == null) {
            this.o = cp.z(textureView.getContext());
            this.o.a(this);
        }
        if (z) {
            bu();
        } else {
            q();
        }
        this.o.a(this.a, textureView);
        long j = this.q;
        if (j > 0) {
            this.o.seekTo(j);
        }
    }

    @Nullable
    private MediaAdView n() {
        WeakReference<MediaAdView> weakReference = this.g;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    private void o() {
        cp cpVar = this.o;
        if (cpVar == null) {
            return;
        }
        cpVar.a(null);
        this.o.destroy();
        this.o = null;
    }

    private void bu() {
        cp cpVar = this.o;
        if (cpVar != null) {
            cpVar.bu();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        WeakReference<cy> weakReference;
        if (!this.k || (weakReference = this.i) == null) {
            return;
        }
        this.state = 2;
        cy cyVar = weakReference.get();
        if (cyVar != null) {
            cp cpVar = this.o;
            if (cpVar != null) {
                cpVar.pause();
            }
            cyVar.L();
        }
    }

    private void a(@NonNull Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        if (audioManager != null) {
            audioManager.abandonAudioFocus(this.b);
        }
    }

    private void a(float f, @NonNull Context context) {
        if (this.d.isEmpty()) {
            return;
        }
        Iterator<ap> it = this.d.iterator();
        while (it.hasNext()) {
            ap next = it.next();
            if (next.Y() <= f) {
                cj.a(next, context);
                it.remove();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        cp cpVar = this.o;
        if (cpVar != null) {
            cpVar.bv();
        }
    }

    private void sendStat(@NonNull String str, @NonNull Context context) {
        cj.a(this.videoBanner.getStatHolder().x(str), context);
    }

    private void r() {
        WeakReference<cy> weakReference;
        cp cpVar = this.o;
        if (cpVar != null && cpVar.isPaused()) {
            this.o.resume();
        } else if (this.k && (weakReference = this.i) != null) {
            a(weakReference.get().getTextureView(), true);
        }
        bA();
    }

    /* JADX INFO: renamed from: com.my.target.core.controllers.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: NativeAdVideoController.java */
    private class C0092a implements AudioManager.OnAudioFocusChangeListener {
        private C0092a() {
        }

        /* synthetic */ C0092a(a aVar, byte b) {
            this();
        }

        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public final void onAudioFocusChange(int i) {
            if (i == -3) {
                a.d(a.this);
                return;
            }
            if (i == -2 || i == -1) {
                a.this.p();
                g.a("Audiofocus loss, pausing");
            } else if ((i == 1 || i == 2 || i == 4) && a.this.k) {
                g.a("Audiofocus gain, unmuting");
                a.this.q();
            }
        }
    }

    static /* synthetic */ void a(a aVar, View view) {
        Context context = view.getContext();
        aVar.k = true;
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        if (audioManager != null) {
            audioManager.requestAudioFocus(aVar.b, 3, 2);
        }
        if (aVar.state == 1) {
            aVar.state = 4;
        }
        try {
            br.a(aVar, context).show();
        } catch (Exception e) {
            e.printStackTrace();
            g.b("Unable to start video dialog! Check myTarget MediaAdView, maybe it was created with non-Activity context");
            aVar.aV();
        }
    }

    static /* synthetic */ void d(a aVar) {
        cp cpVar = aVar.o;
        if (cpVar == null || aVar.m) {
            return;
        }
        cpVar.bt();
    }
}
