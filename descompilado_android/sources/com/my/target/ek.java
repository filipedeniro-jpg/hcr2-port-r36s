package com.my.target;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import com.my.target.nativeads.views.MediaAdView;

/* JADX INFO: compiled from: VerticalView.java */
/* JADX INFO: loaded from: classes2.dex */
@SuppressLint({"ViewConstructor"})
public final class ek extends RelativeLayout {

    @NonNull
    private final bu aB;

    @NonNull
    private final ck aC;

    @NonNull
    private final Button bQ;
    private final boolean bd;

    @NonNull
    private final ec cl;

    @NonNull
    private final ee cm;
    private static final int ci = ck.br();
    private static final int cj = ck.br();
    private static final int bH = ck.br();
    private static final int ck = ck.br();

    public ek(@NonNull Context context, @NonNull ck ckVar, boolean z) {
        super(context);
        this.aC = ckVar;
        this.bd = z;
        this.cm = new ee(context, ckVar, z);
        ck.a(this.cm, "footer_layout");
        this.cl = new ec(context, ckVar, z);
        ck.a(this.cl, "body_layout");
        this.bQ = new Button(context);
        ck.a(this.bQ, "cta_button");
        this.aB = new bu(context);
        ck.a(this.aB, "age_bordering");
    }

    public final void a(int i, int i2, boolean z) {
        int iL;
        int iMax = Math.max(i2, i) / 8;
        this.cl.d(z);
        this.cm.P();
        View view = new View(getContext());
        view.setBackgroundColor(-5592406);
        view.setLayoutParams(new RelativeLayout.LayoutParams(-1, 1));
        this.cm.setId(cj);
        this.cm.a(iMax, z);
        this.bQ.setId(bH);
        this.bQ.setPadding(this.aC.l(15), 0, this.aC.l(15), 0);
        this.bQ.setMinimumWidth(this.aC.l(100));
        this.bQ.setTransformationMethod(null);
        this.bQ.setSingleLine();
        this.bQ.setEllipsize(TextUtils.TruncateAt.END);
        this.aB.setId(ci);
        this.aB.c(1, -7829368);
        this.aB.setPadding(this.aC.l(2), 0, 0, 0);
        this.aB.setTextColor(MediaAdView.COLOR_PLACEHOLDER_GRAY);
        this.aB.setMaxEms(5);
        this.aB.a(1, MediaAdView.COLOR_PLACEHOLDER_GRAY, this.aC.l(3));
        this.aB.setBackgroundColor(1711276032);
        this.cl.setId(ck);
        if (z) {
            this.cl.setPadding(this.aC.l(4), this.aC.l(4), this.aC.l(4), this.aC.l(4));
        } else {
            this.cl.setPadding(this.aC.l(16), this.aC.l(16), this.aC.l(16), this.aC.l(16));
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(2, cj);
        this.cl.setLayoutParams(layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.setMargins(this.aC.l(16), z ? this.aC.l(8) : this.aC.l(16), this.aC.l(16), this.aC.l(4));
        if (Build.VERSION.SDK_INT >= 17) {
            layoutParams2.addRule(21, -1);
        } else {
            layoutParams2.addRule(11, -1);
        }
        this.aB.setLayoutParams(layoutParams2);
        if (this.bd) {
            iL = this.aC.l(64);
        } else {
            iL = this.aC.l(52);
        }
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, iL);
        layoutParams3.addRule(14, -1);
        layoutParams3.addRule(8, ck);
        if (z) {
            double d = -this.aC.l(52);
            Double.isNaN(d);
            layoutParams3.bottomMargin = (int) (d / 1.5d);
        } else {
            layoutParams3.bottomMargin = (-this.aC.l(52)) / 2;
        }
        this.bQ.setLayoutParams(layoutParams3);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, iMax);
        layoutParams4.addRule(12, -1);
        this.cm.setLayoutParams(layoutParams4);
        addView(this.cl);
        addView(view);
        addView(this.aB);
        addView(this.cm);
        addView(this.bQ);
        setClickable(true);
        if (this.bd) {
            this.bQ.setTextSize(2, 32.0f);
        } else {
            this.bQ.setTextSize(2, 22.0f);
        }
    }

    public final void setBanner(@NonNull com.my.target.core.models.banners.i iVar) {
        this.cl.setBanner(iVar);
        this.cm.setBanner(iVar);
        this.bQ.setText(iVar.getCtaText());
        this.cm.setBackgroundColor(iVar.getFooterColor());
        if (TextUtils.isEmpty(iVar.getAgeRestrictions())) {
            this.aB.setVisibility(8);
        } else {
            this.aB.setText(iVar.getAgeRestrictions());
        }
        int ctaButtonColor = iVar.getCtaButtonColor();
        int ctaButtonTouchColor = iVar.getCtaButtonTouchColor();
        int ctaButtonTextColor = iVar.getCtaButtonTextColor();
        ck.a(this.bQ, ctaButtonColor, ctaButtonTouchColor, this.aC.l(2));
        this.bQ.setTextColor(ctaButtonTextColor);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public final void a(@NonNull final ag agVar, @NonNull final View.OnClickListener onClickListener) {
        this.cl.a(agVar, onClickListener);
        if (agVar.cT) {
            this.bQ.setOnClickListener(onClickListener);
            return;
        }
        if (agVar.cN) {
            this.bQ.setOnClickListener(onClickListener);
            this.bQ.setEnabled(true);
        } else {
            this.bQ.setOnClickListener(null);
            this.bQ.setEnabled(false);
        }
        this.aB.setOnTouchListener(new View.OnTouchListener() { // from class: com.my.target.ek.1
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                if (!agVar.cO) {
                    return true;
                }
                int action = motionEvent.getAction();
                if (action == 0) {
                    ek.this.cl.setBackgroundColor(-3806472);
                } else if (action == 1) {
                    ek.this.cl.setBackgroundColor(-1);
                    onClickListener.onClick(view);
                } else if (action == 3) {
                    ek.this.setBackgroundColor(-1);
                }
                return true;
            }
        });
    }
}
