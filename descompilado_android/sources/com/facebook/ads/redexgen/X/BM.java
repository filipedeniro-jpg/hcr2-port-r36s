package com.facebook.ads.redexgen.X;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;

/* JADX INFO: loaded from: assets/audience_network.dex */
@TargetApi(19)
public final class BM extends LinearLayout {
    private Drawable B;
    private TextView C;
    private TextView D;

    public BM(Context context) {
        super(context);
        B();
    }

    private void B() {
        float f = getResources().getDisplayMetrics().density;
        setOrientation(1);
        this.D = new TextView(getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        this.D.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.D.setTextSize(2, 20.0f);
        this.D.setEllipsize(TextUtils.TruncateAt.END);
        this.D.setSingleLine(true);
        this.D.setVisibility(8);
        addView(this.D, layoutParams);
        this.C = new TextView(getContext());
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        this.C.setAlpha(0.5f);
        this.C.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.C.setTextSize(2, 15.0f);
        this.C.setCompoundDrawablePadding((int) (5.0f * f));
        this.C.setEllipsize(TextUtils.TruncateAt.END);
        this.C.setSingleLine(true);
        this.C.setVisibility(8);
        addView(this.C, layoutParams2);
    }

    private Drawable getPadlockDrawable() {
        if (this.B == null) {
            this.B = C0605Ir.B(getContext(), EnumC0606Is.BROWSER_PADLOCK);
        }
        return this.B;
    }

    public void setSubtitle(String subtitle) {
        if (TextUtils.isEmpty(subtitle)) {
            this.C.setText((CharSequence) null);
            this.C.setVisibility(8);
        } else {
            Uri uri = Uri.parse(subtitle);
            this.C.setText(uri.getHost());
            this.C.setCompoundDrawablesRelativeWithIntrinsicBounds("https".equals(uri.getScheme()) ? getPadlockDrawable() : null, (Drawable) null, (Drawable) null, (Drawable) null);
            this.C.setVisibility(0);
        }
    }

    public void setTitle(String title) {
        if (TextUtils.isEmpty(title)) {
            this.D.setText((CharSequence) null);
            this.D.setVisibility(8);
        } else {
            this.D.setText(title);
            this.D.setVisibility(0);
        }
    }
}
