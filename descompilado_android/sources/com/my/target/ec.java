package com.my.target;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import java.util.HashMap;

/* JADX INFO: compiled from: BodyView.java */
/* JADX INFO: loaded from: classes2.dex */
@SuppressLint({"ViewConstructor"})
public final class ec extends FrameLayout implements View.OnTouchListener {

    @NonNull
    private final ck aC;

    @NonNull
    private final TextView aV;

    @NonNull
    private final TextView aW;

    @NonNull
    private final TextView aX;

    @NonNull
    private final LinearLayout aY;

    @NonNull
    private final LinearLayout aZ;

    @NonNull
    private final TextView ba;

    @NonNull
    private final bz bb;

    @NonNull
    private final TextView bc;
    private final boolean bd;

    @NonNull
    private final HashMap<View, Boolean> be;

    @Nullable
    private View.OnClickListener bf;

    @Nullable
    private String navigationType;

    public ec(@NonNull Context context, @NonNull ck ckVar, boolean z) {
        super(context);
        this.be = new HashMap<>();
        this.aV = new TextView(context);
        this.aW = new TextView(context);
        this.aX = new TextView(context);
        this.aY = new LinearLayout(context);
        this.ba = new TextView(context);
        this.bb = new bz(context);
        this.bc = new TextView(context);
        this.aZ = new LinearLayout(context);
        ck.a(this.aV, "title_text");
        ck.a(this.aX, "description_text");
        ck.a(this.ba, "disclaimer_text");
        ck.a(this.bb, "stars_view");
        ck.a(this.bc, "votes_text");
        this.aC = ckVar;
        this.bd = z;
    }

    public final void setBanner(@NonNull com.my.target.core.models.banners.i iVar) {
        this.navigationType = iVar.getNavigationType();
        this.aV.setText(iVar.getTitle());
        this.aX.setText(iVar.getDescription());
        this.bb.setRating(iVar.getRating());
        this.bc.setText(String.valueOf(iVar.getVotes()));
        if ("store".equals(iVar.getNavigationType())) {
            ck.a(this.aW, "category_text");
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
                this.aW.setText(str);
                this.aW.setVisibility(0);
            } else {
                this.aW.setVisibility(8);
            }
            this.aY.setVisibility(0);
            this.aY.setGravity(16);
            if (iVar.getRating() > 0.0f) {
                this.bb.setVisibility(0);
                if (iVar.getVotes() > 0) {
                    this.bc.setVisibility(0);
                } else {
                    this.bc.setVisibility(8);
                }
            } else {
                this.bb.setVisibility(8);
                this.bc.setVisibility(8);
            }
            this.aW.setTextColor(-3355444);
        } else {
            ck.a(this.aW, "domain_text");
            this.aY.setVisibility(8);
            this.aW.setText(iVar.getDomain());
            this.aY.setVisibility(8);
            this.aW.setTextColor(-16733198);
        }
        if (TextUtils.isEmpty(iVar.getDisclaimer())) {
            this.ba.setVisibility(8);
        } else {
            this.ba.setVisibility(0);
            this.ba.setText(iVar.getDisclaimer());
        }
        if (this.bd) {
            this.aV.setTextSize(2, 32.0f);
            this.aX.setTextSize(2, 24.0f);
            this.ba.setTextSize(2, 18.0f);
            this.aW.setTextSize(2, 18.0f);
            return;
        }
        this.aV.setTextSize(2, 20.0f);
        this.aX.setTextSize(2, 16.0f);
        this.ba.setTextSize(2, 14.0f);
        this.aW.setTextSize(2, 16.0f);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public final void a(@NonNull ag agVar, @NonNull View.OnClickListener onClickListener) {
        if (agVar.cT) {
            setOnClickListener(onClickListener);
            ck.a(this, -1, -3806472);
            return;
        }
        this.bf = onClickListener;
        this.aV.setOnTouchListener(this);
        this.aW.setOnTouchListener(this);
        this.aX.setOnTouchListener(this);
        this.bb.setOnTouchListener(this);
        this.bc.setOnTouchListener(this);
        setOnTouchListener(this);
        this.be.put(this.aV, Boolean.valueOf(agVar.cH));
        if ("store".equals(this.navigationType)) {
            this.be.put(this.aW, Boolean.valueOf(agVar.cR));
        } else {
            this.be.put(this.aW, Boolean.valueOf(agVar.cQ));
        }
        this.be.put(this.aX, Boolean.valueOf(agVar.cI));
        this.be.put(this.bb, Boolean.valueOf(agVar.cL));
        this.be.put(this.bc, Boolean.valueOf(agVar.cM));
        this.be.put(this, Boolean.valueOf(agVar.cS));
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
            View.OnClickListener onClickListener = this.bf;
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
        } else if (action == 3) {
            setBackgroundColor(-1);
        }
        return true;
    }

    final void d(boolean z) {
        this.aZ.setOrientation(1);
        this.aZ.setGravity(1);
        this.aV.setGravity(1);
        this.aV.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 1;
        layoutParams.leftMargin = this.aC.l(8);
        layoutParams.rightMargin = this.aC.l(8);
        this.aV.setLayoutParams(layoutParams);
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-2, -2);
        layoutParams2.gravity = 1;
        this.aW.setLayoutParams(layoutParams2);
        this.aW.setLines(1);
        this.aW.setEllipsize(TextUtils.TruncateAt.MIDDLE);
        this.aX.setGravity(1);
        this.aX.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(-2, -2);
        if (z) {
            this.aX.setTextSize(2, 12.0f);
            this.aX.setLines(2);
            this.aX.setEllipsize(TextUtils.TruncateAt.END);
            layoutParams3.topMargin = 0;
            layoutParams3.leftMargin = this.aC.l(4);
            layoutParams3.rightMargin = this.aC.l(4);
        } else {
            this.aX.setTextSize(2, 16.0f);
            layoutParams3.topMargin = this.aC.l(8);
            layoutParams3.leftMargin = this.aC.l(16);
            layoutParams3.rightMargin = this.aC.l(16);
        }
        layoutParams3.gravity = 1;
        this.aX.setLayoutParams(layoutParams3);
        this.aY.setOrientation(0);
        FrameLayout.LayoutParams layoutParams4 = new FrameLayout.LayoutParams(-2, -2);
        layoutParams4.gravity = 1;
        this.aY.setLayoutParams(layoutParams4);
        FrameLayout.LayoutParams layoutParams5 = new FrameLayout.LayoutParams(this.aC.l(73), this.aC.l(12));
        layoutParams5.topMargin = this.aC.l(4);
        layoutParams5.rightMargin = this.aC.l(4);
        this.bb.setLayoutParams(layoutParams5);
        this.bc.setTextColor(-6710887);
        this.bc.setTextSize(2, 14.0f);
        this.ba.setTextColor(-6710887);
        this.ba.setGravity(1);
        FrameLayout.LayoutParams layoutParams6 = new FrameLayout.LayoutParams(-2, -2);
        layoutParams6.gravity = 1;
        if (z) {
            layoutParams6.leftMargin = this.aC.l(4);
            layoutParams6.rightMargin = this.aC.l(4);
        } else {
            layoutParams6.leftMargin = this.aC.l(16);
            layoutParams6.rightMargin = this.aC.l(16);
        }
        layoutParams6.gravity = 1;
        this.ba.setLayoutParams(layoutParams6);
        FrameLayout.LayoutParams layoutParams7 = new FrameLayout.LayoutParams(-2, -2);
        layoutParams7.gravity = 17;
        addView(this.aZ, layoutParams7);
        this.aZ.addView(this.aV);
        this.aZ.addView(this.aW);
        this.aZ.addView(this.aY);
        this.aZ.addView(this.aX);
        this.aZ.addView(this.ba);
        this.aY.addView(this.bb);
        this.aY.addView(this.bc);
    }
}
