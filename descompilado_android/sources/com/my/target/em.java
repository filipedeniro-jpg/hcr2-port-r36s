package com.my.target;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import java.util.HashMap;

/* JADX INFO: compiled from: CardItemView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class em extends ViewGroup implements View.OnTouchListener {

    @NonNull
    private final ck aC;

    @NonNull
    private final Button bQ;

    @NonNull
    private final bz bb;
    private final boolean bd;

    @NonNull
    private final HashMap<View, Boolean> be;

    @Nullable
    private View.OnClickListener bf;

    @NonNull
    private final by cO;

    @NonNull
    private final TextView cP;

    @NonNull
    private final TextView cQ;
    private int cR;
    private int cS;

    @NonNull
    private final TextView cx;
    private int padding;
    private static final int bH = ck.br();
    private static final int bg = ck.br();
    private static final int aF = ck.br();
    private static final int bI = ck.br();
    private static final int bK = ck.br();
    private static final int bJ = ck.br();

    public em(boolean z, @NonNull Context context) {
        super(context);
        this.be = new HashMap<>();
        this.bd = z;
        this.aC = ck.x(context);
        this.cO = new by(context);
        this.cP = new TextView(context);
        this.cQ = new TextView(context);
        this.bQ = new Button(context);
        this.bb = new bz(context);
        this.cx = new TextView(context);
        ck.a(this, 0, 0, -3355444, this.aC.l(1), 0);
        this.padding = this.aC.l(2);
        this.cS = this.aC.l(12);
        this.cO.setId(bg);
        this.bQ.setId(bH);
        this.bQ.setPadding(this.aC.l(15), this.aC.l(10), this.aC.l(15), this.aC.l(10));
        this.bQ.setMinimumWidth(this.aC.l(100));
        this.bQ.setTransformationMethod(null);
        this.bQ.setSingleLine();
        if (this.bd) {
            this.bQ.setTextSize(20.0f);
        } else {
            this.bQ.setTextSize(18.0f);
        }
        this.bQ.setEllipsize(TextUtils.TruncateAt.END);
        if (Build.VERSION.SDK_INT >= 21) {
            this.bQ.setElevation(this.aC.l(2));
        }
        this.cR = this.aC.l(12);
        ck.a(this.bQ, -16733198, -16746839, this.aC.l(2));
        this.bQ.setTextColor(-1);
        this.cP.setId(aF);
        if (this.bd) {
            this.cP.setTextSize(20.0f);
        } else {
            this.cP.setTextSize(18.0f);
        }
        this.cP.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.cP.setTypeface(null, 1);
        this.cP.setLines(1);
        this.cP.setEllipsize(TextUtils.TruncateAt.END);
        this.cQ.setId(bI);
        this.cQ.setTextColor(-7829368);
        this.cQ.setLines(2);
        if (this.bd) {
            this.cQ.setTextSize(20.0f);
        } else {
            this.cQ.setTextSize(18.0f);
        }
        this.cQ.setEllipsize(TextUtils.TruncateAt.END);
        this.bb.setId(bK);
        if (this.bd) {
            this.bb.setStarSize(this.aC.l(24));
        } else {
            this.bb.setStarSize(this.aC.l(18));
        }
        this.bb.setStarsPadding(this.aC.l(4));
        this.cx.setId(bJ);
        ck.a(this, "card_view");
        ck.a(this.cP, "card_title_text");
        ck.a(this.cQ, "card_description_text");
        ck.a(this.cx, "card_domain_text");
        ck.a(this.bQ, "card_cta_button");
        ck.a(this.bb, "card_stars_view");
        ck.a(this.cO, "card_image");
        addView(this.cO);
        addView(this.cQ);
        addView(this.cP);
        addView(this.bQ);
        addView(this.bb);
        addView(this.cx);
    }

    @NonNull
    public final Button getCtaButtonView() {
        return this.bQ;
    }

    @NonNull
    public final TextView getDescriptionTextView() {
        return this.cQ;
    }

    @NonNull
    public final TextView getDomainTextView() {
        return this.cx;
    }

    @NonNull
    public final by getSmartImageView() {
        return this.cO;
    }

    @NonNull
    public final bz getRatingView() {
        return this.bb;
    }

    @NonNull
    public final TextView getTitleTextView() {
        return this.cP;
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public final void a(@Nullable View.OnClickListener onClickListener, @Nullable ag agVar) {
        this.bf = onClickListener;
        if (onClickListener == null || agVar == null) {
            super.setOnClickListener(null);
            this.bQ.setOnClickListener(null);
            return;
        }
        setOnTouchListener(this);
        this.cO.setOnTouchListener(this);
        this.cP.setOnTouchListener(this);
        this.cQ.setOnTouchListener(this);
        this.bb.setOnTouchListener(this);
        this.cx.setOnTouchListener(this);
        this.bQ.setOnTouchListener(this);
        this.be.put(this.cO, Boolean.valueOf(agVar.cK || agVar.cT));
        this.be.put(this, Boolean.valueOf(agVar.cS || agVar.cT));
        this.be.put(this.cP, Boolean.valueOf(agVar.cH || agVar.cT));
        this.be.put(this.cQ, Boolean.valueOf(agVar.cI || agVar.cT));
        this.be.put(this.bb, Boolean.valueOf(agVar.cL || agVar.cT));
        this.be.put(this.cx, Boolean.valueOf(agVar.cQ || agVar.cT));
        this.be.put(this.bQ, Boolean.valueOf(agVar.cN || agVar.cT));
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        if (!this.be.containsKey(view)) {
            return false;
        }
        boolean zBooleanValue = this.be.get(view).booleanValue();
        view.setClickable(zBooleanValue);
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action == 1) {
                View.OnClickListener onClickListener = this.bf;
                if (onClickListener != null) {
                    onClickListener.onClick(view);
                }
                if (zBooleanValue) {
                    Button button = this.bQ;
                    if (view == button) {
                        button.setPressed(false);
                    } else {
                        ck.a(this, 0, 0, -3355444, this.aC.l(1), 0);
                    }
                }
            } else if (action == 3 && zBooleanValue) {
                Button button2 = this.bQ;
                if (view == button2) {
                    button2.setPressed(false);
                } else {
                    ck.a(this, 0, 0, -3355444, this.aC.l(1), 0);
                }
            }
        } else if (zBooleanValue) {
            Button button3 = this.bQ;
            if (view == button3) {
                button3.setPressed(true);
            } else {
                setBackgroundColor(-3806472);
            }
        }
        return true;
    }

    @Override // android.view.View
    protected final void onMeasure(int i, int i2) {
        int measuredHeight;
        int measuredHeight2;
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        boolean z = !this.bd && getResources().getConfiguration().orientation == 2;
        int i3 = size == 0 ? 0 : Integer.MIN_VALUE;
        int i4 = this.padding;
        int i5 = size2 - (i4 * 2);
        int i6 = size - (i4 * 2);
        if (z) {
            this.cP.measure(View.MeasureSpec.makeMeasureSpec(size, i3), View.MeasureSpec.makeMeasureSpec(i5, Integer.MIN_VALUE));
            this.cQ.measure(0, 0);
            this.bb.measure(0, 0);
            this.cx.measure(0, 0);
            this.bQ.measure(0, 0);
        } else {
            this.cP.measure(View.MeasureSpec.makeMeasureSpec(i6 - (this.cS * 2), i3), View.MeasureSpec.makeMeasureSpec(i5, Integer.MIN_VALUE));
            this.cQ.measure(View.MeasureSpec.makeMeasureSpec(i6 - (this.cS * 2), i3), View.MeasureSpec.makeMeasureSpec(i5, Integer.MIN_VALUE));
            this.bb.measure(View.MeasureSpec.makeMeasureSpec(i6, i3), View.MeasureSpec.makeMeasureSpec(i5, Integer.MIN_VALUE));
            this.cx.measure(View.MeasureSpec.makeMeasureSpec(i6, i3), View.MeasureSpec.makeMeasureSpec(i5, Integer.MIN_VALUE));
            this.bQ.measure(View.MeasureSpec.makeMeasureSpec(i6 - (this.cS * 2), i3), View.MeasureSpec.makeMeasureSpec(i5 - (this.cS * 2), Integer.MIN_VALUE));
        }
        if (z) {
            measuredHeight = size2 - this.cP.getMeasuredHeight();
            measuredHeight2 = this.padding;
        } else {
            measuredHeight = (((size2 - this.bQ.getMeasuredHeight()) - (this.cR * 2)) - Math.max(this.bb.getMeasuredHeight(), this.cx.getMeasuredHeight())) - this.cQ.getMeasuredHeight();
            measuredHeight2 = this.cP.getMeasuredHeight();
        }
        int i7 = measuredHeight - measuredHeight2;
        if (i7 <= size) {
            size = i7;
        }
        this.cO.measure(View.MeasureSpec.makeMeasureSpec(size, 1073741824), View.MeasureSpec.makeMeasureSpec(size, 1073741824));
        setMeasuredDimension(size, size2);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5 = (i3 - i) - (this.padding * 2);
        boolean z2 = !this.bd && getResources().getConfiguration().orientation == 2;
        by byVar = this.cO;
        byVar.layout(0, 0, byVar.getMeasuredWidth(), this.cO.getMeasuredHeight());
        if (z2) {
            this.cP.setTypeface(null, 1);
            this.cP.layout(0, this.cO.getBottom(), i5, this.cO.getBottom() + this.cP.getMeasuredHeight());
            ck.a(this, 0, 0);
            this.cQ.layout(0, 0, 0, 0);
            this.bQ.layout(0, 0, 0, 0);
            this.bb.layout(0, 0, 0, 0);
            this.cx.layout(0, 0, 0, 0);
            return;
        }
        this.cP.setTypeface(null, 0);
        ck.a(this, 0, 0, -3355444, this.aC.l(1), 0);
        this.cP.layout(this.padding + this.cS, this.cO.getBottom(), this.cP.getMeasuredWidth() + this.padding + this.cS, this.cO.getBottom() + this.cP.getMeasuredHeight());
        this.cQ.layout(this.padding + this.cS, this.cP.getBottom(), this.cQ.getMeasuredWidth() + this.padding + this.cS, this.cP.getBottom() + this.cQ.getMeasuredHeight());
        int measuredWidth = (i5 - this.bQ.getMeasuredWidth()) / 2;
        Button button = this.bQ;
        button.layout(measuredWidth, (i4 - button.getMeasuredHeight()) - this.cS, this.bQ.getMeasuredWidth() + measuredWidth, i4 - this.cS);
        int measuredWidth2 = (i5 - this.bb.getMeasuredWidth()) / 2;
        this.bb.layout(measuredWidth2, (this.bQ.getTop() - this.cS) - this.bb.getMeasuredHeight(), this.bb.getMeasuredWidth() + measuredWidth2, this.bQ.getTop() - this.cS);
        int measuredWidth3 = (i5 - this.cx.getMeasuredWidth()) / 2;
        this.cx.layout(measuredWidth3, (this.bQ.getTop() - this.cx.getMeasuredHeight()) - this.cS, this.cx.getMeasuredWidth() + measuredWidth3, this.bQ.getTop() - this.cS);
    }
}
