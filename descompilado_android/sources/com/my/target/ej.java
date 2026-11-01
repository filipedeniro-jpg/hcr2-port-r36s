package com.my.target;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import com.my.target.common.models.ImageData;
import com.my.target.common.models.VideoData;

/* JADX INFO: compiled from: PromoMediaAdView.java */
/* JADX INFO: loaded from: classes2.dex */
@SuppressLint({"ViewConstructor"})
public final class ej extends ViewGroup {

    @NonNull
    private final by aA;

    @NonNull
    private final ck aC;

    @NonNull
    private final bw bV;

    @NonNull
    private final b bW;

    @NonNull
    private final ev bX;

    @NonNull
    private final FrameLayout bY;

    @NonNull
    private final ProgressBar bZ;
    private final boolean bd;

    @Nullable
    private co ca;

    @Nullable
    private VideoData cb;

    @Nullable
    private a cc;
    private int cd;
    private int ce;

    @Nullable
    private Bitmap cf;
    private boolean cg;
    private final boolean useExoPlayer;

    /* JADX INFO: compiled from: PromoMediaAdView.java */
    public interface a extends AudioManager.OnAudioFocusChangeListener, co.a {
        void A();

        void B();

        void y();
    }

    public ej(@NonNull Context context, @NonNull ck ckVar, boolean z, boolean z2) {
        super(context);
        this.cg = true;
        this.aC = ckVar;
        this.bd = z;
        this.useExoPlayer = z2;
        this.aA = new by(context);
        this.bV = new bw(context);
        this.bZ = new ProgressBar(context, null, android.R.attr.progressBarStyleLarge);
        this.bY = new FrameLayout(context);
        ck.a(this.bY, 0, 868608760);
        this.bX = new ev(context);
        this.bW = new b(this, (byte) 0);
    }

    @NonNull
    public final FrameLayout getClickableLayout() {
        return this.bY;
    }

    @Nullable
    public final co getVideoPlayer() {
        return this.ca;
    }

    public final void P() {
        ck.a(this.bV, "play_button");
        ck.a(this.aA, "media_image");
        ck.a(this.bX, "video_texture");
        this.aA.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        this.aA.setAdjustViewBounds(true);
        addView(this.bX);
        this.bZ.setVisibility(8);
        addView(this.aA);
        addView(this.bZ);
        addView(this.bV);
        addView(this.bY);
    }

    public final void a(com.my.target.core.models.banners.i iVar) {
        b(iVar);
    }

    public final void destroy() {
        co coVar = this.ca;
        if (coVar != null) {
            coVar.destroy();
        }
        this.ca = null;
    }

    public final void setInterstitialPromoViewListener(@Nullable a aVar) {
        this.cc = aVar;
        co coVar = this.ca;
        if (coVar != null) {
            coVar.a(aVar);
        }
    }

    public final void Q() {
        this.aA.setOnClickListener(this.bW);
        this.bV.setOnClickListener(this.bW);
        setOnClickListener(this.bW);
    }

    public final boolean isPlaying() {
        co coVar = this.ca;
        return coVar != null && coVar.isPlaying();
    }

    public final boolean isPaused() {
        co coVar = this.ca;
        return coVar != null && coVar.isPaused();
    }

    public final void resume() {
        co coVar = this.ca;
        if (coVar != null) {
            if (this.cb != null) {
                coVar.resume();
                this.aA.setVisibility(8);
            }
            this.bV.setVisibility(8);
        }
    }

    public final void pause() {
        co coVar = this.ca;
        if (coVar != null) {
            coVar.pause();
            this.aA.setVisibility(0);
            Bitmap screenShot = this.bX.getScreenShot();
            if (screenShot != null && this.ca.isStarted()) {
                this.aA.setImageBitmap(screenShot);
            }
            if (this.cg) {
                this.bV.setVisibility(0);
            }
        }
    }

    public final void e(int i) {
        co coVar = this.ca;
        if (coVar != null) {
            if (i == 0) {
                coVar.bu();
            } else if (i == 1) {
                coVar.bt();
            } else {
                coVar.bv();
            }
        }
    }

    public final void O() {
        this.aA.setVisibility(8);
        this.bZ.setVisibility(8);
    }

    final void a(@NonNull com.my.target.core.models.banners.i iVar, int i) {
        int iL;
        if (iVar.getVideoBanner() == null) {
            b(iVar);
            return;
        }
        this.bY.setVisibility(8);
        ak<VideoData> videoBanner = iVar.getVideoBanner();
        if (videoBanner != null) {
            this.cb = (VideoData) videoBanner.getMediaData();
            if (this.cb != null) {
                if (this.useExoPlayer && cg.bl()) {
                    this.ca = cp.z(getContext());
                } else {
                    this.ca = ew.X();
                }
                this.ca.a(this.cc);
                this.ce = this.cb.getWidth();
                this.cd = this.cb.getHeight();
                ImageData preview = videoBanner.getPreview();
                if (preview != null) {
                    this.cf = preview.getData();
                    this.aA.setImageBitmap(this.cf);
                } else {
                    ImageData image = iVar.getImage();
                    if (image != null) {
                        this.cf = image.getData();
                        this.aA.setImageBitmap(this.cf);
                    }
                }
                if (i != 1) {
                    ImageData playIcon = iVar.getPlayIcon();
                    if (playIcon != null && playIcon.getData() != null) {
                        this.bV.a(playIcon.getData(), true);
                        return;
                    }
                    if (this.bd) {
                        iL = this.aC.l(IronSourceConstants.USING_CACHE_FOR_INIT_EVENT);
                    } else {
                        iL = this.aC.l(96);
                    }
                    this.bV.a(com.my.target.core.resources.b.getPlayIcon(iL), false);
                }
            }
        }
    }

    final void f(boolean z) {
        co coVar = this.ca;
        if (coVar != null) {
            coVar.stop();
        }
        this.aA.setVisibility(0);
        this.aA.setImageBitmap(this.cf);
        this.cg = z;
        if (z) {
            this.bV.setVisibility(0);
            return;
        }
        this.aA.setOnClickListener(null);
        this.bV.setOnClickListener(null);
        setOnClickListener(null);
    }

    final void S() {
        co coVar;
        this.bV.setVisibility(8);
        this.bZ.setVisibility(0);
        if (this.cb == null || (coVar = this.ca) == null) {
            return;
        }
        coVar.a(this.cc);
        this.ca.a(this.cb, this.bX);
    }

    @Override // android.view.View
    protected final void onMeasure(int i, int i2) {
        int i3;
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        int i4 = this.cd;
        if (i4 == 0 || (i3 = this.ce) == 0) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(0, 1073741824), View.MeasureSpec.makeMeasureSpec(0, 1073741824));
            return;
        }
        if (mode2 == 0 && size2 == 0) {
            size2 = i4;
            size = i3;
            mode = Integer.MIN_VALUE;
            mode2 = Integer.MIN_VALUE;
        }
        if (size2 == 0 || mode2 == 0) {
            size2 = (int) ((size / this.ce) * this.cd);
        }
        if (size == 0 || mode == 0) {
            size = (int) ((size2 / this.cd) * this.ce);
        }
        float f = this.ce / this.cd;
        float f2 = size / f;
        float f3 = size2;
        if (f2 > f3) {
            size = (int) (f * f3);
        } else {
            size2 = (int) f2;
        }
        for (int i5 = 0; i5 < getChildCount(); i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                int i6 = (childAt == this.aA || childAt == this.bY || childAt == this.bX) ? 1073741824 : Integer.MIN_VALUE;
                childAt.measure(View.MeasureSpec.makeMeasureSpec(size, i6), View.MeasureSpec.makeMeasureSpec(size2, i6));
            }
        }
        setMeasuredDimension(size, size2);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        for (int i5 = 0; i5 < getChildCount(); i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int i6 = ((i3 - i) - measuredWidth) / 2;
                int i7 = ((i4 - i2) - measuredHeight) / 2;
                childAt.layout(i6, i7, measuredWidth + i6, measuredHeight + i7);
            }
        }
    }

    private void b(@NonNull com.my.target.core.models.banners.i iVar) {
        this.bY.setVisibility(0);
        setOnClickListener(null);
        this.bV.setVisibility(8);
        ImageData image = iVar.getImage();
        if (image == null || image.getData() == null) {
            return;
        }
        this.ce = image.getWidth();
        this.cd = image.getHeight();
        if (this.ce == 0 || this.cd == 0) {
            this.ce = image.getData().getWidth();
            this.cd = image.getData().getHeight();
        }
        this.aA.setImageBitmap(image.getData());
        this.aA.setClickable(false);
    }

    /* JADX INFO: compiled from: PromoMediaAdView.java */
    private class b implements View.OnClickListener {
        private b() {
        }

        /* synthetic */ b(ej ejVar, byte b) {
            this();
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            if (ej.this.cc == null) {
                return;
            }
            if (!ej.this.isPlaying() && !ej.this.isPaused()) {
                ej.this.cc.y();
            } else if (!ej.this.isPaused()) {
                ej.this.cc.A();
            } else {
                ej.this.cc.B();
            }
        }
    }
}
