package com.my.target;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.HashMap;

/* JADX INFO: renamed from: com.my.target.do, reason: invalid class name */
/* JADX INFO: compiled from: StandardNativeWideView.java */
/* JADX INFO: loaded from: classes2.dex */
@SuppressLint({"ViewConstructor"})
public final class Cdo extends RelativeLayout implements View.OnTouchListener, dn {

    @NonNull
    private final ck aF;

    @NonNull
    private final TextView aW;

    @NonNull
    private final TextView aY;

    @NonNull
    private final RelativeLayout bD;

    @NonNull
    private final by bE;

    @NonNull
    private final LinearLayout bF;

    @NonNull
    private final TextView bG;

    @NonNull
    private final bz bH;

    @NonNull
    private final by bI;

    @NonNull
    private final FrameLayout bJ;

    @NonNull
    private final bu bK;

    @NonNull
    private final Button ba;
    private int backgroundColor;

    @NonNull
    private final by bc;

    @NonNull
    private final FrameLayout bg;

    @NonNull
    private final HashMap<View, Boolean> bk;
    private int bu;

    @Nullable
    private View.OnClickListener bx;
    private final int height;
    private final int width;
    private static final int bB = ck.br();
    private static final int aQ = ck.br();
    private static final int bC = ck.br();
    private static final int aR = ck.br();

    @Override // com.my.target.dn
    @NonNull
    public final View N() {
        return this;
    }

    @Override // com.my.target.dn
    @Nullable
    public final TextView getDescriptionTextView() {
        return null;
    }

    @Override // com.my.target.dn
    @Nullable
    public final TextView getDisclaimerTextView() {
        return null;
    }

    @Override // com.my.target.dn
    public final void start() {
    }

    @Override // com.my.target.dn
    public final void stop() {
    }

    public Cdo(@NonNull Context context) {
        this(context, (byte) 0);
    }

    private Cdo(@NonNull Context context, byte b) {
        super(context, null);
        this.bk = new HashMap<>();
        this.bD = new RelativeLayout(context);
        this.bc = new by(context);
        this.aW = new TextView(context);
        this.aY = new TextView(context);
        this.ba = new Button(context);
        this.bE = new by(context);
        this.bg = new FrameLayout(context);
        this.aF = ck.x(context);
        this.bF = new LinearLayout(context);
        this.bG = new TextView(context);
        this.bH = new bz(context);
        this.bI = new by(context);
        this.bJ = new FrameLayout(context);
        this.bK = new bu(context);
        ck.a(this, "ad_view");
        ck.a(this.bc, "icon_image");
        ck.a(this.aW, "title_text");
        ck.a(this.aY, "domain_text");
        ck.a(this.ba, "cta_button");
        ck.a(this.bE, "main_image");
        ck.a(this.bg, "icon_layout");
        ck.a(this.bG, "votes_text");
        ck.a(this.bH, "rating_view");
        ck.a(this.bI, "banner_image");
        ck.a(this.bK, "age_border");
        ck ckVarX = ck.x(context);
        this.height = ckVarX.l(250);
        this.width = ckVarX.l(300);
        Context context2 = getContext();
        if (context2 != null) {
            RelativeLayout relativeLayout = new RelativeLayout(context2);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, this.aF.l(42));
            layoutParams.leftMargin = this.aF.l(2);
            layoutParams.rightMargin = this.aF.l(2);
            this.bD.setId(bB);
            this.bD.setLayoutParams(layoutParams);
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(this.aF.l(38), this.aF.l(38));
            layoutParams2.rightMargin = this.aF.l(2);
            this.bg.setId(aQ);
            this.bg.setLayoutParams(layoutParams2);
            FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(-1, -1);
            layoutParams3.gravity = 17;
            this.bc.setLayoutParams(layoutParams3);
            RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams4.addRule(1, aQ);
            relativeLayout.setLayoutParams(layoutParams4);
            this.aW.setTextSize(18.0f);
            this.aW.setMaxLines(1);
            this.aW.setEllipsize(TextUtils.TruncateAt.END);
            this.aW.setTransformationMethod(null);
            this.aW.setIncludeFontPadding(false);
            this.aW.setId(aR);
            this.aY.setTextSize(14.0f);
            this.aY.setMaxLines(1);
            this.aY.setEllipsize(TextUtils.TruncateAt.END);
            this.aY.setTransformationMethod(null);
            this.aY.setIncludeFontPadding(false);
            RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams5.addRule(3, aR);
            this.aY.setLayoutParams(layoutParams5);
            this.ba.setId(bC);
            this.ba.setTextSize(20.0f);
            this.ba.setPadding(this.aF.l(4), 0, this.aF.l(4), 0);
            this.ba.setTransformationMethod(null);
            this.ba.setLines(1);
            this.ba.setEllipsize(TextUtils.TruncateAt.END);
            RelativeLayout.LayoutParams layoutParams6 = new RelativeLayout.LayoutParams(-1, this.aF.l(36));
            layoutParams6.leftMargin = this.aF.l(2);
            layoutParams6.rightMargin = this.aF.l(2);
            layoutParams6.bottomMargin = this.aF.l(2);
            layoutParams6.addRule(12, -1);
            getCtaButton().setLayoutParams(layoutParams6);
            if (Build.VERSION.SDK_INT >= 21) {
                getCtaButton().setStateListAnimator(null);
            }
            FrameLayout frameLayout = new FrameLayout(context2);
            RelativeLayout.LayoutParams layoutParams7 = new RelativeLayout.LayoutParams(this.aF.l(296), this.aF.l(168));
            layoutParams7.addRule(3, bB);
            layoutParams7.addRule(2, bC);
            layoutParams7.addRule(14, -1);
            layoutParams7.bottomMargin = this.aF.l(2);
            frameLayout.setLayoutParams(layoutParams7);
            FrameLayout.LayoutParams layoutParams8 = new FrameLayout.LayoutParams(-1, -1);
            layoutParams8.gravity = 17;
            this.bE.setLayoutParams(layoutParams8);
            this.bG.setTransformationMethod(null);
            this.bG.setTextSize(14.0f);
            this.bG.setIncludeFontPadding(false);
            RelativeLayout.LayoutParams layoutParams9 = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams9.addRule(3, aR);
            this.bF.setLayoutParams(layoutParams9);
            LinearLayout.LayoutParams layoutParams10 = new LinearLayout.LayoutParams(this.aF.l(73), this.aF.l(10));
            layoutParams10.topMargin = this.aF.l(2);
            layoutParams10.bottomMargin = this.aF.l(2);
            layoutParams10.rightMargin = this.aF.l(2);
            layoutParams10.gravity = 48;
            this.bH.setLayoutParams(layoutParams10);
            RelativeLayout.LayoutParams layoutParams11 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams11.addRule(11, -1);
            layoutParams11.rightMargin = this.aF.l(2);
            this.bK.setLayoutParams(layoutParams11);
            this.bK.setPadding(this.aF.l(2), this.aF.l(4), 0, 0);
            this.bK.setLines(1);
            this.bK.setTextSize(2, 12.0f);
            RelativeLayout.LayoutParams layoutParams12 = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams12.addRule(13, -1);
            addView(this.bI, layoutParams12);
            addView(this.bJ, -1, -1);
            this.bF.addView(this.bH);
            this.bF.addView(this.bG);
            relativeLayout.addView(this.aW);
            relativeLayout.addView(this.aY);
            relativeLayout.addView(this.bF);
            this.bg.addView(this.bc);
            this.bD.addView(this.bg);
            this.bD.addView(relativeLayout);
            addView(this.bD);
            addView(getCtaButton());
            frameLayout.addView(this.bE);
            addView(frameLayout);
            addView(this.bK);
        }
    }

    @Override // com.my.target.dn
    @NonNull
    public final bz getStarsRatingView() {
        return this.bH;
    }

    @Override // com.my.target.dn
    public final void b(@NonNull ai aiVar) {
        if (ai.a.cV.equals(aiVar.getType())) {
            View[] viewArr = {this.bg, this.bJ, this};
            for (int i = 0; i < 3; i++) {
                ck.a(viewArr[i], this.backgroundColor, this.bu);
            }
            this.bI.setVisibility(8);
            this.bJ.setVisibility(8);
            if ("store".equals(aiVar.getNavigationType())) {
                this.aY.setVisibility(8);
                this.bF.setVisibility(0);
                if (aiVar.getRating() > 0.0f) {
                    this.bH.setVisibility(0);
                    if (aiVar.getVotes() > 0) {
                        this.bG.setVisibility(0);
                    } else {
                        this.bG.setVisibility(8);
                    }
                } else {
                    this.bH.setVisibility(8);
                    this.bG.setVisibility(8);
                }
                this.aY.setVisibility(8);
                return;
            }
            this.aY.setVisibility(0);
            this.bF.setVisibility(8);
            return;
        }
        View[] viewArr2 = {this.bg, this.bJ, this};
        for (int i2 = 0; i2 < 3; i2++) {
            ck.a(viewArr2[i2], 0, ck.k(this.bu));
        }
        this.bK.setVisibility(8);
        this.bD.setVisibility(8);
        getCtaButton().setVisibility(8);
        this.bE.setVisibility(8);
        this.bJ.setVisibility(0);
        this.bI.setVisibility(0);
    }

    @Override // com.my.target.dn
    @NonNull
    public final TextView getDomainTextView() {
        return this.aY;
    }

    @Override // com.my.target.dn
    @NonNull
    public final by getBannerImage() {
        return this.bI;
    }

    @Override // com.my.target.dn
    @NonNull
    public final by getMainImage() {
        return this.bE;
    }

    @Override // com.my.target.dn
    @NonNull
    public final by getIconImage() {
        return this.bc;
    }

    @Override // com.my.target.dn
    @NonNull
    public final bu getAgeRestrictionsView() {
        return this.bK;
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
    public final TextView getRatingTextView() {
        return this.bG;
    }

    @Override // com.my.target.dn
    public final void b(int i, int i2) {
        this.backgroundColor = i;
        this.bu = i2;
    }

    @Override // com.my.target.dn
    @SuppressLint({"ClickableViewAccessibility"})
    public final void a(@NonNull ag agVar, boolean z, @NonNull View.OnClickListener onClickListener) {
        g.a("Apply click area " + agVar.O() + " to view");
        this.bx = onClickListener;
        boolean z2 = z || agVar.cT;
        setOnTouchListener(this);
        this.aW.setOnTouchListener(this);
        this.bc.setOnTouchListener(this);
        this.bE.setOnTouchListener(this);
        this.bH.setOnTouchListener(this);
        this.bG.setOnTouchListener(this);
        this.aY.setOnTouchListener(this);
        this.bK.setOnTouchListener(this);
        this.ba.setOnTouchListener(this);
        this.bk.put(this.aW, Boolean.valueOf(agVar.cH || z2));
        this.bk.put(this.bc, Boolean.valueOf(agVar.cJ || z2));
        this.bk.put(this.bE, Boolean.valueOf(agVar.cK || z2));
        this.bk.put(this.bH, Boolean.valueOf(agVar.cL || z2));
        this.bk.put(this.bG, Boolean.valueOf(agVar.cM || z2));
        this.bk.put(this.bK, Boolean.valueOf(agVar.cO || z2));
        this.bk.put(this.aY, Boolean.valueOf(agVar.cQ || z2));
        this.bk.put(this, Boolean.valueOf(agVar.cS || z2));
        this.bk.put(this.ba, Boolean.valueOf(agVar.cN || z2));
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action != 1) {
                if (action == 3) {
                    if (view == this.ba) {
                        view.setPressed(false);
                    } else {
                        setBackgroundColor(this.backgroundColor);
                        this.bc.setBackgroundColor(this.backgroundColor);
                    }
                }
            } else {
                if (!this.bk.containsKey(view)) {
                    return false;
                }
                if (!this.bk.get(view).booleanValue()) {
                    return true;
                }
                if (view == this.ba) {
                    view.setPressed(false);
                } else {
                    setBackgroundColor(this.backgroundColor);
                    this.bc.setBackgroundColor(this.backgroundColor);
                }
                performClick();
                View.OnClickListener onClickListener = this.bx;
                if (onClickListener != null) {
                    onClickListener.onClick(view);
                }
            }
        } else {
            if (!this.bk.containsKey(view)) {
                return false;
            }
            if (!this.bk.get(view).booleanValue()) {
                return true;
            }
            if (view == this.ba) {
                view.setPressed(true);
            } else {
                setBackgroundColor(this.bu);
                this.bc.setBackgroundColor(this.backgroundColor);
            }
        }
        return true;
    }

    @Override // android.widget.RelativeLayout, android.view.View
    protected final void onMeasure(int i, int i2) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(this.width, 1073741824), View.MeasureSpec.makeMeasureSpec(this.height, 1073741824));
    }
}
