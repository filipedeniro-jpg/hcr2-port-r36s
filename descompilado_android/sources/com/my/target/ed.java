package com.my.target;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import com.my.target.common.models.ImageData;
import com.my.target.common.models.VideoData;

/* JADX INFO: compiled from: DefaultView.java */
/* JADX INFO: loaded from: classes2.dex */
@SuppressLint({"ViewConstructor"})
public final class ed extends RelativeLayout implements eg {
    private static final int bg = ck.br();
    private static final int bh = ck.br();
    private static final int bi = ck.br();
    private static final int bj = ck.br();
    private static final int bk = ck.br();

    @NonNull
    private final bw D;

    @Nullable
    private com.my.target.core.presenters.h.a O;

    @NonNull
    private final ck aC;

    @NonNull
    private final a bl;

    @NonNull
    private final by bm;

    @NonNull
    private final ek bn;

    @NonNull
    private final ej bo;

    @NonNull
    private final ei bp;

    @NonNull
    private final eb bq;

    @NonNull
    private final bw br;

    @Nullable
    private final Bitmap bs;

    @Nullable
    private final Bitmap bt;
    private final int bu;
    private final int bv;
    private final int bw;
    private final int bx;
    private float by;

    @Nullable
    private eh.a bz;

    @Override // com.my.target.eg
    public final void finish() {
    }

    @Override // com.my.target.eh
    @NonNull
    public final View getView() {
        return this;
    }

    public ed(@NonNull Context context, boolean z) {
        super(context);
        boolean z2 = (getContext().getResources().getConfiguration().screenLayout & 15) >= 3;
        this.aC = ck.x(context);
        this.bm = new by(context);
        this.bm.setId(bj);
        this.bn = new ek(context, this.aC, z2);
        this.bn.setId(bh);
        this.bo = new ej(context, this.aC, z2, z);
        this.bo.setId(bg);
        this.D = new bw(context);
        this.D.setId(bk);
        this.bq = new eb(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(3, bg);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.addRule(14, -1);
        this.bp = new ei(context, this.aC);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams3.addRule(12, -1);
        this.bp.setLayoutParams(layoutParams3);
        this.bp.setId(bi);
        this.br = new bw(context);
        this.br.setId(bG);
        this.bs = com.my.target.core.resources.b.getVolumeOnIcon(this.aC.l(28));
        this.bt = com.my.target.core.resources.b.getVolumeOffIcon(this.aC.l(28));
        this.bl = new a();
        this.bu = this.aC.l(64);
        this.bv = this.aC.l(20);
        ck.a(this.bm, "icon_image");
        ck.a(this.br, "sound_button");
        ck.a(this.bn, "vertical_view");
        ck.a(this.bo, "media_view");
        ck.a(this.bp, "panel_view");
        ck.a(this.D, "close_button");
        ck.a(this.bq, "progress_wheel");
        addView(this.bp, 0);
        addView(this.bm, 0);
        addView(this.bn, 0, layoutParams);
        addView(this.bo, 0, layoutParams2);
        addView(this.br);
        addView(this.D);
        addView(this.bq);
        this.bw = this.aC.l(28);
        this.bx = this.aC.l(10);
    }

    /* JADX WARN: Code duplicated, block: B:18:0x009d  */
    /* JADX WARN: Code duplicated, block: B:30:0x00b8  */
    @Override // com.my.target.eh
    public final void setBanner(@NonNull com.my.target.core.models.banners.i iVar) {
        boolean z;
        int width;
        int height;
        int height2;
        int width2;
        this.bq.setVisibility(8);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.bw, this.aC.l(28));
        layoutParams.addRule(9);
        layoutParams.topMargin = this.aC.l(10);
        layoutParams.leftMargin = this.aC.l(10);
        this.bq.setLayoutParams(layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(11);
        this.D.setVisibility(8);
        ak<VideoData> videoBanner = iVar.getVideoBanner();
        if (videoBanner == null) {
            this.br.setVisibility(8);
        }
        this.D.setLayoutParams(layoutParams2);
        WindowManager windowManager = (WindowManager) getContext().getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        }
        if (displayMetrics.widthPixels + displayMetrics.heightPixels < 1280) {
            z = true;
        } else {
            ak<VideoData> videoBanner2 = iVar.getVideoBanner();
            if (videoBanner2 != null) {
                VideoData videoData = (VideoData) videoBanner2.getMediaData();
                if (videoData != null) {
                    height2 = videoData.getHeight();
                    width2 = videoData.getWidth();
                } else {
                    width2 = 0;
                    height2 = 0;
                }
            } else {
                ImageData image = iVar.getImage();
                if (image != null) {
                    height2 = image.getHeight();
                    width2 = image.getWidth();
                } else {
                    width2 = 0;
                    height2 = 0;
                }
            }
            if (height2 > 0 && width2 > 0 && (height2 > width2 || ((float) width2) / ((float) height2) < 1.4f)) {
                z = true;
            } else {
                z = false;
            }
        }
        this.bp.P();
        this.bp.setBanner(iVar);
        this.bn.a(displayMetrics.widthPixels, displayMetrics.heightPixels, z);
        this.bn.setBanner(iVar);
        this.bo.P();
        this.bo.a(iVar, 0);
        ImageData closeIcon = iVar.getCloseIcon();
        if (closeIcon != null && closeIcon.getData() != null) {
            this.D.a(closeIcon.getData(), true);
        } else {
            Bitmap bitmapH = bq.h(this.aC.l(28));
            if (bitmapH != null) {
                this.D.a(bitmapH, false);
            }
        }
        ImageData icon = iVar.getIcon();
        if (icon != null) {
            width = icon.getWidth();
            height = icon.getHeight();
        } else {
            width = 0;
            height = 0;
        }
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.bottomMargin = this.aC.l(4);
        if (width != 0 && height != 0) {
            int iL = (int) (this.aC.l(64) * (height / width));
            layoutParams3.width = this.bu;
            layoutParams3.height = iL;
            if (!z) {
                layoutParams3.bottomMargin = (-iL) / 2;
            }
        }
        layoutParams3.addRule(8, bg);
        if (Build.VERSION.SDK_INT >= 17) {
            layoutParams3.setMarginStart(this.aC.l(20));
        } else {
            layoutParams3.leftMargin = this.aC.l(20);
        }
        this.bm.setLayoutParams(layoutParams3);
        if (icon != null) {
            this.bm.setImageBitmap(icon.getData());
        }
        if (videoBanner != null && videoBanner.isAutoPlay()) {
            this.bo.S();
            post(new Runnable() { // from class: com.my.target.ed.1
                @Override // java.lang.Runnable
                public final void run() {
                    ed.this.bp.a(ed.this.br);
                }
            });
        }
        if (videoBanner != null) {
            this.by = videoBanner.getDuration();
        }
        bw bwVar = this.br;
        bwVar.setOnClickListener(new View.OnClickListener() { // from class: com.my.target.ed.2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                if (ed.this.O != null) {
                    ed.this.O.z();
                }
            }
        });
        bwVar.a(this.bs, false);
        bwVar.setContentDescription("sound_on");
    }

    @Override // com.my.target.eg
    public final void destroy() {
        this.bo.destroy();
    }

    @Override // com.my.target.eg
    public final void a(@NonNull com.my.target.core.models.banners.i iVar) {
        this.br.setVisibility(8);
        this.D.setVisibility(0);
        stop(false);
        this.bo.a(iVar);
    }

    @Override // com.my.target.eg
    public final boolean isPaused() {
        return this.bo.isPaused();
    }

    @Override // com.my.target.eh
    @NonNull
    public final View getCloseButton() {
        return this.D;
    }

    @Override // com.my.target.eg
    public final void play() {
        this.bp.b(this.br);
        this.bo.S();
    }

    @Override // com.my.target.eg
    public final void resume() {
        this.bp.b(this.br);
        this.bo.resume();
    }

    @Override // com.my.target.eg
    @NonNull
    public final ej getPromoMediaView() {
        return this.bo;
    }

    @Override // com.my.target.eg
    public final void stop(boolean z) {
        this.bq.setVisibility(8);
        this.bp.c(this.br);
        this.bo.f(z);
    }

    @Override // com.my.target.eh
    public final void setClickArea(@NonNull ag agVar) {
        g.a("Apply click area " + agVar.O() + " to view");
        if (agVar.cJ || agVar.cT) {
            this.bm.setOnClickListener(this.bl);
        } else {
            this.bm.setOnClickListener(null);
        }
        this.bn.a(agVar, this.bl);
        this.bp.a(agVar, this.bl);
        if (agVar.cK || agVar.cT) {
            this.bo.getClickableLayout().setOnClickListener(new View.OnClickListener() { // from class: com.my.target.ed.3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    if (ed.this.bz != null) {
                        ed.this.bz.K();
                    }
                }
            });
        } else {
            this.bo.getClickableLayout().setOnClickListener(null);
            this.bo.getClickableLayout().setEnabled(false);
        }
    }

    @Override // com.my.target.eg
    public final void setMediaListener(com.my.target.core.presenters.h.a aVar) {
        this.O = aVar;
        this.bo.setInterstitialPromoViewListener(aVar);
        this.bo.Q();
    }

    @Override // com.my.target.eh
    public final void N() {
        this.D.setVisibility(0);
    }

    @Override // com.my.target.eg
    public final void setTimeChanged(float f) {
        this.bq.setVisibility(0);
        float f2 = this.by;
        if (f2 > 0.0f) {
            this.bq.setProgress(f / f2);
        }
        this.bq.setDigit((int) ((this.by - f) + 1.0f));
    }

    @Override // com.my.target.eg
    public final boolean isPlaying() {
        return this.bo.isPlaying();
    }

    @Override // com.my.target.eg
    public final void pause() {
        this.bp.c(this.br);
        this.bo.pause();
    }

    @Override // com.my.target.eg
    public final void e(int i) {
        this.bo.e(i);
    }

    @Override // com.my.target.eg
    public final void O() {
        this.bo.O();
    }

    @Override // com.my.target.eg
    public final void e(boolean z) {
        if (z) {
            this.br.a(this.bt, false);
            this.br.setContentDescription("sound_off");
        } else {
            this.br.a(this.bs, false);
            this.br.setContentDescription("sound_on");
        }
    }

    @Override // com.my.target.eh
    public final void setInterstitialPromoViewListener(@Nullable eh.a aVar) {
        this.bz = aVar;
    }

    @Override // android.widget.RelativeLayout, android.view.View
    protected final void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        this.br.measure(i, i2);
        this.D.measure(i, i2);
        this.bq.measure(View.MeasureSpec.makeMeasureSpec(this.bw, 1073741824), View.MeasureSpec.makeMeasureSpec(this.bw, 1073741824));
        if (size2 > size) {
            this.bo.measure(View.MeasureSpec.makeMeasureSpec(size, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(size2, Integer.MIN_VALUE));
            this.bn.measure(View.MeasureSpec.makeMeasureSpec(size, 1073741824), View.MeasureSpec.makeMeasureSpec(size2 - this.bo.getMeasuredHeight(), Integer.MIN_VALUE));
            this.bm.measure(View.MeasureSpec.makeMeasureSpec(this.bu, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(size2, Integer.MIN_VALUE));
        } else {
            this.bo.measure(View.MeasureSpec.makeMeasureSpec(size, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(size2, Integer.MIN_VALUE));
            this.bp.measure(View.MeasureSpec.makeMeasureSpec(size, 1073741824), View.MeasureSpec.makeMeasureSpec(size2, Integer.MIN_VALUE));
        }
        setMeasuredDimension(i, i2);
    }

    @Override // android.widget.RelativeLayout, android.view.ViewGroup, android.view.View
    protected final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        bw bwVar = this.D;
        bwVar.layout(i3 - bwVar.getMeasuredWidth(), 0, i3, this.D.getMeasuredHeight());
        eb ebVar = this.bq;
        int i5 = this.bx;
        ebVar.layout(i5, i5, ebVar.getMeasuredWidth() + this.bx, this.bq.getMeasuredHeight() + this.bx);
        if (i4 > i3) {
            if (this.br.getTranslationY() > 0.0f) {
                this.br.setTranslationY(0.0f);
            }
            setBackgroundColor(-1);
            int measuredWidth = (i3 - this.bo.getMeasuredWidth()) / 2;
            ej ejVar = this.bo;
            ejVar.layout(measuredWidth, 0, ejVar.getMeasuredWidth() + measuredWidth, this.bo.getMeasuredHeight());
            this.bn.layout(0, this.bo.getBottom(), i3, i4);
            int bottom = this.bv;
            if (this.bo.getMeasuredHeight() != 0) {
                bottom = this.bo.getBottom() - (this.bm.getMeasuredHeight() / 2);
            }
            by byVar = this.bm;
            int i6 = this.bv;
            byVar.layout(i6, bottom, byVar.getMeasuredWidth() + i6, this.bm.getMeasuredHeight() + bottom);
            this.bp.layout(0, 0, 0, 0);
            bw bwVar2 = this.br;
            bwVar2.layout(i3 - bwVar2.getMeasuredWidth(), this.bo.getBottom() - this.br.getMeasuredHeight(), i3, this.bo.getBottom());
            return;
        }
        setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        int measuredWidth2 = (i3 - this.bo.getMeasuredWidth()) / 2;
        int measuredHeight = (i4 - this.bo.getMeasuredHeight()) / 2;
        ej ejVar2 = this.bo;
        ejVar2.layout(measuredWidth2, measuredHeight, ejVar2.getMeasuredWidth() + measuredWidth2, this.bo.getMeasuredHeight() + measuredHeight);
        this.bm.layout(0, 0, 0, 0);
        this.bn.layout(0, 0, 0, 0);
        ei eiVar = this.bp;
        eiVar.layout(0, i4 - eiVar.getMeasuredHeight(), i3, i4);
        bw bwVar3 = this.br;
        bwVar3.layout(i3 - bwVar3.getMeasuredWidth(), this.bp.getTop() - this.br.getMeasuredHeight(), i3, this.bp.getTop());
        if (this.bo.isPlaying()) {
            this.bp.a(this.br);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onAttachedToWindow() {
        super.onAttachedToWindow();
        boolean z = this.by <= 0.0f || isHardwareAccelerated();
        eh.a aVar = this.bz;
        if (aVar != null) {
            aVar.c(z);
        }
    }

    /* JADX INFO: compiled from: DefaultView.java */
    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            if (!view.isEnabled() || ed.this.bz == null) {
                return;
            }
            ed.this.bz.K();
        }
    }
}
