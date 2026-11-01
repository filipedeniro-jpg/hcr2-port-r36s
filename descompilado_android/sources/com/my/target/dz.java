package com.my.target;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.common.models.ImageData;
import com.my.target.nativeads.views.MediaAdView;

/* JADX INFO: compiled from: InterstitialImageView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class dz extends RelativeLayout {
    private static final int ay = ck.br();

    @NonNull
    private final bw D;

    @NonNull
    private final by aA;

    @NonNull
    private final bu aB;

    @NonNull
    private final ck aC;

    @Nullable
    private ImageData aD;

    @Nullable
    private ImageData aE;

    @NonNull
    private final RelativeLayout.LayoutParams az;

    public dz(Context context) {
        super(context);
        setBackgroundColor(0);
        this.aC = ck.x(context);
        this.aA = new by(context);
        this.aA.setId(ay);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13);
        this.aA.setLayoutParams(layoutParams);
        addView(this.aA);
        this.D = new bw(context);
        this.D.a(bq.h((int) TypedValue.applyDimension(1, 28.0f, context.getResources().getDisplayMetrics())), false);
        this.az = new RelativeLayout.LayoutParams(-2, -2);
        this.az.addRule(7, ay);
        this.az.addRule(6, ay);
        this.D.setLayoutParams(this.az);
        this.aB = new bu(context);
        addView(this.D);
        addView(this.aB);
    }

    @NonNull
    public final bw getCloseButton() {
        return this.D;
    }

    @NonNull
    public final ImageView getImageView() {
        return this.aA;
    }

    public final void a(@Nullable ImageData imageData, @Nullable ImageData imageData2, @Nullable ImageData imageData3) {
        this.aE = imageData;
        this.aD = imageData2;
        Bitmap bitmap = imageData3 != null ? imageData3.getBitmap() : null;
        if (bitmap != null) {
            this.D.a(bitmap, true);
            RelativeLayout.LayoutParams layoutParams = this.az;
            int i = -this.D.getMeasuredWidth();
            layoutParams.leftMargin = i;
            layoutParams.bottomMargin = i;
        }
        M();
    }

    public final void setAgeRestrictions(@NonNull String str) {
        if (!TextUtils.isEmpty(str)) {
            this.aB.c(1, -7829368);
            this.aB.setPadding(this.aC.l(2), 0, 0, 0);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            int iL = this.aC.l(10);
            layoutParams.topMargin = iL;
            layoutParams.leftMargin = iL;
            layoutParams.addRule(5, ay);
            layoutParams.addRule(6, ay);
            this.aB.setLayoutParams(layoutParams);
            this.aB.setTextColor(MediaAdView.COLOR_PLACEHOLDER_GRAY);
            this.aB.a(1, MediaAdView.COLOR_PLACEHOLDER_GRAY, this.aC.l(3));
            this.aB.setBackgroundColor(1711276032);
            this.aB.setText(str);
            return;
        }
        this.aB.setVisibility(8);
    }

    @Override // android.view.View
    protected final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        M();
    }

    private void M() {
        WindowManager windowManager = (WindowManager) getContext().getApplicationContext().getSystemService("window");
        if (windowManager != null) {
            Display defaultDisplay = windowManager.getDefaultDisplay();
            ImageData imageData = ((float) defaultDisplay.getWidth()) / ((float) defaultDisplay.getHeight()) > 1.0f ? this.aE : this.aD;
            if (imageData == null && (imageData = this.aE) == null) {
                imageData = this.aD;
            }
            if (imageData == null) {
                return;
            }
            this.aA.setImageData(imageData);
        }
    }
}
