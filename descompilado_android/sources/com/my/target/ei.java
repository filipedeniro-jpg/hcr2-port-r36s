package com.my.target;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.my.target.common.models.ImageData;
import com.my.target.nativeads.views.MediaAdView;
import java.util.ArrayList;

/* JADX INFO: compiled from: PanelView.java */
/* JADX INFO: loaded from: classes2.dex */
@SuppressLint({"ViewConstructor"})
public final class ei extends ViewGroup {

    @NonNull
    private final ck aC;

    @NonNull
    private final LinearLayout aY;

    @NonNull
    private final TextView bM;

    @NonNull
    private final ea bN;

    @NonNull
    private final TextView bO;

    @NonNull
    private final TextView bP;

    @NonNull
    private final Button bQ;
    private final int bR;
    private final int bS;
    private final int bT;

    @NonNull
    private final bz bb;

    @NonNull
    private final TextView bc;

    @NonNull
    private final by bm;
    private static final int bj = ck.br();
    private static final int bH = ck.br();
    private static final int bI = ck.br();
    private static final int bJ = ck.br();
    private static final int bK = ck.br();
    private static final int bL = ck.br();
    private static final int aF = ck.br();

    public ei(@NonNull Context context, @NonNull ck ckVar) {
        super(context);
        this.aC = ckVar;
        this.bQ = new Button(context);
        this.bQ.setId(bH);
        ck.a(this.bQ, "cta_button");
        this.bm = new by(context);
        this.bm.setId(bj);
        ck.a(this.bm, "icon_image");
        this.bN = new ea(context);
        this.bN.setId(aF);
        this.bM = new TextView(context);
        this.bM.setId(bI);
        ck.a(this.bM, "description_text");
        this.bO = new TextView(context);
        ck.a(this.bO, "disclaimer_text");
        this.aY = new LinearLayout(context);
        this.bb = new bz(context);
        this.bb.setId(bK);
        ck.a(this.bb, "stars_view");
        this.bc = new TextView(context);
        this.bc.setId(bL);
        ck.a(this.bc, "votes_text");
        this.bP = new TextView(context);
        ck.a(this.bP, "domain_text");
        this.bP.setId(bJ);
        this.bR = ckVar.l(16);
        this.bT = ckVar.l(8);
        this.bS = ckVar.l(64);
    }

    public final void P() {
        setBackgroundColor(1711276032);
        this.bM.setTextColor(-2236963);
        this.bM.setEllipsize(TextUtils.TruncateAt.END);
        this.bP.setTextColor(-6710887);
        this.bP.setVisibility(8);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(0);
        gradientDrawable.setStroke(1, -3355444);
        this.bO.setPadding(this.aC.l(4), this.aC.l(4), this.aC.l(4), this.aC.l(4));
        this.bO.setBackgroundDrawable(gradientDrawable);
        this.bO.setTextSize(2, 12.0f);
        this.bO.setTextColor(-3355444);
        this.bO.setVisibility(8);
        this.aY.setOrientation(0);
        this.aY.setGravity(16);
        this.aY.setVisibility(8);
        this.bc.setTextColor(-6710887);
        this.bc.setGravity(16);
        this.bc.setTextSize(2, 14.0f);
        this.bQ.setPadding(this.aC.l(15), 0, this.aC.l(15), 0);
        this.bQ.setMinimumWidth(this.aC.l(100));
        this.bQ.setTransformationMethod(null);
        this.bQ.setTextSize(2, 22.0f);
        this.bQ.setMaxEms(10);
        this.bQ.setSingleLine();
        this.bQ.setEllipsize(TextUtils.TruncateAt.END);
        bu rightBorderedView = this.bN.getRightBorderedView();
        rightBorderedView.c(1, -7829368);
        rightBorderedView.setPadding(this.aC.l(2), 0, 0, 0);
        rightBorderedView.setTextColor(MediaAdView.COLOR_PLACEHOLDER_GRAY);
        rightBorderedView.a(1, MediaAdView.COLOR_PLACEHOLDER_GRAY, this.aC.l(3));
        rightBorderedView.setBackgroundColor(1711276032);
        this.bb.setStarSize(this.aC.l(12));
        this.aY.addView(this.bb);
        this.aY.addView(this.bc);
        this.aY.setVisibility(8);
        this.bP.setVisibility(8);
        addView(this.bN);
        addView(this.aY);
        addView(this.bP);
        addView(this.bM);
        addView(this.bO);
        addView(this.bm);
        addView(this.bQ);
    }

    public final void setBanner(@NonNull com.my.target.core.models.banners.i iVar) {
        this.bN.getLeftText().setText(iVar.getTitle());
        this.bM.setText(iVar.getDescription());
        String disclaimer = iVar.getDisclaimer();
        if (!TextUtils.isEmpty(disclaimer)) {
            this.bO.setVisibility(0);
            this.bO.setText(disclaimer);
        } else {
            this.bO.setVisibility(8);
        }
        ImageData icon = iVar.getIcon();
        if (icon != null) {
            this.bm.setVisibility(0);
            this.bm.setImageData(icon);
        } else {
            this.bm.setVisibility(8);
        }
        this.bQ.setText(iVar.getCtaText());
        if (!"".equals(iVar.getAgeRestrictions())) {
            this.bN.getRightBorderedView().setText(iVar.getAgeRestrictions());
        } else {
            this.bN.getRightBorderedView().setVisibility(8);
        }
        int ctaButtonColor = iVar.getCtaButtonColor();
        int ctaButtonTouchColor = iVar.getCtaButtonTouchColor();
        int ctaButtonTextColor = iVar.getCtaButtonTextColor();
        ck.a(this.bQ, ctaButtonColor, ctaButtonTouchColor, this.aC.l(2));
        this.bQ.setTextColor(ctaButtonTextColor);
        if ("store".equals(iVar.getNavigationType())) {
            if (iVar.getVotes() != 0 && iVar.getRating() > 0.0f) {
                this.aY.setEnabled(true);
                this.bb.setRating(iVar.getRating());
                this.bc.setText(String.valueOf(iVar.getVotes()));
            } else {
                this.aY.setEnabled(false);
                this.aY.setVisibility(8);
            }
            this.bP.setEnabled(false);
        } else {
            String domain = iVar.getDomain();
            if (!TextUtils.isEmpty(domain)) {
                this.bP.setEnabled(true);
                this.bP.setText(domain);
            } else {
                this.bP.setEnabled(false);
                this.bP.setVisibility(8);
            }
            this.aY.setEnabled(false);
        }
        if (iVar.getVideoBanner() == null || !iVar.getVideoBanner().isAutoPlay()) {
            this.aY.setVisibility(8);
            this.bP.setVisibility(8);
        }
    }

    public final void a(View... viewArr) {
        if (getVisibility() == 0) {
            a(0, viewArr);
        }
    }

    public final void a(@NonNull ag agVar, @NonNull View.OnClickListener onClickListener) {
        if (agVar.cT) {
            setOnClickListener(onClickListener);
            this.bQ.setOnClickListener(onClickListener);
            return;
        }
        if (agVar.cN) {
            this.bQ.setOnClickListener(onClickListener);
        } else {
            this.bQ.setEnabled(false);
        }
        if (agVar.cS) {
            setOnClickListener(onClickListener);
        } else {
            setOnClickListener(null);
        }
        if (agVar.cH) {
            this.bN.getLeftText().setOnClickListener(onClickListener);
        } else {
            this.bN.getLeftText().setOnClickListener(null);
        }
        if (agVar.cO) {
            this.bN.getRightBorderedView().setOnClickListener(onClickListener);
        } else {
            this.bN.getRightBorderedView().setOnClickListener(null);
        }
        if (agVar.cJ) {
            this.bm.setOnClickListener(onClickListener);
        } else {
            this.bm.setOnClickListener(null);
        }
        if (agVar.cI) {
            this.bM.setOnClickListener(onClickListener);
        } else {
            this.bM.setOnClickListener(null);
        }
        if (agVar.cL) {
            this.bb.setOnClickListener(onClickListener);
        } else {
            this.bb.setOnClickListener(null);
        }
        if (agVar.cM) {
            this.bc.setOnClickListener(onClickListener);
        } else {
            this.bc.setOnClickListener(null);
        }
        if (agVar.cQ) {
            this.bP.setOnClickListener(onClickListener);
        } else {
            this.bP.setOnClickListener(null);
        }
    }

    final void b(View... viewArr) {
        if (getVisibility() == 0) {
            a(300, viewArr);
        }
    }

    @Override // android.view.View
    protected final void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2) / 4;
        int i3 = size - (this.bR * 2);
        int i4 = size2 - (this.bT * 2);
        int iMin = Math.min(i4, this.bS);
        this.bm.measure(View.MeasureSpec.makeMeasureSpec(iMin, 1073741824), View.MeasureSpec.makeMeasureSpec(iMin, 1073741824));
        this.bQ.measure(View.MeasureSpec.makeMeasureSpec(i3, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(iMin - (this.bT * 2), 1073741824));
        int measuredWidth = ((i3 - this.bm.getMeasuredWidth()) - this.bQ.getMeasuredWidth()) - (this.bR * 2);
        this.bN.measure(View.MeasureSpec.makeMeasureSpec(measuredWidth, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(i4, Integer.MIN_VALUE));
        this.aY.measure(View.MeasureSpec.makeMeasureSpec(measuredWidth, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(i4, Integer.MIN_VALUE));
        this.bP.measure(View.MeasureSpec.makeMeasureSpec(measuredWidth, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(i4, Integer.MIN_VALUE));
        this.bM.measure(View.MeasureSpec.makeMeasureSpec(measuredWidth, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(i4 - this.bN.getMeasuredHeight(), Integer.MIN_VALUE));
        this.bO.measure(View.MeasureSpec.makeMeasureSpec(measuredWidth, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(i4, Integer.MIN_VALUE));
        int measuredHeight = this.bN.getMeasuredHeight() + Math.max(this.bM.getMeasuredHeight(), this.aY.getMeasuredHeight()) + (this.bT * 2);
        if (this.bO.getVisibility() == 0) {
            measuredHeight += this.bO.getMeasuredHeight();
        }
        setMeasuredDimension(size, Math.max(this.bQ.getMeasuredHeight(), Math.max(this.bm.getMeasuredHeight(), measuredHeight)) + (this.bT * 2));
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        int measuredHeight2 = this.bm.getMeasuredHeight();
        int measuredWidth2 = this.bm.getMeasuredWidth();
        int i5 = (measuredHeight - measuredHeight2) / 2;
        by byVar = this.bm;
        int i6 = this.bR;
        byVar.layout(i6, i5, i6 + measuredWidth2, measuredHeight2 + i5);
        int measuredWidth3 = this.bQ.getMeasuredWidth();
        int measuredHeight3 = this.bQ.getMeasuredHeight();
        int i7 = (measuredHeight - measuredHeight3) / 2;
        int i8 = this.bR;
        this.bQ.layout((measuredWidth - measuredWidth3) - i8, i7, measuredWidth - i8, measuredHeight3 + i7);
        int i9 = this.bR;
        int i10 = measuredWidth2 + i9 + i9;
        ea eaVar = this.bN;
        eaVar.layout(i10, this.bT, eaVar.getMeasuredWidth() + i10, this.bT + this.bN.getMeasuredHeight());
        this.aY.layout(i10, this.bN.getBottom(), this.aY.getMeasuredWidth() + i10, this.bN.getBottom() + this.aY.getMeasuredHeight());
        this.bP.layout(i10, this.bN.getBottom(), this.bP.getMeasuredWidth() + i10, this.bN.getBottom() + this.bP.getMeasuredHeight());
        this.bM.layout(i10, this.bN.getBottom(), this.bM.getMeasuredWidth() + i10, this.bN.getBottom() + this.bM.getMeasuredHeight());
        this.bO.layout(i10, this.bM.getBottom(), this.bO.getMeasuredWidth() + i10, this.bM.getBottom() + this.bO.getMeasuredHeight());
    }

    private void a(int i, @NonNull View... viewArr) {
        int height = this.bm.getHeight();
        int height2 = getHeight();
        int width = this.bQ.getWidth();
        int height3 = this.bQ.getHeight();
        int width2 = this.bm.getWidth();
        this.bm.setPivotX(0.0f);
        this.bm.setPivotY(height / 2.0f);
        this.bQ.setPivotX(width);
        this.bQ.setPivotY(height3 / 2.0f);
        float f = height2 * 0.3f;
        ArrayList arrayList = new ArrayList();
        arrayList.add(ObjectAnimator.ofFloat(this.bQ, (Property<Button, Float>) View.SCALE_X, 0.7f));
        arrayList.add(ObjectAnimator.ofFloat(this.bQ, (Property<Button, Float>) View.SCALE_Y, 0.7f));
        arrayList.add(ObjectAnimator.ofFloat(this.bm, (Property<by, Float>) View.SCALE_X, 0.7f));
        arrayList.add(ObjectAnimator.ofFloat(this.bm, (Property<by, Float>) View.SCALE_Y, 0.7f));
        arrayList.add(ObjectAnimator.ofFloat(this.bM, (Property<TextView, Float>) View.ALPHA, 0.0f));
        arrayList.add(ObjectAnimator.ofFloat(this.bO, (Property<TextView, Float>) View.ALPHA, 0.0f));
        if (this.aY.isEnabled()) {
            arrayList.add(ObjectAnimator.ofFloat(this.aY, (Property<LinearLayout, Float>) View.ALPHA, 1.0f));
        }
        arrayList.add(ObjectAnimator.ofFloat(this, (Property<ei, Float>) View.ALPHA, 0.6f));
        float f2 = -(width2 * 0.3f);
        arrayList.add(ObjectAnimator.ofFloat(this.bN, (Property<ea, Float>) View.TRANSLATION_X, f2));
        arrayList.add(ObjectAnimator.ofFloat(this.aY, (Property<LinearLayout, Float>) View.TRANSLATION_X, f2));
        arrayList.add(ObjectAnimator.ofFloat(this.bP, (Property<TextView, Float>) View.TRANSLATION_X, f2));
        arrayList.add(ObjectAnimator.ofFloat(this.bM, (Property<TextView, Float>) View.TRANSLATION_X, f2));
        arrayList.add(ObjectAnimator.ofFloat(this.bO, (Property<TextView, Float>) View.TRANSLATION_X, f2));
        arrayList.add(ObjectAnimator.ofFloat(this, (Property<ei, Float>) View.TRANSLATION_Y, f));
        float f3 = (-f) / 2.0f;
        arrayList.add(ObjectAnimator.ofFloat(this.bQ, (Property<Button, Float>) View.TRANSLATION_Y, f3));
        arrayList.add(ObjectAnimator.ofFloat(this.bm, (Property<by, Float>) View.TRANSLATION_Y, f3));
        for (View view : viewArr) {
            arrayList.add(ObjectAnimator.ofFloat(view, (Property<View, Float>) View.TRANSLATION_Y, f));
        }
        if (this.aY.isEnabled()) {
            this.aY.setVisibility(0);
        }
        if (this.bP.isEnabled()) {
            this.bP.setVisibility(0);
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.addListener(new Animator.AnimatorListener() { // from class: com.my.target.ei.1
            @Override // android.animation.Animator.AnimatorListener
            public final void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public final void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public final void onAnimationStart(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                ei.this.bO.setVisibility(8);
                ei.this.bM.setVisibility(8);
            }
        });
        animatorSet.playTogether(arrayList);
        animatorSet.setDuration(i);
        animatorSet.start();
    }

    final void c(View... viewArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(ObjectAnimator.ofFloat(this.bQ, (Property<Button, Float>) View.SCALE_Y, 1.0f));
        arrayList.add(ObjectAnimator.ofFloat(this.bQ, (Property<Button, Float>) View.SCALE_X, 1.0f));
        arrayList.add(ObjectAnimator.ofFloat(this.bm, (Property<by, Float>) View.SCALE_Y, 1.0f));
        arrayList.add(ObjectAnimator.ofFloat(this.bm, (Property<by, Float>) View.SCALE_X, 1.0f));
        arrayList.add(ObjectAnimator.ofFloat(this.bM, (Property<TextView, Float>) View.ALPHA, 1.0f));
        arrayList.add(ObjectAnimator.ofFloat(this.bO, (Property<TextView, Float>) View.ALPHA, 1.0f));
        if (this.aY.isEnabled()) {
            arrayList.add(ObjectAnimator.ofFloat(this.aY, (Property<LinearLayout, Float>) View.ALPHA, 0.0f));
        }
        arrayList.add(ObjectAnimator.ofFloat(this, (Property<ei, Float>) View.ALPHA, 1.0f));
        arrayList.add(ObjectAnimator.ofFloat(this.bN, (Property<ea, Float>) View.TRANSLATION_X, 0.0f));
        arrayList.add(ObjectAnimator.ofFloat(this.aY, (Property<LinearLayout, Float>) View.TRANSLATION_X, 0.0f));
        arrayList.add(ObjectAnimator.ofFloat(this.bP, (Property<TextView, Float>) View.TRANSLATION_X, 0.0f));
        arrayList.add(ObjectAnimator.ofFloat(this.bM, (Property<TextView, Float>) View.TRANSLATION_X, 0.0f));
        arrayList.add(ObjectAnimator.ofFloat(this.bO, (Property<TextView, Float>) View.TRANSLATION_X, 0.0f));
        arrayList.add(ObjectAnimator.ofFloat(this, (Property<ei, Float>) View.TRANSLATION_Y, 0.0f));
        arrayList.add(ObjectAnimator.ofFloat(this.bQ, (Property<Button, Float>) View.TRANSLATION_Y, 0.0f));
        arrayList.add(ObjectAnimator.ofFloat(this.bm, (Property<by, Float>) View.TRANSLATION_Y, 0.0f));
        for (int i = 0; i <= 0; i++) {
            arrayList.add(ObjectAnimator.ofFloat(viewArr[0], (Property<View, Float>) View.TRANSLATION_Y, 0.0f));
        }
        if (!TextUtils.isEmpty(this.bO.getText().toString())) {
            this.bO.setVisibility(0);
        }
        this.bM.setVisibility(0);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(arrayList);
        animatorSet.addListener(new Animator.AnimatorListener() { // from class: com.my.target.ei.2
            @Override // android.animation.Animator.AnimatorListener
            public final void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public final void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public final void onAnimationStart(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                if (ei.this.aY.isEnabled()) {
                    ei.this.aY.setVisibility(8);
                }
                if (ei.this.bP.isEnabled()) {
                    ei.this.bP.setVisibility(8);
                }
            }
        });
        animatorSet.setDuration(300L);
        animatorSet.start();
    }
}
