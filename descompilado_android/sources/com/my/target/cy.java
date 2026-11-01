package com.my.target;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Base64;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import com.my.target.common.models.ImageData;
import com.my.target.common.models.VideoData;
import com.my.target.nativeads.views.MediaAdView;

/* JADX INFO: compiled from: VideoDialogView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class cy extends ViewGroup {

    @Nullable
    private d aA;

    @NonNull
    private final TextView af;

    @NonNull
    private final bz ag;

    @NonNull
    private final Button ah;

    @NonNull
    private final LinearLayout ai;

    @NonNull
    private final TextView aj;

    @NonNull
    private final FrameLayout ak;

    @NonNull
    private final TextView al;

    @NonNull
    private final cz am;

    @NonNull
    private final bw an;

    @NonNull
    private final TextureView ao;

    @NonNull
    private final cx ap;

    @NonNull
    private final cx aq;

    @NonNull
    private final cx ar;

    @NonNull
    private final Runnable as;

    @NonNull
    private final c at;

    @NonNull
    private final View.OnClickListener au;

    @Nullable
    private final Bitmap av;

    @Nullable
    private final Bitmap aw;
    private int ax;
    private final int ay;
    private boolean az;

    @NonNull
    private final Button ctaButton;

    @NonNull
    private final MediaAdView mediaAdView;
    private final int padding;

    @NonNull
    private final ck uiUtils;
    private static final int W = ck.br();
    private static final int a_ = ck.br();
    private static final int b_ = ck.br();
    private static final int c_ = ck.br();
    private static final int aa = ck.br();
    private static final int ab = ck.br();
    private static final int TITLE_ID = ck.br();
    private static final int ac = ck.br();
    private static final int ad = ck.br();
    private static final int SOUND_BUTTON_ID = ck.br();
    private static final int ae = ck.br();

    /* JADX INFO: compiled from: VideoDialogView.java */
    public interface d {
        void a(View view);

        void i();

        void j();

        void k();

        void l();

        void m();
    }

    public cy(@NonNull Context context) {
        super(context);
        this.ah = new Button(context);
        this.af = new TextView(context);
        this.ag = new bz(context);
        this.ctaButton = new Button(context);
        this.aj = new TextView(context);
        this.ak = new FrameLayout(context);
        this.ap = new cx(context);
        this.aq = new cx(context);
        this.ar = new cx(context);
        this.al = new TextView(context);
        this.mediaAdView = new MediaAdView(context);
        this.am = new cz(context);
        this.an = new bw(context);
        this.ai = new LinearLayout(context);
        this.uiUtils = ck.x(context);
        byte b2 = 0;
        this.as = new b(this, b2);
        this.at = new c(this, b2);
        this.au = new a(this, b2);
        this.ao = new TextureView(context);
        this.av = com.my.target.core.resources.a.getVolumeOnIcon(this.uiUtils.l(28));
        this.aw = com.my.target.core.resources.a.getVolumeOffIcon(this.uiUtils.l(28));
        ck.a(this.ah, "dismiss_button");
        ck.a(this.af, "title_text");
        ck.a(this.ag, "stars_view");
        ck.a(this.ctaButton, "cta_button");
        ck.a(this.aj, "replay_text");
        ck.a(this.ak, "shadow");
        ck.a(this.ap, "pause_button");
        ck.a(this.aq, "play_button");
        ck.a(this.ar, "replay_button");
        ck.a(this.al, "domain_text");
        ck.a(this.mediaAdView, "media_view");
        ck.a(this.am, "video_progress_wheel");
        ck.a(this.an, "sound_button");
        this.ay = this.uiUtils.l(28);
        this.padding = this.uiUtils.l(16);
        setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        int i = this.padding;
        this.an.setId(SOUND_BUTTON_ID);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13, -1);
        this.mediaAdView.setLayoutParams(layoutParams);
        this.mediaAdView.setId(ad);
        this.mediaAdView.setOnClickListener(this.at);
        this.mediaAdView.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        this.ak.setBackgroundColor(-1728053248);
        this.ak.setVisibility(8);
        this.ah.setId(W);
        this.ah.setTextSize(2, 16.0f);
        this.ah.setTransformationMethod(null);
        this.ah.setEllipsize(TextUtils.TruncateAt.END);
        this.ah.setMaxLines(2);
        this.ah.setPadding(i, i, i, i);
        this.ah.setTextColor(-1);
        ck.a(this.ah, -2013265920, -1, -1, this.uiUtils.l(1), this.uiUtils.l(4));
        this.af.setId(TITLE_ID);
        this.af.setMaxLines(2);
        this.af.setEllipsize(TextUtils.TruncateAt.END);
        this.af.setTextSize(2, 18.0f);
        this.af.setTextColor(-1);
        ck.a(this.ctaButton, -2013265920, -1, -1, this.uiUtils.l(1), this.uiUtils.l(4));
        this.ctaButton.setId(a_);
        this.ctaButton.setTextColor(-1);
        this.ctaButton.setTransformationMethod(null);
        this.ctaButton.setGravity(1);
        this.ctaButton.setTextSize(2, 16.0f);
        this.ctaButton.setMinimumWidth(this.uiUtils.l(100));
        this.ctaButton.setPadding(i, i, i, i);
        this.af.setShadowLayer(this.uiUtils.l(1), this.uiUtils.l(1), this.uiUtils.l(1), ViewCompat.MEASURED_STATE_MASK);
        this.al.setId(ac);
        this.al.setTextColor(-3355444);
        this.al.setMaxEms(10);
        this.al.setShadowLayer(this.uiUtils.l(1), this.uiUtils.l(1), this.uiUtils.l(1), ViewCompat.MEASURED_STATE_MASK);
        this.ai.setId(b_);
        this.ai.setOnClickListener(this.au);
        this.ai.setGravity(17);
        this.ai.setVisibility(8);
        this.ai.setPadding(this.uiUtils.l(8), 0, this.uiUtils.l(8), 0);
        this.aj.setSingleLine();
        this.aj.setEllipsize(TextUtils.TruncateAt.END);
        TextView textView = this.aj;
        textView.setTypeface(textView.getTypeface(), 1);
        this.aj.setTextColor(-1);
        this.aj.setTextSize(2, 16.0f);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams2.leftMargin = this.uiUtils.l(4);
        this.ar.setPadding(this.uiUtils.l(16), this.uiUtils.l(16), this.uiUtils.l(16), this.uiUtils.l(16));
        this.ap.setId(aa);
        this.ap.setOnClickListener(this.au);
        this.ap.setVisibility(8);
        this.ap.setPadding(this.uiUtils.l(16), this.uiUtils.l(16), this.uiUtils.l(16), this.uiUtils.l(16));
        this.aq.setId(c_);
        this.aq.setOnClickListener(this.au);
        this.aq.setVisibility(8);
        this.aq.setPadding(this.uiUtils.l(16), this.uiUtils.l(16), this.uiUtils.l(16), this.uiUtils.l(16));
        this.ak.setId(ae);
        Context context2 = getContext();
        byte[] bArrDecode = Base64.decode("iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAYAAACqaXHeAAAABmJLR0QA/wD/AP+gvaeTAAAACXBIWXMAAAsTAAALEwEAmpwYAAAAB3RJTUUH4AMXCy8fw79+rQAAAhVJREFUeNrt2y9IXlEYx3H3ooIiiCAIC4JgMRgsCyaLwWaxLK0srZhWVtYWVtYWlpYMNsvK0sKKRTANBivDIIggIiLiZ+URDncHFgzbznN+8d77nvPwvec99zz/xsa6uv4oPMWjzADgK55kBnCvj3icGQBc4hWmsgK41w/sZAPwswLiC9ayAJjGa1wNrt/hAxaaBlBcW8ReZTVc4CUmmwZQ3FvHYQXEd2w3DyDuj/AMJxUQn7HaNIDiuRm8wfUAwi3eY75pAMXzS9ivrIZz7GKiaQDF7zZwVAHxDVvNAyj2h+c4rYD4hJWmARRjzOItbir7wzvMNQ2gGGsZB5XVcIYXGG8aQDHmJo4rII6x2TyAGHc83vpZBcQBlpsGUIw/F/vA7QDCTewbs00DKOZZiS/DUKfxJRk1DaCYbyvOCkMdYaN5ADHnRJwazysg9rHUNIBi7vnwI4b7w3X4HTNNAyhsWA3PcqiT8ERHTQMobNmOWMNQh1hvHkDYMxlRp4sKiD0sNg2gsGsh4pB3AwhXEbecbhpAYd9aRKZVItgPT+v96wAKO3ciVzHUw9J6/wuAsHUqslaXFRC/pfVGY139L9A3wf4Z7AehfhTuzlB3h3tApIfE/jqAtEHRtGHx1ImRtKmxtMnRtOnx1AUSaUtk0hZJpS2TS10ombZUNm2xdPpy+d4w0VtmetNU2ra51I2TuVtnuxrWL/YiKQ6CN9uRAAAAAElFTkSuQmCC", 0);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inDensity = 420;
        options.inTargetDensity = context2.getResources().getDisplayMetrics().densityDpi;
        Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArrDecode, 0, bArrDecode.length, options);
        if (bitmapDecodeByteArray != null) {
            this.aq.setImageBitmap(bitmapDecodeByteArray);
        }
        Context context3 = getContext();
        byte[] bArrDecode2 = Base64.decode("iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAYAAACqaXHeAAAABmJLR0QA/wD/AP+gvaeTAAAACXBIWXMAAAsTAAALEwEAmpwYAAAAB3RJTUUH4AMXCjITNKc0rQAAAJFJREFUeNrt2tENgCAMQEEwLuD+QzpC3cBURWLsvV+JNRfhi9YkSSpbP3sYETF0WO89s27m3KX6H1AeYL2wdrs5Y3/4ja/OTZ8B2f074h0z5zoDAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA/lr6rvDoK+xfmWsLNEmSVLUD47EiX/OuE8UAAAAASUVORK5CYII=", 0);
        BitmapFactory.Options options2 = new BitmapFactory.Options();
        options2.inDensity = 420;
        options2.inTargetDensity = context3.getResources().getDisplayMetrics().densityDpi;
        Bitmap bitmapDecodeByteArray2 = BitmapFactory.decodeByteArray(bArrDecode2, 0, bArrDecode2.length, options2);
        if (bitmapDecodeByteArray2 != null) {
            this.ap.setImageBitmap(bitmapDecodeByteArray2);
        }
        ck.a(this.ap, -2013265920, -1, -1, this.uiUtils.l(1), this.uiUtils.l(4));
        ck.a(this.aq, -2013265920, -1, -1, this.uiUtils.l(1), this.uiUtils.l(4));
        ck.a(this.ar, -2013265920, -1, -1, this.uiUtils.l(1), this.uiUtils.l(4));
        this.ag.setStarSize(this.uiUtils.l(12));
        this.am.setId(ab);
        this.am.setVisibility(8);
        this.mediaAdView.addView(this.ao, new ViewGroup.LayoutParams(-1, -1));
        addView(this.mediaAdView);
        addView(this.ak);
        addView(this.an);
        addView(this.ah);
        addView(this.am);
        addView(this.ai);
        addView(this.ap);
        addView(this.aq);
        addView(this.ag);
        addView(this.al);
        addView(this.ctaButton);
        addView(this.af);
        this.ai.addView(this.ar);
        this.ai.addView(this.aj, layoutParams2);
        this.ctaButton.setOnClickListener(this.au);
        this.ah.setOnClickListener(this.au);
        this.an.setOnClickListener(this.au);
    }

    @NonNull
    public final MediaAdView getMediaAdView() {
        return this.mediaAdView;
    }

    public final void a(@NonNull com.my.target.core.models.banners.a aVar, @NonNull VideoData videoData) {
        ak<VideoData> videoBanner = aVar.getVideoBanner();
        if (videoBanner == null) {
            return;
        }
        this.am.setMax(aVar.getDuration());
        this.az = videoBanner.isAllowReplay();
        this.ctaButton.setText(aVar.getCtaText());
        this.af.setText(aVar.getTitle());
        if ("store".equals(aVar.getNavigationType())) {
            this.al.setVisibility(8);
            if (aVar.getVotes() != 0 && aVar.getRating() > 0.0f) {
                this.ag.setVisibility(0);
                this.ag.setRating(aVar.getRating());
            } else {
                this.ag.setVisibility(8);
            }
        } else {
            this.ag.setVisibility(8);
            this.al.setVisibility(0);
            this.al.setText(aVar.getDomain());
        }
        this.ah.setText(videoBanner.getCloseActionText());
        this.aj.setText(videoBanner.getReplayActionText());
        Context context = getContext();
        byte[] bArrDecode = Base64.decode("iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAYAAACqaXHeAAAABmJLR0QA/wD/AP+gvaeTAAAACXBIWXMAAAsTAAALEwEAmpwYAAAAB3RJTUUH4AMXCjM59gfMOgAAA59JREFUeNrtmkloFEEUhl/N6KgxriiJOHEXF4gENYlgRFA8uyAoCNGggl68CCJ6EQx6cCFqUFzABQQRMYh4EfSi4IJbiFERQVxRgxuRMWri5yE1UBY9yWh6Znq6+z+96a6ZV/8/tbz3qkRChAgRwiMAFgJngWgQyVcDHXTiFKCCRH4Df6MFKAkK+W0W+VfAxCAQV8A+i/wTIB4E8lHgpEX+LjA8COQHAM8t8o3ATKC338kXA59IjXbgEVAPLAb6+k2AHRbhVrrGV+AwMNVPIpx3INncjRi/gTPAWL+IcMIieBGIAXFgGXAU+OggRALY7ItIEdhvkTsNRIz3MWCp3h1sXAGK/RAHnLKIHUzRdgnwwmr7Epjsh3jggkWsNkXbQuC4Q8g8Ld9F6ANctYht7KL9GiNpAnib94ujDo5uaUIdwMo00uafhghNQL98F2EYcA9YlGb75daoOeqHnSHyj+23WyLMC1oRJQpct7LJXkETYSrwyxBhlQQNwAFrFKigCTDK2hXmBnEUnDMEOBZEARYaArwLogAF1jRIq4YQ8YsASqmEiNw2HpUHSgCNB4Y9KYgCPDXs8UEU4LNhF6bzhW7DRmCEiFTqjx+VUtc8LECrYfd3RQA9lxq03SgiZR4WIGbYv9yaAt8Me5DHp8DAFP3ukQDvDTvu8WxrnGG/dUUApdQrEUkYU2a8hwWYnGJH6PEu0GzYczwaCSoRqTIeNbkpwFXDnu/Rf79URJKnyz9E5Kab6i4wYuxvQKEHR8BO8+DE7R+PAu8MB6s9Rj4GvDH6tzYTTvZaVZeIhwSoMfr2HRiUCSd21aXGI+T7WRcu6jLp7Ijh6AMw1AMCmKXxtozeLgOKgC+Gw4ZcFiCB2fpWSRLbsuF0nXUYsSlH5EfqE+IknmblSo0+1m6wRKjOMvnB+jzQHPpl2ezAEOCx0YEOYH2WfMct8rlZkIES4LXVkXqgTwZ9Vjn43JrLFXgC8Mzq0H2g0mU/BTrSa7d8bfHCPlwE3HC41XWyp9fbdIS3Tt8fxprzq7wUivYGdmnithCXgRXpxgw67K4A9ljhdxKPgVK3+q5cFqJcRA6JyAyn1zpFfaBz9RZdtYnpStNonc/PEpEhDt9vE5FaEdmtlPrh2YoEEAEWpbje9j9o1aNrhOQbgOlAncNC2R0SwCVgZaZTb5VFMUpEpEJEpojIGBEZLJ21+zbpLGe3iMgTEXkoIneUUj8lRIgQIUKECJFJ/AEepzU1TSID5QAAAABJRU5ErkJggg==", 0);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inDensity = 420;
        options.inTargetDensity = context.getResources().getDisplayMetrics().densityDpi;
        Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArrDecode, 0, bArrDecode.length, options);
        if (bitmapDecodeByteArray != null) {
            this.ar.setImageBitmap(bitmapDecodeByteArray);
        }
        this.mediaAdView.setPlaceHolderDimension(videoData.getWidth(), videoData.getHeight());
        ImageData image = aVar.getImage();
        if (image != null) {
            this.mediaAdView.getImageView().setImageBitmap(image.getBitmap());
        }
    }

    public final void setVideoDialogViewListener(@Nullable d dVar) {
        this.aA = dVar;
    }

    public final void b(float f, float f2) {
        if (this.am.getVisibility() != 0) {
            this.am.setVisibility(0);
        }
        this.am.setProgress(f / f2);
        this.am.setDigit((int) Math.ceil(f2 - f));
    }

    public final void J() {
        if (this.ax != 4) {
            this.ax = 4;
            this.mediaAdView.getImageView().setVisibility(0);
            this.mediaAdView.getProgressBarView().setVisibility(8);
            if (this.az) {
                this.ai.setVisibility(0);
                this.ak.setVisibility(0);
            }
            this.aq.setVisibility(8);
            this.ap.setVisibility(8);
            this.am.setVisibility(8);
        }
    }

    public final void K() {
        if (this.ax != 3) {
            this.ax = 3;
            this.mediaAdView.getProgressBarView().setVisibility(0);
            this.ai.setVisibility(8);
            this.aq.setVisibility(8);
            this.ap.setVisibility(8);
            this.ak.setVisibility(8);
        }
    }

    public final void L() {
        if (this.ax != 1) {
            this.ax = 1;
            this.mediaAdView.getImageView().setVisibility(0);
            this.mediaAdView.getProgressBarView().setVisibility(8);
            this.ai.setVisibility(8);
            this.aq.setVisibility(0);
            this.ap.setVisibility(8);
            this.ak.setVisibility(0);
        }
    }

    public final void M() {
        int i = this.ax;
        if (i == 0 || i == 2) {
            return;
        }
        this.ax = 0;
        this.mediaAdView.getImageView().setVisibility(8);
        this.mediaAdView.getProgressBarView().setVisibility(8);
        this.ai.setVisibility(8);
        this.aq.setVisibility(8);
        if (this.ax != 2) {
            this.ap.setVisibility(8);
        }
    }

    public final void c(boolean z) {
        if (z) {
            this.an.a(this.aw, false);
            this.an.setContentDescription("sound off");
        } else {
            this.an.a(this.av, false);
            this.an.setContentDescription("sound on");
        }
    }

    public final void N() {
        this.mediaAdView.getImageView().setVisibility(0);
    }

    @NonNull
    public final TextureView getTextureView() {
        return this.ao;
    }

    @Override // android.view.View
    protected final void onMeasure(int i, int i2) {
        this.an.measure(View.MeasureSpec.makeMeasureSpec(this.ay, 1073741824), View.MeasureSpec.makeMeasureSpec(this.ay, 1073741824));
        this.am.measure(View.MeasureSpec.makeMeasureSpec(this.ay, 1073741824), View.MeasureSpec.makeMeasureSpec(this.ay, 1073741824));
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        this.mediaAdView.measure(View.MeasureSpec.makeMeasureSpec(size, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(size2, Integer.MIN_VALUE));
        int i3 = this.padding;
        int i4 = size - (i3 << 1);
        int i5 = size2 - (i3 << 1);
        this.ah.measure(View.MeasureSpec.makeMeasureSpec(i4 / 2, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(i5, Integer.MIN_VALUE));
        this.ap.measure(View.MeasureSpec.makeMeasureSpec(i4, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(i5, Integer.MIN_VALUE));
        this.aq.measure(View.MeasureSpec.makeMeasureSpec(i4, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(i5, Integer.MIN_VALUE));
        this.ai.measure(View.MeasureSpec.makeMeasureSpec(i4, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(i5, Integer.MIN_VALUE));
        this.ag.measure(View.MeasureSpec.makeMeasureSpec(i4, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(i5, Integer.MIN_VALUE));
        this.ak.measure(View.MeasureSpec.makeMeasureSpec(this.mediaAdView.getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(this.mediaAdView.getMeasuredHeight(), 1073741824));
        this.ctaButton.measure(View.MeasureSpec.makeMeasureSpec(i4, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(i5, Integer.MIN_VALUE));
        this.af.measure(View.MeasureSpec.makeMeasureSpec(i4, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(i5, Integer.MIN_VALUE));
        this.al.measure(View.MeasureSpec.makeMeasureSpec(i4, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(i5, Integer.MIN_VALUE));
        if (size > size2) {
            int measuredWidth = this.ctaButton.getMeasuredWidth();
            int measuredWidth2 = this.af.getMeasuredWidth();
            if (this.am.getMeasuredWidth() + measuredWidth2 + Math.max(this.ag.getMeasuredWidth(), this.al.getMeasuredWidth()) + measuredWidth + (this.padding * 3) > i4) {
                int measuredWidth3 = (i4 - this.am.getMeasuredWidth()) - (this.padding * 3);
                int i6 = measuredWidth3 / 3;
                this.ctaButton.measure(View.MeasureSpec.makeMeasureSpec(i6, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(i5, Integer.MIN_VALUE));
                this.ag.measure(View.MeasureSpec.makeMeasureSpec(i6, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(i5, Integer.MIN_VALUE));
                this.al.measure(View.MeasureSpec.makeMeasureSpec(i6, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(i5, Integer.MIN_VALUE));
                this.af.measure(View.MeasureSpec.makeMeasureSpec(((measuredWidth3 - this.ctaButton.getMeasuredWidth()) - this.al.getMeasuredWidth()) - this.ag.getMeasuredWidth(), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(i5, Integer.MIN_VALUE));
            }
        }
        setMeasuredDimension(size, size2);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5 = i3 - i;
        int i6 = i4 - i2;
        int measuredWidth = this.mediaAdView.getMeasuredWidth();
        int measuredHeight = this.mediaAdView.getMeasuredHeight();
        int i7 = (i5 - measuredWidth) >> 1;
        int i8 = (i6 - measuredHeight) >> 1;
        this.mediaAdView.layout(i7, i8, measuredWidth + i7, measuredHeight + i8);
        this.ak.layout(this.mediaAdView.getLeft(), this.mediaAdView.getTop(), this.mediaAdView.getRight(), this.mediaAdView.getBottom());
        int measuredWidth2 = this.aq.getMeasuredWidth();
        int i9 = i3 >> 1;
        int i10 = measuredWidth2 >> 1;
        int i11 = i4 >> 1;
        int measuredHeight2 = this.aq.getMeasuredHeight() >> 1;
        this.aq.layout(i9 - i10, i11 - measuredHeight2, i10 + i9, measuredHeight2 + i11);
        int measuredWidth3 = this.ap.getMeasuredWidth();
        int i12 = measuredWidth3 >> 1;
        int measuredHeight3 = this.ap.getMeasuredHeight() >> 1;
        this.ap.layout(i9 - i12, i11 - measuredHeight3, i12 + i9, measuredHeight3 + i11);
        int measuredWidth4 = this.ai.getMeasuredWidth();
        int i13 = measuredWidth4 >> 1;
        int measuredHeight4 = this.ai.getMeasuredHeight() >> 1;
        this.ai.layout(i9 - i13, i11 - measuredHeight4, i9 + i13, i11 + measuredHeight4);
        Button button = this.ah;
        int i14 = this.padding;
        button.layout(i14, i14, button.getMeasuredWidth() + i14, this.padding + this.ah.getMeasuredHeight());
        if (i5 > i6) {
            int iMax = Math.max(this.ctaButton.getMeasuredHeight(), Math.max(this.af.getMeasuredHeight(), this.ag.getMeasuredHeight()));
            Button button2 = this.ctaButton;
            int measuredWidth5 = (i5 - this.padding) - button2.getMeasuredWidth();
            int measuredHeight5 = ((i6 - this.padding) - this.ctaButton.getMeasuredHeight()) - ((iMax - this.ctaButton.getMeasuredHeight()) >> 1);
            int i15 = this.padding;
            button2.layout(measuredWidth5, measuredHeight5, i5 - i15, (i6 - i15) - ((iMax - this.ctaButton.getMeasuredHeight()) >> 1));
            this.an.layout((this.ctaButton.getRight() - this.an.getMeasuredWidth()) + this.an.getPadding(), (((this.mediaAdView.getBottom() - (this.padding << 1)) - this.an.getMeasuredHeight()) - iMax) + this.an.getPadding(), this.ctaButton.getRight() + this.an.getPadding(), ((this.mediaAdView.getBottom() - (this.padding << 1)) - iMax) + this.an.getPadding());
            bz bzVar = this.ag;
            int left = (this.ctaButton.getLeft() - this.padding) - this.ag.getMeasuredWidth();
            int measuredHeight6 = ((i6 - this.padding) - this.ag.getMeasuredHeight()) - ((iMax - this.ag.getMeasuredHeight()) >> 1);
            int left2 = this.ctaButton.getLeft();
            int i16 = this.padding;
            bzVar.layout(left, measuredHeight6, left2 - i16, (i6 - i16) - ((iMax - this.ag.getMeasuredHeight()) >> 1));
            TextView textView = this.al;
            int left3 = (this.ctaButton.getLeft() - this.padding) - this.al.getMeasuredWidth();
            int measuredHeight7 = ((i6 - this.padding) - this.al.getMeasuredHeight()) - ((iMax - this.al.getMeasuredHeight()) >> 1);
            int left4 = this.ctaButton.getLeft();
            int i17 = this.padding;
            textView.layout(left3, measuredHeight7, left4 - i17, (i6 - i17) - ((iMax - this.al.getMeasuredHeight()) >> 1));
            int iMin = Math.min(this.ag.getLeft(), this.al.getLeft());
            TextView textView2 = this.af;
            int measuredWidth6 = (iMin - this.padding) - textView2.getMeasuredWidth();
            int measuredHeight8 = ((i6 - this.padding) - this.af.getMeasuredHeight()) - ((iMax - this.af.getMeasuredHeight()) >> 1);
            int i18 = this.padding;
            textView2.layout(measuredWidth6, measuredHeight8, iMin - i18, (i6 - i18) - ((iMax - this.af.getMeasuredHeight()) >> 1));
            cz czVar = this.am;
            int i19 = this.padding;
            czVar.layout(i19, ((i6 - i19) - czVar.getMeasuredHeight()) - ((iMax - this.am.getMeasuredHeight()) >> 1), this.padding + this.am.getMeasuredWidth(), (i6 - this.padding) - ((iMax - this.am.getMeasuredHeight()) >> 1));
            return;
        }
        this.an.layout(((this.mediaAdView.getRight() - this.padding) - this.an.getMeasuredWidth()) + this.an.getPadding(), ((this.mediaAdView.getBottom() - this.padding) - this.an.getMeasuredHeight()) + this.an.getPadding(), (this.mediaAdView.getRight() - this.padding) + this.an.getPadding(), (this.mediaAdView.getBottom() - this.padding) + this.an.getPadding());
        TextView textView3 = this.af;
        int i20 = i5 >> 1;
        textView3.layout(i20 - (textView3.getMeasuredWidth() >> 1), this.mediaAdView.getBottom() + this.padding, (this.af.getMeasuredWidth() >> 1) + i20, this.mediaAdView.getBottom() + this.padding + this.af.getMeasuredHeight());
        bz bzVar2 = this.ag;
        bzVar2.layout(i20 - (bzVar2.getMeasuredWidth() >> 1), this.af.getBottom() + this.padding, (this.ag.getMeasuredWidth() >> 1) + i20, this.af.getBottom() + this.padding + this.ag.getMeasuredHeight());
        TextView textView4 = this.al;
        textView4.layout(i20 - (textView4.getMeasuredWidth() >> 1), this.af.getBottom() + this.padding, (this.al.getMeasuredWidth() >> 1) + i20, this.af.getBottom() + this.padding + this.al.getMeasuredHeight());
        Button button3 = this.ctaButton;
        button3.layout(i20 - (button3.getMeasuredWidth() >> 1), this.ag.getBottom() + this.padding, i20 + (this.ctaButton.getMeasuredWidth() >> 1), this.ag.getBottom() + this.padding + this.ctaButton.getMeasuredHeight());
        this.am.layout(this.padding, (this.mediaAdView.getBottom() - this.padding) - this.am.getMeasuredHeight(), this.padding + this.am.getMeasuredWidth(), this.mediaAdView.getBottom() - this.padding);
    }

    /* JADX INFO: compiled from: VideoDialogView.java */
    private class a implements View.OnClickListener {
        private a() {
        }

        /* synthetic */ a(cy cyVar, byte b) {
            this();
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            if (cy.this.aA != null) {
                int id = view.getId();
                if (id == cy.a_) {
                    cy.this.aA.a(view);
                    return;
                }
                if (id == cy.b_) {
                    cy.this.aA.j();
                    return;
                }
                if (id == cy.aa) {
                    cy.this.aA.k();
                    return;
                }
                if (id == cy.c_) {
                    cy.this.aA.i();
                } else if (id == cy.W) {
                    cy.this.aA.l();
                } else if (id == cy.SOUND_BUTTON_ID) {
                    cy.this.aA.m();
                }
            }
        }
    }

    /* JADX INFO: compiled from: VideoDialogView.java */
    private class b implements Runnable {
        private b() {
        }

        /* synthetic */ b(cy cyVar, byte b) {
            this();
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (cy.this.ax == 2) {
                cy.c(cy.this);
            }
        }
    }

    /* JADX INFO: compiled from: VideoDialogView.java */
    private class c implements View.OnClickListener {
        private c() {
        }

        /* synthetic */ c(cy cyVar, byte b) {
            this();
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            cy cyVar = cy.this;
            cyVar.removeCallbacks(cyVar.as);
            if (cy.this.ax != 2) {
                if (cy.this.ax == 0) {
                    cy.e(cy.this);
                }
                cy cyVar2 = cy.this;
                cyVar2.postDelayed(cyVar2.as, 4000L);
                return;
            }
            cy.c(cy.this);
        }
    }

    static /* synthetic */ void c(cy cyVar) {
        if (cyVar.ax != 0) {
            cyVar.ax = 0;
            cyVar.mediaAdView.getImageView().setVisibility(8);
            cyVar.mediaAdView.getProgressBarView().setVisibility(8);
            cyVar.ai.setVisibility(8);
            cyVar.aq.setVisibility(8);
            cyVar.ap.setVisibility(8);
            cyVar.ak.setVisibility(8);
        }
    }

    static /* synthetic */ void e(cy cyVar) {
        if (cyVar.ax != 2) {
            cyVar.ax = 2;
            cyVar.mediaAdView.getImageView().setVisibility(8);
            cyVar.mediaAdView.getProgressBarView().setVisibility(8);
            cyVar.ai.setVisibility(8);
            cyVar.aq.setVisibility(8);
            cyVar.ap.setVisibility(0);
            cyVar.ak.setVisibility(8);
        }
    }
}
