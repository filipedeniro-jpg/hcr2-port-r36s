package com.my.target;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.my.target.common.models.ImageData;
import com.my.target.nativeads.views.MediaAdView;

/* JADX INFO: compiled from: SliderImageView.java */
/* JADX INFO: loaded from: classes2.dex */
@SuppressLint({"ViewConstructor"})
public final class es extends FrameLayout {

    @NonNull
    private final by aA;

    @Nullable
    private bu aB;

    @NonNull
    private final ck aC;
    private final int dI;
    private final int dJ;

    @NonNull
    private final RelativeLayout dK;

    public es(@NonNull Context context, int i) {
        super(context);
        this.aC = ck.x(context);
        this.dK = new RelativeLayout(context);
        this.aA = new by(getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(13, -1);
        this.aA.setLayoutParams(layoutParams);
        this.aA.setScaleType(ImageView.ScaleType.FIT_XY);
        this.dI = this.aC.l(8);
        this.dJ = this.aC.l(8);
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-2, -2);
        layoutParams2.gravity = 17;
        addView(this.dK, layoutParams2);
        this.dK.addView(this.aA);
        this.dK.setBackgroundColor(i);
        setClipToPadding(false);
        if (Build.VERSION.SDK_INT >= 21) {
            this.dK.setElevation(this.aC.l(4));
        }
    }

    @NonNull
    @VisibleForTesting
    final by getImageView() {
        return this.aA;
    }

    public final void setImage(@NonNull ImageData imageData) {
        this.aA.setPlaceholderWidth(imageData.getWidth());
        this.aA.setPlaceholderHeight(imageData.getHeight());
        cf.a(imageData, this.aA);
        if (getResources().getConfiguration().orientation == 2) {
            int i = this.dI;
            setPadding(i, i, i, i);
        } else {
            int i2 = this.dJ;
            setPadding(i2, i2, i2, i2);
        }
    }

    public final void setAgeRestrictions(@NonNull String str) {
        if (this.aB == null) {
            this.aB = new bu(getContext());
            this.aB.c(1, -7829368);
            this.aB.setPadding(this.aC.l(2), 0, 0, 0);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.setMargins(this.aC.l(8), this.aC.l(20), this.aC.l(8), this.aC.l(20));
            this.aB.setLayoutParams(layoutParams);
            this.aB.setTextColor(MediaAdView.COLOR_PLACEHOLDER_GRAY);
            this.aB.a(1, MediaAdView.COLOR_PLACEHOLDER_GRAY, this.aC.l(3));
            this.aB.setBackgroundColor(1711276032);
            this.dK.addView(this.aB);
        }
        this.aB.setText(str);
    }
}
