package com.my.target;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import com.my.target.common.models.ImageData;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: compiled from: CarouselView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ep extends ViewGroup implements View.OnTouchListener, eh {

    @NonNull
    private final bw D;

    @NonNull
    private final ck aC;

    @NonNull
    private final Button bQ;
    private final boolean bd;

    @NonNull
    private final HashMap<View, Boolean> be;

    @NonNull
    private final by bm;

    @Nullable
    private eh.a bz;
    private final int cR;

    @NonNull
    private final TextView cr;

    @NonNull
    private final TextView dm;

    @NonNull
    private final TextView dn;

    /* JADX INFO: renamed from: do, reason: not valid java name */
    @NonNull
    private final eo f705do;
    private final int dp;
    private final int dq;
    private final double dr;
    private static final int dj = ck.br();
    private static final int bj = ck.br();
    private static final int aF = ck.br();
    private static final int dk = ck.br();
    private static final int bI = ck.br();
    private static final int dl = ck.br();
    private static final int bH = ck.br();

    /* JADX INFO: compiled from: CarouselView.java */
    public interface a {
        void a(@NonNull com.my.target.core.models.banners.f fVar);

        void a(@NonNull List<com.my.target.core.models.banners.f> list);
    }

    @Override // com.my.target.eh
    @NonNull
    public final View getView() {
        return this;
    }

    public ep(@NonNull Context context) {
        super(context);
        ck.a(this, -1, -3806472);
        this.bd = (context.getResources().getConfiguration().screenLayout & 15) >= 3;
        this.dr = this.bd ? 0.5d : 0.7d;
        this.D = new bw(context);
        this.aC = ck.x(context);
        this.cr = new TextView(context);
        this.dm = new TextView(context);
        this.dn = new TextView(context);
        this.bm = new by(context);
        this.bQ = new Button(context);
        this.f705do = new eo(context);
        this.D.setId(dj);
        this.D.setContentDescription("close");
        this.D.setVisibility(4);
        this.bm.setId(bj);
        this.bm.setContentDescription("icon");
        this.cr.setId(aF);
        this.cr.setLines(1);
        this.cr.setEllipsize(TextUtils.TruncateAt.END);
        this.dm.setId(dl);
        this.dm.setLines(1);
        this.dm.setEllipsize(TextUtils.TruncateAt.END);
        this.dn.setId(bI);
        this.dn.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.bQ.setId(bH);
        this.bQ.setPadding(this.aC.l(15), this.aC.l(10), this.aC.l(15), this.aC.l(10));
        this.bQ.setMinimumWidth(this.aC.l(100));
        this.bQ.setMaxEms(12);
        this.bQ.setTransformationMethod(null);
        this.bQ.setSingleLine();
        this.bQ.setTextSize(18.0f);
        this.bQ.setEllipsize(TextUtils.TruncateAt.END);
        if (Build.VERSION.SDK_INT >= 21) {
            this.bQ.setElevation(this.aC.l(2));
        }
        ck.a(this.bQ, -16733198, -16746839, this.aC.l(2));
        this.bQ.setTextColor(-1);
        this.f705do.setId(dk);
        this.f705do.setPadding(0, 0, 0, this.aC.l(8));
        this.f705do.setSideSlidesMargins(this.aC.l(10));
        if (this.bd) {
            this.dp = this.aC.l(18);
            this.cR = this.dp;
            DisplayMetrics displayMetrics = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) context.getSystemService("window");
            if (windowManager != null) {
                windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            }
            this.cr.setTextSize(this.aC.m(24));
            this.dn.setTextSize(this.aC.m(20));
            this.dm.setTextSize(this.aC.m(20));
            this.dq = this.aC.l(96);
            this.cr.setTypeface(null, 1);
        } else {
            this.cR = this.aC.l(12);
            this.dp = this.aC.l(10);
            this.cr.setTextSize(22.0f);
            this.dn.setTextSize(18.0f);
            this.dm.setTextSize(18.0f);
            this.dq = this.aC.l(64);
        }
        ck.a(this, "ad_view");
        ck.a(this.cr, "title_text");
        ck.a(this.dn, "description_text");
        ck.a(this.bm, "icon_image");
        ck.a(this.D, "close_button");
        ck.a(this.dm, "category_text");
        addView(this.f705do);
        addView(this.bm);
        addView(this.cr);
        addView(this.dm);
        addView(this.dn);
        addView(this.D);
        addView(this.bQ);
        this.be = new HashMap<>();
    }

    @Override // com.my.target.eh
    public final void setBanner(@NonNull com.my.target.core.models.banners.i iVar) {
        ImageData closeIcon = iVar.getCloseIcon();
        if (closeIcon != null && closeIcon.getData() != null) {
            this.D.a(closeIcon.getData(), true);
        } else {
            Bitmap bitmapH = bq.h(this.aC.l(28));
            if (bitmapH != null) {
                this.D.a(bitmapH, false);
            }
        }
        this.bQ.setText(iVar.getCtaText());
        ImageData icon = iVar.getIcon();
        if (icon != null) {
            this.bm.setPlaceholderHeight(icon.getHeight());
            this.bm.setPlaceholderWidth(icon.getWidth());
            cf.a(icon, this.bm);
        }
        this.cr.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.cr.setText(iVar.getTitle());
        String category = iVar.getCategory();
        String subCategory = iVar.getSubCategory();
        String str = "";
        if (!TextUtils.isEmpty(category)) {
            str = "" + category;
        }
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(subCategory)) {
            str = str + ", ";
        }
        if (!TextUtils.isEmpty(subCategory)) {
            str = str + subCategory;
        }
        if (!TextUtils.isEmpty(str)) {
            this.dm.setText(str);
            this.dm.setVisibility(0);
        } else {
            this.dm.setVisibility(8);
        }
        this.dn.setText(iVar.getDescription());
        this.f705do.b(iVar.getInterstitialAdCards());
    }

    @Override // com.my.target.eh
    public final void setInterstitialPromoViewListener(@Nullable eh.a aVar) {
        this.bz = aVar;
    }

    public final void setCarouselListener(@Nullable a aVar) {
        this.f705do.setCarouselListener(aVar);
    }

    @Override // com.my.target.eh
    public final void N() {
        this.D.setVisibility(0);
    }

    @Override // com.my.target.eh
    @SuppressLint({"ClickableViewAccessibility"})
    public final void setClickArea(@NonNull ag agVar) {
        boolean z = true;
        if (agVar.cT) {
            setOnClickListener(new View.OnClickListener() { // from class: com.my.target.ep.1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    if (ep.this.bz != null) {
                        ep.this.bz.K();
                    }
                }
            });
            ck.a(this, -1, -3806472);
            setClickable(true);
            return;
        }
        this.cr.setOnTouchListener(this);
        this.dm.setOnTouchListener(this);
        this.bm.setOnTouchListener(this);
        this.dn.setOnTouchListener(this);
        this.bQ.setOnTouchListener(this);
        setOnTouchListener(this);
        this.be.put(this.cr, Boolean.valueOf(agVar.cH));
        this.be.put(this.dm, Boolean.valueOf(agVar.cR));
        this.be.put(this.bm, Boolean.valueOf(agVar.cJ));
        this.be.put(this.dn, Boolean.valueOf(agVar.cI));
        HashMap<View, Boolean> map = this.be;
        Button button = this.bQ;
        if (!agVar.cS && !agVar.cN) {
            z = false;
        }
        map.put(button, Boolean.valueOf(z));
        this.be.put(this, Boolean.valueOf(agVar.cS));
    }

    @Override // com.my.target.eh
    @NonNull
    public final View getCloseButton() {
        return this.D;
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        if (!this.be.containsKey(view)) {
            return false;
        }
        if (!this.be.get(view).booleanValue()) {
            return true;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            setBackgroundColor(-3806472);
        } else if (action == 1) {
            setBackgroundColor(-1);
            eh.a aVar = this.bz;
            if (aVar != null) {
                aVar.K();
            }
        } else if (action == 3) {
            setBackgroundColor(-1);
        }
        return true;
    }

    @NonNull
    public final int[] getNumbersOfCurrentShowingCards() {
        int iFindFirstVisibleItemPosition = this.f705do.getCardLayoutManager().findFirstVisibleItemPosition();
        int iFindLastCompletelyVisibleItemPosition = this.f705do.getCardLayoutManager().findLastCompletelyVisibleItemPosition();
        int i = 0;
        if (iFindFirstVisibleItemPosition == -1 || iFindLastCompletelyVisibleItemPosition == -1) {
            return new int[0];
        }
        int i2 = (iFindLastCompletelyVisibleItemPosition - iFindFirstVisibleItemPosition) + 1;
        int[] iArr = new int[i2];
        while (i < i2) {
            iArr[i] = iFindFirstVisibleItemPosition;
            i++;
            iFindFirstVisibleItemPosition++;
        }
        return iArr;
    }

    @Override // android.view.View
    protected final void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        this.D.measure(View.MeasureSpec.makeMeasureSpec(size, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(size2, Integer.MIN_VALUE));
        this.bm.measure(View.MeasureSpec.makeMeasureSpec(this.dq, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(this.dq, Integer.MIN_VALUE));
        if (size2 > size || this.bd) {
            this.bQ.setVisibility(8);
            int measuredHeight = this.D.getMeasuredHeight();
            if (this.bd) {
                measuredHeight = this.dp;
            }
            this.cr.measure(View.MeasureSpec.makeMeasureSpec((size - (this.dp * 2)) - this.bm.getMeasuredWidth(), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(size2, Integer.MIN_VALUE));
            this.dm.measure(View.MeasureSpec.makeMeasureSpec((size - (this.dp * 2)) - this.bm.getMeasuredWidth(), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(size2, Integer.MIN_VALUE));
            this.dn.measure(View.MeasureSpec.makeMeasureSpec(size - (this.dp * 2), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(size2, Integer.MIN_VALUE));
            int iMax = ((size2 - measuredHeight) - Math.max(this.cr.getMeasuredHeight() + this.dm.getMeasuredHeight(), this.bm.getMeasuredHeight() - (this.dp * 2))) - this.dn.getMeasuredHeight();
            int i3 = size - this.dp;
            if (size2 > size) {
                double d = iMax / size2;
                double d2 = this.dr;
                if (d > d2) {
                    double d3 = size2;
                    Double.isNaN(d3);
                    iMax = (int) (d3 * d2);
                }
            }
            if (this.bd) {
                this.f705do.measure(View.MeasureSpec.makeMeasureSpec(i3, 1073741824), View.MeasureSpec.makeMeasureSpec(iMax - (this.dp * 2), Integer.MIN_VALUE));
            } else {
                this.f705do.measure(View.MeasureSpec.makeMeasureSpec(i3, 1073741824), View.MeasureSpec.makeMeasureSpec(iMax - (this.dp * 2), 1073741824));
            }
        } else {
            this.bQ.setVisibility(0);
            this.bQ.measure(View.MeasureSpec.makeMeasureSpec(size, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(size2, Integer.MIN_VALUE));
            int measuredWidth = this.bQ.getMeasuredWidth();
            int i4 = size / 2;
            int i5 = this.dp;
            if (measuredWidth > i4 - (i5 * 2)) {
                this.bQ.measure(View.MeasureSpec.makeMeasureSpec(i4 - (i5 * 2), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(size2, Integer.MIN_VALUE));
            }
            this.cr.measure(View.MeasureSpec.makeMeasureSpec((((size - this.bm.getMeasuredWidth()) - measuredWidth) - this.cR) - this.dp, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(size2, Integer.MIN_VALUE));
            this.dm.measure(View.MeasureSpec.makeMeasureSpec((((size - this.bm.getMeasuredWidth()) - measuredWidth) - this.cR) - this.dp, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(size2, Integer.MIN_VALUE));
            this.f705do.measure(View.MeasureSpec.makeMeasureSpec(size - this.dp, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec((((size2 - Math.max(this.bm.getMeasuredHeight(), Math.max(this.bQ.getMeasuredHeight(), this.cr.getMeasuredHeight() + this.dm.getMeasuredHeight()))) - (this.dp * 2)) - this.f705do.getPaddingBottom()) - this.f705do.getPaddingTop(), Integer.MIN_VALUE));
        }
        setMeasuredDimension(size, size2);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6 = i3 - i;
        int i7 = i4 - i2;
        bw bwVar = this.D;
        bwVar.layout(i3 - bwVar.getMeasuredWidth(), i2, i3, this.D.getMeasuredHeight() + i2);
        if (i7 > i6 || this.bd) {
            int bottom = this.D.getBottom();
            int measuredHeight = this.f705do.getMeasuredHeight() + Math.max(this.cr.getMeasuredHeight() + this.dm.getMeasuredHeight(), this.bm.getMeasuredHeight()) + this.dn.getMeasuredHeight() + (this.dp * 2);
            if (measuredHeight < i7 && (i5 = (i7 - measuredHeight) / 2) > bottom) {
                bottom = i5;
            }
            by byVar = this.bm;
            byVar.layout(this.dp + i, bottom, byVar.getMeasuredWidth() + i + this.dp, i2 + this.bm.getMeasuredHeight() + bottom);
            this.cr.layout(this.bm.getRight(), bottom, this.bm.getRight() + this.cr.getMeasuredWidth(), this.cr.getMeasuredHeight() + bottom);
            this.dm.layout(this.bm.getRight(), this.cr.getBottom(), this.bm.getRight() + this.dm.getMeasuredWidth(), this.cr.getBottom() + this.dm.getMeasuredHeight());
            int iMax = Math.max(Math.max(this.bm.getBottom(), this.dm.getBottom()), this.cr.getBottom());
            TextView textView = this.dn;
            int i8 = this.dp;
            textView.layout(i + i8, iMax, i8 + i + textView.getMeasuredWidth(), this.dn.getMeasuredHeight() + iMax);
            int iMax2 = Math.max(iMax, this.dn.getBottom());
            int i9 = this.dp;
            int i10 = iMax2 + i9;
            eo eoVar = this.f705do;
            eoVar.layout(i + i9, i10, i3, eoVar.getMeasuredHeight() + i10);
            this.f705do.g(!this.bd);
            return;
        }
        this.f705do.g(false);
        by byVar2 = this.bm;
        int i11 = this.dp;
        byVar2.layout(i11, (i4 - i11) - byVar2.getMeasuredHeight(), this.dp + this.bm.getMeasuredWidth(), i4 - this.dp);
        int iMax3 = ((Math.max(this.bm.getMeasuredHeight(), this.bQ.getMeasuredHeight()) - this.cr.getMeasuredHeight()) - this.dm.getMeasuredHeight()) / 2;
        if (iMax3 < 0) {
            iMax3 = 0;
        }
        this.dm.layout(this.bm.getRight(), ((i4 - this.dp) - iMax3) - this.dm.getMeasuredHeight(), this.bm.getRight() + this.dm.getMeasuredWidth(), (i4 - this.dp) - iMax3);
        this.cr.layout(this.bm.getRight(), this.dm.getTop() - this.cr.getMeasuredHeight(), this.bm.getRight() + this.cr.getMeasuredWidth(), this.dm.getTop());
        int iMax4 = (Math.max(this.bm.getMeasuredHeight(), this.cr.getMeasuredHeight() + this.dm.getMeasuredHeight()) - this.bQ.getMeasuredHeight()) / 2;
        if (iMax4 < 0) {
            iMax4 = 0;
        }
        Button button = this.bQ;
        int measuredWidth = (i3 - this.dp) - button.getMeasuredWidth();
        int measuredHeight2 = ((i4 - this.dp) - iMax4) - this.bQ.getMeasuredHeight();
        int i12 = this.dp;
        button.layout(measuredWidth, measuredHeight2, i3 - i12, (i4 - i12) - iMax4);
        eo eoVar2 = this.f705do;
        int i13 = this.dp;
        eoVar2.layout(i13, i13, i3, eoVar2.getMeasuredHeight() + i13);
        this.dn.layout(0, 0, 0, 0);
    }
}
