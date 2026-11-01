package com.my.target;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import com.facebook.appevents.AppEventsConstants;
import com.my.target.common.models.VideoData;

/* JADX INFO: compiled from: VideoStyleView.java */
/* JADX INFO: loaded from: classes2.dex */
@SuppressLint({"ViewConstructor"})
public final class el extends ViewGroup implements eg {

    @Nullable
    private com.my.target.core.presenters.h.a O;

    @NonNull
    private final ck aC;
    private boolean allowClose;

    @NonNull
    private final Button bQ;

    @NonNull
    private final ej bo;

    @NonNull
    private final bw br;

    @Nullable
    private final Bitmap bs;

    @Nullable
    private final Bitmap bt;
    private float by;

    @Nullable
    private eh.a bz;

    @NonNull
    private final ef cA;

    @NonNull
    private final ef cB;

    @NonNull
    private final Runnable cC;

    @NonNull
    private final d cD;

    @NonNull
    private final a cE;
    private final int cF;
    private int cG;
    private float cH;
    private boolean cI;
    private final int cJ;

    @Nullable
    private String cK;

    @Nullable
    private String cL;
    private boolean cM;

    @NonNull
    private final b cq;

    @NonNull
    private final TextView cr;

    @NonNull
    private final bz cs;

    @NonNull
    private final TextView ct;

    @NonNull
    private final LinearLayout cu;

    @NonNull
    private final TextView cv;

    @NonNull
    private final FrameLayout cw;

    @NonNull
    private final TextView cx;

    @NonNull
    private final eb cy;

    @NonNull
    private final ef cz;
    private final int padding;

    @Override // com.my.target.eh
    @NonNull
    public final View getView() {
        return this;
    }

    public el(@NonNull Context context, boolean z) {
        super(context);
        this.ct = new TextView(context);
        this.cr = new TextView(context);
        this.cs = new bz(context);
        this.bQ = new Button(context);
        this.cv = new TextView(context);
        this.cw = new FrameLayout(context);
        this.cz = new ef(context);
        this.cA = new ef(context);
        this.cB = new ef(context);
        this.cx = new TextView(context);
        byte b2 = 0;
        this.bo = new ej(context, ck.x(context), false, z);
        this.cy = new eb(context);
        this.br = new bw(context);
        this.cu = new LinearLayout(context);
        this.aC = ck.x(context);
        this.cC = new c(this, b2);
        this.cD = new d(this, b2);
        this.cE = new a(this, b2);
        ck.a(this.ct, "dismiss_button");
        ck.a(this.cr, "title_text");
        ck.a(this.cs, "stars_view");
        ck.a(this.bQ, "cta_button");
        ck.a(this.cv, "replay_text");
        ck.a(this.cw, "shadow");
        ck.a(this.cz, "pause_button");
        ck.a(this.cA, "play_button");
        ck.a(this.cB, "replay_button");
        ck.a(this.cx, "domain_text");
        ck.a(this.bo, "media_view");
        ck.a(this.cy, "video_progress_wheel");
        ck.a(this.br, "sound_button");
        this.cJ = this.aC.l(28);
        this.padding = this.aC.l(16);
        this.cF = this.aC.l(4);
        this.bs = com.my.target.core.resources.b.getVolumeOnIcon(this.aC.l(28));
        this.bt = com.my.target.core.resources.b.getVolumeOffIcon(this.aC.l(28));
        this.cq = new b();
        setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        int i = this.padding;
        this.br.setId(bG);
        this.bo.setOnClickListener(this.cD);
        this.bo.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        this.bo.P();
        this.cw.setBackgroundColor(-1728053248);
        this.cw.setVisibility(8);
        this.ct.setTextSize(2, 16.0f);
        this.ct.setTransformationMethod(null);
        this.ct.setEllipsize(TextUtils.TruncateAt.END);
        this.ct.setVisibility(8);
        if (Build.VERSION.SDK_INT >= 17) {
            this.ct.setTextAlignment(4);
        }
        this.ct.setTextColor(-1);
        ck.a(this.ct, -2013265920, -1, -1, this.aC.l(1), this.aC.l(4));
        this.cr.setMaxLines(2);
        this.cr.setEllipsize(TextUtils.TruncateAt.END);
        this.cr.setTextSize(2, 18.0f);
        this.cr.setTextColor(-1);
        ck.a(this.bQ, -2013265920, -1, -1, this.aC.l(1), this.aC.l(4));
        this.bQ.setTextColor(-1);
        this.bQ.setTransformationMethod(null);
        this.bQ.setGravity(1);
        this.bQ.setTextSize(2, 16.0f);
        this.bQ.setMinimumWidth(this.aC.l(100));
        this.bQ.setPadding(i, i, i, i);
        this.cr.setShadowLayer(this.aC.l(1), this.aC.l(1), this.aC.l(1), ViewCompat.MEASURED_STATE_MASK);
        this.cx.setTextColor(-3355444);
        this.cx.setMaxEms(10);
        this.cx.setShadowLayer(this.aC.l(1), this.aC.l(1), this.aC.l(1), ViewCompat.MEASURED_STATE_MASK);
        this.cu.setOnClickListener(this.cE);
        this.cu.setGravity(17);
        this.cu.setVisibility(8);
        this.cu.setPadding(this.aC.l(8), 0, this.aC.l(8), 0);
        this.cv.setSingleLine();
        this.cv.setEllipsize(TextUtils.TruncateAt.END);
        TextView textView = this.cv;
        textView.setTypeface(textView.getTypeface(), 1);
        this.cv.setTextColor(-1);
        this.cv.setTextSize(2, 16.0f);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.leftMargin = this.aC.l(4);
        this.cB.setPadding(this.aC.l(16), this.aC.l(16), this.aC.l(16), this.aC.l(16));
        this.cz.setOnClickListener(this.cE);
        this.cz.setVisibility(8);
        this.cz.setPadding(this.aC.l(16), this.aC.l(16), this.aC.l(16), this.aC.l(16));
        this.cA.setOnClickListener(this.cE);
        this.cA.setVisibility(8);
        this.cA.setPadding(this.aC.l(16), this.aC.l(16), this.aC.l(16), this.aC.l(16));
        Context context2 = getContext();
        byte[] bArrDecode = Base64.decode("iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAYAAACqaXHeAAAABmJLR0QA/wD/AP+gvaeTAAAACXBIWXMAAAsTAAALEwEAmpwYAAAAB3RJTUUH4AMXCy8fw79+rQAAAhVJREFUeNrt2y9IXlEYx3H3ooIiiCAIC4JgMRgsCyaLwWaxLK0srZhWVtYWVtYWlpYMNsvK0sKKRTANBivDIIggIiLiZ+URDncHFgzbznN+8d77nvPwvec99zz/xsa6uv4oPMWjzADgK55kBnCvj3icGQBc4hWmsgK41w/sZAPwswLiC9ayAJjGa1wNrt/hAxaaBlBcW8ReZTVc4CUmmwZQ3FvHYQXEd2w3DyDuj/AMJxUQn7HaNIDiuRm8wfUAwi3eY75pAMXzS9ivrIZz7GKiaQDF7zZwVAHxDVvNAyj2h+c4rYD4hJWmARRjzOItbir7wzvMNQ2gGGsZB5XVcIYXGG8aQDHmJo4rII6x2TyAGHc83vpZBcQBlpsGUIw/F/vA7QDCTewbs00DKOZZiS/DUKfxJRk1DaCYbyvOCkMdYaN5ADHnRJwazysg9rHUNIBi7vnwI4b7w3X4HTNNAyhsWA3PcqiT8ERHTQMobNmOWMNQh1hvHkDYMxlRp4sKiD0sNg2gsGsh4pB3AwhXEbecbhpAYd9aRKZVItgPT+v96wAKO3ciVzHUw9J6/wuAsHUqslaXFRC/pfVGY139L9A3wf4Z7AehfhTuzlB3h3tApIfE/jqAtEHRtGHx1ImRtKmxtMnRtOnx1AUSaUtk0hZJpS2TS10ombZUNm2xdPpy+d4w0VtmetNU2ra51I2TuVtnuxrWL/YiKQ6CN9uRAAAAAElFTkSuQmCC", 0);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inDensity = 420;
        options.inTargetDensity = context2.getResources().getDisplayMetrics().densityDpi;
        Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArrDecode, 0, bArrDecode.length, options);
        if (bitmapDecodeByteArray != null) {
            this.cA.setImageBitmap(bitmapDecodeByteArray);
        }
        Context context3 = getContext();
        byte[] bArrDecode2 = Base64.decode("iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAYAAACqaXHeAAAABmJLR0QA/wD/AP+gvaeTAAAACXBIWXMAAAsTAAALEwEAmpwYAAAAB3RJTUUH4AMXCjITNKc0rQAAAJFJREFUeNrt2tENgCAMQEEwLuD+QzpC3cBURWLsvV+JNRfhi9YkSSpbP3sYETF0WO89s27m3KX6H1AeYL2wdrs5Y3/4ja/OTZ8B2f074h0z5zoDAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA/lr6rvDoK+xfmWsLNEmSVLUD47EiX/OuE8UAAAAASUVORK5CYII=", 0);
        BitmapFactory.Options options2 = new BitmapFactory.Options();
        options2.inDensity = 420;
        options2.inTargetDensity = context3.getResources().getDisplayMetrics().densityDpi;
        Bitmap bitmapDecodeByteArray2 = BitmapFactory.decodeByteArray(bArrDecode2, 0, bArrDecode2.length, options2);
        if (bitmapDecodeByteArray2 != null) {
            this.cz.setImageBitmap(bitmapDecodeByteArray2);
        }
        ck.a(this.cz, -2013265920, -1, -1, this.aC.l(1), this.aC.l(4));
        ck.a(this.cA, -2013265920, -1, -1, this.aC.l(1), this.aC.l(4));
        ck.a(this.cB, -2013265920, -1, -1, this.aC.l(1), this.aC.l(4));
        this.cs.setStarSize(this.aC.l(12));
        this.cy.setVisibility(8);
        addView(this.bo);
        addView(this.cw);
        addView(this.br);
        addView(this.ct);
        addView(this.cy);
        addView(this.cu);
        addView(this.cz);
        addView(this.cA);
        addView(this.cs);
        addView(this.cx);
        addView(this.bQ);
        addView(this.cr);
        this.cu.addView(this.cB);
        this.cu.addView(this.cv, layoutParams);
    }

    @Override // com.my.target.eh
    public final void setBanner(@NonNull com.my.target.core.models.banners.i iVar) {
        this.bo.a(iVar, 1);
        ak<VideoData> videoBanner = iVar.getVideoBanner();
        if (videoBanner == null) {
            return;
        }
        this.cy.setMax(iVar.getDuration());
        this.cI = videoBanner.isAllowReplay();
        this.cH = iVar.getAllowCloseDelay();
        this.allowClose = iVar.isAllowClose();
        this.bQ.setText(iVar.getCtaText());
        this.cr.setText(iVar.getTitle());
        if ("store".equals(iVar.getNavigationType())) {
            if (iVar.getRating() > 0.0f) {
                this.cs.setVisibility(0);
                this.cs.setRating(iVar.getRating());
            } else {
                this.cs.setVisibility(8);
            }
            this.cx.setVisibility(8);
        } else {
            this.cs.setVisibility(8);
            this.cx.setVisibility(0);
            this.cx.setText(iVar.getDomain());
        }
        this.cK = videoBanner.getCloseActionText();
        this.cL = videoBanner.getCloseDelayActionText();
        this.ct.setText(this.cK);
        if (videoBanner.isAllowClose()) {
            if (videoBanner.getAllowCloseDelay() > 0.0f) {
                this.cH = videoBanner.getAllowCloseDelay();
                this.ct.setEnabled(false);
                this.ct.setTextColor(-3355444);
                TextView textView = this.ct;
                int i = this.cF;
                textView.setPadding(i, i, i, i);
                ck.a(this.ct, -2013265920, -2013265920, -3355444, this.aC.l(1), this.aC.l(4));
                this.ct.setTextSize(2, 12.0f);
            } else {
                TextView textView2 = this.ct;
                int i2 = this.padding;
                textView2.setPadding(i2, i2, i2, i2);
                this.ct.setVisibility(0);
            }
        }
        this.cv.setText(videoBanner.getReplayActionText());
        Context context = getContext();
        byte[] bArrDecode = Base64.decode("iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAYAAACqaXHeAAAABmJLR0QA/wD/AP+gvaeTAAAACXBIWXMAAAsTAAALEwEAmpwYAAAAB3RJTUUH4AMXCjM59gfMOgAAA59JREFUeNrtmkloFEEUhl/N6KgxriiJOHEXF4gENYlgRFA8uyAoCNGggl68CCJ6EQx6cCFqUFzABQQRMYh4EfSi4IJbiFERQVxRgxuRMWri5yE1UBY9yWh6Znq6+z+96a6ZV/8/tbz3qkRChAgRwiMAFgJngWgQyVcDHXTiFKCCRH4Df6MFKAkK+W0W+VfAxCAQV8A+i/wTIB4E8lHgpEX+LjA8COQHAM8t8o3ATKC338kXA59IjXbgEVAPLAb6+k2AHRbhVrrGV+AwMNVPIpx3INncjRi/gTPAWL+IcMIieBGIAXFgGXAU+OggRALY7ItIEdhvkTsNRIz3MWCp3h1sXAGK/RAHnLKIHUzRdgnwwmr7Epjsh3jggkWsNkXbQuC4Q8g8Ld9F6ANctYht7KL9GiNpAnib94ujDo5uaUIdwMo00uafhghNQL98F2EYcA9YlGb75daoOeqHnSHyj+23WyLMC1oRJQpct7LJXkETYSrwyxBhlQQNwAFrFKigCTDK2hXmBnEUnDMEOBZEARYaArwLogAF1jRIq4YQ8YsASqmEiNw2HpUHSgCNB4Y9KYgCPDXs8UEU4LNhF6bzhW7DRmCEiFTqjx+VUtc8LECrYfd3RQA9lxq03SgiZR4WIGbYv9yaAt8Me5DHp8DAFP3ukQDvDTvu8WxrnGG/dUUApdQrEUkYU2a8hwWYnGJH6PEu0GzYczwaCSoRqTIeNbkpwFXDnu/Rf79URJKnyz9E5Kab6i4wYuxvQKEHR8BO8+DE7R+PAu8MB6s9Rj4GvDH6tzYTTvZaVZeIhwSoMfr2HRiUCSd21aXGI+T7WRcu6jLp7Ijh6AMw1AMCmKXxtozeLgOKgC+Gw4ZcFiCB2fpWSRLbsuF0nXUYsSlH5EfqE+IknmblSo0+1m6wRKjOMvnB+jzQHPpl2ezAEOCx0YEOYH2WfMct8rlZkIES4LXVkXqgTwZ9Vjn43JrLFXgC8Mzq0H2g0mU/BTrSa7d8bfHCPlwE3HC41XWyp9fbdIS3Tt8fxprzq7wUivYGdmnithCXgRXpxgw67K4A9ljhdxKPgVK3+q5cFqJcRA6JyAyn1zpFfaBz9RZdtYnpStNonc/PEpEhDt9vE5FaEdmtlPrh2YoEEAEWpbje9j9o1aNrhOQbgOlAncNC2R0SwCVgZaZTb5VFMUpEpEJEpojIGBEZLJ21+zbpLGe3iMgTEXkoIneUUj8lRIgQIUKECJFJ/AEepzU1TSID5QAAAABJRU5ErkJggg==", 0);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inDensity = 420;
        options.inTargetDensity = context.getResources().getDisplayMetrics().densityDpi;
        Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArrDecode, 0, bArrDecode.length, options);
        if (bitmapDecodeByteArray != null) {
            this.cB.setImageBitmap(bitmapDecodeByteArray);
        }
        if (videoBanner.isAutoPlay()) {
            this.bo.S();
            T();
        } else {
            L();
        }
        this.by = videoBanner.getDuration();
        bw bwVar = this.br;
        bwVar.setOnClickListener(new View.OnClickListener() { // from class: com.my.target.el.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                if (el.this.O != null) {
                    el.this.O.z();
                }
            }
        });
        bwVar.a(this.bs, false);
        bwVar.setContentDescription("sound_on");
    }

    @Override // com.my.target.eh
    public final void N() {
        this.ct.setText(this.cK);
        this.ct.setTextSize(2, 16.0f);
        this.ct.setVisibility(0);
        this.ct.setTextColor(-1);
        this.ct.setEnabled(true);
        TextView textView = this.ct;
        int i = this.padding;
        textView.setPadding(i, i, i, i);
        ck.a(this.ct, -2013265920, -1, -1, this.aC.l(1), this.aC.l(4));
        this.cM = true;
    }

    @Override // com.my.target.eg
    public final void finish() {
        this.cy.setVisibility(8);
        this.cG = 4;
        if (this.cI) {
            this.cu.setVisibility(0);
            this.cw.setVisibility(0);
        }
        this.cA.setVisibility(8);
        this.cz.setVisibility(8);
    }

    @Override // com.my.target.eg
    @NonNull
    public final ej getPromoMediaView() {
        return this.bo;
    }

    @Override // com.my.target.eg
    public final void setTimeChanged(float f) {
        if (!this.cM && this.allowClose) {
            float f2 = this.cH;
            if (f2 > 0.0f && f2 >= f) {
                if (this.ct.getVisibility() != 0) {
                    this.ct.setVisibility(0);
                }
                if (this.cL != null) {
                    int iCeil = (int) Math.ceil(this.cH - f);
                    String strValueOf = String.valueOf(iCeil);
                    if (this.cH > 9.0f && iCeil <= 9) {
                        strValueOf = AppEventsConstants.EVENT_PARAM_VALUE_NO.concat(String.valueOf(strValueOf));
                    }
                    this.ct.setText(this.cL.replace("%d", strValueOf));
                }
            }
        }
        if (this.cy.getVisibility() != 0) {
            this.cy.setVisibility(0);
        }
        this.cy.setProgress(f / this.by);
        this.cy.setDigit((int) Math.ceil(this.by - f));
    }

    @Override // com.my.target.eg
    public final void destroy() {
        this.bo.destroy();
    }

    @Override // com.my.target.eg
    public final void a(@NonNull com.my.target.core.models.banners.i iVar) {
        this.bo.setOnClickListener(null);
        this.br.setVisibility(8);
        N();
        T();
    }

    @Override // com.my.target.eg
    public final void e(boolean z) {
        bw bwVar = this.br;
        if (z) {
            bwVar.a(this.bt, false);
            bwVar.setContentDescription("sound_off");
        } else {
            bwVar.a(this.bs, false);
            bwVar.setContentDescription("sound_on");
        }
    }

    @Override // com.my.target.eh
    public final void setInterstitialPromoViewListener(@Nullable eh.a aVar) {
        this.bz = aVar;
    }

    @Override // android.view.View
    protected final void onMeasure(int i, int i2) {
        this.br.measure(View.MeasureSpec.makeMeasureSpec(this.cJ, 1073741824), View.MeasureSpec.makeMeasureSpec(this.cJ, 1073741824));
        this.cy.measure(View.MeasureSpec.makeMeasureSpec(this.cJ, 1073741824), View.MeasureSpec.makeMeasureSpec(this.cJ, 1073741824));
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        this.bo.measure(View.MeasureSpec.makeMeasureSpec(size, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(size2, Integer.MIN_VALUE));
        int i3 = this.padding;
        int i4 = size - (i3 << 1);
        int i5 = size2 - (i3 << 1);
        this.ct.measure(View.MeasureSpec.makeMeasureSpec(i4 / 2, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(i5, Integer.MIN_VALUE));
        this.cz.measure(View.MeasureSpec.makeMeasureSpec(i4, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(i5, Integer.MIN_VALUE));
        this.cA.measure(View.MeasureSpec.makeMeasureSpec(i4, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(i5, Integer.MIN_VALUE));
        this.cu.measure(View.MeasureSpec.makeMeasureSpec(i4, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(i5, Integer.MIN_VALUE));
        this.cs.measure(View.MeasureSpec.makeMeasureSpec(i4, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(i5, Integer.MIN_VALUE));
        this.cw.measure(View.MeasureSpec.makeMeasureSpec(this.bo.getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(this.bo.getMeasuredHeight(), 1073741824));
        this.bQ.measure(View.MeasureSpec.makeMeasureSpec(i4, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(i5, Integer.MIN_VALUE));
        this.cr.measure(View.MeasureSpec.makeMeasureSpec(i4, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(i5, Integer.MIN_VALUE));
        this.cx.measure(View.MeasureSpec.makeMeasureSpec(i4, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(i5, Integer.MIN_VALUE));
        if (size > size2) {
            int measuredWidth = this.bQ.getMeasuredWidth();
            int measuredWidth2 = this.cr.getMeasuredWidth();
            if (this.cy.getMeasuredWidth() + measuredWidth2 + Math.max(this.cs.getMeasuredWidth(), this.cx.getMeasuredWidth()) + measuredWidth + (this.padding * 3) > i4) {
                int measuredWidth3 = (i4 - this.cy.getMeasuredWidth()) - (this.padding * 3);
                int i6 = measuredWidth3 / 3;
                this.bQ.measure(View.MeasureSpec.makeMeasureSpec(i6, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(i5, Integer.MIN_VALUE));
                this.cs.measure(View.MeasureSpec.makeMeasureSpec(i6, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(i5, Integer.MIN_VALUE));
                this.cx.measure(View.MeasureSpec.makeMeasureSpec(i6, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(i5, Integer.MIN_VALUE));
                this.cr.measure(View.MeasureSpec.makeMeasureSpec(((measuredWidth3 - this.bQ.getMeasuredWidth()) - this.cx.getMeasuredWidth()) - this.cs.getMeasuredWidth(), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(i5, Integer.MIN_VALUE));
            }
        } else {
            int measuredHeight = this.cr.getMeasuredHeight() + this.cs.getMeasuredHeight() + this.cx.getMeasuredHeight() + this.bQ.getMeasuredHeight();
            int measuredHeight2 = (size2 - this.bo.getMeasuredHeight()) / 2;
            int i7 = this.padding;
            if (measuredHeight + (i7 * 3) > measuredHeight2) {
                this.bQ.setPadding(i7, i7 / 2, i7, i7 / 2);
                this.bQ.measure(View.MeasureSpec.makeMeasureSpec(i4, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(i5, Integer.MIN_VALUE));
            }
        }
        setMeasuredDimension(size, size2);
    }

    @Override // com.my.target.eg
    public final boolean isPlaying() {
        return this.bo.isPlaying();
    }

    @Override // com.my.target.eg
    public final boolean isPaused() {
        return this.bo.isPaused();
    }

    @Override // com.my.target.eh
    @NonNull
    public final View getCloseButton() {
        return this.ct;
    }

    @Override // com.my.target.eg
    public final void play() {
        this.bo.S();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5 = i3 - i;
        int i6 = i4 - i2;
        int measuredWidth = this.bo.getMeasuredWidth();
        int measuredHeight = this.bo.getMeasuredHeight();
        int i7 = (i5 - measuredWidth) >> 1;
        int i8 = (i6 - measuredHeight) >> 1;
        this.bo.layout(i7, i8, measuredWidth + i7, measuredHeight + i8);
        this.cw.layout(this.bo.getLeft(), this.bo.getTop(), this.bo.getRight(), this.bo.getBottom());
        int measuredWidth2 = this.cA.getMeasuredWidth();
        int i9 = i3 >> 1;
        int i10 = measuredWidth2 >> 1;
        int i11 = i4 >> 1;
        int measuredHeight2 = this.cA.getMeasuredHeight() >> 1;
        this.cA.layout(i9 - i10, i11 - measuredHeight2, i10 + i9, measuredHeight2 + i11);
        int measuredWidth3 = this.cz.getMeasuredWidth();
        int i12 = measuredWidth3 >> 1;
        int measuredHeight3 = this.cz.getMeasuredHeight() >> 1;
        this.cz.layout(i9 - i12, i11 - measuredHeight3, i12 + i9, measuredHeight3 + i11);
        int measuredWidth4 = this.cu.getMeasuredWidth();
        int i13 = measuredWidth4 >> 1;
        int measuredHeight4 = this.cu.getMeasuredHeight() >> 1;
        this.cu.layout(i9 - i13, i11 - measuredHeight4, i9 + i13, i11 + measuredHeight4);
        TextView textView = this.ct;
        int i14 = this.padding;
        textView.layout(i14, i14, textView.getMeasuredWidth() + i14, this.padding + this.ct.getMeasuredHeight());
        if (i5 > i6) {
            int iMax = Math.max(this.bQ.getMeasuredHeight(), Math.max(this.cr.getMeasuredHeight(), this.cs.getMeasuredHeight()));
            Button button = this.bQ;
            int measuredWidth5 = (i5 - this.padding) - button.getMeasuredWidth();
            int measuredHeight5 = ((i6 - this.padding) - this.bQ.getMeasuredHeight()) - ((iMax - this.bQ.getMeasuredHeight()) >> 1);
            int i15 = this.padding;
            button.layout(measuredWidth5, measuredHeight5, i5 - i15, (i6 - i15) - ((iMax - this.bQ.getMeasuredHeight()) >> 1));
            this.br.layout((this.bQ.getRight() - this.br.getMeasuredWidth()) + this.br.getPadding(), (((this.bo.getBottom() - (this.padding << 1)) - this.br.getMeasuredHeight()) - iMax) + this.br.getPadding(), this.bQ.getRight() + this.br.getPadding(), ((this.bo.getBottom() - (this.padding << 1)) - iMax) + this.br.getPadding());
            bz bzVar = this.cs;
            int left = (this.bQ.getLeft() - this.padding) - this.cs.getMeasuredWidth();
            int measuredHeight6 = ((i6 - this.padding) - this.cs.getMeasuredHeight()) - ((iMax - this.cs.getMeasuredHeight()) >> 1);
            int left2 = this.bQ.getLeft();
            int i16 = this.padding;
            bzVar.layout(left, measuredHeight6, left2 - i16, (i6 - i16) - ((iMax - this.cs.getMeasuredHeight()) >> 1));
            TextView textView2 = this.cx;
            int left3 = (this.bQ.getLeft() - this.padding) - this.cx.getMeasuredWidth();
            int measuredHeight7 = ((i6 - this.padding) - this.cx.getMeasuredHeight()) - ((iMax - this.cx.getMeasuredHeight()) >> 1);
            int left4 = this.bQ.getLeft();
            int i17 = this.padding;
            textView2.layout(left3, measuredHeight7, left4 - i17, (i6 - i17) - ((iMax - this.cx.getMeasuredHeight()) >> 1));
            int iMin = Math.min(this.cs.getLeft(), this.cx.getLeft());
            TextView textView3 = this.cr;
            int measuredWidth6 = (iMin - this.padding) - textView3.getMeasuredWidth();
            int measuredHeight8 = ((i6 - this.padding) - this.cr.getMeasuredHeight()) - ((iMax - this.cr.getMeasuredHeight()) >> 1);
            int i18 = this.padding;
            textView3.layout(measuredWidth6, measuredHeight8, iMin - i18, (i6 - i18) - ((iMax - this.cr.getMeasuredHeight()) >> 1));
            eb ebVar = this.cy;
            int i19 = this.padding;
            ebVar.layout(i19, ((i6 - i19) - ebVar.getMeasuredHeight()) - ((iMax - this.cy.getMeasuredHeight()) >> 1), this.padding + this.cy.getMeasuredWidth(), (i6 - this.padding) - ((iMax - this.cy.getMeasuredHeight()) >> 1));
            return;
        }
        this.br.layout(((this.bo.getRight() - this.padding) - this.br.getMeasuredWidth()) + this.br.getPadding(), ((this.bo.getBottom() - this.padding) - this.br.getMeasuredHeight()) + this.br.getPadding(), (this.bo.getRight() - this.padding) + this.br.getPadding(), (this.bo.getBottom() - this.padding) + this.br.getPadding());
        int i20 = this.padding;
        int measuredHeight9 = this.cr.getMeasuredHeight() + this.cs.getMeasuredHeight() + this.cx.getMeasuredHeight() + this.bQ.getMeasuredHeight();
        int bottom = getBottom() - this.bo.getBottom();
        if ((i20 * 3) + measuredHeight9 > bottom) {
            i20 = (bottom - measuredHeight9) / 3;
        }
        TextView textView4 = this.cr;
        int i21 = i5 >> 1;
        textView4.layout(i21 - (textView4.getMeasuredWidth() >> 1), this.bo.getBottom() + i20, (this.cr.getMeasuredWidth() >> 1) + i21, this.bo.getBottom() + i20 + this.cr.getMeasuredHeight());
        bz bzVar2 = this.cs;
        bzVar2.layout(i21 - (bzVar2.getMeasuredWidth() >> 1), this.cr.getBottom() + i20, (this.cs.getMeasuredWidth() >> 1) + i21, this.cr.getBottom() + i20 + this.cs.getMeasuredHeight());
        TextView textView5 = this.cx;
        textView5.layout(i21 - (textView5.getMeasuredWidth() >> 1), this.cr.getBottom() + i20, (this.cx.getMeasuredWidth() >> 1) + i21, this.cr.getBottom() + i20 + this.cx.getMeasuredHeight());
        Button button2 = this.bQ;
        button2.layout(i21 - (button2.getMeasuredWidth() >> 1), this.cs.getBottom() + i20, i21 + (this.bQ.getMeasuredWidth() >> 1), this.cs.getBottom() + i20 + this.bQ.getMeasuredHeight());
        this.cy.layout(this.padding, (this.bo.getBottom() - this.padding) - this.cy.getMeasuredHeight(), this.padding + this.cy.getMeasuredWidth(), this.bo.getBottom() - this.padding);
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

    @Override // com.my.target.eg
    public final void setMediaListener(@Nullable com.my.target.core.presenters.h.a aVar) {
        this.O = aVar;
        this.bo.setInterstitialPromoViewListener(aVar);
    }

    @Override // com.my.target.eg
    public final void resume() {
        this.bo.resume();
    }

    @Override // com.my.target.eh
    public final void setClickArea(@NonNull ag agVar) {
        g.a("Apply click area " + agVar.O() + " to view");
        if (agVar.cT) {
            setOnClickListener(this.cq);
        }
        if (agVar.cN || agVar.cT) {
            this.bQ.setOnClickListener(this.cq);
        } else {
            this.bQ.setOnClickListener(null);
            this.bQ.setEnabled(false);
        }
        if (agVar.cH || agVar.cT) {
            this.cr.setOnClickListener(this.cq);
        } else {
            this.cr.setOnClickListener(null);
        }
        if (agVar.cL || agVar.cT) {
            this.cs.setOnClickListener(this.cq);
        } else {
            this.cs.setOnClickListener(null);
        }
        if (agVar.cQ || agVar.cT) {
            this.cx.setOnClickListener(this.cq);
        } else {
            this.cx.setOnClickListener(null);
        }
        if (agVar.cS || agVar.cT) {
            setOnClickListener(this.cq);
        }
    }

    @Override // com.my.target.eg
    public final void stop(boolean z) {
        this.bo.f(true);
    }

    @Override // com.my.target.eg
    public final void pause() {
        int i = this.cG;
        if (i == 0 || i == 2) {
            L();
            this.bo.pause();
        }
    }

    @Override // com.my.target.eg
    public final void e(int i) {
        this.bo.e(i);
    }

    @Override // com.my.target.eg
    public final void O() {
        this.bo.O();
        this.cu.setVisibility(8);
        this.cA.setVisibility(8);
        if (this.cG != 2) {
            this.cz.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void T() {
        this.cG = 0;
        this.cu.setVisibility(8);
        this.cA.setVisibility(8);
        this.cz.setVisibility(8);
        this.cw.setVisibility(8);
    }

    private void L() {
        this.cG = 1;
        this.cu.setVisibility(8);
        this.cA.setVisibility(0);
        this.cz.setVisibility(8);
        this.cw.setVisibility(0);
    }

    /* JADX INFO: compiled from: VideoStyleView.java */
    private class a implements View.OnClickListener {
        private a() {
        }

        /* synthetic */ a(el elVar, byte b) {
            this();
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            if (view == el.this.cu) {
                if (el.this.O != null) {
                    el.this.O.y();
                }
                el.this.T();
            } else {
                if (view == el.this.cz) {
                    if (!el.this.bo.isPlaying() || el.this.O == null) {
                        return;
                    }
                    el.this.O.A();
                    return;
                }
                if (view == el.this.cA) {
                    if (el.this.O != null) {
                        if (el.this.isPaused()) {
                            el.this.O.B();
                        } else {
                            el.this.O.y();
                        }
                    }
                    el.this.T();
                }
            }
        }
    }

    /* JADX INFO: compiled from: VideoStyleView.java */
    class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            if (!view.isEnabled() || el.this.bz == null) {
                return;
            }
            el.this.bz.K();
        }
    }

    /* JADX INFO: compiled from: VideoStyleView.java */
    private class c implements Runnable {
        private c() {
        }

        /* synthetic */ c(el elVar, byte b) {
            this();
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (el.this.cG == 2 || el.this.cG == 0) {
                el.this.T();
            }
        }
    }

    /* JADX INFO: compiled from: VideoStyleView.java */
    private class d implements View.OnClickListener {
        private d() {
        }

        /* synthetic */ d(el elVar, byte b) {
            this();
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            el elVar = el.this;
            elVar.removeCallbacks(elVar.cC);
            if (el.this.cG == 2) {
                el.this.T();
                return;
            }
            if (el.this.cG == 0 || el.this.cG == 3) {
                el.j(el.this);
            }
            el elVar2 = el.this;
            elVar2.postDelayed(elVar2.cC, 4000L);
        }
    }

    static /* synthetic */ void j(el elVar) {
        elVar.cG = 2;
        elVar.cu.setVisibility(8);
        elVar.cA.setVisibility(8);
        elVar.cz.setVisibility(0);
        elVar.cw.setVisibility(8);
    }
}
