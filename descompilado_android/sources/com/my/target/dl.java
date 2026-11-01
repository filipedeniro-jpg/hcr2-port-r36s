package com.my.target;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import java.util.HashMap;

/* JADX INFO: compiled from: StandardNativeSlimView.java */
/* JADX INFO: loaded from: classes2.dex */
@SuppressLint({"ViewConstructor"})
public final class dl extends RelativeLayout implements View.OnTouchListener, dn {
    private static final int aP = ck.br();
    private static final int aQ = ck.br();
    private static final int aR = ck.br();
    private static final int aS = ck.br();
    private static final int aT = ck.br();

    @VisibleForTesting
    static long aU = 4000;

    @NonNull
    private final ck aF;

    @NonNull
    private final bu aV;

    @NonNull
    private final TextView aW;

    @NonNull
    private final TextView aX;

    @NonNull
    private final TextView aY;

    @NonNull
    private final LinearLayout aZ;

    @NonNull
    private final Button ba;
    private int backgroundColor;

    @NonNull
    private final bz bb;

    @NonNull
    private final by bc;

    @NonNull
    private final RelativeLayout bd;

    @NonNull
    private final RelativeLayout be;

    @NonNull
    private final ViewFlipper bf;

    @NonNull
    private final FrameLayout bg;

    @NonNull
    private final TextView bh;

    @NonNull
    private final TextView bi;

    @NonNull
    private final a bj;

    @NonNull
    private final HashMap<View, Boolean> bk;
    private final int bl;
    private final float bm;
    private final int bn;
    private final int bo;
    private final int bp;
    private final int bq;
    private final int br;
    private final int bs;
    private final int bt;
    private int bu;
    private int bv;
    private boolean bw;

    @Nullable
    private View.OnClickListener bx;
    private boolean by;
    private boolean bz;

    @Override // com.my.target.dn
    @NonNull
    public final View N() {
        return this;
    }

    @Override // com.my.target.dn
    @Nullable
    public final by getMainImage() {
        return null;
    }

    public dl(@NonNull String str, @NonNull Context context) {
        this(str, context, (byte) 0);
    }

    private dl(@NonNull String str, @NonNull Context context, byte b) {
        super(context, null);
        this.bk = new HashMap<>();
        this.aF = ck.x(context);
        this.bc = new by(context);
        this.bg = new FrameLayout(context);
        this.bf = new ViewFlipper(context);
        this.aZ = new LinearLayout(context);
        this.ba = new Button(context);
        this.aW = new TextView(context);
        this.aV = new bu(context);
        this.bb = new bz(context);
        this.aX = new TextView(context);
        this.aY = new TextView(context);
        this.bd = new RelativeLayout(context);
        this.be = new RelativeLayout(context);
        this.bh = new TextView(context);
        this.bi = new TextView(context);
        ck.a(this, "ad_view");
        ck.a(this.bc, "icon_image");
        ck.a(this.bg, "icon_layout");
        ck.a(this.ba, "cta_button");
        ck.a(this.aW, "title_text");
        ck.a(this.aV, "age_border");
        ck.a(this.bb, "rating_view");
        ck.a(this.aX, "votes_text");
        ck.a(this.aY, "domain_text");
        ck.a(this.bh, "description_text");
        ck.a(this.bi, "disclaimer_text");
        if (b.a.c.equals(str)) {
            this.bl = 20;
            this.bo = 24;
            this.bn = 32;
            this.bp = 16;
            this.bq = 24;
            this.br = 24;
            this.bs = this.aF.l(180);
            this.bt = this.aF.l(270);
        } else {
            this.bl = 15;
            this.bo = 16;
            this.bn = 20;
            this.bp = 10;
            this.bq = 12;
            this.br = 16;
            this.bs = this.aF.l(50);
            this.bt = this.aF.l(100);
        }
        this.bm = 1.2f;
        this.bc.setId(aQ);
        this.ba.setId(aS);
        this.ba.setPadding(this.aF.l(8), this.aF.l(8), this.aF.l(8), this.aF.l(8));
        this.ba.setMinimumWidth(this.bs);
        this.ba.setTextSize(2, this.bn);
        this.ba.setMaxWidth(this.bt);
        this.ba.setLines(1);
        this.ba.setEllipsize(TextUtils.TruncateAt.END);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -1);
        layoutParams.addRule(11, -1);
        this.ba.setLayoutParams(layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.addRule(0, aS);
        layoutParams2.addRule(1, aQ);
        this.bf.setLayoutParams(layoutParams2);
        ViewFlipper viewFlipper = this.bf;
        TranslateAnimation translateAnimation = new TranslateAnimation(2, 0.0f, 2, 0.0f, 2, 1.0f, 2, 0.0f);
        translateAnimation.setDuration(400L);
        translateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        viewFlipper.setInAnimation(translateAnimation);
        ViewFlipper viewFlipper2 = this.bf;
        TranslateAnimation translateAnimation2 = new TranslateAnimation(2, 0.0f, 2, 0.0f, 2, 0.0f, 2, -1.0f);
        translateAnimation2.setDuration(400L);
        translateAnimation2.setInterpolator(new AccelerateDecelerateInterpolator());
        viewFlipper2.setOutAnimation(translateAnimation2);
        this.aZ.setOrientation(1);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, 0);
        layoutParams3.weight = 1.0f;
        this.bd.setLayoutParams(layoutParams3);
        this.bd.setGravity(16);
        this.aW.setId(aR);
        this.aW.setMaxLines(1);
        this.aW.setHorizontallyScrolling(true);
        this.aW.setSingleLine(true);
        this.aW.setMaxEms(25);
        this.aW.setTextSize(2, this.bo);
        this.aW.setEllipsize(TextUtils.TruncateAt.END);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams4.rightMargin = this.aF.l(4);
        layoutParams4.leftMargin = this.aF.l(4);
        layoutParams4.topMargin = this.aF.l(2);
        layoutParams4.addRule(15);
        this.aW.setLayoutParams(layoutParams4);
        this.aV.setId(aP);
        this.aV.c(1, -7829368);
        this.aV.setGravity(17);
        this.aV.setTextSize(2, this.bp);
        this.aV.setPadding(this.aF.l(2), this.aF.l(4), 0, 0);
        this.aV.setLines(1);
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams5.addRule(15);
        layoutParams5.topMargin = this.aF.l(2);
        layoutParams5.addRule(1, aR);
        this.aV.setLayoutParams(layoutParams5);
        LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(-1, 0);
        layoutParams6.weight = 1.0f;
        this.be.setLayoutParams(layoutParams6);
        this.bb.setId(aT);
        this.bb.setStarSize(this.aF.l(this.bq));
        RelativeLayout.LayoutParams layoutParams7 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams7.leftMargin = this.aF.l(4);
        layoutParams7.rightMargin = this.aF.l(4);
        layoutParams7.topMargin = this.aF.l(4);
        layoutParams7.bottomMargin = this.aF.l(2);
        layoutParams7.addRule(15);
        this.bb.setLayoutParams(layoutParams7);
        RelativeLayout.LayoutParams layoutParams8 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams8.addRule(1, aT);
        layoutParams8.addRule(15);
        this.aX.setTextSize(2, this.br);
        this.aX.setLayoutParams(layoutParams8);
        RelativeLayout.LayoutParams layoutParams9 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams9.leftMargin = this.aF.l(4);
        this.aY.setTextSize(2, this.br);
        this.aY.setLayoutParams(layoutParams9);
        new RelativeLayout.LayoutParams(-2, -2).addRule(15);
        this.ba.setTransformationMethod(null);
        addView(this.bc);
        addView(this.bg);
        addView(this.ba);
        this.be.addView(this.bb);
        this.be.addView(this.aX);
        this.be.addView(this.aY);
        this.bd.addView(this.aW);
        this.bd.addView(this.aV);
        this.aZ.addView(this.bd);
        this.aZ.addView(this.be);
        this.bf.addView(this.aZ);
        addView(this.bf);
        this.bj = new a(this, (byte) 0);
    }

    @Override // com.my.target.dn
    @NonNull
    public final bz getStarsRatingView() {
        return this.bb;
    }

    @Override // com.my.target.dn
    public final void b(@NonNull ai aiVar) {
        String type = aiVar.getType();
        if (ai.a.cV.equals(type)) {
            this.bw = true;
            ck.a(this, this.backgroundColor, this.bu);
            ck.a(this.bf, this.backgroundColor, this.bu);
            ck.a(this.bg, 0, ck.k(this.bu));
            this.ba.setVisibility(0);
            this.bf.setVisibility(0);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -1);
            this.bc.setPadding(this.aF.l(2), this.aF.l(2), this.aF.l(2), this.aF.l(2));
            this.bc.setLayoutParams(layoutParams);
            this.bc.setMaxWidth(this.bt);
            this.bg.setLayoutParams(layoutParams);
            layoutParams.addRule(9, -1);
            layoutParams.addRule(15, -1);
            this.bc.setLayoutParams(layoutParams);
        } else if (ai.a.cW.equals(type)) {
            this.bw = false;
            ck.a(this, 0, ck.k(this.bu));
            this.ba.setVisibility(8);
            this.bf.setVisibility(8);
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams2.addRule(14);
            this.bc.setPadding(0, 0, 0, 0);
            this.bc.setLayoutParams(layoutParams2);
            this.bc.setMaxWidth(0);
            this.bg.setLayoutParams(layoutParams2);
        }
        if ("store".equalsIgnoreCase(aiVar.getNavigationType())) {
            if (aiVar.getRating() > 0.0f) {
                this.bb.setVisibility(0);
                if (aiVar.getVotes() > 0) {
                    this.aX.setVisibility(0);
                } else {
                    this.aX.setVisibility(8);
                }
            } else {
                this.bb.setVisibility(8);
                this.aX.setVisibility(8);
            }
            this.aY.setVisibility(8);
            return;
        }
        this.aY.setVisibility(0);
        this.bb.setVisibility(8);
        this.aX.setVisibility(8);
    }

    @Override // com.my.target.dn
    @NonNull
    public final TextView getDomainTextView() {
        return this.aY;
    }

    @Override // com.my.target.dn
    @Nullable
    public final by getBannerImage() {
        return this.bc;
    }

    @Override // com.my.target.dn
    @NonNull
    public final by getIconImage() {
        return this.bc;
    }

    @Override // com.my.target.dn
    @NonNull
    public final bu getAgeRestrictionsView() {
        return this.aV;
    }

    @Override // com.my.target.dn
    @NonNull
    public final Button getCtaButton() {
        return this.ba;
    }

    @Override // com.my.target.dn
    @NonNull
    public final TextView getTitleTextView() {
        return this.aW;
    }

    @Override // com.my.target.dn
    @NonNull
    public final TextView getDescriptionTextView() {
        return this.bh;
    }

    @Override // com.my.target.dn
    @NonNull
    public final TextView getDisclaimerTextView() {
        return this.bi;
    }

    @Override // com.my.target.dn
    @NonNull
    public final TextView getRatingTextView() {
        return this.aX;
    }

    @Override // com.my.target.dn
    public final void b(int i, int i2) {
        this.backgroundColor = i;
        this.bu = i2;
        this.bc.setBackgroundColor(this.backgroundColor);
        setBackgroundColor(this.backgroundColor);
    }

    @Override // com.my.target.dn
    @SuppressLint({"ClickableViewAccessibility"})
    public final void a(@NonNull ag agVar, boolean z, @NonNull View.OnClickListener onClickListener) {
        g.a("Apply click area " + agVar.O() + " to view");
        boolean z2 = z || agVar.cT;
        this.bx = onClickListener;
        setOnTouchListener(this);
        this.aW.setOnTouchListener(this);
        this.bc.setOnTouchListener(this);
        this.bb.setOnTouchListener(this);
        this.aX.setOnTouchListener(this);
        this.aY.setOnTouchListener(this);
        this.aV.setOnTouchListener(this);
        this.bh.setOnTouchListener(this);
        this.bk.put(this.aW, Boolean.valueOf(agVar.cH || z2));
        this.bk.put(this.bc, Boolean.valueOf(agVar.cJ || z2));
        this.bk.put(this.bb, Boolean.valueOf(agVar.cL || z2));
        this.bk.put(this.aX, Boolean.valueOf(agVar.cM || z2));
        this.bk.put(this.aV, Boolean.valueOf(agVar.cO || z2));
        this.bk.put(this.aY, Boolean.valueOf(agVar.cQ || z2));
        this.bk.put(this, Boolean.valueOf(agVar.cS || z2));
        this.by = agVar.cS || z2;
        this.bz = agVar.cI || z2;
        if (agVar.cN || z2) {
            this.ba.setOnClickListener(onClickListener);
            this.ba.setEnabled(true);
        } else {
            this.ba.setOnClickListener(null);
            this.ba.setEnabled(false);
        }
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action == 1) {
                setBackgroundColor(this.backgroundColor);
                getIconImage().setBackgroundColor(this.backgroundColor);
                if (!this.bk.containsKey(view)) {
                    return false;
                }
                if (!this.bk.get(view).booleanValue()) {
                    return true;
                }
                performClick();
                View.OnClickListener onClickListener = this.bx;
                if (onClickListener != null) {
                    onClickListener.onClick(view);
                }
            } else if (action == 3) {
                setBackgroundColor(this.backgroundColor);
                getIconImage().setBackgroundColor(this.backgroundColor);
            }
        } else {
            if (!this.bk.containsKey(view)) {
                return false;
            }
            if (!this.bk.get(view).booleanValue()) {
                return true;
            }
            setBackgroundColor(this.bu);
            getIconImage().setBackgroundColor(this.backgroundColor);
        }
        return true;
    }

    @Override // android.widget.RelativeLayout, android.view.View
    protected final void onMeasure(int i, int i2) {
        int i3;
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int measuredWidth = this.bc.getMeasuredWidth();
        int measuredWidth2 = this.ba.getMeasuredWidth();
        if (this.bw && size != 0 && (i3 = this.bv) != size && measuredWidth > 0 && measuredWidth2 > 0) {
            if (i3 != 0) {
                if (this.bf.getChildCount() > 1) {
                    this.bf.setDisplayedChild(0);
                }
                while (this.bf.getChildCount() > 1) {
                    ViewFlipper viewFlipper = this.bf;
                    viewFlipper.removeViewAt(viewFlipper.getChildCount() - 1);
                }
            }
            this.bv = size;
            int paddingRight = (((size - this.bf.getPaddingRight()) - this.bf.getPaddingLeft()) - measuredWidth) - measuredWidth2;
            CharSequence text = this.aV.getText();
            if (!TextUtils.isEmpty(text)) {
                this.aW.setMaxWidth((paddingRight - (text.length() * this.aF.l(10))) - this.aF.l(10));
            }
            LinearLayout linearLayout = null;
            CharSequence text2 = this.bh.getText();
            if (!TextUtils.isEmpty(text2)) {
                Paint paint = new Paint();
                paint.setTextSize(this.aF.m(this.bl));
                paint.setTypeface(this.bh.getTypeface());
                for (String str : dm.a(text2.toString(), paddingRight, paint)) {
                    if (linearLayout == null || linearLayout.getChildCount() > 1) {
                        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, size2);
                        LinearLayout linearLayout2 = new LinearLayout(getContext());
                        linearLayout2.setGravity(16);
                        linearLayout2.setOrientation(1);
                        linearLayout2.setLayoutParams(layoutParams);
                        linearLayout2.setBackgroundColor(0);
                        this.bf.addView(linearLayout2);
                        linearLayout = linearLayout2;
                    }
                    TextView textView = new TextView(getContext());
                    this.bk.put(textView, Boolean.valueOf(this.bz));
                    textView.setOnTouchListener(this);
                    this.bk.put(linearLayout, Boolean.valueOf(this.by));
                    linearLayout.setOnTouchListener(this);
                    textView.setGravity(16);
                    textView.setTextSize(2, this.bl);
                    textView.setLineSpacing(0.0f, this.bm);
                    textView.setLines(1);
                    textView.setTextColor(this.bh.getCurrentTextColor());
                    textView.setTypeface(this.bh.getTypeface());
                    textView.setText(str);
                    linearLayout.addView(textView);
                }
            }
            CharSequence text3 = this.bi.getText();
            if (!TextUtils.isEmpty(text3)) {
                Paint paint2 = new Paint();
                paint2.setTextSize(this.aF.m(this.bl));
                paint2.setTypeface(this.bi.getTypeface());
                for (String str2 : dm.a(text3.toString(), paddingRight, paint2)) {
                    if (linearLayout == null || linearLayout.getChildCount() > 1) {
                        ViewGroup.LayoutParams layoutParams2 = new ViewGroup.LayoutParams(-1, size2);
                        LinearLayout linearLayout3 = new LinearLayout(getContext());
                        linearLayout3.setGravity(16);
                        linearLayout3.setOrientation(1);
                        linearLayout3.setLayoutParams(layoutParams2);
                        this.bf.addView(linearLayout3);
                        linearLayout = linearLayout3;
                    }
                    TextView textView2 = new TextView(getContext());
                    textView2.setGravity(16);
                    textView2.setLineSpacing(0.0f, this.bm);
                    textView2.setTextSize(2, this.bl);
                    textView2.setLines(1);
                    textView2.setTextColor(this.bi.getCurrentTextColor());
                    textView2.setTypeface(this.bi.getTypeface());
                    textView2.setText(str2);
                    linearLayout.addView(textView2);
                }
            }
        }
        super.onMeasure(i, i2);
    }

    /* JADX INFO: compiled from: StandardNativeSlimView.java */
    private class a implements Runnable {
        private a() {
        }

        /* synthetic */ a(dl dlVar, byte b) {
            this();
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (dl.this.bf.getChildCount() > 1) {
                dl.this.bf.showNext();
                dl dlVar = dl.this;
                dlVar.postDelayed(dlVar.bj, dl.aU);
            }
        }
    }

    @Override // com.my.target.dn
    public final void start() {
        removeCallbacks(this.bj);
        postDelayed(this.bj, aU);
    }

    @Override // com.my.target.dn
    public final void stop() {
        this.bf.stopFlipping();
        removeCallbacks(this.bj);
    }
}
